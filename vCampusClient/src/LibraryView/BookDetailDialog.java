package LibraryView;

import entity.Book;

import javax.swing.*;
import java.awt.*;

/**
 * @author <a href="mailto:zkq1026@gmail.com">keqin</a>
 * @description
 * @date 2023年09月09日 17:09
 */
public class BookDetailDialog extends JDialog {

    private Book book;
    public BookDetailDialog(all app,Book book) {
        //super(app, true);
        this.book = book;
        setTitle("图书详细信息");
        setSize(1000, 1000);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel btnPanel = new JPanel();
/*        btnPanel.add(new JButton("查询"));
        btnPanel.add(new JButton("重置"));
        btnPanel.add(new JButton("删除"));*/

        add(btnPanel, BorderLayout.NORTH);
        JPanel dtlPanel = new JPanel(new GridLayout(10,2,15,5));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BorderLayout());
        JTextField textField = new JTextField(book.getName());
        textField.setEditable(false);
        namePanel.add(new JLabel("书名:"),BorderLayout.WEST);
        namePanel.add(textField,BorderLayout.CENTER);

        JPanel authorPanel = new JPanel();
        authorPanel.setLayout(new BorderLayout());
        JTextField textauthor = new JTextField(book.getAuthor());
        textauthor.setEditable(false);
        authorPanel.add(new JLabel("作者:"),BorderLayout.WEST);
        authorPanel.add(textauthor,BorderLayout.CENTER);

        dtlPanel.add(namePanel);
        dtlPanel.add(authorPanel);

        add(dtlPanel, BorderLayout.CENTER);
    }
}
