/*
 * Created by JFormDesigner on Sun Sep 10 16:41:20 CST 2023
 */

package LibraryView;

import utils.UIStyler;
import entity.Paper;
import module.BookSystem;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.SocketHelper;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Vector;
import entity.*;

/**
 * @author 86153
 */
public class UserPaper extends JPanel{
    SocketHelper socketHelper;
    String uid;
    BookSystem model;
    Paper paper;
    List<Paper> papers;
    private CardLayout card=new CardLayout();

    private JPanel card_panel=new JPanel();  //子布局，代表后面切换的布局都是它
    private Paperintro one;
    public void beautify() {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public UserPaper(SocketHelper helper,User uu) {
        initComponents();
        this.setLocation(0,0);
        this.uid=uu.getId();
        UIStyler.setTransparentTable(scrollPane1);
        UIStyler.setBelowButton(search);
        UIStyler.setBelowButton(refresh);
        table1.getTableHeader().setReorderingAllowed(false);
        table1.getTableHeader().setReorderingAllowed(false);
        this.socketHelper = helper;
        setLayout(new BorderLayout()); // 设置布局管理器为 BorderLayout

        card_panel.setBounds(0, 0, 1685, 830);
        card_panel.setOpaque(false);
        card_panel.setLayout(card);
        model = new BookSystem(socketHelper);

        add(card_panel, BorderLayout.CENTER); // 将 card_panel 添加到 CENTER 位置

        // 添加 paperpp 到 card_panel
        paperpp.setVisible(true);
        card_panel.add(paperpp, "paperpp");

        // 初始显示 paperpp
        card.show(card_panel, "paperpp");
        refreshtable();
    }

    public void refreshtable() {
        paper = new Paper(0,null, null, null, null, null, null,0);
        List<Paper> rs = model.searchPaper(paper, 1);
        table1.clearSelection();
        table1.setRowHeight(120);
        papers = rs;
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0); // 清空表格数据
        for (int j = 0; j < papers.size(); ++j) {
            Vector<Object> rowData = new Vector<>();
            rowData.add(papers.get(j).getName());
            rowData.add(papers.get(j).getAuthor());
            rowData.add(papers.get(j).getJournal());
            rowData.add(papers.get(j).getPublishdate());
            rowData.add(papers.get(j).getCount());
            tableModel.addRow(rowData);
        }
    }


