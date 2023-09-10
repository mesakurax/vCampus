/*
 * Created by JFormDesigner on Sat Sep 09 02:13:00 AWST 2023
 */

package chatView;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 22431
 */
public class tonghua_clinet extends JFrame {
    private Client cli;
    public tonghua_clinet(String ip,String text) {
        initComponents();
        textField1.setText(text);
        cli=new Client(ip);
        cli.start();
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        cli.stopClient();
        this.dispose();
    }

    private void thisWindowClosing(WindowEvent e) {
        // TODO add your code here
        cli.stopClient();
        this.dispose();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        textField1 = new JTextField();
        button1 = new JButton();

        //======== this ========
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 15f));
        label1.setText("\u6b63\u5728\u901a\u8bdd\u4e2d....");
        contentPane.add(label1);
        label1.setBounds(125, 35, 340, 35);

        //---- textField1 ----
        textField1.setEditable(false);
        contentPane.add(textField1);
        textField1.setBounds(75, 95, 250, 55);

        //---- button1 ----
        button1.setText("\u6302\u65ad");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(135, 175, 135, 55);

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
    private JLabel label1;
    private JTextField textField1;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
