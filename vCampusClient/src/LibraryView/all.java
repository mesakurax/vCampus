/*
 * Created by JFormDesigner on Mon Sep 04 14:17:54 CST 2023
 */

package LibraryView;

/*import com.intellij.ide.ui.laf.*;*/

import utils.UIStyler;
import com.alee.laf.WebLookAndFeel;
import module.BookSystem;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.SocketHelper;
import entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 86153
 */


public class all extends JPanel {
    private SocketHelper socketHelper;
    private BookSystem model;

    private CardLayout card=new CardLayout();

    private JPanel card_panel=new JPanel();  //子布局，代表后面切换的布局都是它

    private UserLibrary one;

    private Studentbookrecord two;
    private myillega three;
    private UserPaper four;
    private mainframe five;

    private User uu;
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
    public all(SocketHelper helper,User uu) {

        initComponents();
        beautify();
        this.uu=uu;
        book.setBorderPainted(false);
        book.setOpaque(false);
        book.setFocusPainted(false);
        button1.setBorderPainted(false);
        button1.setOpaque(false);
        button1.setFocusPainted(false);
        button2.setBorderPainted(false);
        button2.setOpaque(false);
        button2.setFocusPainted(false);
        button3.setBorderPainted(false);
        button3.setOpaque(false);
        button3.setFocusPainted(false);
        button4.setBorderPainted(false);
        button4.setOpaque(false);
        button4.setFocusPainted(false);
        this.socketHelper=helper;
        this.model=new BookSystem(this.socketHelper);
        UIStyler.setTopButton(button2);
        UIStyler.setTopButton(button1);
        UIStyler.setTopButton(button3);
        UIStyler.setTopButton(button4);
        UIStyler.setTopButton(book);

        card_panel.setBounds(0,200,1685,830);  //位置大小
        this.add(card_panel);
        card_panel.setOpaque(false);
       // beautify();
        one=new UserLibrary(socketHelper,uu);
        two=new Studentbookrecord(socketHelper,uu);
        three=new myillega(socketHelper,uu);
        four=new UserPaper(socketHelper,uu);
        five=new mainframe();

        card_panel.setLayout(card);
        card_panel.add(one,"p0");
        card_panel.add(two,"p1");
        card_panel.add(three,"p2");
        card_panel.add(four,"p3");
        card_panel.add(five,"p4");


        card.show(card_panel,"p4");

    }
    private void bookMouseClicked(MouseEvent e) {
        // TODO add your code here
        card.show(card_panel,"p0");
        one.refreshTable();
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
                    User uu=new User();
                    uu.setId("0902");
                    all stuAdmin = new all(socketHelper,uu);

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

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        card.show(card_panel,"p1");
        two.refreshtable();
    }


    private void bookMouseEntered(MouseEvent e) {
        // TODO add your code here
        book.setOpaque(true);
        book.setBackground(Color.white);
    }

    private void bookMouseExited(MouseEvent e) {
        // TODO add your code here
        book.setOpaque(false);
    }

    private void button1MouseEntered(MouseEvent e) {
        // TODO add your code here
        button1.setOpaque(true);
        button1.setBackground(Color.white);
    }

    private void button1MouseExited(MouseEvent e) {
        // TODO add your code here
        button1.setOpaque(false);
    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        card.show(card_panel,"p2");
        three.refreshtable();
    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        card.show(card_panel,"p3");
        four.refreshtable();
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

    private void button3MouseEntered(MouseEvent e) {
        // TODO add your code here
        button3.setOpaque(true);
        button3.setBackground(Color.white);
    }

    private void button3MouseExited(MouseEvent e) {
        // TODO add your code here
        button3.setOpaque(false);
    }

    private void button4MouseClicked(MouseEvent e) {
        // TODO add your code here
        card.show(card_panel,"p4");
    }

    private void button4MouseEntered(MouseEvent e) {
        // TODO add your code here
        button4.setOpaque(true);
        button4.setBackground(Color.white);

    }
    private void button4MouseExited(MouseEvent e) {
        // TODO add your code here
        button4.setOpaque(false);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        book = new JButton();
        button1 = new JButton();
        label1 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        label2 = new JLabel();
        panel2 = new JPanel();

        //======== this ========
        setPreferredSize(new Dimension(1685, 1030));
        setMaximumSize(new Dimension(1685, 1030));
        setMinimumSize(new Dimension(1685, 1030));
        setLayout(null);

        //======== panel1 ========
        {
            panel1.setForeground(Color.white);
            panel1.setBackground(new Color(0x24321e));
            panel1.setPreferredSize(new Dimension(1685, 500));
            panel1.setLayout(null);

            //---- book ----
            book.setText("\u56fe\u4e66\u4fe1\u606f");
            book.setBorder(null);
            book.setBackground(new Color(0xcccccc));
            book.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
            book.setBorderPainted(false);
            book.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    bookMouseClicked(e);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    bookMouseEntered(e);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    bookMouseExited(e);
                }
            });
            panel1.add(book);
            book.setBounds(190, 150, 155, 50);

            //---- button1 ----
            button1.setText("\u6211\u7684\u501f\u9605");
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
            button1.setBounds(345, 150, 155, 50);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/logonew.png")));
            panel1.add(label1);
            label1.setBounds(185, 0, 480, 170);

            //---- button2 ----
            button2.setText("\u6211\u7684\u8fdd\u7ae0");
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
            button2.setBounds(500, 150, 155, 50);

            //---- button3 ----
            button3.setText("\u6587\u732e\u4fe1\u606f");
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
            button3.setBounds(655, 150, 155, 50);

            //---- button4 ----
            button4.setText("\u9996\u9875");
            button4.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
            button4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button4MouseClicked(e);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    button4MouseEntered(e);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    button4MouseExited(e);
                }
            });
            panel1.add(button4);
            button4.setBounds(35, 150, 155, 50);

            //---- label2 ----
            label2.setText("text");
            label2.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/background.png")));
            panel1.add(label2);
            label2.setBounds(new Rectangle(new Point(0, 150), label2.getPreferredSize()));

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
            panel2.setBackground(new Color(0xcccccc));
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
    private JButton book;
    private JButton button1;
    private JLabel label1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel label2;
    private JPanel panel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

}
