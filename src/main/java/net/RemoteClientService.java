package net;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by KVN on 30/05/2016.
 */
public class RemoteClientService implements Runnable{
    private Socket serviceSocket;
    private NetDataWriter ndw;
    private NetDataReader ndr;

    private boolean alive;

    public RemoteClientService(Socket serviceSocket){
        this.serviceSocket = serviceSocket;
    }

    public void run() {
        try {
            this.ndr = new NetDataReader(this.serviceSocket.getInputStream());
            this.ndw = new NetDataWriter(this.serviceSocket.getOutputStream());
            this.alive = true;

            while(this.alive){
                byte discriminant = this.ndr.readDiscriminant();
                switch (discriminant){
                    case Protocol.TEST:
                        System.out.println("Trame de test recue");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
