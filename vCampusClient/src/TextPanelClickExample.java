import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TextPanelClickExample extends JFrame {
    private JTextPane textPane;

    public TextPanelClickExample() {
        setTitle("Text Panel Click Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textPane = new JTextPane();
        textPane.setEditable(false);
        add(new JScrollPane(textPane), BorderLayout.CENTER);

        // 添加鼠标点击事件监听器
        textPane.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int offset = textPane.viewToModel(e.getPoint()); // 获取点击位置的偏移量
                Document document = textPane.getDocument();
                try {
                    int startOffset = Utilities.getRowStart(textPane, offset); // 获取所在行的起始偏移量
                    int endOffset = Utilities.getRowEnd(textPane, offset); // 获取所在行的结束偏移量
                    String lineText = document.getText(startOffset, endOffset - startOffset); // 提取点击位置所在行的文本
                    int mp4Index = lineText.indexOf(".mp4"); // 查找 .mp4 的位置
                    if (mp4Index != -1) {
                        lineText = lineText.substring(0, mp4Index + 4); // 截取 .mp4 之前的部分（包括 .mp4）
                        System.out.println("Clicked line text: " + lineText);
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextPanelClickExample frame = new TextPanelClickExample();
            frame.setVisible(true);

            // 添加示例文本
            frame.textPane.setText("Video1.mp4\nVideo2.mov\nImage1.jpg\nVideo3.mp4yuihbhj");
        });
    }
}