    private void table1MousePressed(MouseEvent e) {
        // TODO add your code here
        if (e.getClickCount() == 2) {
            int row = table1.getSelectedRow();
            paper = new Paper(0,(String) table1.getValueAt(row, 0), (String) table1.getValueAt(row, 1),null,null,null,null,0);
            one=new Paperintro(socketHelper,paper);
            card_panel.setLayout(card);
            card_panel.add(one,"p0");
            card.show(card_panel,"p0");
            refreshtable();
            JButton returnButton = one.getReturnButton();
            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardLayout cardLayout = (CardLayout) card_panel.getLayout();
                    cardLayout.show(card_panel, "paperpp" );
                }
            });
        }
    }

    private void refreshMouseClicked(MouseEvent e) {
        // TODO add your code here
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
            } else {
                if (flag == "题名") {
                    paper = new Paper(0, text, null, null, null, null, null, 0);
                    System.out.println("开始查找");
                    List<Paper> rs = model.searchPaper(paper, 2);
                    if (rs.size() == 0) {
                        textField1.setText("");//必须加
                        table1.clearSelection();
                        JOptionPane.showMessageDialog(null, "未找到匹配的文献", "查找失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (int j = 0; j < papers.size(); ++j) {
                            Vector<Object> rowData = new Vector<>();
                            rowData.add(papers.get(j).getName());
                            rowData.add(papers.get(j).getAuthor());
                            rowData.add(papers.get(j).getJournal());
                            rowData.add(papers.get(j).getPublishdate());
                            rowData.add(papers.get(j).getCount());
                            tableModel.addRow(rowData);
                        }
                    }
                }
                if (flag == "作者") {
                    paper = new Paper(0, null, text, null, null, null, null, 0);
                    System.out.println("开始查找");
                    List<Paper> rs = model.searchPaper(paper, 4);
                    if (rs.size() == 0) {
                        textField1.setText("");//必须加
                        table1.clearSelection();
                        JOptionPane.showMessageDialog(null, "未找到匹配的文献", "查找失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (int j = 0; j < papers.size(); ++j) {
                            Vector<Object> rowData = new Vector<>();
                            rowData.add(papers.get(j).getName());
                            rowData.add(papers.get(j).getAuthor());
                            rowData.add(papers.get(j).getJournal());
                            rowData.add(papers.get(j).getPublishdate());
                            rowData.add(papers.get(j).getCount());
                            tableModel.addRow(rowData);
                        }
                    }
                }
                if (flag == "来源") {
                    paper = new Paper(0, null, null, null, text, null, null, 0);
                    System.out.println("开始查找");
                    List<Paper> rs = model.searchPaper(paper, 5);
                    if (rs.size() == 0) {
                        textField1.setText("");//必须加
                        table1.clearSelection();
                        JOptionPane.showMessageDialog(null, "未找到匹配的文献", "查找失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (int j = 0; j < papers.size(); ++j) {
                            Vector<Object> rowData = new Vector<>();
                            rowData.add(papers.get(j).getName());
                            rowData.add(papers.get(j).getAuthor());
                            rowData.add(papers.get(j).getJournal());
                            rowData.add(papers.get(j).getPublishdate());
                            rowData.add(papers.get(j).getCount());
                            tableModel.addRow(rowData);
                        }
                    }
                }
            }
        } catch (HeadlessException ex) {
            throw new RuntimeException(ex);
        } catch (NumberFormatException ex) {
            throw new RuntimeException(ex);
        }
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        paperpp = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        refresh = new JButton();
        comboBox1 = new JComboBox<>();
        textField1 = new JTextField();
        search = new JButton();
        label1 = new JLabel();

        //======== paperpp ========
        {
            paperpp.setLayout(null);

            //======== scrollPane1 ========
            {

                //---- table1 ----
                table1.setModel(new DefaultTableModel(
                    new Object[][] {
                        {"", "", null, null, ""},
                        {null, null, null, null, null},
                    },
                    new String[] {
                        "\u9898\u540d", "\u4f5c\u8005", "\u6765\u6e90", "\u53d1\u8868\u65f6\u95f4", "\u4e0b\u8f7d\u6b21\u6570"
                    }
                ));
                table1.setForeground(Color.white);
                table1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        table1MousePressed(e);
                    }
                });
                scrollPane1.setViewportView(table1);
            }
            paperpp.add(scrollPane1);
            scrollPane1.setBounds(125, 70, 1450, 730);

            //---- refresh ----
            refresh.setText("\u5237\u65b0");
            refresh.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    refreshMouseClicked(e);
                }
            });
            paperpp.add(refresh);
            refresh.setBounds(1150, 10, 150, 50);

            //---- comboBox1 ----
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "\u9898\u540d",
                "\u4f5c\u8005",
                "\u6765\u6e90"
            }));
            comboBox1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
            paperpp.add(comboBox1);
            comboBox1.setBounds(200, 10, 140, 40);

            //---- textField1 ----
            textField1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
            textField1.setForeground(Color.white);
            paperpp.add(textField1);
            textField1.setBounds(370, 10, 535, 50);

            //---- search ----
            search.setText("\u641c\u7d22");
            search.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    searchMouseClicked(e);
                }
            });
            paperpp.add(search);
            search.setBounds(940, 10, 150, 50);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/finalib.jpg")));
            paperpp.add(label1);
            label1.setBounds(new Rectangle(new Point(-10, -10), label1.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < paperpp.getComponentCount(); i++) {
                    Rectangle bounds = paperpp.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = paperpp.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                paperpp.setMinimumSize(preferredSize);
                paperpp.setPreferredSize(preferredSize);
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
    private JPanel paperpp;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton refresh;
    private JComboBox<String> comboBox1;
    private JTextField textField1;
    private JButton search;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
