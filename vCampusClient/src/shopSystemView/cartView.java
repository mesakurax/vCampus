/*
 * Created by JFormDesigner on Thu Sep 07 21:17:21 CST 2023
 */

package shopSystemView;

import javax.rmi.CORBA.Util;
import javax.swing.table.*;
import module.shopSystem;
import utils.SocketHelper;
import module.bankSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.Calendar;
import java.util.Vector;

import entity.*;
import utils.UIStyler;

/**
 * @author 22865
 */


public class cartView extends JPanel {
    shopSystem shop_user;
    User user;

    String uid;
    Object[][] data;
    String[] columnNames;

    private JTextField texfile_yue;
    public cartView(SocketHelper socketHelper,shopSystem shop_user,String uid,JTextField textField) {
        this.shop_user=shop_user;
        this.uid = uid;
        user = new User();
        user.setId(uid);
        texfile_yue=textField;
        initComponents();

        Vector<Cart> itemList=new Vector<Cart>();
        setTable(shop_user.queryAllCart(uid));

        //美化
        UIStyler.setBelowButton(button1);
        UIStyler.setBelowButton(button2);
        UIStyler.setTransparentTable(scrollPane1);
        table1.setRowHeight(60);
    }


    private void button1MouseClicked(MouseEvent e) {   //从购物车购买
        // TODO add your code here
        int[] rs = table1.getSelectedRows();
        if (rs.length == 0)
            JOptionPane.showMessageDialog(null, "没有选中的商品", "提示", JOptionPane.INFORMATION_MESSAGE);
        for (int i = 0; i < rs.length; i++) {
            String id, name;
            double prc;
            int str, num,cartId;
            id = (String) table1.getValueAt(rs[i], 0);
            name = (String) table1.getValueAt(rs[i], 1);
            prc = Double.parseDouble((String) table1.getValueAt(rs[i], 2));
            str = Integer.parseInt((String) table1.getValueAt(rs[i], 3));
            num = Integer.parseInt((String) table1.getValueAt(rs[i], 4));

            cartId = (int) table1.getValueAt(rs[i], 5);
            Calendar c = Calendar.getInstance();
            System.out.println("成功拿到购物车编号"+cartId);
            while (num > str || num <= 0) {
                JOptionPane.showMessageDialog
                        (null, "库存不足/商品数量<=0", "提示", JOptionPane.INFORMATION_MESSAGE);
                String temp = JOptionPane.showInputDialog("请输入" + name + "(库存：" + str + ")的购买数量");
                num = Integer.parseInt(temp);
            }

            double yue=shop_user.query_balance(user);


          //  double banlance =
            if (yue < prc * num)
                JOptionPane.showMessageDialog
                        (null, "余额不足", "提示", JOptionPane.INFORMATION_MESSAGE);
            else {
                if (shop_user.buyFromCart(id,uid,name,c.getTime().toString(),prc,num,cartId)) {

                    JOptionPane.showMessageDialog
                            (null, "购买成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("购买成功");
                    shop_user.modifyyue(user,yue-prc*num);
                }
            }

            texfile_yue.setText(String.valueOf(shop_user.query_balance(user)));
        }

        setTable(shop_user.queryAllCart(uid));
    }

    private void button2MouseClicked(MouseEvent e) {  //从购物车删除
        // TODO add your code here
        int[] rs=table1.getSelectedRows();
        if(rs.length==0){
            JOptionPane.showMessageDialog
                    (null,"没有选中的商品","提示",JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            boolean flag = true;

            for (int i = 0; i < rs.length; i++) {
                String uname = (String) table1.getValueAt(rs[i], 1);
                int cartId = (int) table1.getValueAt(rs[i], 5);
               // System.out.println((String) table1.getValueAt(rs[i], 1));
                //clearTable(uid);
                if (shop_user.deleteFromCart(uid, uname,cartId))
                    flag = true;
            }

            if (flag) {
                JOptionPane.showMessageDialog
                        (null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
            else JOptionPane.showMessageDialog
                    (null, "删除失败", "提示", JOptionPane.INFORMATION_MESSAGE);

            //showTable(uid);
          // setTable(shop_user.queryAllCart(uid));
            //remind2.setText(cus.queryAllCart(uid).size() + " 件商品");  统计购物车有几件的文本框

        }    System.out.println("delete table success");
    }

    public void query(Cart rs,String uid){
           /*     clearTable(uid);
            table.setValueAt((Object)rs.getItemId(),0,0);
            table.setValueAt((Object)rs.getItemName(),0,1);
            table.setValueAt((Object)rs.getItemPrc(),0,2);
            table.setValueAt((Object)rs.getItemStr(),0,3);*/
        Vector<Cart> temp=new Vector<>();
        temp.add(rs);
        setTable(temp);
    }

    public void setTable(Vector<Cart> itemList) {
        data = new Object[][]{};
        columnNames = new String[]{
                "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u5355\u4ef7", "\u5e93\u5b58", "\u6570\u91cf", "\u8d2d\u7269\u8f66\u7f16\u53f7"};
        DefaultTableModel tableModel=(DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0);   //
        Vector<Object[]> ArrayData = new Vector<>();
        if(itemList!=null){
            for (int i = 0; i < itemList.size(); i++) {
                Cart temp = itemList.elementAt(i);
                Vector<Object> rowData = new Vector<>();
                String url = "";
                rowData.add(String.valueOf(temp.getItemId()));
                rowData.add(temp.getItemName());
                rowData.add(String.valueOf(temp.getItemPrc()));
                rowData.add(String.valueOf(temp.getItemStr()));
                rowData.add(String.valueOf(1));
                rowData.add(temp.getCartId());
       /*         Object[] tempObj = new Object[rowData.size()];
                rowData.toArray(tempObj);
                ArrayData.add(tempObj)*/;

                tableModel.addRow(rowData);
            }
        }

       /* Object[][] data = new Object[ArrayData.size()][];
        ArrayData.toArray(data);

        DefaultTableModel model=new DefaultTableModel(data,columnNames);*/
       // table1.setModel(model);
    }

    public void run(String uid){

        System.out.println("跑了cartView的run");
        if(shop_user.queryAllCart(uid)==null)
        {
            System.out.println("判断购物车没有东西");;
            table1.setVisible(false);
            scrollPane1.setVisible(false);
            button1.setVisible(false);
            button2.setVisible(false);
            // ((DefaultTableModel)table.getModel()).getDataVector().clear();
            /*ImageIcon figure=new ImageIcon("shopImage/cart.png");
            figure.setImage(figure.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
            JLabel figureIcon=new JLabel(figure);
            figureIcon.setBounds(600,150,200,200);
            buypanel.add(figureIcon);*/
        }
        else {
            table1.setVisible(true);
            scrollPane1.setVisible(true);
            button1.setVisible(true);
            button2.setVisible(true);


            /*ImageIcon figure=new ImageIcon("shopImage/shopping.png");
            figure.setImage(figure.getImage().getScaledInstance(300,250,Image.SCALE_DEFAULT));
            JLabel figureIcon=new JLabel(figure);
            figureIcon.setBounds(1240,150,270,300);
            buypanel.add(figureIcon);*/
            // remind.setBounds(1250,500,270,300);
           /* remind.setText("您的购物车中有 ");
            remind.setFont(new Font("华文琥珀",Font.PLAIN,30));
            remind2.setText(cus.queryAllCart(uid).size()+" 件商品");
            remind2.setFont(new Font("华文琥珀",Font.PLAIN,30));
            panel1.setVisible(true);
            remind.setVisible(true);
            remind2.setVisible(true);*/
            setTable(shop_user.queryAllCart(uid));
            //clearTable(uid);
            //showTable(uid);
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        label1 = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(1685, 780));
        setVisible(false);
        setLayout(null);

        //======== panel1 ========
        {
            panel1.setForeground(new Color(0x64fff8f8, true));
            panel1.setBackground(new Color(0x64fff8f8, true));
            panel1.setPreferredSize(new Dimension(1685, 780));
            panel1.setLayout(null);

            //======== scrollPane1 ========
            {

                //---- table1 ----
                table1.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                    },
                    new String[] {
                        "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u5355\u4ef7", "\u5e93\u5b58", "\u6570\u91cf", "\u8d2d\u7269\u8f66\u7f16\u53f7"
                    }
                ));
                table1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 27));
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1);
            scrollPane1.setBounds(15, 35, 1635, 635);

            //---- button1 ----
            button1.setText("\u8d2d\u4e70");
            button1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            panel1.add(button1);
            button1.setBounds(1170, 700, 150, 50);

            //---- button2 ----
            button2.setText("\u5220\u9664");
            button2.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 24));
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            panel1.add(button2);
            button2.setBounds(1360, 700, 150, 50);

            //---- label1 ----
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
    private JButton button2;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}

