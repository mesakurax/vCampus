package entityModel;

import entity.Book;
import entityModel.Model;
import sqlutil.DBHelper;

import java.sql.ResultSet;
public class BookModel implements Model{
    private String Query = "";
    private Book info = null;
    public BookModel() {

    }
    /*
    原子操作增删改查
     */
    public boolean Insert(Object obj) {
        this.info = (Book) obj;
        try {
            this.Query = "INSERT INTO tblBook(ISBN, Name, Author, Publisher, PublishDate, Count, Address, Image) " +
                    "VALUES ('" + info.getISBN() + "','" + info.getName() + "','" + info.getAuthor() + "','" +
                    info.getPublisher() + "','" + info.getPublishdate() + "'," + info.getCount() + ",'" +
                    info.getAddress() + "','" + info.getImage() + "')";
            System.out.println(this.Query);
            if (1 == DBHelper.executeNonQuery(this.Query)) {
                return true;
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
    }
    public boolean Modify(Object obj) {
        this.info = (Book) obj;
        try {
            this.Query = "UPDATE tblBook SET Name = '" + info.getName() + "', Author = '" + info.getAuthor() + "', Publisher = '" +
                    info.getPublisher() + "', PublishDate = '" + info.getPublishdate() + "', Count = " + info.getCount() +
                    ", Address = '" + info.getAddress() + "', Image = '" + info.getImage() + "' WHERE ISBN = '" + info.getISBN() + "'";
            System.out.println(this.Query);
            if(1 ==  DBHelper.executeNonQuery(this.Query))
                return true;
        } catch(Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public boolean Delete(Object obj) {
        this.info = (Book) obj;
        try {
            this.Query = "delete from tblBook where ISBN = '" + info.getISBN() + "'";
            System.out.println(this.Query);
            if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
        } catch(Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public Object Search(Object obj, int opt) {
        this.info = (Book) obj;
        if(1 == opt) this.Query = "select * from tblBook";
        if(2 == opt) this.Query = "select * from tblBook where Name = '" + info.getName() + "'";
        if(3 == opt) this.Query = "select * from tblBook where Author = '" + info.getAuthor() + "'";
        if(4 == opt) this.Query = "select * from tblBook where ISBN = '" + info.getISBN() + "'";
        try {
            System.out.println(this.Query);
            ResultSet rs =  DBHelper.executeQuery(this.Query);
            if(rs!=null) {
                System.out.println("BookRepository successfully return rs ");
                return rs;
            } else {
                System.out.println("BookRepository return rs = null");
            }
        } catch(Exception err) {
            err.printStackTrace();
        }
        return null;
    }
}
