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
                Thread thread = new Thread(new RemoteClientService(serverSocket.accept()));
                thread.start();

                this.services.put(thread.getId(), thread);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
