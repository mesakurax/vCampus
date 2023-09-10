/*
 * Created by JFormDesigner on Mon Sep 11 09:57:13 CST 2023
 */

package LibraryView;

import utils.UIStyler;
import entity.Paper;
import module.BookSystem;
import utils.SocketHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 86153
 */
public class Paperintro extends JPanel{
    SocketHelper socketHelper;
    Paper paper;
    List<Paper> papers;
    BookSystem model;
    public Paperintro(SocketHelper helper, Paper pp) {
        initComponents();
        paperintro.setVisible(true);
        this.socketHelper=helper;
        model=new BookSystem(socketHelper);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        textArea1.setOpaque(false);
        textArea1.setBorder(BorderFactory.createEmptyBorder());
        nnname.setHorizontalAlignment(JTextField.CENTER);
        author.setHorizontalAlignment(JTextField.CENTER);
        date.setHorizontalAlignment(JTextField.CENTER);
        journal.setHorizontalAlignment(JTextField.CENTER);
        setLayout(new BorderLayout()); // 设置布局管理器为 BorderLayou
        UIStyler.setBelowButton(button1);
        UIStyler.setBelowButton(button2);
        UIStyler.setBelowButton(button3);
        add(paperintro, BorderLayout.CENTER); // 将myrecord添加到CENTER位置
        model=new BookSystem(socketHelper);
        this.paper=pp;
        refreshtable(pp);
    }

    public void refreshtable(Paper pp) {
        papers=model.searchPaper(pp,6);
        paper=papers.get(0);
        nnname.setText(paper.getName());
        author.setText(paper.getAuthor());
        date.setText(paper.getPublishdate());
        journal.setText(paper.getJournal());
        textArea1.setText(paper.getAbstract());
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        try {
            papers=model.searchPaper(paper,6);
            paper=papers.get(0);
            System.out.println(paper.getName());
            boolean flag=model.download(paper);
            if(flag) {
                JOptionPane.showMessageDialog(null, "下载成功", "", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("成功");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }

    private void label2MouseClicked(MouseEvent e) {
        // TODO add your code here
        JTextArea textArea = new JTextArea("[1]"+this.paper.getAuthor()+'.'+this.paper.getName()+'.'+this.paper.getJournal()+'.'+this.paper.getPublishdate());
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 200)); // 设置尺寸

        JOptionPane.showMessageDialog(this, scrollPane, "引文", JOptionPane.PLAIN_MESSAGE);
    }

    private void button2MouseClicked(MouseEvent e)  {
        try {
            papers = model.searchPaper(paper, 6);
            System.out.println(papers.size());
            paper = papers.get(0);
            System.out.println(paper.getName());
            String path = model.read(paper);
            System.out.println(path);
            if (path != null) {
                File pdfFile = new File(path);

                try {
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        if (pdfFile.exists() && pdfFile.isFile()) {
                            desktop.open(pdfFile);
                        } else {
                            System.out.println("指定的PDF文件不存在或不可打开。");
                        }
                    } else {
                        System.out.println("Desktop类在此平台上不受支持。");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                // 文件使用完成后再进行删除操作
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

/*        File tempFile = new File(path);
        if (tempFile.delete()) {
            System.out.println("临时文件删除成功");
        } else {
            System.out.println("临时文件删除失败");
        }*/
    }
    public JButton getReturnButton()
    {
        return button3;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        paperintro = new JPanel();
        nnname = new JTextField();
        author = new JTextField();
        journal = new JTextField();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        label1 = new JLabel();
        label4 = new JLabel();
        date = new JTextField();
        button1 = new JButton();
        label2 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();
        label3 = new JLabel();

        //======== paperintro ========
        {
            paperintro.setLayout(null);

            //---- nnname ----
            nnname.setEditable(false);
            nnname.setOpaque(false);
            nnname.setForeground(Color.white);
            nnname.setFont(new Font("\u6977\u4f53", Font.BOLD, 25));
            paperintro.add(nnname);
            nnname.setBounds(580, 30, 510, 55);

            //---- author ----
            author.setEditable(false);
            author.setOpaque(false);
            author.setForeground(Color.white);
            author.setFont(new Font("\u6977\u4f53", Font.BOLD, 18));
            paperintro.add(author);
            author.setBounds(585, 125, 505, author.getPreferredSize().height);

            //---- journal ----
            journal.setEditable(false);
            journal.setOpaque(false);
            journal.setForeground(Color.white);
            journal.setFont(new Font("\u6977\u4f53", Font.BOLD, 18));
            paperintro.add(journal);
            journal.setBounds(585, 175, 500, journal.getPreferredSize().height);

            //======== scrollPane1 ========
            {

                //---- textArea1 ----
                textArea1.setEditable(false);
                textArea1.setOpaque(false);
                textArea1.setFont(new Font("\u6977\u4f53", Font.BOLD, 18));
                scrollPane1.setViewportView(textArea1);
            }
            paperintro.add(scrollPane1);
            scrollPane1.setBounds(495, 300, 690, 350);

            //---- label1 ----
            label1.setText("\u6458\u8981:");
            label1.setFont(new Font("\u6977\u4f53", Font.PLAIN, 20));
            label1.setForeground(Color.white);
            paperintro.add(label1);
            label1.setBounds(495, 245, 80, 40);

            //---- label4 ----
            label4.setText("\u9898\u540d");
            label4.setFont(new Font("\u6977\u4f53", Font.BOLD, 20));
            label4.setForeground(Color.white);
            paperintro.add(label4);
            label4.setBounds(480, 30, label4.getPreferredSize().width, 50);

            //---- date ----
            date.setEditable(false);
            date.setOpaque(false);
            date.setForeground(Color.white);
            date.setFont(new Font("\u6977\u4f53", Font.BOLD, 18));
            paperintro.add(date);
            date.setBounds(590, 215, 490, 30);

            //---- button1 ----
            button1.setText("\u4e0b\u8f7d");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            paperintro.add(button1);
            button1.setBounds(800, 700, 83, 35);

            //---- label2 ----
            label2.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/\u5f15\u7528 (8).png")));
            label2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    label2MouseClicked(e);
                }
            });
            paperintro.add(label2);
            label2.setBounds(1320, 20, 100, 85);

            //---- button2 ----
            button2.setText("\u5728\u7ebf\u9605\u8bfb");
            button2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button2MouseClicked(e);
                }
            });
            paperintro.add(button2);
            button2.setBounds(new Rectangle(new Point(600, 700), button2.getPreferredSize()));

            //---- button3 ----
            button3.setText("\u8fd4\u56de");
            paperintro.add(button3);
            button3.setBounds(new Rectangle(new Point(1000, 700), button3.getPreferredSize()));

            //---- label3 ----
            label3.setIcon(new ImageIcon(getClass().getResource("/LibraryView/pic/finalib.jpg")));
            paperintro.add(label3);
            label3.setBounds(new Rectangle(new Point(0, 0), label3.getPreferredSize()));

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < paperintro.getComponentCount(); i++) {
                    Rectangle bounds = paperintro.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = paperintro.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                paperintro.setMinimumSize(preferredSize);
                paperintro.setPreferredSize(preferredSize);
            }
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel paperintro;
    private JTextField nnname;
    private JTextField author;
    private JTextField journal;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JLabel label1;
    private JLabel label4;
    private JTextField date;
    private JButton button1;
    private JLabel label2;
    private JButton button2;
    private JButton button3;
    private JLabel label3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
