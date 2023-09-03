/*
 * Created by JFormDesigner on Fri Sep 01 01:06:15 AWST 2023
 */

package bankSystemView;

import module.*;
import entity.*;
import utils.*;

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

    private String uId;
    private SocketHelper socketHelper;
    private bankSystem model;

    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public userBankView(SocketHelper socketHelper, String uid) {
        beautify();
        initComponents();
        this.uId=uid;
        this.socketHelper=socketHelper;
        model=new bankSystem(this.socketHelper);


        button1.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
      /*  DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        table1.getTableHeader().setDefaultRenderer(hr);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table1.setFont(new Font("宋体", Font.BOLD, 20));
        table1.setDefaultRenderer(Object.class, r);
        table1.setRowHeight(60);
        JTableHeader head = table1.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 40));// 设置表头大小
        head.setFont(new Font("华文仿宋", Font.BOLD, 30));// 设置表格字体*/

            //设置表头格式
        table1.getTableHeader().setPreferredSize(new Dimension(1, 30));
        table1.getTableHeader().setFont(new Font("华文仿宋",Font.BOLD,25));
        //设置表格字体和位置
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();//创建一个默认的表单元格渲染器
        dc.setHorizontalAlignment(SwingConstants.CENTER);
        table1.setDefaultRenderer(Object.class,dc);
        table1.setFont(new Font("华文仿宋",Font.BOLD,20));
        table1.setRowHeight(60);

        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        table1.getTableHeader().setDefaultRenderer(hr);
        try
        {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer(){
                private static final long serialVersionUID = 1L;
                public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column){
                    if(row%2 == 0)
                        setBackground(Color.WHITE);//设置奇数行底色
                    else if(row%2 == 1)
                        setBackground(new Color(124,179,158));//设置偶数行底色
                    return super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
                }
            };
            for(int i = 0; i < table1.getColumnCount(); i++) {
                table1.getColumn(table1.getColumnName(i)).setCellRenderer(tcr);
            }
            tcr.setHorizontalAlignment(JLabel.CENTER);
            table1.setDefaultRenderer(Object.class,tcr);
        }
        catch (Exception e){
            e.printStackTrace();
        }


        refreshTable();
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String text = textField1.getText();
        try {
            double value = Double.parseDouble(text);
            rechargeRecord temp=new rechargeRecord();
            temp.setuId(uId);
            temp.setAmount(value);
            temp.setuName("ll");
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
        temp.setuId(this.uId);
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
    }

    private void refreshMouseClicked() {
        // TODO add your code here
        refreshTable();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        pamel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        textField1 = new JTextField();
        button1 = new JButton();
        label3 = new JLabel();
        label4 = new JLabel();
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(1685, 1030));
        setLayout(null);

        //======== pamel2 ========
        {
            pamel2.setBackground(new Color(0x15888f));
            pamel2.setPreferredSize(new Dimension(100, 100));
            pamel2.setLayout(null);

            //======== scrollPane1 ========
            {

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
                scrollPane1.setViewportView(table1);
            }
            pamel2.add(scrollPane1);
            scrollPane1.setBounds(240, 115, 1240, 665);

            //---- textField1 ----
            textField1.setBackground(new Color(0xccffff));
            textField1.setFont(new Font("\u5e7c\u5706", Font.BOLD, 28));
            textField1.setHorizontalAlignment(SwingConstants.CENTER);
            pamel2.add(textField1);
            textField1.setBounds(240, 35, 295, 55);

            //---- button1 ----
            button1.setText("\u5145\u503c");
            button1.setFont(new Font("\u5fae\u8edf\u6b63\u9ed1\u9ad4", Font.BOLD, 28));
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            pamel2.add(button1);
            button1.setBounds(555, 25, 110, 75);

            //---- label3 ----
            label3.setText("text");
            label3.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/\u6682\u65e0\u94f6\u884c\u5361 (2).png")));
            pamel2.add(label3);
            label3.setBounds(1020, -125, 670, 925);

            //---- label4 ----
            label4.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/\u6682\u65e0\u94f6\u884c\u5361 (3).png")));
            pamel2.add(label4);
            label4.setBounds(-110, -65, 985, 870);

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
        pamel2.setBounds(0, 190, 1685, 840);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0xccf0e6));
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("\u4e1c\u5357\u5927\u5b66\u7f51\u4e0a\u94f6\u884c");
            label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 60));
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/\u94f6\u884c (2).png")));
            label1.setForeground(Color.white);
            panel1.add(label1);
            label1.setBounds(475, 40, 730, 125);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/seulogo.png")));
            panel1.add(label2);
            label2.setBounds(1310, 5, 355, 190);

            //---- label5 ----
            label5.setText("text");
            label5.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/img.png")));
            label5.setForeground(new Color(0x15888f));
            panel1.add(label5);
            label5.setBounds(0, 0, 855, 250);

            //---- label6 ----
            label6.setText("text");
            label6.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/img.png")));
            panel1.add(label6);
            label6.setBounds(660, 0, 1175, 235);

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
        panel1.setBounds(0, 0, 1685, 230);

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
    private JPanel pamel2;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JTextField textField1;
    private JButton button1;
    private JLabel label3;
    private JLabel label4;
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label5;
    private JLabel label6;
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
                    userBankView stuAdmin = new userBankView(socketHelper,"555");

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
