/*
 * Created by JFormDesigner on Sun Sep 03 23:02:03 CST 2023
 */

package LibraryView;

import java.awt.event.*;
import entity.Book;
import entity.BookRecord;
import module.BookSystem;
import sun.misc.BASE64Encoder;
import utils.SocketHelper;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.*;
import java.util.Base64;

/**
 * @author 86153
 */
public class BookInfo extends JPanel{
    SocketHelper socketHelper;
    String uid="dhb";
    BookSystem model;
    Book book;
    List<Book> books;
    BookRecord record;
    List<BookRecord> records;
    public BookInfo(SocketHelper helper) {

        initComponents();
        bookinfo.setVisible(true);
        this.socketHelper=helper;
        setLayout(new BorderLayout()); // 设置布局管理器为BorderLayout
        add(bookinfo, BorderLayout.CENTER); // 将myrecord添加到CENTER位置
        model=new BookSystem(socketHelper);
        modify.setEnabled(false);
    }
    public BookInfo(SocketHelper helper,Book temp)
    {
        initComponents();
        bookinfo.setVisible(true);
        this.socketHelper=helper;
        setLayout(new BorderLayout()); // 设置布局管理器为BorderLayout
        add(bookinfo, BorderLayout.CENTER); // 将myrecord添加到CENTER位置
        model=new BookSystem(socketHelper);
        bookname.setText(temp.getName());
        ISBN1.setText(temp.getISBN());
        author.setText(temp.getAuthor());
        publisher.setText(temp.getPublisher());
        pubdate.setText(temp.getPublishdate());
        count.setText(Integer.toString(temp.getCount()));
        address.setText(temp.getAddress());
        image.setText(temp.getImage());
        sure.setEnabled(false);

    }

    private void sureMouseClicked(MouseEvent e) {
        // TODO add your code here
        String name = bookname.getText();
        String ISBN = ISBN1.getText();
        String Author = author.getText();
        String Publisher = publisher.getText();
        String Publishdate = pubdate.getText();
        String Count = count.getText();
        String Address = address.getText();
        String Image=new String();
        try{ Image=ImagetoString(image.getText());}catch(IOException e1){e1.printStackTrace();}
        if (name.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "书名不能为空！");
            return;
        }
        if (ISBN.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "ISBN号不能为空！");
            return;
        }
        if (Author.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "作者不能为空！");
            return;
        }
        if (Publisher.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "出版社不能为空！");
            return;
        }
        if (Publishdate.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "出版日期不能为空！");
            return;
        }
        int amout = Integer.parseInt(Count);
        if (amout <= 0 || amout != Double.valueOf(amout).intValue()) {
            JOptionPane.showMessageDialog((Component) this, "请输入合法的库存!");
            return;
        }
        if (Address.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "馆藏地不能为空!");
            return;
        }
        if (Image.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "图片不能为空！");
            return;
        }
        System.out.println("addbook");
        try {
            book = new Book(name, ISBN, Author, Publisher, Publishdate, Address, amout, Image);
            boolean flag = model.admin_addBook(book);
            if (flag) {
                JOptionPane.showMessageDialog((Component) this, "录入成功！");
            } else {
                JOptionPane.showMessageDialog((Component) this, "录入失败！");
            }


        } catch (HeadlessException ex) {
            throw new RuntimeException(ex);
        }
        closeFrame();
    }
    private JFrame getMainFrame() {
        return (JFrame) SwingUtilities.getWindowAncestor(this);
    }

    private void closeFrame() {
        JFrame frame = getMainFrame();
        if (frame != null) {
            frame.dispose();
        }
    }

    private void cancelMouseClicked(MouseEvent e) {
        // TODO add your code here
        closeFrame();
    }

    private void modifyMouseClicked(MouseEvent e) {
        // TODO add your code here
        String name = bookname.getText();
        String ISBN = ISBN1.getText();
        String Author = author.getText();
        String Publisher = publisher.getText();
        String Publishdate = pubdate.getText();
        String Count = count.getText();
        String Address = address.getText();
        String Image = image.getText();
        if (name.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "书名不能为空！");
            return;
        }
        if (ISBN.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "ISBN号不能为空！");
            return;
        }
        if (Author.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "作者不能为空！");
            return;
        }
        if (Publisher.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "出版社不能为空！");
            return;
        }
        if (Publishdate.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "出版日期不能为空！");
            return;
        }
        int amout = Integer.parseInt(Count);
        if (amout <= 0 || amout != Double.valueOf(amout).intValue()) {
            JOptionPane.showMessageDialog((Component) this, "请输入合法的库存!");
            return;
        }
        if (Address.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "馆藏地不能为空!");
            return;
        }
        if (Image.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "图片不能为空！");
            return;
        }
        System.out.println("modifybook");
        try {
            book = new Book(name, ISBN, Author, Publisher, Publishdate, Address, amout, Image);
            boolean flag = model.admin_modify(book);
            if (flag) {
                JOptionPane.showMessageDialog((Component) this, "修改成功！");
            } else {
                JOptionPane.showMessageDialog((Component) this, "修改失败！");
            }


        } catch (HeadlessException ex) {
            throw new RuntimeException(ex);
        }
        closeFrame();
    }

