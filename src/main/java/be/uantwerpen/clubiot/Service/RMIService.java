package be.uantwerpen.clubiot.Service;

import be.uantwerpen.clubiot.Model.Music;
import org.springframework.stereotype.Service;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

@Service
public class RMIService {
    private Music music;
    private String serverIp = "127.0.0.1";

    public RMIService(){
        /*if (System.getSecurityManager() == null) {
            System.setProperty("java.security.policy","file:src/client.policy");
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Bank";
            Registry registry = LocateRegistry.getRegistry(serverIp);
            music = (Music) registry.lookup(name);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public String findSong(String song){
        return music.getSong(song);
    }
}
