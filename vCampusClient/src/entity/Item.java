package entity;

import java.io.Serializable;

//实体：商店物品
public class Item implements Serializable{
    private String itemId;
    private String itemName;
    private String itemPrd; //商品厂家
    public double itemPrc;  //价钱
    private int itemSales; //销量
    private int itemStr; //库存

    public Item() {
    }

    public Item(String id, String name){
        itemId =id;
        itemName =name;
        itemPrd =" ";
        itemPrc =0;
        itemSales =0;
        itemStr =0;
    }

    public Item(String id, String name, String producer, double pr, int sales, int storage){
        itemId =id;
        itemName =name;
        itemPrd =producer;
        itemPrc =pr;
        itemSales =sales;
        itemStr =storage;
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

    public String getItemPrd() {
        return itemPrd;
    }

    public void setItemPrd(String itemPrd) {
        this.itemPrd = itemPrd;
    }

    public double getItemPrc() {
        return itemPrc;
    }

    public void setItemPrc(double itemPrc) {
        this.itemPrc = itemPrc;
    }

    public int getItemSales() {
        return itemSales;
    }

    public void setItemSales(int itemSales) {
        this.itemSales = itemSales;
    }

    public int getItemStr() {
        return itemStr;
    }

    public void setItemStr(int itemStr) {
        this.itemStr = itemStr;
    }

    private String itemImage;

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public Item(String itemId, String itemName, String itemPrd, double itemPrc, int itemSales, int itemStr, String itemImage) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrd = itemPrd;
        this.itemPrc = itemPrc;
        this.itemSales = itemSales;
        this.itemStr = itemStr;
        this.itemImage = itemImage;
    }

    public Item(String itemId, String itemName, String itemPrd) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrd = itemPrd;
    }
}
