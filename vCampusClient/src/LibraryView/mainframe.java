/*
 * Created by JFormDesigner on Thu Sep 14 09:30:42 CST 2023
 */

package LibraryView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

/**
 * @author 86153
 */
public class mainframe extends JPanel {

    private ImageIcon[] images;
    private int currentIndex;

    public mainframe() {

        initComponents();
        setSize(1685, 1030);
        images=new ImageIcon[5];
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        images[1]=new ImageIcon(projectPath+"/src/LibraryView/pic/lib1.jpg");
        images[2]=new ImageIcon(projectPath+"/src/LibraryView/pic/lib2.png");
        images[3]=new ImageIcon(projectPath+"/src/LibraryView/pic/lib3.png");
        images[4]=new ImageIcon(projectPath+"/src/LibraryView/pic/lib4.jpg");
        images[0]=new ImageIcon(projectPath+"/src/LibraryView/pic/lib1.jpg");

        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setIcon(images[0]);
        // beautify();

        // 创建定时器，用于切换图片
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % images.length;
                imageLabel.setIcon(images[currentIndex]);
            }
        });
        timer.start();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        imageLabel = new JLabel();
        label3 = new JLabel();
        label2 = new JLabel();
        label1 = new JLabel();

        //======== this ========
        setLayout(null);
        add(imageLabel);
        imageLabel.setBounds(325, 155, 1000, 500);

        //---- label3 ----
        label3.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/QQ\u56fe\u724720230914120818.gif")));
        add(label3);
        label3.setBounds(1370, 25, 285, 250);

        //---- label2 ----
        label2.setText("\u4e1c\u5357\u5927\u5b66\u56fe\u4e66\u9986\u6b22\u8fce\u4f60\uff01");
        label2.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 48));
        label2.setForeground(Color.white);
        add(label2);
        label2.setBounds(535, 60, label2.getPreferredSize().width, 50);

        //---- label1 ----
        label1.setBackground(new Color(0xb2d66e));
        label1.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/admin.jpg")));
        add(label1);
        label1.setBounds(0, 0, 1685, 830);

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
    private JLabel imageLabel;
    private JLabel label3;
    private JLabel label2;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
