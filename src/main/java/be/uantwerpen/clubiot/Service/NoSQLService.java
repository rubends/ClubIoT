package be.uantwerpen.clubiot.Service;

import be.uantwerpen.clubiot.Model.SongResult;
import be.uantwerpen.clubiot.Model.Stats;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Service;

@Service
public class NoSQLService
{
    @Autowired
    HadoopService hadoopService;

    private final MongoDbFactory mongo;

    public NoSQLService(MongoDbFactory mongo)
    {
        this.mongo = mongo;
    }

    public void refresh()
    {
        hadoopService.startAll();
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
