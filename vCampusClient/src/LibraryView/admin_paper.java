/*
 * Created by JFormDesigner on Sun Sep 10 22:08:12 CST 2023
 */

package LibraryView;

import utils.UIStyler;
import entity.Paper;
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
import java.util.Vector;

/**
 * @author 86153
 */
public class admin_paper extends JPanel{
    SocketHelper socketHelper;
    String uid="dhb";
    BookSystem model;
    Paper paper;
    List<Paper> papers;

    public admin_paper(SocketHelper helper)
    {
        initComponents();
        adminppp.setVisible(true);
        this.socketHelper=helper;
        UIStyler.setTransparentTable(scrollPane1);
        UIStyler.setBelowButton(button1);
        UIStyler.setBelowButton(button2);
        UIStyler.setBelowButton(button3);
        UIStyler.setBelowButton(button4);
        UIStyler.setBelowButton(search);
        table1.getTableHeader().setReorderingAllowed(false);
        table1.getTableHeader().setReorderingAllowed(false);
        setLayout(new BorderLayout()); // 设置布局管理器为BorderLayout
        add(adminppp, BorderLayout.CENTER); // 将myrecord添加到CENTER位置
        model=new BookSystem(socketHelper);
        refreshtable();
    }

    public void refreshtable() {
        paper = new Paper(0, null, null, null, null, null, null,0);
        List<Paper> rs = model.searchPaper(paper, 1);
        table1.clearSelection();
        table1.setRowHeight(60);
        papers = rs;
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0); // 清空表格数据
        for (int j = 0; j < papers.size(); ++j) {
            Vector<Object> rowData = new Vector<>();
            rowData.add(papers.get(j).getId());
            rowData.add(papers.get(j).getName());
            rowData.add(papers.get(j).getAuthor());
            rowData.add(papers.get(j).getJournal());
            rowData.add(papers.get(j).getPublishdate());
            rowData.add(papers.get(j).getPath());
            rowData.add(papers.get(j).getCount());
            tableModel.addRow(rowData);
        }
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        Paperinfo info= new Paperinfo(admin_paper.this,socketHelper);

        // 创建第二个 JDialog 并设置其内容面板
        info.setModal(true);

