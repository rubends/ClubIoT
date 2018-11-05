package be.uantwerpen.clubiot.Service;

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * Return the sum of all up- and downvotes for a song with a given ID.
     * @param songId
     * @return
     */
    public int getSongVotes (long songId)
    {
        DB database = this.mongo.getDb();
        DBCollection voteCache = database.getCollection("vote_cache");

        DBObject query = new BasicDBObject("_id", Long.toString(songId));

        DBCursor voteCacheIt = voteCache.find(query);

        if (voteCacheIt.count() == 0)
        {
            System.err.println("Song ID \"" + songId + "\" wasn't present in NoSQL Database.");
            return 0;
        }

        int numVotes = 0;

        while (voteCacheIt.hasNext())
        {
            DBObject object = voteCacheIt.next();

            numVotes += (Integer)object.get("value");
        }

        voteCacheIt.close();


        return numVotes;
    }

    /**
     * Return the number of votes a user with a specific ID has cast.
     * @param userId
     * @return
     */
    public int getUserVotes (long userId)
    {
        DB database = this.mongo.getDb();
        DBCollection userVoteCache = database.getCollection("user_vote_cache");

        DBObject query = new BasicDBObject("uid", Long.toString(userId));

        DBCursor userVoteCacheIt = userVoteCache.find(query);

        if (userVoteCacheIt.count() == 0)
        {
            System.err.println("User ID \"" + userId + "\" wasn't present in NoSQL Database.");
            return 0;
        }

        int numVotes = 0;

        while (userVoteCacheIt.hasNext())
        {
            DBObject object = userVoteCacheIt.next();

            numVotes += (Integer)object.get("value");
        }

        userVoteCacheIt.close();


        return numVotes;
    }

    /**
     * Return the ID of the most popular song.
     * When no songs were found, -1 is returned.
     * @return
     */
    public long getMostPopular()
    {
        Map<Long, Long> votes = this.getVotes();

        long maxSongId = -1;
        long maxVotes = Long.MIN_VALUE;

        for (long songId : votes.keySet())
        {
            if (votes.get(songId) > maxVotes)
            {
                maxSongId = songId;
                maxVotes = votes.get(songId);
            }
        }

        return maxSongId;
    }

    /**
     *  Return the Id of the least popular song.
     *  When no songs were found, -1 is returned.
     */
    public long getLeastPopular()
    {
        Map<Long, Long> votes = this.getVotes();
        long minSongId = -1;
        long minVotes = Long.MAX_VALUE;

        for (long songId : votes.keySet())
        {
            if (votes.get(songId) < minVotes)
            {
                minSongId = songId;
                minVotes = votes.get(songId);
            }
        }

        return minSongId;
    }

    public String getMostActiveVoter()
    {
        Map<String, Long> voters = this.getUserVotes();

        String maxUserId = "";
        long maxVotes = Long.MIN_VALUE;

        for (String userId : voters.keySet())
        {
            if (voters.get(userId) > maxVotes)
            {
                maxUserId = userId;
                maxVotes = voters.get(userId);
            }
        }

        return maxUserId;
    }

    public Map<Long, Long> getVotes()
    {
        DB database = this.mongo.getDb();
        DBCollection voteCache = database.getCollection("vote_cache");
        DBCursor voteCacheIt = voteCache.find();

        if (voteCacheIt.count() == 0)
        {
            System.err.println("NoSQL doesn't contain any records");
            return new HashMap<>();
        }

        Map<Long, Long> votes = new HashMap<>();

        while (voteCacheIt.hasNext())
        {
            DBObject object =  voteCacheIt.next();

            long songId = Long.parseLong((String)object.get("_id"));
            int vote = (int)object.get("value");

            if (votes.containsKey(songId))
            {
                votes.put(songId, votes.get(songId) + vote);
            }
            else
            {
                votes.put(songId, (long)vote);
            }
        }

        return votes;
    }

    public Map<String, Long> getUserVotes()
    {
        DB database = this.mongo.getDb();
        DBCollection voteCache = database.getCollection("user_vote_cache");
        DBCursor voteCacheIt = voteCache.find();

        if (voteCacheIt.count() == 0)
        {
            System.err.println("NoSQL doesn't contain any records");
            return new HashMap<>();
        }

        Map<String, Long> votes = new HashMap<>();

        while (voteCacheIt.hasNext())
        {
            DBObject object =  voteCacheIt.next();

            String userId = (String)object.get("uid");
            int vote = (int)object.get("value");

            if (!votes.containsKey(userId))
            {
                votes.put(userId, (long)vote);
            }
        }

        return votes;
    }
}
