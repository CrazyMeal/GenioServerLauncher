package net;

import org.junit.Test;

/**
 * Created by KVN on 13/06/2016.
 */
public class ServerThreadTest {

    private static ServerThread SERVER = new ServerThread();

    @Test
    public void testServer(){
        SERVER.start();
    }

}
