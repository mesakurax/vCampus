package LoginView;
import entity.Operation;
import entity.User;
import utils.SocketHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PasswordChange extends JFrame {


    //输入输出流
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;

    private User utemp;
    private User unew;

    private MyButton button1;
    private MyTextField textField;
    private MyPasswordField textField2;
    private MyPasswordField textField3;


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

    public User getNewuser()
    {
        return unew;
    }

    public void close()
    {
        setVisible(false);
    }

    //设置主背景
    public void setBak() {
        WIDTH = 400;
        HEIGHT = 600;
        ((JPanel) this.getContentPane()).setOpaque(false);
        //图片适应标签大小
        img.setImage(img.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
        //创建背景标签
        background = new JLabel(img);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, WIDTH, HEIGHT);
    }
    public PasswordChange(SocketHelper stemp)
    {

        //设置输入输出流
        outputStream = stemp.getOs();
        inputStream = stemp.getIs();


        //设置可见性
        setVisible(true);

        //操作相关初始化
        utemp=new User();
        unew=new User();

        //锁定窗口大小
        this.setResizable(false);

        //背景相关初始化

        //添加图片
        img = new ImageIcon("./src/LoginView/pic/image5.jpg"); //添加图片
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


        //字体设置
        Font f = new Font("华文行楷", Font.BOLD, 20);

        //按钮
        button1= new MyButton("修改密码");
        button1.setActionCommand("修改密码");
        button1.setFont(f);


        //输入框
        textField=new MyTextField(20);
        textField2=new MyPasswordField(20);
        textField3=new MyPasswordField(20);

        //添加组件
        jp.add(textField);
        jp.add(textField2);
        jp.add(textField3);
        jp.add(button1);

        //输入框设置
        textField.setText("用户名");
        textField.setOpaque(false);
        textField.setForeground(Color.white);
        textField.setFont(f);

        //密码明文
        textField2.setEchoChar((char)0);
        textField2.setText("原密码");
        textField2.setOpaque(false);
        textField2.setForeground(Color.white);
        textField2.setFont(f);

        textField3.setEchoChar((char)0);
        textField3.setText("新密码");
        textField3.setOpaque(false);
        textField3.setForeground(Color.white);
        textField3.setFont(f);


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
                    textField.setForeground(Color.white); //将提示文字设置为灰色
                    textField.setText("用户名");     //显示提示文字
                }
            }
        });



        //密码框监听
        textField2.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                //得到焦点时，当前文本框的提示文字和创建该对象时的提示文字一样，说明用户正要键入内容
                if (textField2.getText().equals("原密码")) {
                    textField2.setText("");     //将提示文字清空
                    //解除明文
                    textField2.setEchoChar('?');
                    textField2.setForeground(Color.black);  //设置用户输入的字体颜色为黑色
                }


            }

            @Override
            public void focusLost (FocusEvent e){
                //失去焦点时，用户尚未在文本框内输入任何内容，所以依旧显示提示文字
                if (textField2.getText().equals("")) {
                    //明文
                    textField2.setEchoChar((char)0);
                    textField2.setForeground(Color.white); //将提示文字设置为灰色
                    textField2.setText("原密码");     //显示提示文字
                }
            }
        });

        textField3.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                //得到焦点时，当前文本框的提示文字和创建该对象时的提示文字一样，说明用户正要键入内容
                if (textField3.getText().equals("新密码")) {
                    textField3.setText("");     //将提示文字清空
                    //解除明文
                    textField3.setEchoChar('?');
                    textField3.setForeground(Color.black);  //设置用户输入的字体颜色为黑色
                }


            }

            @Override
            public void focusLost (FocusEvent e){
                //失去焦点时，用户尚未在文本框内输入任何内容，所以依旧显示提示文字
                if (textField3.getText().equals("")) {
                    //明文
                    textField3.setEchoChar((char)0);
                    textField3.setForeground(Color.white); //将提示文字设置为灰色
                    textField3.setText("新密码");     //显示提示文字
                }
            }
        });

        //大小位置设定
        textField.setBounds(90,120,220,40);
        textField2.setBounds(90,180,220,40);
        textField3.setBounds(90,240,220,40);
        button1.setBounds(90,300,220,40);



        //按钮设置监听
        MyListener1 lis1=new MyListener1();
        button1.addActionListener(lis1);

        //退出监听
        addWindowListener(new WindowAdapter() {
            //关闭窗口
            @Override
            public void windowClosing(WindowEvent e) {
                  setVisible(false);
            }
        });
        //pack();
    }

    //监听子类
    class MyListener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id=textField.getText();
            String op=textField2.getText();
            String np=textField3.getText();
            //判断是否输入
            Boolean r1=!id.isEmpty()&&!id.equals("用户名");
            Boolean r2=!op.isEmpty()&&!op.equals("原密码");
            Boolean r3=!np.isEmpty()&&!np.equals("新密码");
            //符合
            if(r1&&r2&&r3) {
                utemp.setId(id);
                utemp.setPassword(op);
                unew.setId(id);
                unew.setPassword(np);
                textField.setText("用户名");
                textField.setOpaque(false);
                textField.setForeground(Color.white);
                //明文
                textField2.setEchoChar((char) 0);
                textField2.setForeground(Color.white); //将提示文字设置为灰色
                textField2.setText("原密码");     //显示提示文字
                //明文
                textField3.setEchoChar((char) 0);
                textField3.setForeground(Color.white); //将提示文字设置为灰色
                textField3.setText("新密码");     //显示提示文字


                //以下是和后端通讯
                //传输命令
                try {
                    outputStream.reset();
                    outputStream.writeInt(800);
                    outputStream.flush();


                    //实例化操作对象
                    Operation oper=new Operation();
                    oper.addUser(utemp);
                    oper.addUser(unew);
                    oper.setOperationcode(007);
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
                        JOptionPane.showMessageDialog(null, "密码修改成功");
                        setVisible(false);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "密码修改失败", "", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }


            }
            else {
                JOptionPane.showMessageDialog(null, "请检查是否输入全部信息", "",JOptionPane.WARNING_MESSAGE);

            }


        }
    }



}
