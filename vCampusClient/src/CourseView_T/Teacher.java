/*
 * Created by JFormDesigner on Wed Sep 06 16:09:56 CST 2023
 */

package CourseView_T;

import utils.UIStyler;
import entity.User;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.SocketHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 13352
 */
public  class Teacher extends JPanel{

    User userInfo;


    public SocketHelper socketHelper;

    private CardLayout card=new CardLayout();

    private JPanel card_panel=new JPanel();

    private Course_T one;

    private nameList two;

    private Schedule three;

    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Teacher(User userInfo, SocketHelper socketHelper) {

        //beautify();
        this.socketHelper = socketHelper;
        this.userInfo = userInfo;

        initComponents();
        new UIStyler().createHeader(this);//生成绿条页眉
        new UIStyler().setTopButton(button1);//设置上方按钮格式
        new UIStyler().setTopButton(button2);
        new UIStyler().setTopButton(button3);

        card_panel.setBounds(0,200,1685,830);
        this.add(card_panel);

        one=new Course_T(userInfo,socketHelper);
        two=new nameList(userInfo,socketHelper);
        three=new Schedule(userInfo,socketHelper);


        card_panel.setLayout(card);
        card_panel.add(one,"p0");
        card_panel.add(two,"p1");
        card_panel.add(three,"p2");

        card.show(card_panel,"p0");
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        card.show(card_panel,"p0");
        one.refreshTable();
    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
            passDataToTwo();
            if(two.recordTable!=null&&two.course!=null) {
                card.show(card_panel, "p1");
            }
    }
    // 方法用于传递数据给two界面
    private void passDataToTwo() {
        two.setCourseTData(one.getCourse()); // 通过Course_T类的方法获取数据并传递给nameList类
    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        card.show(card_panel,"p2");
        three.refresh();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();

        //======== this ========
        setBackground(new Color(0xdfe1e5));
        setLayout(null);

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(1685, 830));
            panel1.setBackground(new Color(0xdfe1e5));
            panel1.setOpaque(false);
            panel1.setLayout(null);

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
        panel1.setBounds(0, 200, 1685, 830);

        //======== panel2 ========
        {
            panel2.setPreferredSize(new Dimension(1685, 200));
            panel2.setBackground(new Color(0x24321e));
            panel2.setForeground(new Color(0x24321e));
            panel2.setLayout(null);

            //---- label1 ----
            label1.setText("\u4e1c\u5357\u5927\u5b66\u8bfe\u7a0b\u7ba1\u7406\u7cfb\u7edf");
            label1.setForeground(Color.white);
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 48));
            panel2.add(label1);
            label1.setBounds(550, 50, 500, 50);

            //---- button1 ----
            button1.setText("\u8bfe\u7a0b\u4fe1\u606f");
            button1.setPreferredSize(new Dimension(100, 50));
            button1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            panel2.add(button1);
            button1.setBounds(130, 150, 150, 50);

            //---- button2 ----
            button2.setText("\u67e5\u770b\u540d\u5355");
            button2.setPreferredSize(new Dimension(150, 50));
            button2.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            panel2.add(button2);
            button2.setBounds(330, 150, 200, 50);

            //---- button3 ----
            button3.setText("\u67e5\u770b\u8bfe\u8868");
            button3.setPreferredSize(new Dimension(100, 50));
            button3.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button3MouseClicked(e);
                }
            });
            panel2.add(button3);
            button3.setBounds(580, 150, 150, 50);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/pic/mlogo.png")));
            panel2.add(label2);
            label2.setBounds(new Rectangle(new Point(1330, 35), label2.getPreferredSize()));

            //---- label3 ----
            label3.setIcon(new ImageIcon(getClass().getResource("/pic/background.png")));
            panel2.add(label3);
            label3.setBounds(new Rectangle(new Point(0, 150), label3.getPreferredSize()));

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
        panel2.setBounds(0, 0, 1685, panel2.getPreferredSize().height);

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
    private JPanel panel2;
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label2;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    SocketHelper socketHelper=new SocketHelper();
                    User temp = new User("0800",  "Alice", "john@example.com", "123456", "New York");
                    socketHelper.getConnection(socketHelper.ip,socketHelper.port);
                    socketHelper.getOs().writeInt(1);
                    socketHelper.getOs().flush();
                    Teacher tea = new Teacher(temp,socketHelper);

                    frame.setLayout(new BorderLayout());

                    frame.add(tea, BorderLayout.CENTER);
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
