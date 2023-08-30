package entityModel;

import entity.*;
import sqlutil.*;
import java.sql.ResultSet;

public class rechargeRecordModel {
    private String Query = "";
    private rechargeRecord info = null;

    public rechargeRecordModel() {
    }

    public boolean insert(Object obj) {
        this.info = (rechargeRecord) obj;
        try {
            this.Query = "Insert into rechargeRecord(rId, uId, uName, amount, isDispose, status, time) " +
                    "values(" + info.getrId() + ",'" + info.getuId() + "','" + info.getuName() + "'," + info.getAmount()
                    + "," + info.getIsDispose() + ",'" + info.getStatus() + "','" + info.getTime() + "')";
            System.out.println(this.Query);
            if (1 == DBHelper.executeNonQuery(this.Query)) return true;
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public boolean delete(Object obj) {
        this.info = (rechargeRecord) obj;
        try {
            this.Query = "delete from rechargeRecord where rId = " + this.info.getrId();
            System.out.println(this.Query);
            if (1 == DBHelper.executeNonQuery(this.Query)) return true;
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public boolean modify(Object obj) {
        this.info = (rechargeRecord) obj;
        try {
            this.Query = "update rechargeRecord set isDispose = " + info.getIsDispose() + ",status = '" + this.info.getStatus() + "'"
                    + " where rId = " + info.getrId();
            System.out.println(this.Query);
            if (1 == DBHelper.executeNonQuery(this.Query)) return true;
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public Object query(Object obj, int flag) {
        this.info = (rechargeRecord) obj;
        if (flag == 1) this.Query = "select * from rechargeRecord where uId = '" + this.info.getuId() + "'";
        if (flag == 2) this.Query = "select * from rechargeRecord where isDispose = 0";
        if (flag == 3) this.Query = "select * from rechargeRecord where isDispose = 1";
        if (flag == 4)
            this.Query = "select * from rechargeRecord where isDispose = 0 and uId = '" + this.info.getuId() + "'";
        if (flag == 5)
            this.Query = "select * from rechargeRecord where isDispose = 1 and uId = '" + this.info.getuId() + "'";

        try {
            System.out.println(this.Query);
            ResultSet rs = DBHelper.executeQuery(this.Query);
            if (rs != null) {
                System.out.println("rechargeRecord successfully return rs");
                return rs;
            } else {
                System.out.println("rechargeRecord return rs = null");
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
    }

}

//test
/*    public static void main(String[] args)
    {
        rechargeRecord temp=new rechargeRecord(5,"555","1","50",550,18,"u»ò");
        rechargeRecordModel model=new rechargeRecordModel();
        ResultSet rs=(ResultSet) model.query(temp,5);
        try{
            while(rs.next())
            {
                rechargeRecord a=new rechargeRecord();
                a.setTime(rs.getString("time"));
                System.out.println(a.toString());
            }
        }
        catch (Exception err)
        {

        }
    }
}*/
