package net;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by KVN on 13/06/2016.
 */
public class SimpleNetwork implements Closeable{
    private static final int CONNECTION_TIMEOUT = 10000;

    private Socket connection;
    private NetDataReader ndr;
    private NetDataWriter ndw;

    public SimpleNetwork(Socket connection) throws IOException {
        this.connection = connection;
        this.connection.setSoTimeout(SimpleNetwork.CONNECTION_TIMEOUT);
        this.ndr = new NetDataReader(this.connection.getInputStream());
        this.ndw = new NetDataWriter(this.connection.getOutputStream());
    }

    protected Socket getSocket(){
        return this.connection;
    }

    protected NetDataReader getNetDataReader(){
        return this.ndr;
    }

    protected NetDataWriter getNetDataWriter(){
        return this.ndw;
    }

    @Override
    public void close() throws IOException {
        try {
            this.connection.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
