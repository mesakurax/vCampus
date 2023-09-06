package module;

import entity.Book;
import entity.BookRecord;
import utils.SocketHelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

public class BookSystem {
    ObjectInputStream is;
    ObjectOutputStream os;

    public BookSystem(SocketHelper socketHelper){
        this.is=socketHelper.getIs();
        this.os=socketHelper.getOs();
    }

    /*public boolean user_addRecord(rechargeRecord info)
    {

        try {
            this.os.writeInt(701);
            this.os.flush();
            this.os.writeObject(info);
            this.os.flush();
            System.out.println("user_addRecord");

            try {
                if(this.is.readInt()==7011)
                    return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }*/
    public boolean admin_addBook(Book bk)
    {
        try
        {
            this.os.writeInt(501);
            this.os.flush();;
            this.os.writeObject(bk);
            this.os.flush();
            System.out.println("admin_addBook");
            try
            {
                if(this.is.readInt()==5011)
                    return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean admin_delete(Book bk)
    {
        try
        {
            this.os.writeInt(502);
            this.os.flush();;
            this.os.writeObject(bk);
            this.os.flush();
            System.out.println("admin_deleteBook");
            try
            {
                if(this.is.readInt()==5021)
                    return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean admin_modify(Book bk)
    {
        try
        {
            this.os.writeInt(503);
            this.os.flush();;
            this.os.writeObject(bk);
            this.os.flush();
            System.out.println("admin_modifyBook");
            try
            {
                if(this.is.readInt()==5031)
                    return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Book> searchbook(Book bk, int flag)
    {
        try
        {
            this.os.writeInt(504);
            this.os.flush();;
            this.os.writeObject(bk);
            this.os.flush();
            this.os.writeInt(flag);
            this.os.flush();
            System.out.println("searchBook");
            try
            {
                Object receivedObject = this.is.readObject();
                if (receivedObject != null ) {
                    List<Book> receivedList = (List<Book>) receivedObject;
                    return receivedList;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean userborrow(BookRecord record)
    {
        try
        {
            this.os.writeInt(511);
            this.os.flush();
            this.os.writeObject(record);
            this.os.flush();
            System.out.println("userborrowbook");
            try
            {
                if(this.is.readInt()==5111)
                    return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean userreturn(BookRecord record)
    {
        try
        {
            this.os.writeInt(512);
            this.os.flush();;
            this.os.writeObject(record);
            this.os.flush();
            System.out.println("userreturn");
            try
            {
                if(this.is.readInt()==5121)
                    return true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<BookRecord> searchrecord(BookRecord record, int flag)
    {
        try
        {
            this.os.writeInt(513);
            this.os.flush();;
            this.os.writeObject(record);
            this.os.flush();
            this.os.writeInt(flag);
            this.os.flush();
            System.out.println("searchRecord");
            try
            {
                Object receivedObject = this.is.readObject();
                if (receivedObject != null ) {
                    return (List<BookRecord>) receivedObject;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
