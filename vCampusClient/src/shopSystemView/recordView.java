/*
 * Created by JFormDesigner on Wed Sep 06 17:14:30 CST 2023
 */

package shopSystemView;

import javax.swing.table.*;

import utils.UIStyler;
import utils.SocketHelper;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.table.*;

import Decoder.BASE64Decoder;
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
public class recordView extends JPanel {

    shopSystem shop_user;
    String uid;
    Object[][] data;
    String[] columnNames;
    public recordView(SocketHelper socketHelper,shopSystem shop_user,String uid) {
        this.shop_user = shop_user;
        this.uid = uid;
        initComponents();
        UIStyler.setTransparentTable(scrollPane1);
        table1.setRowHeight(60);
    }
    public void run(String uid){

        setTable(shop_user.queryAllRecord(uid));
        scrollPane1.setVisible(true);
        table1.setVisible(true);

    }

    public void setTable(Vector<ItemRecord> itemList) {
        data = new Object[][]{};
        columnNames = new String[]{
                "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u5355\u4ef7", "\u6570\u91cf", "\u603b\u4ef7", "\u4ea4\u6613\u65f6\u95f4"};

        DefaultTableModel tableModel=(DefaultTableModel) table1.getModel();
        tableModel.setRowCount(0);
        Vector<Object[]> ArrayData = new Vector<>();
        if(itemList!=null)
            for (int i = 0; i < itemList.size(); i++) {
                ItemRecord temp = itemList.elementAt(i);
                Vector<Object> rowData = new Vector<>();
               // String url = "";
                rowData.add(String.valueOf(temp.getItemId()));
                rowData.add(temp.getItemName());
                rowData.add(String.valueOf(temp.getItemPrc()));
                rowData.add(String.valueOf(temp.getItemNum()));
                rowData.add(String.valueOf(temp.getTotalPrc()));
                rowData.add(temp.getTime());
                Object[] tempObj = new Object[rowData.size()];
                rowData.toArray(tempObj);
                ArrayData.add(tempObj);
            }
        Object[][] data = new Object[ArrayData.size()][];
        ArrayData.toArray(data);

        DefaultTableModel model=new DefaultTableModel(data,columnNames);
        table1.setModel(model);
    }

    public void query(Vector<ItemRecord> rs,String uid){   //主界面查询消费记录
        setTable(rs);
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        label1 = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(1685, 780));
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
                        "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u5355\u4ef7", "\u6570\u91cf", "\u603b\u4ef7", "\u4ea4\u6613\u65f6\u95f4"
                    }
                ));
                table1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 27));
                scrollPane1.setViewportView(table1);
            }
            panel1.add(scrollPane1);
            scrollPane1.setBounds(15, 35, 1635, 730);

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
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
