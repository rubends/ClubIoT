package be.uantwerpen.clubiot.Model;

import java.rmi.Remote;

public interface Music extends Remote {
    String getSong(String song);
}
