/*
 * Created by JFormDesigner on Fri Sep 08 16:23:43 CST 2023
 */

package LibraryView;

import utils.UIStyler;
import entity.Book;
import entity.BookIllegal;
import entity.BookRecord;
import module.BookSystem;
import utils.SocketHelper;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * @author 86153
 */
public class admin_illiegal extends JPanel {
    SocketHelper socketHelper;
    BookSystem model;
    Book book;
    List<Book> books;
    BookRecord record;
    List<BookRecord> records;
    BookIllegal illegal;
    List<BookIllegal>illegals;
    public admin_illiegal(SocketHelper helper) {

        initComponents();
        this.socketHelper = helper;
        setLayout(new BorderLayout());
        UIStyler.setBelowButton(button1);
        UIStyler.setBelowButton(button2);
        UIStyler.setBelowButton(search);
        UIStyler.setTransparentTable(scrollPane1);
        table1.getTableHeader().setReorderingAllowed(false);
        table1.getTableHeader().setReorderingAllowed(false);
        model = new BookSystem(socketHelper);
        table1.setRowHeight(60);
        refreshtable();
    }

    private void refreshtable() {
        illegal=new BookIllegal(0,"","","","","","","",0,false);
        List<BookIllegal> rs=model.searchillegal(illegal,1);
        table1.clearSelection();
        illegals=rs;
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0); // 清空表格数据
        for (BookIllegal illegal1:illegals)
        {
            String temp;
            if(illegal1.isStatus()==false)
            {
                temp="未缴清";
            }
            else
            {
                temp="缴清";
            }
            Object[] rowData = {illegal1.getRecordid(),illegal1.getISBN(), illegal1.getName(),illegal1.getAuthor(),illegal1.getUserID(),illegal1.getBorrowtime(), illegal1.getDeadline(), illegal1.getToday(),illegal1.getPenalty(),
                    temp};
            tableModel.addRow(rowData);
        }
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        int row=table1.getSelectedRow();
        if(row==-1)
        {
            JOptionPane.showMessageDialog(null, "请选中指定记录", "错误", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            illegal=new BookIllegal((int) table1.getValueAt(row,0),
                    (String)table1.getValueAt(row,4),
                    (String)table1.getValueAt(row,1),
                    (String)table1.getValueAt(row,2),
                    (String)table1.getValueAt(row,3),
                    (String)table1.getValueAt(row,5),
                    (String)table1.getValueAt(row,6),
                    (String)table1.getValueAt(row,7),
                    (double)table1.getValueAt(row,8),
                    false
                    );
            model.admin_deleteillegal(illegal);
        }
        refreshtable();
    }

    private void button2MouseClicked(MouseEvent e) {
        refreshtable();
    }