/*    private String ImagetoString(String path) throws IOException {
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
    }*/
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

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        bookinfo = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        ISBN1 = new JTextField();
        author = new JTextField();
        bookname = new JTextField();
        publisher = new JTextField();
        pubdate = new JTextField();
        count = new JTextField();
        address = new JTextField();
        image = new JTextField();
        sure = new JButton();
        cancel = new JButton();
        modify = new JButton();

        //======== bookinfo ========
        {
            bookinfo.setLayout(null);

            //---- label1 ----
            label1.setText("ISBN\uff1a");
            bookinfo.add(label1);
            label1.setBounds(85, 25, 140, 75);

            //---- label2 ----
            label2.setText("\u4e66\u540d");
            bookinfo.add(label2);
            label2.setBounds(new Rectangle(new Point(85, 90), label2.getPreferredSize()));

            //---- label3 ----
            label3.setText("\u4f5c\u8005");
            bookinfo.add(label3);
            label3.setBounds(new Rectangle(new Point(85, 125), label3.getPreferredSize()));

            //---- label4 ----
            label4.setText("\u51fa\u7248\u793e");
            bookinfo.add(label4);
            label4.setBounds(new Rectangle(new Point(80, 160), label4.getPreferredSize()));

            //---- label5 ----
            label5.setText("\u51fa\u7248\u65e5\u671f");
            bookinfo.add(label5);
            label5.setBounds(new Rectangle(new Point(85, 205), label5.getPreferredSize()));

            //---- label6 ----
            label6.setText("\u6570\u91cf");
            bookinfo.add(label6);
            label6.setBounds(new Rectangle(new Point(85, 245), label6.getPreferredSize()));

            //---- label7 ----
            label7.setText("\u9986\u85cf\u5730");
            bookinfo.add(label7);
            label7.setBounds(new Rectangle(new Point(85, 280), label7.getPreferredSize()));

            //---- label8 ----
            label8.setText("\u56fe\u7247");
            bookinfo.add(label8);
            label8.setBounds(new Rectangle(new Point(95, 325), label8.getPreferredSize()));
            bookinfo.add(ISBN1);
            ISBN1.setBounds(245, 55, 150, ISBN1.getPreferredSize().height);
            bookinfo.add(author);
            author.setBounds(250, 130, 140, author.getPreferredSize().height);
            bookinfo.add(bookname);
            bookname.setBounds(250, 95, 145, bookname.getPreferredSize().height);
            bookinfo.add(publisher);
            publisher.setBounds(250, 170, 140, 30);
            bookinfo.add(pubdate);
            pubdate.setBounds(255, 210, 135, 30);
            bookinfo.add(count);
            count.setBounds(255, 245, 145, 30);
            bookinfo.add(address);
            address.setBounds(260, 280, 145, 30);
            bookinfo.add(image);
            image.setBounds(265, 315, 140, 30);

            //---- sure ----
            sure.setText("\u786e\u8ba4\u6dfb\u52a0");
            sure.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    sureMouseClicked(e);
                }
            });
            bookinfo.add(sure);
            sure.setBounds(new Rectangle(new Point(450, 380), sure.getPreferredSize()));

            //---- cancel ----
            cancel.setText("\u53d6\u6d88");
            cancel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cancelMouseClicked(e);
                }
            });
            bookinfo.add(cancel);
            cancel.setBounds(new Rectangle(new Point(585, 380), cancel.getPreferredSize()));

            //---- modify ----
            modify.setText("\u786e\u8ba4\u4fee\u6539");
            modify.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    modifyMouseClicked(e);
                }
            });
            bookinfo.add(modify);
            modify.setBounds(new Rectangle(new Point(365, 380), modify.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < bookinfo.getComponentCount(); i++) {
                    Rectangle bounds = bookinfo.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = bookinfo.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                bookinfo.setMinimumSize(preferredSize);
                bookinfo.setPreferredSize(preferredSize);
            }
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel bookinfo;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JTextField ISBN1;
    private JTextField author;
    private JTextField bookname;
    private JTextField publisher;
    private JTextField pubdate;
    private JTextField count;
    private JTextField address;
    private JTextField image;
    private JButton sure;
    private JButton cancel;
    private JButton modify;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
