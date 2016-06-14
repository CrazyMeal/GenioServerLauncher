import net.ServerThread;

/**
 * Created by KVN on 14/06/2016.
 */
public class Main {
    private static ServerThread SERVER = new ServerThread();

    public static void main(String... args){
        SERVER.start();
    }
}
