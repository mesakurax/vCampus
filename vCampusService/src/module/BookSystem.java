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
import entity.Paper;
import entityModel.BookModel;
import entityModel.BookRecordModel;
import entity.BookIllegal;
import entityModel.BookillegalModel;
import entityModel.PaperModel;

public class BookSystem {
    private BookModel bookModel;
    private BookRecordModel bookRecordModel;
    private BookillegalModel bookillegalModel;
    private PaperModel paperModel;

    public BookSystem() {
        bookModel = new BookModel();
        bookRecordModel = new BookRecordModel();
        bookillegalModel=new BookillegalModel();
        paperModel = new PaperModel();
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
                            rs.getString("Image"),rs.getString("Intro"),rs.getString("Category"));
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
        Book temp=new Book(info.getName(),info.getISBN(),null,null,null,null,0,null,null);
        ResultSet rs=(ResultSet) bookModel.Search(temp,4);
       try{
           if(rs.next()){
               temp.setAuthor(rs.getString("Author"));
               temp.setPublisher(rs.getString("Publisher"));
               temp.setPublishdate(rs.getString("Publishdate"));
               temp.setAddress(rs.getString("Address"));
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
        Book temp = new Book(info.getName(), info.getISBN(), null, null, null, null,0,null,null);
        ResultSet rs = (ResultSet) bookModel.Search(temp, 4);
        try {
            if (rs.next()) {
                temp.setAuthor(rs.getString("Author"));
                temp.setPublisher(rs.getString("Publisher"));
                temp.setPublishdate(rs.getString("Publishdate"));
                temp.setAddress(rs.getString("Address"));
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

    public boolean addpenality(BookIllegal info)
    {
        return bookillegalModel.Insert(info);
    }
    public boolean modifyilleagl(BookIllegal info)
    {
        return bookillegalModel.Modify(info);
    }
    public boolean deleteillegal(BookIllegal info)
    {
        return bookillegalModel.Delete(info);
    }
    public List<BookIllegal> searchilleagl(BookIllegal info,int flag)
    {
        try {
            ResultSet rs = (ResultSet) bookillegalModel.Search(info, flag);
            List<BookIllegal> temp = new ArrayList<>();

            while (rs.next()) {
                BookIllegal record = new BookIllegal(rs.getInt("RecordID"),rs.getString("UserID"),rs.getString("ISBN"),rs.getString("bookName"),
                        rs.getString("Author"),rs.getString("borrowtime"),rs.getString("deadline"),rs.getString("Today"),rs.getDouble("penality"),rs.getBoolean("Status"));
                temp.add(record);

            }
            return temp;
        } catch (SQLException e) {
            System.out.println("Database exception");
            e.printStackTrace();
        }
        return null;
    }
    public boolean addpaper(Paper paper)
    {
        return paperModel.Insert(paper);
    }
    public boolean deletepaper(Paper paper)
    {
        return paperModel.Delete(paper);
    }
    public boolean modifypaper(Paper paper)
    {
        return paperModel.Modify(paper);
    }
    public List<Paper> searchpaper(Paper info,int flag)
    {
        try {
            ResultSet rs = (ResultSet) paperModel.Search(info, flag);
            List<Paper> temp = new ArrayList<>();

            while (rs.next()) {
                Paper paper = new Paper(rs.getInt("ID"),rs.getString("Name"),
                        rs.getString("Author"),rs.getString("Abstract"),
                        rs.getString("Journal"),rs.getString("Publishdate"),
                        rs.getString("Path"),rs.getInt("Count"));
                temp.add(paper);
            }
            System.out.println(temp.size());
            return temp;
        } catch (SQLException e) {
            System.out.println("Database exception");
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws SQLException {
       BookRecord temp = new BookRecord("活着", "12345", "余华", "人民出版社", "李文正图书馆", false, "2023-09-22","2023-09-01","",0);
       Book ttmp=new Book(null,"12345",null,null,null,null,0,null,null);
        BookRecordModel model = new BookRecordModel();
        BookSystem p = new BookSystem();
        p.deletebook(ttmp);
        /*p.searchbook(ttmp,4);*/
        //p.borrowbook(temp);
       /* BookIllegal bookIllegal=new BookIllegal("jzx","1234js","活着","余华","2023-08-01","2023-09-01",5,false);
        p.modifyilleagl(bookIllegal);*/
/*        p.addbook(bk);*/
        /*bk.setPublishdate("2013-6-1");*/
       // p.searchbook(bk,4);
       /* if (p.addbook(bk))
            System.out.println("yes");
        else
            System.out.println("null");*/
    }
}
