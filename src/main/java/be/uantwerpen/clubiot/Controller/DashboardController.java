package be.uantwerpen.clubiot.Controller;

import be.uantwerpen.clubiot.Model.Music;
import be.uantwerpen.clubiot.Model.Stats;
import be.uantwerpen.clubiot.Service.BrokerService;
import be.uantwerpen.clubiot.Service.DatabaseService;
import be.uantwerpen.clubiot.Service.HadoopService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Controller
public class DashboardController {

    private DatabaseService databaseService;
    private HadoopService hadoopService;

    @RequestMapping({"/dashboard"})
    public String showDashboard(ModelMap model){

        databaseService = new DatabaseService();
        hadoopService = new HadoopService();

        // TODO
        // [ ] use NOSQL service to request 'old data' ( MostLiked, BestVoter, ...)
        // [ ] *store data in "data" object
        // [ ] use data object as model to pass to dashboard template (inserted in html using thymeleaf)
        // [ ] return dashboard.html


        Stats stats = new Stats();
        stats.setBestVoter("Stijn");
        stats.setMostDisliked("mostDisliked");
        stats.setMostLiked("mostLiked");

        model.addAttribute("stats", stats);
        return "test";
    }

    @RequestMapping(value="/api/songs", method= RequestMethod.GET)
    public JSONObject getAllSongs(){
        // TODO
        // [ ] get All songs from SQL service
        Iterable<Music> allMusic = databaseService.findAll();

        JSONArray songArray = new JSONArray();
        for(Music song: allMusic){
            System.out.println(song.getId() + " " + song.getTitle());

            JSONObject jsonSong = new JSONObject();
            jsonSong.put("id", song.getId());
            jsonSong.put("title", song.getTitle());
            jsonSong.put("artist", song.getArtist());
            jsonSong.put("year", song.getYear());
            songArray.add(jsonSong);
        }

        JSONObject response = new JSONObject();
        response.put("data", songArray);

        return response;
    }

    @RequestMapping(value="/api/songs/{id}", method= RequestMethod.GET)
    public JSONPObject getSongVotes(@PathVariable int id){
        // TODO
        // [ ] use NOSQL service to request vote data of {id}
        // [ ] return vote data json object
        return null;
    }

    @RequestMapping(value="/api/refresh", method= RequestMethod.GET)
    public JSONObject refresh(){
        // TODO
        // [ ] use HADOOP Service to trigger a refresh
        //      -> continue when hadoopservice returns "done"
        // [ ] use NOQL service to request the "refreshed" data ( MostLiked, BestVoter, ...)
        // [ ] return json object

        return null;
    }

//    @RequestMapping(value="/api/songs/{id}", method= RequestMethod.GET)
//    public String playSong(@PathVariable int id){
//        // TODO
//        // [ ] init mqtt here or in dashboard  TODO ?!
//        // [ ] send out
//
//      return "song: " + id;
//    }





//    public void initMqttBroker(){
//
//        JSONObject musicObject = new JSONObject();
//        musicObject.put("title","Spring Nicht");
//        musicObject.put("artist","Tokio Hotel");
//        musicObject.put("year",1906);
//        musicObject.put("songid",358666);
//
//
//        brokerService = new BrokerService();
//        // IN CONSTRUCTOR
//       // brokerService.connect("tcp://143.129.39.126:1883", "dj_web", "a134bie5"); // open connection: "tcp://iot.eclipse.org:1883"
//       // brokerService.subscribe("music");
////        brokerService.publishString("music", "Hello");
//        brokerService.publishJson("music", musicObject);
//
//    }

//    // Method for testing music json array passing to Dashboard
//    public JSONArray parseJsonFile(String filepath){
//        JSONParser parser = new JSONParser();
//        JSONArray a = new JSONArray();
//        JSONArray output = new JSONArray();
//        try {
//            a = (JSONArray) parser.parse(new FileReader(filepath));
//        }
//        catch(FileNotFoundException e){
//            System.out.println("file not found");
//        }
//        catch(IOException e){
//            System.out.println("IOException");
//
//        }
//        catch(ParseException e){
//            System.out.println("ParseException");
//        }
//
//        for (Object o : a) {
//            JSONObject item = (JSONObject) o;
//            output.add(item);
//        }
//
//        return output;
//    }

}