    private void searchMouseClicked(MouseEvent e) {
        // TODO add your code here
        String flag = comboBox1.getSelectedItem().toString();
        System.out.println(flag);
        String text = textField1.getText();
        try {
            if (textField1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "请输入查找内容", "查找失败", JOptionPane.ERROR_MESSAGE);
            }
            else {
                if(flag.equals("ISBN")) {
                    illegal = new BookIllegal(0, null, text, null, null, null, null, null, 0, false);
                    System.out.println("开始查找");
                    List<BookIllegal> rs = model.searchillegal(illegal, 7);
                    table1.clearSelection();
                    illegals = rs;
                    if (rs.size() == 0) {
                        textField1.setText("");//必须加
                        table1.clearSelection();
                        JOptionPane.showMessageDialog(null, "未找到匹配的记录", "查找失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (BookIllegal illegal1 : illegals) {
                            String temp;
                            if (illegal1.isStatus() == false) {
                                temp = "未缴清";
                            } else {
                                temp = "缴清";
                            }
                            Object[] rowData = {illegal1.getRecordid(), illegal1.getISBN(), illegal1.getName(), illegal1.getAuthor(), illegal1.getUserID(), illegal1.getBorrowtime(), illegal1.getDeadline(), illegal1.getToday(), illegal1.getPenalty(),
                                    temp};
                            tableModel.addRow(rowData);
                        }
                    }
                }
                if(flag.equals("书名")) {
                    illegal = new BookIllegal(0, null, null, text, null, null, null, null, 0, false);
                    System.out.println("开始查找");
                    List<BookIllegal> rs = model.searchillegal(illegal, 2);
                    table1.clearSelection();
                    illegals = rs;
                    if (rs.size() == 0) {
                        textField1.setText("");//必须加
                        table1.clearSelection();
                        JOptionPane.showMessageDialog(null, "未找到匹配的记录", "查找失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (BookIllegal illegal1 : illegals) {
                            String temp;
                            if (illegal1.isStatus() == false) {
                                temp = "未缴清";
                            } else {
                                temp = "缴清";
                            }
                            Object[] rowData = {illegal1.getRecordid(), illegal1.getISBN(), illegal1.getName(), illegal1.getAuthor(), illegal1.getUserID(), illegal1.getBorrowtime(), illegal1.getDeadline(), illegal1.getToday(), illegal1.getPenalty(),
                                    temp};
                            tableModel.addRow(rowData);
                        }
                    }
                }
                if(flag.equals("借阅人")) {
                    illegal = new BookIllegal(0, text, null, null, null, null, null, null, 0, false);
                    System.out.println("开始查找");
                    List<BookIllegal> rs = model.searchillegal(illegal, 5);
                    table1.clearSelection();
                    illegals = rs;
                    if (rs.size() == 0) {
                        textField1.setText("");//必须加
                        table1.clearSelection();
                        JOptionPane.showMessageDialog(null, "未找到匹配的记录", "查找失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (BookIllegal illegal1 : illegals) {
                            String temp;
                            if (illegal1.isStatus() == false) {
                                temp = "未缴清";
                            } else {
                                temp = "缴清";
                            }
                            Object[] rowData = {illegal1.getRecordid(), illegal1.getISBN(), illegal1.getName(), illegal1.getAuthor(), illegal1.getUserID(), illegal1.getBorrowtime(), illegal1.getDeadline(), illegal1.getToday(), illegal1.getPenalty(),
                                    temp};
                            tableModel.addRow(rowData);
                        }
                    }
                }
                if(flag.equals("违章记录号")) {
                    illegal = new BookIllegal(Integer.parseInt(text), null, null, null, null, null, null, null, 0, false);
                    System.out.println("开始查找");
                    List<BookIllegal> rs = model.searchillegal(illegal, 3);
                    table1.clearSelection();
                    illegals = rs;
                    if (rs.size() == 0) {
                        textField1.setText("");//必须加
                        table1.clearSelection();
                        JOptionPane.showMessageDialog(null, "未找到匹配的记录", "查找失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (BookIllegal illegal1 : illegals) {
                            String temp;
                            if (illegal1.isStatus() == false) {
                                temp = "未缴清";
                            } else {
                                temp = "缴清";
                            }
                            Object[] rowData = {illegal1.getRecordid(), illegal1.getISBN(), illegal1.getName(), illegal1.getAuthor(), illegal1.getUserID(), illegal1.getBorrowtime(), illegal1.getDeadline(), illegal1.getToday(), illegal1.getPenalty(),
                                    temp};
                            tableModel.addRow(rowData);
                        }
                    }
                }
            }
        } catch (HeadlessException ex) {
            ex.printStackTrace();
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        comboBox1 = new JComboBox<>();
        textField1 = new JTextField();
        search = new JButton();
        label1 = new JLabel();

        //======== this ========
        setLayout(null);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {"", "", "", null, null, null, null, null, "", ""},
                    {null, null, null, null, null, null, null, null, null, null},
                },
                new String[] {
                    "\u8fdd\u7ae0\u8bb0\u5f55\u53f7", "ISBN", "\u4e66\u540d", "\u4f5c\u8005", "\u501f\u9605\u4eba", "\u501f\u9605\u65e5\u671f", "\u5e94\u8fd8\u65e5\u671f", "\u8fd8\u4e66\u65e5\u671f", "\u7f5a\u91d1", "\u662f\u5426\u7f34\u6e05"
                }
            ));
            table1.setForeground(Color.white);
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(50, 100, 1595, 700);

        //---- button1 ----
        button1.setText("\u5220\u9664\u8bb0\u5f55");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        add(button1);
        button1.setBounds(1250, 20, 150, 50);

        //---- button2 ----
        button2.setText("\u5237\u65b0");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });
        add(button2);
        button2.setBounds(1080, 20, 150, 50);

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "ISBN",
            "\u4e66\u540d",
            "\u501f\u9605\u4eba",
            "\u8fdd\u7ae0\u8bb0\u5f55\u53f7"
        }));
        comboBox1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
        add(comboBox1);
        comboBox1.setBounds(145, 20, 160, 50);

        //---- textField1 ----
        textField1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
        textField1.setForeground(Color.white);
        add(textField1);
        textField1.setBounds(335, 20, 530, 55);

        //---- search ----
        search.setText("\u67e5\u8be2");
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchMouseClicked(e);
            }
        });
        add(search);
        search.setBounds(905, 20, 150, 50);

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/QQ\u56fe\u724720230913212503.jpg")));
        add(label1);
        label1.setBounds(new Rectangle(new Point(0, 0), label1.getPreferredSize()));

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

        String tipText = "请输入查找内容";
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
    private JButton button1;
    private JButton button2;
    private JComboBox<String> comboBox1;
    private JTextField textField1;
    private JButton search;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
