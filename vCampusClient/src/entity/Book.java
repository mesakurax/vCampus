package entity;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 111;

    private String name;//书名
    private String ISBN;//图书ISBN
    private String author;//作者
    private String publisher;//出版商
    private String publishdate;//发行日期
    private String intro;
    private int count;//可借阅数量

    private String image;//图片
    private String address;//馆藏地
    private String category;
    public  Book(){

    }
    public Book(String name_,String ISBN_,String author_,String publisher_,String publishdate_,String address_,int count_,String intro_,String category_)
    {
        this.name=name_;
        this.ISBN=ISBN_;
        this.author=author_;
        this.publisher=publisher_;
        this.publishdate=publishdate_;
        this.count=count_;
        this.address=address_;
        this.intro=intro_;
        this.category=category_;
    }

    public Book(String name_,String ISBN_,String author_,String publisher_,String publishdate_,String address_,int count_,String image_,String intro_,String category_)
    {
        this.name=name_;
        this.ISBN=ISBN_;
        this.author=author_;
        this.publisher=publisher_;
        this.publishdate=publishdate_;
        this.count=count_;
        this.image=image_;
        this.address=address_;
        this.intro=intro_;
        this.category=category_;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name_){
        this.name=name_;
    }

    public String getName(){return this.name;}
    public void setISBN(String ISBN_){
        this.ISBN=ISBN_;}
    public String getISBN(){return this.ISBN;}
    public void setAuthor(String author_){
        this.author=author_;
    }
    public String getAuthor(){return this.author;}
    public void setPublisher(String publisher_){
        this.publisher=publisher_;
    }
    public String getPublisher(){return this.publisher;}
    public void setPublishdate(String publishdate_){
        this.publishdate=publishdate_;
    }
    public String getPublishdate(){return this.publishdate;}
    public void setCount(int count_){
        this.count=count_;
    }
    public int getCount(){return this.count;}
    public void setImage(String image_){
        this.image=image_;
    }
    public String getImage(){return this.image;}

    public void setAddress(String address_) {
        this.address = address_;
    }

    public String getAddress() {
        return this.address;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro() {
        return intro;
    }
}
