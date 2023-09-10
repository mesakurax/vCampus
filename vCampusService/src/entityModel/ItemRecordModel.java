package entityModel;

import entity.ItemRecord;
import sqlutil.DBHelper;

import java.sql.ResultSet;

public class ItemRecordModel {

        private String Query = "";
        private ItemRecord info = null;
        public ItemRecordModel() {

        }

        public boolean Insert(Object obj) {
            this.info = (ItemRecord) obj;

            try {
                this.Query = "Insert into ItemRecord(recID, uId, itemId, itemName, itemPrc, itemNum, totalPrc, time) " +
                        "values('" + info.getRecID() + "','" + info.getuId() + "','" + info.getItemId() +  "','" + info.getItemName() + "'," + info.getItemPrc() + ","
                        + info.getItemNum() + "," + info.getTotalPrc() + ",'"+info.getTime()+ "')";
                System.out.println(this.Query);
                if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
            } catch(Exception err) {
                err.printStackTrace();
            }
            return false;
        }

        public boolean Modify(Object obj) {  //不能修改
            return false;
        }


    public boolean Delete(Object obj) {
        this.info = (ItemRecord) obj;
        try {
            this.Query = "delete from ItemRecord  where uId = '" + this.info.getuId() + "'"
                    + " and ItemName = '" + this.info.getItemName() + "'";
            System.out.println(this.Query);
            if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
        } catch(Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public Object Search(Object obj, int opt) {
        this.info = (ItemRecord) obj;
        if(1 == opt) this.Query = "select * from ItemRecord where uId = '" + this.info.getuId() + "'";
        if(2 == opt) this.Query = "select * from ItemRecord where uId = '" + this.info.getuId() + "'"
                + " and ItemName = '" + this.info.getItemName() + "'";
        try {
            System.out.println(this.Query);
            ResultSet rs =  DBHelper.executeQuery(this.Query);
            if(rs != null) {
                System.out.println("ItemRecordRepository successfully return rs");
                return rs;
            } else {
                System.out.println("ItemRecordRepository return rs = null");
            }
        } catch(Exception err) {
            err.printStackTrace();
        }
        return null;
    }

}