        // 显示对话框
        info.setVisible(true);
        refreshtable();
    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        int row=table1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "请选中指定文献", "错误", JOptionPane.ERROR_MESSAGE);
        }
        else {
            String ID=  table1.getValueAt(row,0).toString();
            String name=(String) table1.getValueAt(row,1);
            String author=(String) table1.getValueAt(row,2);
            int id=Integer.parseInt(ID);
            paper=new Paper(id,name,author,null,null,null,null,0);
            papers=model.searchPaper(paper,3);
            Paper temp=new Paper();
            for(int i=0; i<papers.size(); ++i){
                if(papers.get(i).getId()==id){
                    temp = papers.get(i);
                    break;
                }
            }
            Paperinfo info= new Paperinfo(admin_paper.this,socketHelper,temp);

            // 创建第二个 JDialog 并设置其内容面板
            info.setModal(true);

            // 显示对话框
            info.setVisible(true);
            refreshtable();
        }

    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        refreshtable();
    }

    private void button4MouseClicked(MouseEvent e) {
        // TODO add your code here
        if (table1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "请选中指定文献", "错误", JOptionPane.ERROR_MESSAGE);
        }
        else {
            int row = table1.getSelectedRow();
            int ID= (int) table1.getValueAt(row,0);
            String name=(String) table1.getValueAt(row,1);
            String author=(String) table1.getValueAt(row,2);
            paper=new Paper(ID,name,author,null,null,null,null,0);
            boolean flag=model.deletepaper(paper);
            {
                if(flag)
                {
                    refreshtable();
                    JOptionPane.showMessageDialog((Component) this, "删除成功！");
                }
                else {
                    JOptionPane.showMessageDialog(null,"删除失败","错误",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
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
                if(flag=="记录号")
                {
                    paper=new Paper(Integer.parseInt(text),null,null,null,null,null,null,0);
                    System.out.println("开始查找");
                    List<Paper> rs = model.searchPaper(paper, 3);
                    if (rs.size() == 0) {
                        textField1.setText("");//必须加
                        table1.clearSelection();
                        JOptionPane.showMessageDialog(null, "未找到匹配的文献", "查找失败", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (int j = 0; j < papers.size(); ++j) {
                            Vector<Object> rowData = new Vector<>();
                            rowData.add(papers.get(j).getId());
                            rowData.add(papers.get(j).getName());
                            rowData.add(papers.get(j).getAuthor());
                            rowData.add(papers.get(j).getJournal());
                            rowData.add(papers.get(j).getPublishdate());
                            rowData.add(papers.get(j).getPath());
                            rowData.add(papers.get(j).getCount());
                            tableModel.addRow(rowData);
                        }
                    }
                }
                if(flag=="题名")
                {
                    paper=new Paper(0,text,null,null,null,null,null,0);
                    System.out.println("开始查找");
                    List<Paper> rs = model.searchPaper(paper, 2);
                    if (rs.size() == 0) {
                        textField1.setText("");//必须加
                        table1.clearSelection();
                        JOptionPane.showMessageDialog(null, "未找到匹配的文献", "查找失败", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (int j = 0; j < papers.size(); ++j) {
                            Vector<Object> rowData = new Vector<>();
                            rowData.add(papers.get(j).getId());
                            rowData.add(papers.get(j).getName());
                            rowData.add(papers.get(j).getAuthor());
                            rowData.add(papers.get(j).getJournal());
                            rowData.add(papers.get(j).getPublishdate());
                            rowData.add(papers.get(j).getPath());
                            rowData.add(papers.get(j).getCount());
                            tableModel.addRow(rowData);
                        }
                    }
                }
                if(flag=="作者")
                {
                    paper=new Paper(0,null,text,null,null,null,null,0);
                    System.out.println("开始查找");
                    List<Paper> rs = model.searchPaper(paper, 4);
                    if (rs.size() == 0) {
                        textField1.setText("");//必须加
                        table1.clearSelection();
                        JOptionPane.showMessageDialog(null, "未找到匹配的文献", "查找失败", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (int j = 0; j < papers.size(); ++j) {
                            Vector<Object> rowData = new Vector<>();
                            rowData.add(papers.get(j).getId());
                            rowData.add(papers.get(j).getName());
                            rowData.add(papers.get(j).getAuthor());
                            rowData.add(papers.get(j).getJournal());
                            rowData.add(papers.get(j).getPublishdate());
                            rowData.add(papers.get(j).getPath());
                            rowData.add(papers.get(j).getCount());
                            tableModel.addRow(rowData);
                        }
                    }
                }
                if(flag=="来源")
                {
                    paper=new Paper(0,null,null,null,text,null,null,0);
                    System.out.println("开始查找");
                    List<Paper> rs = model.searchPaper(paper, 5);
                    if (rs.size() == 0) {
                        textField1.setText("");//必须加
                        table1.clearSelection();
                        JOptionPane.showMessageDialog(null, "未找到匹配的文献", "查找失败", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (int j = 0; j < papers.size(); ++j) {
                            Vector<Object> rowData = new Vector<>();
                            rowData.add(papers.get(j).getId());
                            rowData.add(papers.get(j).getName());
                            rowData.add(papers.get(j).getAuthor());
                            rowData.add(papers.get(j).getJournal());
                            rowData.add(papers.get(j).getPublishdate());
                            rowData.add(papers.get(j).getPath());
                            rowData.add(papers.get(j).getCount());
                            tableModel.addRow(rowData);
                        }
                    }
                }
            }
        } catch (HeadlessException |NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        adminppp = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        comboBox1 = new JComboBox<>();
        textField1 = new JTextField();
        search = new JButton();
        label1 = new JLabel();

        //======== adminppp ========
        {
            adminppp.setLayout(null);

            //======== scrollPane1 ========
            {

                //---- table1 ----
                table1.setModel(new DefaultTableModel(
                    new Object[][] {
                        {"", null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                    },
                    new String[] {
                        "\u8bb0\u5f55\u53f7", "\u9898\u540d", "\u4f5c\u8005", "\u6765\u6e90", "\u53d1\u8868\u65f6\u95f4", "\u94fe\u63a5", "\u4e0b\u8f7d\u6b21\u6570"
                    }
                ));
                table1.setForeground(Color.white);
                scrollPane1.setViewportView(table1);
            }
            adminppp.add(scrollPane1);
            scrollPane1.setBounds(50, 85, 1595, 635);

            //---- button1 ----
            button1.setText("\u6dfb\u52a0\u6587\u732e");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            adminppp.add(button1);
            button1.setBounds(1490, 740, 150, 50);

            //---- button2 ----
            button2.setText("\u4fee\u6539\u6587\u732e");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            adminppp.add(button2);
            button2.setBounds(1150, 740, 150, 50);

            //---- button3 ----
            button3.setText("\u5237\u65b0");
            button3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button3MouseClicked(e);
                }
            });
            adminppp.add(button3);
            button3.setBounds(1040, 10, 150, 50);

            //---- button4 ----
            button4.setText("\u5220\u9664\u6587\u732e");
            button4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button4MouseClicked(e);
                }
            });
            adminppp.add(button4);
            button4.setBounds(1320, 740, 150, 50);

            //---- comboBox1 ----
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u8bb0\u5f55\u53f7",
                "\u9898\u540d",
                "\u4f5c\u8005",
                "\u6765\u6e90"
            }));
            comboBox1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
            adminppp.add(comboBox1);
            comboBox1.setBounds(120, 15, 185, 50);

            //---- textField1 ----
            textField1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
            textField1.setForeground(Color.white);
            adminppp.add(textField1);
            textField1.setBounds(325, 10, 510, 50);

            //---- search ----
            search.setText("\u67e5\u8be2");
            search.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    searchMouseClicked(e);
                }
            });
            adminppp.add(search);
            search.setBounds(855, 10, 150, 50);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/QQ\u56fe\u724720230913212503.jpg")));
            adminppp.add(label1);
            label1.setBounds(new Rectangle(new Point(0, 0), label1.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < adminppp.getComponentCount(); i++) {
                    Rectangle bounds = adminppp.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = adminppp.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                adminppp.setMinimumSize(preferredSize);
                adminppp.setPreferredSize(preferredSize);
            }
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
    private JPanel adminppp;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JComboBox<String> comboBox1;
    private JTextField textField1;
    private JButton search;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
