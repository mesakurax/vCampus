/*
 * Created by JFormDesigner on Fri Sep 08 15:58:22 CST 2023
 */

package LibraryView;

import com.alee.laf.WebLookAndFeel;
import module.BookSystem;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.SocketHelper;
import utils.UIStyler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 86153
 */
public class Admin_View extends JPanel {
    private SocketHelper socketHelper;
    private BookSystem model;

    private CardLayout card=new CardLayout();

    private JPanel card_panel=new JPanel();  //子布局，代表后面切换的布局都是它

    private AdminLibrary one;

   //private Studentbookrecord two;
    private admin_illiegal two ;
    private admin_paper three;
    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            /*btnInstance.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));*/
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Admin_View(SocketHelper helper) {

        initComponents();
        button1.setBorderPainted(false);
        button1.setOpaque(false);
        button1.setFocusPainted(false);
        button2.setBorderPainted(false);
        button2.setOpaque(false);
        button2.setFocusPainted(false);
        button3.setBorderPainted(false);
        button3.setOpaque(false);
        button3.setFocusPainted(false);
        beautify();

        UIStyler.setTopButton(button2);
        UIStyler.setTopButton(button1);
        UIStyler.setTopButton(button3);


        this.socketHelper=helper;
        this.model=new BookSystem(this.socketHelper);
        card_panel.setBounds(0,200,1685,830);  //位置大小
        card_panel.setOpaque(false);
        this.add(card_panel);
        one=new AdminLibrary(socketHelper);
        two=new admin_illiegal(socketHelper);
        three=new admin_paper(socketHelper);

        card_panel.setLayout(card);
        card_panel.add(one,"p0");
        card_panel.add(two,"p1");
        card_panel.add(three,"p2");
        //card_panel.add(three,"p2");

        card.show(card_panel,"p0");

    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        card.show(card_panel,"p0");
        one.refreshtable();
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    SocketHelper socketHelper=new SocketHelper();
                    socketHelper.getConnection(socketHelper.ip,socketHelper.port);
                    socketHelper.getOs().writeInt(1);
                    socketHelper.getOs().flush();
                    Admin_View stuAdmin = new Admin_View(socketHelper);

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

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        card.show(card_panel,"p1");
        one.refreshtable();
    }

    private void button1MouseExited(MouseEvent e) {
        // TODO add your code here
        button1.setOpaque(false);
    }

    private void button1MouseEntered(MouseEvent e) {
        // TODO add your code here
        button1.setOpaque(true);
        button1.setBackground(Color.white);
    }

    private void button2MouseEntered(MouseEvent e) {
        // TODO add your code here
        button2.setOpaque(true);
        button2.setBackground(Color.white);
    }

    private void button2MouseExited(MouseEvent e) {
        // TODO add your code here
        button2.setOpaque(false);
    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        card.show(card_panel,"p2");
        three.refreshtable();
    }

    private void button3MouseEntered(MouseEvent e) {
        // TODO add your code here
        button3.setOpaque(true);
        button3.setBackground(Color.white);
    }

    private void button3MouseExited(MouseEvent e) {
        // TODO add your code here
        button3.setOpaque(false);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        panel2 = new JPanel();

        //======== this ========
        setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x24321e));
            panel1.setLayout(null);

            //---- button1 ----
            button1.setText("\u56fe\u4e66\u7ba1\u7406");
            button1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    button1MouseEntered(e);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    button1MouseExited(e);
                }
            });
            panel1.add(button1);
            button1.setBounds(120, 150, 155, 50);

            //---- button2 ----
            button2.setText("\u8fdd\u7ae0\u7ba1\u7406");
            button2.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    button2MouseEntered(e);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    button2MouseExited(e);
                }
            });
            panel1.add(button2);
            button2.setBounds(275, 150, 155, 50);

            //---- button3 ----
            button3.setText("\u6587\u732e\u7ba1\u7406");
            button3.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button3MouseClicked(e);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    button3MouseEntered(e);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    button3MouseExited(e);
                }
            });
            panel1.add(button3);
            button3.setBounds(435, 150, 155, 50);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/background.png")));
            panel1.add(label1);
            label1.setBounds(new Rectangle(new Point(0, 150), label1.getPreferredSize()));

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/logonew.png")));
            panel1.add(label2);
            label2.setBounds(115, 45, 515, 70);

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
        panel1.setBounds(0, 0, 1685, 200);

        //======== panel2 ========
        {
            panel2.setOpaque(false);
            panel2.setLayout(null);

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
        panel2.setBounds(0, 200, 1685, 830);

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
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label1;
    private JLabel label2;
    private JPanel panel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
