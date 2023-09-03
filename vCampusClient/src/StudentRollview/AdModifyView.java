package StudentRollview;

import entity.StudentRoll;
import module.StudentData;
import utils.SocketHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Brainrain
 */
public class AdModifyView extends JFrame {
    private SocketHelper socketHelper;
    StuAdmin p;

    private String optType;
    private StudentRoll stu;

    public AdModifyView(StudentRoll stu, SocketHelper ss, StuAdmin pp) {
        initComponents();

        this.stu = stu;
        if (stu.getStuName() == "") optType = "add";  //根据传递的学生信息是否为空来判断操作类型
        else optType = "modify";

        p = pp;
        socketHelper = ss;

        textField1.setText(stu.getStuName());
        textField2.setText(stu.getsSex());
        textField3.setText(stu.getStuId());
        textField4.setText(stu.getsGrades());
        textField5.setText(stu.getsPlace());
        textField6.setText(stu.getsDepart());
        textField7.setText(stu.getsProfession());
    }


    private void button1MouseClicked(MouseEvent e) {
        String sName = textField1.getText();
        String sSex = textField2.getText();
        String sId = textField3.getText();
        String sGrade = textField4.getText();
        String sPlace = textField5.getText();
        String sDepart = textField6.getText();
        String sProfession = textField7.getText();

        if ((!sId.equals("")) && (!sName.equals("")) && (!sGrade.equals("")) && (!sPlace.equals("")) && (!sSex.equals("")) && (!sDepart.equals("")) && (!sProfession.equals(""))) {
            StudentRoll s = new StudentRoll();
            s.setStuId(sId);
            s.setStuName(sName);
            s.setsGrades(sGrade);
            s.setsPlace(sPlace);
            s.setsDepart(sDepart);
            s.setsProfession(sProfession);
            s.setsSex(sSex);
            boolean success = false;
            if(optType.equals("modify")){
                success = new StudentData(socketHelper).Modify(s);
            }
            else{
                success = new StudentData(socketHelper).Add(s);
            }
            if (success) {
                JOptionPane.showMessageDialog(null, "保存成功！");
                this.dispose();
                p.refreshTable();

            } else {
                JOptionPane.showMessageDialog(null, "保存失败！");
            }

        } else {
            JOptionPane.showMessageDialog(null, "缺少信息，无法保存！");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label9 = new JLabel();
        panel2 = new JPanel();
        button1 = new JButton();
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

        //======== this ========
        setIconImage(new ImageIcon(getClass().getResource("/pic/name.png")).getImage());
        setBackground(Color.white);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x9a9c99));
            panel1.setLayout(null);

            //---- label9 ----
            label9.setText("\u5b66\u7c4d\u4fe1\u606f\u53d8\u52a8");
            label9.setFont(new Font("\u6977\u4f53", Font.BOLD, 50));
            label9.setIcon(new ImageIcon(getClass().getResource("/pic/system.png")));
            panel1.add(label9);
            label9.setBounds(90, 35, 440, 95);

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
        contentPane.add(panel1);
        panel1.setBounds(-15, 0, 640, 175);

