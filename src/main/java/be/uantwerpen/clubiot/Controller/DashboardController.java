package be.uantwerpen.clubiot.Controller;

import be.uantwerpen.clubiot.Model.Music;
import be.uantwerpen.clubiot.Model.SongResult;
import be.uantwerpen.clubiot.Model.Stats;
import be.uantwerpen.clubiot.Service.DatabaseService;
import be.uantwerpen.clubiot.Service.HadoopService;
import be.uantwerpen.clubiot.Service.NoSQLService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardController {

    @Autowired
    private DatabaseService databaseService;

    private HadoopService hadoopService;

    @Autowired
    NoSQLService noSQLService;

    @RequestMapping(value={"/dashboard"}, method= RequestMethod.GET)
    public String showDashboard(ModelMap model){

        databaseService = new DatabaseService();
        noSQLService = new NoSQLService();
        // TODO
        // [ ] use NOSQL service to request 'old data' ( MostLiked, BestVoter, ...) => returns song id
        // [ ] *store stats in "stats" object
        // [ ] use data object as model to pass to dashboard template (inserted in html using thymeleaf)
        // [ ] return dashboard.html

        SongResult mostLikedResult = noSQLService.getMostPopular();
        SongResult mostDislikedResult = noSQLService.getMostDisliked();

        Music mostLikedSong = databaseService.findSongById(mostDislikedResult.getSongId());
        Music mostDislikedSong = databaseService.findSongById(mostDislikedResult.getSongId());


        //
        Stats stats = new Stats();
        stats.setBestVoter("Stijn");
        stats.setMostDisliked("mostDisliked");
        stats.setMostLiked("mostLiked");

        model.addAttribute("stats", stats);
        return "dashboard";
    }

    @RequestMapping(value="/api/songs", method= RequestMethod.GET)
    @ResponseBody
    public JSONObject getAllSongs(){
        // TODO
        // [ ] get All songs from SQL service
        Iterable<Music> allMusic = databaseService.findAll();

        JSONArray songArray = new JSONArray();
        for(Music song: allMusic){
//            System.out.println(song.getId() + " " + song.getTitle());

            JSONObject jsonSong = new JSONObject();
            jsonSong.put("id", song.getId());
            jsonSong.put("title", song.getTitle());
            jsonSong.put("artist", song.getArtist());
            jsonSong.put("year", song.getYear());
            songArray.add(jsonSong);
        }
        JSONObject response = new JSONObject();
        response.put("data", songArray);
        System.out.println(response);
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


    /// END END END ///






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
