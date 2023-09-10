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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 * @author 22431
 */
public class chatView extends JPanel{
    private SocketHelper message=new SocketHelper();;

    private User uu;

    private tonghua_wait deng;

    private tonghua_accept accept;

    private tonghua_clinet tong_client;

    private Vector<mytextpane> panelist=new Vector<mytextpane>();

    private JTextPane curpane;


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
        initComponents();

        scrollPane3.setViewportView(null);

        UIStyler.createHeader(this);
        UIStyler.setTopButton(button2);

        message.getConnection(message.ip,message.port);
        try
        {
            //告诉服务器是消息通道
            message.getOs().writeInt(0);
            message.getOs().flush();

            //初始化用户名字
            message.getOs().writeInt(-100);
            message.getOs().flush();
            message.getOs().writeObject(uu.getId()+"-"+uu.getName());
            message.getOs().flush();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        new Thread(new Message_L()).start();

        refreshlist();
    }

    public void close()
    {
        try {
            message.getOs().writeInt(-300);
            message.getOs().flush();
            panelist.clear();

        } catch (IOException e) {
            throw new RuntimeException(e);
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
            if (selectedItem != null) {
                textField2.setText(selectedItem);
                mytextpane temp=search(selectedItem);
                if(temp==null){
                    mytextpane newtemp=new mytextpane(selectedItem,mytextpane.copyTextPane(textPane1,selectedItem));
                    panelist.add(newtemp);
                    curpane=newtemp.getTextPane();
                    scrollPane3.setViewportView(null);
                    scrollPane3.setViewportView(curpane);
                }
                else
                {
                    curpane=temp.getTextPane();
                    scrollPane3.setViewportView(null);
                    scrollPane3.setViewportView(curpane);
                }
            }
        }
    }

