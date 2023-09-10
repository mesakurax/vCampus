/*
 * Created by JFormDesigner on Sun Sep 03 23:02:03 CST 2023
 */

package LibraryView;

import entity.Book;
import entity.BookRecord;
import module.BookSystem;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import utils.SocketHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

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
       // ImageIcon top=new ImageIcon(getClass().getResource("/image/图书管理.png"));
       /* this.setIconImage(top.getImage());*/
        final String[] p = new String[1];
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame frame = new Frame();
                FileDialog fileDialog = new FileDialog(frame, "Select Image", FileDialog.LOAD);
                // 设置文件过滤器，只显示图片文件
                fileDialog.setFile("*.jpg;*.jpeg;*.png;*.gif");
                // 显示文件选择对话框
                fileDialog.setVisible(true);

                // 获取选择的文件路径
                String selectedFile = fileDialog.getFile();
                if (selectedFile != null) {
                    String filePath = fileDialog.getDirectory() + selectedFile;
                    File file = new File(filePath);
                    p[0] = file.getAbsolutePath();
                    image.setText(p[0]);
                    try {
                        label11.setIcon(StringtoImage(ImagetoString(image.getText())));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
    public BookInfo(SocketHelper helper,Book temp) throws IOException {
        initComponents();
        //ImageIcon top=new ImageIcon(getClass().getResource("/image/图书管理.png"));
        /* this.setIconImage(top.getImage());*/
        final String[] p = new String[1];
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame frame = new Frame();
                FileDialog fileDialog = new FileDialog(frame, "Select Image", FileDialog.LOAD);
                // 设置文件过滤器，只显示图片文件
                fileDialog.setFilenameFilter((dir, name) -> {
                    String lowercaseName = name.toLowerCase();
                    return lowercaseName.endsWith(".jpg") || lowercaseName.endsWith(".jpeg") ||
                            lowercaseName.endsWith(".png") || lowercaseName.endsWith(".gif");
                });
                // 显示文件选择对话框
                fileDialog.setVisible(true);

                // 获取选择的文件路径
                String selectedFile = fileDialog.getFile();
                if (selectedFile != null) {
                    String filePath = fileDialog.getDirectory() + selectedFile;
                    File file = new File(filePath);
                    p[0] = file.getAbsolutePath();
                    image.setText(p[0]);
                    try {
                        label11.setIcon(StringtoImage(ImagetoString(image.getText())));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.book=temp;
        label11.setIcon(StringtoImage(book.getImage()));
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
        introduction.setText(temp.getIntro());
        category.setText(temp.getCategory());
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
        String intro=introduction.getText();
        String Category=category.getText();
        String Image=new String();
        try{ Image=ImagetoString(image.getText());
        }catch(IOException e1){e1.printStackTrace();}
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
        if (intro.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "简介不能为空！");
            return;
        }
        if (Category.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "类别不能为空！");
            return;
        }
        System.out.println("addbook");
        try {
            book = new Book(name, ISBN, Author, Publisher, Publishdate, Address, amout, Image,intro,Category);
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

    private void modifyMouseClicked(MouseEvent e) throws IOException {
        // TODO add your code here
        String name = bookname.getText();
        String ISBN = ISBN1.getText();
        String Author = author.getText();
        String Publisher = publisher.getText();
        String Publishdate = pubdate.getText();
        String Count = count.getText();
        String Address = address.getText();
        String Image=new String();
        String Category=category.getText();
        System.out.println(book.getImage());
        if(image.getText()!="")
        {
            try{ Image=ImagetoString(image.getText());
                label11.setIcon(StringtoImage(Image));
            }catch(IOException e1){e1.printStackTrace();}
        }
        String intro=introduction.getText();
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
        if (intro.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "简介不能为空！");
            return;
        }
        if (Category.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "分类不能为空！");
            return;
        }
        System.out.println("modifybook");
        try {
            book = new Book(name, ISBN, Author, Publisher, Publishdate, Address, amout, Image,intro,Category);
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
    private Icon StringtoImage(String ss) throws IOException {
        BASE64Decoder decoder=new BASE64Decoder();
        byte[]imagebyte=decoder.decodeBuffer(ss);
        BufferedImage imag= ImageIO.read(new ByteArrayInputStream(imagebyte));
        if(imag!=null){
            Image resultingImage = imag.getScaledInstance(150, 150, Image.SCALE_AREA_AVERAGING);
            BufferedImage outputImage = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);
            outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
            ImageIcon icon=new ImageIcon(outputImage);
            return icon;
        }
        else return null;
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
        button1 = new JButton();
        label9 = new JLabel();
        scrollPane1 = new JScrollPane();
        introduction = new JTextArea();
        label10 = new JLabel();
        category = new JTextField();
        label11 = new JLabel();
        label12 = new JLabel();

        //======== bookinfo ========
        {
            bookinfo.setLayout(null);

            //---- label1 ----
            label1.setText("ISBN\uff1a");
            bookinfo.add(label1);
            label1.setBounds(200, 100, 90, 50);

            //---- label2 ----
            label2.setText("\u4e66\u540d:");
            bookinfo.add(label2);
            label2.setBounds(200, 150, 90, 50);

            //---- label3 ----
            label3.setText("\u4f5c\u8005");
            bookinfo.add(label3);
            label3.setBounds(200, 200, 90, 50);

            //---- label4 ----
            label4.setText("\u51fa\u7248\u793e");
            bookinfo.add(label4);
            label4.setBounds(200, 250, 90, 50);

            //---- label5 ----
            label5.setText("\u51fa\u7248\u65e5\u671f");
            bookinfo.add(label5);
            label5.setBounds(200, 300, 90, 50);

            //---- label6 ----
            label6.setText("\u6570\u91cf");
            bookinfo.add(label6);
            label6.setBounds(200, 350, 90, 50);

            //---- label7 ----
            label7.setText("\u9986\u85cf\u5730");
            bookinfo.add(label7);
            label7.setBounds(200, 400, 90, 50);

            //---- label8 ----
            label8.setText("\u56fe\u7247");
            bookinfo.add(label8);
            label8.setBounds(200, 450, 90, 50);
            bookinfo.add(ISBN1);
            ISBN1.setBounds(300, 110, 150, ISBN1.getPreferredSize().height);
            bookinfo.add(author);
            author.setBounds(300, 210, 150, author.getPreferredSize().height);
            bookinfo.add(bookname);
            bookname.setBounds(300, 160, 150, bookname.getPreferredSize().height);
            bookinfo.add(publisher);
            publisher.setBounds(300, 260, 150, 30);
            bookinfo.add(pubdate);
            pubdate.setBounds(300, 310, 150, 30);
            bookinfo.add(count);
            count.setBounds(300, 360, 150, 30);
            bookinfo.add(address);
            address.setBounds(300, 410, 150, 30);
            bookinfo.add(image);
            image.setBounds(450, 465, 150, 30);

            //---- sure ----
            sure.setText("\u786e\u8ba4\u6dfb\u52a0");
            sure.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    sureMouseClicked(e);
                }
            });
            bookinfo.add(sure);
            sure.setBounds(new Rectangle(new Point(550, 750), sure.getPreferredSize()));

            //---- cancel ----
            cancel.setText("\u53d6\u6d88");
            cancel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cancelMouseClicked(e);
                }
            });
            bookinfo.add(cancel);
            cancel.setBounds(new Rectangle(new Point(650, 750), cancel.getPreferredSize()));

            //---- modify ----
            modify.setText("\u786e\u8ba4\u4fee\u6539");
            modify.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
