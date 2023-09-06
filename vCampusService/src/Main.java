import entity.BookRecord;
import entity.Book;
import entityModel.BookRecordModel;
import entityModel.BookModel;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Main
{
    public static void main(String[] args) throws SQLException {
        Book temp=new Book("活着","123456789","yuhua","renminchubanshe","2023-1-1","李文正图书馆",1);
        BookModel ttemp=new BookModel();
        ttemp.Insert(temp);
        BookRecord record=new BookRecord("活着","123456789","yuhua","renminchubanshe","李文正图书馆",false,"2023-09-22","2023-09-01"," ",0);
       //ttemp.Insert(bk);,
        BookRecordModel model=new BookRecordModel();
        model.Insert(record);
       /* ttemp.Delete(temp);*/

      /*  BookRecord record=new BookRecord("活着","1234js","李文正图书馆一楼新书阅览室",false," "," "," ",0);*/
        //System.out.println(record.getStatus());
    /*    BookRecordModel recordModel=new BookRecordModel();
        recordModel.Insert(record);*/
        //recordModel.Insert(record);
        /*recordModel.Search(record,6);
        recordModel.Delete(record);*/
        /*ttemp.Delete(temp);
        ttemp.Delete(temp1);*/
  /*      ResultSet rs;
        rs=(ResultSet) ttemp.Search(temp,2);
        rs.next();
        Book res=new Book();
        res.setISBN(rs.getString("ISBN"));
        res.setName(rs.getString("Name"));
        res.setAuthor(rs.getString("Author"));
        res.setPublisher(rs.getString("Publisher"));
        res.setPublishdate(rs.getString("Publishdate"));
        res.setCount(rs.getInt("Count"));
        res.setImage(rs.getString("Image"));
        System.out.println(res.getAuthor());*/

    }
}
