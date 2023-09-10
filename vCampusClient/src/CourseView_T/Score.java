/*
 * Created by JFormDesigner on Thu Sep 07 12:13:08 CST 2023
 */

package CourseView_T;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Score extends JFrame{
    nameList list;

    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Score(nameList list) {
        beautify();
        initComponents();
        this.list=list;

        String cid_s=Integer.toString(list.course.getCrsId());
        cid.setText(cid_s);
        cid.setEditable(false);
        cname.setText(list.course.getCrsName());
        cname.setEditable(false);

        String pingshi_s=Double.toString(list.scoreArray[0]*100);
        pingshi.setText(pingshi_s);
        String qizhong_s=Double.toString(list.scoreArray[1]*100);
        qizhong.setText(qizhong_s);
        String qimo_s=Double.toString(list.scoreArray[2]*100);
        qimo.setText(qimo_s);

    }



    private void addconfimMouseClicked(MouseEvent e) {
        // TODO add your code here

        if (pingshi.getText() == null||qizhong.getText()==null||qimo.getText()==null) {
            JOptionPane.showMessageDialog(this, "请输入正确占比！");
        }

        String usual_s = pingshi.getText();
        Double usual = Double.parseDouble(usual_s)/100;
        String midterm_s = qizhong.getText();
        Double midterm = Double.parseDouble(midterm_s)/100;
        String finalTerm_s = qimo.getText();
        Double finalTerm = Double.parseDouble(finalTerm_s)/100;

        Double[] array = {usual, midterm, finalTerm};

        if(usual+midterm+finalTerm==1) {
            list.scoreArray = array;
            JOptionPane.showMessageDialog(this, "输入占比成功！");
        }
        else {
            JOptionPane.showMessageDialog(this, "请输入正确占比！");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        addItem = new JFrame();
        MODIFY = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        cid = new JTextField();
        cname = new JTextField();
        pingshi = new JTextField();
        qizhong = new JTextField();
        qimo = new JTextField();
        addconfim = new JButton();
        addcancel = new JButton();
        label6 = new JLabel();

        //======== addItem ========
        {
            addItem.setPreferredSize(new Dimension(400, 400));
            addItem.setVisible(true);
            Container addItemContentPane = addItem.getContentPane();
            addItemContentPane.setLayout(null);

            //======== MODIFY ========
            {
                MODIFY.setPreferredSize(new Dimension(333, 331));
                MODIFY.setForeground(new Color(0x2b2d30));

                //---- label1 ----
                label1.setText("\u8bfe\u7a0b\u7f16\u53f7");
                label1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 18));

                //---- label2 ----
                label2.setText("\u8bfe\u7a0b\u540d\u79f0");
                label2.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 18));

                //---- label3 ----
                label3.setText("\u5e73\u65f6\u6210\u7ee9\u5360\u6bd4\uff08%\uff09");
                label3.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 18));

                //---- label4 ----
                label4.setText("\u671f\u4e2d\u6210\u7ee9\u5360\u6bd4\uff08%)");
                label4.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 18));

                //---- label5 ----
                label5.setText("\u671f\u672b\u6210\u7ee9\u5360\u6bd4\uff08%\uff09");
                label5.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 18));

                //---- addconfim ----
                addconfim.setText("\u786e\u8ba4");
                addconfim.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 18));
                addconfim.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        addconfimMouseClicked(e);
                    }
                });

                //---- addcancel ----
                addcancel.setText("\u53d6\u6d88");
                addcancel.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 18));

                GroupLayout MODIFYLayout = new GroupLayout(MODIFY);
                MODIFY.setLayout(MODIFYLayout);
                MODIFYLayout.setHorizontalGroup(
                    MODIFYLayout.createParallelGroup()
                        .addGroup(MODIFYLayout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addComponent(addconfim)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                            .addComponent(addcancel)
                            .addGap(48, 48, 48))
                        .addGroup(MODIFYLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(MODIFYLayout.createParallelGroup()
                                .addGroup(MODIFYLayout.createSequentialGroup()
                                    .addGroup(MODIFYLayout.createParallelGroup()
                                        .addGroup(MODIFYLayout.createSequentialGroup()
                                            .addComponent(label4)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(label5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(MODIFYLayout.createParallelGroup()
                                        .addComponent(qizhong, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                        .addComponent(qimo, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                                    .addContainerGap())
                                .addGroup(MODIFYLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addGroup(MODIFYLayout.createSequentialGroup()
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(cid, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(MODIFYLayout.createSequentialGroup()
                                        .addGap(91, 91, 91)
                                        .addComponent(cname, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(MODIFYLayout.createSequentialGroup()
                                        .addComponent(label3)
                                        .addGap(21, 21, 21)
                                        .addComponent(pingshi)))))
                );
                MODIFYLayout.setVerticalGroup(
                    MODIFYLayout.createParallelGroup()
                        .addGroup(MODIFYLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(MODIFYLayout.createParallelGroup()
                                .addGroup(MODIFYLayout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(label1))
                                .addComponent(cid, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                            .addGap(12, 12, 12)
                            .addGroup(MODIFYLayout.createParallelGroup()
                                .addComponent(cname, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGroup(MODIFYLayout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(label2)))
                            .addGroup(MODIFYLayout.createParallelGroup()
                                .addGroup(MODIFYLayout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(label3))
                                .addGroup(MODIFYLayout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addComponent(pingshi, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(MODIFYLayout.createParallelGroup()
                                .addGroup(MODIFYLayout.createSequentialGroup()
                                    .addGap(24, 24, 24)
                                    .addComponent(label4))
                                .addGroup(MODIFYLayout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addComponent(qizhong, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(MODIFYLayout.createParallelGroup()
                                .addGroup(MODIFYLayout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(label5))
                                .addGroup(MODIFYLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(qimo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
                            .addGap(26, 26, 26)
                            .addGroup(MODIFYLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addconfim)
                                .addComponent(addcancel)))
                );
            }
            addItemContentPane.add(MODIFY);
            MODIFY.setBounds(new Rectangle(new Point(26, 0), MODIFY.getPreferredSize()));

            //---- label6 ----
            label6.setIcon(new ImageIcon(getClass().getResource("/pic/\u6885\u5eb5.jpg")));
            label6.setForeground(new Color(0x2b2d30));
            addItemContentPane.add(label6);
            label6.setBounds(new Rectangle(new Point(0, 0), label6.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < addItemContentPane.getComponentCount(); i++) {
                    Rectangle bounds = addItemContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = addItemContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                addItemContentPane.setMinimumSize(preferredSize);
                addItemContentPane.setPreferredSize(preferredSize);
            }
            addItem.pack();
            addItem.setLocationRelativeTo(addItem.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JFrame addItem;
    private JPanel MODIFY;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JTextField cid;
    private JTextField cname;
    private JTextField pingshi;
    private JTextField qizhong;
    private JTextField qimo;
    private JButton addconfim;
    private JButton addcancel;
    private JLabel label6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
