/*
 * Created by JFormDesigner on Sun Sep 03 15:04:49 CST 2023
 */

package LibraryView;

import java.awt.event.*;
import javax.swing.table.*;
import entity.Book;
import entity.BookRecord;
import module.BookSystem;
import utils.SocketHelper;

import java.awt.*;
import java.util.List;
import java.util.Vector;
import javax.swing.*;

/**
 * @author 86153
 */
public class AdminLibrary extends JPanel {
    String uid="dhb";
    SocketHelper socketHelper;
    BookSystem model;
    Book book;
    List<Book> books;
    BookRecord record;
    List<BookRecord> records;
    public AdminLibrary(SocketHelper socketHelper) {

        initComponents();

        /* beautify();*/
        this.socketHelper=socketHelper;
        model=new BookSystem(socketHelper);
        refreshtable();
        }

    public void refreshtable()
    {
        book = new Book(null, null, null, null, null, null, 0);
        List<Book> rs = model.searchbook(book, 1);
        books = rs;
        DefaultTableModel tableModel = (DefaultTableModel) infotable.getModel();
        tableModel.setRowCount(0); // 清空表格数据
        textField1.setText("");//必须加
        infotable.clearSelection();
        for (Book tmp : books) {
            Object[] rowData={tmp.getISBN(), tmp.getName(),tmp.getAuthor(),tmp.getPublisher(),tmp.getAddress(),"---", "---", "---",(tmp.getCount()>0)?"可借阅":"不可借阅"};
            if(tmp.getCount()>0)
            {
                for(int i=0;i<tmp.getCount();i++)
                    tableModel.addRow(rowData);
            }
            BookRecord bookb = new BookRecord(tmp.getName(), tmp.getISBN(), tmp.getAuthor(),null, null, false,null,null,null,0);
            records = model.searchrecord(bookb, 2);
            for (int j = 0; j < records.size(); ++j) {
                Vector<String> rowData2 = new Vector<>();
                rowData2.add(String.valueOf(bookb.getISBN()));
                rowData2.add(bookb.getName());
                rowData2.add(records.get(j).getAuthor());
                rowData2.add(records.get(j).getPublisher());
                rowData2.add(records.get(j).getAddress());
                rowData2.add(records.get(j).getUserID());
                rowData2.add(records.get(j).getBorrowtime());
                rowData2.add(records.get(j).getDeadline());

                rowData2.add((records.get(j).getStatus()) ? "可借阅" : "不可借阅");
                tableModel.addRow(rowData2);
            }

        }


    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    SocketHelper socketHelper=new SocketHelper();
                    socketHelper.getConnection(socketHelper.ip,socketHelper.port);
                    socketHelper.getOs().writeInt(1);
                    socketHelper.getOs().flush();
                    AdminLibrary stuAdmin = new AdminLibrary(socketHelper);

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

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        refreshtable();
    }

