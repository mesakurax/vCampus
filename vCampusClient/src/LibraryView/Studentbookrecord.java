/*
 * Created by JFormDesigner on Sun Sep 03 10:33:35 CST 2023
 */

package LibraryView;

import utils.UIStyler;
import entity.Book;
import entity.*;
import entity.BookIllegal;
import entity.BookRecord;
import module.BookSystem;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.SocketHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

/**
 * @author 86153
 */
public class Studentbookrecord extends JPanel {
    SocketHelper socketHelper;
    String uid;
    BookSystem model;
    Book book;
    List<Book> books;
    BookRecord record;
    BookIllegal illegal;
    List<BookRecord> records;
    public void beautify() {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Studentbookrecord(SocketHelper helper,User uu) {

        initComponents();
        this.uid=uu.getId();
        //beautify();
        myrecord.setVisible(true);
        UIStyler.setTransparentTable(scrollPane1);
        setLayout(new BorderLayout()); // 设置布局管理器为 BorderLayout
        table1.getTableHeader().setReorderingAllowed(false);
        table1.getTableHeader().setReorderingAllowed(false);
        UIStyler.setBelowButton(button1);
        UIStyler.setBelowButton(returnbook);
        this.socketHelper=helper;
        setLayout(new BorderLayout()); // 设置布局管理器为BorderLayout
        add(myrecord, BorderLayout.CENTER); // 将myrecord添加到CENTER位置
        model=new BookSystem(socketHelper);
        refreshtable();
        table1.setRowHeight(60);
    }

    private boolean ifover(String deadline) {
        LocalDate day=LocalDate.parse(deadline);
        LocalDate today=LocalDate.now();
        if(today.isAfter(day))
            return false;
        else
            return true;
    }

    public static long calculateDateDifference(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        long daysDifference = start.until(end, ChronoUnit.DAYS);

        return daysDifference;
    }
    private void returnbookMouseClicked(MouseEvent e) {
        // TODO add your code here
        int row=table1.getSelectedRow();
        if(row==-1)
        {
            JOptionPane.showMessageDialog(null,"请选中相应记录"," ",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            record = new BookRecord((String) table1.getValueAt(row, 2),
                    (String) table1.getValueAt(row, 1),
                    (String) table1.getValueAt(row, 3),
                    (String) table1.getValueAt(row,4),
                    (String) table1.getValueAt(row,5),
                    false,
                    (String) table1.getValueAt(row, 7),
                    (String) table1.getValueAt(row, 6),
                    uid,
                    Integer.parseInt(table1.getValueAt(row, 0).toString()));
            if((String)table1.getValueAt(row,8)=="未逾期")
            {
                model.userreturn(record);
                refreshtable();
            }
            else
            {
                String returnday = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
                long date=calculateDateDifference((String) table1.getValueAt(row, 7),returnday);
                double pen=date*0.1;

                DecimalFormat decimalFormat = new DecimalFormat("#.#"); // 使用一位小数的格式化
                String formattedPen = decimalFormat.format(pen);
                System.out.println(pen);
                illegal=new BookIllegal(0,uid, (String) table1.getValueAt(row, 1),(String) table1.getValueAt(row, 2), (String) table1.getValueAt(row, 3),
                        (String) table1.getValueAt(row, 6),(String) table1.getValueAt(row, 7),returnday,pen,false);
                model.admin_addpen(illegal);
                model.userreturn(record);
                refreshtable();
            }
        }
    }

    public void refreshtable() {
        record=new BookRecord(null,null,null,null,null,false,null,null,uid,0);
        List<BookRecord> rs=model.searchrecord(record,4);
        table1.clearSelection();
        records=rs;
        table1.setRowHeight(120);
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0); // 清空表格数据
        for (BookRecord bookRecord:records)

        {
            boolean over=ifover(bookRecord.getDeadline());
            System.out.println(over);
            String temp;
            if(over)
            {
                temp="未逾期";
            }
            else
            {
                temp="逾期";
            }

            Object[] rowData = {bookRecord.getRecordID(), bookRecord.getISBN(), bookRecord.getName(),bookRecord.getAuthor(),bookRecord.getPublisher(), bookRecord.getAddress(), bookRecord.getBorrowtime(),
                    bookRecord.getDeadline(),temp };
            tableModel.addRow(rowData);
        }

    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        refreshtable();
    }
    private String Deadline(String date) {
        LocalDate today = LocalDate.parse(date);
        today = today.plusDays(21);
        return today.toString();
    }

/*        final String[] p = new String[1];
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                JFileChooser chooser = new JFileChooser();
                int flag = chooser.showOpenDialog(frame);
                if (flag == JFileChooser.APPROVE_OPTION)
                    p[0] =chooser.getSelectedFile().getAbsolutePath();
                if(p[0]!=null)  path.setText(p[0]);
            }
        });*/
/*        socketHelper=new SocketHelper();
        socketHelper.getConnection(socketHelper.ip,socketHelper.port);*/



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        myrecord = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        returnbook = new JButton();
        label3 = new JLabel();
        label2 = new JLabel();
        label1 = new JLabel();

        //======== myrecord ========
        {
            myrecord.setPreferredSize(new Dimension(1685, 830));
            myrecord.setMaximumSize(new Dimension(1685, 830));
            myrecord.setMinimumSize(new Dimension(1685, 830));
            myrecord.setLayout(null);

            //======== scrollPane1 ========
            {

                //---- table1 ----
                table1.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null, null, null},
                    },
                    new String[] {
                        "\u501f\u9605\u8bb0\u5f55\u53f7", "ISBN", "\u4e66\u540d", "\u4f5c\u8005", "\u51fa\u7248\u793e", "\u9986\u85cf\u5730", "\u501f\u9605\u65e5\u671f", "\u622a\u6b62\u65e5\u671f", "\u903e\u671f"
                    }
                ));
                table1.setForeground(Color.white);
                scrollPane1.setViewportView(table1);
            }
            myrecord.add(scrollPane1);
            scrollPane1.setBounds(130, 105, 1380, 690);

            //---- button1 ----
            button1.setText("\u5237\u65b0");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            myrecord.add(button1);
            button1.setBounds(1250, 50, 150, 50);

            //---- returnbook ----
            returnbook.setText("\u8fd8\u4e66");
            returnbook.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    returnbookMouseClicked(e);
                }
            });
            myrecord.add(returnbook);
            returnbook.setBounds(1085, 50, 150, 50);

            //---- label3 ----
            label3.setText("\u53ee\u4e1c\u63d0\u9192\u60a8\uff1a\u4e66\u4e2d\u81ea\u6709\u9ec4\u91d1\u5c4b\uff0c\u4e66\u4e2d\u81ea\u6709\u989c\u5982\u7389");
            label3.setForeground(Color.white);
            label3.setFont(new Font("\u5e7c\u5706", Font.BOLD, 30));
            myrecord.add(label3);
            label3.setBounds(435, 0, label3.getPreferredSize().width, 50);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/QQ\u56fe\u724720230914115703.gif")));
            myrecord.add(label2);
            label2.setBounds(new Rectangle(new Point(1385, 485), label2.getPreferredSize()));

            //---- label1 ----
            label1.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/finalib.jpg")));
            myrecord.add(label1);
            label1.setBounds(new Rectangle(new Point(0, 0), label1.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < myrecord.getComponentCount(); i++) {
                    Rectangle bounds = myrecord.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = myrecord.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                myrecord.setMinimumSize(preferredSize);
                myrecord.setPreferredSize(preferredSize);
            }
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel myrecord;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JButton returnbook;
    private JLabel label3;
    private JLabel label2;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
