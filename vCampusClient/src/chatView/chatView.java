/*
 * Created by JFormDesigner on Sat Sep 02 22:34:52 AWST 2023
 */

package chatView;


import javax.swing.event.*;

import entity.*;
import utils.*;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
import javax.swing.text.*;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;


/**
 * @author 22431
 */
public class chatView extends JPanel{
    private SocketHelper message=new SocketHelper();;

    private User uu;

    private tonghua_wait deng;

    private tonghua_accept accept;

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

        beautify();
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
            message.getOs().writeObject(uu.getId());
            message.getOs().flush();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        initComponents();
        new Thread(new Message_L()).start();

        StyledDocument doc = textPane1.getStyledDocument();
        SimpleAttributeSet centerAlignment = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAlignment, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), centerAlignment, false);

        try {
            // 设置欢迎语的样式
            SimpleAttributeSet welcomeStyle = new SimpleAttributeSet();
            StyleConstants.setFontFamily(welcomeStyle, "幼圆");  // 设置字体为幼圆
            StyleConstants.setFontSize(welcomeStyle, 22);  // 设置字体大小为20
            doc.insertString(doc.getLength(), "欢迎来到聊天室！\n", welcomeStyle);

            // 设置后续行的样式
            SimpleAttributeSet textStyle = new SimpleAttributeSet();
            StyleConstants.setFontFamily(textStyle, "幼圆");  // 设置字体为幼圆
            StyleConstants.setFontSize(textStyle, 16);  // 设置字体大小为14
            doc.setParagraphAttributes(doc.getLength(), 1, textStyle, false);
            SimpleAttributeSet topAlignment = new SimpleAttributeSet();
            StyleConstants.setAlignment(topAlignment, StyleConstants.ALIGN_LEFT);
            doc.setParagraphAttributes(doc.getLength(), 1, topAlignment, false);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
        refreshlist();
    }

    private void messageMouseClicked() {
        // TODO add your code here
        if(textField2.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"请选择聊天对象！！");
            refreshlist();
            return;
        }
        if(textField1.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"请不要发送空消息！！");
            refreshlist();
            return;
        }

        if(textField2.getText().equals("All user")){
            String mes="(群发文本) "+uu.getId()+" "+uu.getName();
            mes=mes+"  ["+Timehelp.getCurrentTime()+"] :\n";
            mes=mes+textField1.getText();

            try {
                message.getOs().writeInt(001);
                message.getOs().flush();
                message.getOs().writeObject(mes);
                message.getOs().flush();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }

        else {
            String mes="(私聊文本) "+uu.getId()+" "+uu.getName();
            mes=mes+"  ["+Timehelp.getCurrentTime()+"] :\n";
            mes=mes+textField1.getText();

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
        refreshlist();

    }


    private void imageMouseClicked() {
        // TODO add your code here
        if(textField2.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"请选择聊天对象！！");
            refreshlist();
            return;
        }
        if(textField2.getText().equals("All user"))
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

                    String mes="(群发图片) "+uu.getId()+" "+uu.getName();
                    mes=mes+"  ["+Timehelp.getCurrentTime()+"] :\n";

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

        else
        {
            if(textField2.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this,"请输入聊天对象的ID！！");
                return;
            }

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
                    message.getOs().writeInt(004);
                    message.getOs().flush();

                    message.getOs().writeObject(textField2.getText());
                    message.getOs().flush();

                    String mes="(私聊图片) "+uu.getId()+" "+uu.getName();
                    mes=mes+"  ["+Timehelp.getCurrentTime()+"] :\n";

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

        refreshlist();
    }


    private void fileMouseClicked() {
        // TODO add your code here
        if(textField2.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"请选择聊天对象！！");
            refreshlist();
            return;
        }
        if (textField2.getText().equals("All user")) {
            JFileChooser fileChooser = new JFileChooser();

            // 设置文件选择器的标题
            fileChooser.setDialogTitle("选择文件");

            // 显示文件选择器对话框
            int result = fileChooser.showOpenDialog(null);

            // 处理用户选择的结果
            if (result == JFileChooser.APPROVE_OPTION) {
                // 用户选择了文件
                String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                System.out.println("已选择文件路径：" + selectedFilePath);

                try {
                    //代表发送文件
                    message.getOs().writeInt(005);
                    message.getOs().flush();

                    String selectedFileName = new File(selectedFilePath).getName();
                    String mes="(群发文件) "+uu.getId()+" "+uu.getName();
                    mes=mes+"  ["+Timehelp.getCurrentTime()+"] :\n"+selectedFileName;
                    message.getOs().writeObject(mes);
                    message.getOs().flush();
                    message.getOs().writeObject(selectedFileName);
                    message.getOs().flush();

                    FileInputStream fis = new FileInputStream(selectedFilePath);

                    // 创建一个缓冲区，用于读取文件内容
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        // 将文件内容写入输出流
                        message.getOs().write(buffer, 0, bytesRead);
                    }
                    // 刷新输出流，并关闭连接
                    message.getOs().flush();
                    fis.close();


                    String stopMessage = "STOP";
                    byte[] stopData = stopMessage.getBytes();
                    message.getOs().write(stopData);
                    message.getOs().flush();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else if (result == JFileChooser.CANCEL_OPTION) {
                // 用户取消了选择
                System.out.println("选择被取消。");
            }
        }

        else {
            if (textField2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请输入聊天对象的ID！！");
                return;
            }
            JFileChooser fileChooser = new JFileChooser();

            // 设置文件选择器的标题
            fileChooser.setDialogTitle("选择文件");

            // 显示文件选择器对话框
            int result = fileChooser.showOpenDialog(null);

            // 处理用户选择的结果
            if (result == JFileChooser.APPROVE_OPTION) {
                // 用户选择了文件
                String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                System.out.println("已选择文件路径：" + selectedFilePath);

                try {
                    //代表发送文件
                    message.getOs().writeInt(006);
                    message.getOs().flush();
                    message.getOs().writeObject(textField2.getText());
                    message.getOs().flush();

                    String selectedFileName = new File(selectedFilePath).getName();
                    String mes="(私聊文件) "+uu.getId()+" "+uu.getName();
                    mes=mes+"  ["+Timehelp.getCurrentTime()+"] :\n"+selectedFileName;
                    message.getOs().writeObject(mes);
                    message.getOs().flush();
                    message.getOs().writeObject(selectedFileName);
                    message.getOs().flush();

                    FileInputStream fis = new FileInputStream(selectedFilePath);

                    // 创建一个缓冲区，用于读取文件内容
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        // 将文件内容写入输出流
                        message.getOs().write(buffer, 0, bytesRead);
                    }
                    // 刷新输出流，并关闭连接
                    message.getOs().flush();
                    fis.close();


                    String stopMessage = "STOP";
                    byte[] stopData = stopMessage.getBytes();
                    message.getOs().write(stopData);
                    message.getOs().flush();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else if (result == JFileChooser.CANCEL_OPTION) {
                // 用户取消了选择
                System.out.println("选择被取消。");
            }
        }

        refreshlist();
    }


    private void textPane1MouseClicked(MouseEvent e) {
        // TODO add your code here
        int offset = textPane1.viewToModel(e.getPoint());

        StyledDocument doc = textPane1.getStyledDocument();
        // 获取点击位置的元素
        Element element = doc.getCharacterElement(offset);


        if (element.getAttributes().getAttribute(StyleConstants.IconAttribute) != null) {
            Icon icon = (Icon) element.getAttributes().getAttribute(StyleConstants.IconAttribute);

            // 创建一个弹出窗口
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(textPane1);

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
                String projectPath = System.getProperty("user.dir");
                String savePath = projectPath + "/" + "src/chatView/Sourse/"+text;
                File file = new File(savePath);

                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("文件不存在");
                }
            } catch (BadLocationException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void refreshlist()
    {
        try {
            message.getOs().writeInt(007);
            message.getOs().flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void list1ValueChanged(ListSelectionEvent e) {
        // TODO add your code here
        if (!e.getValueIsAdjusting()) {
            JList<String> source = (JList<String>) e.getSource();
            String selectedItem = source.getSelectedValue();
            if(selectedItem!=null)
                textField2.setText(selectedItem);
        }
    }

    private void tonghuaMouseClicked(MouseEvent e) {
        // TODO add your code here
        if(textField2.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"通话对象不能为空");
            refreshlist();
            return;
        }
        if(textField2.getText().equals("All user"))
        {
            JOptionPane.showMessageDialog(this,"请选择指定用户进行通话!");
            refreshlist();
            return;
        }
        try {
            message.getOs().writeInt(8);
            message.getOs().writeObject(textField2.getText());
            message.getOs().flush();
            deng=new tonghua_wait();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    public class Message_L implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    int cmd=message.getIs().readInt();
                    if (cmd == 0011 ) {
                        String mes = (String) message.getIs().readObject();
                        System.out.println(mes);
                        StyledDocument doc = textPane1.getStyledDocument();
                        textPane1.setCaretPosition(doc.getLength()); // 将插入位置移动到最后
                        doc.insertString(doc.getLength(), "\n", null);
                        doc.insertString(doc.getLength(), "\n", null);
                        doc.insertString(doc.getLength(), mes, null);
                        refreshlist();

                    }
                    else if(cmd==0031) {
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


                        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
                        BufferedImage image = ImageIO.read(inputStream);
                        inputStream.close();


                        ImageIcon icon = ImageHelper.resizeImage(image, 700, 600);

                        StyledDocument doc = textPane1.getStyledDocument();

                        textPane1.setCaretPosition(doc.getLength()); // 将插入位置移动到最后
                        doc.insertString(doc.getLength(), "\n", null);
                        doc.insertString(doc.getLength(), "\n", null);
                        doc.insertString(doc.getLength(), mes, null);
                        doc.insertString(doc.getLength(), " ", null);
                        textPane1.setCaretPosition(doc.getLength()); // 将插入位置移动到最后
                        textPane1.insertIcon(icon);
                        refreshlist();

                    }
                    else if(cmd==0051){
                        String mes = (String) message.getIs().readObject();
                        System.out.println(mes);
                        String filename=(String) message.getIs().readObject();


                        // 获取当前项目的根路径
                        String projectPath = System.getProperty("user.dir");
                        String savePath = projectPath + "/" + "src/chatView/Sourse/"+filename;
                        FileOutputStream fos = new FileOutputStream(savePath); // 将 "path/to/save/" 替换为你想要保存文件的实际路径
                        System.out.println(savePath);

                        // 接收文件数据并写入文件
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = message.getIs().read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                            fos.flush();
                            if (new String(buffer, 0, bytesRead).equals("STOP")) {
                                break;
                            }
                        }
                        fos.close();

                        StyledDocument doc = textPane1.getStyledDocument();

                        textPane1.setCaretPosition(doc.getLength()); // 将插入位置移动到最后
                        doc.insertString(doc.getLength(), "\n", null);
                        doc.insertString(doc.getLength(), "\n", null);
                        doc.insertString(doc.getLength(), mes, null);
                        refreshlist();
                    }
                    else if(cmd==0071){
                        String[] result=(String[]) message.getIs().readObject();
                        DefaultListModel<String> listModel = new DefaultListModel<>();
                        listModel.addElement("All user");
                        for (String element : result) {
                            listModel.addElement(element);
                        }
                        list1.setModel(listModel);
                    }
                    else if(cmd==81)
                    {
                        accept=new tonghua_accept(message);
                        accept.setVisible(true);
                    }
                    else if(cmd==91) {
                        JOptionPane.showMessageDialog(null,"对方拒绝了你的通话！");
                        deng.dispose();
                    }
                    else if(cmd==92) {
                        String ip=(String) message.getIs().readObject();
                        String text=(String) message.getIs().readObject();
                        deng.dispose();
                        new tonghua_clinet(ip,text);
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
        textField2 = new JTextField();
        button3 = new JButton();
        button4 = new JButton();
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        label1 = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(1680, 1030));
        setLayout(null);

        //======== scrollPane3 ========
        {

            //---- textPane1 ----
            textPane1.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 13));
            textPane1.setEditable(false);
            textPane1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    textPane1MouseClicked(e);
                }
            });
            scrollPane3.setViewportView(textPane1);
        }
        add(scrollPane3);
        scrollPane3.setBounds(285, 30, 1090, 775);
        add(textField1);
        textField1.setBounds(520, 895, 460, 60);

        //---- button1 ----
        button1.setText("\u6d88\u606f");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageMouseClicked();
            }
        });
        add(button1);
        button1.setBounds(1010, 900, 118, 55);

        //---- button2 ----
        button2.setText("\u56fe\u7247");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imageMouseClicked();
            }
        });
        add(button2);
        button2.setBounds(1150, 900, 115, 55);

        //---- textField2 ----
        textField2.setEditable(false);
        add(textField2);
        textField2.setBounds(520, 835, 145, 45);

        //---- button3 ----
        button3.setText("\u6587\u4ef6");
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fileMouseClicked();
            }
        });
        add(button3);
        button3.setBounds(1285, 905, 110, 50);

        //---- button4 ----
        button4.setText("\u901a\u8bdd");
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tonghuaMouseClicked(e);
            }
        });
        add(button4);
        button4.setBounds(680, 835, 95, 45);

        //======== scrollPane1 ========
        {

            //---- list1 ----
            list1.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 20));
            list1.addListSelectionListener(e -> list1ValueChanged(e));
            scrollPane1.setViewportView(list1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(80, 75, 160, 720);

        //---- label1 ----
        label1.setText("\u5728\u7ebf\u4eba\u5458");
        label1.setFont(new Font("\u5e7c\u5706", Font.BOLD, 23));
        add(label1);
        label1.setBounds(95, 30, 115, 32);

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
    private JTextField textField2;
    private JButton button3;
    private JButton button4;
    private JScrollPane scrollPane1;
    private JList list1;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

}
