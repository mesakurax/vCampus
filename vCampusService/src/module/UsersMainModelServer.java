package module;

//import common.User;

import entity.Operation;
import entity.User;
import entityModel.UserDao_Imp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.HashMap;

public class UsersMainModelServer extends Thread{

    //private SocketHelper socket;
    private static Operation operresult;
    //输入输出流
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;

    public static void register(User utemp) throws SQLException {

        //操作读取完毕清空
        operresult.operClear();
        //实例化数据库操作对象
        UserDao_Imp daotemp = new UserDao_Imp();
        //登录操作
        boolean result = daotemp.insert(utemp);
        if (result) {
            operresult.setSuccess(true);
        }
    }
    public static void searchAll() throws SQLException {

        //实例化数据库操作对象
        UserDao_Imp daotemp = new UserDao_Imp();
        //查询全部操作
        operresult.setUsers(daotemp.searchAll());
        if(!operresult.getUsers().isEmpty())
        {
            operresult.setSuccess(true);
        }
    }
    public static void searchOne(User utemp) throws SQLException {

        //操作读取完毕清空
        operresult.operClear();
        //实例化数据库操作对象
        UserDao_Imp daotemp = new UserDao_Imp();
        System.out.println("kkkkkkkkk: "+utemp.getId());
        //查询一个操作
        User u=daotemp.select(utemp.getId());

        if(u!=null)
        {
            operresult.addUser(u);
            operresult.setSuccess(true);
        }
    }
    public static void deleteOne(User utemp) throws SQLException {

        //实例化数据库操作对象
        UserDao_Imp daotemp = new UserDao_Imp();
        //删除一个操作
        if(daotemp.delete(utemp.getId()))
        {

            operresult.setSuccess(true);
        }
        else
        {
            operresult.setSuccess(false);
        }
    }
    public static void updateOne(User utemp) throws SQLException {

        //操作读取完毕清空
        operresult.operClear();
        //实例化数据库操作对象
        UserDao_Imp daotemp = new UserDao_Imp();
        //删除一个操作
        if(daotemp.update(utemp))
        {

            operresult.setSuccess(true);
        }
    }

    public static void searchOcupation(String ocu) throws SQLException {

        //操作读取完毕清空
        operresult.operClear();

        //实例化数据库操作对象
        UserDao_Imp daotemp = new UserDao_Imp();
        //查询全部操作
        HashMap<Integer,User> users=new HashMap<Integer,User>();
        users=daotemp.searchAll();
        if(!users.isEmpty())
        {
            //筛选
            for(int i=1;i<=users.size();i++)
            {
                if(users.get(i).getOccupation().equals(ocu))
                {
                    operresult.addUser(users.get(i));
                }
            }
            if(!operresult.getUsers().isEmpty())
            {
                operresult.setSuccess(true);
            }
        }
        else
        {
            operresult.setSuccess(false);
            System.out.println("无人为当前职业");
        }
    }

    public UsersMainModelServer(ObjectOutputStream ostemp,ObjectInputStream oitemp) {

        //设置输入输出
        outputStream = ostemp;
        inputStream = oitemp;

        //实例化操作
        operresult=new Operation();
    }
    public static void UsersMainHandler() throws SQLException, IOException, ClassNotFoundException {


        boolean isfinished = false;
        // while (!isfinished) {
        // 读取客户端发送的对象
        operresult.copy((Operation) inputStream.readObject());
        System.out.println("接受用户端对象");
        System.out.println("编号： " + operresult.getOperationcode());

        if (operresult.getOperationcode() == 002) {
            System.out.println("注册");
            //注册操作
            System.out.println("909090");
            register(operresult.getUser(1));
            // 向客户端发送响应数据
            outputStream.reset();
            outputStream.writeObject(operresult);
            //重置操作
            operresult.operClear();
            // 清理资源
            outputStream.flush();
        }
        //查询全部
        else if (operresult.getOperationcode() == 003) {
            System.out.println("查询全部");
            //操作读取完毕，清空
            operresult.operClear();
            searchAll();
            // 向客户端发送响应数据
            outputStream.reset();
            outputStream.writeObject(operresult);
            //重置操作
            operresult.operClear();
            // 清理资源
            outputStream.flush();


        }
        //查询一个
        else if (operresult.getOperationcode() == 004) {
            System.out.println("查询一个");
            System.out.println("查询: " + operresult.getUser(1).getId());
            searchOne(operresult.getUser(1));
            // 向客户端发送响应数据
            outputStream.reset();
            outputStream.writeObject(operresult);
            //重置操作
            operresult.operClear();
            // 清理资源
            outputStream.flush();


        }
        //删除
        else if (operresult.getOperationcode() == 005) {
            System.out.println("删除");
            //读取操作对象个数
            int number=operresult.getUsers().size();
            boolean finalresult=true;
            //更新结果
            for(int i=1;i<=number;i++) {
                //执行删除操作
                deleteOne(operresult.getUser(i));
                //更新结果
                if(!operresult.getSuccess())
                {
                    finalresult=false;
                }
            }
            //设置结果
            operresult.operClear();
            operresult.setSuccess(finalresult);
            // 向客户端发送响应数据
            outputStream.reset();
            outputStream.writeObject(operresult);
            //重置操作
            operresult.operClear();
            // 清理资源
            outputStream.flush();


        }
        //更新
        else if (operresult.getOperationcode() == 006) {
            System.out.println("更新");
            updateOne(operresult.getUser(1));
            // 向客户端发送响应数据
            outputStream.reset();
            outputStream.writeObject(operresult);
            //重置操作
            operresult.operClear();
            // 清理资源
            outputStream.flush();


        }
        else if(operresult.getOperationcode()==007)
        {
            System.out.println("按职业查询");
            searchOcupation(operresult.getUser(1).getOccupation());
            // 向客户端发送响应数据
            outputStream.reset();
            outputStream.writeObject(operresult);
            //重置操作
            operresult.operClear();
            // 清理资源
            outputStream.flush();
        }

        //终止操作
        else if (operresult.getOperationcode() == -1) {
            System.out.println("服务端终止");
            isfinished = true;

        }
    }
}
// }






