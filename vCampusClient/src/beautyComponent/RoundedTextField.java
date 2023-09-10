package beautyComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;

public class RoundedTextField extends JTextField {
    private int arc;
    private Color borderColor = Color.BLACK; // 默认边框颜色
    private Color hoverBorderColor = new Color(0, 128, 0); // 深绿色
    private String placeholder = ""; // 占位符文本
    private boolean isPlaceholderVisible = true;
    private Font customFont = new Font("宋体", Font.PLAIN, 15); // 默认字体

    private void init(){
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setFont(customFont);

        // 添加鼠标事件监听器
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // 鼠标悬停时，设置边框颜色为浅绿色
                borderColor = hoverBorderColor;
                repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // 鼠标离开时，恢复默认的边框颜色
                borderColor = Color.BLACK;
                repaint();
            }
        });

        // 添加焦点监听器
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // 文本框获得焦点时，清空占位符文本，字体变为黑色
                if (isPlaceholderVisible) {
                    setText("");
                    setForeground(Color.BLACK);
                    isPlaceholderVisible = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // 文本框失去焦点时，如果文本为空，显示占位符文本，字体颜色为灰色
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setFont(new Font("华文仿宋", Font.BOLD | Font.ITALIC, getFont().getSize()));
                    setForeground(Color.GRAY);
                    isPlaceholderVisible = true;
                }
            }
        });
    }
    public RoundedTextField() {
        this.arc = 30;
        init();
    }
    public RoundedTextField(int arc) {
        this.arc = arc;
        init();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, arc, arc);

        g2.setColor(getBackground());
        g2.fill(roundedRectangle);

        g2.setColor(borderColor); // 使用当前边框颜色
        g2.draw(roundedRectangle);

        super.paintComponent(g2);
        g2.dispose();
    }

    // 设置占位符文本
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    // 设置字体
    public void setCustomFont(Font font) {
        customFont = font;
        setFont(font);
        repaint();
    }

    // 设置文本框的圆角半径
    public void setArc(int arc) {
        this.arc = arc;
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        RoundedTextField textField = new RoundedTextField(30);
        textField.setPreferredSize(new Dimension(150, 30));

        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(textField);
        frame.add(new JButton());

        // 设置占位符文本
        textField.setPlaceholder("请输入文本");

        // 设置字体大小
        textField.setCustomFont(new Font("微软雅黑", Font.PLAIN, 16));
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
}
