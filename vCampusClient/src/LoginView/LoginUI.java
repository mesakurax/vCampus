package LoginView;

import entity.Operation;
import entity.User;
import mainUiView.LoginToMain;
import utils.SocketHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginUI extends JFrame {
    private User utemp;
    private MyButton b1;
    private MyButton b2;
    private MyButton b3;
    private MyTextField textField;
    private MyPasswordField textField2;

    //输入输出流
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;

    //sockethelper
    private SocketHelper sh;


    int operation;//判断用户的操作类型登录or注册,-1未选操作，0登录，1注册.
    Boolean isfinished;//判断当前用户是否完成输入,


    //背景图片
    private ImageIcon img;
    //背景标签
    private JLabel background;
    //背景面板
    private JPanel jp;


    private int WIDTH;
    private int HEIGHT;


    public User getUser() {
        return utemp;
    }

    public boolean getResult() {
        return isfinished;
    }

    public void setResult(boolean temp) {
        isfinished = temp;
    }

    public void close() {
        setVisible(false);
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int temp) {
        operation = temp;
    }

    //设置主背景
    public void setBak() {
        WIDTH = img.getIconWidth();
        HEIGHT = img.getIconHeight();
        ((JPanel) this.getContentPane()).setOpaque(false);
        //图片适应标签大小
        img.setImage(img.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
        //创建背景标签
        background = new JLabel(img);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, WIDTH, HEIGHT);
    }

    public LoginUI(SocketHelper stemp) {

        sh=stemp;

        //设置输入输出流
        outputStream = stemp.getOs();
        inputStream = stemp.getIs();

        //锁定窗口大小
        this.setResizable(false);

        //设置背景

        //添加图片
        img = new ImageIcon("./src/LoginView/pic/image2.jpg"); //添加图片
        setBak(); //调用背景方法
        Container c = getContentPane(); //获取JFrame面板
        jp = new JPanel(); //创建个JPanel
        jp.setOpaque(false);
        //把JPanel设置为透明 这样就不会遮住后面的背景 这样能在JPanel随意加组件
        jp.setBounds(0,0,WIDTH, HEIGHT);
        c.add(jp);
        setBounds(0, 0, WIDTH, HEIGHT + 37);
        setVisible(true);
        //JPanel布局缺省
        jp.setLayout(null);


        //布局缺省
        setLayout(null);

        //设置可见性
        setVisible(true);


        //参数设置
        utemp = new User();
        operation = -1;
        isfinished = false;

        //按钮以及字体设置
        Font f = new Font("微软雅黑", Font.BOLD, 20);
        Font f2 = new Font("微软雅黑", Font.BOLD, 15);
        Font f3 = new Font("华文行楷", Font.BOLD, 50);
        Font f4 = new Font("华文行楷", Font.BOLD, 70);
        b1 = new MyButton("登录");
        b1.setFont(f);
        b2 = new MyButton("注册");
        b2.setFont(f2);
        b3 = new MyButton("修改密码");
        b3.setFont(f2);


        //命令设置
        b1.setActionCommand("登录");
        b2.setActionCommand("注册");
        b3.setActionCommand("修改密码");


        //标签
        JLabel lab = new JLabel("身份认证中心");
        lab.setFont(f3);
        lab.setForeground(Color.black);
        JLabel lab2 = new JLabel("东南大学");
        lab2.setFont(f4);
        lab2.setForeground(Color.black);

        //输入框
        textField = new MyTextField(20);
        textField.setText("用户名");
        textField.setOpaque(false);
        textField.setForeground(Color.black);
        textField.setFont(f);

        //密码框
        textField2 = new MyPasswordField(20);
        //密码明文
        textField2.setEchoChar((char)0);
        textField2.setText("密码");
        textField2.setOpaque(false);
        textField2.setForeground(Color.black);
        textField2.setFont(f);

        //文本框监听
        textField.addFocusListener(new FocusListener() {

                                       public void focusGained(FocusEvent e) {
                                           //得到焦点时，当前文本框的提示文字和创建该对象时的提示文字一样，说明用户正要键入内容
                                           if (textField.getText().equals("用户名")) {
                                               textField.setText("");     //将提示文字清空
                                               textField.setForeground(Color.black);  //设置用户输入的字体颜色为黑色
                                           }


                                       }

                                       @Override
                                       public void focusLost (FocusEvent e){
                                           //失去焦点时，用户尚未在文本框内输入任何内容，所以依旧显示提示文字
                                           if (textField.getText().equals("")) {
                                               textField.setForeground(Color.black); //将提示文字设置为灰色
                                               textField.setText("用户名");     //显示提示文字
                                           }
                                       }
                                   });

        //密码框监听
        textField2.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                //得到焦点时，当前文本框的提示文字和创建该对象时的提示文字一样，说明用户正要键入内容
                if (textField2.getText().equals("密码")) {
                    textField2.setText("");     //将提示文字清空
                    //解除明文
                    textField2.setEchoChar('*');
                    textField2.setForeground(Color.black);  //设置用户输入的字体颜色为黑色
                }


            }

            @Override
            public void focusLost (FocusEvent e){
                //失去焦点时，用户尚未在文本框内输入任何内容，所以依旧显示提示文字
                if (textField2.getText().equals("")) {
                    //明文
                    textField2.setEchoChar((char)0);
                    textField2.setForeground(Color.black); //将提示文字设置为灰色
                    textField2.setText("密码");     //显示提示文字
                }
            }
        });

        //添加组件
        jp.add(textField);
        jp.add(textField2);
        jp.add(lab);
        jp.add(lab2);
        jp.add(b1);
        jp.add(b2);
        jp.add(b3);


        //大小位置设定
        lab.setBounds(270, 380, 320, 50);
        lab2.setBounds(280, 270, 300, 80);

        textField.setBounds(320, 480, 220, 30);
        textField2.setBounds(320, 530, 220, 30);
        b1.setBounds(320, 580, 220, 30);
        b2.setBounds(320, 620, 100, 30);
        b3.setBounds(440, 620, 100, 30);


        //按钮设置监听
        MyListener1 lis1 = new MyListener1();
        b1.addActionListener(lis1);
        b2.addActionListener(lis1);
        b3.addActionListener(lis1);

        //退出监听
        addWindowListener(new WindowAdapter() {
            //关闭窗口
            @Override
            public void windowClosing(WindowEvent e) {
                operation = -2;
                isfinished = true;
            }
        });
        //pack();
    }

    //监听子类
    class MyListener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("登录")) {
                boolean r1=!textField.getText().equals(null)&&!textField.getText().equals("用户名");
                boolean r2=!textField2.getText().equals(null)&&!textField2.getText().equals("密码");
                if(r1&&r2) {
                    utemp.setId(textField.getText());
                    utemp.setPassword(textField2.getText());
                    System.out.println("登录对象： ");
                    utemp.UserPrint();
                    textField.setForeground(Color.black); //将提示文字设置为灰色
                    textField.setText("用户名");     //显示提示文字
                    textField2.setForeground(Color.black); //将提示文字设置为灰色
                    textField2.setEchoChar((char)0);//明文
                    textField2.setText("密码");     //显示提示文字
                    System.out.println("登录");

                    //以下为和后端的联系

                    try {
                        //传输命令
                        outputStream.reset();
                        outputStream.writeInt(800);
                        outputStream.flush();

                        //实例化操作对象
                        Operation oper=new Operation();
                        oper.addUser(utemp);
                        oper.setOperationcode(001);
                        // 向服务端发送对象
                        System.out.println("登录对象");
                        utemp.UserPrint();
                        //发送对象
                        outputStream.reset();
                        outputStream.writeObject(oper);
                        outputStream.flush();
                        System.out.println("对象发送成功");

                        // 读取服务端发送的响应对象
                        oper.copy((Operation)inputStream.readObject());
                        System.out.println("对象读取成功");
                        System.out.println("服务端响应的oper对象：");
                        if(oper.getSuccess())
                        {
                            System.out.println("登录成功");
                            utemp=oper.getUser(1);
                            System.out.println("当前登录成功的用户是： " + utemp.getName());
                            setVisible(false);
                            new LoginToMain(sh,utemp);

                        }
                        else
                        {
                            System.out.println("登陆失败");
                            JOptionPane.showMessageDialog(null, "登陆失败", "",JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException | InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }


                }
            } else if (e.getActionCommand().equals("注册")) {
                //弹出注册界面
                System.out.println("注册");
                RegisterUI ru=new RegisterUI(sh);
            } else {
                System.out.println("修改密码");
                //密码修改界面
                PasswordChange pc=new PasswordChange(sh);
            }

        }
    }
    //public static void main(String[] args)
    //{
       // new LoginUI();
    //}

}


