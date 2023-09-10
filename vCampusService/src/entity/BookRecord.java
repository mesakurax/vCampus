package entity;

import java.io.Serializable;
public class BookRecord implements Serializable{
    private String name;//书名
    private int recordID;
    private String ISBN;//编号
    private String BookID;//图书ID
    private String address;//馆藏地址
    private boolean status;//状态(true:可借，false:借出)
    private String deadline;//还书时间
    private String borrowtime;//借出时间
    private String UserID;
    private String Author;
    private String Publisher;

    public BookRecord(String name_,String ISBN_,String Author_,String Publisher_,String address_,boolean status_,String deadline_,String borrowtime_,String userID_,int recordID_)
    {
        this.name=name_;
        this.address=address_;
        this.ISBN=ISBN_;
        this.status=status_;
        this.deadline=deadline_;
        this.borrowtime=borrowtime_;
        this.UserID=userID_;
        this.recordID=recordID_;
        this.Author=Author_;
        this.Publisher=Publisher_;
    }
    public void setName(String name_){
        this.name=name_;
    }
    public String getName(){return this.name;}

    public void setRecordID(int recordID_) {
        this.recordID = recordID_;
    }

    public int getRecordID() {
        return this.recordID;
    }

    public void setISBN(String ISBN_){
        this.ISBN=ISBN;
    }
    public String getISBN(){return this.ISBN;}
    public void setAddress(String address_){
        this.address=address_;
    }
    public String getAddress(){return this.address;}
    public void setStatus(boolean status_){
        this.status=status_;
    }
    public boolean getStatus(){return this.status;}
    public void setDeadline(String deadline_){
        this.deadline=deadline_;
    }
    public String getDeadline(){return this.deadline;}

    public void setBorrowtime(String borrowtime_) {
        this.borrowtime = borrowtime_;
    }

    public String getBorrowtime() {
        return this.borrowtime;
    }

    public void setUserID(String userID_) {
        this.UserID = userID_;
    }

    public String getUserID() {
        return this.UserID;
    }

    public void setAuthor(String author) {
        this.Author=author;
    }
    public String getAuthor()
    {
        return this.Author;
    }

    public void setPublisher(String publisher) {
        this.Publisher=publisher;
    }
    public String getPublisher(){return this.Publisher;}
}
