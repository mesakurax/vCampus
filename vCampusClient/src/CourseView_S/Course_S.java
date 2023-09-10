/*
 * Created by JFormDesigner on Tue Sep 05 12:33:09 CST 2023
 */

package CourseView_S;

import utils.UIStyler;
import entity.Course;
import entity.CourseTable;
import entity.User;
import module.CourseSystem;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.SocketHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author 13352
 */
public class Course_S extends JPanel {
    CourseSystem model;
    User userInfo;

    Course course;

    CourseTable courseTable;//记录表格数据
    public SocketHelper socketHelper;

    int num=0;
    CourseTable rs;//记录查询结果
    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Course_S(User userInfo, SocketHelper socketHelper) {
        //beautify();
        initComponents();

        new UIStyler().setTextField(textField1);
        new UIStyler().setCombobox(comboBox1);
        new UIStyler().setBelowButton(button3);//设置下方按钮格式
        new UIStyler().setBelowButton(button4);//设置下方按钮格式
        new UIStyler().setBelowButton(button5);//设置下方按钮格式
        new UIStyler().setTransparentTable(scrollPane1);//设置表格格式

        this.socketHelper = socketHelper;
        this.userInfo = userInfo;
        model=new CourseSystem(this.socketHelper);
        refreshTable();
    }

