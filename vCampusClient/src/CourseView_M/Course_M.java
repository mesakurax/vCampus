/*
 * Created by JFormDesigner on Thu Sep 07 15:13:04 CST 2023
 */

package CourseView_M;


import beautyComponent.RoundedTextField;
import beautyComponent.TransparentTable;
import utils.UIStyler;
import entity.Course;
import entity.CourseTable;
import entity.User;
import module.CourseSystem;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.SocketHelper;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 13352
 */
public class Course_M extends JPanel {
    CourseSystem model;
    User userInfo;

    Course course;

    CourseTable courseTable;
    public SocketHelper socketHelper;

    int num=0;
    CourseTable rs;
    public static void beautify() {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Course_M(User userInfo, SocketHelper socketHelper) {
        //beautify();
        initComponents();
        new UIStyler().setTextField(textField1);
        new UIStyler().setCombobox(comboBox1);
        new UIStyler().setBelowButton(button1);//设置下方按钮格式
        new UIStyler().setBelowButton(button2);//设置下方按钮格式
        new UIStyler().setBelowButton(button3);//设置下方按钮格式
        new UIStyler().setBelowButton(button4);//设置下方按钮格式
        new UIStyler().setBelowButton(button5);//设置下方按钮格式
        new UIStyler().setBelowButton(button6);
        new UIStyler().setTransparentTable(scrollPane1);//设置表格格式

        this.socketHelper = socketHelper;
        this.userInfo = userInfo;
        model=new CourseSystem(this.socketHelper);
        refreshTable();

    }
    public void refreshTable() {
        button3.setText("查询");
        textField1.setText("");
        courseTable = model.CourseDis();
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0);
        if(courseTable != null) {
            for (int i = 0; i < courseTable.getCount(); i++) {
                Course temp = courseTable.getIndex(i);
                Object[] rowData = {temp.getCrsId(), temp.getCrsName(), temp.getCrsTime(), temp.getCrsRoom(), temp.getCrsDate(), temp.getMajor(), temp.getTeacherId(), temp.getTeacherName(), temp.getCrsSize(), temp.getCrsCSize()};
                tableModel.addRow(rowData); 
            }
        }
        else {
            System.out.println("CourseTable is null");
        }
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
        Add_adm adm =new Add_adm(socketHelper,this);
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

    private void CrsDeleteMouseClicked(MouseEvent e) {
        // TODO add your code here
        int selectedRow = table1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选择一行课程信息");
            return;
        }
        ObjectInputStream is;
        ObjectOutputStream os;

        is = socketHelper.getIs(); 
        os = socketHelper.getOs(); 


        try {
            os.writeInt(305);
            os.flush();
            os.writeObject(course);
            os.flush();

            try {
                int re = is.readInt(); 
                if (re == 3051) {

                    if(num==0)
                        refreshTable();
                    else {
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); 
                        for(int i=0;i<rs.getCount();i++){
                            if(rs.getIndex(i)!=course) {
                                Course temp = rs.getIndex(i);
                                Object[] rowData = {temp.getCrsId(), temp.getCrsName(), temp.getCrsTime(), temp.getCrsRoom(), temp.getCrsDate(), temp.getMajor(), temp.getTeacherId(), temp.getTeacherName(), temp.getCrsSize(), temp.getCrsCSize()};
                                tableModel.addRow(rowData); 
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(this, "删除成功");
                } else  {
                    JOptionPane.showMessageDialog(this, "删除失败");
                }
            } catch (Exception ess) {
                ess.printStackTrace();
            }
        } catch (IOException ess) {
            ess.printStackTrace();
        }
    }

    private void modifyMouseClicked(MouseEvent e)
    {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选择一行课程信息");
            return;
        }
        Modify_adm adm =new Modify_adm(socketHelper,this);
    }

    private void sortMouseClicked(MouseEvent e) {
        // TODO add your code here
        ObjectInputStream is;
        ObjectOutputStream os;

        is = socketHelper.getIs();
        os = socketHelper.getOs();

        CourseTable courseTable=model.CourseDis();
        //未排课迭代器
        Vector<Course> Course_NotArr=new Vector<>();

        //排课迭代器
        Vector<int[][]> CourseArrangeV=new Vector<int[][]>();
        for(int i=0;i<18;i++){
            int[][] temp=new int[5][5];
            for(int j=0;j<5;j++)
            {
                for(int n=0;n<5;n++)
                {
                    temp[j][n]=0;
                }
            }
            CourseArrangeV.add(temp);
        }
        for(int i=0;i<courseTable.getCourseVector().size();i++){
            int week=-1;
            int time=-1;
            int address=-1;
            if(courseTable.getCourseVector().get(i).getCrsDate().equals("待排课")||courseTable.getCourseVector().get(i).getCrsTime().equals("待排课")||courseTable.getCourseVector().get(i).getCrsRoom().equals("待排课")){
                Course_NotArr.add(courseTable.getCourseVector().get(i));
            }
            else{
                switch (courseTable.getCourseVector().get(i).getCrsDate()){
                    case "周一":week=0;break;
                    case "周二":week=1;break;
                    case "周三":week=2;break;
                    case "周四":week=3;break;
                    case "周五":week=4;break;
                    default:continue;
                }
                switch (courseTable.getCourseVector().get(i).getCrsTime()){
                    case "第1-2节":time=0;break;
                    case "第3-4节":time=1;break;
                    case "第5-6节":time=2;break;
                    case "第7-8节":time=3;break;
                    case "第9-10节":time=4;break;
                    default:continue;
                }
                switch (courseTable.getCourseVector().get(i).getCrsRoom()){
                    case "教一101":address=0;break;
                    case "教一102":address=1;break;
                    case "教一103":address=2;break;
                    case "教一104":address=3;break;
                    case "教一105":address=4;break;
                    case "教一106":address=5;break;
                    case "教二201":address=6;break;
                    case "教二202":address=7;break;
                    case "教二203":address=8;break;
                    case "教二204":address=9;break;
                    case "教二205":address=10;break;
                    case "教二106":address=11;break;
                    case "教三301":address=12;break;
                    case "教三302":address=13;break;
                    case "教三303":address=14;break;
                    case "教三304":address=15;break;
                    case "教三305":address=16;break;
                    case "教三306":address=17;break;
                    default:continue;
                }
                CourseArrangeV.get(address)[week][time]=1;
            }
        }


        for(int i=0;i<Course_NotArr.size();i++){
            Random R=new Random();//产生随机数
            int w;
            int t;
            int a;
            int flag=-1;
            //日期待排
            if(Course_NotArr.get(i).getCrsDate().equals("待排课")){
                w=R.nextInt(5);//生成0-4随机数
            }else{
                switch (Course_NotArr.get(i).getCrsDate()){
                    case "周一":w=0;break;
                    case "周二":w=1;break;
                    case "周三":w=2;break;
                    case "周四":w=3;break;
                    case "周五":w=4;break;
                    default:continue;
                }
            }
            if(Course_NotArr.get(i).getCrsTime().equals("待排课")){
                t=R.nextInt(5);
            }else{
                switch (courseTable.getCourseVector().get(i).getCrsTime()){
                    case "第1-2节":t=0;break;
                    case "第3-4节":t=1;break;
                    case "第5-6节":t=2;break;
                    case "第7-8节":t=3;break;
                    case "第9-10节":t=4;break;
                    default:continue;
                }
            }
            if(Course_NotArr.get(i).getCrsRoom().equals("待排课")){
                a=R.nextInt(18);
            }else{
                switch (courseTable.getCourseVector().get(i).getCrsRoom()){
                    case "教一101":a=0;break;
                    case "教一102":a=1;break;
                    case "教一103":a=2;break;
                    case "教一104":a=3;break;
                    case "教一105":a=4;break;
                    case "教一106":a=5;break;
                    case "教二201":a=6;break;
                    case "教二202":a=7;break;
                    case "教二203":a=8;break;
                    case "教二204":a=9;break;
                    case "教二205":a=10;break;
                    case "教二106":a=11;break;
                    case "教三301":a=12;break;
                    case "教三302":a=13;break;
                    case "教三303":a=14;break;
                    case "教三304":a=15;break;
                    case "教三305":a=16;break;
                    case "教三306":a=17;break;
                    default:continue;
                }
            }

            //遍历 CourseArrangeV 中的教室，找到一个可用的教室（即 CourseArrangeV.get(a)[w][t] 为 0），并将课程安排在该教室
            for(int j=0;j<17;j++){
                if(CourseArrangeV.get(a)[w][t]==0){
                    flag=1;//教室被占用
                    break;
                }
                a=j;//将合适的教室给该课
            }

            //如果未找到可用的教室，代码进入循环，不断生成新的随机数，直到找到可用的教室为止。
            if(flag==-1){
                while(CourseArrangeV.get(a)[w][t]==1){
                    w=R.nextInt(5);
                    t=R.nextInt(5);
                    a=R.nextInt(18);
                }
            }

            //将教室各时间段标记为有课
            CourseArrangeV.get(a)[w][t]=1;
            String week="";
            String time="";
            String address="";
            switch (w){
                case 0:week="周一";break;
                case 1:week="周二";break;
                case 2:week="周三";break;
                case 3:week="周四";break;
                case 4:week="周五";break;
                default:continue;
            }
            switch (t){
                case 0:time="第1-2节";break;
                case 1:time="第3-4节";break;
                case 2:time="第5-6节";break;
                case 3:time="第7-8节";break;
                case 4:time="第9-10节";break;
                default:continue;
            }
            switch (a){
                case 0:address="教一101";break;
                case 1:address="教一102";break;
                case 2:address="教一103";break;
                case 3:address="教一104";break;
                case 4:address="教一105";break;
                case 5:address="教一106";break;
                case 6:address="教二201";break;
                case 7:address="教二202";break;
                case 8:address="教二203";break;
                case 9:address="教二204";break;
                case 10:address="教二205";break;
                case 11:address="教二206";break;
                case 12:address="教三301";break;
                case 13:address="教三302";break;
                case 14:address="教三303";break;
                case 15:address="教三304";break;
                case 16:address="教三305";break;
                case 17:address="教三306";break;
                default:continue;
            }
            Course_NotArr.get(i).setCrsDate(week);
            Course_NotArr.get(i).setCrsTime(time);
            Course_NotArr.get(i).setCrsRoom(address);
            try {
                os.writeInt(304); // 发送选择课程的请求码
                os.flush();
                os.writeObject(Course_NotArr.get(i)); // 发送课程信息
                os.flush();

                try {
                    int r = is.readInt(); // 接收服务器返回的结果码
                    if (r == 3041) {
                        refreshTable();
                        JOptionPane.showMessageDialog(this, "排课成功！");
                    } else{

                        JOptionPane.showMessageDialog(this, "排课失败!");
                    }
                } catch (Exception ess) {
                    ess.printStackTrace();
                }
            } catch (IOException ess) {
                ess.printStackTrace();
            }

        }
    }

    public JTable getTable()
    {
        return table1;
    }

    private void button6MouseClicked() {
        // TODO add your code here
        refreshTable();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label3 = new JLabel();
        button3 = new JButton();
        button4 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        comboBox1 = new JComboBox<>();
        textField1 = new JTextField();
        button5 = new JButton();
        button6 = new JButton();
        label4 = new JLabel();
        panel2 = new JPanel();
        label2 = new JLabel();
        label1 = new JLabel();

        //======== this ========
        setBackground(new Color(0xdfe1e5));
        setPreferredSize(new Dimension(1685, 1030));
        setLayout(null);

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(1685, 1030));
            panel1.setBackground(new Color(0xdfe1e5));
            panel1.setLayout(null);

            //---- label3 ----
            label3.setText("\u67e5\u8be2\u6761\u4ef6");
            label3.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            label3.setForeground(Color.black);
            panel1.add(label3);
            label3.setBounds(130, 50, 100, 50);

            //---- button3 ----
            button3.setText("\u67e5\u8be2");
            button3.setPreferredSize(new Dimension(100, 50));
            button3.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            button3.setDefaultCapable(false);
            button3.setForeground(new Color(0x2b2d30));
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CrsQueryMouseClicked(e);
                }
            });
            panel1.add(button3);
            button3.setBounds(690, 50, 150, 50);

            //---- button4 ----
            button4.setText("\u65b0\u589e\u8bfe\u7a0b");
            button4.setPreferredSize(new Dimension(100, 50));
            button4.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            button4.setForeground(new Color(0x2b2d30));
            button4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CrsAddMouseClicked(e);
                }
            });
            panel1.add(button4);
            button4.setBounds(850, 50, 155, 50);

            //======== scrollPane1 ========
            {
                scrollPane1.setPreferredSize(new Dimension(1000, 500));

                //---- table1 ----
                table1.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, null, null, "", null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null, null},
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
            panel1.add(scrollPane1);
            scrollPane1.setBounds(125, 130, 1435, 630);

            //---- button1 ----
            button1.setText("\u5220\u9664\u8bfe\u7a0b");
            button1.setPreferredSize(new Dimension(100, 50));
            button1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            button1.setForeground(new Color(0x2b2d30));
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    CrsDeleteMouseClicked(e);
                }
            });
            panel1.add(button1);
            button1.setBounds(1015, 50, 150, 50);

            //---- button2 ----
            button2.setText("\u4fee\u6539\u8bfe\u7a0b");
            button2.setPreferredSize(new Dimension(100, 50));
            button2.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            button2.setForeground(new Color(0x2b2d30));
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    modifyMouseClicked(e);
                }
            });
            panel1.add(button2);
            button2.setBounds(1175, 50, 150, 50);

            //---- comboBox1 ----
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u6309\u8bfe\u7a0b\u7f16\u53f7\u67e5\u8be2",
                "\u6309\u8bfe\u7a0b\u540d\u79f0\u67e5\u8be2"
            }));
            comboBox1.setPreferredSize(new Dimension(200, 50));
            comboBox1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            comboBox1.setForeground(new Color(0x2b2d30));
            panel1.add(comboBox1);
            comboBox1.setBounds(255, 50, 250, 50);

            //---- textField1 ----
            textField1.setPreferredSize(new Dimension(150, 50));
            textField1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            textField1.setForeground(new Color(0x2b2d30));
            panel1.add(textField1);
            textField1.setBounds(530, 50, 150, 50);

            //---- button5 ----
            button5.setText("\u81ea\u52a8\u6392\u8bfe");
            button5.setPreferredSize(new Dimension(100, 50));
            button5.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            button5.setForeground(new Color(0x2b2d30));
            button5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    sortMouseClicked(e);
                }
            });
            panel1.add(button5);
            button5.setBounds(1335, 50, 150, 50);

            //---- button6 ----
            button6.setText("\u5237\u65b0");
            button6.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button6MouseClicked();
                }
            });
            panel1.add(button6);
            button6.setBounds(1495, 50, 150, 50);

            //---- label4 ----
            label4.setIcon(new ImageIcon(getClass().getResource("/pic/\u80cc\u666f2.png")));
            panel1.add(label4);
            label4.setBounds(new Rectangle(new Point(0, 0), label4.getPreferredSize()));

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
            panel2.setBackground(new Color(0x24321e));
            panel2.setLayout(null);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/pic/mlogo.png")));
            panel2.add(label2);
            label2.setBounds(new Rectangle(new Point(1330, 35), label2.getPreferredSize()));

            //---- label1 ----
            label1.setText("\u4e1c\u5357\u5927\u5b66\u8bfe\u7a0b\u7ba1\u7406\u7cfb\u7edf");
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 48));
            label1.setPreferredSize(new Dimension(1000, 80));
            label1.setBackground(Color.white);
            label1.setForeground(Color.white);
            panel2.add(label1);
            label1.setBounds(550, 50, 500, 50);

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
    private JPanel panel1;
    private JLabel label3;
    private JButton button3;
    private JButton button4;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JComboBox<String> comboBox1;
    private JTextField textField1;
    private JButton button5;
    private JButton button6;
    private JLabel label4;
    private JPanel panel2;
    private JLabel label2;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    SocketHelper socketHelper=new SocketHelper();
                    User temp = new User("S001",  "Alice", "john@example.com", "123456", "New York");
                    socketHelper.getConnection(socketHelper.ip,socketHelper.port);
                    socketHelper.getOs().writeInt(1);
                    socketHelper.getOs().flush();
                    Course_M stu = new Course_M(temp,socketHelper);

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
