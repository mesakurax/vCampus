package sqlutil;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConector_wyj {
    public static String driver;
    private static String url;
    private static String username;
    private static String password;

    //静态语句块
    static
    {
        //读取数据库基本信息文件

        InputStream is = DBConector_wyj.class.getClassLoader().getResourceAsStream("dbbasicinformation.properties");


///创建Properties类型的对象
        Properties p=new Properties();
//加载流文件
        try {
            p.load(is);
            url=p.getProperty("url");
            username = p.getProperty("username");
            password=p.getProperty("password");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String driver = p.getProperty("driver");

//加载mysql驱动
        try {
            Class.forName(driver);
            System.out.println("驱动加载成功");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //获得连接对象的方法
    public static Connection getConnection()
    {
        try {
            Connection con=DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接成功");
            return con;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("数据库连接失败");
        return null;
    }

    //释放资源
    public static void close(Connection conn, PreparedStatement sta, ResultSet res)
    {
        try {
            if(res!=null)
            {
                res.close();
                res=null;
            }

            if(sta!=null)
            {
                sta.close();
                sta=null;
            }
            if(conn!=null)
            {
                conn.close();
                conn=null;
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void close(Connection conn,PreparedStatement sta)
    {
        try {


            if(sta!=null)
            {
                sta.close();
                sta=null;
            }
            if(conn!=null)
            {
                conn.close();
                conn=null;
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}

