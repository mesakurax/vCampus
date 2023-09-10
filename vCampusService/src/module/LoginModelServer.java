package module;

//import common.User;

import entity.Operation;
import entity.User;
import entityModel.UserDao_Imp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

public class LoginModelServer extends Thread{

    private static Operation operresult;

    public static void login(User utemp) throws SQLException {


        //操作读取完毕清空
        operresult.operClear();
        //实例化数据库操作对象
        UserDao_Imp daotemp=new UserDao_Imp();
        //登录操作
        User u=daotemp.login(utemp);
        if(u!=null)
        {
            operresult.addUser(u);
            operresult.setSuccess(true);
        }



    }
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
    public static void passwordChange(User utemp, User unew) throws SQLException {

        //操作读取完毕清空
        operresult.operClear();
        //实例化数据库操作对象
        UserDao_Imp daotemp = new UserDao_Imp();
        //删除一个操作
        if(daotemp.login(utemp)!=null)
        {

            System.out.println("登录成功090909");
            if(daotemp.updateB(unew))
            {
                System.out.println("修改成功090909");
                operresult.setSuccess(true);
            }
        }
    }

    //输入输出流
    private static ObjectOutputStream outputStream;
    private static ObjectInputStream inputStream;



    public LoginModelServer(ObjectOutputStream ostemp, ObjectInputStream oitemp) {

        //设置输入输出
        outputStream = ostemp;
        inputStream = oitemp;

        //实例化操作
         operresult=new Operation();

    }

    public static  void LoginHandler() throws IOException, ClassNotFoundException, SQLException {

        //用户是否退出
        boolean isfinished=false;
       // while (!isfinished) {
            System.out.println("4545454545");

            // 读取客户端发送的对象
             operresult.copy((Operation)inputStream.readObject());
            // operresult=((Operation)inputStream.readObject());

            //获取操作

            if (operresult.getOperationcode() == 001) {
                System.out.println("登录");
                //登录操作
                login(operresult.getUser(1));
                if(operresult.getSuccess())
                {
                    isfinished=true;
                }
                // 向客户端发送响应数据
                outputStream.reset();
                outputStream.writeObject(operresult);
                //重置操作
                operresult.operClear();
                // 清理资源
                outputStream.flush();
            } else if (operresult.getOperationcode() == 002) {
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

            //修改密码
            else if (operresult.getOperationcode() == 007) {
                System.out.println("修改");
                passwordChange(operresult.getUser(1),operresult.getUser(2));
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
                isfinished=true;

            }

        }
   // }



}


