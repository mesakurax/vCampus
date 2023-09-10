package entityModel;

import entity.User;
import sqlutil.DBConector_wyj;

import java.sql.*;
import java.util.HashMap;

public class UserDao_Imp implements UserDao {
    //Mysql查询语句模板
    private static final String SQL_USER_LOGIN = "SELECT * FROM test.user  WHERE uid=? AND upassword=?";
    //Mysql添加语句模板
    private static final String SQL_USER_INSERT = "INSERT INTO test.user VALUES(?,?,?,?,?,?,?,?)";
    //Mysql修改语句模板
    private static final String SQL_USER_UPDATE="update test.user set uname=?,upassword=?,occupation=?,academy=?,sex=?,age=?  where uid=?";
    private static final String SQL_USER_UPDATEB="update test.user set upassword=? where uid=?";
    private static final String SQL_USER_UPDATEC="update test.user set balance=? where uid=?";
    private static final String SQL_USER_SEARCHALL="select * from test.user";




    //用户登录
    @Override

    public User login(User user) throws SQLException {
        // TODO Auto-generated method stub
        //连接数据库，创建连接对象conn
        Connection conn = DBConector_wyj.getConnection();
        //创建预编译环境
        PreparedStatement prepare = conn.prepareStatement(SQL_USER_LOGIN);
        //设置sql语句中的参数
        prepare.setString(1,user.getId());
        prepare.setString(2,user.getPassword());
        System.out.println("id: "+user.getId()+"p: "+user.getPassword());
        //执行语句
        ResultSet result= prepare.executeQuery();

        //存储结果
        User u=new User();
        while(result.next())
        {
            //System.out.println("登陆成功");
            u.setId(result.getString("uid"));
            u.setName(result.getString("uname"));
            u.setPassword(result.getString("upassword"));
            u.setOccupation(result.getString("occupation"));
            u.setAcademy((result.getString("academy")));
            u.setBalance(result.getDouble("balance"));
            u.setSex(result.getString("sex"));
            u.setAge(result.getInt("age"));
            //清理资源
            prepare.close();
            conn.close();
            result.close();
            return u;
        }
            //System.out.println("登陆失败");
        //清理资源
        prepare.close();
        conn.close();
        result.close();
            return null;


    }


    @Override
    public boolean insert(User user)  {
        // TODO Auto-generated method stub
        //连接数据库，创建连接对象conn
        Connection conn = DBConector_wyj.getConnection();


        try {
            //创建预编译环境
            PreparedStatement prepare = conn.prepareStatement(SQL_USER_INSERT);
            //设置sql语句中的参数
            prepare.setString(1, user.getId());
            prepare.setString(2, user.getName());
            prepare.setString(3, user.getPassword());
            prepare.setString(4, user.getOccupation());
            prepare.setString(5, user.getAcademy());
            prepare.setString(6, user.getSex());
            prepare.setString(7, String.valueOf(user.getBalance()));
            prepare.setString(8, String.valueOf(user.getAge()));

            int result = prepare.executeUpdate();
            if (result > 0) {
                System.out.println("第" + result + "行被添加");
                //清理资源
                prepare.close();
                conn.close();
                return true;
            } else {
                System.out.println("添加失败");
                //清理资源
                prepare.close();
                conn.close();
                return false;
            }
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            return false;
        } catch (SQLException e1) {
            try {
                throw new RuntimeException(e1);
            }
            catch( RuntimeException e2)
            {
                return false;
            }
        }


    }

    @Override
    public boolean delete(String idtemp) throws SQLException {
        // TODO Auto-generated method stub
        //连接数据库，创建连接对象conn
        Connection conn = DBConector_wyj.getConnection();
        System.out.println("成功连接到数据库！");
        System.out.println("删除用户ID: "+idtemp);
        //删除数据的代码
        String sql = "delete from test.user where uid = "+idtemp;
        Statement pst= conn.createStatement();
        int rs = pst.executeUpdate(sql);//创建数据对象
        if(rs>0)
        {
            System.out.println("第"+rs+"行被删除");
            //清理资源
            pst.close();
            conn.close();

            return true;
        }
        else
        {
            System.out.println("删除失败");
            //清理资源
            pst.close();
            conn.close();
            return false;
        }

    }


