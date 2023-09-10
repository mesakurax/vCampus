package module;

import entity.*;
import utils.SocketHelper;

import java.io.*;
import java.util.Vector;


public class shopSystem {
    ObjectInputStream is;
    ObjectOutputStream os;

    public shopSystem(SocketHelper socketHelper){
        this.is=socketHelper.getIs();
        this.os=socketHelper.getOs();
    }

//管理员行为 增加商品
    public boolean addItem(Item info){
        try {
            this.os.writeInt(901);
            this.os.writeObject(info);
            this.os.flush();
            System.out.println("admin_addItem");

            try {
                if(this.is.readInt()==9011)
                    return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

   public boolean deleteItem(String id,String name){
       try {
           Item temp = new Item(id,name);
           this.os.writeInt(902);
           this.os.flush();
           this.os.writeObject(temp);
           this.os.flush();
           System.out.println("admin_deleteItem");

           try {
               if(this.is.readInt()==9021)
                   return true;
           }catch (Exception e){
               e.printStackTrace();
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }

    public boolean modifyItem(Item info){
        try {
            this.os.writeInt(903);
            this.os.flush();
            this.os.writeObject(info);
            this.os.flush();
            System.out.println("admin_modifyItem");

            try {
                if(this.is.readInt()==9031)
                    return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Vector<Item> searchItem(Item info){
        try{
            this.os.writeInt(904);
            this.os.flush();
            this.os.writeObject(info);
            this.os.flush();
            System.out.println("admin_searchItem");
            int check = is.readInt();
            try {
                if (check == 9041) {

                    System.out.println("ok");
                    return (Vector<Item>) is.readObject();

                }
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean addToCart(String uid,String itemId,String name,double prc,int str){
        try{

            os.writeInt(911);
            os.flush();

            Cart temp=new Cart(uid,itemId,name,prc,str);
            os.writeObject(temp);
            os.flush();

            int check=is.readInt();
            if(check==9111)return true;
            else if(check==9112)return false;
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteFromCart(String uid,String name,int cartId) {
        try{
            Cart temp=new Cart(cartId,uid,name);
            os.writeInt(912);
            os.flush();

            os.writeObject(temp);
            os.flush();

            int check=is.readInt();
            if(check==9121)return true;
            else if(check==9122)return false;
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }
//查找购物车某个商品
    public Cart searchCartItem(String uid,String name){
        try{
            Cart temp=new Cart(uid,name);
            os.writeInt(913);
            os.flush();
            os.writeObject(temp);
            os.flush();

            int check=is.readInt();
            if(check==9131){
                Vector<Cart>c = (Vector<Cart>)is.readObject();
                return c.elementAt(0);}
            else if(check==9132)
                return null;
        }catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

//查找用户购物车所有产品
    public Vector<Cart> queryAllCart(String uid){
        try{
            Cart temp=new Cart(uid,1);
            os.writeInt(913);
            os.flush();
            os.writeObject(temp);
            os.flush();


            int check=is.readInt();
            if(check==9131){
                return (Vector<Cart>)is.readObject();
            }
            else if(check==9132) {
                return null;
            }
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
//从购物车购买==增加一条消费记录

    public boolean buyFromCart(String itemid,String uid,String name,String time,double prc,int num,int cartId){
        try {
            ItemRecord temp = new ItemRecord(cartId,uid,itemid,name,prc,num,time);
            os.writeInt(921);
            os.flush();
            os.writeObject(temp);
            os.flush();

            int check=is.readInt();
            if(check==9211) return true;
            else if(check==9212)return false;
        }catch(IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //查找某条消费记录
    //查找该用户对某商品的消费记录
    public Vector<ItemRecord> searchItemRecord(String uid,String name){
        try{
            ItemRecord temp=new ItemRecord(uid,name);
            os.writeInt(922);
            os.flush();
            os.writeObject(temp);
            os.flush();

            int check=is.readInt();
            if(check==9221){
                Vector<ItemRecord> ir=(Vector<ItemRecord>)is.readObject();
                return ir;
            }
            else if(check==9222)
                return null;
        }catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Vector<ItemRecord> queryAllRecord(String uid) {
        try{
            ItemRecord temp=new ItemRecord(uid);
            os.writeInt(922);
            os.flush();
            os.writeObject(temp);
            os.flush();

            int check=is.readInt();
            if(check==9221) {

                return (Vector<ItemRecord>) is.readObject();
            }
            else if(check==9222)
                return null;
        }catch(IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public double query_balance(User uu)
    {
        try {
            os.writeInt(945);
            os.writeObject(uu);
            os.flush();

            return is.readDouble();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public void modifyyue(User uu,Double m)
    {
        try {
            os.writeInt(946);
            os.writeObject(uu);
            os.flush();
            os.writeDouble(m);
            os.flush();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
//刷新

