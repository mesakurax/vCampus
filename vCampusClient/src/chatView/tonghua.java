/*
 * Created by JFormDesigner on Thu Sep 07 13:08:23 AWST 2023
 */

package chatView;

import java.awt.event.*;

import utils.AudioUtils;
import utils.SocketHelper;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.*;

/**
 * @author 22431
 */
public class tonghua extends JFrame {
    private SocketHelper socketHelper;
    public tonghua(SocketHelper socketHelper) {
        initComponents();
        this.socketHelper=socketHelper;
        try {
            textField1.setText((String)this.socketHelper.getIs().readObject());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        try {
            socketHelper.getOs().writeInt(9);
            socketHelper.getOs().writeObject(textField1.getText());
            socketHelper.getOs().writeInt(92);
            InetAddress localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();
            socketHelper.getOs().writeObject(ipAddress);
            socketHelper.getOs().flush();

            new Thread(new tong()).start();


            this.dispose();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        try {
            socketHelper.getOs().writeInt(9);
            socketHelper.getOs().writeObject(textField1.getText());
            socketHelper.getOs().writeInt(91);
            socketHelper.getOs().flush();
            this.dispose();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    private void thisWindowClosing(WindowEvent e) {
        // TODO add your code here
        try {
            socketHelper.getOs().writeInt(9);
            socketHelper.getOs().writeObject(textField1.getText());
            socketHelper.getOs().writeInt(91);
            socketHelper.getOs().flush();
            this.dispose();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    class tong implements  Runnable{
        @Override
        public void run() {

            try {
                ServerSocket serverSocket = new ServerSocket(9000, 20);
                // 等待连接
                System.out.println("服务端:等待连接");
                Socket socket = serverSocket.accept();
                OutputStream out = socket.getOutputStream();
                // out.flush();
                System.out.println("服务端：连接成功");
                // 保持通讯
                InputStream in = socket.getInputStream();

                TargetDataLine targetDataLine = AudioUtils.getTargetDataLine();

                SourceDataLine sourceDataLine = AudioUtils.getSourceDataLine();

                byte[] bos=new byte[2024];
                byte[] bis=new byte[2024];

                while (true) {
                    System.out.println("server:");

                    /**
                     * 这里一定要先发再收  不然socket的读取流会阻塞
                     */
                    //获取音频流
                    int writeLen = targetDataLine.read(bos,0,bos.length);
                    //发
                    if (bos != null) {
                        //向对方发送拾音器获取到的音频
                        System.out.println("rerver 发");
                        out.write(bos,0,writeLen);
                    }

                    //收
                    int readLen = in.read(bis);
                    if (bis != null) {
                        //播放对方发送来的音频
                        System.out.println("rerver 收");
                        sourceDataLine.write(bis, 0, readLen);
                    }
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        textField1 = new JTextField();
        label1 = new JLabel();
        panel1 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- textField1 ----
        textField1.setEditable(false);
        textField1.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 24));
        contentPane.add(textField1);
        textField1.setBounds(75, 85, 240, 45);

        //---- label1 ----
        label1.setText("\u6765\u7535\u63d0\u793a");
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 15f));
        contentPane.add(label1);
        label1.setBounds(135, 30, 140, label1.getPreferredSize().height);

        //======== panel1 ========
        {
            panel1.setLayout(null);

            //---- button1 ----
            button1.setText("\u63a5\u542c");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            panel1.add(button1);
            button1.setBounds(105, 190, button1.getPreferredSize().width, 35);

            //---- button2 ----
            button2.setText("\u62d2\u7edd");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            panel1.add(button2);
            button2.setBounds(225, 190, button2.getPreferredSize().width, 35);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 395, 280);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTextField textField1;
    private JLabel label1;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
