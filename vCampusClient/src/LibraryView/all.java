/*
 * Created by JFormDesigner on Mon Sep 04 14:17:54 CST 2023
 */

package LibraryView;

import module.BookSystem;
import utils.SocketHelper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
    public all(SocketHelper helper) {

        initComponents();
        this.socketHelper=helper;
        this.model=new BookSystem(this.socketHelper);
/*        initComponents();*/


        card_panel.setBounds(0,200,1685,830);  //位置大小
        this.add(card_panel);

        one=new UserLibrary(socketHelper);
        two=new Studentbookrecord(socketHelper);

        card_panel.setLayout(card);
        card_panel.add(one,"p0");
        card_panel.add(two,"p1");

        card.show(card_panel,"p0");
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
                    all stuAdmin = new all(socketHelper);

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
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        book = new JButton();
        button1 = new JButton();
        panel2 = new JPanel();

        //======== this ========
        setPreferredSize(new Dimension(1685, 1030));
        setMaximumSize(new Dimension(1685, 1030));
        setMinimumSize(new Dimension(1685, 1030));
        setLayout(null);

        //======== panel1 ========
        {
            panel1.setForeground(new Color(0x00ff33));
            panel1.setBackground(new Color(0x99cc00));
            panel1.setPreferredSize(new Dimension(1685, 500));
            panel1.setLayout(null);

            //---- book ----
            book.setText("\u56fe\u4e66\u4fe1\u606f");
            book.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    bookMouseClicked(e);
                }
            });
            panel1.add(book);
            book.setBounds(new Rectangle(new Point(65, 100), book.getPreferredSize()));

            //---- button1 ----
            button1.setText("\u6211\u7684\u501f\u9605");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            panel1.add(button1);
            button1.setBounds(new Rectangle(new Point(195, 100), button1.getPreferredSize()));

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
    private JButton book;
    private JButton button1;
    private JPanel panel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
