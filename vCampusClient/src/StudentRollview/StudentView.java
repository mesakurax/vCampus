package StudentRollview;

import entity.StudentRoll;
import entity.User;
import module.StudentData;
import utils.SocketHelper;

import javax.swing.*;
import java.awt.*;

public class StudentView extends JPanel {
    public User user;
    private SocketHelper socketHelper;

    public StudentView(User uu, SocketHelper socketHelper) {
        initComponents();
        this.user=uu;
        this.socketHelper=socketHelper;
        StudentRoll sr=new StudentData(this.socketHelper).Display1(this.user);
        textField1.setText(sr.getStuName());
        textField2.setText(sr.getsSex());
        textField3.setText(sr.getStuId());
        textField4.setText(sr.getsGrades());
        textField5.setText(sr.getsPlace());
        textField6.setText(sr.getsDepart());
        textField7.setText(sr.getsProfession());
    }



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SocketHelper skhp= new SocketHelper();
                    //	//
                    skhp.getConnection(skhp.ip ,skhp.port);
                    skhp.getOs().writeInt(1);
                    skhp.getOs().flush();
                    User uu = new User();
                    uu.setId("1");
                    JFrame frame = new JFrame();
                    frame.add(new StudentView(uu, skhp));
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();
        textField6 = new JTextField();
        textField7 = new JTextField();
        label8 = new JLabel();
        label10 = new JLabel();
        panel1 = new JPanel();
        label12 = new JLabel();
        label11 = new JLabel();
        label13 = new JLabel();
        label14 = new JLabel();
        label15 = new JLabel();
        label9 = new JLabel();

        //======== this ========
        setBackground(Color.white);
        setLayout(null);

        //---- label1 ----
        label1.setText("\u59d3\u540d");
        label1.setFont(new Font("\u6977\u4f53", label1.getFont().getStyle() & ~Font.BOLD, label1.getFont().getSize() + 20));
        add(label1);
        label1.setBounds(670, 195, 80, 50);

        //---- label2 ----
        label2.setText("\u6027\u522b");
        label2.setFont(new Font("\u6977\u4f53", label2.getFont().getStyle() & ~Font.BOLD, label2.getFont().getSize() + 20));
        add(label2);
        label2.setBounds(new Rectangle(new Point(670, 300), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u5b66\u53f7");
        label3.setFont(new Font("\u6977\u4f53", label3.getFont().getStyle(), label3.getFont().getSize() + 20));
        add(label3);
        label3.setBounds(new Rectangle(new Point(670, 400), label3.getPreferredSize()));

        //---- label4 ----
        label4.setText("\u5e74\u7ea7");
        label4.setFont(new Font("\u6977\u4f53", label4.getFont().getStyle(), label4.getFont().getSize() + 20));
        add(label4);
        label4.setBounds(670, 500, label4.getPreferredSize().width, 40);

        //---- label5 ----
        label5.setText("\u7c4d\u8d2f");
        label5.setFont(new Font("\u6977\u4f53", label5.getFont().getStyle(), label5.getFont().getSize() + 20));
        add(label5);
        label5.setBounds(670, 600, 75, label5.getPreferredSize().height);

        //---- label6 ----
        label6.setText("\u5b66\u9662");
        label6.setFont(new Font("\u6977\u4f53", label6.getFont().getStyle(), label6.getFont().getSize() + 20));
        add(label6);
        label6.setBounds(670, 705, label6.getPreferredSize().width, 55);

        //---- label7 ----
        label7.setText("\u4e13\u4e1a");
        label7.setFont(new Font("\u6977\u4f53", label7.getFont().getStyle(), label7.getFont().getSize() + 20));
        add(label7);
        label7.setBounds(new Rectangle(new Point(670, 800), label7.getPreferredSize()));

        //---- textField1 ----
        textField1.setFont(new Font("\u6977\u4f53", textField1.getFont().getStyle(), textField1.getFont().getSize() + 20));
        textField1.setEditable(false);
        add(textField1);
        textField1.setBounds(775, 195, 295, 55);

        //---- textField2 ----
        textField2.setFont(new Font("\u6977\u4f53", textField2.getFont().getStyle(), textField2.getFont().getSize() + 20));
        textField2.setEditable(false);
        add(textField2);
        textField2.setBounds(775, 300, 295, 55);

        //---- textField3 ----
        textField3.setFont(new Font("\u6977\u4f53", textField3.getFont().getStyle(), textField3.getFont().getSize() + 20));
        textField3.setEditable(false);
        add(textField3);
        textField3.setBounds(775, 400, 295, 55);

        //---- textField4 ----
        textField4.setFont(new Font("\u6977\u4f53", textField4.getFont().getStyle(), textField4.getFont().getSize() + 20));
        textField4.setEditable(false);
        add(textField4);
        textField4.setBounds(775, 500, 295, 55);

        //---- textField5 ----
        textField5.setFont(new Font("\u6977\u4f53", textField5.getFont().getStyle(), textField5.getFont().getSize() + 20));
        textField5.setEditable(false);
        add(textField5);
        textField5.setBounds(775, 600, 295, 55);

        //---- textField6 ----
        textField6.setFont(new Font("\u6977\u4f53", Font.PLAIN, 20));
        textField6.setEditable(false);
        add(textField6);
        textField6.setBounds(775, 700, 295, 55);

        //---- textField7 ----
        textField7.setFont(new Font("\u6977\u4f53", Font.PLAIN, 28));
        textField7.setEditable(false);
        add(textField7);
        textField7.setBounds(780, 800, 290, 55);

        //---- label8 ----
        label8.setIcon(new ImageIcon(getClass().getResource("/pic/H.jpg")));
        add(label8);
        label8.setBounds(330, 410, 275, 225);

        //---- label10 ----
        label10.setText("\u4e2a\u4eba\u5b66\u7c4d\u4fe1\u606f");
        label10.setFont(new Font("\u6977\u4f53", Font.BOLD, 60));
        label10.setIcon(new ImageIcon(getClass().getResource("/pic/gr.png")));
        add(label10);
        label10.setBounds(600, 65, 540, label10.getPreferredSize().height);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0xffe4b5));
            panel1.setLayout(null);

            //---- label12 ----
            label12.setIcon(new ImageIcon(getClass().getResource("/pic/4098.png")));
            panel1.add(label12);
            label12.setBounds(0, 5, 1650, 180);

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
        panel1.setBounds(0, -5, 1650, 185);

        //---- label11 ----
        label11.setIcon(new ImageIcon(getClass().getResource("/pic/leave.jpg")));
        add(label11);
        label11.setBounds(0, 560, 315, 412);

        //---- label13 ----
        label13.setIcon(new ImageIcon(getClass().getResource("/pic/leaves.png")));
        add(label13);
        label13.setBounds(0, 180, 500, 205);

        //---- label14 ----
        label14.setIcon(new ImageIcon(getClass().getResource("/pic/yellow1.jpg")));
        add(label14);
        label14.setBounds(5, 395, 205, 205);

        //---- label15 ----
        label15.setIcon(new ImageIcon(getClass().getResource("/pic/heap.jpg")));
        add(label15);
        label15.setBounds(1050, 790, 365, 180);

        //---- label9 ----
        label9.setIcon(new ImageIcon(getClass().getResource("/pic/trees(1).jpg")));
        add(label9);
        label9.setBounds(1280, 200, 430, 775);

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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JLabel label8;
    private JLabel label10;
    private JPanel panel1;
    private JLabel label12;
    private JLabel label11;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label9;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
