package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by KVN on 30/05/2016.
 */
public class GSLServer {
    private int port;
    private boolean alive;
    private ServerSocket serverSocket;
    private HashMap<Long, Thread> services;

    public GSLServer(int port){
        this.port = port;
        this.alive = false;
        this.services = new HashMap<Long, Thread>();
    }

    public void init(){
        try {
            this.serverSocket = new ServerSocket(this.port);
            this.alive = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        while(this.alive){
            try {
                Socket s = serverSocket.accept();
                if(s != null){
                    System.out.println("Un client s'est connecte");
                } else{
                    System.out.println("Personne de connecte");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
