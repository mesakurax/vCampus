/*
 * Created by JFormDesigner on Fri Sep 01 11:27:42 CST 2023
 */


package LibraryView;

import entity.Book;
import entity.BookRecord;
import module.BookSystem;
import sun.misc.BASE64Decoder;
import utils.SocketHelper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import javax.swing.JOptionPane;

/**
 * @author 86153
 */
public class UserLibrary extends JPanel {
    String uid="dhb";
    SocketHelper socketHelper;
    BookSystem model;
    Book book;
    List<Book> books;
    BookRecord record;
   /* public UserView() {
        initComponents();
    }*/
    private static Studentbookrecord sturecord;

    public UserLibrary(SocketHelper helper)
    {
       /* beautify();*/
        initComponents();
        this.socketHelper=helper;
        model=new BookSystem(socketHelper);
        book = new Book(null, null, null, null, null, null, 0);
        List<Book> rs = model.searchbook(book, 1);

        books = rs;
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0); // 清空表格数据
        for (Book book : books) {

            Object[] rowData = {book.getISBN(), book.getImage(), book.getName(), book.getAuthor(), book.getPublisher(), book.getPublishdate(), book.getAddress(), book.getCount(), ""};

            if (book.getImage() != null) {
                try {
                    Icon icon = StringtoImage(book.getImage());
                    Object[] newRowData = new Object[rowData.length];
                    System.arraycopy(rowData, 0, newRowData, 0, rowData.length - 1); // 复制原始数据到新数组
                    newRowData[rowData.length - 1] = icon; // 将图标对象放入新数组的最后一个位置
                    rowData = newRowData; // 使用新数组作为行数据
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            tableModel.addRow(rowData); // 添加行数据
        }
    }

    private void searchbookMouseClicked(MouseEvent e) {
        // TODO add your code here
        String flag = comboBox1.getSelectedItem().toString();
        System.out.println(flag);
        String text = textField1.getText();
        try {
            if(textField1.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "请输入查找内容", "查找失败", JOptionPane.ERROR_MESSAGE);
            }
            else {
                if (flag.equals("ISBN")) {
                    book = new Book(null, text, null, null, null, null, 0);
                    System.out.println("开始查找");
                    List<Book> rs = model.searchbook(book, 4);
                    if (rs.size() == 0) {
                        textField1.setText("");//必须加
                        table1.clearSelection();
                        JOptionPane.showMessageDialog(null, "未找到匹配的书籍", "查找失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        books = rs;
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (Book book : books) {
                            Object[] rowData = {book.getISBN(), book.getImage(), book.getName(), book.getAuthor(), book.getPublisher(), book.getPublishdate(), book.getAddress(), book.getCount()};
                            tableModel.addRow(rowData); // 添加行数据
                        }
                    }
                }
                if (flag.equals("书名")) {
                    book = new Book(text, null, null, null, null, null, 0);
                    System.out.println("开始查找");
                    List<Book> rs = model.searchbook(book, 2);
                    if (rs.size()==0) {
                        System.out.println("查找失败");
                        JOptionPane.showMessageDialog(null, "未找到匹配的书籍", "查找失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        books = rs;
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (Book book : books) {
                            Object[] rowData = {book.getISBN(), book.getImage(), book.getName(), book.getAuthor(), book.getPublisher(), book.getPublishdate(), book.getAddress(), book.getCount()};
                            tableModel.addRow(rowData); // 添加行数据
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
                            DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                            tableModel.setRowCount(0); // 清空表格数据
                            for (Book book : books) {
                                Object[] rowData = {book.getISBN(), book.getImage(), book.getName(), book.getAuthor(), book.getPublisher(), book.getPublishdate(), book.getAddress(), book.getCount()};
                            tableModel.addRow(rowData); // 添加行数据
                        }
                    }
                }
            }

        }catch(HeadlessException ex){
                throw new RuntimeException(ex);
            }
        }



    public void refreshTable() {
        book = new Book(null, null, null, null, null, null, 0);
        List<Book> rs = model.searchbook(book, 1);
        table1.clearSelection();
        books = rs;
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0); // 清空表格数据
        for (Book book : books) {
            Object[] rowData = {book.getISBN(), book.getImage(), book.getName(), book.getAuthor(), book.getPublisher(), book.getPublishdate(), book.getAddress(), book.getCount()};
            tableModel.addRow(rowData); // 添加行数据
        }
    }
    



   /* public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame frame = new JFrame();
                    UserLibrary stuAdmin = new UserLibrary();

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
    }*/

    private void refreshMouseClicked(MouseEvent e) {
        // TODO add your code here
        book = new Book(null, null, null, null, null, null, 0);
        List<Book> rs = model.searchbook(book, 1);
        textField1.setText("");//必须加
        table1.clearSelection();
        books = rs;
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0); // 清空表格数据
        for (int j=0;j<books.size();++j) {
            Vector<Object> rowData = new Vector<>();
            rowData.add(books.get(j).getISBN());
            if(books.get(j).getImage()!=null)
                try{
                    Icon icon = StringtoImage(books.get(j).getImage());
                    rowData.add(icon);
                }catch(IOException ex)
                {ex.printStackTrace();}
            rowData.add(books.get(j).getName());
            rowData.add(books.get(j).getAuthor());
            rowData.add(books.get(j).getPublisher());
            rowData.add(books.get(j).getPublishdate());
            rowData.add(books.get(j).getAddress());
            rowData.add(String.valueOf(books.get(j).getCount()));

            tableModel.addRow(rowData);
            /*Object[] rowData = {book.getISBN(), book.getImage(), book.getName(), book.getAuthor(), book.getPublisher(), book.getPublishdate(), book.getAddress(), book.getCount()};*/
        }

    }
    private String Deadline(String date) {
        LocalDate today = LocalDate.parse(date);
        today = today.plusDays(21);
        return today.toString();
    }

    private void borrowMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (table1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "请选中指定图书", "错误", JOptionPane.ERROR_MESSAGE);
        } else {
            int row = table1.getSelectedRow();
            String bookname = (String) table1.getValueAt(row, 2);
            String borrowtime = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
            String deadline = Deadline(borrowtime);

            record = new BookRecord(bookname, (String) table1.getValueAt(row, 0), (String) table1.getValueAt(row,3),(String) table1.getValueAt(row, 4),
                    (String) table1.getValueAt(row,6),
                    false,
                    deadline, borrowtime, uid, 0);
            boolean falg = model.userborrow(record);
            if (falg) {
                refreshTable();
                JOptionPane.showMessageDialog(null, "借阅成功", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "借阅失败，当前书无库存", "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Icon StringtoImage(String ss) throws IOException {
        BASE64Decoder decoder=new BASE64Decoder();
        byte[]imagebyte=decoder.decodeBuffer(ss);
        BufferedImage imag= ImageIO.read(new ByteArrayInputStream(imagebyte));
        if(imag!=null){
            Image resultingImage = imag.getScaledInstance(100, 130, Image.SCALE_AREA_AVERAGING);
            BufferedImage outputImage = new BufferedImage(100, 130, BufferedImage.TYPE_INT_RGB);
            outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
            ImageIcon icon=new ImageIcon(outputImage);
            return icon;
        }
        else return null;
    }
    private void myrecordMouseClicked(MouseEvent e) {
        Studentbookrecord studentRecord = new Studentbookrecord(socketHelper);

        // 创建新的顶层容器（例如 JFrame）
        JFrame newFrame = new JFrame("New Frame");

        // 添加 Studentbookrecord 实例到新的顶层容器中
        newFrame.getContentPane().add(studentRecord);

        // 设置新的顶层容器的属性
        newFrame.setSize(1000, 500); // 设置大小
        newFrame.setLocationRelativeTo(null); // 居中显示
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭时只关闭当前窗口
        newFrame.setVisible(true); // 设置可见性
        refreshTable();
    }














    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        comboBox1 = new JComboBox<>();
        searchbook = new JButton();
        textField1 = new JTextField();
        refresh = new JButton();
        borrow = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(1685, 1030));
        setPreferredSize(new Dimension(1685, 1030));
        setLayout(null);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "ISBN", "\u56fe\u7247", "\u4e66\u540d", "\u4f5c\u8005", "\u51fa\u7248\u793e", "\u51fa\u7248\u65e5\u671f", "\u9986\u85cf\u5730", "\u53ef\u501f\u6570\u91cf"
                }
            ));
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(280, 270, 1090, 567);

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "ISBN",
            "\u4e66\u540d",
            "\u4f5c\u8005"
        }));
        add(comboBox1);
        comboBox1.setBounds(285, 190, 105, comboBox1.getPreferredSize().height);

        //---- searchbook ----
        searchbook.setText("\u67e5\u8be2");
        searchbook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchbookMouseClicked(e);
            }
        });
        add(searchbook);
        searchbook.setBounds(new Rectangle(new Point(935, 190), searchbook.getPreferredSize()));
        add(textField1);
        textField1.setBounds(400, 190, 525, textField1.getPreferredSize().height);

        //---- refresh ----
        refresh.setText("\u5237\u65b0");
        refresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshMouseClicked(e);
            }
        });
        add(refresh);
        refresh.setBounds(new Rectangle(new Point(1055, 190), refresh.getPreferredSize()));

        //---- borrow ----
        borrow.setText("\u501f\u9605");
        borrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                borrowMouseClicked(e);
            }
        });
        add(borrow);
        borrow.setBounds(new Rectangle(new Point(1175, 190), borrow.getPreferredSize()));

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
    private JTable table1;
    private JComboBox<String> comboBox1;
    private JButton searchbook;
    private JTextField textField1;
    private JButton refresh;
    private JButton borrow;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

