package beautyComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

class TransparentHeaderRenderer extends DefaultTableCellRenderer {
    public TransparentHeaderRenderer() {
        setOpaque(false);
    }
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setBackground(new Color(0, 0, 0, 0)); //设置背景色为透明
        return this;
    }
}
public class TransparentTable {
    public TransparentTable(){}

    //将传入滑动条内的Table设为透明
    public void set(JScrollPane scrollPane1){
        JViewport viewport = scrollPane1.getViewport();
        JTable table1 = (JTable) viewport.getView();
        TransparentHeaderRenderer hr = new TransparentHeaderRenderer(); //创建自定义的渲染器
        hr.setOpaque(false);
        hr.setHorizontalAlignment(JLabel.CENTER);

        table1.getTableHeader().setDefaultRenderer(hr);
        table1.setOpaque(false);//将table设置为透明
        table1.setDefaultRenderer(Object.class, hr);

        JTableHeader head = table1.getTableHeader(); // 创建表格标题对象
        head.setOpaque(false);

        scrollPane1.setOpaque(false);
        scrollPane1.getViewport().setOpaque(false);
        scrollPane1.getVerticalScrollBar().setOpaque(false);//滚动条设置透明
        scrollPane1.setColumnHeaderView(table1.getTableHeader());
        scrollPane1.getColumnHeader().setOpaque(false);
    }
}
