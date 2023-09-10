package module;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import entity.Book;
import entity.BookRecord;
import entityModel.BookModel;
import entityModel.BookRecordModel;
public class BookSystem {
    private BookModel bookModel;
    private BookRecordModel bookRecordModel;

    public BookSystem() {
        bookModel = new BookModel();
        bookRecordModel = new BookRecordModel();
    }

    public boolean addbook(Book info) {
        return bookModel.Insert(info);
    }

    public boolean deletebook(Book info) throws SQLException//删除图书记录
    {
        BookRecord temp = new BookRecord(info.getName(), info.getISBN(), null, null, null, false, null, null,null,0);
        ResultSet rs = (ResultSet) bookRecordModel.Search(temp, 7);//判断该书有无借出记录

        try {
            if (rs == null || !rs.next())
            {
                System.out.println(123);
                return bookModel.Delete(info);
            }

   /*         while (rs.next())
            {
                bookRecordModel.Delete(new BookRecord(rs.getString("Name"), rs.getString("ISBN"),rs.getString("Address"),
                        rs.getBoolean("Status"),rs.getString("Deadline"),rs.getString("Borrowtime"),
                        rs.getString("UserID"),rs.getString("RecordID")));
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean modifybook(Book info) {
        return bookModel.Modify(info);
    }
    /*查找图书*/
    public List<Book> searchbook(Book info, int flag) {
        try {
            ResultSet rs = (ResultSet) bookModel.Search(info, flag);
            List<Book> books = new ArrayList<Book>();
            System.out.println("测试");
            if(rs!=null) {
                while (rs.next()) {
                    Book bk = new Book(rs.getString("Name"),
                            rs.getString("ISBN"), rs.getString("Author"),
                            rs.getString("Publisher"), rs.getString("Publishdate"), rs.getString("Address"), rs.getInt("Count"),
                            rs.getString("Image"));
                    System.out.println("success");
                    books.add(bk);
                }
                System.out.println(books.size());
                return books;
            }
        } catch (SQLException e) {
            System.out.println("Database exception");
            e.printStackTrace();
        }
        return null;
    }
    public boolean borrowbook(BookRecord info)//借书
    {
        Book temp=new Book(info.getName(),info.getISBN(),null,null,null,null,0);
        ResultSet rs=(ResultSet) bookModel.Search(temp,4);
       try{
           if(rs.next()){
               temp.setAuthor(rs.getString("Author"));
               temp.setPublisher(rs.getString("Publisher"));
               temp.setPublishdate(rs.getString("Publishdate"));
               temp.setCount(rs.getInt("Count")-1);
               temp.setImage(rs.getString("Image"));
           }
           if (temp.getCount()>=0)
               return bookModel.Modify(temp)&&bookRecordModel.Insert(info);
       } catch (SQLException e) {
          e.printStackTrace();
       }

       return false;
    }
    public boolean returnbook(BookRecord info) {
        Book temp = new Book(info.getName(), info.getISBN(), null, null, null, null,info.getRecordID());
        ResultSet rs = (ResultSet) bookModel.Search(temp, 4);
        try {
            if (rs.next()) {
                temp.setAuthor(rs.getString("Author"));
                temp.setPublisher(rs.getString("Publisher"));
                temp.setPublishdate(rs.getString("Publishdate"));
                temp.setCount(rs.getInt("Count") + 1);
                temp.setImage(rs.getString("Image"));
            } else {
                return false; // 图书不存在
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); // 抛出异常
        }

        return bookModel.Modify(temp) && bookRecordModel.Delete(info);
    }

    public List<BookRecord> searchstatus(BookRecord info,int falg)
    {
        try {
            ResultSet rs = (ResultSet) bookRecordModel.Search(info, falg);
            List<BookRecord> temp = new ArrayList<>();

            while (rs.next()) {
                BookRecord record = new BookRecord(rs.getString("Name"), rs.getString("ISBN"),rs.getString("Author"),
                        rs.getString("Publisher"),rs.getString("Address"),
                        rs.getBoolean("Status"),rs.getString("Deadline"),rs.getString("Borrowtime"),
                        rs.getString("UserID"),rs.getInt("RecordID"));
                temp.add(record);

            }
            return temp;
        } catch (SQLException e) {
            System.out.println("Database exception");
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws SQLException {
       BookRecord temp = new BookRecord("活着", "1234js", "余华", "人民出版社", "李文正图书馆", false, "2023-09-22","2023-09-01","",0);
        Book bk=new Book("活着","12345","余华","人民出版社","2013-6-1","李文正图书馆",0);
        BookRecordModel model = new BookRecordModel();
        BookSystem p = new BookSystem();
        p.borrowbook(temp);
/*        p.addbook(bk);*/
        /*bk.setPublishdate("2013-6-1");*/
        p.searchbook(bk,4);
       /* if (p.addbook(bk))
            System.out.println("yes");
        else
            System.out.println("null");*/
    }
}