    private void searchadminMouseClicked(MouseEvent e) {
        String text=textField1.getText();
        String flag = comboBox1.getSelectedItem().toString();
        DefaultTableModel tableModel=(DefaultTableModel) infotable.getModel();
        tableModel.setRowCount(0);
        try {
            if (textField1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "请输入查找内容", "查找失败", JOptionPane.ERROR_MESSAGE);
            } else {
                if (flag.equals("ISBN")) {
                    book = new Book(null, text, null, null, null, null, 0);
                    List<Book> rs = model.searchbook(book, 4);
                    if (rs.size() == 0) {
                        System.out.println("查找失败");
                        JOptionPane.showMessageDialog(null, "未找到匹配信息", "查找失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        books = rs;

                        for (Book tmp : books) {
                            Object[] rowData = {tmp.getISBN(), tmp.getName(), tmp.getAuthor(), tmp.getPublisher(), "---", "---", "---", (tmp.getCount() > 0) ? "可借阅" : "不可借阅"};
                            if (tmp.getCount() > 0) {
                                for (int i = 0; i < tmp.getCount(); i++)
                                    tableModel.addRow(rowData);
                            }
                            BookRecord bookb = new BookRecord(tmp.getName(), tmp.getISBN(), tmp.getAuthor(), null, null, false, null, null,null,0);
                            records = model.searchrecord(bookb, 2);
                            for (int j = 0; j < records.size(); ++j) {

                                Vector<String> rowData2 = new Vector<>();
                                rowData2.add(String.valueOf(bookb.getISBN()));
                                rowData2.add(bookb.getName());
                                rowData2.add(records.get(j).getAuthor());
                                rowData2.add(records.get(j).getPublisher());
                                rowData2.add(records.get(j).getUserID());
                                rowData2.add(records.get(j).getBorrowtime());
                                rowData2.add(records.get(j).getDeadline());

                                rowData2.add((records.get(j).getStatus()) ? "可借阅" : "不可借阅");
                                tableModel.addRow(rowData2);
                            }

                        }

                    }
                }
                if (flag.equals("书名")) {
                    book = new Book(text, null, null, null, null, null, 0);
                    List<Book> rs = model.searchbook(book, 2);
                    if (rs.size() == 0) {
                        System.out.println("查找失败");
                        JOptionPane.showMessageDialog(null, "未找到匹配信息", "查找失败", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        books = rs;
                        for (Book tmp : books) {
                            Object[] rowData = {tmp.getISBN(), tmp.getName(), tmp.getAuthor(), tmp.getPublisher(), "---", "---", "---", (tmp.getCount() > 0) ? "可借阅" : "不可借阅"};
                            if (tmp.getCount() > 0) {
                                for (int i = 0; i < tmp.getCount(); i++)
                                    tableModel.addRow(rowData);
                            }
                            BookRecord bookb = new BookRecord(tmp.getName(), tmp.getISBN(), tmp.getAddress(), null, null, false, null, null,null,0);
                            records = model.searchrecord(bookb, 3);
                            for (int j = 0; j < records.size(); ++j) {

                                Vector<String> rowData2 = new Vector<>();
                                rowData2.add(String.valueOf(bookb.getISBN()));
                                rowData2.add(bookb.getName());
                                rowData2.add(records.get(j).getAuthor());
                                rowData2.add(records.get(j).getPublisher());
                                rowData2.add(records.get(j).getUserID());
                                rowData2.add(records.get(j).getBorrowtime());
                                rowData2.add(records.get(j).getDeadline());

                                rowData2.add((records.get(j).getStatus()) ? "可借阅" : "不可借阅");
                                tableModel.addRow(rowData2);
                            }

                        }
                    }
                }


                if (flag.equals("作者")) {
                    book = new Book(null, null, text, null, null, null, 0);
                    System.out.println("开始查找");
                    List<Book> rs = model.searchbook(book, 3);
                    if (rs.size()==0) {
                        System.out.println("查找失败");
                        JOptionPane.showMessageDialog(null, "未找到匹配的书籍", "查找失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        books = rs;
                        for (Book tmp : books) {
                            Object[] rowData = {tmp.getISBN(), tmp.getName(), tmp.getAuthor(), tmp.getPublisher(), "---", "---", "---", (tmp.getCount() > 0) ? "可借阅" : "不可借阅"};
                            if (tmp.getCount() > 0) {
                                for (int i = 0; i < tmp.getCount(); i++)
                                    tableModel.addRow(rowData);
                            }
                            BookRecord bookb = new BookRecord(tmp.getName(), tmp.getISBN(), tmp.getAuthor(), null, "", false, null, null,null,0);
                            records = model.searchrecord(bookb, 3);
                            for (int j = 0; j < records.size(); ++j) {

                                Vector<String> rowData2 = new Vector<>();
                                rowData2.add(String.valueOf(bookb.getISBN()));
                                rowData2.add(bookb.getName());
                                rowData2.add(records.get(j).getAuthor());
                                rowData2.add(records.get(j).getPublisher());
                                rowData2.add(records.get(j).getUserID());
                                rowData2.add(records.get(j).getBorrowtime());
                                rowData2.add(records.get(j).getDeadline());

                                rowData2.add((records.get(j).getStatus()) ? "可借阅" : "不可借阅");
                                tableModel.addRow(rowData2);
                            }
                        }
                    }
                }
                if (flag.equals("借阅人")) {
                    record = new BookRecord(null, null, null, null, null, false, null,null,text,0);
                    System.out.println("开始查找");
                    List<BookRecord> rs = model.searchrecord(record, 4);
                    if (rs.size()==0) {
                        System.out.println("查找失败");
                        JOptionPane.showMessageDialog(null, "不存在该借阅人", "查找失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        records = rs;
                        for (BookRecord bookRecord : records) {
                            Object[] rowData = {bookRecord.getName(), bookRecord.getISBN(),bookRecord.getAuthor(), bookRecord.getPublisher(), bookRecord.getUserID(), bookRecord.getBorrowtime(),bookRecord.getDeadline() , "不可借阅"};
                            tableModel.addRow(rowData);
                            }
                        }
                    }
                }




        }catch(HeadlessException ex){
            throw new RuntimeException(ex);
        }
    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        BookInfo bookInfo = new BookInfo(socketHelper);

        // 创建新的顶层容器（例如 JFrame）
        JFrame newFrame = new JFrame("New Frame");

        // 添加 Studentbookrecord 实例到新的顶层容器中
        newFrame.getContentPane().add(bookInfo);

        // 设置新的顶层容器的属性
        newFrame.setSize(1000, 500); // 设置大小
        newFrame.setLocationRelativeTo(null); // 居中显示
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭时只关闭当前窗口
        newFrame.setVisible(true); // 设置可见性
        refreshtable();
    }

    private void deleteMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (infotable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "请选中指定图书", "错误", JOptionPane.ERROR_MESSAGE);
        }
        else {
            int row = infotable.getSelectedRow();
            String ISBN=(String) infotable.getValueAt(row,0);
            String name=(String) infotable.getValueAt(row,1);
            String author=(String) infotable.getValueAt(row,2);
            book=new Book(name,ISBN,author,null,null,null,0);
            boolean flag=model.admin_delete(book);
            {
                if(flag)
                {
                    refreshtable();
                    JOptionPane.showMessageDialog((Component) this, "删除成功！");
                }
                else {
                    JOptionPane.showMessageDialog(null,"删除失败，存在借阅记录","错误",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void modifybookMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (infotable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "请选中指定图书", "错误", JOptionPane.ERROR_MESSAGE);
        }
        else
        {

            // 添加必要的文本框和其他组件到 modifyPanel
            int row = infotable.getSelectedRow();
            String ISBN=(String) infotable.getValueAt(row,0);
            String name=(String) infotable.getValueAt(row,1);
            String author=(String) infotable.getValueAt(row,2);
            book=new Book(name,ISBN,author,null,null,null,0,null);
            books=model.searchbook(book,4);
            Book temp=new Book();
            for(int i=0; i<books.size(); ++i){
                if(books.get(i).getISBN().equals(ISBN)){
                    temp = books.get(i);
                    break;
                }
            }
            BookInfo bookInfo = new BookInfo(socketHelper,temp);
            // 创建新的 JFrame 并设置其内容面板为 modifyPanel
            JFrame modifyFrame = new JFrame("Bookinfo");
            modifyFrame.setContentPane(bookInfo);
            modifyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            modifyFrame.pack();
            modifyFrame.setVisible(true);
            refreshtable();

        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        infotable = new JTable();
        button1 = new JButton();
        comboBox1 = new JComboBox<>();
        textField1 = new JTextField();
        searchadmin = new JButton();
        button2 = new JButton();
        delete = new JButton();
        modifybook = new JButton();

        //======== this ========
        setPreferredSize(new Dimension(1685, 1030));
        setLayout(null);

        //======== scrollPane1 ========
        {

            //---- infotable ----
            infotable.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, "", null, null, null, null},
                },
                new String[] {
                    "ISBN", "\u4e66\u540d", "\u4f5c\u8005", "\u51fa\u7248\u793e", "\u9986\u85cf\u5730", "\u501f\u9605\u4eba", "\u501f\u51fa\u65e5\u671f", "\u622a\u6b62\u65e5\u671f", "\u662f\u5426\u53ef\u501f"
                }
            ));
            scrollPane1.setViewportView(infotable);
        }
        add(scrollPane1);
        scrollPane1.setBounds(95, 150, 860, 470);

        //---- button1 ----
        button1.setText("\u5237\u65b0");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        add(button1);
        button1.setBounds(new Rectangle(new Point(90, 70), button1.getPreferredSize()));

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "ISBN",
            "\u4e66\u540d",
            "\u4f5c\u8005",
            "\u501f\u9605\u4eba"
        }));
        add(comboBox1);
        comboBox1.setBounds(new Rectangle(new Point(200, 70), comboBox1.getPreferredSize()));
        add(textField1);
        textField1.setBounds(325, 75, 335, textField1.getPreferredSize().height);

        //---- searchadmin ----
        searchadmin.setText("\u67e5\u8be2");
        searchadmin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchadminMouseClicked(e);
            }
        });
        add(searchadmin);
        searchadmin.setBounds(new Rectangle(new Point(715, 75), searchadmin.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u6dfb\u52a0\u56fe\u4e66");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });
        add(button2);
        button2.setBounds(new Rectangle(new Point(815, 75), button2.getPreferredSize()));

        //---- delete ----
        delete.setText("\u5220\u9664\u56fe\u4e66");
        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteMouseClicked(e);
            }
        });
        add(delete);
        delete.setBounds(new Rectangle(new Point(685, 115), delete.getPreferredSize()));

        //---- modifybook ----
        modifybook.setText("\u4fee\u6539\u56fe\u4e66");
        modifybook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                modifybookMouseClicked(e);
            }
        });
        add(modifybook);
        modifybook.setBounds(new Rectangle(new Point(825, 120), modifybook.getPreferredSize()));

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
    private JScrollPane scrollPane1;
    private JTable infotable;
    private JButton button1;
    private JComboBox<String> comboBox1;
    private JTextField textField1;
    private JButton searchadmin;
    private JButton button2;
    private JButton delete;
    private JButton modifybook;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
