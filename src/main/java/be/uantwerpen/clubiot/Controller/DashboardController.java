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
    private NoSQLService noSqlService;

    @Autowired
    NoSQLService noSQLService;

    @RequestMapping(value={"/dashboard"}, method= RequestMethod.GET)
    public String showDashboard(ModelMap model){
        // [ ] use NOSQL service to request 'old data' ( MostLiked, BestVoter, ...) => returns song id
        // [ ] *store stats in "stats" object
        // [ ] use data object as model to pass to dashboard template (inserted in html using thymeleaf)
        // [ ] return dashboard.html


        // get most/least popular songs
        long mostPopularId = noSQLService.getMostPopular();
        long leastPopularId = noSQLService.getLeastPopular();

        // get vote numbers
        int mostPopularVotes = noSQLService.getSongVotes(mostPopularId);
        int leastPopularVotes = noSQLService.getSongVotes(mostPopularId);


        // get fields of most/least popular songs
        Music mostLikedSong = databaseService.findSongById((int)mostPopularId);
        Music leastLikedSong = databaseService.findSongById((int)leastPopularId);

        // combine fields/votes into Songresult objects (combined votecount instead of separate up/down)
        SongResult mostLikedResult = new SongResult(mostPopularId, mostPopularVotes, mostPopularVotes);
        mostLikedResult.setArtist(mostLikedSong.getArtist());
        mostLikedResult.setYear(mostLikedSong.getYear());
        mostLikedResult.setTitle(mostLikedSong.getTitle());
        System.out.println(mostLikedSong);

        SongResult leastLikedResult = new SongResult(leastPopularId, leastPopularVotes, leastPopularVotes);
        leastLikedResult.setArtist(leastLikedSong.getArtist());
        leastLikedResult.setYear(leastLikedSong.getYear());
        leastLikedResult.setTitle(leastLikedSong.getTitle());

        // fill stats object
        Stats stats = new Stats();
        stats.setBestVoter("Thomas");
        stats.setMostLiked(mostLikedResult);
        stats.setMostDisliked(leastLikedResult);

        // pass stats as an atrribute of dashboard
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

        //get new data
        noSQLService.refresh();
        //get leastpopuler, mostpopular and best voter
        long leastPopularID = noSQLService.getLeastPopular();
        int leastPopularVotes = noSQLService.getSongVotes(leastPopularID);

        long mostPopularID = noSQLService.getMostPopular();
        int mostPopulerVotes = noSQLService.getSongVotes(mostPopularID);

        String bestVoter = noSQLService.getMostActiveVoter();
        int votesCount = noSQLService.getUserVotes(bestVoter);

        //get names from id
        Music leastPopular = databaseService.findSongById((int)leastPopularID);
        Music mostPopular = databaseService.findSongById((int)mostPopularID);

        //JSON objects: data: [mostPopular {id, title, artist, year, votes},
        //              mostDisliked {id, title, artist, year, votes},
        //              bestVoter {name, votesCount}]

        //set object mostPopular

        JSONArray jsonArray = new JSONArray();
        JSONObject mostPopularObject = new JSONObject();
        mostPopularObject.put("id",mostPopular.getId());
        mostPopularObject.put("title",mostPopular.getTitle());
        mostPopularObject.put("artist",mostPopular.getArtist());
        mostPopularObject.put("year",mostPopular.getYear());
        mostPopularObject.put("votes",mostPopulerVotes);
        jsonArray.add(mostPopularObject);

        //set object mostDisliked
        JSONObject mostDislikedObject = new JSONObject();
        mostDislikedObject.put("id",leastPopular.getId());
        mostDislikedObject.put("title",leastPopular.getTitle());
        mostDislikedObject.put("artist",leastPopular.getArtist());
        mostDislikedObject.put("year",leastPopular.getYear());
        mostDislikedObject.put("votes",leastPopularVotes);
        jsonArray.add(mostDislikedObject);

        //set object bestvoter
        JSONObject bestVoterObject = new JSONObject();
        bestVoterObject.put("name",bestVoter);
        bestVoterObject.put("votesCount",votesCount);
        jsonArray.add(bestVoter);

        //set object data

        JSONObject data = new JSONObject();
//        data.put("mostPopular", mostPopularObject);
//        data.put("mostDisliked",mostDislikedObject);
//        data.put("bestVoter",bestVoterObject);
        data.put("data", jsonArray);
        System.out.println("data: " + data);
        return data;
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
