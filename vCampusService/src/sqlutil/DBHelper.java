package sqlutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {
    public static String Driver = "com.mysql.cj.jdbc.Driver";
    public static String dbUrl = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
    public static String username = "root";
    public static String password = "655375";
    public static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName(Driver);
                System.out.println("Driver loaded");
                conn = DriverManager.getConnection(dbUrl, username, password);
                System.out.println("Database connected");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Fail to load driver");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Fail to connect database");
            }
        }
        return conn;
    }

    public static ResultSet executeQuery(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return rs;
    }

    public static ResultSet executeQuery(String sql, Object... obj) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pstmt.setObject(i + 1, obj[i]);
            }
            rs = pstmt.executeQuery();
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return rs;
    }

    public static int executeNonQuery(String sql) {
        int result = 0;
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            result = stmt.executeUpdate(sql);
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return result;
    }

    public static int executeNonQuery(String sql, Object... obj) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pstmt.setObject(i + 1, obj[i]);
            }
            result = pstmt.executeUpdate();
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return result;
    }

    public static void free(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public static void free(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public static void free(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
    }

    public static void free(ResultSet rs, Statement st, Connection conn) {
        free(rs);
        free(st);
        free(conn);
    }
}