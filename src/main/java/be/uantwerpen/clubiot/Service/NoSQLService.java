package be.uantwerpen.clubiot.Service;

import be.uantwerpen.clubiot.Model.VotesDummy;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Service;

@Service
public class NoSQLService
{
    private final MongoDbFactory mongo;

    public NoSQLService(MongoDbFactory mongo)
    {
        this.mongo = mongo;
    }

    public VotesDummy getResults()
    {
        VotesDummy dummy = new VotesDummy();

        DB database = this.mongo.getDb();
        DBCollection voteCache = database.getCollection("vote_cache");
        DBCursor voteCacheIt = voteCache.find();

        while (voteCacheIt.hasNext())
        {
            DBObject object =  voteCacheIt.next();

            dummy = new VotesDummy(object.toString(), "mostDisliked", "bestVoter", "welcome");
        }


        return dummy;
    }
}
