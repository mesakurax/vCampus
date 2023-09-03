package module;
import utils.*;
import entity.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class bankSystem {
    ObjectInputStream is;
    ObjectOutputStream os;

    public bankSystem(SocketHelper socketHelper){
        this.is=socketHelper.getIs();
        this.os=socketHelper.getOs();
    }

    public boolean user_addRecord(rechargeRecord info)
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
    }

    public rechargeRecord[] user_queryRecord(rechargeRecord info)
    {

        try {
            this.os.writeInt(702);
            this.os.flush();
            this.os.writeObject(info);
            this.os.flush();
            System.out.println("user_queryRecord");

            try {
                if(this.is.readInt()==7021)
                    return (rechargeRecord[])this.is.readObject();
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public rechargeRecord[] admin_requestRecord(rechargeRecord info)
    {

        try {
            this.os.writeInt(703);
            this.os.flush();
            this.os.writeObject(info);
            this.os.flush();
            System.out.println("admin_requestRecord");

            try {
                if(this.is.readInt()==7031)
                    return (rechargeRecord[])this.is.readObject();
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public rechargeRecord[] admin_slovedRecord(rechargeRecord info)
    {

        try {
            this.os.writeInt(704);
            this.os.flush();
            this.os.writeObject(info);
            this.os.flush();
            System.out.println("admin_slovedRecord");

            try {
                if(this.is.readInt()==7041)
                    return (rechargeRecord[])this.is.readObject();
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean admin_accept(rechargeRecord info)
    {

        try {
            this.os.writeInt(705);
            this.os.flush();
            this.os.writeObject(info);
            this.os.flush();
            System.out.println("admin_accept");

            try {
                if(this.is.readInt()==7051)
                    return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean admin_refuse(rechargeRecord info)
    {

        try {
            this.os.writeInt(706);
            this.os.flush();
            this.os.writeObject(info);
            this.os.flush();
            System.out.println("admin_refuse");

            try {
                if(this.is.readInt()==7061)
                    return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public rechargeRecord[] admin_queryrequest(rechargeRecord info)
    {

        try {
            this.os.writeInt(707);
            this.os.flush();
            this.os.writeObject(info);
            this.os.flush();
            System.out.println("admin_queryrequest");

            try {
                if(this.is.readInt()==7071)
                    return (rechargeRecord[])this.is.readObject();
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public rechargeRecord[] admin_querysolved(rechargeRecord info)
    {

        try {
            this.os.writeInt(708);
            this.os.flush();
            this.os.writeObject(info);
            this.os.flush();
            System.out.println("admin_querysolved");

            try {
                if(this.is.readInt()==7081)
                    return (rechargeRecord[])this.is.readObject();
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
