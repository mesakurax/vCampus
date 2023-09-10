/*
 * Created by JFormDesigner on Mon Sep 04 16:39:53 CST 2023
 */

package StudentRollview;

import entity.StudentRoll;
import entity.User;
import module.StudentData;
import utils.SocketHelper;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.*;
import java.util.Random;

/**
 * @author OverSky
 */
public class PersonalInfo extends JPanel {
    private SocketHelper socketHelper;
    private StudentRoll studentRoll;
    private String[] text;
    public PersonalInfo(User info, SocketHelper socketHelper) {
        text = new String[]{"山黛远，月波长，暮云秋影蘸潇湘。",
                "风软一江水，云轻九子山。",
                "已识乾坤大，犹怜草木青。",
                "灭烛怜光满，披衣觉露滋。",
                "长安一片月，万户捣衣声。",
                "可怜今夕月，向何处，去悠悠？",
                "从此无心爱良夜，任他明月下西楼。",
                "莺嘴啄花红溜，燕尾点波绿皱。",
                "竹叶于人既无分，菊花从此不须开。",
                "两鬓可怜青，只为相思老。",
                "山月不知心里事，水风空落眼前花，摇曳碧云斜。",
                "当时明月在，曾照彩云归。",
                "夜月一帘幽梦，春风十里柔情。",
                "渭北春天树，江东日暮云。",
                "当时明月在,琵琶弦上说相思。",
                "晚来天欲雪，能饮一杯无。",
                "清明时节雨纷纷，路上行人欲断魂。",
                "人生若只如初见，何事秋风悲画扇？",
                "桃花潭水深千尺，不及汪伦送我情。",
                "落霞与孤鹜齐飞，秋水共长天一色。"};

        initComponents();
        this.socketHelper = socketHelper;
        this.studentRoll = new StudentData(socketHelper).Display1(info);
        name.setText(info.getName());

        if (info.getOccupation().equals("学生")) Id.setText("学号: "+ info.getId());
        else Id.setText("工号: "+ info.getId());

        Department.setText("学院: " + info.getAcademy());
        Random random = new Random();
        int randomIndex = random.nextInt(text.length); // 生成0到数组长度之间的随机索引
        String randomString = "<html>" + text[randomIndex] + "</html>";
        saying.setText(randomString);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        name = new JLabel();
        saying = new JLabel();
        Id = new JLabel();
        Department = new JLabel();

        //======== this ========
        setBackground(Color.white);

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/StudentRollview/studentRollPic/purpleline.png")));

        //---- label2 ----
        label2.setIcon(new ImageIcon(getClass().getResource("/StudentRollview/studentRollPic/redline.png")));

        //---- name ----
        name.setForeground(Color.black);
        name.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 28));
        name.setText("text");

        //---- saying ----
        saying.setFont(new Font("\u534e\u6587\u884c\u6977", Font.PLAIN, 25));
        saying.setForeground(Color.black);

        //---- Id ----
        Id.setText("text");
        Id.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 26));

        //---- Department ----
        Department.setText("text");
        Department.setFont(new Font("\u534e\u6587\u6977\u4f53", Font.PLAIN, 26));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(label2)
                        .addComponent(label1))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap(215, Short.MAX_VALUE)
                            .addComponent(saying, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(name, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                            .addGap(66, 66, 66)
                            .addGroup(layout.createParallelGroup()
                                .addComponent(Department, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                                .addComponent(Id, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))))
                    .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(name, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                        .addComponent(Id, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Department, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(saying, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(label2))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel name;
    private JLabel saying;
    private JLabel Id;
    private JLabel Department;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
