/*
 * Created by JFormDesigner on Sun Sep 10 16:54:50 CST 2023
 */

package shopSystemView;

import java.awt.event.*;

import Decoder.BASE64Encoder;
import utils.UIStyler;
import entity.Item;
import module.shopSystem;
import utils.IconHelp;
import utils.SocketHelper;
import utils.Timehelp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

/**
 * @author 22865
 */
public class addModifyItem extends JFrame {

   // private final String itemId;
        String itemId;
        String itemName;
        String itemPrd; //商品厂家
        String itemPrc;  //价钱
        String itemSales; //销量
        String itemStr; //库存

    private SocketHelper socketHelper;
    public addModifyItem(SocketHelper socketHelper) {
        this.socketHelper = socketHelper;
        //  beauty();
        initComponents();


    }


    public addModifyItem(SocketHelper socketHelper,String id,String name, String prd, String prc, String sale, String str) {
       //**************构造函数里加image

        this.socketHelper = socketHelper;
        this.itemId = id;
        this.itemName = name;
        this.itemPrd = prd;
        this.itemPrc = prc;
        this.itemSales = sale;
        this.itemStr = str;
       // modifySetContent();
      //  this.itemImage = image;
        initComponents();
        //添加图片
        /*ImageIcon top=new ImageIcon(getClass().getResource("/image/图书管理.png"));
        this.setIconImage(top.getImage());
        final String[] p = new String[1];
        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("跑到这里了");
                JFrame frame = new JFrame();
                JFileChooser chooser = new JFileChooser();
                int flag = chooser.showOpenDialog(frame);
                if (flag == JFileChooser.APPROVE_OPTION)
                    p[0] =chooser.getSelectedFile().getAbsolutePath();
                if(p[0]!=null)  path.setText(p[0]);
            }
        });*/

    }

    private void buttonCancelMouseClicked(MouseEvent e) {
        // TODO add your code here
     dispose();
    }

    private void buttonAddMouseClicked(MouseEvent e) {
        // TODO add your code here
        String tid = getItemId();
        String tname = getItemName();
        String tprd = getItemPrd();
        String tprc = getItemPrc();
        String tsales = getItemSales();
        String tstr = getItemStr();


        if(tid.equals("")){
            JOptionPane.showMessageDialog((Component) this,"商品编号不能为空！");
            return;
        }

        if(tname.equals("")){
            JOptionPane.showMessageDialog((Component) this, "商品名称不能为空！");
            return;
        }
        if(tprd.equals("")){
            JOptionPane.showMessageDialog((Component) this,"生产商！");
            return;
        }
        if(tprc.equals("")){
            JOptionPane.showMessageDialog((Component) this,"单价不能为空！");
            return;
        }
        if(tsales.equals("")){
            JOptionPane.showMessageDialog((Component) this,"销量不能为空！");
            return;
        }

        int tStr = Integer.parseInt(tstr);
        if(tStr<=0 || tStr!= Double.valueOf(tStr).intValue()){  //检查是否存在小数
            JOptionPane.showMessageDialog((Component) this, "请输入合法的库存!");
            return;
        }
        if(path.getText().isEmpty()){  //检查图片是否存在
            JOptionPane.showMessageDialog((Component) this, "请添加图片！");
            return;
        }



        System.out.println("adminViewAdd!");

        Item info = null;
        try {
            info = new Item(tid,tname,tprd,Double.parseDouble(tprc),Integer.parseInt(tsales),Integer.parseInt(tstr), IconHelp.ImagetoString(path.getText()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        boolean flag = new shopSystem(socketHelper).addItem(info);

        if (!flag) {
            JOptionPane.showMessageDialog((Component) this, "录入失败！");
        } else {
            JOptionPane.showMessageDialog((Component) this, "录入成功！");
        }
        this.dispose();
    }

    private void buttonModifyMouseClicked(MouseEvent e) {
        // TODO add your code here
        String tid = getItemId();
        String tname = getItemName();
        String tprd = getItemPrd();
        String tprc = getItemPrc();
        String tsales = getItemSales();
        String tstr = getItemStr();

        if(tid.equals("")){
            JOptionPane.showMessageDialog((Component) this,"商品编号不能为空！");
            return;
        }

        if(tname.equals("")){
            JOptionPane.showMessageDialog((Component) this, "商品名称不能为空！");
            return;
        }
        if(tprd.equals("")){
            JOptionPane.showMessageDialog((Component) this,"生产商！");
            return;
        }
        if(tprc.equals("")){
            JOptionPane.showMessageDialog((Component) this,"单价不能为空！");
            return;
        }
        if(tsales.equals("")){
            JOptionPane.showMessageDialog((Component) this,"销量不能为空！");
            return;
        }

        int tStr = Integer.parseInt(tstr);
        if(tStr<=0 || tStr!= Double.valueOf(tStr).intValue()){  //检查是否存在小数
            JOptionPane.showMessageDialog((Component) this, "请输入合法的库存!");
            return;
        }
        if(path.getText().isEmpty()){  //检查图片是否存在
            JOptionPane.showMessageDialog((Component) this, "请添加图片！");
            return;
        }

        Item info = null;
        try {
            info = new Item(tid,tname,tprd,Double.parseDouble(tprc),Integer.parseInt(tsales),Integer.parseInt(tstr), IconHelp.ImagetoString(path.getText()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        shopSystem admin = new shopSystem(socketHelper);
        boolean flag = admin.modifyItem(info);

        //boolean flag = admin.modifyItem(id,name,prd,prc,sale,str,image);
        if(flag){
            JOptionPane.showMessageDialog(null,"修改成功！","提示", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(null,"修改失败！","提示", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("adminViewModify!");
    }


    private void selectMouseClicked(MouseEvent e) {
        // TODO add your code here
        JFileChooser fileChooser = new JFileChooser();

        // 设置文件选择器的标题
        fileChooser.setDialogTitle("选择文件");

        // 显示文件选择器对话框
        int result = fileChooser.showOpenDialog(null);

        // 处理用户选择的结果
        if (result == JFileChooser.APPROVE_OPTION) {
            // 用户选择了文件
            String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println("已选择文件路径：" + selectedFilePath);

            path.setText(selectedFilePath);

        } else if (result == JFileChooser.CANCEL_OPTION) {
            // 用户取消了选择
            System.out.println("选择图片信息选择被取消。");
        }

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        id = new JTextField();
        name = new JTextField();
        prd = new JTextField();
        prc = new JTextField();
        sales = new JTextField();
        str = new JTextField();
        select = new JButton();
        buttonAdd = new JButton();
        buttonModify = new JButton();
        buttonCancel = new JButton();
        path = new JTextField();

        //======== this ========
        setPreferredSize(new Dimension(500, 650));
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u5546\u54c1\u7f16\u53f7");
        label1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 16));
        contentPane.add(label1);
        label1.setBounds(70, 25, 70, 50);

        //---- label2 ----
        label2.setText("\u5546\u54c1\u540d\u79f0");
        label2.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 16));
        contentPane.add(label2);
        label2.setBounds(70, 95, 70, 50);

        //---- label3 ----
        label3.setText("\u751f\u4ea7\u5546");
        label3.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 16));
        contentPane.add(label3);
        label3.setBounds(70, 165, 70, 50);

        //---- label4 ----
        label4.setText("\u5355\u4ef7");
        label4.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 16));
        contentPane.add(label4);
        label4.setBounds(70, 235, 70, 50);

        //---- label5 ----
        label5.setText("\u9500\u91cf");
        label5.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 16));
        contentPane.add(label5);
        label5.setBounds(70, 305, 70, 50);

