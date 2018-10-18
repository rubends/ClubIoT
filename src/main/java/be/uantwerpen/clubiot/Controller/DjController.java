package be.uantwerpen.clubiot.Controller;

import be.uantwerpen.clubiot.Service.BrokerService;
import be.uantwerpen.clubiot.Service.RMIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // spring --> controller class --> autowired objecten
public class DjController {
    @Autowired
    private BrokerService brokerService;
    @Autowired
    private RMIService rmiService;

    @RequestMapping({"/","/djpage"})
    @ResponseBody
    public String showHomepage(){ return "dj"; }

    @RequestMapping(value="/api/search", method= RequestMethod.GET)
    public String searchSong(@RequestParam("song") String song)
    {
        rmiService.findSong(song);
        return song;
    }

    @RequestMapping(value="/api/play/{id}", method= RequestMethod.POST)
    public int playSong(@PathVariable int id)
    {
        brokerService.playSong(id);
        return id;
    }

}