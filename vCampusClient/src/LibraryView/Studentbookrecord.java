/*
 * Created by JFormDesigner on Sun Sep 03 10:33:35 CST 2023
 */

package LibraryView;

import java.awt.event.*;
import javax.swing.table.*;

import entity.Book;
import entity.BookRecord;
import module.BookSystem;
import utils.SocketHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.swing.*;

/**
 * @author 86153
 */
public class Studentbookrecord extends JPanel {
    SocketHelper socketHelper;
    String uid="dhb";
    BookSystem model;
    Book book;
    List<Book> books;
    BookRecord record;
    List<BookRecord> records;
    public Studentbookrecord(SocketHelper helper) {

        initComponents();
        myrecord.setVisible(true);
        this.socketHelper=helper;
        setLayout(new BorderLayout()); // 设置布局管理器为BorderLayout
        add(myrecord, BorderLayout.CENTER); // 将myrecord添加到CENTER位置
        model=new BookSystem(socketHelper);
        record=new BookRecord(null,null,null,null,null,false,null,null,uid,0);
        List<BookRecord> rs=model.searchrecord(record,4);
        records=rs;
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

    private boolean ifover(String deadline) {
        LocalDate day=LocalDate.parse(deadline);
        LocalDate today=LocalDate.now();
        if(today.isAfter(day))
            return false;
        else
            return true;
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
                JOptionPane.showMessageDialog(null, "还书失败", "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void refreshtable() {
        record=new BookRecord(null,null,null,null,null,false,null,null,uid,0);
        List<BookRecord> rs=model.searchrecord(record,4);
        table1.clearSelection();
        records=rs;
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
        returnbook = new JButton();

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
                scrollPane1.setViewportView(table1);
            }
            myrecord.add(scrollPane1);
            scrollPane1.setBounds(130, 220, 705, scrollPane1.getPreferredSize().height);

            //---- returnbook ----
            returnbook.setText("\u8fd8\u4e66");
            returnbook.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    returnbookMouseClicked(e);
                }
            });
            myrecord.add(returnbook);
            returnbook.setBounds(new Rectangle(new Point(680, 170), returnbook.getPreferredSize()));

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
    private JButton returnbook;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
