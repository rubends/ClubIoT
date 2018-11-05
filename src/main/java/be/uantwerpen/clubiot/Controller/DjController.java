package be.uantwerpen.clubiot.Controller;

import be.uantwerpen.clubiot.Model.Music;
import be.uantwerpen.clubiot.Service.BrokerService;
import be.uantwerpen.clubiot.Service.DatabaseService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

//    @RequestMapping(value="/api/search", method= RequestMethod.GET)
//    @ResponseBody
//    public Iterable searchSong(@RequestParam(value = "song", required = false) String search, ModelMap model)
//    {
//        Iterable<Music> songResults = databaseService.findSongByText(search);
//        model.addAttribute("searchMusic", searchMusic);
//        return songResults;
//    }

    @RequestMapping(value="/api/search", method= RequestMethod.GET)
    @ResponseBody
    public JSONObject searchSong(@RequestParam(value = "song", required = false) String search)
    {
        System.out.println("Test - The received string is: "+ search);

        Iterable<Music> songResults = databaseService.findSongByText(search);

        // Code from DashboardController
        JSONArray songArray = new JSONArray();
        for(Music song: songResults)
        {
            JSONObject jsonSong = new JSONObject();
            jsonSong.put("id", song.getId());
            jsonSong.put("title", song.getTitle());
            jsonSong.put("artist", song.getArtist());
            jsonSong.put("year", song.getYear());
            songArray.add(jsonSong);
        }
        JSONObject searchResults = new JSONObject();
        searchResults.put("data", songArray);
        return searchResults;
    }

    @RequestMapping(value="/api/play/{id}", method= RequestMethod.POST)
    @ResponseBody
    public Music playSong(@PathVariable int id)
    {
        //new connection, otherwise no idea when to disconnect
        brokerService.connect("tcp://143.129.39.126:1883", "dj_web", "a134bie5"); // open connection: "tcp://iot.eclipse.org:1883"
        brokerService.subscribe("music", 2);
        Music song = databaseService.findSongById(id);
        brokerService.playSong("music", song);
        brokerService.disconnect();
        return song;
    }
}