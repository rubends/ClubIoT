package be.uantwerpen.clubiot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller // spring --> controller class --> autowired objecten
public class DjController {
    @RequestMapping({"/","/djhomepage"})
    public String showHomepage(){ return "dj"; }

    @RequestMapping(value="/api/search", method= RequestMethod.GET)
    public String searchSong(@RequestParam("song") String song)
    {
        return song;
    }

    @RequestMapping(value="/api/play/{id}", method= RequestMethod.POST)
    public String playSong(@PathVariable String id)
    {
        return id;
    }

}