package be.uantwerpen.clubiot.Controller;

import be.uantwerpen.clubiot.Model.VotesDummy;
import be.uantwerpen.clubiot.Service.BrokerService;
import be.uantwerpen.clubiot.Service.DatabaseService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardController {

    private BrokerService brokerService;
    private DatabaseService databaseService;

    @RequestMapping({"/dashboard"})
    public String showDashboard(ModelMap model){
        initMqttBroker();
        VotesDummy votes = new VotesDummy();
        model.addAttribute("votes", votes);
        return "dashboard";
    }

    @RequestMapping(value="/api/songs/{id}", method= RequestMethod.GET)
    public String playSong(@PathVariable int id){
      return "song: " + id;
    }

    public void initMqttBroker(){

        JSONObject musicObject = new JSONObject();
        musicObject.put("title","Spring Nicht");
        musicObject.put("artist","Tokio Hotel");
        musicObject.put("year",1906);
        musicObject.put("songid",358666);


        brokerService = new BrokerService();
        brokerService.connect("tcp://143.129.39.126:1883", "dj_web", "a134bie5"); // open connection: "tcp://iot.eclipse.org:1883"
        brokerService.subscribe("music");
//        brokerService.publishString("music", "Hello");
        brokerService.publishJson("music", musicObject);

    }
}
