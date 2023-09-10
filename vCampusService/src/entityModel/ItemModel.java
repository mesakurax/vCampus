package entityModel;

import entity.Item;
import sqlutil.DBHelper;

import java.sql.ResultSet;

public class ItemModel  {
    private String Query = "";
    private Item info = null;
    public ItemModel() {

    }

    public boolean Insert(Object obj) {
        this.info = (Item) obj;

        try {
            this.Query = "Insert into Item(itemId, itemName, itemPrd, itemPrc, itemStr, itemSales,itemImage) " +
                    "values('" + info.getItemId() + "','" + info.getItemName() + "','" + info.getItemPrd() + "'," + info.getItemPrc() + ","
                    + info.getItemStr() + "," + info.getItemSales() + ",'"+info.getItemImage()+ "')";
            System.out.println(this.Query);
            if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
        } catch(Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public boolean Modify(Object obj) {
        this.info = (Item) obj;
        try {
            this.Query = "update Item set itemName = '" + info.getItemName() + "',itemPrd = '" + info.getItemPrd() + "',itemPrc = "
                    + info.getItemPrc() + ",itemStr = " + info.getItemStr() + ",itemSales = " + info.getItemSales()+",itemImage = '"+info.getItemImage()+"'"
                    + " where itemId = '" + info.getItemId() + "'";
            System.out.println(this.Query);
            if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
        } catch(Exception err) {
            err.printStackTrace();
        }
        return false;
    }


    public boolean Delete(Object obj) {
        this.info = (Item) obj;
        try {
            this.Query = "delete from Item where itemId = '" + info.getItemId() + "'";
            System.out.println(this.Query);
            if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
        } catch(Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public Object Search(Object obj, int opt) {
        this.info = (Item) obj;
        if(1 == opt) this.Query = "select * from Item";
        if(2 == opt) this.Query = "select * from Item where itemId = '" + info.getItemId() + "'";
        if(3 == opt) this.Query = "select * from Item where itemName = '" + info.getItemName() + "'";
        try {
            System.out.println(this.Query);
            ResultSet rs =  DBHelper.executeQuery(this.Query);
            if(rs != null) {
                System.out.println("ItemRepository successfully return rs");
                return rs;
            } else {
                System.out.println("ItemRepository return rs = null");
            }
        } catch(Exception err) {
            err.printStackTrace();
        }
        return null;
    }


}
