package be.uantwerpen.clubiot.Service;

import org.eclipse.paho.client.mqttv3.*;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.UUID;

@Service
public class BrokerService implements MqttCallback {

    private IMqttClient client;
    String serverUri ;


    public BrokerService(){
        System.out.println("Brokerservice Contructor");
    }

    public int connect(String serverUri, String user, String password) {
        System.out.println("connecting...");
        this.serverUri = serverUri;
        String publisherId = UUID.randomUUID().toString();
        try{

            client = new MqttClient(serverUri, publisherId);

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


    public void subscribe(String topic){
        if(isConnected()){
            try {
                client.subscribe(topic);
                System.out.println("Subscribed to '" + topic + "'");
            }
            catch(MqttException e){
                System.out.println("Subscribing to '" + topic + "' failed");
            }
        }
    }

//    public void publishTest(){
//        if(isConnected()){
//            MqttMessage msg = new MqttMessage();
//            msg.setPayload("Hello Mqtt".getBytes());
//            try {
//                client.publish("TopicTest", msg);
//            }
//            catch(MqttException e){
//                System.out.println("Publishing failed");
//            }
//        }
//    }

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

    public void playSong(int id){

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