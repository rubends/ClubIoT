package be.uantwerpen.clubiot;

import be.uantwerpen.clubiot.Service.HadoopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HadoopServiceTest {
    @Autowired
    HadoopService service;

    @Test
    public void testVotes() {
        service.startCountVotes();
    }

    @Test
    public void testAll() {
        service.startAll();
    }
}