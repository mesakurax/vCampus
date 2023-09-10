/*
 * Created by JFormDesigner on Sat Sep 09 23:06:41 CST 2023
 */

package shopSystemView;

import java.awt.event.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import utils.UIStyler;
import entity.*;
import module.shopSystem;
import utils.IconHelp;
import utils.SocketHelper;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author 22865
 */
public class adminShopView extends JPanel {

    private SocketHelper socketHelper;
    shopSystem shop_admin;

    Object[][] data;
    String[] columnNames;

    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public adminShopView(SocketHelper helper){
        initComponents();
        beautify();

        UIStyler.setTopButton(queryItem);
        UIStyler.setTopButton(addItem);
        UIStyler.setTransparentTable(scrollPane1);

        UIStyler.setBelowButton(modify);
        UIStyler.setBelowButton(returnto);
        UIStyler.setBelowButton(delete);
        UIStyler.createHeader(this);
        table1.setRowHeight(100);
        this.socketHelper = helper;
        shop_admin = new shopSystem(this.socketHelper);
        Item empty = new Item(null, null);
        Vector<Item> itemList;
        itemList = shop_admin.searchItem(empty);

        if (itemList != null) {
            setTable(itemList);
        }

    }

    private void queryItemMouseClicked(MouseEvent e) {
        // TODO add your code here
        //管理员用商品id查
        //偷懒了......
        String iid = queryText.getText();
        if (iid.isEmpty()) {
            JOptionPane.showMessageDialog
                    (null, "请输入商品id", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        Item rs = new Item(iid, null);
        Vector<Item> search = shop_admin.searchItem(rs);
        if (search == null) {
            JOptionPane.showMessageDialog(null, "没有该商品", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else {
            setTable(search);
        }
    }

    private void addItemMouseClicked(MouseEvent e) {
        // TODO add your code here
      /*  addItemView addview=new addItemView(helper);
        Itemtable=addview.add(Itemtable);*/
        //将一个名为 Itemtable 的 JTable 添加到 addview 这个 JFrame 中??

        addModifyItem addView = new addModifyItem(socketHelper);
        addView.setModifyButtonUnenabled();
        addView.setVisible(true);

    }

    private void deleteMouseClicked(MouseEvent e) {
        // TODO add your code here
        int[] rows = table1.getSelectedRows();
        for (int i = 0; i < rows.length; i++) {
            String iid = (String) table1.getValueAt(rows[i], 0);
            String iname = (String) table1.getValueAt(rows[i], 1);

            int sl = JOptionPane.showOptionDialog(null, "确认删除" + iid + iname + "吗",
                    "删除商品", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"确认", "取消"}, "确认");
            //弹出确认删除框
            if (sl == 0)
                if (shop_admin.deleteItem(iid, iname)) {
                    JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else JOptionPane.showMessageDialog(null, "删除失败", "提示", JOptionPane.INFORMATION_MESSAGE);
            //  clearTable(admin);
            Item temp = new Item();
            Vector<Item> itemList = shop_admin.searchItem(temp);

            // Vector<Item>itemList=shop_admin.searchItem(new Item(null,null));
            // setTable(itemList);
            //  showTable(admin);
        }
    }

    private void returntoMouseClicked(MouseEvent e) {
        // TODO add your code here
        Item empty = new Item(null, null);
        Vector<Item> itemList;
        itemList = shop_admin.searchItem(empty);
        setTable(itemList);
    }

    private void setTable(Vector<Item> itemList) {
        data = new Object[][]{};
        columnNames = new String[]{
                "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u751f\u4ea7\u5546", "\u5355\u4ef7", "\u9500\u91cf", "\u5e93\u5b58", "\u56fe\u7247"};

        DefaultTableModel tableModel=(DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0);
        for (int i = 0; i < itemList.size(); i++) {
            Item temp = itemList.elementAt(i);
            Vector<Object> rowData = new Vector<>();
            rowData.add(String.valueOf(temp.getItemId()));
            rowData.add(temp.getItemName());
            rowData.add(temp.getItemPrd());
            rowData.add(String.valueOf(temp.getItemPrc()));
            rowData.add(String.valueOf(temp.getItemSales()));
            rowData.add(String.valueOf(temp.getItemStr()));

            try {
                Icon icon = IconHelp.StringtoImage(temp.getItemImage());
                rowData.add(icon);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            tableModel.addRow(rowData);
        }
        table1.getColumnModel().getColumn(6).setCellRenderer(new ImageRenderer());
    }


    private void modifyMouseClicked(MouseEvent e) {
        // TODO add your code here
        int row = table1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "未选中商品！");
            return;
        }
        String selectedId = (String) table1.getValueAt(row, 0);
        String selectedName = (String) table1.getValueAt(row, 1);
        String selectedPrd = (String) table1.getValueAt(row,2);
        String selectedPrc = (String) table1.getValueAt(row,3);
        String selectedSale = (String) table1.getValueAt(row,4);
        String selectedStr = (String) table1.getValueAt(row,5);


        Item sitem = new Item(selectedId, selectedName);
        List<Item> findItems = new shopSystem(socketHelper).searchItem(sitem);

        System.out.println("Selected ID: " + selectedId);
        System.out.println("Number of found items: " + findItems.size());

        int i;
        Item item = new Item(null, null);
        for (i = 0; i < findItems.size(); ++i) {
            if (findItems.get(i).getItemId() == selectedId) {
                item = findItems.get(i);
                break;
            }
        }
        System.out.println("i: " + i);
        System.out.println("findItems.size(): " + findItems.size());

        if (i == findItems.size() + 1) {
            JOptionPane.showMessageDialog(this, "Error");
            return;
        }

        addModifyItem editItem = new addModifyItem(socketHelper, item.getItemId(), item.getItemName(), item.getItemPrd(),
                String.valueOf(item.getItemPrc()), String.valueOf(item.getItemSales()), String.valueOf(item.getItemStr()));
//**********************还没加图片
        //addModifyItem editItem = new addModifyItem(socketHelper,item.getItemId(), item.getItemName(),item.getItemPrd(),
        //       String.valueOf(item.getItemPrc()), String.valueOf(item.getItemSales()),String.valueOf(item.getItemStr()));
        editItem.setAddButtonUnenabled();
        editItem.setVisible(true);
        //System.out.println("要填充的信息是"+item.getItemId());
        editItem.modifySetContent(selectedId,selectedName, selectedPrd,selectedPrc, selectedSale, selectedStr);
        System.out.println("设置了修改信息");



    }

    private void comboBox1MouseEntered(MouseEvent e) {
        // TODO add your code here
    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void button2_recordMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void button3_recordMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void button2_cartMouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void button1_itemMouseClicked(MouseEvent e) {
        // TODO add your code here
    }


    //写到这里了
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel3 = new JPanel();
        panel2 = new JPanel();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        returnto = new JButton();
        delete = new JButton();
        modify = new JButton();
        label2 = new JLabel();
        panel1 = new JPanel();
        panel4 = new JPanel();
        queryText = new JTextField();
        queryItem = new JButton();
        addItem = new JButton();
        label4 = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(1685, 1030));
        setLayout(null);

        //======== panel3 ========
        {
            panel3.setBackground(new Color(0x99ffff));
            panel3.setLayout(null);

            //======== panel2 ========
            {
                panel2.setBackground(new Color(0x006633));
                panel2.setLayout(null);

                //---- label3 ----
                label3.setText("text");
                label3.setIcon(new ImageIcon(getClass().getResource("/shopSystemView/pic/admin.png")));
                panel2.add(label3);
                label3.setBounds(new Rectangle(new Point(0, -860), label3.getPreferredSize()));

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
            panel3.add(panel2);
            panel2.setBounds(0, 815, 1685, 15);

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
                ) {
                    boolean[] columnEditable = new boolean[] {
                        true, true, true, true, true, true, false
                    };
                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }
                });
                {
                    TableColumnModel cm = table1.getColumnModel();
                    cm.getColumn(6).setMaxWidth(120);
                    cm.getColumn(6).setPreferredWidth(120);
                }
                table1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 27));
                scrollPane1.setViewportView(table1);
            }
            panel3.add(scrollPane1);
            scrollPane1.setBounds(95, 15, 1515, 695);

            //---- returnto ----
            returnto.setText("\u8fd4\u56de");
            returnto.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 20));
            returnto.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    returntoMouseClicked(e);
                }
            });
            panel3.add(returnto);
            returnto.setBounds(1120, 745, 150, 50);

            //---- delete ----
            delete.setText("\u5220\u9664");
            delete.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 20));
            delete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    deleteMouseClicked(e);
                }
            });
            panel3.add(delete);
            delete.setBounds(1460, 745, 150, 50);

            //---- modify ----
            modify.setText("\u4fee\u6539");
            modify.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 20));
            modify.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    modifyMouseClicked(e);
                }
            });
            panel3.add(modify);
            modify.setBounds(1285, 745, 150, 50);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/shopSystemView/pic/imageonline-co-brightnessadjusted (5).png")));
            panel3.add(label2);
            label2.setBounds(0, -150, 1685, label2.getPreferredSize().height);

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
        add(panel3);
        panel3.setBounds(0, 200, 1685, 830);

        //======== panel1 ========
        {
            panel1.setForeground(new Color(0x33ff66));
            panel1.setBackground(new Color(0x24321e));
            panel1.setLayout(null);

            //======== panel4 ========
            {
                panel4.setBackground(new Color(0x405032));
                panel4.setLayout(null);

                //---- queryText ----
                queryText.setFont(new Font("\u6977\u4f53", Font.BOLD, 26));
                panel4.add(queryText);
                queryText.setBounds(95, 0, 320, 50);

                //---- queryItem ----
                queryItem.setText("\u67e5\u627e\u5546\u54c1");
                queryItem.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 25));
                queryItem.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        queryItemMouseClicked(e);
                    }
                });
                panel4.add(queryItem);
                queryItem.setBounds(450, 0, 150, 50);

                //---- addItem ----
                addItem.setText("\u6dfb\u52a0\u5546\u54c1");
                addItem.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.PLAIN, 25));
                addItem.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        addItemMouseClicked(e);
                    }
                });
                panel4.add(addItem);
                addItem.setBounds(630, 0, 150, 50);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for(int i = 0; i < panel4.getComponentCount(); i++) {
                        Rectangle bounds = panel4.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel4.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel4.setMinimumSize(preferredSize);
                    panel4.setPreferredSize(preferredSize);
                }
            }
            panel1.add(panel4);
            panel4.setBounds(0, 150, 1685, 50);

            //---- label4 ----
            label4.setText("\u4e1c\u5357\u5927\u5b66\u7f51\u4e0a\u5546\u57ce");
            label4.setFont(new Font("\u6977\u4f53", Font.BOLD, 60));
            label4.setBackground(Color.white);
            label4.setForeground(Color.white);
            panel1.add(label4);
            label4.setBounds(1035, 15, 600, 135);

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
    private JPanel panel3;
    private JPanel panel2;
    private JLabel label3;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton returnto;
    private JButton delete;
    private JButton modify;
    private JLabel label2;
    private JPanel panel1;
    private JPanel panel4;
    private JTextField queryText;
    private JButton queryItem;
    private JButton addItem;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    public static void main(String[] arg) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                JFrame frame = new JFrame("Admin Shop View");
                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1685, 1030); // 设置 JFrame 大小


                SocketHelper socketHelper = new SocketHelper(); // 创建 SocketHelper 对象或者按照你的需求初始化
                socketHelper.getConnection(socketHelper.ip, socketHelper.port);
                try {
                    socketHelper.getOs().writeInt(1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                adminShopView admin = null;
                admin = new adminShopView(socketHelper);
                addModifyItem add = new addModifyItem(socketHelper);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(admin); // 将 userShopView 添加到 JFrame 中

                frame.setVisible(true); // 设置 JFrame 可见

            }
        });
    }


}
