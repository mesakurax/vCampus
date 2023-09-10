/*
 * Created by JFormDesigner on Mon Sep 11 17:41:56 CST 2023
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
import java.util.Iterator;

/**
 * @author 13352
 */
public class Schedule extends JPanel {

    SocketHelper socketHelper;

    User user;
    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Schedule(User user,SocketHelper socketHelper) {
        //beautify();
        initComponents();

        new UIStyler().setTransparentTable(scrollPane1);//设置表格格式

        this.socketHelper=socketHelper;
        this.user=user;

        refresh();

    }

    public void refresh()
    {
        DefaultTableModel tableModel = (DefaultTableModel) CLASSTABLE.getModel();
        // 清除表格数据
        tableModel.setRowCount(0);
        String[] timeSlots = {"第1-2节", "第3-4节", "第5-6节", "第7-8节", "第9-10节"};

        for (String timeSlot : timeSlots) {
            Object[] rowData = {timeSlot, null, null, null, null, null};
            tableModel.addRow(rowData);
        }

        CourseSystem model=new CourseSystem(socketHelper);
        Course temp=new Course(-1,"","","","","",user.getId(),"",-1,-1);
        CourseTable courseTable=model.CourseSearch(temp,4);

        if(courseTable!=null) {
            Iterator<Course> it = courseTable.getCourseVector().iterator();
            System.out.println(courseTable.getCourseVector().size());

            while (it.hasNext()) {
                Course course = (Course) it.next();
                String date = course.getCrsDate();
                String time = course.getCrsTime();
                String info = "《" + course.getCrsName() + "》";
                System.out.println(info);
                System.out.println(date);

                switch (date) {
                    case "周一": {
                        int d = 1;//列

                        switch (time) {
                            case "第1-2节": {
                                int t = 0;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第3-4节": {
                                int t = 1;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第5-6节": {
                                int t = 2;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第7-8节": {
                                int t = 3;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第9-10节": {
                                int t = 4;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            default:
                                break;

                        }

                        break;
                    }

                    case "周二": {
                        int d = 2;

                        switch (time) {
                            case "第1-2节": {
                                int t = 0;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第3-4节": {
                                int t = 1;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第5-6节": {
                                int t = 2;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第7-8节": {
                                int t = 3;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第9-10节": {
                                int t = 4;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            default:
                                break;

                        }

                        break;
                    }

                    case "周三": {
                        int d = 3;

                        switch (time) {
                            case "第1-2节": {
                                int t = 0;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第3-4节": {
                                int t = 1;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第5-6节": {
                                int t = 2;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第7-8节": {
                                int t = 3;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第9-10节": {
                                int t = 4;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            default:
                                break;

                        }

                        break;
                    }

                    case "周四": {
                        int d = 4;

                        switch (time) {
                            case "第1-2节": {
                                int t = 0;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第3-4节": {
                                int t = 1;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第5-6节": {
                                int t = 2;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第7-8节": {
                                int t = 3;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第9-10节": {
                                int t = 4;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            default:
                                break;

                        }

                        break;
                    }

                    case "周五": {
                        int d = 5;
                        switch (time) {
                            case "第1-2节": {
                                int t = 0;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第3-4节": {
                                int t = 1;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第5-6节": {
                                int t = 2;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第7-8节": {
                                int t = 3;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            case "第9-10节": {
                                int t = 4;
                                CLASSTABLE.setValueAt(info, t, d);
                                break;
                            }

                            default:
                                break;

                        }

                        break;
                    }

                }
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        CLASSTABLE = new JTable();
        label1 = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(1685, 830));
        setBackground(new Color(0xdfe1e5));
        setLayout(null);

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(1685, 830));
            panel1.setBackground(Color.white);
            panel1.setLayout(null);

            //======== scrollPane1 ========
            {
                scrollPane1.setFont(new Font("\u6977\u4f53", Font.PLAIN, 18));

                //---- CLASSTABLE ----
                CLASSTABLE.setModel(new DefaultTableModel(
                    new Object[][] {
                        {"\u7b2c1-2\u8282", null, null, null, null, null},
                        {"\u7b2c3-4\u8282", null, null, null, null, null},
                        {"\u7b2c5-6\u8282", null, null, null, null, null},
                        {"\u7b2c7-8\u8282", null, null, null, null, null},
                        {"\u7b2c9-10\u8282", null, null, null, null, null},
                    },
                    new String[] {
                        "\u8282\u6b21\\\u661f\u671f", "\u5468\u4e00", "\u5468\u4e8c", "\u5468\u4e09", "\u5468\u56db", "\u5468\u4e94"
                    }
                ));
                CLASSTABLE.setRowHeight(100);
                CLASSTABLE.setFont(new Font("\u6977\u4f53", Font.BOLD, 20));
                scrollPane1.setViewportView(CLASSTABLE);
            }
            panel1.add(scrollPane1);
            scrollPane1.setBounds(200, 120, 1285, 530);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/pic/\u80cc\u666f2.png")));
            panel1.add(label1);
            label1.setBounds(new Rectangle(new Point(0, 0), label1.getPreferredSize()));

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
        panel1.setBounds(new Rectangle(new Point(0, 0), panel1.getPreferredSize()));

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
    private JScrollPane scrollPane1;
    private JTable CLASSTABLE;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
