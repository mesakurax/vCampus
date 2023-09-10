/*
 * Created by JFormDesigner on Sat Sep 09 22:28:51 CST 2023
 */

package LibraryView;

import utils.UIStyler;
import entity.Book;
import entity.BookRecord;
import module.BookSystem;
import sun.misc.BASE64Decoder;
import utils.SocketHelper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * @author 86153
 */
public class introduction extends JPanel{
    String uid="dhb";
    SocketHelper socketHelper;
    BookSystem model;
    Book book;
    List<Book> books;
    List<BookRecord> records;
    private boolean iff=false;
    public introduction(SocketHelper helper,Book bk) {

        initComponents();
        UIStyler.setTransparentTable(scrollPane1);
        intro.setVisible(true);
        this.socketHelper=helper;
        model=new BookSystem(socketHelper);
        introduction.setLineWrap(true);
        introduction.setWrapStyleWord(true);
        add(intro, BorderLayout.CENTER); // 将myrecord添加到CENTER位置
        introduction.setOpaque(false);
        introduction.setBackground(new Color(0, 0, 0, 0));
        table1.getTableHeader().setReorderingAllowed(false);
        table1.getTableHeader().setReorderingAllowed(false);
        model=new BookSystem(socketHelper);
        this.book=bk;
        UIStyler.setBelowButton(button1);
        refreshtable(book);

    }
    public void refreshtable(Book bk)
    {
        books=model.searchbook(bk,4);
        book=books.get(0);
        if (book.getImage() != null) {
            try {
                Icon icon = StringtoImage(book.getImage());
                label1.setIcon(icon);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        name.setText(book.getName());
        Author.setText(book.getAuthor());
        publisher.setText(book.getPublisher());
        introduction.setText(book.getIntro());
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0); // 清空表格数据
        for (Book book1:books)
        {
            Object[] rowData={book1.getISBN(), book1.getAddress(),(book1.getCount()>0)?"可借阅":"不可借阅","---"};
            if(book1.getCount()>0)
            {
                for(int i=0;i<book1.getCount();i++)
                    tableModel.addRow(rowData);
            }
            BookRecord bookb = new BookRecord(book1.getName(), book1.getISBN(), book1.getAuthor(),null, null, false,null,null,null,0);
            records = model.searchrecord(bookb, 2);
            for (int j = 0; j < records.size(); ++j) {
                Vector<String> rowData2 = new Vector<>();
                rowData2.add(String.valueOf(bookb.getISBN()));
                rowData2.add(records.get(j).getAddress());
                rowData2.add((records.get(j).getStatus()) ? "可借阅" : "不可借阅");
                rowData2.add(records.get(j).getBorrowtime());
                tableModel.addRow(rowData2);
            }
        }

    }

    public JScrollPane returnscrollPane()
    {
        return this.scrollPane1;
    }
    private Icon StringtoImage(String ss) throws IOException {
        BASE64Decoder decoder=new BASE64Decoder();
        byte[]imagebyte=decoder.decodeBuffer(ss);
        BufferedImage imag= ImageIO.read(new ByteArrayInputStream(imagebyte));
        if(imag!=null){
            Image resultingImage = imag.getScaledInstance(195, 195, Image.SCALE_AREA_AVERAGING);
            BufferedImage outputImage = new BufferedImage(195, 195, BufferedImage.TYPE_INT_RGB);
            outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
            ImageIcon icon=new ImageIcon(outputImage);
            return icon;
        }
        else return null;
    }


    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        iff=true;
    }
    public JButton getReturnButton() {
        return button1;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        intro = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        button1 = new JButton();
        name = new JTextField();
        label3 = new JLabel();
        Author = new JTextField();
        laber4 = new JLabel();
        publisher = new JTextField();
        label5 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        introduction = new JTextArea();
        label4 = new JLabel();

        //======== intro ========
        {
            intro.setMinimumSize(new Dimension(800, 800));
            intro.setPreferredSize(new Dimension(800, 800));
            intro.setLayout(null);
            intro.add(label1);
            label1.setBounds(860, 35, 190, 195);

            //---- label2 ----
            label2.setText("\u4e66\u540d");
            label2.setFont(new Font("\u6977\u4f53", Font.BOLD, 24));
            label2.setForeground(Color.white);
            intro.add(label2);
            label2.setBounds(350, 75, 90, 47);

            //---- button1 ----
            button1.setText("\u8fd4\u56de");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            intro.add(button1);
            button1.setBounds(new Rectangle(new Point(1225, 395), button1.getPreferredSize()));

            //---- name ----
            name.setEditable(false);
            intro.add(name);
            name.setBounds(450, 80, 200, 35);

            //---- label3 ----
            label3.setText("\u4f5c\u8005");
            label3.setFont(new Font("\u6977\u4f53", Font.BOLD, 24));
            label3.setForeground(Color.white);
            intro.add(label3);
            label3.setBounds(350, 140, 90, 47);

            //---- Author ----
            Author.setEditable(false);
            intro.add(Author);
            Author.setBounds(450, 145, 200, 35);

            //---- laber4 ----
            laber4.setText("\u51fa\u7248\u793e");
            laber4.setFont(new Font("\u6977\u4f53", Font.BOLD, 24));
            laber4.setForeground(Color.white);
            intro.add(laber4);
            laber4.setBounds(350, 205, 90, 47);

            //---- publisher ----
            publisher.setEditable(false);
            intro.add(publisher);
            publisher.setBounds(450, 210, 200, 35);

            //---- label5 ----
            label5.setText("\u7b80\u4ecb");
            label5.setFont(new Font("\u6977\u4f53", Font.BOLD, 24));
            label5.setForeground(Color.white);
            intro.add(label5);
            label5.setBounds(350, 270, 90, 47);

            //======== scrollPane1 ========
            {

                //---- table1 ----
                table1.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, "", null, ""},
                        {null, null, null, null},
                    },
                    new String[] {
                        "ISBN", "\u9986\u85cf\u5730", "\u501f\u9605\u72b6\u6001", "\u5e94\u8fd8\u65f6\u95f4"
                    }
                ));
                table1.setForeground(Color.white);
                table1.setEnabled(false);
                scrollPane1.setViewportView(table1);
            }
            intro.add(scrollPane1);
            scrollPane1.setBounds(200, 485, 1150, 250);

            //======== scrollPane2 ========
            {

                //---- introduction ----
                introduction.setOpaque(false);
                introduction.setBorder(null);
                introduction.setEditable(false);
                scrollPane2.setViewportView(introduction);
            }
            intro.add(scrollPane2);
            scrollPane2.setBounds(450, 270, 565, 170);

            //---- label4 ----
            label4.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/finalib.jpg")));
            intro.add(label4);
            label4.setBounds(0, 0, 1685, 830);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < intro.getComponentCount(); i++) {
                    Rectangle bounds = intro.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = intro.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                intro.setMinimumSize(preferredSize);
                intro.setPreferredSize(preferredSize);
            }
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel intro;
    private JLabel label1;
    private JLabel label2;
    private JButton button1;
    private JTextField name;
    private JLabel label3;
    private JTextField Author;
    private JLabel laber4;
    private JTextField publisher;
    private JLabel label5;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTextArea introduction;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
