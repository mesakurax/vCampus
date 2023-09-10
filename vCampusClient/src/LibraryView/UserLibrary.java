/*
 * Created by JFormDesigner on Fri Sep 01 11:27:42 CST 2023
 */


package LibraryView;

import utils.UIStyler;
import entity.Book;
import entity.BookIllegal;
import entity.BookRecord;
import module.BookSystem;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import utils.SocketHelper;
import entity.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Vector;
/**
 * @author 86153
 */
public class UserLibrary extends JPanel{
    String uid;
    SocketHelper socketHelper;
    BookSystem model;
    Book book;
    List<Book> books;
    BookRecord record;
   /* public UserView() {
        initComponents();
    }*/
   private CardLayout card=new CardLayout();

    private JPanel card_panel=new JPanel();  //子布局，代表后面切换的布局都是它
    private introduction one;
    private Color backgroundColor;

    private static Studentbookrecord sturecord;

    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            /*btnInstance.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));*/
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UserLibrary(SocketHelper helper,User uu)
    {
       /* beautify();*/
        initComponents();
        uid=uu.getId();
        beautify();
        UIStyler.setTransparentTable(scrollPane1);
        UIStyler.setBelowButton(searchbook);
        UIStyler.setBelowButton(refresh);
        UIStyler.setBelowButton(borrow);
        button1.setOpaque(false);
        button1.setContentAreaFilled(false);
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);
        table1.setRowHeight(120);
        table1.setOpaque(false);
       // table1.setDefaultRenderer(Object.class, new MyTableCellRenderer());
        button2.setOpaque(false);
        button2.setContentAreaFilled(false);
        button2.setBorderPainted(false);
        button2.setFocusPainted(false);
        button3.setOpaque(false);
        button3.setContentAreaFilled(false);
        button3.setBorderPainted(false);
        button3.setFocusPainted(false);
        button4.setOpaque(false);
        button4.setContentAreaFilled(false);
        button4.setBorderPainted(false);
        button4.setFocusPainted(false);
        button5.setOpaque(false);
        button5.setContentAreaFilled(false);
        button5.setBorderPainted(false);
        button5.setFocusPainted(false);
        button6.setOpaque(false);
        button6.setContentAreaFilled(false);
        button6.setBorderPainted(false);
        button6.setFocusPainted(false);
        button7.setOpaque(false);
        button7.setContentAreaFilled(false);
        button7.setBorderPainted(false);
        button7.setFocusPainted(false);
        button8.setOpaque(false);
        button8.setContentAreaFilled(false);
        button8.setBorderPainted(false);
        button8.setFocusPainted(false);
        button9.setOpaque(false);
        button9.setContentAreaFilled(false);
        button9.setBorderPainted(false);
        button9.setFocusPainted(false);
        setLayout(new BorderLayout()); // 设置布局管理器为 BorderLayout

        DefaultTableModel table = (DefaultTableModel) table1.getModel();
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.getTableHeader().setReorderingAllowed(false);
        table1.getTableHeader().setReorderingAllowed(false);

// 将新的表格实例设置回原始的 table1
        table1.setModel(table);
        this.socketHelper=helper;
        model=new BookSystem(socketHelper);
        this.setLocation(0,0);
        this.label1.setLocation(0,0);
        card_panel.setBounds(0, 0, 1685, 830);
        card_panel.setLayout(card);
        card_panel.setOpaque(false);

// 设置 card_panel 的大小
        card_panel.setPreferredSize(new Dimension(1685, 830));

// 将 card_panel 添加到 CENTER 位置
        add(card_panel, BorderLayout.CENTER);
        card_panel.setOpaque(false);

        // 添加 paperpp 到 card_panel
        liblib.setSize(1685,830);
        liblib.setVisible(true);
        card_panel.add(liblib, "liblib");