    private void CrsQueryMouseClicked(MouseEvent e) {
        // TODO add your code here
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("查询")) {
            // 执行查询的逻辑
            String flag = comboBox1.getSelectedItem().toString().trim();
            String text = textField1.getText();
            try {
                if(textField1.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "请输入查找内容", "查找失败", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    System.out.println(flag);
                    if (flag.equals("按课程编号查询")) {
                        int number = Integer.parseInt(text);
                        Course course=new Course(number,"", "", "", "", "", "-1", " ", -1,-1);
                        System.out.println("开始查找");
                        rs = model.CourseSearch(course, 2);
                        if (rs==null) {
                            textField1.setText("");//必须加
                            table1.clearSelection();
                            JOptionPane.showMessageDialog(null, "未找到匹配的课程", "查找失败", JOptionPane.ERROR_MESSAGE);
                        } else {
                            DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();//获取与表格关联的数据模型对象，使用 DefaultTableModel 类的方法来操作和管理表格的数据。
                            tableModel.setRowCount(0); // 清空表格数据
                            for(int i=0;i<rs.getCount();i++){
                                Course temp=rs.getIndex(i);
                                Object[] rowData = {temp.getCrsId(),temp.getCrsName(),temp.getCrsTime(),temp.getCrsRoom(),temp.getCrsDate(),temp.getMajor(),temp.getTeacherId(),temp.getTeacherName(),temp.getCrsSize(),temp.getCrsCSize()};
                                tableModel.addRow(rowData); // 添加行数据
                            }
                        }
                    }
                    if (flag.equals("按课程名称查询")) {
                        Course course=new Course(-1,text, "", "", "", "", "-1", " ", -1,-1);
                        System.out.println("开始查找");
                        rs = model.CourseSearch(course, 3);
                        if (rs==null) {
                            textField1.setText("");//必须加
                            table1.clearSelection();
                            JOptionPane.showMessageDialog(null, "未找到匹配的课程", "查找失败", JOptionPane.ERROR_MESSAGE);
                        } else {
                            DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();//获取与表格关联的数据模型对象，使用 DefaultTableModel 类的方法来操作和管理表格的数据。
                            tableModel.setRowCount(0); // 清空表格数据
                            for(int i=0;i<rs.getCount();i++){
                                Course temp=rs.getIndex(i);
                                Object[] rowData = {temp.getCrsId(),temp.getCrsName(),temp.getCrsTime(),temp.getCrsRoom(),temp.getCrsDate(),temp.getMajor(),temp.getTeacherId(),temp.getTeacherName(),temp.getCrsSize(),temp.getCrsCSize()};
                                tableModel.addRow(rowData); // 添加行数据
                            }
                        }
                    }
                    if (flag.equals("按老师编号查询")) {
                        Course course= new Course(-1, "", "", "", "", "", text, " ", -1, -1);
                        System.out.println("开始查找");
                        rs = model.CourseSearch(course, 4);
                        if (rs==null) {
                            textField1.setText("");//必须加
                            table1.clearSelection();
                            JOptionPane.showMessageDialog(null, "未找到匹配的课程", "查找失败", JOptionPane.ERROR_MESSAGE);
                        } else {
                            DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();//获取与表格关联的数据模型对象，使用 DefaultTableModel 类的方法来操作和管理表格的数据。
                            tableModel.setRowCount(0); // 清空表格数据
                            for (int i = 0; i < rs.getCount(); i++) {
                                Course temp = rs.getIndex(i);
                                Object[] rowData = {temp.getCrsId(), temp.getCrsName(), temp.getCrsTime(), temp.getCrsRoom(), temp.getCrsDate(), temp.getMajor(), temp.getTeacherId(), temp.getTeacherName(), temp.getCrsSize(), temp.getCrsCSize()};
                                tableModel.addRow(rowData); // 添加行数据
                            }
                            button.setText("返回");
                            num=1;
                        }
                    }
                    if (flag.equals("按老师名称查询")) {
                        Course course=new Course(-1,"", "", "", "", "", "-1", text, -1,-1);
                        System.out.println("开始查找");
                        rs = model.CourseSearch(course, 8);
                        if (rs==null) {
                            textField1.setText("");//必须加
                            table1.clearSelection();
                            JOptionPane.showMessageDialog(null, "未找到匹配的课程", "查找失败", JOptionPane.ERROR_MESSAGE);
                        } else {
                            DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();//获取与表格关联的数据模型对象，使用 DefaultTableModel 类的方法来操作和管理表格的数据。
                            tableModel.setRowCount(0); // 清空表格数据
                            for(int i=0;i<rs.getCount();i++){
                                Course temp=rs.getIndex(i);
                                Object[] rowData = {temp.getCrsId(),temp.getCrsName(),temp.getCrsTime(),temp.getCrsRoom(),temp.getCrsDate(),temp.getMajor(),temp.getTeacherId(),temp.getTeacherName(),temp.getCrsSize(),temp.getCrsCSize()};
                                tableModel.addRow(rowData); // 添加行数据
                            }
                            button.setText("返回");
                            num=1;
                        }
                    }
                }
            }catch (Exception a) {
                a.printStackTrace();
            }

        } else if (button.getText().equals("返回")) {
            // 执行返回的逻辑
            refreshTable();
            num=0;
        }

    }

    private void CrsAddMouseClicked(MouseEvent e) {
        // TODO add your code here
        int selectedRow = table1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选择一行课程信息");
            return;
        }
        ObjectInputStream is;
        ObjectOutputStream os;

        is = socketHelper.getIs(); // 从socketHelper获取ObjectInputStream对象
        os = socketHelper.getOs(); // 从socketHelper获取ObjectOutputStream对象

        boolean csr=model.IfChoose(userInfo,course);
        if(!csr) {
            // 选择课程
            CourseTable courseTable = model.ChooseDisplay(userInfo); // 获取课程表，打印选课记录里的课程
            if (courseTable != null) {
                for (int i = 0; i < courseTable.getCount(); i++) {
                    // 检查是否与已选课程时间冲突
                    if (courseTable.getIndex(i).getCrsDate().equals(course.getCrsDate()) && courseTable.getIndex(i).getCrsTime().equals(course.getCrsTime())) {
                        JOptionPane.showMessageDialog(this, "课程冲突！");
                        return;
                    }
                }
            }
            try {
                os.writeInt(302); // 发送选择课程的请求码
                os.flush();
                os.writeObject(userInfo); // 发送用户信息
                os.flush();
                os.writeObject(course); // 发送课程信息
                os.flush();

                try {
                    int r = is.readInt(); // 接收服务器返回的结果码
                    if (r == 3021) {
                        // 选课成功
                        if(num==0)
                            refreshTable();
                        else {
                            DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();//获取与表格关联的数据模型对象，使用 DefaultTableModel 类的方法来操作和管理表格的数据。
                            tableModel.setRowCount(0); // 清空表格数据
                            for(int i=0;i<rs.getCount();i++){
                                if(rs.getIndex(i)!=course) {
                                    Course temp = rs.getIndex(i);
                                    Object[] rowData = {temp.getCrsId(), temp.getCrsName(), temp.getCrsTime(), temp.getCrsRoom(), temp.getCrsDate(), temp.getMajor(), temp.getTeacherId(), temp.getTeacherName(), temp.getCrsSize(), temp.getCrsCSize()};
                                    tableModel.addRow(rowData); // 添加行数据
                                }
                                else {
                                    CourseTable temp1=model.CourseSearch(course,6);
                                    Course temp=temp1.getIndex(0);
                                    Object[] rowData = {temp.getCrsId(), temp.getCrsName(), temp.getCrsTime(), temp.getCrsRoom(), temp.getCrsDate(), temp.getMajor(), temp.getTeacherId(), temp.getTeacherName(), temp.getCrsSize(), temp.getCrsCSize()};
                                    tableModel.addRow(rowData); // 添加行数据
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(this, "选课成功");
                    } else if (r == 3022) {
                        // 选课失败，人数超过课程限制
                        JOptionPane.showMessageDialog(this, "选课失败！人数超过课程限制。");
                    }
                } catch (Exception ess) {
                    ess.printStackTrace();
                }
            } catch (IOException ess) {
                ess.printStackTrace();
            }
        }
        else
            JOptionPane.showMessageDialog(this, "该课程已在选课记录中！");

    }

    private void CrsDeleteMouseClicked(MouseEvent e) {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选择一行课程信息");
            return;
        }
        // TODO add your code here
        ObjectInputStream is;
        ObjectOutputStream os;

        is = socketHelper.getIs(); // 从socketHelper获取ObjectInputStream对象
        os = socketHelper.getOs(); // 从socketHelper获取ObjectOutputStream对象

        boolean csr=model.IfChoose(userInfo,course);
        if(csr) {
            // 退选课程
            try {
                os.writeInt(303); // 发送退选课程的请求码
                os.flush();
                os.writeObject(userInfo); // 发送用户信息
                os.flush();
                os.writeObject(course); // 发送课程信息
                os.flush();

                try {
                    int re = is.readInt(); // 接收服务器返回的结果码
                    if (re == 3031) {
                        // 退选成功
                        if(num==0)
                            refreshTable();
                        else {
                            DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();//获取与表格关联的数据模型对象，使用 DefaultTableModel 类的方法来操作和管理表格的数据。
                            tableModel.setRowCount(0); // 清空表格数据
                            for(int i=0;i<rs.getCount();i++){
                                if(rs.getIndex(i)!=course) {
                                    Course temp = rs.getIndex(i);
                                    Object[] rowData = {temp.getCrsId(), temp.getCrsName(), temp.getCrsTime(), temp.getCrsRoom(), temp.getCrsDate(), temp.getMajor(), temp.getTeacherId(), temp.getTeacherName(), temp.getCrsSize(), temp.getCrsCSize()};
                                    tableModel.addRow(rowData); // 添加行数据
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(this, "退课成功！");
                    } else  {
                        // 退选失败
                        JOptionPane.showMessageDialog(this, "退课失败！");
                    }
                } catch (Exception ess) {
                    ess.printStackTrace();
                }
            } catch (IOException ess) {
                ess.printStackTrace();
            }
        }
        else
                JOptionPane.showMessageDialog(this, "该课程不在选课记录中!");
    }

    private void table1MouseClicked(MouseEvent e) {
        // TODO add your code here
        int rowNum=table1.getSelectedRow();
        if(num==0) {
        course=courseTable.getCourseVector().get(rowNum);
        }
        else {
            course=rs.getCourseVector().get(rowNum);
        }

    }

    public void refreshTable() {
        button3.setText("查询");
        textField1.setText("");//必须加
            // 从 model 中请求记录
            courseTable = model.CourseDis();
            // 获取表格模型
            DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
            // 清除表格数据
            tableModel.setRowCount(0);
            // 清空文本框内容
            textField1.setText("");
            // 遍历充值记录数组，添加到表格模型中
        if(courseTable != null) {
            for (int i = 0; i < courseTable.getCount(); i++) {
                Course temp = courseTable.getIndex(i);
                Object[] rowData = {temp.getCrsId(), temp.getCrsName(), temp.getCrsTime(), temp.getCrsRoom(), temp.getCrsDate(), temp.getMajor(), temp.getTeacherId(), temp.getTeacherName(), temp.getCrsSize(), temp.getCrsCSize()};
                tableModel.addRow(rowData); // 添加行数
            }
        }
        else {
            System.out.println("CourseTable is null");
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel2 = new JPanel();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label4 = new JLabel();
        label3 = new JLabel();
        comboBox1 = new JComboBox<>();
        textField1 = new JTextField();
        label1 = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(1685, 860));
        setLayout(null);

        //======== panel2 ========
        {
            panel2.setBackground(new Color(0xdfe1e5));
            panel2.setPreferredSize(new Dimension(1685, 815));
            panel2.setLayout(null);

            //---- button3 ----
            button3.setText("\u67e5\u8be2");
            button3.setPreferredSize(new Dimension(100, 50));
            button3.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            button3.setForeground(new Color(0x2b2d30));
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CrsQueryMouseClicked(e);
                }
            });
            panel2.add(button3);
            button3.setBounds(850, 50, 150, 50);

            //---- button4 ----
            button4.setText("\u9009\u8bfe");
            button4.setPreferredSize(new Dimension(100, 50));
            button4.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            button4.setForeground(new Color(0x2b2d30));
            button4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CrsAddMouseClicked(e);
                }
            });
            panel2.add(button4);
            button4.setBounds(1050, 50, 150, 50);

            //---- button5 ----
            button5.setText("\u9000\u8bfe");
            button5.setPreferredSize(new Dimension(100, 50));
            button5.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            button5.setForeground(new Color(0x2b2d30));
            button5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CrsDeleteMouseClicked(e);
                }
            });
            panel2.add(button5);
            button5.setBounds(1250, 50, 150, 50);

            //======== scrollPane1 ========
            {
                scrollPane1.setPreferredSize(new Dimension(1000, 500));

                //---- table1 ----
                table1.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, null, null, "", null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                    },
                    new String[] {
                        "\u8bfe\u7a0b\u7f16\u53f7", "\u8bfe\u7a0b\u540d\u79f0", "\u8bfe\u7a0b\u65f6\u95f4", "\u6559\u5ba4", "\u8bfe\u7a0b\u65e5\u671f", "\u5f00\u8bfe\u5b66\u9662", "\u8001\u5e08\u7f16\u53f7", "\u8001\u5e08\u59d3\u540d", "\u8bfe\u7a0b\u5bb9\u91cf", "\u8bfe\u7a0b\u5269\u4f59\u5bb9\u91cf"
                    }
                ));
                table1.setPreferredSize(new Dimension(1300, 750));
                table1.setRowHeight(40);
                table1.setFont(new Font("\u6977\u4f53", Font.BOLD, 22));
                table1.setForeground(Color.black);
                table1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        table1MouseClicked(e);
                    }
                });
                scrollPane1.setViewportView(table1);
            }
            panel2.add(scrollPane1);
            scrollPane1.setBounds(125, 150, 1435, 530);

            //---- label4 ----
            label4.setText("\u67e5\u8be2\u6761\u4ef6");
            label4.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            label4.setForeground(Color.black);
            panel2.add(label4);
            label4.setBounds(200, 50, 100, 50);

            //---- label3 ----
            label3.setText("\u67e5\u8be2\u6761\u4ef6");
            label3.setFont(new Font("\u6977\u4f53", Font.PLAIN, 20));
            panel2.add(label3);
            label3.setBounds(0, 1110, 150, 42);

            //---- comboBox1 ----
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u6309\u8bfe\u7a0b\u7f16\u53f7\u67e5\u8be2",
                "\u6309\u8bfe\u7a0b\u540d\u79f0\u67e5\u8be2"
            }));
            comboBox1.setPreferredSize(new Dimension(200, 50));
            comboBox1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            comboBox1.setForeground(new Color(0x2b2d30));
            panel2.add(comboBox1);
            comboBox1.setBounds(350, 50, 250, 50);

            //---- textField1 ----
            textField1.setPreferredSize(new Dimension(150, 50));
            textField1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 37));
            textField1.setForeground(new Color(0x2b2d30));
            panel2.add(textField1);
            textField1.setBounds(650, 50, 150, 50);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/pic/\u80cc\u666f2.png")));
            panel2.add(label1);
            label1.setBounds(new Rectangle(new Point(0, 0), label1.getPreferredSize()));

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
        panel2.setBounds(0, 0, 1685, 830);

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
    private JPanel panel2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label4;
    private JLabel label3;
    private JComboBox<String> comboBox1;
    private JTextField textField1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
