package StudentRollview;

import utils.UIStyler;
import entity.StudentRoll;
import module.StudentData;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.SocketHelper;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Brainrain
 */
public class AdModifyView extends JPanel {
    private SocketHelper socketHelper;
    StuAdmin p;

    private String optType;
    private StudentRoll stu;

    public AdModifyView(StudentRoll stu, SocketHelper ss, StuAdmin pp) {
        initComponents();
        button1.setLocation(1220,0);
        UIStyler.setBelowButton(button1);
        setOpaque(false);
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
//                Window window = SwingUtilities.windowForComponent(this);
//                if (window instanceof JFrame) {
//                    JFrame frameToClose = (JFrame) window;
//                    frameToClose.dispose();
//                }
                p.refreshTable();

            } else {
                JOptionPane.showMessageDialog(null, "保存失败！");
            }
        } else {
            JOptionPane.showMessageDialog(null, "缺少信息，无法保存！");
        }
        p.card.show(p.getCardPanel(),"p1");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
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
        setBackground(Color.black);
        setMinimumSize(new Dimension(1685, 648));
        setLayout(null);

        //---- button1 ----
        button1.setFont(new Font("\u6977\u4f53", button1.getFont().getStyle(), button1.getFont().getSize() + 10));
        button1.setForeground(Color.black);
        button1.setIcon(null);
        button1.setText("\u4fdd\u5b58");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        add(button1);
        button1.setBounds(1220, 10, 65, 65);

        //---- label1 ----
        label1.setText("\u59d3\u540d");
        label1.setFont(new Font("\u6977\u4f53", Font.PLAIN, 36));
        label1.setIcon(null);
        label1.setForeground(Color.white);
        add(label1);
        label1.setBounds(90, 155, 90, 75);

        //---- label2 ----
        label2.setText("\u6027\u522b");
        label2.setFont(new Font("\u6977\u4f53", Font.PLAIN, 36));
        label2.setForeground(Color.white);
        add(label2);
        label2.setBounds(new Rectangle(new Point(90, 410), label2.getPreferredSize()));

        //---- label3 ----
        label3.setText("\u5b66\u53f7");
        label3.setFont(new Font("\u6977\u4f53", Font.PLAIN, 36));
        label3.setForeground(Color.white);
        add(label3);
        label3.setBounds(new Rectangle(new Point(90, 285), label3.getPreferredSize()));

        //---- label4 ----
        label4.setText("\u5e74\u7ea7");
        label4.setFont(new Font("\u6977\u4f53", Font.PLAIN, 36));
        label4.setForeground(Color.white);
        add(label4);
        label4.setBounds(new Rectangle(new Point(645, 170), label4.getPreferredSize()));

        //---- label5 ----
        label5.setText("\u7c4d\u8d2f");
        label5.setFont(new Font("\u6977\u4f53", Font.PLAIN, 36));
        label5.setForeground(Color.white);
        add(label5);
        label5.setBounds(645, 285, 80, 50);

        //---- label6 ----
        label6.setText("\u5b66\u9662");
        label6.setFont(new Font("\u6977\u4f53", Font.PLAIN, 36));
        label6.setForeground(Color.white);
        add(label6);
        label6.setBounds(90, 55, 95, 70);

        //---- label7 ----
        label7.setText("\u4e13\u4e1a");
        label7.setFont(new Font("\u6977\u4f53", Font.PLAIN, 36));
        label7.setForeground(Color.white);
        add(label7);
        label7.setBounds(645, 55, 75, 65);

        //---- textField1 ----
        textField1.setFont(new Font("\u6977\u4f53", Font.PLAIN, 30));
        add(textField1);
        textField1.setBounds(195, 165, 320, 55);

        //---- textField2 ----
        textField2.setFont(new Font("\u6977\u4f53", Font.PLAIN, 30));
        add(textField2);
        textField2.setBounds(195, 400, 320, 55);

        //---- textField3 ----
        textField3.setFont(new Font("\u6977\u4f53", Font.PLAIN, 30));
        add(textField3);
        textField3.setBounds(195, 275, 320, 55);

        //---- textField4 ----
        textField4.setFont(new Font("\u6977\u4f53", Font.PLAIN, 30));
        add(textField4);
        textField4.setBounds(745, 165, 320, 55);

        //---- textField5 ----
        textField5.setFont(new Font("\u6977\u4f53", Font.PLAIN, 30));
        add(textField5);
        textField5.setBounds(745, 280, 320, 55);

        //---- textField6 ----
        textField6.setFont(new Font("\u6977\u4f53", Font.PLAIN, 30));
        add(textField6);
        textField6.setBounds(195, 60, 320, 55);

        //---- textField7 ----
        textField7.setFont(new Font("\u6977\u4f53", Font.PLAIN, 30));
        add(textField7);
        textField7.setBounds(745, 60, 320, 55);

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