        //======== panel2 ========
        {
            panel2.setBackground(Color.white);
            panel2.setLayout(null);

            //---- button1 ----
            button1.setText("\u4fdd\u5b58");
            button1.setFont(new Font("\u6977\u4f53", button1.getFont().getStyle(), button1.getFont().getSize() + 10));
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            panel2.add(button1);
            button1.setBounds(new Rectangle(new Point(270, 545), button1.getPreferredSize()));

            //---- label1 ----
            label1.setText("\u59d3\u540d");
            label1.setFont(new Font("\u6977\u4f53", label1.getFont().getStyle() & ~Font.BOLD, label1.getFont().getSize() + 10));
            label1.setIcon(null);
            panel2.add(label1);
            label1.setBounds(110, 15, 70, 45);

            //---- label2 ----
            label2.setText("\u6027\u522b");
            label2.setFont(new Font("\u6977\u4f53", label2.getFont().getStyle(), label2.getFont().getSize() + 10));
            panel2.add(label2);
            label2.setBounds(new Rectangle(new Point(110, 100), label2.getPreferredSize()));

            //---- label3 ----
            label3.setText("\u5b66\u53f7");
            label3.setFont(new Font("\u6977\u4f53", label3.getFont().getStyle(), label3.getFont().getSize() + 10));
            panel2.add(label3);
            label3.setBounds(new Rectangle(new Point(110, 175), label3.getPreferredSize()));

            //---- label4 ----
            label4.setText("\u5e74\u7ea7");
            label4.setFont(new Font("\u6977\u4f53", label4.getFont().getStyle(), label4.getFont().getSize() + 10));
            panel2.add(label4);
            label4.setBounds(new Rectangle(new Point(110, 250), label4.getPreferredSize()));

            //---- label5 ----
            label5.setText("\u7c4d\u8d2f");
            label5.setFont(new Font("\u6977\u4f53", label5.getFont().getStyle(), label5.getFont().getSize() + 10));
            panel2.add(label5);
            label5.setBounds(new Rectangle(new Point(110, 325), label5.getPreferredSize()));

            //---- label6 ----
            label6.setText("\u5b66\u9662");
            label6.setFont(new Font("\u6977\u4f53", label6.getFont().getStyle(), label6.getFont().getSize() + 10));
            panel2.add(label6);
            label6.setBounds(new Rectangle(new Point(110, 400), label6.getPreferredSize()));

            //---- label7 ----
            label7.setText("\u4e13\u4e1a");
            label7.setFont(new Font("\u6977\u4f53", label7.getFont().getStyle(), label7.getFont().getSize() + 10));
            panel2.add(label7);
            label7.setBounds(new Rectangle(new Point(110, 475), label7.getPreferredSize()));

            //---- textField1 ----
            textField1.setFont(new Font("\u6977\u4f53", textField1.getFont().getStyle(), textField1.getFont().getSize() + 10));
            panel2.add(textField1);
            textField1.setBounds(175, 20, 275, 35);

            //---- textField2 ----
            textField2.setFont(new Font("\u6977\u4f53", textField2.getFont().getStyle(), textField2.getFont().getSize() + 10));
            panel2.add(textField2);
            textField2.setBounds(175, 95, 275, 35);

            //---- textField3 ----
            textField3.setFont(new Font("\u6977\u4f53", textField3.getFont().getStyle(), textField3.getFont().getSize() + 10));
            panel2.add(textField3);
            textField3.setBounds(175, 170, 275, 35);

            //---- textField4 ----
            textField4.setFont(new Font("\u6977\u4f53", textField4.getFont().getStyle(), textField4.getFont().getSize() + 10));
            panel2.add(textField4);
            textField4.setBounds(175, 245, 275, 35);

            //---- textField5 ----
            textField5.setFont(new Font("\u6977\u4f53", textField5.getFont().getStyle(), textField5.getFont().getSize() + 10));
            panel2.add(textField5);
            textField5.setBounds(175, 320, 275, 35);

            //---- textField6 ----
            textField6.setFont(new Font("\u6977\u4f53", textField6.getFont().getStyle(), textField6.getFont().getSize() + 10));
            panel2.add(textField6);
            textField6.setBounds(175, 395, 275, 35);

            //---- textField7 ----
            textField7.setFont(new Font("\u6977\u4f53", textField7.getFont().getStyle(), textField7.getFont().getSize() + 10));
            panel2.add(textField7);
            textField7.setBounds(175, 470, 275, 35);

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
        contentPane.add(panel2);
        panel2.setBounds(0, 175, 605, 605);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        setSize(610, 810);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label9;
    private JPanel panel2;
    private JButton button1;
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
