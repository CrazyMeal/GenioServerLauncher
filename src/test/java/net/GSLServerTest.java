package net;

import org.junit.Test;

/**
 * Created by KVN on 30/05/2016.
 */
public class GSLServerTest {

    @Test
    public void testServer(){
        GSLServer server = new GSLServer(Protocol.port);
        server.init();
        server.run();
    }
}
