/*
 * Created by JFormDesigner on Fri Sep 01 01:06:15 AWST 2023
 */

package bankSystemView;

import module.*;
import entity.*;
import utils.*;
import beautyComponent.*;
import utils.UIStyler;

import javax.swing.table.*;
import javax.swing.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author 22431
 */
public class userBankView extends JPanel {

    private User uu;
    private SocketHelper socketHelper;
    private bankSystem model;

    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public userBankView(SocketHelper socketHelper, User uu) {
        beautify();
        initComponents();
        this.uu=uu;
        this.socketHelper=socketHelper;
        model=new bankSystem(this.socketHelper);

        UIStyler.createHeader(this);
        UIStyler.setTopButton(button2);
        UIStyler.setTopButton(button1);
        UIStyler.setTransparentTable(scrollPane1);

        table1.setRowHeight(60);
/*       ;

        JTableHeader head = table1.getTableHeader(); // 创建表格标题对象
      // 设置表头大小*/

        refreshTable();
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String text = textField1.getText();
        try {
            double value = Double.parseDouble(text);
            rechargeRecord temp=new rechargeRecord();
            temp.setuId(uu.getId());
            temp.setAmount(value);
            temp.setuName(uu.getName());
            temp.setIsDispose(0);
            temp.setTime(Timehelp.getCurrentTime());
            if(model.user_addRecord(temp))
            {
                JOptionPane.showMessageDialog(this, "发送充值请求成功！");
                textField1.setText("");
                refreshTable();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "发送充值请求失败!！");
                textField1.setText("");}

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "请输入正确的金额格式！！");
            textField1.setText("");
        }
    }

    public void refreshTable() {
        rechargeRecord temp=new rechargeRecord();
        temp.setuId(uu.getId());
        rechargeRecord[] sss = model.user_queryRecord(temp);

        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.getDataVector().clear();   //清除表格数据

        table1.clearSelection();
        for (rechargeRecord record : sss) {
            String isDisposeText = (record.getIsDispose() == 0) ? "否" : "是";
            Object[] rowData = {
                    record.getrId(),
                    record.getuId(),
                    record.getuName(),
                    record.getAmount(),
                    isDisposeText,
                    record.getStatus(),
                    record.getTime(),
            };
            tableModel.addRow(rowData);
        }
        textField2.setText(String.valueOf(model.query_balance(uu)));
    }

    private void refreshMouseClicked() {
        // TODO add your code here
        refreshTable();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        textField1 = new JTextField();
        pamel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        label7 = new JLabel();
        textField2 = new JTextField();

        //======== this ========
        setPreferredSize(new Dimension(1685, 1030));
        setLayout(null);

        //---- textField1 ----
        textField1.setBackground(Color.lightGray);
        textField1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
        textField1.setForeground(Color.black);
        add(textField1);
        textField1.setBounds(590, 150, 335, 50);

        //======== pamel2 ========
        {
            pamel2.setBackground(new Color(0x15888f));
            pamel2.setPreferredSize(new Dimension(100, 100));
            pamel2.setOpaque(false);
            pamel2.setLayout(null);

            //======== scrollPane1 ========
            {
                scrollPane1.setForeground(Color.black);

                //---- table1 ----
                table1.setModel(new DefaultTableModel(
                    new Object[][] {
                        {"", "", "", "", "", "", ""},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                    },
                    new String[] {
                        "\u7f16\u53f7", "ID", "\u59d3\u540d", "\u91d1\u989d", "\u662f\u5426\u5904\u7406", "\u7ed3\u679c", "\u65f6\u95f4"
                    }
                ) {
                    boolean[] columnEditable = new boolean[] {
                        false, false, false, false, false, false, false
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                {
                    TableColumnModel cm = table1.getColumnModel();
                    cm.getColumn(0).setMinWidth(15);
                    cm.getColumn(6).setPreferredWidth(160);
                }
                table1.setForeground(Color.white);
                scrollPane1.setViewportView(table1);
            }
            pamel2.add(scrollPane1);
            scrollPane1.setBounds(15, 35, 1635, 730);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < pamel2.getComponentCount(); i++) {
                    Rectangle bounds = pamel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = pamel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                pamel2.setMinimumSize(preferredSize);
                pamel2.setPreferredSize(preferredSize);
            }
        }
        add(pamel2);
        pamel2.setBounds(15, 225, 1670, 860);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x24321e));
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("\u4e1c\u5357\u5927\u5b66\u7f51\u4e0a\u94f6\u884c");
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 60));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setIcon(null);
            label1.setForeground(Color.white);
            panel1.add(label1);
            label1.setBounds(1035, 15, 600, 135);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/seulogo.png")));
            panel1.add(label2);
            label2.setBounds(50, 0, 490, 145);

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
        panel1.setBounds(0, 0, 1685, 150);

        //---- label3 ----
        label3.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/imageonline-co-brightnessadjusted (4).png")));
        add(label3);
        label3.setBounds(0, 200, 1685, 830);

        //---- button1 ----
        button1.setText("\u5145\u503c");
        button1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 28));
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        add(button1);
        button1.setBounds(940, 150, 150, 50);

        //---- button2 ----
        button2.setText("\u5237\u65b0");
        button2.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 28));
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshMouseClicked();
            }
        });
        add(button2);
        button2.setBounds(1110, 150, 150, 50);

        //---- label7 ----
        label7.setText("\u5f53\u524d\u4f59\u989d:");
        label7.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
        label7.setForeground(Color.black);
        add(label7);
        label7.setBounds(195, 150, 125, 50);

        //---- textField2 ----
        textField2.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
        textField2.setEditable(false);
        textField2.setForeground(Color.white);
        textField2.setOpaque(false);
        textField2.setHorizontalAlignment(SwingConstants.CENTER);
        add(textField2);
        textField2.setBounds(330, 150, 170, 50);

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
    private JTextField textField1;
    private JPanel pamel2;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JButton button1;
    private JButton button2;
    private JLabel label7;
    private JTextField textField2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on



    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    SocketHelper socketHelper=new SocketHelper();
                    socketHelper.getConnection(socketHelper.ip,socketHelper.port);
                    socketHelper.getOs().writeInt(1);
                    socketHelper.getOs().flush();
                    User uu=new User();
                    uu.setId("0800");
                    userBankView stuAdmin = new userBankView(socketHelper,uu);


                    frame.setLayout(new BorderLayout()); // 设置布局管理器为BorderLayout

                    frame.add(stuAdmin, BorderLayout.CENTER); // 将StuAdmin添加到CENTER位置
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
