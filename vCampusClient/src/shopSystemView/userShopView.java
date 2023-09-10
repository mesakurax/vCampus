/*
 * Created by JFormDesigner on Wed Sep 06 16:42:32 CST 2023
 */

package shopSystemView;

import java.awt.event.*;

import utils.UIStyler;
//import com.intellij.ide.ui.laf.*;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.SocketHelper;
import entity.*;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;
import javax.swing.*;
import module.*;
import javax.swing.*;
/**
 * @author 22865
 */
public class userShopView extends JPanel {

    private SocketHelper socketHelper;
    shopSystem shop_user;

    String uid;

    User user;

    private CardLayout card=new CardLayout();

    private JPanel card_panel=new JPanel();  //子布局，代表后面切换的布局都是它

    private itemView item;
    private cartView cart;
    private recordView record;

    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public userShopView(User user,SocketHelper socketHelper) {
        beautify();
        this.user = user;
        uid = user.getId();

        this.socketHelper=socketHelper;
        this.shop_user=new shopSystem(this.socketHelper);
        initComponents();
        UIStyler.setTopButton(button1_item);
        UIStyler.setTopButton(button2_cart);
        UIStyler.setTopButton(button3_record);
        UIStyler.setTopButton(button2);


        panel1.setVisible(true);


        card_panel.setBounds(0,200,1685,830);  //位置大小
        this.add(card_panel);

        item=new itemView(socketHelper,shop_user,uid);
        cart=new cartView(socketHelper,shop_user,uid,textField2);
        record = new recordView(socketHelper,shop_user,uid);

        card_panel.setLayout(card);
        card_panel.add(item,"p0");
        card_panel.add(cart,"p1");
        card_panel.add(record,"p2");

        card.show(card_panel,"p0");

        textField2.setText(String.valueOf(shop_user.query_balance(user)));
    }

    private void button1_itemMouseClicked(MouseEvent e) {
        // TODO add your code here
        item.run(uid);
        card.show(card_panel,"p0");

        //one.refreshTable();
    }

    private void button2_cartMouseClicked(MouseEvent e) {
        // TODO add your code here
        cart.run(uid);
        card.show(card_panel,"p1");
    }
    
