package be.uantwerpen.clubiot.Controller;

import be.uantwerpen.clubiot.Model.Stats;
import be.uantwerpen.clubiot.Service.DatabaseService;
import be.uantwerpen.clubiot.Service.HadoopService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

    private DatabaseService databaseService;
    private HadoopService hadoopService;

    @RequestMapping(value={"/dashboard"}, method= RequestMethod.GET)
    public String showDashboard(ModelMap model){

        // TODO
        // [ ] use NOSQL service to request 'old data' ( MostLiked, BestVoter, ...)
        // [ ] *store data in "data" object
        // [ ] use data object as model to pass to dashboard template (inserted in html using thymeleaf)
        // [ ] return dashboard.html
        Stats stats = new Stats();




        model.addAttribute("stats", stats);
        return "dashboard";
    }

    @RequestMapping(value="/api/songs", method= RequestMethod.GET)
    public JSONObject getAllSongs(){
        // TODO
        // [ ] get All songs from SQL service
        // [ ] return all songs JSON object
        return null;
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