    @Override
    public boolean update(User usertemp) throws SQLException {
        // TODO Auto-generated method stub
        //连接数据库，创建连接对象conn
        Connection conn = DBConector_wyj.getConnection();
        //创建预编译环境
        PreparedStatement prepare = conn.prepareStatement(SQL_USER_UPDATE);
        prepare.setString(1, usertemp.getName());
        prepare.setString(2, usertemp.getPassword());
        prepare.setString(3, usertemp.getOccupation());
        prepare.setString(4, usertemp.getAcademy());
        prepare.setString(5, usertemp.getSex());
        prepare.setString(6, String.valueOf(usertemp.getAge()));
        prepare.setString(7, usertemp.getId());
        int result=prepare.executeUpdate();
        if(result>0)
        {
            System.out.println("第"+result+"行被修改");
            //清理资源
            prepare.close();
            conn.close();
            return true;
        }
        else
        {
            System.out.println("修改失败");
            //清理资源
            prepare.close();
            conn.close();
            return false;
        }
    }
    @Override
    public boolean updateB(User usertemp) throws SQLException {
        // TODO Auto-generated method stub
        //连接数据库，创建连接对象conn
        Connection conn = DBConector_wyj.getConnection();
        //创建预编译环境
        PreparedStatement prepare = conn.prepareStatement(SQL_USER_UPDATEB);
        prepare.setString(1, usertemp.getPassword());
        prepare.setString(2, usertemp.getId());

        int result=prepare.executeUpdate();
        if(result>0)
        {
            System.out.println("第"+result+"行被修改");
            //清理资源
            prepare.close();
            conn.close();
            return true;
        }
        else
        {
            System.out.println("修改失败");
            //清理资源
            prepare.close();
            conn.close();
            return false;
        }
    }
    @Override
    public boolean updateC(String idtemp,double balancetemp) throws SQLException {
        // TODO Auto-generated method stub
        //连接数据库，创建连接对象conn
        Connection conn = DBConector_wyj.getConnection();
        //创建预编译环境
        try {
            PreparedStatement prepare = conn.prepareStatement(SQL_USER_UPDATEC);
            prepare.setString(1, String.valueOf(balancetemp));
            prepare.setString(2,idtemp);

            int result = prepare.executeUpdate();
            if (result > 0) {
                System.out.println("第" + result + "行被修改");
                //清理资源
                prepare.close();
                conn.close();
                return true;
            } else {
                System.out.println("修改失败");
                //清理资源
                prepare.close();
                conn.close();
                return false;
            }
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            return false;
        } catch (SQLException e1) {
            try {
                throw new RuntimeException(e1);
            }
            catch( RuntimeException e2)
            {
                return false;
            }
        }

    }

    @Override
    public User select(String idtemp) throws SQLException {
        // TODO Auto-generated method stub
        Connection conn = DBConector_wyj.getConnection();
        System.out.println("成功连接到数据库！");


        String  sql = "select * from test.user where uid="+idtemp+" ";    //要执行的SQL
        Statement pst= conn.createStatement();
        ResultSet rs = pst.executeQuery(sql);//创建数据对象
        System.out.println("正在查询： "+ idtemp);
        User u=new User();
        while(rs .next())
        {

            System.out.println("查到了："+ idtemp);
            u.setId(rs .getString("uid"));
            u.setName(rs .getString("uname"));
            u.setPassword(rs .getString("upassword"));
            u.setOccupation(rs .getString("occupation"));
            u.setAcademy((rs .getString("academy")));
            u.setBalance(rs .getDouble("balance"));
            u.setSex(rs .getString("sex"));
            u.setAge(rs .getInt("age"));
            //清理资源
            pst.close();
            conn.close();
            rs.close();
            return u;
        }

        System.out.println("没查到了："+ idtemp);
        //清理资源
        pst.close();
        conn.close();
        rs.close();
        return null;

    }
    @Override
    public HashMap<Integer, User> searchAll() throws SQLException {
        // TODO Auto-generated method stub
        Connection conn = DBConector_wyj.getConnection();
        //创建预编译环境
        PreparedStatement prepare = conn.prepareStatement(SQL_USER_SEARCHALL);
        //执行语句
        ResultSet result= prepare.executeQuery();
        //新建hashmap
        HashMap<Integer, User> users=new HashMap<Integer,User>();
        while(result.next())
        {
            User u=new User();
            u.setId(result.getString("uid"));
            u.setName(result.getString("uname"));
            u.setPassword(result.getString("upassword"));
            u.setOccupation(result.getString("occupation"));
            u.setAcademy((result.getString("academy")));
            u.setBalance(result.getDouble("balance"));
            u.setSex(result.getString("sex"));
            u.setAge(result.getInt("age"));

            users.put(users.size()+1,u);
        }
        //清理资源
        prepare.close();
        conn.close();
        result.close();
        return users;
    }

}
