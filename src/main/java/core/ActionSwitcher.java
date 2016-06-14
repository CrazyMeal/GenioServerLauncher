package core;

import net.NetDataReader;
import net.NetDataWriter;
import net.Protocol;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by KVN on 04/06/2016.
 */
public class ActionSwitcher {
    private NetDataWriter ndw;
    private NetDataReader ndr;

    public ActionSwitcher(NetDataReader ndr, NetDataWriter ndw){
        this.ndr = ndr;
        this.ndw = ndw;
    }

    public void processDiscriminant(byte discriminant) throws IOException {
        switch (discriminant){
            case Protocol.TEST:
                System.out.println("Trame de test recue");

                break;
            case Protocol.CONNECT_REQUEST:
                System.out.println("Connecion request received");
                this.ndw.writeDiscriminant(Protocol.OK);
                this.ndw.send();
                break;
            case Protocol.GET_INTERFACE_LIST:
                this.sendInterfaceList();
                break;
        }
    }

    public void sendInterfaceList(){
        String itf1 = "Interface 1";
        String itf2 = "Interface 2";
        ArrayList<String> l = new ArrayList<String>();
        l.add(itf1);
        l.add(itf2);

        int interfacesToSend = l.size();

        this.ndw.writeDiscriminant(Protocol.SERVER_SENDING_ITF_LIST);
        this.ndw.writeInt(interfacesToSend);
        for(String s : l){
            this.ndw.writeString(s);
        }

        try {
            this.ndw.send();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
