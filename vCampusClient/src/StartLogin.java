import LoginView.LoginUI;
import utils.SocketHelper;

import java.io.IOException;

public class StartLogin {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        new login().setVisible(true);
    }
}
