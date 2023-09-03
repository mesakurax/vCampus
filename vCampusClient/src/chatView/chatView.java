/*
 * Created by JFormDesigner on Sat Sep 02 22:34:52 AWST 2023
 */

package chatView;


import bankSystemView.admin_1;
import module.*;
import entity.*;
import utils.*;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import javax.swing.*;
import javax.swing.text.StyledDocument;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * @author 22431
 */
public class chatView extends JPanel {
    private SocketHelper message=new SocketHelper();;

    private User uu;

    public void beautify(){
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public chatView(User uu) {

        this.uu=uu;
        message.getConnection(message.ip,message.port);
        try
        {
            //告诉服务器是消息通道
            message.getOs().writeInt(0);
            message.getOs().flush();

            //初始化用户名字
            message.getOs().writeInt(-100);
            message.getOs().flush();
            message.getOs().writeObject(uu.getName());
            message.getOs().flush();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        beautify();
        initComponents();
        new Thread(new Message_L()).start();

    }

    private void messageMouseClicked() {
        // TODO add your code here
        if(textField1.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"请不要发送空消息！！");
            return;
        }

        if(comboBox1.getSelectedItem().toString().equals("群发")){
            String mes="(文本) "+uu.getId()+" "+uu.getName()+": ";
            mes=mes+"\n"+textField1.getText()+"                             ";
            for(int k=0;k<50;k++)
            {
                mes=mes+"-";
            }
            mes=mes+"----"+Timehelp.getCurrentTime();

            try {
                message.getOs().writeInt(001);
                message.getOs().flush();
                message.getOs().writeObject(mes);
                message.getOs().flush();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }

        else if(comboBox1.getSelectedItem().toString().equals("私聊")){
            String mes="(文本) "+uu.getId()+" "+uu.getName()+": ";
            mes=mes+"\n"+textField1.getText()+"                              ";
            for(int k=0;k<50;k++)
            {
                mes=mes+"-";
            }
            mes=mes+"----"+Timehelp.getCurrentTime();
            textPane1.setText(textPane1.getText() + mes + "\r\n\n"); // 使用 setText()

            try {
                message.getOs().writeInt(002);
                message.getOs().flush();
                message.getOs().writeObject(textField2.getText());
                message.getOs().flush();
                message.getOs().writeObject(mes);
                message.getOs().flush();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }

        textField1.setText("");
    }


    private void comboBox1ItemStateChanged() {
        // TODO add your code here
        if(comboBox1.getSelectedItem().toString().equals("群发"))
            textField2.setText("");
    }


    private void imageMouseClicked() {
        // TODO add your code here
        if(comboBox1.getSelectedItem().toString().equals("群发"))
        {
            JFileChooser fileChooser = new JFileChooser();

            // 设置文件过滤器，只显示图片文件
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
            fileChooser.setFileFilter(filter);

            // 显示文件选择对话框
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                // 用户选择了文件
                File selectedFile = fileChooser.getSelectedFile();
                String imagePath = selectedFile.getAbsolutePath();
                System.out.println("选择的图像文件: " + imagePath);


                try {
                    //代表发送图片
                    message.getOs().writeInt(003);
                    message.getOs().flush();

                    String mes="(图像) "+uu.getId()+" "+uu.getName()+":                                   ";
                    for(int k=0;k<50;k++)
                    {
                        mes=mes+"-";
                    }
                    mes=mes+"----"+Timehelp.getCurrentTime();
                    mes=mes+"\n"+textField1.getText();
                    message.getOs().writeObject(mes);
                    message.getOs().flush();

                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(imagePath));
                    int readLen = 0;
                    byte[] buf = new byte[1024];
                    while( (readLen = bis.read(buf)) != -1 ) {
                        message.getOs().write(buf,0,readLen);
                    }
                    message.getOs().flush();
                    bis.close();

                    String stopMessage = "STOP";
                    byte[] stopData = stopMessage.getBytes();
                    message.getOs().write(stopData);
                    message.getOs().flush();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            } else if (result == JFileChooser.CANCEL_OPTION) {
                // 用户取消选择文件
                System.out.println("取消选择图像");
            } else if (result == JFileChooser.ERROR_OPTION) {
                // 发生错误
                System.out.println("发生错误");
            }

        }
    }


    class Message_L implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    int cmd=message.getIs().readInt();
                    if (cmd == 0011) {
                        String mes = (String) message.getIs().readObject();
                        System.out.println(mes);
                        StyledDocument doc = textPane1.getStyledDocument();
                        doc.insertString(doc.getLength(), mes+"\n", null);
                        doc.insertString(doc.getLength(), "\n", null);

                    }
                    else if (cmd == 0021)
                    {
                        String mes = (String) message.getIs().readObject();
                        System.out.println(mes);
                        StyledDocument doc = textPane1.getStyledDocument();
                        doc.insertString(doc.getLength(), mes+"\n", null);
                        doc.insertString(doc.getLength(), "\n", null);
                    }
                    else if(cmd==0031)
                    {
                        String mes = (String) message.getIs().readObject();
                        System.out.println(mes);

                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                        byte[] buffer = new byte[1024];
                        int bytesRead;

                        while ((bytesRead = message.getIs().read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);

                            if (new String(buffer, 0, bytesRead).equals("STOP")) {
                                break;
                            }
                        }

                        byte[] imageData = outputStream.toByteArray();

                        // 将字节数组转换为图像
                        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
                        Image image = ImageIO.read(inputStream);


                        Icon icon = new ImageIcon(image);


                        StyledDocument doc = textPane1.getStyledDocument();
                        // 获取文档
                        doc.insertString(doc.getLength(), mes, null);
                        doc.insertString(doc.getLength(), " ", null);
                        textPane1.setCaretPosition(doc.getLength()); // 将插入位置移动到最后
                        textPane1.insertIcon(icon);
                        textPane1.setCaretPosition(doc.getLength()); // 将插入位置移动到最后
                        doc.insertString(doc.getLength(), "\n", null);
                        doc.insertString(doc.getLength(), "\n", null);


                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        scrollPane3 = new JScrollPane();
        textPane1 = new JTextPane();
        textField1 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        comboBox1 = new JComboBox<>();
        textField2 = new JTextField();

        //======== this ========
        setPreferredSize(new Dimension(1680, 1030));
        setLayout(null);

        //======== scrollPane3 ========
        {
            scrollPane3.setViewportView(textPane1);
        }
        add(scrollPane3);
        scrollPane3.setBounds(340, 35, 1060, 810);
        add(textField1);
        textField1.setBounds(645, 935, 375, 60);

        //---- button1 ----
        button1.setText("\u6d88\u606f");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageMouseClicked();
            }
        });
        add(button1);
        button1.setBounds(new Rectangle(new Point(1460, 705), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("\u56fe\u7247");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imageMouseClicked();
            }
        });
        add(button2);
        button2.setBounds(new Rectangle(new Point(1145, 950), button2.getPreferredSize()));

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "\u7fa4\u53d1",
            "\u79c1\u804a"
        }));
        comboBox1.setFont(new Font("\u534e\u6587\u4eff\u5b8b", Font.BOLD, 14));
        comboBox1.addItemListener(e -> comboBox1ItemStateChanged());
        add(comboBox1);
        comboBox1.setBounds(650, 890, 78, 30);
        add(textField2);
        textField2.setBounds(745, 885, 170, 40);

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
    private JScrollPane scrollPane3;
    private JTextPane textPane1;
    private JTextField textField1;
    private JButton button1;
    private JButton button2;
    private JComboBox<String> comboBox1;
    private JTextField textField2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

}
