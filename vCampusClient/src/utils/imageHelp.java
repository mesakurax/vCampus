package utils;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class imageHelp{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 创建按钮
        JButton button = new JButton("选择图像");

        // 添加按钮点击事件处理程序
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建文件选择器
                JFileChooser fileChooser = new JFileChooser();

                // 设置文件过滤器，只显示图片文件
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
                fileChooser.setFileFilter(filter);

                // 显示文件选择对话框
                int result = fileChooser.showOpenDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    // 用户选择了文件
                    File selectedFile = fileChooser.getSelectedFile();
                    String imagePath = selectedFile.getAbsolutePath();
                    System.out.println("选择的图像文件: " + imagePath);
                    // 在这里进行图像处理操作，如显示图像等
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    // 用户取消选择文件
                    System.out.println("取消选择图像");
                } else if (result == JFileChooser.ERROR_OPTION) {
                    // 发生错误
                    System.out.println("发生错误");
                }
            }
        });

        // 将按钮添加到窗口
        frame.getContentPane().add(button);
        frame.setVisible(true);
    }
}