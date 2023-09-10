package chatView;
import entity.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class mytextpane {
    private String uid;
    private JTextPane textPane;

    public mytextpane(String uid, JTextPane textPane) {
        this.uid = uid;
        this.textPane = textPane;

        textPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int offset = textPane.viewToModel(e.getPoint());

                StyledDocument doc = textPane.getStyledDocument();
                // 获取点击位置的元素
                Element element = doc.getCharacterElement(offset);


                if (element.getAttributes().getAttribute(StyleConstants.IconAttribute) != null) {
                    Icon icon = (Icon) element.getAttributes().getAttribute(StyleConstants.IconAttribute);

                    // 创建一个弹出窗口
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(textPane);

                    try {
                        // 创建临时文件
                        // 获取系统默认的图片查看器
                        Desktop desktop = Desktop.getDesktop();

                        // 创建临时文件
                        File tempFile = File.createTempFile("image", ".png");

                        // 将图像保存到临时文件
                        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
                        icon.paintIcon(null, image.getGraphics(), 0, 0);
                        ImageIO.write(image, "png", tempFile);

                        // 打开临时文件，调用系统默认的图片查看器
                        desktop.open(tempFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    // 文本处理逻辑
                    Element paragraphElement = doc.getParagraphElement(offset);

                    try {
                        int start = paragraphElement.getStartOffset();
                        int end = paragraphElement.getEndOffset();

                        String text = doc.getText(start, end - start).trim();

                        if(!text.isEmpty()) {
                            String projectPath = System.getProperty("user.dir");
                            String savePath = projectPath + "/" + "src/chatView/Sourse/" + text;
                            File file = new File(savePath);

                            if (file.exists()) {
                                Desktop.getDesktop().open(file);
                            } else {
                                System.out.println("文件不存在");
                            }
                        }
                    } catch (BadLocationException | IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public JTextPane getTextPane() {
        return textPane;
    }

    public void setTextPane(JTextPane textPane) {
        this.textPane = textPane;
    }

    public static JTextPane copyTextPane(JTextPane sourceTextPane,String uid) {
            JTextPane targetTextPane = new JTextPane();

            // 获取源文本样式
            StyledDocument sourceDoc = sourceTextPane.getStyledDocument();
            StyledDocument targetDoc = targetTextPane.getStyledDocument();

            // 复制样式
            int length = sourceDoc.getLength();
            AttributeSet attrs = sourceDoc.getCharacterElement(0).getAttributes();
            targetDoc.setCharacterAttributes(0, length, attrs, true);

            // 复制字体和颜色
            Style defaultStyle = targetDoc.getStyle(StyleContext.DEFAULT_STYLE);
            Style sourceStyle = sourceDoc.getStyle(StyleContext.DEFAULT_STYLE);
            targetDoc.setLogicalStyle(0, defaultStyle);
            targetDoc.getLogicalStyle(0).addAttributes(sourceStyle);

             StyledDocument doc = targetTextPane.getStyledDocument();
             SimpleAttributeSet centerAlignment = new SimpleAttributeSet();
              StyleConstants.setAlignment(centerAlignment, StyleConstants.ALIGN_CENTER);
             doc.setParagraphAttributes(0, doc.getLength(), centerAlignment, false);

        // 创建样式
                 Style style = sourceTextPane.addStyle("CustomStyle", null);
              StyleConstants.setBackground(style, Color.GRAY); // 设置背景颜色为灰色

        // 设置样式
         doc.setParagraphAttributes(0, doc.getLength(), style, false);

        try {
            // 设置欢迎语的样式
            SimpleAttributeSet welcomeStyle = new SimpleAttributeSet();
            StyleConstants.setFontFamily(welcomeStyle, "幼圆");  // 设置字体为幼圆
            StyleConstants.setFontSize(welcomeStyle, 24);  // 设置字体大小为20
            doc.insertString(doc.getLength(), "开始和"+uid+"聊天吧！！\n", welcomeStyle);

            // 设置后续行的样式
            SimpleAttributeSet textStyle = new SimpleAttributeSet();
            StyleConstants.setFontFamily(textStyle, "幼圆");  // 设置字体为幼圆
            StyleConstants.setFontSize(textStyle, 18);  // 设置字体大小为14
            doc.setParagraphAttributes(doc.getLength(), 1, textStyle, false);
            SimpleAttributeSet topAlignment = new SimpleAttributeSet();
            StyleConstants.setAlignment(topAlignment, StyleConstants.ALIGN_LEFT);
            doc.setParagraphAttributes(doc.getLength(), 1, topAlignment, false);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

            return targetTextPane;
        }

}