        // 初始显示 paperpp
        card.show(card_panel, "liblib");
        refreshTable();
        table1.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());

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
                    book = new Book(null, text, null, null, null, null, 0,null,null);
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
                        for (int j=0;j<books.size();++j) {
                            Vector<Object> rowData = new Vector<>();
                            rowData.add(String.valueOf(books.get(j).getISBN()));

                            if (books.get(j).getImage() != null) {
                                try {
                                    Icon icon = StringtoImage(books.get(j).getImage());
                                    rowData.add(icon);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            rowData.add(books.get(j).getName());
                            rowData.add(books.get(j).getAuthor());
                            rowData.add(books.get(j).getPublisher());
                            rowData.add(books.get(j).getPublishdate());
                            rowData.add(books.get(j).getAddress());
                            rowData.add(books.get(j).getCount());
                            tableModel.addRow(rowData);
                        }
                    }
                }
                if (flag.equals("书名")) {
                    book = new Book(text, null, null, null, null, null, 0,null,null);
                    System.out.println("开始查找");
                    List<Book> rs = model.searchbook(book, 2);
                    if (rs.size()==0) {
                        System.out.println("查找失败");
                        JOptionPane.showMessageDialog(null, "未找到匹配的书籍", "查找失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        books = rs;
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (int j=0;j<books.size();++j) {
                            Vector<Object> rowData = new Vector<>();
                            rowData.add(String.valueOf(books.get(j).getISBN()));

                            if (books.get(j).getImage() != null) {
                                try {
                                    Icon icon = StringtoImage(books.get(j).getImage());
                                    rowData.add(icon);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            rowData.add(books.get(j).getName());
                            rowData.add(books.get(j).getAuthor());
                            rowData.add(books.get(j).getPublisher());
                            rowData.add(books.get(j).getPublishdate());
                            rowData.add(books.get(j).getAddress());
                            rowData.add(books.get(j).getCount());
                            tableModel.addRow(rowData);
                        }
                    }

                }
                if (flag.equals("作者")) {
                    book = new Book(null, null, text, null, null, null, 0,null,null);
                    System.out.println("开始查找");
                    List<Book> rs = model.searchbook(book, 3);
                    if (rs.size()==0) {
                        System.out.println("查找失败");
                        JOptionPane.showMessageDialog(null, "未找到匹配的书籍", "查找失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        books = rs;
                        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
                        tableModel.setRowCount(0); // 清空表格数据
                        for (int j=0;j<books.size();++j) {
                            Vector<Object> rowData = new Vector<>();
                            rowData.add(String.valueOf(books.get(j).getISBN()));

                            if (books.get(j).getImage() != null) {
                                try {
                                    Icon icon = StringtoImage(books.get(j).getImage());
                                    rowData.add(icon);
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            rowData.add(books.get(j).getName());
                            rowData.add(books.get(j).getAuthor());
                            rowData.add(books.get(j).getPublisher());
                            rowData.add(books.get(j).getPublishdate());
                            rowData.add(books.get(j).getAddress());
                            rowData.add(books.get(j).getCount());
                            tableModel.addRow(rowData);
                        }
                    }
                }
            }

        }catch(HeadlessException ex){
                throw new RuntimeException(ex);
            }
        }



    public void refreshTable() {
        book = new Book(null, null, null, null, null, null, 0,null,null);
        List<Book> rs = model.searchbook(book, 1);
        table1.clearSelection();
        table1.setRowHeight(120);
        books = rs;
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0); // 清空表格数据
        for (int j=0;j<books.size();++j) {
            Vector<Object> rowData = new Vector<>();
            rowData.add(String.valueOf(books.get(j).getISBN()));

            if (books.get(j).getImage() != null) {
                try {
                    Icon icon = StringtoImage(books.get(j).getImage());
                    rowData.add(icon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            rowData.add(books.get(j).getName());
            rowData.add(books.get(j).getAuthor());
            rowData.add(books.get(j).getPublisher());
            rowData.add(books.get(j).getPublishdate());
            rowData.add(books.get(j).getAddress());
            rowData.add(books.get(j).getCount());
            tableModel.addRow(rowData);

        }

    }

    public void refreshTable(String category) {
        book = new Book(null, null, null, null, null, null, 0,null,category);
        List<Book> rs = model.searchbook(book, 5);
        table1.clearSelection();
        table1.setRowHeight(120);
        books = rs;
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0); // 清空表格数据
        for (int j=0;j<books.size();++j) {
            Vector<Object> rowData = new Vector<>();
            rowData.add(String.valueOf(books.get(j).getISBN()));

            if (books.get(j).getImage() != null) {
                try {
                    Icon icon = StringtoImage(books.get(j).getImage());
                    rowData.add(icon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            rowData.add(books.get(j).getName());
            rowData.add(books.get(j).getAuthor());
            rowData.add(books.get(j).getPublisher());
            rowData.add(books.get(j).getPublishdate());
            rowData.add(books.get(j).getAddress());
            rowData.add(books.get(j).getCount());
            tableModel.addRow(rowData);

        }

    }






    private void refreshMouseClicked(MouseEvent e) {
        // TODO add your code here
        textField1.setText("");//必须加
        table1.clearSelection();
        refreshTable();

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
            BookIllegal illegal = new BookIllegal(0, uid, null, null, null, null, null, null, 0, false);
            if (model.searchillegal(illegal, 6).size() >= 5) {
                JOptionPane.showMessageDialog(null, "借阅失败，请先处理违章", "", JOptionPane.ERROR_MESSAGE);

            /*if(model.searchrecord())    */
            }
            if (model.searchrecord(new BookRecord(null,null,null,null,null,false,null,null,uid,0),4).size()==3)
                JOptionPane.showMessageDialog(null, "借阅失败，最多借阅三本书", "", JOptionPane.ERROR_MESSAGE);
                else {
                int row = table1.getSelectedRow();
                String bookname = (String) table1.getValueAt(row, 2);
                String borrowtime = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
                String deadline = Deadline(borrowtime);

                record = new BookRecord(bookname, (String) table1.getValueAt(row, 0), (String) table1.getValueAt(row, 3), (String) table1.getValueAt(row, 4),
                        (String) table1.getValueAt(row, 6),
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
    }

    private Icon StringtoImage(String ss) throws IOException {
        BASE64Decoder decoder=new BASE64Decoder();
        byte[]imagebyte=decoder.decodeBuffer(ss);
        BufferedImage imag= ImageIO.read(new ByteArrayInputStream(imagebyte));
        if(imag!=null){
            Image resultingImage = imag.getScaledInstance(136, 120, Image.SCALE_AREA_AVERAGING);
            BufferedImage outputImage = new BufferedImage(136, 120, BufferedImage.TYPE_INT_RGB);
            outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
            ImageIcon icon=new ImageIcon(outputImage);
            return icon;
        }
        else return null;
    }
    private String ImagetoString(String path) throws IOException {
        FileInputStream fis=new FileInputStream(path);
        BufferedInputStream bis=new BufferedInputStream(fis);
        ByteArrayOutputStream bos= new ByteArrayOutputStream();
        byte[]buff=new byte[1024];
        int len=0;
        while((len=fis.read(buff))!=-1)bos.write(buff,0,len);

        byte[]rs=bos.toByteArray();
        BASE64Encoder encoder=new BASE64Encoder();
        String str=encoder.encode(rs).trim();
        return str;
    }



    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        textField1.setText("");//必须加
        table1.clearSelection();
        refreshTable(button1.getText());
    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        textField1.setText("");//必须加
        table1.clearSelection();
        refreshTable(button2.getText());
    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        textField1.setText("");//必须加
        table1.clearSelection();
        refreshTable(button3.getText());
    }

    private void button4MouseClicked(MouseEvent e) {
        // TODO add your code here
        textField1.setText("");//必须加
        table1.clearSelection();
        refreshTable(button4.getText());
    }

    private void button5MouseClicked(MouseEvent e) {
        // TODO add your code here
        textField1.setText("");//必须加
        table1.clearSelection();
        refreshTable(button5.getText());
    }

    private void button6MouseClicked(MouseEvent e) {
        // TODO add your code here
        textField1.setText("");//必须加
        table1.clearSelection();
        refreshTable(button6.getText());
    }

    private void button7MouseClicked(MouseEvent e) {
        // TODO add your code here
        textField1.setText("");//必须加
        table1.clearSelection();
        refreshTable(button7.getText());
    }

    private void button8MouseClicked(MouseEvent e) {
        // TODO add your code here
        textField1.setText("");//必须加
        table1.clearSelection();
        refreshTable(button8.getText());
    }

    private void button9MouseClicked(MouseEvent e) {
        // TODO add your code here
        textField1.setText("");//必须加
        table1.clearSelection();
        refreshTable(button9.getText());
    }

    private void table1MousePressed(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int row = table1.getSelectedRow();
            book = new Book((String) table1.getValueAt(row, 2), (String) table1.getValueAt(row, 0), null, null, null, null, 0, null,null);
            one=new introduction(socketHelper,book);
            card_panel.setLayout(card);
            card_panel.add(one,"p0");
            card.show(card_panel,"p0");
            JButton returnButton = one.getReturnButton();
            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardLayout cardLayout = (CardLayout) card_panel.getLayout();
                    cardLayout.show(card_panel, "liblib" );
                }
            });
            refreshTable();
        }
    }















    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        liblib = new JPanel();
        panel1 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button6 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button7 = new JButton();
        button9 = new JButton();
        button8 = new JButton();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        comboBox1 = new JComboBox<>();
        searchbook = new JButton();
        textField1 = new JTextField();
        refresh = new JButton();
        borrow = new JButton();
        label1 = new JLabel();

        //======== liblib ========
        {
            liblib.setMinimumSize(new Dimension(1685, 1030));
            liblib.setPreferredSize(new Dimension(1685, 1030));
            liblib.setLayout(null);

            //======== panel1 ========
            {
                panel1.setOpaque(false);

                //---- button1 ----
                button1.setText("\u54f2\u5b66\u3001\u5fc3\u7406\u5b66");
                button1.setBorderPainted(false);
                button1.setBorder(null);
                button1.setFocusPainted(false);
                button1.setFont(new Font("\u65b0\u5b8b\u4f53", Font.BOLD, 20));
                button1.setForeground(Color.white);
                button1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button1MouseClicked(e);
                    }
                });

                //---- button2 ----
                button2.setText("\u5b97\u6559\u3001\u795e\u5b66");
                button2.setOpaque(false);
                button2.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 20));
                button2.setForeground(Color.white);
                button2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button2MouseClicked(e);
                    }
                });

                //---- button3 ----
                button3.setText("\u81ea\u7136\u79d1\u5b66");
                button3.setOpaque(false);
                button3.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 20));
                button3.setForeground(Color.white);
                button3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button3MouseClicked(e);
                    }
                });

                //---- button6 ----
                button6.setText("\u827a\u672f\u3001\u4f11\u95f2");
                button6.setOpaque(false);
                button6.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 20));
                button6.setForeground(Color.white);
                button6.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button6MouseClicked(e);
                    }
                });

                //---- button4 ----
                button4.setText("\u793e\u4f1a\u79d1\u5b66");
                button4.setOpaque(false);
                button4.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 20));
                button4.setForeground(Color.white);
                button4.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button4MouseClicked(e);
                    }
                });

                //---- button5 ----
                button5.setText("\u533b\u5b66");
                button5.setOpaque(false);
                button5.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 20));
                button5.setForeground(Color.white);
                button5.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button5MouseClicked(e);
                    }
                });

                //---- button7 ----
                button7.setText("\u79d1\u5b66\u4e0e\u77e5\u8bc6");
                button7.setOpaque(false);
                button7.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 20));
                button7.setForeground(Color.white);
                button7.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button7MouseClicked(e);
                    }
                });

                //---- button9 ----
                button9.setText("\u5730\u7406\u3001\u5386\u53f2");
                button9.setOpaque(false);
                button9.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 20));
                button9.setForeground(Color.white);
                button9.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button9MouseClicked(e);
                    }
                });

                //---- button8 ----
                button8.setText("\u8bed\u8a00\u3001\u6587\u5b66");
                button8.setOpaque(false);
                button8.setFont(new Font("\u5b8b\u4f53", Font.BOLD, 20));
                button8.setForeground(Color.white);
                button8.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button8MouseClicked(e);
                    }
                });

                GroupLayout panel1Layout = new GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addGap(39, 39, 39)
                            .addGroup(panel1Layout.createParallelGroup()
                                .addComponent(button8, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button9, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button6, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button4, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button5, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button7, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 21, Short.MAX_VALUE))
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup()
                        .addGroup(panel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button3, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(button6, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button4, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button5, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button7, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button9, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(button8, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(51, Short.MAX_VALUE))
                );
            }
            liblib.add(panel1);
            panel1.setBounds(70, 70, 210, 645);

            //======== scrollPane1 ========
            {

                //---- table1 ----
                table1.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, null, null, null},
                    },
                    new String[] {
                        "ISBN", "\u5c01\u9762", "\u4e66\u540d", "\u4f5c\u8005", "\u51fa\u7248\u793e", "\u51fa\u7248\u65e5\u671f", "\u9986\u85cf\u5730", "\u53ef\u501f\u6570\u91cf"
                    }
                ));
                table1.setOpaque(false);
                table1.setBorder(BorderFactory.createEmptyBorder());
                table1.setFont(new Font("\u6977\u4f53", Font.BOLD, 20));
                table1.setForeground(Color.white);
                table1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        table1MousePressed(e);
                    }
                });
                scrollPane1.setViewportView(table1);
            }
            liblib.add(scrollPane1);
            scrollPane1.setBounds(290, 70, 1335, 730);

            //---- comboBox1 ----
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "ISBN",
                "\u4e66\u540d",
                "\u4f5c\u8005"
            }));
            comboBox1.setForeground(Color.white);
            comboBox1.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
            comboBox1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
            liblib.add(comboBox1);
            comboBox1.setBounds(330, 10, 135, 45);

            //---- searchbook ----
            searchbook.setText("\u67e5\u8be2");
            searchbook.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    searchbookMouseClicked(e);
                }
            });
            liblib.add(searchbook);
            searchbook.setBounds(1075, 10, 150, 50);

            //---- textField1 ----
            textField1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
            textField1.setForeground(Color.white);
            liblib.add(textField1);
            textField1.setBounds(510, 10, 530, 50);

            //---- refresh ----
            refresh.setText("\u5237\u65b0");
            refresh.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    refreshMouseClicked(e);
                }
            });
            liblib.add(refresh);
            refresh.setBounds(1240, 10, 150, 50);

            //---- borrow ----
            borrow.setText("\u501f\u9605");
            borrow.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    borrowMouseClicked(e);
                }
            });
            liblib.add(borrow);
            borrow.setBounds(1405, 10, 150, 50);

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/finalib.jpg")));
            liblib.add(label1);
            label1.setBounds(new Rectangle(new Point(0, -10), label1.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < liblib.getComponentCount(); i++) {
                    Rectangle bounds = liblib.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = liblib.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                liblib.setMinimumSize(preferredSize);
                liblib.setPreferredSize(preferredSize);
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
    private JPanel liblib;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button6;
    private JButton button4;
    private JButton button5;
    private JButton button7;
    private JButton button9;
    private JButton button8;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JComboBox<String> comboBox1;
    private JButton searchbook;
    private JTextField textField1;
    private JButton refresh;
    private JButton borrow;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

