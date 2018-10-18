package be.uantwerpen.clubiot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardController {
    @RequestMapping({"/dashboard"})
    @ResponseBody
    public String showDashboardPage(){
        return "Hello Dashboard";
    }

    @RequestMapping(value="/api/songs/{id}", method= RequestMethod.POST)
    public String playSong(@PathVariable int id){
      return "song: " + id;
    }
}
