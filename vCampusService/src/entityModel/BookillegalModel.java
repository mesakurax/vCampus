package entityModel;

import entity.Book;
import entity.BookIllegal;
import sqlutil.DBHelper;

import java.sql.ResultSet;

public class BookillegalModel implements Model{
    private String Query = "";
    private BookIllegal info = null;
    public BookillegalModel(){

    }

    @Override
    public boolean Insert(Object obj) {
        this.info=(BookIllegal) obj;
        try {
            this.Query = "INSERT INTO illegalrecord (RecordID, UserID, ISBN, bookName, Author, borrowtime, deadline, Today, penality, Status) " +
                    "VALUES ('" + info.getRecordid() + "','" + info.getUserID() + "','" + info.getISBN() + "','" + info.getName() + "','" +
                    info.getAuthor() + "','" + info.getBorrowtime() + "','" + info.getDeadline() + "','" + info.getToday() + "','" +
                    String.valueOf(info.getPenalty()) + "'," + (info.isStatus() ? 1 : 0) + ")";
            System.out.println(this.Query);
            if (1 == DBHelper.executeNonQuery(this.Query)) {
                return true;
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean Modify(Object obj) {
        this.info=(BookIllegal) obj;
        try{
            this.Query = "UPDATE illegalrecord SET bookName = '" + info.getName() + "', Author = '" + info.getAuthor() + "', borrowtime = '" +
                    info.getBorrowtime() + "', ISBN = '" + info.getISBN() + "', deadline = '" + info.getDeadline() +
                    "', Today='"+info.getToday()+"',penality = '" + String.valueOf(info.getPenalty()) + "', Status = 1, UserID = '" + info.getUserID() + "' WHERE RecordID = " + info.getRecordid();
            System.out.println(this.Query);
            if(1 ==  DBHelper.executeNonQuery(this.Query))
                return true;
        }
        catch (Exception err)
        {
            err.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean Delete(Object obj) {
        this.info = (BookIllegal) obj;
        try {
            this.Query = "delete from illegalrecord where RecordID = '" + info.getRecordid() + "'";
            System.out.println(this.Query);
            if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
        } catch(Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    @Override
    public Object Search(Object obj, int opt) {
        this.info = (BookIllegal) obj;
        if(1 == opt) this.Query = "select * from illegalrecord";
        if(2 == opt) this.Query = "select * from illegalrecord where Name = '" + info.getName() + "'";
        if(3 == opt) this.Query = "select * from illegalrecord where RecordID = '" + info.getRecordid() + "'";
        if(4 == opt) this.Query = "select * from illegalrecord where Status = '" + info.isStatus() + "'";
        if(5 == opt) this.Query=  "select * from illegalrecord where UserID ='"+info.getUserID()+"'";
        if(6 == opt) this.Query=  "select * from illegalrecord where Status =0 and UserID ='"+info.getUserID()+"'";
        if(7 == opt) this.Query=  "select * from illegalrecord where ISBN ='"+info.getISBN()+"'";
        try {
            System.out.println(this.Query);
            ResultSet rs =  DBHelper.executeQuery(this.Query);
            if(rs!=null) {
                System.out.println("Bookillegalrecord successfully return rs ");
                return rs;
            } else {
                System.out.println("Bookillegalrecord return rs = null");
            }
        } catch(Exception err) {
            err.printStackTrace();
        }
        return null;
    }
}
