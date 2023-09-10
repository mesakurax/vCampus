/*
 * Created by JFormDesigner on Sat Sep 09 01:52:15 AWST 2023
 */

package chatView;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 22431
 */
public class tonghua_server extends JFrame {
    private Server ser;
    public tonghua_server(String text) {

        System.out.println(532);
        initComponents();
        textField1.setText(text);
        ser=new Server();
        ser.start();

        System.out.println(51);
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        ser.stopServer();
        this.dispose();
    }

    private void thisWindowClosing(WindowEvent e) {
        // TODO add your code here
        ser.stopServer();
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        button1 = new JButton();
        label1 = new JLabel();
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

        //---- button1 ----
        button1.setText("\u6302\u65ad");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(140, 160, 135, 55);

        //---- label1 ----
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 15f));
        label1.setText("\u6b63\u5728\u901a\u8bdd\u4e2d....");
        contentPane.add(label1);
        label1.setBounds(125, 25, 340, 35);

        //---- textField1 ----
        textField1.setEditable(false);
        contentPane.add(textField1);
        textField1.setBounds(90, 80, 250, 55);

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
    private JButton button1;
    private JLabel label1;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
