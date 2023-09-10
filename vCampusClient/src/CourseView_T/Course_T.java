/*
 * Created by JFormDesigner on Wed Sep 06 16:07:08 CST 2023
 */

package CourseView_T;

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

/**
 * @author 13352
 */
public class Course_T extends JPanel {
    CourseSystem model;
    User userInfo;

    public Course course;

    CourseTable courseTable;//记录表格数据


    public SocketHelper socketHelper;

    int num=0;
    CourseTable rs;
    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Course_T(User userInfo, SocketHelper socketHelper) {
        //beautify();
        initComponents();

        new UIStyler().setTextField(textField1);
        new UIStyler().setCombobox(comboBox1);
        new UIStyler().setBelowButton(button3);//设置下方按钮格式
        new UIStyler().setTransparentTable(scrollPane1);//设置表格格式

        this.socketHelper = socketHelper;
        this.userInfo = userInfo;
        model=new CourseSystem(this.socketHelper);
        refreshTable();
    }
    public void refreshTable() {
        button3.setText("查询");
        textField1.setText("");//必须加
        // 从 model 中请求记录
        Course course=new Course(-1,"", "", "", "", "", userInfo.getId(), userInfo.getName(), -1,-1);
        courseTable = model.CourseSearch(course,4);
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
                        Course course=new Course(number,"", "", "", "", "", userInfo.getId(), " ", -1,-1);
                        System.out.println("开始查找");
                        rs = model.CourseSearch(course, 5);
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
                    if (flag.equals("按课程名称查询")) {
                        Course course=new Course(-1,text, "", "", "", "",  userInfo.getId(), " ", -1,-1);
                        System.out.println("开始查找");
                        rs = model.CourseSearch(course, 7);
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

    public void table1MouseClicked(MouseEvent e) {
        // TODO add your code here
        int rowNum=table1.getSelectedRow();
        if(num==0) {
            course=courseTable.getCourseVector().get(rowNum);
        }
        else {
            course=rs.getCourseVector().get(rowNum);
        }

    }

    public Course getCourse()
    {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选择一行课程信息");
            return null;
        }
        else {
            return course;
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel2 = new JPanel();
        button3 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label4 = new JLabel();
        comboBox1 = new JComboBox<>();
        textField1 = new JTextField();
        label1 = new JLabel();

        //======== this ========
        setLayout(null);

        //======== panel2 ========
        {
            panel2.setBackground(new Color(0xdfe1e5));
            panel2.setPreferredSize(new Dimension(1685, 830));
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
            textField1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
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
    private JScrollPane scrollPane1;
    private JTable table1;
    private JLabel label4;
    private JComboBox<String> comboBox1;
    private JTextField textField1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
