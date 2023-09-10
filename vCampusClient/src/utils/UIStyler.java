package utils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIStyler {
    public UIStyler() {}

    public static void createHeader(Container container) {
        // 创建粗绿条
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(36, 50, 30));
        topPanel.setBounds(0, 0, 1685, 150);
        container.add(topPanel);

        // 创建细绿条
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(64, 80, 50));
        bottomPanel.setBounds(0, 150, 1685, 50);
        container.add(bottomPanel);
    }

    public static void setCombobox(JComboBox comboBox) {
        comboBox.setOpaque(false);
        comboBox.setBackground(new Color(64, 80, 50, 128));
        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public int getWidth() {
                        return 0;
                    }
                };
            }
        });
        comboBox.setBorder(BorderFactory.createLineBorder(new Color(64, 80, 50), 2));
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                list.setSelectionBackground(new Color(64, 80, 50, 128));
                if (isSelected) {
                    renderer.setBackground(new Color(64, 80, 50)); // 选中项的背景颜色为半透明
                    renderer.setForeground(Color.white); // 选中项的字体颜色为白色
                } else {
                    renderer.setBackground(new Color(255, 255, 255)); // 未选中项的背景颜色为半透明
                    renderer.setForeground(Color.black); // 未选中项的字体颜色为黑色
                }
                return renderer;
            }
        });
    }

    public static  void setTextField(JTextField textField)
    {
        textField.setBorder(BorderFactory.createLineBorder(new Color(64, 80, 50),2));
        textField.setOpaque(false);
        textField.setBackground(Color.white);  // 设置背景颜色为白色
        textField.setForeground(Color.black);

    }

    public static void setTopButton(JButton button1) {
        button1.setSize(150, 50);
        button1.setForeground(Color.black);  //字体颜色
        button1.setFont(new Font("楷体", Font.BOLD, 26));
        button1.setMargin(new Insets(0, 0, 0, 0));//将边框外的上下左右空间设置为0
        button1.setContentAreaFilled(false);//除去默认的背景填充
        button1.setBorderPainted(false);//不打印边框
        button1.setBorder(null);
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button1.setOpaque(true);
                button1.setBackground(Color.white);  //鼠标移上去后变白
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button1.setOpaque(false);
            }
        });
    }

    public static void setBelowButton(JButton button1) {
        button1.setSize(150, 50);
        button1.setFont(new Font("华文仿宋", Font.BOLD, 24));
    }

    public static void setBelowButton1(JButton button1) {
        button1.setBorder(BorderFactory.createLineBorder(new Color(64, 80, 50),2));
        button1.setForeground(Color.black);  //字体颜色
        button1.setBackground(Color.white);
        button1.setContentAreaFilled(false);//除去默认的背景填充
        button1.setSize(150, 50);
        button1.setFont(new Font("华文仿宋", Font.BOLD, 24));

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                button1.setContentAreaFilled(true);//除去默认的背景填充
                button1.setForeground(Color.white);
                button1.setBackground(new Color(64, 80, 50));

            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                button1.setContentAreaFilled(false);
                button1.setForeground(Color.black);  //字体颜色
                button1.setBackground(Color.white);
            }
        });
    }

    //将传入滑动条内的Table设为透明
    public static void setTransparentTable(JScrollPane scrollPane1) {
        JViewport viewport = scrollPane1.getViewport();
        JTable table1 = (JTable) viewport.getView();
        TransparentHeaderRenderer hr = new TransparentHeaderRenderer(); // 创建自定义的渲染器
        hr.setOpaque(false);
        hr.setHorizontalAlignment(JLabel.CENTER);

        table1.getTableHeader().setDefaultRenderer(hr);
        table1.setOpaque(false); // 将table设置为透明
        table1.setDefaultRenderer(Object.class, hr);
        table1.setForeground(Color.WHITE);

        JTableHeader head = table1.getTableHeader(); // 创建表格标题对象
        head.setFont(new Font("华文仿宋", Font.BOLD, 40));
        head.setPreferredSize(new Dimension(head.getWidth(), 40));
        table1.setFont(new Font("华文仿宋", Font.BOLD, 28));
        head.setOpaque(false);

        scrollPane1.setOpaque(false);
        scrollPane1.getViewport().setOpaque(false);
        scrollPane1.getVerticalScrollBar().setOpaque(false); // 滚动条设置透明
        scrollPane1.setColumnHeaderView(table1.getTableHeader());
        scrollPane1.getColumnHeader().setOpaque(false);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setFont(new Font("华文仿宋", Font.BOLD, 28));
                // 自定义颜色设置
                if (isSelected) {
                    // 当前行被选中时的颜色
                    component.setBackground(Color.WHITE);
                    component.setForeground(Color.BLACK);
                } else {
                    // 其他行的颜色
                    component.setBackground(new Color(0, 0, 0, 0)); // 设置背景透明
                    component.setForeground(Color.WHITE);
                }

                return component;
            }
        };
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // 设置 TableCellRenderer 为表格的默认渲染器
        table1.setDefaultRenderer(Object.class, cellRenderer);

        // 添加选择监听器以设置选中行的颜色
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // 设置只能选择单行
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // 确保只在选择完成时触发
                    int selectedRow = table1.getSelectedRow();
                    table1.repaint(); // 刷新表格以更新选中行的颜色
                }
            }
        });
    }
}

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
