package entity;

import java.io.Serializable;

public class BookIllegal implements Serializable {
    private static final long serialVersionUID = 111222;
    private String name;//书名
    private String ISBN;//图书ISBN
    private String author;//作者
    private String publisher;//出版商
    private String publishdate;//发行日期

    private String address;//馆藏地
    private String deadline;//应还时间
    private String borrowtime;//借出时间
    private String today;//还书日期
    private String UserID;
    private double penalty;//罚款
    private int recordid;
    public BookIllegal(int recordid_,String UserID_,String isbn,String bookname_,String author_,String borrowtime_,String deadline_,String today_, double penalty_,boolean status_)
    {
        recordid=recordid_;
        UserID=UserID_;
        ISBN=isbn;
        name=bookname_;
        author=author_;
        borrowtime=borrowtime_;
        deadline=deadline_;
        today=today_;
        penalty=penalty_;
        status=status_;
    }

    private boolean status;//是否处理违章(true:已处理）

    public void setToday(String today) {
        this.today = today;
    }

    public String getToday() {
        return today;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public void setRecordid(int recordid) {
        this.recordid = recordid;
    }

    public int getRecordid() {
        return recordid;
    }

    public double getPenalty() {
        return penalty;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAddress() {
        return address;
    }

    public boolean isStatus() {
        return status;
    }

    public String getAuthor() {
        return author;
    }

    public String getBorrowtime() {
        return borrowtime;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getName() {
        return name;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }


}
