package be.uantwerpen.clubiot.Service;

import be.uantwerpen.clubiot.Model.Music;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.UUID;

@Service
public class BrokerService implements MqttCallback {

    private IMqttClient client;

    public BrokerService(){ }

    public int connect(String serverUri, String user, String password) {
        System.out.println("connecting...");
        String publisherId = UUID.randomUUID().toString();
        try{

            client = new MqttClient(serverUri,publisherId, new MemoryPersistence()); //MemoryPersistence --> MqttDefaultFilePersistence creates files

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            options.setUserName(user);
            options.setPassword(password.toCharArray());
            client.connect(options);
            client.setCallback(this); // callbacks are located in this class

            if(client.isConnected()){
                System.out.println("connected to " + serverUri);
                return 0;
            }
        }catch(MqttException e){
            System.out.println("connection failed.");
            return -1;
        }
        System.out.println("shouldn't be able to come here.");
        return -1;
    }

    public boolean isConnected(){
        return client.isConnected();
    }


    public void subscribe(String topic, int qos){
        if(isConnected()){
            try {
                client.subscribe(topic, qos);
                System.out.println("Subscribed to '" + topic + "'");
            }
            catch(MqttException e){
                System.out.println("Subscribing to '" + topic + "' failed");
            }
        }
    }

    public void disconnect(){
        if(isConnected()){
            try {
                client.disconnect();
                System.out.println("Disconnected");
            }
            catch(MqttException e){
                System.out.println("Disconnection failed");
            }
        }
    }

    public void publishString(String topic, String message){
        if(isConnected()){
            MqttMessage msg = new MqttMessage();
            msg.setPayload(message.getBytes());
            try {
                client.publish(topic, msg);
            }
            catch(MqttException e){
                System.out.println("Publishing to '" + topic +"' failed");
            }
        }
    }

    public void publishJson(String topic, JSONObject object){
        if(isConnected()){
            MqttMessage msg = new MqttMessage();
            msg.setPayload(object.toJSONString().getBytes());
            try {
                client.publish(topic, msg);
            }
            catch(MqttException e){
                System.out.println("Publishing to '" + topic +"' failed");
            }
        }
    }

    public void playSong(String topic, Music song){
        JSONObject songObj = new JSONObject();
        songObj.put("songid",song.getId());
        songObj.put("title",song.getTitle());
        songObj.put("artist",song.getArtist());
        songObj.put("year",song.getYear());
        this.publishJson(topic, songObj);
    }

    // Mqtt Callbacks

    @Override
    public void connectionLost(Throwable cause){
        System.out.println("connection lost...");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message)
            throws Exception {
        System.out.println("Message arrived from topic: " + topic );
        System.out.println("-> " + message+ "\n");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // TODO Auto-generated method stub
        System.out.println("Delivery Complete!" );
    }
}