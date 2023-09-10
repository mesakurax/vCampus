package mainUiView;

import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import LoginView.UserAdmin;
import StudentRollview.PersonalInfo;
import StudentRollview.StuAdmin;
import bankSystemView.adminBankView;
import utils.UIStyler;
import entity.User;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.SocketHelper;
import utils.dimensionUtil;
import LibraryView.*;
import CourseView_M.*;
import shopSystemView.*;
import chatView.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mainUIAdmin extends JFrame {
    private SocketHelper socketHelper;
    public User userInfo;
    CardLayout card = new CardLayout();

    chatView chatview;

    public void beautify() {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void setBtn(JButton button1){
        button1.setSize(235,80);
        button1.setForeground(Color.BLACK);
        button1.setFont(new Font("宋体", Font.BOLD, 26));
        button1.setMargin(new Insets(0, 0, 0, 0));//将边框外的上下左右空间设置为0
        button1.setContentAreaFilled(false);//除去默认的背景填充
        button1.setBorderPainted(false);//不打印边框
        button1.setBorder(null);
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button1.setOpaque(true);
                button1.setBackground(Color.white);  //鼠标移上去后变白
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button1.setOpaque(false);
            }
        });
    }
    public mainUIAdmin(SocketHelper socketHelper, User userInfo) throws InterruptedException {
        this.userInfo = userInfo;
        this.socketHelper = socketHelper;
        beautify();
        initComponents();
        setBtn(button1);
        setBtn(button2);
        setBtn(button3);
        setBtn(button4);
        setBtn(button5);
        setBtn(button6);
        setBtn(button7);
        setBtn(logout);
        button1.setLocation(0,150);
        button2.setLocation(0,230);
        button3.setLocation(0,310);
        button4.setLocation(0,390);
        button5.setLocation(0,470);
        button6.setLocation(0,550);
        button7.setLocation(0,630);
        logout.setLocation(0,930);

        label1.setBounds(0,600,500,500); //东大水印

        pagePanel.setLayout(card);
        pagePanel.setBounds(236, 0, 1685, 1030);
        pagePanel.add(new UserAdmin(socketHelper),"p1");
        pagePanel.add(new StuAdmin(socketHelper),"p2");
        pagePanel.add(new Admin_View(socketHelper),"p3");
        pagePanel.add(new Course_M(userInfo,socketHelper),"p4");
        pagePanel.add(new adminBankView(socketHelper),"p6");
        pagePanel.add(new adminShopView(socketHelper),"p5");
        this.chatview=new chatView(userInfo);
        pagePanel.add(this.chatview,"p7");


        card.show(pagePanel,"p1");
        //根据屏幕大小设置主界面大小
        Rectangle bounds = dimensionUtil.getBounds();
        setBounds(bounds);
        //设置窗体完全充满整个屏幕的可见大小
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        //获取屏幕高和宽
        int height = bounds.height;
        int width = bounds.width;
        System.out.println(width + "x" + height);
        //设置背景颜色
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);
        //加入头像图片
        ImageIcon header = new ImageIcon("./src/mainUIView/mainPagePic/head.png");
        header.setImage(header.getImage().getScaledInstance(95, 95, Image.SCALE_DEFAULT));
        JLabel headerIcon = new JLabel(header);
        headerIcon.setBounds(20, 20, 200, 100);
        getContentPane().add(headerIcon);
        headerIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(getParent());

                if (result == JFileChooser.APPROVE_OPTION) {
                    // 获取选择的文件
                    java.io.File file = fileChooser.getSelectedFile();

                    // 创建一个ImageIcon对象，并将其设置为JLabel的图标
                    ImageIcon originalIcon = new ImageIcon(file.getAbsolutePath());
                    Image originalImage = originalIcon.getImage();

                    // 缩放图像到指定尺寸
                    Image scaledImage = originalImage.getScaledInstance(95, 95, Image.SCALE_SMOOTH);

                    // 创建一个新的ImageIcon对象，并将其设置为JLabel的图标
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    headerIcon.setIcon(scaledIcon);
                }
            }
        });
        headerIcon.addMouseListener(new MouseAdapter() {
            private JFrame infoWindow; // 个人信息窗口

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (infoWindow == null) {
                    // 创建个人信息窗口
                    JFrame jFrame = new JFrame();
                    infoWindow = jFrame;
                    infoWindow.add(new PersonalInfo(userInfo, socketHelper));
                    infoWindow.setSize(389, 301);

                    infoWindow.setUndecorated(true); //去掉标题栏
                    infoWindow.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

                    // 设置个人信息窗口的位置
                    int x = headerIcon.getLocationOnScreen().x;
                    int y = headerIcon.getLocationOnScreen().y;
                    infoWindow.setLocation(x + headerIcon.getWidth(), y);
                }
                // 显示个人信息窗口
                infoWindow.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                // 隐藏个人信息窗口
                if (infoWindow != null) {
                    infoWindow.setVisible(false);
                }
            }
        });

        //设置头像分割线
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.WHITE);
        separator.setBounds(0, 149, 235, 3);
        this.add(separator);

        //侧边栏颜色
        JPanel left = new JPanel();
        left.setBackground(new Color(64, 80, 50));
        left.setBounds(0,0,235,858);
        add(left);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SocketHelper skhp = new SocketHelper();
                skhp.getConnection(skhp.ip, skhp.port);
                try {
                    skhp.getOs().writeInt(1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                JFrame frame = new JFrame();
                User uu = new User();
                uu.setId("09021102");
                try {
                    new mainUIAdmin(skhp,uu).setVisible(true);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    


    private void logoutMouseClicked(MouseEvent e) {
        try {
            socketHelper.getOs().writeInt(-400);
            chatview.close();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.dispose();
    }

    private void button1MouseClicked(MouseEvent e) {
        card.show(pagePanel,"p1");
    }

    private void button2MouseClicked(MouseEvent e) {
        card.show(pagePanel,"p2");
    }

    private void button7MouseClicked(MouseEvent e) {
        card.show(pagePanel,"p7");
    }

    private void button3MouseClicked(MouseEvent e) {
        card.show(pagePanel,"p3");
    }

    private void button4MouseClicked(MouseEvent e) {
        card.show(pagePanel,"p4");
    }

    private void button5MouseClicked(MouseEvent e) {
        card.show(pagePanel,"p5");
    }

    private void button6MouseClicked(MouseEvent e) {
        card.show(pagePanel,"p6");
    }

    private void thisWindowClosing(WindowEvent e) {
        try {
            socketHelper.getOs().writeInt(-300);
            socketHelper.getOs().flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        pagePanel = new JPanel();
        logout = new JButton();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        label1 = new JLabel();

        //======== this ========
        setTitle("Virtual Campus");
        setBackground(new Color(0x3333ff));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thisWindowClosing(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== pagePanel ========
        {
            pagePanel.setLayout(new CardLayout());
        }
        contentPane.add(pagePanel);
        pagePanel.setBounds(new Rectangle(new Point(1451, 186), pagePanel.getPreferredSize()));

        //---- logout ----
        logout.setText("\u767b\u51fa");
        logout.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 22));
        logout.setIcon(new ImageIcon(getClass().getResource("/mainUiView/mainPagePic/\u6ce8\u9500.png")));
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logoutMouseClicked(e);
            }
        });
        contentPane.add(logout);
        logout.setBounds(new Rectangle(new Point(19, 587), logout.getPreferredSize()));

        //---- button1 ----
        button1.setText("\u7528\u6237\u7ba1\u7406");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(30, 120, button1.getPreferredSize().width, 39);

        //---- button2 ----
        button2.setText("\u5b66\u7c4d\u7ba1\u7406");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(35, 198), button2.getPreferredSize()));

        //---- button3 ----
        button3.setText("\u56fe\u4e66\u9986");
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button3MouseClicked(e);
            }
        });
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(35, 269), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u81ea\u4e3b\u9009\u8bfe");
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button4MouseClicked(e);
            }
        });
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(35, 341), button4.getPreferredSize()));

        //---- button5 ----
        button5.setText("\u5728\u7ebf\u5546\u5e97");
        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button5MouseClicked(e);
            }
        });
        contentPane.add(button5);
        button5.setBounds(new Rectangle(new Point(35, 412), button5.getPreferredSize()));

        //---- button6 ----
        button6.setText("\u94f6\u884c\u7cfb\u7edf");
        button6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button6MouseClicked(e);
            }
        });
        contentPane.add(button6);
        button6.setBounds(new Rectangle(new Point(44, 468), button6.getPreferredSize()));

        //---- button7 ----
        button7.setText("\u804a\u5929\u5ba4");
        button7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button7MouseClicked(e);
            }
        });
        contentPane.add(button7);
        button7.setBounds(new Rectangle(new Point(40, 535), button7.getPreferredSize()));

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/mainUiView/mainPagePic/newseu.png")));
        contentPane.add(label1);
        label1.setBounds(215, 435, 138, 140);

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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel pagePanel;
    private JButton logout;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
