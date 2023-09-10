/*
 * Created by JFormDesigner on Sun Sep 03 03:01:21 CST 2023
 */

package CourseView_S;

import utils.UIStyler;
import entity.Course;
import entity.User;
import module.CourseSystem;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.SocketHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * @author 13352
 */
public class Student extends JPanel {


    CourseSystem model;
    User userInfo;

    Course course;

    public SocketHelper socketHelper;

    private CardLayout card=new CardLayout();

    private JPanel card_panel=new JPanel();  //??????????????л???????????

    private Course_S one;

    private Record_S two;

    private Schedule_S three;

    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Student(User userInfo, SocketHelper socketHelper) {
        this.socketHelper = socketHelper;
        this.userInfo = userInfo;

        //beautify();
        initComponents();
        new UIStyler().createHeader(this);//生成绿条页眉
        new UIStyler().setTopButton(button1);//设置上方按钮格式
        new UIStyler().setTopButton(button2);
        new UIStyler().setTopButton(button3);

        card_panel.setBounds(0,200,1685,830);  //λ???С
        this.add(card_panel);

        one=new Course_S(userInfo,socketHelper);
        two=new Record_S(userInfo,socketHelper);
        three=new Schedule_S(userInfo,socketHelper);

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
        card.show(card_panel,"p1");
        two.refreshTable();
    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        card.show(card_panel,"p2");
        three.refresh();
    }





    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        panel1 = new JPanel();
        button3 = new JButton();
        panel2 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(1685, 1030));
        setBackground(new Color(0xdfe1e5));
        setForeground(new Color(0xccffcc));
        setLayout(null);

        //---- label1 ----
        label1.setText("\u4e1c\u5357\u5927\u5b66\u8bfe\u7a0b\u7ba1\u7406\u7cfb\u7edf");
        label1.setForeground(Color.white);
        label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 48));
        label1.setPreferredSize(new Dimension(500, 50));
        add(label1);
        label1.setBounds(550, 50, 500, 50);

        //---- button1 ----
        button1.setText("\u9009\u8bfe");
        button1.setPreferredSize(new Dimension(100, 50));
        button1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
        button1.setForeground(Color.white);
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        add(button1);
        button1.setBounds(130, 150, 150, 50);

        //---- button2 ----
        button2.setText("\u67e5\u770b\u9009\u8bfe");
        button2.setPreferredSize(new Dimension(150, 50));
        button2.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
        button2.setForeground(Color.white);
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });
        add(button2);
        button2.setBounds(330, 150, 200, 50);

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(1685, 860));
            panel1.setBackground(new Color(0x9999ff));
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

        //---- button3 ----
        button3.setText("\u67e5\u770b\u8bfe\u8868");
        button3.setPreferredSize(new Dimension(100, 50));
        button3.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
        button3.setForeground(Color.white);
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button3MouseClicked(e);
            }
        });
        add(button3);
        button3.setBounds(580, 150, 150, 50);

        //======== panel2 ========
        {
            panel2.setBackground(new Color(0x24321e));
            panel2.setForeground(new Color(0x24321e));
            panel2.setLayout(null);

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
        panel2.setBounds(0, 0, 1685, 200);

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
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JPanel panel1;
    private JButton button3;
    private JPanel panel2;
    private JLabel label2;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    SocketHelper socketHelper=new SocketHelper();
                    User temp = new User("0900",  "Alice", "john@example.com", "123456", "New York");
                    socketHelper.getConnection(socketHelper.ip,socketHelper.port);
                    socketHelper.getOs().writeInt(1);
                    socketHelper.getOs().flush();
                    Student stu = new Student(temp,socketHelper);

                    frame.setLayout(new BorderLayout());

                    frame.add(stu, BorderLayout.CENTER);
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
