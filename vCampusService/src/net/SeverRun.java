package net;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SeverRun extends JFrame {
    ServerThread serverThread;

    public JTextArea textArea;

    public void beauty(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public SeverRun() {
        beauty();
        initComponents();
        setSize(700,420);
        panel1.setBounds(0,0,700,420);
        panel1.setOpaque(true);
        panel1.setBackground(new Color(0xC2DBDE));
        button2.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        //setBackground(new Color(0x5B6AA5));
        textArea=textArea1;
        serverThread=new ServerThread(this);
        textArea1.append("服务器已开启"+"\n");
        serverThread.run();
    }




    private void button2MouseClicked(MouseEvent e) {
        if(serverThread!=null){
            serverThread.close();
            JOptionPane.showMessageDialog(this, "服务器已关闭");
            this.dispose();
            System.exit(0);
        }
        else{
            System.out.println("服务器空");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        button2 = new JButton();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        panel1 = new JPanel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- button2 ----
        button2.setText("\u5173\u95ed\u670d\u52a1\u5668");
        button2.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 16));
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });
        contentPane.add(button2);
        button2.setBounds(540, 165, 115, 35);

        //======== scrollPane1 ========
        {
            scrollPane1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));

            //---- textArea1 ----
            textArea1.setFont(new Font("\u6977\u4f53", Font.PLAIN, 24));
            scrollPane1.setViewportView(textArea1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(15, 20, 500, 300);

        //======== panel1 ========
        {
            panel1.setLayout(null);

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
        contentPane.add(panel1);
        panel1.setBounds(new Rectangle(new Point(0, 0), panel1.getPreferredSize()));

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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JButton button2;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JPanel panel1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
