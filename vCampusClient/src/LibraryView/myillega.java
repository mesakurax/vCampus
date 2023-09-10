/*
 * Created by JFormDesigner on Fri Sep 08 14:06:10 CST 2023
 */

package LibraryView;

import utils.UIStyler;
import entity.Book;
import entity.BookIllegal;
import entity.BookRecord;
import module.BookSystem;
import utils.SocketHelper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import module.*;

/**
 * @author 86153
 */
public class myillega extends JPanel {
    String uid;
    SocketHelper socketHelper;
    BookSystem model;
    Book book;
    List<Book> books;
    BookRecord record;
    BookIllegal illegal;
    List<BookIllegal> illegals;
    public myillega(SocketHelper helper,User uu)
    {
        initComponents();
        this.uid=uu.getId();
        this.socketHelper=helper;
        System.out.println(111);
        setLayout(new BorderLayout()); // 设置布局管理器为BorderLayout
        table1.setRowHeight(60);
        table1.getTableHeader().setReorderingAllowed(false);
        table1.getTableHeader().setReorderingAllowed(false);
        UIStyler.setBelowButton(button1);
        UIStyler.setTransparentTable(scrollPane1);
        UIStyler.setBelowButton(button2);
        model=new BookSystem(socketHelper);
        refreshtable();
    }

    public void refreshtable() {
        illegal=new BookIllegal(0,uid,"","","","","","",0,false);
        List<BookIllegal> rs=model.searchillegal(illegal,5);
        table1.clearSelection();
        illegals=rs;
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0); // 清空表格数据
        for (BookIllegal illegal1:illegals)
        {
            System.out.println(illegal1.getPenalty());
            String temp;
            if(illegal1.isStatus()==false)
            {
                temp="未缴清";
            }
            else
            {
                temp="缴清";
            }
            Object[] rowData = {illegal1.getRecordid(),illegal1.getISBN(), illegal1.getName(),illegal1.getAuthor(),illegal1.getUserID(),illegal1.getBorrowtime(),illegal1.getDeadline(),illegal1.getToday(),
                    illegal1.getPenalty(),
                    temp};
            tableModel.addRow(rowData);
        }
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        int row=table1.getSelectedRow();
        if(row==-1)
            JOptionPane.showMessageDialog(null,"请选中相应记录"," ",JOptionPane.ERROR_MESSAGE);
        else
        {
            boolean temp=true;
            if((String) table1.getValueAt(row,9)=="未缴清")
            {
                temp=false;
            }

            if(!temp) {
                illegal = new BookIllegal((Integer) table1.getValueAt(row, 0),
                        (String) table1.getValueAt(row, 4), (String) table1.getValueAt(row, 1), (String) table1.getValueAt(row, 2),
                        (String) table1.getValueAt(row, 3), (String) table1.getValueAt(row, 5), (String) table1.getValueAt(row, 6),
                        (String) table1.getValueAt(row, 7),
                        (Double) table1.getValueAt(row, 8),
                        temp);

                shopSystem shop_model = new shopSystem(socketHelper);
                User userinfo = new User();
                userinfo.setId(uid);
                double b = shop_model.query_balance(userinfo);

                if (b < illegal.getPenalty()) {
                    JOptionPane.showMessageDialog(this, "余额不足！！");
                } else {
                    JOptionPane.showMessageDialog(this,"缴纳成功！！");
                    model.modifyillegal(illegal);
                    shop_model.modifyyue(userinfo, b - illegal.getPenalty());

                }
            }
            else
                JOptionPane.showMessageDialog(this,"你已支付过罚款！！");
            refreshtable();
        }
    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        refreshtable();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        button2 = new JButton();
        label1 = new JLabel();

        //======== this ========
        setMinimumSize(new Dimension(1605, 830));
        setPreferredSize(new Dimension(1605, 830));
        setLayout(null);

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {"", "", null, "", null, "", null, "", null, null},
                    {null, null, null, null, null, null, null, null, null, null},
                },
                new String[] {
                    "\u8fdd\u7ae0\u8bb0\u5f55\u53f7", "ISBN", "\u4e66\u540d", "\u4f5c\u8005", "\u501f\u9605\u4eba", "\u501f\u9605\u65e5\u671f", "\u5e94\u8fd8\u65e5\u671f", "\u8fd8\u4e66\u65e5\u671f", "\u7f5a\u91d1", "\u662f\u5426\u7f34\u6e05"
                }
            ));
            table1.setForeground(Color.white);
            scrollPane1.setViewportView(table1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(130, 105, 1380, 695);

        //---- button1 ----
        button1.setText("\u7f34\u7eb3");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        add(button1);
        button1.setBounds(1215, 50, 150, 50);

        //---- label2 ----
        label2.setText("\u501f\u9605\u65f6\u8981\u7231\u62a4\u4e66\u7c4d,\u79bb\u5f00\u540e\u53ca\u65f6\u5f52\u8fd8");
        label2.setForeground(Color.white);
        label2.setFont(new Font("\u5e7c\u5706", Font.BOLD, 30));
        add(label2);
        label2.setBounds(510, 0, 520, 65);

        //---- label3 ----
        label3.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/QQ\u56fe\u724720230914121748.gif")));
        add(label3);
        label3.setBounds(new Rectangle(new Point(1385, 20), label3.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u5237\u65b0");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
                button2MouseClicked(e);
            }
        });
        add(button2);
        button2.setBounds(1045, 50, 150, 50);

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/finalib.jpg")));
        add(label1);
        label1.setBounds(new Rectangle(new Point(0, 0), label1.getPreferredSize()));

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
    private JButton button1;
    private JLabel label2;
    private JLabel label3;
    private JButton button2;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
