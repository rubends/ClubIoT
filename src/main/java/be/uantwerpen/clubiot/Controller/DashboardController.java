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

    @RequestMapping({"/dashboard"})
    public String showDashboard(ModelMap model){
        VotesDummy votes = new VotesDummy();
        model.addAttribute("votes", votes);
        return "dashboard";
    }
}