        //---- label6 ----
        label6.setText("\u5e93\u5b58");
        label6.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 16));
        contentPane.add(label6);
        label6.setBounds(70, 370, 70, 50);

        //---- label7 ----
        label7.setText("\u56fe\u7247");
        label7.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 16));
        contentPane.add(label7);
        label7.setBounds(70, 435, 70, 50);
        contentPane.add(id);
        id.setBounds(170, 35, 200, 30);
        contentPane.add(name);
        name.setBounds(170, 105, 200, 30);
        contentPane.add(prd);
        prd.setBounds(170, 175, 200, 30);
        contentPane.add(prc);
        prc.setBounds(170, 245, 200, 30);
        contentPane.add(sales);
        sales.setBounds(170, 310, 200, 30);
        contentPane.add(str);
        str.setBounds(170, 380, 200, 30);

        //---- select ----
        select.setText("\u4ece\u6587\u4ef6\u5939\u4e2d\u9009\u62e9");
        select.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectMouseClicked(e);
            }
        });
        contentPane.add(select);
        select.setBounds(150, 445, 120, 30);

        //---- buttonAdd ----
        buttonAdd.setText("\u6dfb\u52a0");
        buttonAdd.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 16));
        buttonAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonAddMouseClicked(e);
            }
        });
        contentPane.add(buttonAdd);
        buttonAdd.setBounds(50, 515, 100, 40);

        //---- buttonModify ----
        buttonModify.setText("\u4fee\u6539");
        buttonModify.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 16));
        buttonModify.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonModifyMouseClicked(e);
            }
        });
        contentPane.add(buttonModify);
        buttonModify.setBounds(180, 515, 100, 40);

        //---- buttonCancel ----
        buttonCancel.setText("\u53d6\u6d88");
        buttonCancel.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 16));
        buttonCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonCancelMouseClicked(e);
            }
        });
        contentPane.add(buttonCancel);
        buttonCancel.setBounds(305, 515, 100, 40);
        contentPane.add(path);
        path.setBounds(280, 445, 150, path.getPreferredSize().height);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField id;
    private JTextField name;
    private JTextField prd;
    private JTextField prc;
    private JTextField sales;
    private JTextField str;
    private JButton select;
    private JButton buttonAdd;
    private JButton buttonModify;
    private JButton buttonCancel;
    private JTextField path;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


    public String getItemId(){
        return id.getText();
    }

    public String getItemName(){
        return name.getText();
    }

    public String getItemPrd(){
        return prd.getText();
    }

    public String getItemPrc(){
        return prc.getText();
    }

    public String getItemSales(){
        return sales.getText();
    }

    public String getItemStr(){
        return str.getText();
    }


    public void setModifyButtonUnenabled(){
        buttonModify.setEnabled(false);
    }

    public void setAddButtonUnenabled(){
        buttonAdd.setEnabled(false);
    }

    public void modifySetContent(String iid,String iname, String iprd, String iprc, String isale, String istr){


        id.setText(iid);
        name.setText(iname);
        prd.setText(iprd);
        prc.setText(iprc);
        sales.setText(isale);
        str.setText(istr);
        System.out.println("设置了初始文本");
    }
}
