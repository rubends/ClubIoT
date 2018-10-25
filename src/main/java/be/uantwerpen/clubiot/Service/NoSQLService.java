package be.uantwerpen.clubiot.Service;

import be.uantwerpen.clubiot.Model.VotesDummy;
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
        return new VotesDummy();
    }
}
