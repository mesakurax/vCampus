package entityModel;

import entity.Cart;
import sqlutil.DBHelper;

import java.sql.ResultSet;
public class CartModel {
        private String Query = "";
        private Cart info = null;
        public CartModel() {

        }
        public boolean Insert(Object obj) {
            this.info = (Cart) obj;
            try {
                this.Query = "Insert into Cart(cartId,uId, itemId, itemName, itemPrc, itemStr) " +
                        "values('" + info.getCartId() + "','"+info.getuId() + "','" + info.getItemId() + "','" + info.getItemName()
                        + "'," + info.getItemPrc() + "," + info.getItemStr() + ")";
                System.out.println(this.Query);
                if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
            } catch(Exception err) {
                err.printStackTrace();
            }
            return false;
        }

        public boolean Modify(Object obj) {
            this.info = (Cart) obj;
            try {
                this.Query = "update Cart set itemName = '" + info.getItemName() + "',itemId = '" + info.getItemId() + "',itemPrc = "
                        + info.getItemPrc() + ",itemStr = " + info.getItemStr() ;
                System.out.println(this.Query);
                if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
            } catch(Exception err) {
                err.printStackTrace();
            }
            return false;
        }

        public boolean Delete(Object obj,int opt) {
            this.info = (Cart) obj;
            try {
                if(opt==1)this.Query="delete from Cart where uId ='" + this.info.getuId() + "'";
                if(opt==2)this.Query="delete from Cart where itemId = '"+this.info.getItemId()+"'";
                if(opt==3)this.Query = "delete from Cart  where uId = '" + this.info.getuId() + "'"
                       + " and ItemName = '" + this.info.getItemName() + "'";
                if(opt==4)this.Query = "delete from Cart  where cartID = '" + this.info.getCartId() + "'";


                System.out.println(this.Query);

                if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
            } catch(Exception err) {
                err.printStackTrace();
            }
            return false;
        }

        public Object Search(Object obj, int opt) {
            this.info = (Cart) obj;
            if(1 == opt) this.Query = "select * from Cart where uId = '" + this.info.getuId() + "'";
            if(2 == opt) this.Query = "select * from Cart where uId = '" + this.info.getuId() + "'"
                    + " and ItemName = '" + this.info.getItemName() + "'";
            if(3==opt)this.Query="select * from Cart where itemId = '" + this.info.getItemId() + "'";

            try {
                System.out.println(this.Query);
                ResultSet rs =  DBHelper.executeQuery(this.Query);
                if(rs != null) {
                    System.out.println("CartRepository successfully return rs");
                    return rs;
                } else {
                    System.out.println("CartRepository return rs = null");
                }
            } catch(Exception err) {
                err.printStackTrace();
            }
            return null;
        }

   /* public static void main(String[] args) {
        // 创建一个示例 Cart 对象
        Cart cartToDelete = new Cart("123456", "ItemNameToDelete");

        // 创建 DBHelper 实例
        DBHelper dbHelper = new DBHelper(); // 确保替换为实际的 DBHelper 创建方式
  CartModel model = new CartModel();
        // 调用 Delete 方法删除购物车项目
        boolean deleted = model.Delete(cartToDelete, 2);

        // 检查是否删除成功
        if (deleted) {
            System.out.println("成功删除购物车项目！");
        } else {
            System.out.println("删除购物车项目失败！");
        }
    }*/



}
