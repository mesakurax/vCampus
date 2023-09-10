package entity;

import java.io.Serializable;
public class Cart implements Serializable{
    private int cartId;
    private String uId;
    private String itemId;
    private String itemName;
    private double itemPrc;
    private int itemStr;  //库存

    public Cart() {
    }
    public Cart(String uId, String itemId, String itemName, double itemPrc, int itemStr) {
        this.uId = uId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrc = itemPrc;
        this.itemStr = itemStr;
    }
    public Cart(String uId, String itemName) {
        this.uId = uId;
        this.itemName = itemName;
    }

    public Cart(String itemId) {
        this.itemId = itemId;
    }

    public Cart(String id,int opt){   //判断传进来的是uid还是itemid
        if(opt ==1){
            this.uId = id;
            this.itemId = null;
        }
        if(opt ==2){
            this.itemId = id;
            this.uId = null;
        }
        cartId = 0;
        itemName = "";
        itemPrc = 0;
        itemStr = 0;
    }

    public Cart(int cartId, String uId, String itemName) {
        this.cartId = cartId;
        this.uId = uId;
        this.itemName = itemName;
    }
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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

    public int getItemStr() {
        return itemStr;
    }

    public void setItemStr(int itemStr) {
        this.itemStr = itemStr;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
}
