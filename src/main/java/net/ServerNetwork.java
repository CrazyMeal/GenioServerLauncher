package net;

import core.ActionSwitcher;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by KVN on 13/06/2016.
 */
public class ServerNetwork extends SimpleNetwork {

    private ActionSwitcher as;

    public ServerNetwork(Socket connection) throws IOException {
        super(connection);
        this.as = new ActionSwitcher(this.getNetDataReader(), this.getNetDataWriter());
    }

    synchronized public void read() throws IOException{
        NetDataReader ndr = this.getNetDataReader();

        byte discriminant = ndr.readDiscriminant();

        this.as.processDiscriminant(discriminant);
    }
}
