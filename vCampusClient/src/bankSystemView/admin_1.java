/*
 * Created by JFormDesigner on Sat Sep 02 12:55:57 AWST 2023
 */

package bankSystemView;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;


import module.*;
import entity.*;
import utils.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import sun.swing.table.DefaultTableCellHeaderRenderer;



/**
 * @author 22431
 */
public class admin_1 extends JPanel {
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
    public admin_1(SocketHelper socketHelper) {
        beautify();
        initComponents();
        this.socketHelper=socketHelper;
        model=new bankSystem(this.socketHelper);
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        table1.getTableHeader().setDefaultRenderer(hr);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table1.setFont(new Font("宋体", Font.BOLD, 20));
        table1.setDefaultRenderer(Object.class, r);
        table1.setRowHeight(60);
        JTableHeader head = table1.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 40));// 设置表头大小
        head.setFont(new Font("华文仿宋", Font.BOLD, 30));
        refreshTable();
    }
    public void refreshTable() {
        rechargeRecord[] sss = model.admin_requestRecord(new rechargeRecord());
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        // 清除表格数据
        tableModel.setRowCount(0);
        textField1.setText("");//必须加

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

    private void searchMouseClicked(MouseEvent e) {
        // TODO add your code here
        if(textField1.getText()=="")
            ;

    }

    private void acceptMouseClicked(MouseEvent e) {
        // TODO add your code here
        int selectedRow = table1.getSelectedRow();
        if (selectedRow != -1) {
            TableModel model = table1.getModel();
            Object rowData[] = new Object[model.getColumnCount()];
            for (int i = 0; i < model.getColumnCount(); i++) {
                rowData[i] = model.getValueAt(selectedRow, i);
            }

            rechargeRecord temp=new rechargeRecord();
            temp.setrId((Integer) rowData[0]);
             if(this.model.admin_accept(temp))
                 JOptionPane.showMessageDialog(this,"已通过该充值请求！！");
             else
                 JOptionPane.showMessageDialog(this,"操作失败，请再试一次！！");
        }
        else
            JOptionPane.showMessageDialog(this,"请选择充值记录！！");
        refreshTable();

    }

    private void refuseMouseClicked(MouseEvent e) {
        // TODO add your code here、
        int selectedRow = table1.getSelectedRow();
        if (selectedRow != -1) {
            TableModel model = table1.getModel();
            Object rowData[] = new Object[model.getColumnCount()];
            for (int i = 0; i < model.getColumnCount(); i++) {
                rowData[i] = model.getValueAt(selectedRow, i);
            }

            rechargeRecord temp=new rechargeRecord();
            temp.setrId((Integer) rowData[0]);
            if(this.model.admin_refuse(temp))
            {
                JOptionPane.showMessageDialog(this,"已拒绝该充值请求！！");
                refreshTable();
            }
            else
                JOptionPane.showMessageDialog(this,"操作失败，请再试一次！！");
        }
        else
            JOptionPane.showMessageDialog(this,"请选择充值记录！！");


    }

    private void refreshMouseClicked(MouseEvent e) {
        // TODO add your code here
        refreshTable();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel1 = new JPanel();
        textField1 = new JTextField();
        button3 = new JButton();
        button2 = new JButton();
        button4 = new JButton();
        label3 = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(1685, 815));
        setBackground(new Color(0x00ffcc));
        setOpaque(false);
        setMinimumSize(new Dimension(1685, 815));
        setLayout(null);

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
        add(scrollPane1);
        scrollPane1.setBounds(230, 155, 1145, 610);

        //======== panel1 ========
        {
            panel1.setForeground(Color.white);
            panel1.setBackground(new Color(0x2b4e50));
            panel1.setPreferredSize(new Dimension(1685, 815));
            panel1.setLayout(null);
            panel1.add(textField1);
            textField1.setBounds(225, 60, 260, 50);

            //---- button3 ----
            button3.setText("\u62d2\u7edd");
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    refuseMouseClicked(e);
                }
            });
            panel1.add(button3);
            button3.setBounds(1425, 320, 120, 65);

            //---- button2 ----
            button2.setText("\u901a\u8fc7");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    acceptMouseClicked(e);
                }
            });
            panel1.add(button2);
            button2.setBounds(1430, 225, 115, 65);

            //---- button4 ----
            button4.setText("\u5237\u65b0");
            button4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    refreshMouseClicked(e);
                }
            });
            panel1.add(button4);
            button4.setBounds(525, 40, 115, 75);

            //---- label3 ----
            label3.setText("text");
            label3.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/\u6682\u65e0\u94f6\u884c\u5361 (4).png")));
            panel1.add(label3);
            label3.setBounds(1120, 5, 935, 890);

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
        panel1.setBounds(0, 0, 1685, 810);

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
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) table1.getModel());
        table1.setRowSorter(sorter);

        String tipText = "请输入充值请求ID";
        textField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (textField1.getText().equals(tipText)) {
                } else {
                    updateFilter();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (textField1.getText().equals(tipText)) {
                } else {
                    updateFilter();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (textField1.getText().equals(tipText)) {
                } else {
                    updateFilter();
                }

            }

            private void updateFilter() {
                String searchText = textField1.getText();
                if (searchText.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
                }
            }
        });
        textField1.setForeground(Color.GRAY);
        // 添加焦点监听器，当获得焦点时清空文本内容和修改文字颜色
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField1.getText().equals(tipText)) {
                    textField1.setText("");
                    textField1.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField1.getText().isEmpty()) {
                    textField1.setText(tipText);
                    textField1.setForeground(Color.GRAY);
                }
            }
        });
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel1;
    private JTextField textField1;
    private JButton button3;
    private JButton button2;
    private JButton button4;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    SocketHelper socketHelper=new SocketHelper();
                    socketHelper.getConnection(socketHelper.ip,socketHelper.port);
                    admin_1 stuAdmin = new admin_1(socketHelper);

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