    private void button3_recordMouseClicked(MouseEvent e) {
        // TODO add your code here
        record.run(uid);
        card.show(card_panel,"p2");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    JFrame frame = new JFrame("User Shop View");
                    SocketHelper socketHelper = new SocketHelper(); // 创建 SocketHelper 对象或者按照你的需求初始化
                    socketHelper.getConnection(socketHelper.ip, socketHelper.port);
                    try {
                        socketHelper.getOs().writeInt(1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    User user = new User();
                    user.setId("0900");
                    userShopView userView = new userShopView(user, socketHelper);

                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(userView); // 将 userShopView 添加到 JFrame 中
                    frame.setSize(1685, 1030); // 设置 JFrame 大小
                    frame.setVisible(true); // 设置 JFrame 可见
                }
            });

    }

    private void button2_recordMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void button2MouseClicked(MouseEvent e) {   //搜索按钮
        // TODO add your code here
        int flag=comboBox1.getSelectedIndex();
        String name=textField1.getText();
        if(name.isEmpty()) {
            JOptionPane.showMessageDialog(null,"请输入商品名称","提示",JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            switch(flag){

                case 1://在所有商品中查询
                    Item t=new Item(null,name);
                    Vector<Item> rs=shop_user.searchItem(t);
                    if(rs==null){
                        JOptionPane.showMessageDialog(null,"该商品不存在","提示",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        card.show(card_panel,"p0");
                        item.query(rs.elementAt(0));
                    }
                    break;

                case 2://在购物车中查询
                    Cart rs1=shop_user.searchCartItem(uid,name);
                    if(rs1==null){
                        JOptionPane.showMessageDialog(null,"您的购物车中没有该商品","提示",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        System.out.println("购物车中有该商品");
                        card.show(card_panel, "p1");
                        cart.query(rs1,uid);
                    }
                    break;
                case 3://在消费记录中查询
                    Vector<ItemRecord> rs2=shop_user.searchItemRecord(uid,name);
                    if(rs2==null){
                        JOptionPane.showMessageDialog(null,"您没有购买过该商品","提示",JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        card.show(card_panel,"p2");
                        record.query(rs2,uid);
                    }

                    break;
            }
        }
    }

    private void comboBox1MouseEntered(MouseEvent e) {
        // TODO add your code here
        //super.mouseEntered(e);
        comboBox1.setOpaque(true);
        comboBox1.setBackground(Color.white);  //鼠标移上去后变白
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label2 = new JLabel();
        panel1 = new JPanel();
        panel3 = new JPanel();
        comboBox1 = new JComboBox<>();
        textField1 = new JTextField();
        button2 = new JButton();
        button3_record = new JButton();
        button2_cart = new JButton();
        button1_item = new JButton();
        textField2 = new JTextField();
        label1 = new JLabel();
        label3 = new JLabel();
        panel2 = new JPanel();

        //======== this ========
        setPreferredSize(new Dimension(1685, 1030));
        setLayout(null);

        //---- label2 ----
        label2.setIcon(new ImageIcon(getClass().getResource("/bankSystemView/pic/seulogo.png")));
        add(label2);
        label2.setBounds(50, 0, 490, 145);

        //======== panel1 ========
        {
            panel1.setForeground(new Color(0x33ff66));
            panel1.setBackground(new Color(0x24321e));
            panel1.setLayout(null);

            //======== panel3 ========
            {
                panel3.setBackground(new Color(0x405032));
                panel3.setLayout(null);

                //---- comboBox1 ----
                comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                    "\u8bf7\u9009\u62e9",
                    "\u6240\u6709\u5546\u54c1",
                    "\u8d2d\u7269\u8f66",
                    "\u6d88\u8d39\u8bb0\u5f55"
                }));
                comboBox1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
                comboBox1.setOpaque(false);
                comboBox1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        comboBox1MouseEntered(e);
                    }
                });
                panel3.add(comboBox1);
                comboBox1.setBounds(535, 0, 170, 50);

                //---- textField1 ----
                textField1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
                textField1.setForeground(Color.white);
                panel3.add(textField1);
                textField1.setBounds(715, 0, 265, 50);

                //---- button2 ----
                button2.setText("\u641c\u7d22");
                button2.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
                button2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button2MouseClicked(e);
                    }
                });
                panel3.add(button2);
                button2.setBounds(990, 0, 85, 50);

                //---- button3_record ----
                button3_record.setText("\u8d2d\u4e70\u8bb0\u5f55");
                button3_record.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
                button3_record.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button2_recordMouseClicked(e);
                        button3_recordMouseClicked(e);
                    }
                });
                panel3.add(button3_record);
                button3_record.setBounds(1485, 0, 150, 50);

                //---- button2_cart ----
                button2_cart.setText("\u8d2d\u7269\u8f66");
                button2_cart.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
                button2_cart.setIcon(null);
                button2_cart.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button2_cartMouseClicked(e);
                    }
                });
                panel3.add(button2_cart);
                button2_cart.setBounds(1315, 0, 150, 50);

                //---- button1_item ----
                button1_item.setText("\u5546\u57ce");
                button1_item.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
                button1_item.setIcon(null);
                button1_item.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        button1_itemMouseClicked(e);
                    }
                });
                panel3.add(button1_item);
                button1_item.setBounds(1140, 0, 150, 50);

                //---- textField2 ----
                textField2.setOpaque(false);
                textField2.setEditable(false);
                textField2.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
                textField2.setForeground(Color.white);
                panel3.add(textField2);
                textField2.setBounds(220, 5, 170, 45);

                //---- label1 ----
                label1.setText("\u8d26\u6237\u4f59\u989d\uff1a");
                label1.setForeground(Color.white);
                label1.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
                panel3.add(label1);
                label1.setBounds(75, 0, 150, 50);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel3.getComponentCount(); i++) {
                        Rectangle bounds = panel3.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel3.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel3.setMinimumSize(preferredSize);
                    panel3.setPreferredSize(preferredSize);
                }
            }
            panel1.add(panel3);
            panel3.setBounds(0, 150, 1685, 50);

            //---- label3 ----
            label3.setText("\u4e1c\u5357\u5927\u5b66\u7f51\u4e0a\u5546\u57ce");
            label3.setFont(new Font("\u6977\u4f53", Font.BOLD, 60));
            label3.setBackground(Color.white);
            label3.setForeground(Color.white);
            panel1.add(label3);
            label3.setBounds(1035, 15, 600, 135);

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
        panel1.setBounds(0, 0, 1685, 200);

        //======== panel2 ========
        {
            panel2.setVisible(false);
            panel2.setLayout(null);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel2.getComponentCount(); i++) {
                    Rectangle bounds = panel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
        }
        add(panel2);
        panel2.setBounds(0, 200, 1685, 830);

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
    private JLabel label2;
    private JPanel panel1;
    private JPanel panel3;
    private JComboBox<String> comboBox1;
    private JTextField textField1;
    private JButton button2;
    private JButton button3_record;
    private JButton button2_cart;
    private JButton button1_item;
    private JTextField textField2;
    private JLabel label1;
    private JLabel label3;
    private JPanel panel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}


