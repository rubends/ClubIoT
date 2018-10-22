package be.uantwerpen.clubiot.Controller;

import be.uantwerpen.clubiot.Model.Music;
import be.uantwerpen.clubiot.Service.BrokerService;
import be.uantwerpen.clubiot.Service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller // spring --> controller class --> autowired objecten
public class DjController {
    @Autowired
    private BrokerService brokerService;
    @Autowired
    private DatabaseService databaseService;

    @RequestMapping(value={"/","/djpage"}, method= RequestMethod.GET)
    public String getDJpage(ModelMap model){
        //data base get all songs
        Music[] music = new Music[2];
        music[1] = new Music("Never gonna give Spring up", "Rick Spastley", 1980);
        music[2] = new Music("Spring is in the air", "Urbanus", 1996);
        model.addAttribute("music", music);
        return "dj";
    }

    @RequestMapping(value="/api/search", method= RequestMethod.GET)
    public String searchSong(@RequestParam(value = "song", required = false) String search, ModelMap model)
    {
        Music song = new Music(databaseService.findSong(search), "Britney Spears", 1982);
        model.addAttribute("song", song);
        return "dj";
    }

    @RequestMapping(value="/api/play/{id}", method= RequestMethod.POST)
    public void playSong(@PathVariable int id)
    {
        //brokerService.playSong(id);
    }

}