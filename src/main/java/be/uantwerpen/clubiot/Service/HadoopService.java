package be.uantwerpen.clubiot.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HadoopService {

    @Value("${hadoop.host}")
    private String serverIp;

    @Value("${hadoop.port}")
    private int serverPort;


    public void startAll() {
        // start all calculations in parallel
        Thread votes = new Thread(this::startCountVotes);
        votes.start();

        Thread topUser = new Thread(this::startTopUser);
        topUser.start();

        try {
            //wait for all threads to finish
            votes.join();
            topUser.join();
        }
        catch (InterruptedException e) {
            System.err.println("Thread join was interrupted");
        }
    }

    public void startCountVotes() {
        startHadoopCalculation("countVotes");
    }

    public void startTopUser() {
        startHadoopCalculation("countUserVotes");
    }

    private void startHadoopCalculation(String name) {
        String uri = "http://" + serverIp + ":" + serverPort + "/" + name;
        System.out.println("Requesting Hadoop start via "+uri);

        RestTemplate restTemplate = restTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(uri, String.class);

        // will wait here for Hadoop response

        if(response.getStatusCode() != HttpStatus.OK) {
            throw new IllegalStateException("Hadoop returned an error code: "+response.getStatusCode());
        }
    }

    private RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(25000);
        factory.setConnectTimeout(100);
        return factory;
    }

}
