package LoginView;

import entity.Operation;
import entity.User;
import utils.SocketHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RegisterUI  extends JFrame {



    //输入输出流
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;
    private User utemp;



    //背景图片
    private ImageIcon img;
    //背景标签
    private JLabel background;
    //背景面板
    private JPanel jp;


    private int WIDTH;
    private int HEIGHT;




    public User getUser()
    {
        return utemp;
    }
    public void close()
    {
        setVisible(false);
    }

    public RegisterUI(SocketHelper stemp)
    {

        //设置输入输出流
        outputStream = stemp.getOs();
        inputStream = stemp.getIs();

        //设置可见性
        setVisible(true);


        //设置初始用户
        utemp=new User();



        //锁定窗口大小
        this.setResizable(false);


        //背景设置

        //添加图片
        img = new ImageIcon("./src/LoginView/pic/image4.jpg"); //添加图片
        setBak(); //调用背景方法
        Container c = getContentPane(); //获取JFrame面板
        jp = new JPanel(); //创建个JPanel
        jp.setOpaque(false);
        //把JPanel设置为透明 这样就不会遮住后面的背景 这样能在JPanel随意加组件
        jp.setBounds(0,0,WIDTH, HEIGHT);
        c.add(jp);
        setBounds(700, 200, WIDTH, HEIGHT);
        //JPanel布局缺省
        jp.setLayout(null);


        //布局缺省
        setLayout(null);




        //设置按钮
        MyButton button1=new MyButton ("确认");
        button1.setBounds(150,360,100,40);
        button1.setBUTTON_COLOR1(new Color(243, 149, 4, 131));
        button1.setBUTTON_COLOR2(new Color(234, 154, 5, 229));
        Font f1 = new Font("华文行楷", Font.BOLD, 16);
        button1.setFont(f1);

        //设置标签
        JLabel l1=new JLabel("工号/一卡通号");
        JLabel l2=new JLabel("姓名");
        JLabel l3=new JLabel("密码");
        JLabel l4=new JLabel("职业");
        JLabel l5=new JLabel("学院");
        JLabel l6=new JLabel("性别");
        JLabel l7=new JLabel("年龄");

        //设置标签字体
        Font f = new Font("华文行楷", Font.BOLD, 16);
        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l5.setFont(f);
        l6.setFont(f);
        l7.setFont(f);

        //设置标签颜色
        l1.setForeground(new Color(3, 1, 2));
        l2.setForeground(new Color(3, 1, 2));
        l3.setForeground(new Color(3, 1, 2));
        l4.setForeground(new Color(3, 1, 2));
        l5.setForeground(new Color(3, 1, 2));
        l6.setForeground(new Color(3, 1, 2));
        l7.setForeground(new Color(3, 1, 2));

        //设置标签大小位置
        l1.setBounds(70,50,130,30);
        l2.setBounds(70,90,130,30);
        l3.setBounds(70,130,130,30);
        l4.setBounds(70,170,130,30);
        l5.setBounds(70,210,130,30);
        l6.setBounds(70,250,130,30);
        l7.setBounds(70,290,130,30);


        //设置文本框
        JTextField t1=new  JTextField();
        JTextField t2=new  JTextField();
        JTextField t3=new  JTextField();
        //下拉选项
        JComboBox<String> t4=new JComboBox<>();
        t4.addItem("教师");// 下拉框列表添加内容。Item（条款，项）
        t4.addItem("学生");
        JTextField t5=new  JTextField();
        JTextField t6=new  JTextField();
        JTextField t7=new  JTextField();

        //设置文本框透明
        t1.setOpaque (false);
        t2.setOpaque (false);
        t3.setOpaque (false);
        t4.setOpaque (false);
        t5.setOpaque (false);
        t6.setOpaque (false);
        t7.setOpaque (false);

        //设置文本框字体
        t1.setFont(f1);
        t2.setFont(f1);
        t3.setFont(f1);
        t4.setFont(f1);
        t5.setFont(f1);
        t6.setFont(f1);
        t7.setFont(f1);

        //设置文本框颜色
        t1.setForeground(new Color(3, 1, 2));
        t2.setForeground(new Color(3, 1, 2));
        t3.setForeground(new Color(3, 1, 2));
        t4.setForeground(new Color(3, 1, 2));
        t5.setForeground(new Color(3, 1, 2));
        t6.setForeground(new Color(3, 1, 2));
        t7.setForeground(new Color(3, 1, 2));



        t1.setBounds(200,50,120,30);
        t2.setBounds(200,90,120,30);
        t3.setBounds(200,130,120,30);
        t4.setBounds(200,170,120,30);
        t5.setBounds(200,210,120,30);
        t6.setBounds(200,250,120,30);
        t7.setBounds(200,290,120,30);
        //添加组件
        jp.add(t1);
        jp.add(l1);
        jp.add(t2);
        jp.add(l2);
        jp.add(t3);
        jp.add(l3);
        jp.add(t4);
        jp.add(l4);
        jp.add(t5);
        jp.add(l5);
        jp.add(t6);
        jp.add(l6);
        jp.add(t7);
        jp.add(l7);
        jp.add(button1);
        //监听按钮
        button1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            //判断非空逻辑
            Boolean r1=!t1.getText().isEmpty()&&!t2.getText().isEmpty()&&!t3.getText().isEmpty();
            Boolean r2=!t5.getText().isEmpty()&&!t6.getText().isEmpty()&&!t7.getText().isEmpty();
            Boolean r3=r1&&r2;
            //符合
            if(r3) {

                utemp.setId(t1.getText());
                utemp.setName(t2.getText());
                utemp.setPassword(t3.getText());
                utemp.setOccupation((String) t4.getSelectedItem());
                utemp.setAcademy(t5.getText());
                utemp.setSex(t6.getText());
                utemp.setAge((Integer.valueOf(t7.getText()).intValue()));
                utemp.UserPrint();
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t5.setText("");
                t6.setText("");
                t7.setText("");

                //以下为和后端通讯
                try {
                    //传输命令
                    outputStream.reset();
                    outputStream.writeInt(800);
                    outputStream.flush();


                    //实例化操作对象
                    Operation oper=new Operation();
                    oper.addUser(utemp);
                    System.out.println("808080");
                    oper.getUser(1).UserPrint();
                    oper.setOperationcode(002);
                    // 向服务端发送对象
                    outputStream.reset();
                    outputStream.writeObject(oper);
                    outputStream.flush();
                    System.out.println("对象发送成功");

                    // 读取服务端发送的响应对象
                    oper.copy((Operation) inputStream.readObject());
                    System.out.println("对象读取成功");
                    System.out.println("服务端响应的oper对象：");
                    if(oper.getSuccess())
                    {
                        JOptionPane.showMessageDialog(null, "注册成功");
                        setVisible(false);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "注册失败", "",JOptionPane.WARNING_MESSAGE);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }


            }
            else
            {
                JOptionPane.showMessageDialog(null, "请检查是否完成全部信息填写", "",JOptionPane.WARNING_MESSAGE);

            }

        }
    });


        //关闭监听
        addWindowListener(new WindowAdapter() {
            //关闭窗口
            @Override
            public void windowClosing(WindowEvent e) {

                setVisible(false);
            }
        });

    }

    //设置主背景
    public void setBak() {
        WIDTH = 400;
        HEIGHT =600;
        ((JPanel) this.getContentPane()).setOpaque(false);
        //图片适应标签大小
        img.setImage(img.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
        //创建背景标签
        background = new JLabel(img);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, WIDTH, HEIGHT);
    }
   // public static void main(String[] args)
   // {
       // new RegisterUI();
   // }



}
