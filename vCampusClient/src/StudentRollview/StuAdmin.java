package StudentRollview;

import module.StudentData;
import entity.StudentRoll;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import sun.swing.table.DefaultTableCellHeaderRenderer;
import utils.SocketHelper;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;

public class StuAdmin extends JPanel {
    private SocketHelper socketHelper;
    private StudentData studentData;
    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public StuAdmin(SocketHelper socketHelper) {
        beautify();
        initComponents();

        addbtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
        modifybtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        deletebtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        table1.getTableHeader().setDefaultRenderer(hr);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table1.setFont(new Font("华文仿宋", Font.BOLD, 20));
        table1.setDefaultRenderer(Object.class, r);
        table1.setRowHeight(60);
        JTableHeader head = table1.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 40));// 设置表头大小
        head.setFont(new Font("华文仿宋", Font.BOLD, 30));// 设置表格字体

        this.socketHelper = socketHelper;
        studentData = new StudentData(socketHelper);
        this.refreshTable();
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SocketHelper skhp = new SocketHelper();
                    skhp.getConnection(SocketHelper.ip,SocketHelper.port);
                    skhp.getOs().writeInt(1);
                    skhp.getOs().flush();
                    JFrame frame = new JFrame();
                    StuAdmin stuAdmin = new StuAdmin(skhp);

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

    public void refreshTable() {
        Vector<StudentRoll> sss = studentData.Displayall();

        Iterator<StudentRoll> it = sss.iterator();
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();

        ((DefaultTableModel) table1.getModel()).getDataVector().clear();   //清除表格数据

        while (it.hasNext()) {
            StudentRoll stu = (StudentRoll) it.next();
            Vector<String> cv = new Vector<>();
            cv.add(stu.getStuId());
            cv.add(stu.getStuName());
            cv.add(stu.getsSex());
            cv.add(stu.getsGrades());
            cv.add(stu.getsPlace());
            cv.add(stu.getsDepart());
            cv.add(stu.getsProfession());
            tableModel.addRow(cv);
        }
    }

    private void deletebtnMouseClicked(MouseEvent e) {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow != -1) {
            TableModel model = table1.getModel();
            String stuId = (String) model.getValueAt(selectedRow, 0);
            boolean cmd = studentData.Delete(new StudentRoll(stuId));
            this.refreshTable();
            if (cmd) {
                JOptionPane.showMessageDialog(this, "删除成功");
            } else {
                JOptionPane.showMessageDialog(this, "删除失败");
            }
        }
    }

    private void modifybtnMouseClicked(MouseEvent e) {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow != -1) {
            TableModel model = table1.getModel();
            String rowData[] = new String[model.getColumnCount()];
            for (int i = 0; i < model.getColumnCount(); i++) {
                rowData[i] = (String) model.getValueAt(selectedRow, i);
            }

            AdModifyView adModifyView = new AdModifyView(new StudentRoll(rowData[0], rowData[1], rowData[2], rowData[3], rowData[4], rowData[5], rowData[6]), socketHelper, this);
            adModifyView.setVisible(true);
        }
    }

    private void addbtnMouseClicked(MouseEvent e) {
        AdModifyView adModifyView = new AdModifyView(new StudentRoll(), socketHelper, this);
        adModifyView.setVisible(true);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY // GEN-BEGIN:initComponents
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        modifybtn = new JButton();
        addbtn = new JButton();
        textField1 = new JTextField();
        deletebtn = new JButton();
        panel1 = new JPanel();
        label2 = new JLabel();
        separator1 = new JSeparator();
        label5 = new JLabel();
        label3 = new JLabel();

        //======== this ========
        setBackground(new Color(0xcbdde8));
        setLayout(null);

        //---- label1 ----
        label1.setText("\u5b66\u7c4d\u4fe1\u606f\u7ba1\u7406");
        label1.setFont(new Font("\u5fae\u8f6f\u96c5\u9ed1", label1.getFont().getStyle() | Font.BOLD, label1.getFont().getSize() + 50));
        label1.setIcon(new ImageIcon(getClass().getResource("/pic/stu_title.png")));
        add(label1);
        label1.setBounds(575, 65, 565, 65);

        //======== scrollPane1 ========
        {
            scrollPane1.setBackground(new Color(0x66ffcc));

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
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
                    {null, null, null, null, null, null, null},
                },
                new String[] {
                    "\u5b66\u53f7", "\u59d3\u540d", "\u6027\u522b", "\u5e74\u7ea7", "\u7c4d\u8d2f", "\u5b66\u9662", "\u4e13\u4e1a"
                }
            ) {
                boolean[] columnEditable = new boolean[] {
                    false, true, true, true, true, true, true
                };
                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }
            });
            table1.setFont(new Font("\u65b9\u6b63\u8212\u4f53", table1.getFont().getStyle() | Font.BOLD, 24));
            table1.setForeground(Color.black);
            table1.setSelectionBackground(new Color(0xb4c9c9));
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(115, 280, 1345, 640);

        //---- modifybtn ----
        modifybtn.setText("\u4fee\u6539");
        modifybtn.setFont(new Font("\u534e\u6587\u4eff\u5b8b", modifybtn.getFont().getStyle() | Font.BOLD, modifybtn.getFont().getSize() + 10));
        modifybtn.setIcon(new ImageIcon(getClass().getResource("/pic/stu_modify.png")));
        modifybtn.setBackground(new Color(0x0099ff));
        modifybtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                modifybtnMouseClicked(e);
            }
        });
        add(modifybtn);
        modifybtn.setBounds(1510, 455, 150, 65);

        //---- addbtn ----
        addbtn.setText("\u6dfb\u52a0");
        addbtn.setFont(new Font("\u534e\u6587\u4eff\u5b8b", addbtn.getFont().getStyle() | Font.BOLD, addbtn.getFont().getSize() + 10));
        addbtn.setIcon(new ImageIcon(getClass().getResource("/pic/stu_add.png")));
        addbtn.setBackground(new Color(0xff9966));
        addbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addbtnMouseClicked(e);
            }
        });
        add(addbtn);
        addbtn.setBounds(1510, 310, 150, 65);

        //---- textField1 ----
        textField1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD|Font.ITALIC, textField1.getFont().getSize() + 10));
        textField1.setText("\u8bf7\u8f93\u5165\u641c\u7d22\u5173\u952e\u8bcd");
        textField1.setBackground(Color.white);
        add(textField1);
        textField1.setBounds(475, 210, 690, 55);

        //---- deletebtn ----
        deletebtn.setText("\u5220\u9664");
        deletebtn.setFont(new Font("\u534e\u6587\u4eff\u5b8b", deletebtn.getFont().getStyle() | Font.BOLD, deletebtn.getFont().getSize() + 10));
        deletebtn.setIcon(new ImageIcon(getClass().getResource("/pic/stu_delete.png")));
        deletebtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deletebtnMouseClicked(e);
            }
        });
        add(deletebtn);
        deletebtn.setBounds(1510, 605, 150, 65);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x9a9c99));
            panel1.setLayout(null);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/pic/stu_logo.png")));
            panel1.add(label2);
            label2.setBounds(110, -15, 525, 225);

            //---- separator1 ----
            separator1.setBackground(Color.black);
            panel1.add(separator1);
            separator1.setBounds(0, 195, 1650, 5);

            //---- label5 ----
            label5.setIcon(new ImageIcon(getClass().getResource("/pic/seulogo.png")));
            label5.setBackground(new Color(0x50524f));
            panel1.add(label5);
            label5.setBounds(1305, 0, 690, 195);

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
        panel1.setBounds(0, 0, 1685, 200);

        //---- label3 ----
        label3.setIcon(new ImageIcon(getClass().getResource("/pic/search2.png")));
        add(label3);
        label3.setBounds(395, 205, 60, 60);

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
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) table1.getModel());
        table1.setRowSorter(sorter);

        String tipText = "请输入搜索关键词";
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


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton modifybtn;
    private JButton addbtn;
    private JTextField textField1;
    private JButton deletebtn;
    private JPanel panel1;
    private JLabel label2;
    private JSeparator separator1;
    private JLabel label5;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
