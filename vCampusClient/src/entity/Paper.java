package entity;

import java.io.Serializable;

public class Paper implements Serializable {
    private static final long serialVersionUID = 666;
    int id;
    private String name;
    private String author;
    private String Abstract;
    private String journal;
    private String publishdate;
    private String path;
    private int Count;

    public Paper(){

    }
    public Paper(int id, String name, String author, String Abstract, String journal, String publishdate, String path, int count)
    {
        this.id=id;
        this.name=name;
        this.author=author;
        this.Abstract=Abstract;
        this.journal=journal;
        this.publishdate=publishdate;
        this.path=path;
        this.Count=count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getCount() {
        return Count;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPublishdate() {
        return publishdate;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public String getAbstract() {
        return Abstract;
    }

    public String getJournal() {
        return journal;
    }

    public String getPath() {
        return path;
    }
}
