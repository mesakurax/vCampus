/*
 * Created by JFormDesigner on Thu Sep 07 13:08:23 AWST 2023
 */

package chatView;

import java.awt.event.*;

import utils.IPV4Helper;
import utils.SocketHelper;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import javax.swing.*;


/**
 * @author 22431
 */
public class tonghua_accept extends JFrame {
    private SocketHelper socketHelper;


    public tonghua_accept(SocketHelper socketHelper) {
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

            String ip= IPV4Helper.ipv4();
            socketHelper.getOs().writeObject(ip);
            socketHelper.getOs().flush();

            new tonghua_server(textField1.getText(),socketHelper);
            this.dispose();

        } catch (Exception ex) {
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        textField1 = new JTextField();
        label1 = new JLabel();

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

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
            button1.setBounds(90, 195, 85, 50);

            //---- button2 ----
            button2.setText("\u62d2\u7edd");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            panel1.add(button2);
            button2.setBounds(215, 195, 95, 50);

            //---- textField1 ----
            textField1.setHorizontalAlignment(SwingConstants.CENTER);
            textField1.setFont(new Font("\u5e7c\u5706", Font.BOLD, 20));
            textField1.setEditable(false);
            panel1.add(textField1);
            textField1.setBounds(75, 100, 245, 60);

            //---- label1 ----
            label1.setText("\u6765\u7535\u63d0\u793a");
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 15f));
            panel1.add(label1);
            label1.setBounds(135, 45, 140, label1.getPreferredSize().height);

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
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JTextField textField1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
