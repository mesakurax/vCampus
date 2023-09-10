package chatView;

import entity.User;
import utils.SocketHelper;
import utils.Timehelp;

import javax.swing.*;
import java.awt.*;

public class test {

    public test() {
    }

    public static void main(String[] args) {

        User a1=new User();
        a1.setName("小明");
        a1.setId("090212");
        User b1=new User();
        b1.setName("小红");
        b1.setId("090211");
        client_pp a=new client_pp();
        a.run(a1);
        client_pp b=new client_pp();
        b.run(b1);
       // client c=new client();
       // c.run();
    }
}




class client_pp {

    public void run(User info) {
        try {
            JFrame frame = new JFrame();

            chatView stuAdmin = new chatView(info);

            frame.setLayout(new BorderLayout()); // 设置布局管理器为BorderLayout

            frame.add(stuAdmin, BorderLayout.CENTER); // 将StuAdmin添加到CENTER位置
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

