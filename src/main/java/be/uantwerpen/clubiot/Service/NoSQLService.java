package be.uantwerpen.clubiot.Service;

import be.uantwerpen.clubiot.Model.SongResult;
import be.uantwerpen.clubiot.Model.Stats;
import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Service;

@Service
public class NoSQLService
{
    @Autowired
    HadoopService hadoopService;

    private final MongoDbFactory mongo;

    @Autowired
    public NoSQLService(MongoDbFactory mongo)
    {
        this.mongo = mongo;
    }

    public void refresh()
    {
        this.hadoopService.startAll();
    }

    public Stats getResults()
    {
        Stats dummy = new Stats();

        DB database = this.mongo.getDb();
        DBCollection voteCache = database.getCollection("vote_cache");
        DBCursor voteCacheIt = voteCache.find();

        while (voteCacheIt.hasNext())
        {
            DBObject object =  voteCacheIt.next();

            dummy = new Stats(object.toString(), "mostDisliked", "bestVoter", "welcome");
        }


        return dummy;
    }

    /**
     * Return the sum of all up- and downvotes for a song with a given ID.
     * @param songId
     * @return
     */
    public int getVotes (long songId)
    {
        DB database = this.mongo.getDb();

        System.out.println("Database: " + database.getName());

        for (String collection: database.getCollectionNames())
        {
            System.out.println("Collection: " + collection);
        }

        DBCollection voteCache = database.getCollection("vote_cache");

        DBObject query = new BasicDBObject("_id", songId);

        DBCursor voteCacheIt = voteCache.find(/*query*/);

        System.out.println("vote_cache: " + voteCache.count());

        /*
        if (voteCacheIt.count() == 0)
        {
            System.err.println("Song ID \"" + songId + "\" wasn't present in NoSQL Database.");
            return 0;
        }
        */
        int numVotes = 0;

        while (voteCacheIt.hasNext())
        {
            DBObject object = voteCacheIt.next();

            System.out.println(object.toString());

            numVotes += (Integer)object.get("value");
        }

        voteCacheIt.close();


        return numVotes;
    }

    public SongResult getMostPopular()
    {
        SongResult result = new SongResult();

        DB database = this.mongo.getDb();
        DBCollection voteCache = database.getCollection("vote_cache");
        DBCursor voteCacheIt = voteCache.find();

        while (voteCacheIt.hasNext())
        {
            DBObject object =  voteCacheIt.next();

            result = new SongResult();
        }


        return result;
    }

    public SongResult getMostDisliked()
    {
        SongResult result = new SongResult();

        DB database = this.mongo.getDb();
        DBCollection voteCache = database.getCollection("vote_cache");
        DBCursor voteCacheIt = voteCache.find();

        while (voteCacheIt.hasNext())
        {
            DBObject object =  voteCacheIt.next();

            result = new SongResult();
        }

        return result;
    }
}
