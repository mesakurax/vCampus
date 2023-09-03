/*
 * Created by JFormDesigner on Fri Sep 01 15:40:57 AWST 2023
 */

package bankSystemView;

import java.awt.event.*;
import module.bankSystem;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.SocketHelper;

import java.awt.*;
import javax.swing.*;

/**
 * @author 22431
 */
public class adminBankView extends JPanel {
    private SocketHelper socketHelper;
    private bankSystem model;

    private CardLayout card=new CardLayout();

    private JPanel card_panel=new JPanel();  //子布局，代表后面切换的布局都是它

    private admin_1 one;

    private admin_2 two;


    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public adminBankView(SocketHelper socketHelper) {
        beautify();
        this.socketHelper=socketHelper;
        this.model=new bankSystem(this.socketHelper);
        initComponents();


        card_panel.setBounds(0,215,1685,815);  //位置大小
        this.add(card_panel);

        one=new admin_1(socketHelper);
        two=new admin_2(socketHelper);

        card_panel.setLayout(card);
        card_panel.add(one,"p0");
        card_panel.add(two,"p1");

        card.show(card_panel,"p0");


    }

    private void button1MouseClicked() {
        // TODO add your code here
        card.show(card_panel,"p0");
        one.refreshTable();
    }

    private void button2MouseClicked() {
        // TODO add your code here
        card.show(card_panel,"p1");
        two.refreshTable();
    }

    private void button2() {
        // TODO add your code here
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel2 = new JPanel();
        button2 = new JButton();
        button1 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        panel3 = new JPanel();

        //======== this ========
        setPreferredSize(new Dimension(1685, 1030));
        setOpaque(false);
        setLayout(null);

        //======== panel2 ========
        {
            panel2.setBackground(new Color(0xccf0e6));
            panel2.setLayout(null);

            //---- button2 ----
            button2.setText("\u5df2\u5904\u7406");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked();
                }
            });
            button2.addActionListener(e -> button2());
            panel2.add(button2);
            button2.setBounds(220, 145, 115, 58);

            //---- button1 ----
            button1.setText("\u5f85\u5904\u7406");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked();
                }
            });
            panel2.add(button1);
            button1.setBounds(70, 145, 115, 58);

            //---- label1 ----
            label1.setText("\u4e1c\u5357\u5927\u5b66\u7f51\u4e0a\u94f6\u884c");
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 60));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/\u94f6\u884c (2).png")));
            label1.setForeground(Color.white);
            panel2.add(label1);
            label1.setBounds(500, 40, 730, 125);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/seulogo.png")));
            panel2.add(label2);
            label2.setBounds(1310, 5, 355, 190);

            //---- label5 ----
            label5.setText("text");
            label5.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/img.png")));
            label5.setForeground(new Color(0x6666ff));
            panel2.add(label5);
            label5.setBounds(0, 0, 855, 215);

            //---- label6 ----
            label6.setText("text");
            label6.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/img.png")));
            panel2.add(label6);
            label6.setBounds(660, -5, 1175, 220);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel2.getComponentCount(); i++) {
                    Rectangle bounds = panel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
        }
        add(panel2);
        panel2.setBounds(0, 0, 1685, 215);

        //======== panel3 ========
        {
            panel3.setOpaque(false);
            panel3.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel3.getComponentCount(); i++) {
                    Rectangle bounds = panel3.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel3.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel3.setMinimumSize(preferredSize);
                panel3.setPreferredSize(preferredSize);
            }
        }
        add(panel3);
        panel3.setBounds(0, 215, 1685, 815);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel2;
    private JButton button2;
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label5;
    private JLabel label6;
    private JPanel panel3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    SocketHelper socketHelper=new SocketHelper();
                    socketHelper.getConnection(socketHelper.ip,socketHelper.port);
                    socketHelper.getOs().writeInt(1);
                    socketHelper.getOs().flush();
                    adminBankView stuAdmin = new adminBankView(socketHelper);

                    frame.setLayout(new BorderLayout()); // 设置布局管理器为BorderLayout

                    frame.add(stuAdmin, BorderLayout.CENTER); // 将StuAdmin添加到CENTER位置
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
