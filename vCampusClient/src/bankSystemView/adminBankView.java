/*
 * Created by JFormDesigner on Fri Sep 01 15:40:57 AWST 2023
 */

package bankSystemView;

import java.awt.event.*;
import module.bankSystem;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.SocketHelper;
import utils.UIStyler;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;

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
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
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

        UIStyler.createHeader(this);
        UIStyler.setTopButton(button2);
        UIStyler.setTopButton(button1);


        card_panel.setBounds(0,200,1685,830);  //位置大小
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


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        panel3 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(1685, 1030));
        setOpaque(false);
        setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x24321e));
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("\u4e1c\u5357\u5927\u5b66\u7f51\u4e0a\u94f6\u884c");
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 60));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setIcon(null);
            label1.setForeground(Color.white);
            panel1.add(label1);
            label1.setBounds(1035, 15, 600, 135);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/seulogo.png")));
            panel1.add(label2);
            label2.setBounds(50, 0, 490, 145);

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
        add(panel1);
        panel1.setBounds(0, 0, 1685, 150);

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
        panel3.setBounds(0, 200, 1685, 830);

        //---- button1 ----
        button1.setText("\u5f85\u5904\u7406");
        button1.setFont(new Font("\u5e7c\u5706", Font.BOLD, 20));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked();
            }
        });
        add(button1);
        button1.setBounds(95, 150, 115, 50);

        //---- button2 ----
        button2.setText("\u5df2\u5904\u7406");
        button2.setFont(new Font("\u5e7c\u5706", Font.BOLD, 20));
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked();
            }
        });
        add(button2);
        button2.setBounds(250, 150, 115, 50);

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
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JPanel panel3;
    private JButton button1;
    private JButton button2;
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
