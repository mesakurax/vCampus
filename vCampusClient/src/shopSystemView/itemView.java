/*
 * Created by JFormDesigner on Wed Sep 06 17:13:32 CST 2023
 */

package shopSystemView;

import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.table.*;

import Decoder.BASE64Decoder;
//import com.intellij.ide.ui.laf.*;
import utils.UIStyler;
import utils.IconHelp;
import utils.SocketHelper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Vector;
import javax.swing.*;
import module.*;
import entity.*;

/**
 * @author 22865
 */
public class itemView extends JPanel {
    shopSystem user;

    Object[][] data;

    String[] columnNames;
    private String uid;
    public itemView(SocketHelper socketHelper,shopSystem user,String uid) {
        initComponents();
        this.user = user;
        this.uid = uid;


        UIStyler.setTransparentTable(scrollPane1);
        UIStyler.setBelowButton(button1);
        Vector<Item> itemList;
        Item empty=new Item(null,null);
        itemList=user.searchItem(empty);
        setTable(itemList);
        table1.setRowHeight(100);


    }

    public itemView(SocketHelper socketHelper) {
    }

    private void button1MouseClicked(MouseEvent e) {

        int[] rs=table1.getSelectedRows();
        if(rs.length==0){
            JOptionPane.showMessageDialog
                    (null,"没有选中的商品","提示",JOptionPane.INFORMATION_MESSAGE);
        }
        System.out.println(rs.length);
        for(int i=0;i<rs.length;i++){
            String id,name;
            double prc;
            int str;
            id=(String)table1.getValueAt(rs[i],0);
            name=(String)table1.getValueAt(rs[i],1);
            prc=Double.parseDouble((String)table1.getValueAt(rs[i],3));
            str=Integer.parseInt((String)table1.getValueAt(rs[i],5));

            Cart temp= user.searchCartItem(id,name);
            if(temp!=null){
                JOptionPane.showMessageDialog
                        (null,"购物车中已有该商品","提示",JOptionPane.INFORMATION_MESSAGE);
            }
            else if(user.addToCart(uid,id,name,prc,str))
                JOptionPane.showMessageDialog
                        (null,"添加成功","提示",JOptionPane.INFORMATION_MESSAGE);
            else JOptionPane.showMessageDialog
                        (null,"添加失败","提示",JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void setTable(Vector<Item>itemList){
        data=new Object[][]{};
        columnNames=new String[]{
                "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u751f\u4ea7\u5546", "\u5355\u4ef7", "\u9500\u91cf", "\u5e93\u5b58", "\u56fe\u7247"};

        DefaultTableModel tableModel=(DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0);   //
        Vector<Object[]> ArrayData = new Vector<>();
        if(itemList!=null)
            for(int i=0;i<itemList.size();i++) {
                Item temp = itemList.elementAt(i);
                Vector<Object> rowData = new Vector<>();
                String url = "";
                rowData.add(String.valueOf(temp.getItemId()));
                rowData.add(temp.getItemName());
                rowData.add(temp.getItemPrd());
                rowData.add(String.valueOf(temp.getItemPrc()));
                rowData.add(String.valueOf(temp.getItemSales()));
                rowData.add(String.valueOf(temp.getItemStr()));
                try {
                    System.out.println(temp.getItemImage());
                    Icon icon = IconHelp.StringtoImage(temp.getItemImage());
                    rowData.add(icon);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                tableModel.addRow(rowData);
            }
        table1.getColumnModel().getColumn(6).setCellRenderer(new ImageRenderer());
    }

    public void query(Item rs){
        // clearTable();
        Vector<Item> temp=new Vector<>();
        temp.add(rs);
        setTable(temp);
    }

    public void run(String uid){
        //clearTable();
        Vector<Item> itemList=new Vector<Item>();
        Item empty=new Item(null,null);
        setTable(user.searchItem(empty));
    }

    private void button1MouseEntered(MouseEvent e) {
        // TODO add your code here
        button1.setOpaque(true);
        button1.setBackground(Color.white);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        label1 = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(1685, 780));
        setOpaque(false);
        setVisible(false);
        setLayout(null);

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);
            panel1.setLayout(null);

            //======== scrollPane1 ========
            {

                //---- table1 ----
                table1.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                    },
                    new String[] {
                        "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u751f\u4ea7\u5546", "\u5355\u4ef7", "\u9500\u91cf", "\u5e93\u5b58", "\u56fe\u7247"
                    }
                ));
                {
                    TableColumnModel cm = table1.getColumnModel();
                    cm.getColumn(6).setMaxWidth(120);
                    cm.getColumn(6).setPreferredWidth(120);
                }
                table1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 27));
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1);
            scrollPane1.setBounds(15, 35, 1620, 655);

            //---- button1 ----
            button1.setText("\u52a0\u5165\u8d2d\u7269\u8f66");
            button1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    button1MouseEntered(e);
                }
            });
            panel1.add(button1);
            button1.setBounds(1485, 700, 150, 50);

            //---- label1 ----
            label1.setText("text");
            label1.setIcon(new ImageIcon(getClass().getResource("/shopSystemView/pic/imageonline-co-brightnessadjusted (5).png")));
            panel1.add(label1);
            label1.setBounds(0, 0, 2046, label1.getPreferredSize().height);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }
        add(panel1);
        panel1.setBounds(0, 0, 1685, 830);

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
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton button1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
