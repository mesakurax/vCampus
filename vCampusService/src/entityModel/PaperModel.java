package entityModel;

import entity.BookIllegal;
import entity.Paper;
import sqlutil.DBHelper;

import java.sql.ResultSet;

public class PaperModel implements Model{
    private String Query = "";
    private Paper info = null;
    public PaperModel(){

    }
    @Override
    public boolean Insert(Object obj) {
        this.info=(Paper) obj;
        try {
            this.Query = "INSERT INTO paper (ID, Name, Author, Journal, Abstract, Path, Publishdate, Count) " +
                    "VALUES (" + info.getId() + ",'" + info.getName() + "','" + info.getAuthor() + "','"
                    + info.getJournal() + "','"
                    + info.getAbstract() + "','"
                    + info.getPath() + "','" + info.getPublishdate() + "'," + info.getCount() + ")";
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
        this.info=(Paper) obj;
        try{
            this.Query = "UPDATE paper SET Name = '" + info.getName() + "', Author = '" + info.getAuthor() + "', Journal = '" +
                    info.getJournal() + "', Abstract = '" + info.getAbstract() + "', Path = '" + info.getPath() +
                    "', Publishdate = '" + info.getPublishdate() + "', Count = " + info.getCount() +
                    " WHERE ID = " + info.getId();

// Ö´ÐÐSQL²é
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
        this.info = (Paper) obj;
        try {
            this.Query = "delete from paper where ID = " + info.getId() + "";
            System.out.println(this.Query);
            if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
        } catch(Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    @Override
    public Object Search(Object obj, int opt) {
        this.info = (Paper) obj;
        if(1 == opt) this.Query = "select * from paper";
        if(2 == opt) this.Query = "select * from paper where Name = '" + info.getName() + "'";
        if(3 == opt) this.Query = "select * from paper where ID = " + info.getId() + "";
        if(4 == opt) this.Query = "select * from paper where Author = '" + info.getAuthor() + "'";
        if(5 == opt) this.Query=  "select * from paper where Journal ='"+info.getJournal()+"'";
        if (6 == opt) {
            this.Query = "SELECT * FROM paper WHERE Author = '" + info.getAuthor() + "' AND Name = '" + info.getName() + "'";
        }
        try {
            System.out.println(this.Query);
            ResultSet rs =  DBHelper.executeQuery(this.Query);
            if(rs!=null) {
                System.out.println("Paper successfully return rs ");
                return rs;
            } else {
                System.out.println("Paper return rs = null");
            }
        } catch(Exception err) {
            err.printStackTrace();
        }
        return false;
    }
}
