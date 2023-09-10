/*
 * Created by JFormDesigner on Sun Sep 10 23:00:55 CST 2023
 */

package LibraryView;

import entity.Book;
import entity.BookRecord;
import entity.Paper;
import module.BookSystem;
import utils.SocketHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

/**
 * @author 86153
 */
public class Paperinfo extends JDialog {
    BookSystem model;
    SocketHelper socketHelper;
    Book book;
    List<Book> books;
    BookRecord record;
    List<BookRecord> records;
    Paper paper;
    int count;
    public Paperinfo(admin_paper app, SocketHelper helper) {
        this.setSize(800, 800);
        initComponents();
        this.socketHelper=helper;
        final String[] p = new String[1];
        model=new BookSystem(socketHelper);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame frame = new Frame();
                FileDialog fileDialog = new FileDialog(frame, "Select PDF", FileDialog.LOAD);
                // 设置文件过滤器，只显示 PDF 文件
                fileDialog.setFilenameFilter((dir, name) -> {
                    String lowercaseName = name.toLowerCase();
                    return lowercaseName.endsWith(".pdf");
                });
                // 显示文件选择对话框
                fileDialog.setVisible(true);

                // 获取选择的文件路径
                String selectedFile = fileDialog.getFile();
                String filePath = fileDialog.getDirectory() + selectedFile;
                path.setText(filePath);

            }
        });
        button3.setEnabled(false);

    }
    public Paperinfo(admin_paper app, SocketHelper socketHelper,Paper ppaper) {
        this.setSize(800, 800);
        initComponents();
        final String[] p = new String[1];
        model=new BookSystem(socketHelper);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame frame = new Frame();
                FileDialog fileDialog = new FileDialog(frame, "Select PDF", FileDialog.LOAD);
                // 设置文件过滤器，只显示 PDF 文件
                fileDialog.setFilenameFilter((dir, name) -> {
                    String lowercaseName = name.toLowerCase();
                    return lowercaseName.endsWith(".pdf");
                });
                // 显示文件选择对话框
                fileDialog.setVisible(true);

                // 获取选择的文件路径
                String selectedFile = fileDialog.getFile();
                String filePath = fileDialog.getDirectory() + selectedFile;
                path.setText(filePath);

            }
        });
        count=ppaper.getCount();
        ID.setText("" +ppaper.getId());
        Name.setText(ppaper.getName());
        Author.setText(ppaper.getAuthor());
        Journal.setText(ppaper.getJournal());
        date.setText(ppaper.getPublishdate());
        abs.setText(ppaper.getAbstract());
        button1.setEnabled(false);
    }
    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        int ID;
        String name = Name.getText();
        String Author = this.Author.getText();
        String journal = Journal.getText();
        String Publishdate = date.getText();
        String Abstract = abs.getText();
        String Path =path.getText();
        if (name.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "题名不能为空！");
            return;
        }
        if (this.ID.getText().equals("")) {
            JOptionPane.showMessageDialog((Component) this, "文献号不能为空！");
            return;
        } else {
            ID = Integer.parseInt(this.ID.getText());
        }
        if (Author.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "作者不能为空！");
            return;
        }
        if (journal.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "来源不能为空！");
            return;
        }
        if (Publishdate.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "出版日期不能为空！");
            return;
        }
        if (Abstract.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "摘要不能为空!");
            return;
        }
        if (path.getText().isEmpty()) {
            JOptionPane.showMessageDialog((Component) this, "路径不能为空!");
            return;
        }
        System.out.println("addpeper");
        try {
            Paper paper = new Paper(ID, name, Author, Abstract, journal, Publishdate, Path,0);
            boolean flag = model.addpaper(paper, path.getText());

            if (flag) {
                JOptionPane.showMessageDialog((Component) this, "录入成功！");
            } else {
                JOptionPane.showMessageDialog((Component) this, "录入失败！");
            }
        }catch(HeadlessException ex){
                throw new RuntimeException(ex);
            }
            this.dispose();
        }


    private void button4MouseClicked(MouseEvent e) {
        // TODO add your code here
        this.dispose();
    }

    private void button3MouseClicked(MouseEvent e) {
        // TODO add your code here
        int ID;
        String name = Name.getText();
        String Author = this.Author.getText();
        String journal=Journal.getText();
        String Publishdate = date.getText();
        String Abstract=abs.getText();
        String Path=path.getText();
        if (name.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "题名不能为空！");
            return;
        }
        if (this.ID.getText().equals("")) {
            JOptionPane.showMessageDialog((Component) this, "文献号不能为空！");
            return;
        }
        else
        {
            ID=Integer.parseInt(this.ID.getText());
        }
        if (Author.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "作者不能为空！");
            return;
        }
        if (journal.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "来源不能为空！");
            return;
        }
        if (Publishdate.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "出版日期不能为空！");
            return;
        }
        if (Abstract.equals("")) {
            JOptionPane.showMessageDialog((Component) this, "摘要不能为空!");
            return;
        }
        if (Objects.equals(Path, "")) {
            JOptionPane.showMessageDialog((Component) this, "路径不能为空!");
            return;
        }
        System.out.println("modifypaper");
        try {
            Paper paper= new Paper(ID,name,Author,Abstract,journal,Publishdate,Path,count);
            boolean flag = model.modifypaper(paper,path.getText());
            if (flag) {
                JOptionPane.showMessageDialog((Component) this, "录入成功！");
            } else {
                JOptionPane.showMessageDialog((Component) this, "录入失败！");
            }


        } catch (HeadlessException ex) {
            throw new RuntimeException(ex);
        }
        this.dispose();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        ID = new JTextField();
        Name = new JTextField();
        Author = new JTextField();
        Journal = new JTextField();
        date = new JTextField();
        scrollPane1 = new JScrollPane();
        abs = new JTextArea();
        button1 = new JButton();
        label10 = new JLabel();
        button2 = new JButton();
        path = new JTextField();
        button3 = new JButton();
        button4 = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u6587\u732e\u53f7");
        contentPane.add(label1);
        label1.setBounds(95, 65, 90, 45);

        //---- label2 ----
        label2.setText("\u9898\u540d");
        contentPane.add(label2);
        label2.setBounds(95, 125, 90, 45);

        //---- label6 ----
        label6.setText("\u4f5c\u8005");
        contentPane.add(label6);
        label6.setBounds(95, 180, 90, 45);

        //---- label7 ----
        label7.setText("\u6765\u6e90");
        contentPane.add(label7);
        label7.setBounds(95, 235, 90, 45);

        //---- label8 ----
        label8.setText("\u53d1\u8868\u65f6\u95f4");
        contentPane.add(label8);
        label8.setBounds(95, 285, 90, 45);

        //---- label9 ----
        label9.setText("\u6458\u8981");
        contentPane.add(label9);
        label9.setBounds(95, 390, 90, 45);
        contentPane.add(ID);
        ID.setBounds(180, 75, 205, ID.getPreferredSize().height);
        contentPane.add(Name);
        Name.setBounds(180, 130, 205, 30);
        contentPane.add(Author);
        Author.setBounds(180, 190, 205, 30);
        contentPane.add(Journal);
        Journal.setBounds(180, 245, 205, 30);
        contentPane.add(date);
        date.setBounds(180, 295, 205, 30);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(abs);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(180, 420, 510, 180);

        //---- button1 ----
        button1.setText("\u6dfb\u52a0\u786e\u8ba4");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(575, 615), button1.getPreferredSize()));

        //---- label10 ----
        label10.setText("\u94fe\u63a5");
        contentPane.add(label10);
        label10.setBounds(95, 340, 90, 45);

        //---- button2 ----
        button2.setText("\u4ece\u6587\u4ef6\u4e2d\u9009\u53d6");
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(185, 350), button2.getPreferredSize()));
        contentPane.add(path);
        path.setBounds(320, 350, 180, path.getPreferredSize().height);

        //---- button3 ----
        button3.setText("\u4fee\u6539\u786e\u8ba4");
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button3MouseClicked(e);
            }
        });
        contentPane.add(button3);
        button3.setBounds(new Rectangle(new Point(675, 615), button3.getPreferredSize()));

        //---- button4 ----
        button4.setText("\u53d6\u6d88");
        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button4MouseClicked(e);
            }
        });
        contentPane.add(button4);
        button4.setBounds(new Rectangle(new Point(765, 615), button4.getPreferredSize()));

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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JTextField ID;
    private JTextField Name;
    private JTextField Author;
    private JTextField Journal;
    private JTextField date;
    private JScrollPane scrollPane1;
    private JTextArea abs;
    private JButton button1;
    private JLabel label10;
    private JButton button2;
    private JTextField path;
    private JButton button3;
    private JButton button4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
