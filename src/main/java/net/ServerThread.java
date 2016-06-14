package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by KVN on 30/05/2016.
 */
public class ServerThread extends Thread{

    public static final int TIMEOUT_ACCEPT = 10000;
    public static final int TIMEOUT_CLIENT = 10000;

    @Override
    public void run() {
        try(ServerSocket serverSocket = new ServerSocket(Protocol.port)){
            serverSocket.setSoTimeout(TIMEOUT_ACCEPT);

            while(!this.isInterrupted()){
                try {
                    final Socket clientSocket = serverSocket.accept();
                    clientSocket.setSoTimeout(TIMEOUT_CLIENT);
                    new Thread() {
                        @Override
                        public void run(){
                            System.out.println("Starting new thread for " + clientSocket.getInetAddress().getHostName());
                            try (ServerNetwork sn = new ServerNetwork(clientSocket)){
                                sn.read();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    clientSocket.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }.start();
                } catch (SocketTimeoutException e) {
                    //e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