    private void messageClicked() {
        // TODO add your code here
        refreshlist();
        if(textField2.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"请选择聊天对象！！");
            return;
        }
        if(textField1.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"请不要发送空消息！！");
            return;
        }

        if(textField2.getText().equals("All user")){
            String mes="(群发文本) "+uu.getId()+" "+uu.getName();
            mes=mes+"  ["+Timehelp.getCurrentTime()+"]\n";
            mes=mes+textField1.getText();

            try {
                message.getOs().writeInt(001);
                message.getOs().flush();
                message.getOs().writeObject(mes);
                message.getOs().flush();

                StyledDocument doc = curpane.getStyledDocument();


                // 创建一个 SimpleAttributeSet 对象并设置对齐方式为右对齐
                SimpleAttributeSet rightAlign = new SimpleAttributeSet();
                StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);

                try {
                    // 在插入文本之前设置插入点的属性
                    doc.setParagraphAttributes(doc.getLength(), 1, rightAlign, false);

                    // 插入两个换行符和要插入的文本
                    doc.insertString(doc.getLength(), "\n\n", null);
                    doc.insertString(doc.getLength(), mes, null);
                    doc.insertString(doc.getLength(), "\n", null);


                } catch (BadLocationException e) {
                    e.printStackTrace();
                }

                refreshlist();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }

        else {
            String mes="(私聊文本) "+uu.getId()+" "+uu.getName();
            mes=mes+"  ["+Timehelp.getCurrentTime()+"]\n";
            mes=mes+textField1.getText();

            try {
                message.getOs().writeInt(002);
                message.getOs().flush();
                message.getOs().writeObject(textField2.getText());
                message.getOs().flush();
                message.getOs().writeObject(mes);
                message.getOs().flush();

                StyledDocument doc = curpane.getStyledDocument();
                // 创建一个 SimpleAttributeSet 对象并设置对齐方式为右对齐
                SimpleAttributeSet rightAlign = new SimpleAttributeSet();
                StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);

                try {
                    // 在插入文本之前设置插入点的属性
                    doc.setParagraphAttributes(doc.getLength(), 1, rightAlign, false);

                    // 插入两个换行符和要插入的文本
                    doc.insertString(doc.getLength(), "\n\n", null);
                    doc.insertString(doc.getLength(), mes, null);
                    doc.insertString(doc.getLength(), "\n", null);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
                refreshlist();


            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        textField1.setText("");
    }

    private void imageClicked()  {
        // TODO add your code here
        refreshlist();
        if(textField2.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"请选择聊天对象！！");
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
                    mes=mes+"  ["+Timehelp.getCurrentTime()+"]\n";

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

                    BufferedImage image= ImageIO.read(new File(imagePath));
                    ImageIcon icon = ImageHelper.resizeImage(image, 500, 300);

                    StyledDocument doc = curpane.getStyledDocument();

                    SimpleAttributeSet rightAlign = new SimpleAttributeSet();
                    StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);

                    curpane.setCaretPosition(doc.getLength()); // 将插入位置移动到最后
                    doc.insertString(doc.getLength(), "\n\n", null);

                    doc.setParagraphAttributes(doc.getLength(), 1, rightAlign, false);

                    doc.insertString(doc.getLength(), mes, null);
                    doc.insertString(doc.getLength(), " ", null);

                    curpane.setCaretPosition(doc.getLength()); // 将插入位置移动到最后
                    curpane.insertIcon(icon);
                    curpane.setCaretPosition(doc.getLength()); // 将插入位置移动到最后
                    doc.insertString(doc.getLength(), "\n", null);
                    refreshlist();
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
                    mes=mes+"  ["+Timehelp.getCurrentTime()+"]\n";

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

                    BufferedImage image= ImageIO.read(new File(imagePath));
                    ImageIcon icon = ImageHelper.resizeImage(image, 700, 600);

                    StyledDocument doc = curpane.getStyledDocument();

                    curpane.setCaretPosition(doc.getLength()); // 将插入位置移动到最后
                    doc.insertString(doc.getLength(), "\n", null);
                    doc.insertString(doc.getLength(), "\n", null);
                    doc.insertString(doc.getLength(), mes, null);
                    doc.insertString(doc.getLength(), " ", null);
                    curpane.setCaretPosition(doc.getLength()); // 将插入位置移动到最后
                    curpane.insertIcon(icon);
                    refreshlist();

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

    private void fileClicked() {
        // TODO add your code here
        refreshlist();
        if(textField2.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"请选择聊天对象！！");
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
                    mes=mes+"  ["+Timehelp.getCurrentTime()+"]\n"+selectedFileName;
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

                    StyledDocument doc = curpane.getStyledDocument();

                    // 创建一个 SimpleAttributeSet 对象并设置对齐方式为右对齐
                    SimpleAttributeSet rightAlign = new SimpleAttributeSet();
                    StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);

                    try {
                        // 在插入文本之前设置插入点的属性
                        doc.setParagraphAttributes(doc.getLength(), 1, rightAlign, false);

                        // 插入两个换行符和要插入的文本
                        doc.insertString(doc.getLength(), "\n\n", null);
                        doc.insertString(doc.getLength(), mes, null);
                        doc.insertString(doc.getLength(), "\n", null);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                    refreshlist();

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
                    mes=mes+"  ["+Timehelp.getCurrentTime()+"]\n"+selectedFileName;
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

                    StyledDocument doc = curpane.getStyledDocument();

                    // 创建一个 SimpleAttributeSet 对象并设置对齐方式为右对齐
                    SimpleAttributeSet rightAlign = new SimpleAttributeSet();
                    StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);

                    try {
                        // 在插入文本之前设置插入点的属性
                        doc.setParagraphAttributes(doc.getLength(), 1, rightAlign, false);

                        // 插入两个换行符和要插入的文本
                        doc.insertString(doc.getLength(), "\n\n", null);
                        doc.insertString(doc.getLength(), mes, null);
                        doc.insertString(doc.getLength(), "\n", null);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                    refreshlist();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } else if (result == JFileChooser.CANCEL_OPTION) {
                // 用户取消了选择
                System.out.println("选择被取消。");
            }
        }
    }

    private void phoneClicked() {
        // TODO add your code here
        refreshlist();
        if(textField2.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"通话对象不能为空");
            return;
        }
        if(textField2.getText().equals("All user"))
        {
            JOptionPane.showMessageDialog(this,"请选择指定用户进行通话!");
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

    private void gptClicked() {
        // TODO add your code here
        if(textField1.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this,"校园助手不接受输入空的内容！");
            return;
        }
        mytextpane temp=search("校园助手");
        if(temp==null){
            mytextpane newtemp=new mytextpane("校园助手",mytextpane.copyTextPane(textPane1,"校园助手"));
            panelist.add(newtemp);
            curpane=newtemp.getTextPane();
            scrollPane3.setViewportView(null);
            scrollPane3.setViewportView(curpane);
        }
        else
        {
            curpane=temp.getTextPane();
            scrollPane3.setViewportView(null);
            scrollPane3.setViewportView(curpane);
        }
        String mes="(自动问答) "+uu.getId()+" "+uu.getName();
        mes=mes+"  ["+Timehelp.getCurrentTime()+"]\n";
        String ques=textField1.getText();
        mes=mes+ques;

        try {
            message.getOs().writeInt(11);
            message.getOs().flush();
            message.getOs().writeObject(ques);
            message.getOs().flush();

            StyledDocument doc = curpane.getStyledDocument();
            // 创建一个 SimpleAttributeSet 对象并设置对齐方式为右对齐
            SimpleAttributeSet rightAlign = new SimpleAttributeSet();
            StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);

            try {
                // 在插入文本之前设置插入点的属性
                doc.setParagraphAttributes(doc.getLength(), 1, rightAlign, false);

                // 插入两个换行符和要插入的文本
                doc.insertString(doc.getLength(), "\n\n", null);
                doc.insertString(doc.getLength(), mes, null);
                doc.insertString(doc.getLength(), "\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            refreshlist();


        }catch (Exception exception){
            exception.printStackTrace();
        }


    }

    private mytextpane search(String uid)
    {
        for(mytextpane target:panelist)
            if (target.getUid().equals(uid))
                return target;
        return null;
    }


    private void button2MouseClicked() {
        // TODO add your code here
        refreshlist();
    }

    private void label8MouseClicked() {
        // TODO add your code here
        refreshlist();
    }

    public class Message_L implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    int cmd=message.getIs().readInt();
                    if (cmd == 0011 ) {
                        String sendid=(String) message.getIs().readObject();
                        String mes = (String) message.getIs().readObject();
                        System.out.println(mes);

                        mytextpane temp=search(sendid);
                        JTextPane acceptpane=null;
                        if(temp==null){
                            mytextpane newtemp=new mytextpane(sendid,mytextpane.copyTextPane(textPane1,sendid));
                            panelist.add(newtemp);
                            acceptpane=newtemp.getTextPane();
                        }
                        else
                            acceptpane=temp.getTextPane();


                        StyledDocument doc = acceptpane.getStyledDocument();
                        // 创建一个 SimpleAttributeSet 对象并设置对齐方式为右对齐
                        SimpleAttributeSet leftAlign = new SimpleAttributeSet();
                        StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);

                        try {
                            // 在插入文本之前设置插入点的属性
                            doc.setParagraphAttributes(doc.getLength(), 1, leftAlign, false);

                            // 插入两个换行符和要插入的文本
                            doc.insertString(doc.getLength(), "\n\n", null);
                            doc.insertString(doc.getLength(), mes, null);
                            doc.insertString(doc.getLength(), "\n", null);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                        refreshlist();

                    }
                    else if(cmd==0031) {
                        String sendid=(String) message.getIs().readObject();
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


                        ImageIcon icon = ImageHelper.resizeImage(image, 500, 300);

                        mytextpane temp=search(sendid);
                        JTextPane acceptpane=null;
                        if(temp==null){
                            mytextpane newtemp=new mytextpane(sendid,mytextpane.copyTextPane(textPane1,sendid));
                            panelist.add(newtemp);
                            acceptpane=newtemp.getTextPane();
                        }
                        else
                            acceptpane=temp.getTextPane();

                        StyledDocument doc = acceptpane.getStyledDocument();

                        SimpleAttributeSet leftAlign = new SimpleAttributeSet();
                        StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);

                        acceptpane.setCaretPosition(doc.getLength()); // 将插入位置移动到最后
                        doc.insertString(doc.getLength(), "\n", null);
                        doc.insertString(doc.getLength(), "\n", null);


                        doc.setParagraphAttributes(doc.getLength(), 1, leftAlign, false);

                        doc.insertString(doc.getLength(), mes, null);
                        doc.insertString(doc.getLength(), " ", null);

                        acceptpane.setCaretPosition(doc.getLength()); // 将插入位置移动到最后
                        acceptpane.insertIcon(icon);
                        acceptpane.setCaretPosition(doc.getLength()); // 将插入位置移动到最后
                        doc.insertString(doc.getLength(), "\n", null);

                        refreshlist();
                        refreshlist();

                    }
                    else if(cmd==0051){
                        String sendid=(String) message.getIs().readObject();
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

                        mytextpane temp=search(sendid);
                        JTextPane acceptpane=null;
                        if(temp==null){
                            mytextpane newtemp=new mytextpane(sendid,mytextpane.copyTextPane(textPane1,sendid));
                            panelist.add(newtemp);
                            acceptpane=newtemp.getTextPane();
                        }
                        else
                            acceptpane=temp.getTextPane();

                        StyledDocument doc = acceptpane.getStyledDocument();

                        // 创建一个 SimpleAttributeSet 对象并设置对齐方式为右对齐
                        SimpleAttributeSet leftAlign = new SimpleAttributeSet();
                        StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);

                        try {
                            // 在插入文本之前设置插入点的属性
                            doc.setParagraphAttributes(doc.getLength(), 1, leftAlign, false);

                            // 插入两个换行符和要插入的文本
                            doc.insertString(doc.getLength(), "\n\n", null);
                            doc.insertString(doc.getLength(), mes, null);
                            doc.insertString(doc.getLength(), "\n", null);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
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
                        tong_client=new tonghua_clinet(ip,text,message);
                    }
                    else if(cmd==101)
                    {
                        JOptionPane.showMessageDialog(null,"对方已挂断！！！");
                    }
                    else if(cmd==111)
                    {
                        String m="(自动问答) "+"校园助手";
                        m=m+"  ["+Timehelp.getCurrentTime()+"]\n";
                        String mes = m+(String) message.getIs().readObject();
                        System.out.println(mes);

                        mytextpane temp=search("校园助手");
                        JTextPane acceptpane=null;
                        if(temp==null){
                            mytextpane newtemp=new mytextpane("校园助手",mytextpane.copyTextPane(textPane1,"校园助手"));
                            panelist.add(newtemp);
                            acceptpane=newtemp.getTextPane();
                        }
                        else
                            acceptpane=temp.getTextPane();


                        StyledDocument doc = acceptpane.getStyledDocument();
                        // 创建一个 SimpleAttributeSet 对象并设置对齐方式为右对齐
                        SimpleAttributeSet leftAlign = new SimpleAttributeSet();
                        StyleConstants.setAlignment(leftAlign, StyleConstants.ALIGN_LEFT);

                        try {
                            // 在插入文本之前设置插入点的属性
                            doc.setParagraphAttributes(doc.getLength(), 1, leftAlign, false);

                            // 插入两个换行符和要插入的文本
                            doc.insertString(doc.getLength(), "\n\n", null);
                            doc.insertString(doc.getLength(), mes, null);
                            doc.insertString(doc.getLength(), "\n", null);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                        refreshlist();
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
        textField2 = new JTextField();
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        panel1 = new JPanel();
        label3 = new JLabel();
        label4 = new JLabel();
        label6 = new JLabel();
        label5 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        button2 = new JButton();
        panel2 = new JPanel();
        label11 = new JLabel();
        label2 = new JLabel();
        label1 = new JLabel();

        //======== this ========
        setPreferredSize(new Dimension(1680, 1030));
        setLayout(null);

        //======== scrollPane3 ========
        {

            //---- textPane1 ----
            textPane1.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 13));
            textPane1.setEditable(false);
            textPane1.setOpaque(false);
            scrollPane3.setViewportView(textPane1);
        }
        add(scrollPane3);
        scrollPane3.setBounds(230, 215, 1055, 665);

        //---- textField1 ----
        textField1.setFont(new Font("\u5e7c\u5706", Font.BOLD, 18));
        add(textField1);
        textField1.setBounds(230, 905, 550, 70);

        //---- textField2 ----
        textField2.setEditable(false);
        textField2.setFont(new Font("\u5e7c\u5706", Font.BOLD, 20));
        textField2.setHorizontalAlignment(SwingConstants.CENTER);
        add(textField2);
        textField2.setBounds(1305, 215, 185, 55);

        //======== scrollPane1 ========
        {

            //---- list1 ----
            list1.setFont(new Font("\u5e7c\u5706", Font.PLAIN, 20));
            list1.setOpaque(false);
            list1.addListSelectionListener(e -> list1ValueChanged(e));
            scrollPane1.setViewportView(list1);
        }
        add(scrollPane1);
        scrollPane1.setBounds(1305, 280, 185, 600);

        //======== panel1 ========
        {
            panel1.setOpaque(false);

            //---- label3 ----
            label3.setIcon(new ImageIcon(getClass().getResource("/chatView/pic/\u56fe\u7247.png")));
            label3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    imageClicked();
                }
            });

            //---- label4 ----
            label4.setIcon(new ImageIcon(getClass().getResource("/chatView/pic/\u6d88\u606f-\u7f6e\u7070.png")));
            label4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    messageClicked();
                }
            });

            //---- label6 ----
            label6.setIcon(new ImageIcon(getClass().getResource("/chatView/pic/\u8ba2\u5355.png")));
            label6.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    fileClicked();
                }
            });

            //---- label5 ----
            label5.setIcon(new ImageIcon(getClass().getResource("/chatView/pic/\u7535\u8bdd.png")));
            label5.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    phoneClicked();
                }
            });

            //---- label7 ----
            label7.setIcon(new ImageIcon(getClass().getResource("/chatView/pic/icons8-chatgpt-96.png")));
            label7.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    gptClicked();
                }
            });

            //---- label8 ----
            label8.setIcon(new ImageIcon(getClass().getResource("/chatView/pic/\u5237\u65b0.png")));
            label8.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    label8MouseClicked();
                }
            });

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label4)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label3)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label6)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label5)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label7)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(label8, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(label4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(label7, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18))
            );
        }
        add(panel1);
        panel1.setBounds(800, 885, 450, 100);

        //---- button2 ----
        button2.setText("\u5237\u65b0");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked();
            }
        });
        add(button2);
        button2.setBounds(230, 150, 150, 50);

        //======== panel2 ========
        {
            panel2.setBackground(new Color(0x24321e));
            panel2.setLayout(null);

            //---- label11 ----
            label11.setText("\u5728\u7ebf\u8054\u7edc\u5e73\u53f0");
            label11.setFont(new Font("\u6977\u4f53", Font.BOLD, 60));
            label11.setForeground(Color.white);
            panel2.add(label11);
            label11.setBounds(1035, 15, 600, 135);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/chatView/pic/img.png")));
            panel2.add(label2);
            label2.setBounds(60, 10, 745, 130);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel2.getComponentCount(); i++) {
                    Rectangle bounds = panel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
        }
        add(panel2);
        panel2.setBounds(0, 0, 1685, 150);

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/chatView/pic/imageonline-co-brightnessadjusted (1).png")));
        add(label1);
        label1.setBounds(0, 200, 1685, 830);

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
    private JTextField textField2;
    private JScrollPane scrollPane1;
    private JList list1;
    private JPanel panel1;
    private JLabel label3;
    private JLabel label4;
    private JLabel label6;
    private JLabel label5;
    private JLabel label7;
    private JLabel label8;
    private JButton button2;
    private JPanel panel2;
    private JLabel label11;
    private JLabel label2;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

}
