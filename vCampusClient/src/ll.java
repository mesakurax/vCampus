import chatView.Client;
import chatView.Server;

import static java.lang.Thread.sleep;

public class


ll {
    public static void main(String[] args) throws InterruptedException {
        Server e=new Server();
        e.start();
        new Client("localhost").start();
    }
}