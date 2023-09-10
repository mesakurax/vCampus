/*
 * Created by JFormDesigner on Sat Sep 09 02:13:00 AWST 2023
 */

package chatView;

import utils.SocketHelper;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

/**
 * @author 22431
 */
public class tonghua_clinet extends JFrame {
    private SocketHelper socketHelper;
    private Client cli;
    public tonghua_clinet(String ip, String text, SocketHelper socketHelper) {
        initComponents();
        this.socketHelper=socketHelper;
        textField1.setText(text);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
        cli=new Client(ip);
        cli.start();

    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        try {
            socketHelper.getOs().writeInt(10);
            socketHelper.getOs().writeObject(textField1.getText());
            socketHelper.getOs().flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        cli.stopClient();
            System.out.println(65);
            this.dispose();

    }

    private void thisWindowClosing(WindowEvent e) {
        // TODO add your code here
        cli.stopClient();
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label1 = new JLabel();
        button1 = new JButton();
        textField1 = new JTextField();

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

            //---- label1 ----
            label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 15f));
            label1.setText("\u6b63\u5728\u901a\u8bdd\u4e2d....");
            panel1.add(label1);
            label1.setBounds(130, 40, 340, 35);

            //---- button1 ----
            button1.setText("\u6302\u65ad");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            panel1.add(button1);
            button1.setBounds(150, 185, 135, 55);

            //---- textField1 ----
            textField1.setEditable(false);
            textField1.setFont(new Font("\u5e7c\u5706", Font.BOLD, 20));
            textField1.setHorizontalAlignment(SwingConstants.CENTER);
            panel1.add(textField1);
            textField1.setBounds(85, 100, 250, 55);

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
        panel1.setBounds(0, 0, 420, 270);

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
    private JLabel label1;
    private JButton button1;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
