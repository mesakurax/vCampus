package entity;
import java.io.Serializable;

public class ItemRecord implements Serializable{
    private int recID;
    private String uId;
    private String itemId;
    private String itemName;
    private double itemPrc;
    private int itemNum;
    private double totalPrc;
    private String time;

    public ItemRecord() {
    }

    public ItemRecord(String uId, String itemId, String itemName, double itemPrc, int itemNum, String time) {
        this.uId = uId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrc = itemPrc;
        this.itemNum = itemNum;
        this.time = time;
        totalPrc = itemPrc*itemNum;
    }

    public ItemRecord(String uId) {
        this.uId = uId;
    }

    public ItemRecord(String uId, String itemName) {
        this.uId = uId;
        this.itemName = itemName;
    }

    public ItemRecord(int recID, String uId, String itemId, String itemName, double itemPrc, int itemNum, String time) {
        this.recID = recID;
        this.uId = uId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrc = itemPrc;
        this.itemNum = itemNum;
        this.time = time;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrc() {
        return itemPrc;
    }

    public void setItemPrc(double itemPrc) {
        this.itemPrc = itemPrc;
    }

    public int getRecID() {
        return recID;
    }

    public void setRecID(int recID) {
        this.recID = recID;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public double getTotalPrc() {
        return totalPrc;
    }

    public void setTotalPrc(double totalPrc) {
        this.totalPrc = totalPrc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
