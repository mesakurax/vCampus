package module;

import entity.Cart;
import entity.Item;
import entity.ItemRecord;
import entityModel.CartModel;
import entityModel.ItemModel;
import entityModel.ItemRecordModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Shop {

        private ItemModel itemModel;
        private CartModel cartModel;
        private ItemRecordModel recordModel;

        public Shop() {
            itemModel = new ItemModel();
            cartModel = new CartModel();
            recordModel = new ItemRecordModel();
        }

        //增加商品  管理员行为
        public boolean addItem(Item temp) {
            try {
                ResultSet rs = (ResultSet) (itemModel.Search(temp, 2));  //用itemid查
                int row = rs.getRow();
                if (row != 0) return false;
                if (itemModel.Insert(temp)) return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        //删除商品 管理员
        //先找这个商品存不存在
        //存在的话 提取商品所在的购物车记录 全都删掉
        public boolean deleteItem(Item temp) {
            try {
                ResultSet rs = (ResultSet) (itemModel.Search(temp, 2));  //用id查
                if(!rs.next())return false;
                else
                {
                    Cart ctemp=new Cart(temp.getItemId(),2); //2是itemID，1是uid
                    ResultSet rs1=(ResultSet)(cartModel.Search(ctemp,3));  //用itemid查
                    if(itemModel.Delete(temp)){
                        if(rs1.next()){
                            cartModel.Delete(ctemp,2);
                        }
                        return true;
                    }
                    else return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        //修改商品 管理员
        public boolean modifyItem(Item temp) {
            try{
                if(itemModel.Modify(temp)) {
                    Cart ctemp=new Cart(temp.getItemId(),2);
                    ResultSet rs=(ResultSet)(cartModel.Search(ctemp,3));
                    //int check=0;
                    while(rs.next()){
                        Cart c=new Cart(null,temp.getItemName());
                        c.setItemId(temp.getItemId());
                        c.setItemName(temp.getItemName());
                        c.setItemPrc(temp.getItemPrc());
                        c.setItemStr(temp.getItemStr());
                       // cartModel.Modify(c);
                    }
                    return true;
                }
                return false;
            }catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        //管理员查找商品
        public Vector<Item> searchItem(Item temp){
            try {
                ResultSet rs;
                if(temp.getItemId()==null&&temp.getItemName()==null)
                    rs=(ResultSet)itemModel.Search(temp,1);  //1全搜
                else if(temp.getItemId().equals(""))
                    rs=(ResultSet)itemModel.Search(temp,3);
                else rs=(ResultSet)itemModel.Search(temp,2);
                Vector<Item> v=new Vector<Item>();
                int check=0;
                while(rs.next()){
                    Item t=new Item(null,null);
                    t.setItemId(rs.getString("itemId"));
                    t.setItemName(rs.getString("itemName"));
                    t.setItemPrd(rs.getString("itemPrd"));
                    t.setItemPrc(rs.getDouble("itemPrc"));
                    t.setItemStr(rs.getInt("itemStr"));
                    t.setItemSales(rs.getInt("itemSales"));
                    t.setItemImage(rs.getString("itemImage"));
                    v.add(t);
                    check++;
                }

                if(check!=0) {
                    return v;
                }
                return null;
            }catch(SQLException e){
                e.printStackTrace();
            }
            return null;
        }


        //商品增加到购物车
        public boolean addtoCart(Cart temp) {
            try {
                ResultSet rs = (ResultSet) (cartModel.Search(temp, 3));//用uid和商品id查该商品是否已经在该用户购物车中
                int row = rs.getRow();

                if (row != 0)
                    return false;
                if (cartModel.Insert(temp))
                    return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }

        //从购物车中删除商品
        public boolean deletefromCart(Cart temp){
            //Vector<Cart> dele = new Vector<Cart>();
            // ResultSet rs = (ResultSet) (cartModel.Search(temp, 3));//用商品id查
            //  if(!rs.next())return false;
            // else
            if(cartModel.Delete(temp,4)) {
                System.out.println("删除成功");
                return true;
            }
            else return false;
        }

        //从购物车查找
        public Vector<Cart> searchInCart(Cart temp){
            try {
                ResultSet rs;
                if(temp.getItemName().equals("")) {
                    System.out.println("在购物车查找用的id");
                    rs = (ResultSet) (cartModel.Search(temp, 1));
                }
                else {
                    System.out.println("在购物车查找用的id和名字");
                    rs = (ResultSet) (cartModel.Search(temp, 2));
                }
                Vector<Cart> v=new Vector<Cart>();
                int check=0;
                while(rs.next()){
                    Cart c=new Cart(null,1);
                    c.setItemId(rs.getString("itemId"));
                    c.setCartId(Integer.parseInt(rs.getString("cartId")));
                    c.setuId(rs.getString("uId"));
                    c.setItemName(rs.getString("itemName"));
                    c.setItemStr(rs.getInt("itemStr"));
                    c.setItemPrc(rs.getDouble("itemPrc"));
                    v.add(c);
                    check++;
                }

                if(check!=0) return v;
                return null;
            }catch(SQLException e){
                e.printStackTrace();
            }
            return null;
        }

        //用户购买商品即增加一条消费记录
        public boolean addItemRecord(ItemRecord temp){

            if (recordModel.Insert(temp)) {

                Item t=new Item(temp.getItemId(),temp.getItemName());
                Vector<Item> rs=searchItem(t);
                int sales=rs.elementAt(0).getItemSales()+temp.getItemNum();
                int str=rs.elementAt(0).getItemStr()-temp.getItemNum();
                t.setItemPrd(rs.elementAt(0).getItemPrd());
                t.setItemPrc(rs.elementAt(0).getItemPrc());
                t.setItemSales(sales);
                t.setItemStr(str);
                t.setItemImage(rs.elementAt(0).getItemImage());
                modifyItem(t);

                Cart c = new Cart();
                c.setCartId(temp.getRecID());
               CartModel model = new CartModel();
               model.Delete(c,4);

               return true;
            }

            return false;
        }

        //用户查询消费记录
        public Vector<ItemRecord> searchItemRecord(ItemRecord temp){
            try {
                ResultSet rs;
                if (temp.getuId()!=null&&temp.getItemName()!=null) {
                    rs=(ResultSet)(recordModel.Search(temp,2));
                }
                else
                    rs = (ResultSet)(recordModel.Search(temp, 1));
                Vector<ItemRecord> v=new Vector<ItemRecord>();
                int check=0;
                while(rs.next()){
                    ItemRecord t=new ItemRecord(null);
                    t.setItemId(rs.getString("itemId"));

                    String recIdStr = rs.getString("recId");
                    int recId = (recIdStr != null && !recIdStr.equals("null")) ? Integer.parseInt(recIdStr) : 0;
                    t.setRecID(recId);

                    t.setuId(rs.getString("uId"));
                    t.setItemName(rs.getString("itemName"));
                    t.setItemNum(rs.getInt("itemNum"));
                    t.setItemPrc(rs.getDouble("itemPrc"));
                    t.setTotalPrc(t.getItemNum()*t.getItemPrc());
                    t.setTime(rs.getString("time"));
                    v.add(t);
                    check++;
                }
                System.out.println(check);
                if(check!=0) {
                    return v;
                }
                return null;
            }catch(SQLException e){
                e.printStackTrace();
            }
            return null;
        }

/*    public static void main(String[] args) {
        ItemRecord temp = new ItemRecord("23","34","45","56",8.8,3,5,"20:38");
        ItemRecordModel model = new ItemRecordModel();

        Item temp1 = new Item("34","345");
        ItemModel mod2 = new ItemModel();

        Cart temp2 = new Cart("345");
        CartModel mod3 = new CartModel();

        Shop p = new Shop();
        if (p.addtoCart(temp2))
            System.out.println("yes");
        else
            System.out.println("null");
    }*/

}
