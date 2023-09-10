package mainUiView;

import entity.User;
import utils.SocketHelper;

public class LoginToMain {
    public LoginToMain(SocketHelper socketHelper, User userInfo) throws InterruptedException {
        String type = userInfo.getOccupation();
        if(type.equals("学生")){
            new mainUIStu(socketHelper,userInfo).setVisible(true);
        }
        else if(type.equals("教师")){
            new mainUITea(socketHelper,userInfo).setVisible(true);
        }
        else{
            new mainUIAdmin(socketHelper,userInfo).setVisible(true);
        }
    }
}
