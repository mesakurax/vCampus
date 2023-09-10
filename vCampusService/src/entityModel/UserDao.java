package entityModel;

//import common.User;

import entity.User;

import java.sql.SQLException;
import java.util.HashMap;

public interface UserDao {
    User login(User user) throws SQLException;
    boolean insert(User user) throws SQLException;
    boolean delete(String uid) throws SQLException;
    //修改学生信息
    boolean update(User usertemp) throws SQLException;
    boolean updateB(User usertemp) throws SQLException;
    boolean updateC(String idtemp,double balancetemp) throws SQLException;

    //查询学生信息
    User select(String uid) throws SQLException;


    HashMap<Integer, User> searchAll() throws SQLException;
}
