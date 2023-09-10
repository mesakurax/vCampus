package LoginView;

import entity.User;
import entity.Operation;
import utils.SocketHelper;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Vector;
import  utils.UIStyler;

public class UserAdmin extends JPanel {

    public static final int CMD = 1200;

    //输入输出流
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;

    //背景图片
    private Image image = null;

    //文本框
    private MyTextField t;


    //表格组件
    private Vector<Vector<String>> vData;// 数据行向量集，因为列表不止一行，往里面添加数据行向量，添加方法add(row)
    private Vector<String> vName;// 列名（标题）向量，使用它的add()方法添加列名
    private JTable jTable1;//表格
    JTableHeader tab_header;//表头

    private JScrollPane scroll;//滚动面包，扩大范围
    private DefaultTableModel model; //新建一个默认数据模型


    //框架大小
    private int WIDTH;
    private int HEIGHT;

    //表格内容物
    public HashMap<Integer, User> users;


    //绘图重写
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon img = new ImageIcon("./src/LoginView/pic/image0.jpg");
        //System.out.println("kkkkk: "+img.getIconWidth()+" ooooo: "+img.getIconHeight());
        //img.paintIcon(this, g, 0, 0);
        g.drawImage(img.getImage(), 0, 0, 1685, 1030, img.getImageObserver());
    }

    //按钮变色
    public void setBtnTran(JButton button1){

        button1.setForeground(Color.BLACK);
        button1.setMargin(new Insets(0, 0, 0, 0));//将边框外的上下左右空间设置为0
        //去边框
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);
        button1.setContentAreaFilled(false);//除去默认的背景填充
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button1.setOpaque(true);
                button1.setBackground(Color.white);
                button1.setFocusPainted(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button1.setOpaque(false);
                button1.setFocusPainted(false);
                button1.setBackground(Color.BLACK);
            }
        });
    }


    public UserAdmin(SocketHelper stemp) {

        //设置输入输出流
        outputStream = stemp.getOs();
        inputStream = stemp.getIs();

        //初设users
        users = new HashMap<Integer, User>();


        //框架初设
        WIDTH = 1685;
        HEIGHT = 1030;

        //布局缺省
        setLayout(null);
        //设置大小
        setSize(WIDTH, HEIGHT);
        //布局缺省
        setLayout(null);


        JLabel chessboard2;
        add(chessboard2=new JLabel(new ImageIcon("./src/LoginView/pic/image7.png")));
        chessboard2.setBounds(0,27,273,88);


        Font f3 = new Font("楷体", Font.BOLD, 60);
        JLabel lab = new JLabel("东南大学用户管理");
        lab.setFont(f3);
        lab.setForeground(Color.WHITE);
        lab.setBounds(1035,15,600,135);
        add(lab);

        JLabel lb=new JLabel();
        lb.setBackground(new Color(36,50,30));
        lb.setOpaque(true);
        lb.setBounds(0,0,1685,150);

        JLabel lb1=new JLabel();
        lb1.setBackground(new Color(64,80,50));
        lb1.setOpaque(true);
        lb1.setBounds(0,150,1685,50);





        //新建按钮
        JButton button1 = new JButton("查询全部");
        JButton button2 = new JButton("用户查询");
        JButton button3 = new JButton("添加用户");
        JButton button4=new JButton("查询教师");
        JButton button5 = new JButton("查询学生");
        JButton button6 = new JButton("删除所选");

        //字体设置
        Font f = new Font("楷体", Font.BOLD, 20);
        Font f1 = new Font("楷体", Font.BOLD, 26);

        //新建文本框
        t = new MyTextField(20);
        t.setOpaque(false);
        t.setFont(f);
        t.setText("请输入工号/一卡通号");
        t.setForeground(new Color(1,2,3));
        t.setVisible(true);


        //文本框监听

        t.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                //得到焦点时，当前文本框的提示文字和创建该对象时的提示文字一样，说明用户正要键入内容
                if (t.getText().equals("请输入工号/一卡通号")) {
                    t.setText("");     //将提示文字清空
                    t.setForeground(Color.black);  //设置用户输入的字体颜色为黑色
                }


            }

            @Override
            public void focusLost(FocusEvent e) {
                //失去焦点时，用户尚未在文本框内输入任何内容，所以依旧显示提示文字
                if (t.getText().equals("")) {
                    t.setForeground(new Color(3, 1, 2));
                    ; //将提示文字设置为白色
                    t.setText("请输入工号/一卡通号");     //显示提示文字
                }
            }
        });


        //按钮字体设置
        button1.setFont(f1);
        button2.setFont(f1);
        button3.setFont(f1);
        button4.setFont(f1);
        button5.setFont(f1);
        button6.setFont(f1);




        //为按钮设置命令
        button1.setActionCommand("查询全部");
        button2.setActionCommand("用户查询");
        button3.setActionCommand("添加用户");
        button4.setActionCommand("查询教师");
        button5.setActionCommand("查询学生");
        button6.setActionCommand("删除所选");


        //按钮变色监听
        setBtnTran(button1);
        setBtnTran(button2);
        setBtnTran(button3);
        setBtnTran(button4);
        setBtnTran(button5);
        setBtnTran(button6);

        //按钮位置大小设置;
        int x1=0;
        button1.setBounds(x1, 150, 150, 50);
        button2.setBounds(x1+960, 150, 150, 50);
        //button2.setBounds(1500, 150, 150, 50);
        button3.setBounds(x1+150, 150, 150, 50);
        button4.setBounds(x1+300,150,150,50);
        button5.setBounds(x1+450, 150, 150, 50);
        button6.setBounds(x1+600, 150, 150, 50);

        //文本框设置大小位置
        t.setBounds(x1+750, 161, 200, 28);


        //设置监听
        MyListener lis = new MyListener();
        button1.addActionListener(lis);
        button2.addActionListener(lis);
        button3.addActionListener(lis);
        button4.addActionListener(lis);
        button5.addActionListener(lis);
        button6.addActionListener(lis);

        //添加按钮
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);



        //添加文本框
        add(t);

        //添加标签
        add(lb);
        add(lb1);

        //表格组件初设
        iniTable();
        //设置表格
        setTable();


        //表格监听
        jTable1.getModel().addTableModelListener(new TableModelListener() {

            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {

                    int row = jTable1.getEditingRow();
                    int col = jTable1.getEditingColumn();

                    if (col != 7) {
                        //System.out.println("更改对象："+vData.get(row).get(col) );
                        User utemp = new User();
                        utemp.setId(vData.get(row).get(0));
                        utemp.setName(vData.get(row).get(1));
                        utemp.setPassword(vData.get(row).get(2));
                        utemp.setSex(vData.get(row).get(3));
                        utemp.setAge(Integer.parseInt(vData.get(row).get(4)));
                        utemp.setOccupation(vData.get(row).get(5));
                        utemp.setAcademy(vData.get(row).get(6));
                        //utemp.UserPrint();


                        //储存前清空操作对象
                        clearUsers();
                        //存储对象
                        addUser(utemp);

                        //以下为后端联系

                        try {
                            //传输命令
                            outputStream.reset();
                            outputStream.writeInt(CMD);
                            outputStream.flush();

                            //实例化操作对象
                            Operation oper = new Operation();
                            //设置操作码
                            oper.setOperationcode(006);
                            //存储查询用户ID
                            oper.addUser(users.get(1));
                            // 向服务端发送对象
                            outputStream.reset();
                            outputStream.writeObject(oper);
                            outputStream.flush();
                            System.out.println("对象发送成功");

                            // 读取服务端发送的响应对象
                            oper.copy((Operation) inputStream.readObject());
                            System.out.println("对象读取成功");
                            System.out.println("服务端响应的oper对象：");
                            if (oper.getSuccess()) {

                                System.out.println("更新成功");
                                JOptionPane.showMessageDialog(null, "用户修改成功");


                            } else {
                                System.out.println("更新失败");
                                JOptionPane.showMessageDialog(null, "用户修改失败", "", JOptionPane.WARNING_MESSAGE);

                            }
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }


                    }


                }

            }

        });


    }

    //表格组件初设
    public void iniTable() {
        vData = new Vector<Vector<String>>(); // 数据行向量集，因为列表不止一行，往里面添加数据行向量，添加方法add(row)
        vName = new Vector<String>(); // 列名（标题）向量，使用它的add()方法添加列名
        jTable1 = new JTable();
        jTable1.setRowHeight(50);

        //修改改写权限
        model = new DefaultTableModel(vData, vName) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        ; //新建一个默认数据模型
        scroll = new JScrollPane();//新建滚动面板
        //建立表头
        vName.add(0, "工号/一卡通号");
        vName.add(1, "姓名");
        vName.add(2, "密码");
        vName.add(3, "性别");
        vName.add(4, "年龄");
        vName.add(5, "职业");
        vName.add(6, "学院");
        vName.add(7, "删除键");

        //滑动面板位置设置
        scroll.setBounds(150, 210, 1500, 800);
        //表头设置
        tab_header = jTable1.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("华文仿宋", Font.BOLD, 20));
        //tab_header.setOpaque(false);
        tab_header.setForeground(new Color(255, 255, 255));
        tab_header.setBackground(new Color(1,2,3, 0));
        tab_header.setReorderingAllowed(false);
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度
        jTable1.setFont(new Font("华文行楷", Font.PLAIN, 20));
        model.setDataVector(vData, vName);//新建一个数据模型
        System.out.println("bbbbbbbbb");

        TransparentHeaderRenderer hr = new TransparentHeaderRenderer(); //创建自定义的渲染器
        hr.setOpaque(false);
        hr.setHorizontalAlignment(JLabel.CENTER);
        jTable1.setDefaultRenderer(Object.class, hr);
        jTable1.setModel(model);

        //表格的列模型
        TableColumnModel cm = jTable1.getColumnModel();
        //得到第i个列对象
        TableColumn column = cm.getColumn(7);
        column.setPreferredWidth(31);

        jTable1.setOpaque(false);
        // 设置 TableCellRenderer 为表格的默认渲染器
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setFont(new Font("华文仿宋", Font.BOLD, 24));
                // 自定义颜色设置
                if (isSelected) {
                    // 当前行被选中时的颜色
                    component.setBackground(Color.WHITE);
                    component.setForeground(Color.BLACK);
                } else {
                    // 其他行的颜色
                    component.setBackground(new Color(0, 0, 0, 0)); // 设置背景透明
                    component.setForeground(Color.WHITE);
                }

                return component;
            }
        };
        jTable1.setDefaultRenderer(Object.class, cellRenderer);

        // 添加选择监听器以设置选中行的颜色
        //jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 设置只能选择单行
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // 确保只在选择完成时触发
                    int selectedRow = jTable1.getSelectedRow();
                    jTable1.repaint(); // 刷新表格以更新选中行的颜色
                }
            }
        });

        scroll.getViewport().setOpaque(false);
        scroll.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(7).setCellRenderer(new TableCellRendererButton());
        jTable1.getColumnModel().getColumn(7).setCellEditor(new TableCellEditorButton());
        System.out.println("nnnnnn");
        add(scroll);

        UIStyler.setTransparentTable(scroll);

        jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }




    //设置表格
    public void setTable() {
        vData.clear();
        for (int times = 1; times <= users.size(); times++) {


            Vector vRow = new Vector();
            vRow.add(0, users.get(times).getId());
            vRow.add(1, users.get(times).getName());
            vRow.add(2, users.get(times).getPassword());
            vRow.add(3, users.get(times).getSex());
            vRow.add(4, Integer.toString(users.get(times).getAge()));
            vRow.add(5, users.get(times).getOccupation());
            vRow.add(6, users.get(times).getAcademy());
            vRow.add(7, "");
            vData.add(vRow);


        }

        //jTable1.getColumnModel().getColumn(7).setCellRenderer(new TableCellRendererButton());
        //jTable1.getColumnModel().getColumn(7).setCellEditor(new TableCellEditorButton());
        jTable1.setOpaque(false);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setViewportView(jTable1);
        System.out.println("..............");


    }
    //添加表格内容物
    public void addUser(User utemp) {
        users.put(users.size() + 1, utemp);
    }

    //清空Users
    public void clearUsers() {
        users.clear();
    }

    public static void main(String[] args) throws IOException {

        SocketHelper socketHelper111 = new SocketHelper();
        socketHelper111.getConnection("localhost", 8081);

        socketHelper111.getOs().reset();
        socketHelper111.getOs().writeInt(1);
        socketHelper111.getOs().flush();
        System.out.println("ppppppp");


        UserAdmin ui = new UserAdmin(socketHelper111);

        JFrame j0000 = new JFrame();
        j0000.setVisible(true);
        j0000.add(ui);
        j0000.setLayout(null);
        j0000.setBounds(0, 0, 1685, 1030);
        ui.setLocation(0,0);


    }

    //自定义监听类
    class MyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getActionCommand().equals("查询全部")) {

                try {
                    //传输命令
                    outputStream.reset();
                    outputStream.writeInt(CMD);
                    outputStream.flush();

                    //设置命令类
                    Operation oper = new Operation();
                    System.out.println("808080");
                    oper.setOperationcode(003);
                    // 向服务端发送对象
                    outputStream.reset();
                    outputStream.writeObject(oper);
                    outputStream.flush();
                    System.out.println("对象发送成功");

                    // 读取服务端发送的响应对象
                    oper.copy((Operation) inputStream.readObject());
                    System.out.println("对象读取成功");
                    // System.out.println("服务端响应的oper对象：");
                    if (oper.getSuccess()) {

                        System.out.println("全部查询成功");

                        //清空数据
                        clearUsers();

                        //向ui复制数据
                        for (int i = 1; i <= oper.getUsers().size(); i++) {
                            addUser(oper.getUser(i));
                        }
                        //绘制表格
                        setTable();
                    } else {
                        System.out.println("全部查询失败");
                        JOptionPane.showMessageDialog(null, "全部查询失败", "", JOptionPane.WARNING_MESSAGE);

                    }
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }


            }
            else if (e.getActionCommand().equals("添加用户")) {

                AddUI ad=new AddUI();
            }
            else if (e.getActionCommand().equals("用户查询")) {
                if (!t.getText().isEmpty() && !t.getText().equals("请输入工号/一卡通号")) {
                    User u = new User();
                    u.setId(t.getText());
                    //清空数据
                    clearUsers();
                    //添加数据
                    addUser(u);
                    //清除文本
                    t.setForeground(new Color(3, 1, 2, 255)); //将提示文字设置为白色
                    t.setText("请输入工号/一卡通号");     //显示提示文字

                    //以下为与后端通信
                    //传输命令
                    try {
                        //发送命令
                        outputStream.reset();
                        outputStream.writeInt(CMD);
                        outputStream.flush();

                        //实例化操作对象
                        Operation oper = new Operation();
                        //设置操作码
                        oper.setOperationcode(004);
                        System.out.println("当前查询用户ID: " + users.get(1).getId());
                        //存储查询用户ID
                        oper.addUser(users.get(1));
                        // 向服务端发送对象
                        outputStream.reset();
                        outputStream.writeObject(oper);
                        outputStream.flush();
                        System.out.println("对象发送成功");

                        // 读取服务端发送的响应对象
                        oper.operClear();
                        oper.copy((Operation) inputStream.readObject());
                        System.out.println("对象读取成功");

                        if (oper.getSuccess()) {
                            System.out.println("查询成功");
                            //清空数据
                            clearUsers();
                            //向ui复制数据
                            oper.getUser(1).UserPrint();
                            addUser(oper.getUser(1));

                            //读取成功绘制表格
                            setTable();


                        } else {
                            System.out.println("查询失败");
                            JOptionPane.showMessageDialog(null, "未查询到该用户", "", JOptionPane.WARNING_MESSAGE);

                        }

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }


                }
            }
            else if (e.getActionCommand().equals("查询教师")) {


                try {
                    //发送命令
                    outputStream.reset();
                    outputStream.writeInt(CMD);
                    outputStream.flush();


                    //实例化操作对象
                    Operation oper = new Operation();
                    //设置操作码
                    oper.setOperationcode(007);
                    //新建用户
                    User u=new User();
                    u.setOccupation("教师");
                    //向操作类存储用户
                    oper.addUser(u);

                    // 向服务端发送对象
                    outputStream.reset();
                    outputStream.writeObject(oper);
                    outputStream.flush();
                    System.out.println("对象发送成功");

                    // 读取服务端发送的响应对象
                    oper.operClear();
                    oper.copy((Operation) inputStream.readObject());
                    System.out.println("对象读取成功");

                    if(oper.getSuccess())
                    {
                        //清空数据
                        clearUsers();

                        //添加数据
                        for(int i=1;i<=oper.getUsers().size();i++)
                        {
                            addUser(oper.getUser(i));
                        }

                        //绘制表格
                        setTable();
                    }
                    else
                    {
                        //查询失败
                        JOptionPane.showMessageDialog(null, "未查询到该职业的用户", "", JOptionPane.WARNING_MESSAGE);

                    }

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
            else if (e.getActionCommand().equals("查询学生")) {


                try {
                    //发送命令
                    outputStream.reset();
                    outputStream.writeInt(CMD);
                    outputStream.flush();


                    //实例化操作对象
                    Operation oper = new Operation();
                    //设置操作码
                    oper.setOperationcode(007);
                    //新建用户
                    User u=new User();
                    u.setOccupation("学生");
                    //向操作类存储用户
                    oper.addUser(u);

                    // 向服务端发送对象
                    outputStream.reset();
                    outputStream.writeObject(oper);
                    outputStream.flush();
                    System.out.println("对象发送成功");

                    // 读取服务端发送的响应对象
                    oper.operClear();
                    oper.copy((Operation) inputStream.readObject());
                    System.out.println("对象读取成功");

                    if(oper.getSuccess())
                    {
                        //清空数据
                        clearUsers();

                        //添加数据
                        for(int i=1;i<=oper.getUsers().size();i++)
                        {
                            addUser(oper.getUser(i));
                        }

                        //绘制表格
                        setTable();
                    }
                    else
                    {
                        //查询失败
                        JOptionPane.showMessageDialog(null, "未查询到该职业的用户", "", JOptionPane.WARNING_MESSAGE);

                    }

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
            else if (e.getActionCommand().equals("删除所选"))
            {
                //新建数组存储操作行
                int[] operrows;
                System.out.println("删除所选行");
                operrows=jTable1.getSelectedRows();
                //判断用户是否进行了选择，再进行后续操作
                if(operrows.length!=0) {
                    //清理数据
                    clearUsers();

                    for (int i = 0; i < operrows.length; i++) {
                        User u = new User();
                        //添加数据
                        u.setId(vData.get(operrows[i]).get(0));
                        addUser(u);
                        System.out.println("按钮事件触发----: " + vData.get(operrows[i]).get(0));
                    }




                    //以下为和后端联系


                try {
                    //传输命令
                    outputStream.reset();
                    outputStream.writeInt(CMD);
                    outputStream.flush();

                    //实例化操作对象
                    Operation oper = new Operation();
                    //设置操作码
                    oper.setOperationcode(005);
                    //存储查询用户ID
                    oper.setUsers(users);
                    // 向服务端发送对象
                    outputStream.reset();
                    outputStream.writeObject(oper);
                    outputStream.flush();
                    System.out.println("对象发送成功");

                    // 读取服务端发送的响应对象
                    oper.copy((Operation) inputStream.readObject());
                    System.out.println("对象读取成功");

                    if (oper.getSuccess()) {

                        System.out.println("删除成功");
                        JOptionPane.showMessageDialog(null, "用户删除成功");

                    } else {
                        System.out.println("删除失败");
                        JOptionPane.showMessageDialog(null, "用户删除失败", "", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }


            }


                }
            }



        }

    //自定义渲染
    class TableCellRendererButton implements TableCellRenderer {


        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            //字体设置
            Font f = new Font("华文仿宋", Font.BOLD, 24);
            MyButton button = new MyButton("删除");
            button.setBUTTON_COLOR2(new Color(1,2,3, 47));
            button.setBUTTON_COLOR1(new Color(2,3,4, 116));
            button.setFont(f);
            button.setForeground(Color.WHITE);
            return button;
        }

    }

    //自定义透明表格渲染
    class TransparentHeaderRenderer extends DefaultTableCellRenderer {
        public TransparentHeaderRenderer() {
            setOpaque(false);
        }
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBackground(new Color(0, 0, 0, 0)); //设置背景色为透明
            return this;
        }
    }

    //自定义编辑
    class TableCellEditorButton extends DefaultCellEditor {

        private MyButton btn;
        private int r;

        public TableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new MyButton("删除");
            //字体设置
            Font f1 = new Font("华文仿宋", Font.BOLD, 24);
            btn= new MyButton("删除");
            btn.setFont(f1);
            btn.setBUTTON_COLOR2(new Color(1,2,3, 47));
            btn.setBUTTON_COLOR1(new Color(2,3,4, 116));
            btn.setForeground(Color.WHITE);
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("删除用户");
                    System.out.println("按钮事件触发----: " + vData.get(r).get(0));
                    User u = new User();
                    u.setId(vData.get(r).get(0));
                    //清理数据
                    clearUsers();
                    //添加数据
                    addUser(u);

                    //以下为和后端联系

                    try {
                        //传输命令
                        outputStream.reset();
                        outputStream.writeInt(CMD);
                        outputStream.flush();

                        //实例化操作对象
                        Operation oper = new Operation();
                        //设置操作码
                        oper.setOperationcode(005);
                        //存储查询用户ID
                        oper.addUser(users.get(1));
                        // 向服务端发送对象
                        outputStream.reset();
                        outputStream.writeObject(oper);
                        outputStream.flush();
                        System.out.println("对象发送成功");

                        // 读取服务端发送的响应对象
                        oper.copy((Operation) inputStream.readObject());
                        System.out.println("对象读取成功");

                        if (oper.getSuccess()) {

                            System.out.println("删除成功");
                            JOptionPane.showMessageDialog(null, "用户删除成功");

                        } else {
                            System.out.println("删除失败");
                            JOptionPane.showMessageDialog(null, "用户删除失败", "", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }


                }
            });

        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

            r = row;
            return btn;
        }


    }

    //重定义添加
    public class AddUI  extends JFrame {
        //用户类
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

        public AddUI()
        {

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
            t4.addItem("管理员" +
                    "");
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


                        //以下是和后端的通讯
                        try {
                            //传输命令
                            outputStream.reset();
                            outputStream.writeInt(CMD);
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
                            if (oper.getSuccess()) {

                                JOptionPane.showMessageDialog(null, "用户添加成功");

                                close();

                            } else {
                                JOptionPane.showMessageDialog(null, "用户添加失败", "",JOptionPane.WARNING_MESSAGE);

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




    }
    //表格渲染
    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            component.setFont(new Font("华文仿宋", Font.BOLD, 24));
            // 自定义颜色设置
            if (isSelected) {
                // 当前行被选中时的颜色
                component.setBackground(Color.WHITE);
                component.setForeground(Color.BLACK);
            } else {
                // 其他行的颜色
                component.setBackground(new Color(0, 0, 0, 0)); // 设置背景透明
                component.setForeground(Color.WHITE);
            }

            return component;
        }
    };


}





