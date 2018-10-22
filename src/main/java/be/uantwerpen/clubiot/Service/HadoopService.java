package be.uantwerpen.clubiot.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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

    public void startHadoop() {
       String uri = "http://" + serverIp + ":" + serverPort + "/statistics";

        RestTemplate restTemplate = restTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(uri, String.class);

        assert(response.getStatusCode() == HttpStatus.OK);

        if(response.getBody().equals("OK")) return;
        else throw new IllegalStateException("Hadoop returned an invalid response: "+response.getBody());
    }

    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(20000);
        factory.setConnectTimeout(100);
        return factory;
    }

}
