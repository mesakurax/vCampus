package StudentRollview;

import utils.UIStyler;
import module.StudentData;
import entity.StudentRoll;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
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
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

public class StuAdmin extends JPanel {
    private SocketHelper socketHelper;
    private StudentData studentData;

    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public JPanel getCardPanel(){return panel1;}
    public CardLayout card = new CardLayout();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>();

    public StuAdmin(SocketHelper socketHelper) {
        initComponents();
        UIStyler.setTopButton(button2);
        UIStyler.setTopButton(addbtn);
        UIStyler.setTopButton(modifybtn);
        UIStyler.setTopButton(deletebtn);
        addbtn.setLocation(617,150);
        modifybtn.setLocation(767,150);
        deletebtn.setLocation(917,150);
        button2.setLocation(1535,150);
        UIStyler.createHeader(this);

        UIStyler.setTransparentTable(scrollPane1);
        label2.setBounds(0,200,1685,830);
        panel1.setOpaque(false);
        panel1.setLayout(card);
        panel1.setBounds(50,280,1585,660);
        scrollPane1.setLocation(0,0);
        panel1.add(scrollPane1,"p1");

        TableColumnModel columnModel = table1.getColumnModel();
        columnModel.getColumn(5).setPreferredWidth(220);
        columnModel.getColumn(6).setPreferredWidth(220);

        table1.setFont(new Font("华文仿宋", Font.BOLD, 28));
        table1.setRowHeight(60);

        JTableHeader head = table1.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 40));// 设置表头大小

        sorter = new TableRowSorter<>((DefaultTableModel)table1.getModel());
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

        this.socketHelper = socketHelper;
        studentData = new StudentData(socketHelper);
        this.refreshTable();

    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            SocketHelper skhp = new SocketHelper();
            skhp.getConnection(SocketHelper.ip, SocketHelper.port);
            try {
                skhp.getOs().writeInt(1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            JFrame frame = new JFrame();
            StuAdmin stuAdmin = new StuAdmin(skhp);
            frame.setLayout(new BorderLayout()); // 设置布局管理器为BorderLayout

            frame.add(stuAdmin, BorderLayout.CENTER); // 将StuAdmin添加到CENTER位置
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

        });
    }

    public void refreshTable() {
        Vector<StudentRoll> sss = studentData.Displayall();

        Iterator<StudentRoll> it = sss.iterator();
        sorter.setRowFilter(null);
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
        table1.repaint();
    }

    private void deletebtnMouseClicked(MouseEvent e) {
        int[] selectedRows = table1.getSelectedRows();
        for (int i = 0; i < selectedRows.length; i++) {
            selectedRows[i] = table1.convertRowIndexToModel(selectedRows[i]);
        }
        if (selectedRows.length != 0) {
            TableModel model = table1.getModel();
            boolean flag = true;
            for(int i=0;i<selectedRows.length;i++) {
                String stuId = (String) model.getValueAt(selectedRows[i], 0);
                if(studentData.Delete(new StudentRoll(stuId)) == false) flag = false;
            }
            if (flag) {
                JOptionPane.showMessageDialog(this, "删除成功");
            } else {
                JOptionPane.showMessageDialog(this, "删除失败");
            }
            this.refreshTable();
        }
    }

    private void modifybtnMouseClicked(MouseEvent e) {
        int selectedRow = table1.getSelectedRow();
        selectedRow =  table1.convertRowIndexToModel(selectedRow);
        if (selectedRow != -1) {
            TableModel model = table1.getModel();
            String rowData[] = new String[model.getColumnCount()];
            for (int i = 0; i < model.getColumnCount(); i++) {
                rowData[i] = (String) model.getValueAt(selectedRow, i);
            }
            AdModifyView adModifyView = new AdModifyView(new StudentRoll(rowData[0], rowData[1], rowData[2], rowData[3], rowData[4], rowData[5], rowData[6]), socketHelper, this);
            adModifyView.setBounds(0,0,1685,755);
            panel1.add(adModifyView,"p2");
            card.show(panel1,"p2");
        }
    }

    private void addbtnMouseClicked(MouseEvent e) {
        AdModifyView adModifyView = new AdModifyView(new StudentRoll(), socketHelper, this);
        adModifyView.setBounds(0,0,1685,755);
        panel1.add(adModifyView,"p2");
        card.show(panel1,"p2");
    }

    private void button1MouseClicked(MouseEvent e) {
        card.show(panel1,"p1");
        this.refreshTable();
    }

    private void button2MouseClicked(MouseEvent e) {
        card.show(panel1,"p1");
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY // GEN-BEGIN:initComponents
        label1 = new JLabel();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        modifybtn = new JButton();
        addbtn = new JButton();
        deletebtn = new JButton();
        label4 = new JLabel();
        button1 = new JButton();
        textField1 = new JTextField();
        button2 = new JButton();
        label2 = new JLabel();

        //======== this ========
        setBackground(Color.white);
        setMinimumSize(new Dimension(1685, 1030));
        setLayout(null);

        //---- label1 ----
        label1.setText("\u5b66\u7c4d\u4fe1\u606f\u7ba1\u7406");
        label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 60));
        label1.setIcon(null);
        label1.setForeground(Color.white);
        add(label1);
        label1.setBounds(1035, 15, 650, 130);

        //======== panel1 ========
        {
            panel1.setLayout(null);

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
        panel1.setBounds(new Rectangle(new Point(70, 235), panel1.getPreferredSize()));

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
            table1.setFont(new Font("\u65b9\u6b63\u8212\u4f53", Font.BOLD, 30));
            table1.setForeground(Color.black);
            table1.setSelectionBackground(Color.white);
            table1.setSelectionForeground(new Color(0xffffcc));
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(30, 300, 1635, 695);

        //---- modifybtn ----
        modifybtn.setFont(new Font("\u534e\u6587\u4eff\u5b8b", modifybtn.getFont().getStyle() | Font.BOLD, modifybtn.getFont().getSize() + 10));
        modifybtn.setIcon(null);
        modifybtn.setBackground(new Color(0x0099ff));
        modifybtn.setText("\u4fee\u6539\u4fe1\u606f");
        modifybtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                modifybtnMouseClicked(e);
            }
        });
        add(modifybtn);
        modifybtn.setBounds(1020, 200, 150, 65);

        //---- addbtn ----
        addbtn.setFont(new Font("\u534e\u6587\u4eff\u5b8b", addbtn.getFont().getStyle() | Font.BOLD, addbtn.getFont().getSize() + 10));
        addbtn.setIcon(null);
        addbtn.setBackground(new Color(0xff9966));
        addbtn.setText("\u6dfb\u52a0\u4fe1\u606f");
        addbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addbtnMouseClicked(e);
            }
        });
        add(addbtn);
        addbtn.setBounds(820, 195, 140, 70);

        //---- deletebtn ----
        deletebtn.setFont(new Font("\u534e\u6587\u4eff\u5b8b", deletebtn.getFont().getStyle() | Font.BOLD, deletebtn.getFont().getSize() + 10));
        deletebtn.setIcon(null);
        deletebtn.setText("\u5220\u9664\u4fe1\u606f");
        deletebtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deletebtnMouseClicked(e);
            }
        });
        add(deletebtn);
        deletebtn.setBounds(1225, 195, 135, 70);

        //---- label4 ----
        label4.setIcon(new ImageIcon(getClass().getResource("/StudentRollview/studentRollPic/img_1.png")));
        add(label4);
        label4.setBounds(50, 25, 650, 115);

        //---- button1 ----
        button1.setIcon(new ImageIcon(getClass().getResource("/StudentRollview/studentRollPic/searchstu.png")));
        button1.setBackground(Color.white);
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        add(button1);
        button1.setBounds(611, 210, 55, 55);

        //---- textField1 ----
        textField1.setFont(new Font("\u5b8b\u4f53", Font.PLAIN, 20));
        add(textField1);
        textField1.setBounds(51, 210, 565, 55);

        //---- button2 ----
        button2.setIcon(null);
        button2.setText("\u8fd4\u56de");
        button2.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 24));
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });
        add(button2);
        button2.setBounds(1450, 195, 135, 70);

        //---- label2 ----
        label2.setIcon(new ImageIcon(getClass().getResource("/StudentRollview/studentRollPic/imageonline-co-brightnessadjusted (3).png")));
        add(label2);
        label2.setBounds(0, 305, 1790, 920);

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
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton modifybtn;
    private JButton addbtn;
    private JButton deletebtn;
    private JLabel label4;
    private JButton button1;
    private JTextField textField1;
    private JButton button2;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
