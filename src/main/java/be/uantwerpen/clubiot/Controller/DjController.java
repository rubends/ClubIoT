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
        Iterable<Music> music = databaseService.findAll();
        model.addAttribute("music", music);
        return "dj";
    }

    @RequestMapping(value="/api/search", method= RequestMethod.GET)
    public String searchSong(@RequestParam(value = "song", required = false) String search, ModelMap model)
    {
        Iterable<Music> songs = databaseService.findSongByText(search);
        model.addAttribute("songs", songs);
        return "dj";
    }

    @RequestMapping(value="/api/play/{id}", method= RequestMethod.POST)
    public void playSong(@PathVariable int id)
    {
        Music song = databaseService.findSongById(id);
        brokerService.playSong(song);
    }
}