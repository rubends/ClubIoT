package be.uantwerpen.clubiot.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HadoopService {

    private String serverIp = "143.129.39.127";
    private int serverPort = 80;

    public void startAll() {
        Thread votes = new Thread(this::startCountVotes);
        votes.start();

        try {
            //wait for all threads to finish
            votes.join();
        }
        catch (InterruptedException e) {
            System.err.println("Thread join was interrupted");
        }
    }

    public void startCountVotes() {
        startHadoopCalculation("countVotes");
    }

    private void startHadoopCalculation(String name) {
        String uri = "http://" + serverIp + ":" + serverPort + "/" + name;

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
        factory.setReadTimeout(20000);
        factory.setConnectTimeout(100);
        return factory;
    }

}
