import LoginView.LoginUI;
import utils.SocketHelper;
import utils.UIStyler;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Fri Sep 15 04:00:58 AWST 2023
 */



/**
 * @author 22431
 */
public class login extends JFrame {
    public login() {
        initComponents();
        UIStyler.setBelowButton(button1);
        UIStyler.setTextField(textField1);
    }

    private void button1MouseClicked() {
        // TODO add your code here
        if(textField1.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"¿Õ°×ip£¡£¡");
            return;
        }
        try {
            SocketHelper socketHelper = new SocketHelper();
            socketHelper.setip(textField1.getText());
            socketHelper.getConnection(socketHelper.ip, socketHelper.port);

            socketHelper.getOs().reset();
            socketHelper.getOs().writeInt(1);
            socketHelper.getOs().flush();


            LoginUI UI = new LoginUI(socketHelper);
            UI.setVisible(true);
            this.dispose();
        }catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Á¬½ÓÊ§°Ü£¡£¡");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        textField1 = new JTextField();
        button1 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();

        //---- button1 ----
        button1.setText("\u8fde\u63a5");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked();
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addContainerGap(143, Short.MAX_VALUE)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                    .addGap(150, 150, 150))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(69, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                    .addGap(38, 38, 38)
                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(66, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JTextField textField1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
