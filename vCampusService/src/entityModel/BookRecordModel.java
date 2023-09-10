package entityModel;

import com.sun.prism.impl.Disposer;
import entity.BookRecord;
import entityModel.Model;
import sqlutil.DBHelper;
import java.sql.SQLException;
import java.sql.ResultSet;
public class BookRecordModel implements Model{
    private String Query = "";
    private BookRecord info = null;
    public BookRecordModel(){

    }

    @Override
    public boolean Insert(Object obj) {
        this.info=(BookRecord) obj;
        try {
 /*           int tempstatus=0;
            if(info.getStatus())
                tempstatus=1;*/
            this.Query = "INSERT INTO tblBookRecord (RecordID, ISBN, Name, Author, Publisher, Address, Status, UserID, Borrowtime, Deadline) " +
                    "VALUES ('" + info.getRecordID() + "', '" + info.getISBN() + "', '" + info.getName() + "', '" +
                    info.getAuthor() + "', '" + info.getPublisher() + "', '" + info.getAddress() + "', " + info.getStatus() + ", '" +
                    info.getUserID() + "', '" + info.getBorrowtime() + "', '" + info.getDeadline() + "')";
            System.out.println(this.Query);
            if(1 ==  DBHelper.executeNonQuery(this.Query))
                return true;
        } catch(Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Modify(Object obj) {
        this.info = (BookRecord) obj;
        /*if (info.getStatus()) {
            ResultSet temp = (ResultSet) Search(obj, 6);
            if (temp == null)
                return false;*/
            try {
              /*  temp.next();
                String RecordID = temp.getString("RecordId");*/
                this.Query = "UPDATE tblBookRecord SET Status = " + info.getStatus() + ", UserID = '" + info.getUserID() +
                        "', BorrowTime = '" + info.getBorrowtime() + "', DeadLine = '" + info.getDeadline() +
                        "', Author = '" + info.getAuthor() + "', Publisher = '" + info.getPublisher() +
                        "' WHERE RecordId = " + info.getRecordID();
                System.out.println(this.Query);
                if (1 == DBHelper.executeNonQuery(this.Query)) return true;
            } catch (Exception err) {
                err.printStackTrace();
            }
        return false;
    }
    @Override
    public boolean Delete(Object obj)
    {
       this.info=(BookRecord) obj;

            try{
                this.Query = "delete from tblBookRecord where RecordID = '" + info.getRecordID() + "'";

                System.out.println(this.Query);
                if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
            }catch (Exception err) {
                err.printStackTrace();
            }
        return false;
    }

    @Override
    public Object Search(Object obj, int opt) {
        this.info = (BookRecord) obj;
        if(1 == opt) this.Query = "select * from tblBookRecord";
        if(2 == opt) this.Query = "select * from tblBookRecord where ISBN = '" + info.getISBN() + "'";
        if(3 == opt) this.Query = "select * from tblBookRecord where Name = '" + info.getName() + "'";
        if(4 == opt) this.Query = "select * from tblBookRecord where UserID = '" + info.getUserID() + "'";
        if(5 == opt) this.Query = "select * from tblBookRecord where UserID = '" + info.getUserID() + "' and ISBN = " + info.getISBN() +
                " and Name = '" + info.getName() + "'";
        if(6 == opt) this.Query = "select * from tblBookRecord where Status = 1 and ISBN = '" + info.getISBN() + "' and Name = '" + info.getName() + "'";
        if(7 == opt) this.Query = "select * from tblBookRecord where Status = 0 and ISBN = '" + info.getISBN() + "' and Name = '" + info.getName()+ "'";
        try {
            System.out.println(this.Query);
            ResultSet rs =  DBHelper.executeQuery(this.Query);
            if(rs == null) {
                System.out.println("BookBorrowRepository return rs = null");
            } else {
                System.out.println("BookBorrowRepository successfully return rs");
                return rs;
            }
        } catch(Exception err) {
            err.printStackTrace();
        }
        return null;
    }
    }