modifyMouseClicked(e);} catch (IOException ex) {
    throw new RuntimeException(ex);
}
                }
            });
            bookinfo.add(modify);
            modify.setBounds(new Rectangle(new Point(450, 750), modify.getPreferredSize()));

            //---- button1 ----
            button1.setText("\u4ece\u6587\u4ef6\u5939\u9009\u53d6");
            bookinfo.add(button1);
            button1.setBounds(new Rectangle(new Point(300, 460), button1.getPreferredSize()));

            //---- label9 ----
            label9.setText("\u7b80\u4ecb");
            bookinfo.add(label9);
            label9.setBounds(200, 550, 90, 50);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(introduction);
            }
            bookinfo.add(scrollPane1);
            scrollPane1.setBounds(300, 550, 460, 170);

            //---- label10 ----
            label10.setText("\u5206\u7c7b");
            bookinfo.add(label10);
            label10.setBounds(200, 500, 90, 50);
            bookinfo.add(category);
            category.setBounds(300, 510, 130, category.getPreferredSize().height);
            bookinfo.add(label11);
            label11.setBounds(605, 90, 150, 150);

            //---- label12 ----
            label12.setText("\u56fe\u4e66\u57fa\u672c\u4fe1\u606f");
            label12.setFont(new Font("\u6977\u4f53", Font.BOLD, 48));
            bookinfo.add(label12);
            label12.setBounds(300, 5, 300, 95);

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
    private JButton button1;
    private JLabel label9;
    private JScrollPane scrollPane1;
    private JTextArea introduction;
    private JLabel label10;
    private JTextField category;
    private JLabel label11;
    private JLabel label12;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
