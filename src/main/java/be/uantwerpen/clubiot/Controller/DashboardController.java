package be.uantwerpen.clubiot.Controller;

import be.uantwerpen.clubiot.Model.VotesDummy;
import be.uantwerpen.clubiot.Service.BrokerService;
import be.uantwerpen.clubiot.Service.DatabaseService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.text.html.parser.Parser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Controller
public class DashboardController {

    private BrokerService brokerService;
    private DatabaseService databaseService;

    @RequestMapping({"/dashboard"})
    public String showDashboard(ModelMap model){
        initMqttBroker();
        VotesDummy votes = new VotesDummy(); // TODO get data from HADOOP
        JSONArray allMusic = parseJsonFile("./allmusic.json");               // TODO get all music data from DatabaseService
        System.out.print(allMusic); // TODO LEFTOFF trying to simulate json data commming from MUSIC DB

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
        // IN CONSTRUCTOR
       // brokerService.connect("tcp://143.129.39.126:1883", "dj_web", "a134bie5"); // open connection: "tcp://iot.eclipse.org:1883"
       // brokerService.subscribe("music");
//        brokerService.publishString("music", "Hello");
        brokerService.publishJson("music", musicObject);

    }

    // Method for testing music json array passing to Dashboard
    public JSONArray parseJsonFile(String filepath){
        JSONParser parser = new JSONParser();
        JSONArray a = new JSONArray();
        JSONArray output = new JSONArray();
        try {
            a = (JSONArray) parser.parse(new FileReader(filepath));
        }
        catch(FileNotFoundException e){
            System.out.println("file not found");
        }
        catch(IOException e){
            System.out.println("IOException");

        }
        catch(ParseException e){
            System.out.println("ParseException");
        }

        for (Object o : a) {
            JSONObject item = (JSONObject) o;
            output.add(item);
        }

        return output;
    }
}
