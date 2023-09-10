package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


/**
 * 服务器主线程
 */
public class

ServerThread extends Thread {

    private ServerSocket server;   //服务器Socket

    public Vector<ClientThread> clients=new Vector<ClientThread>(); //已连接的客户端线程向量

    public Vector<ClientThread> mess=new Vector<ClientThread>(); //已连接的客户端线程向量

    public int count=0;

    public SeverRun severRun;

    public ServerSocket getServer() {
        return server;
    }

    public ServerThread(SeverRun severRun) {

        this.severRun=severRun;

        try {
            server = new ServerSocket(8081);

            this.severRun.textArea.append("Server main thread start.\nListen on port 8081"+"\n");
            System.out.println ("Server main thread start.\nListen on port 8081"+"\n");

            this.start();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        //当服务器在运行
        while(!server.isClosed()) {

            this.severRun.setVisible(true);
            try {
                Socket client = server.accept();  //监听新的客户端
                ClientThread current = new ClientThread(client, this);
                int a=current.getOis().readInt();
                if(a==0)
                {
                    mess.add(current);
                    System.out.println("现在有"+mess.size()+"个消息客户端连接服务器\n");
                }
                else if(a==1)
                {
                    clients.add(current);
                    System.out.println("现在有"+clients.size()+"个客户端连接服务器\n");
                }
                current.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        //如果服务器Socket已被打开
        if (server != null) {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 返回当前已连接客户端数量
     */
    public int getSize() {
        return clients.size();
    }

    /**
     * 向向量中添加新的客户端
     */
    public int addClientConnection(ClientThread ct) {
        clients.add(ct);

        return clients.size();
    }

    /**
     * 从向量中移除关闭的客户端
     *
     * @param ct 要关闭的客户端线程
     * @return 关闭状态
     */
    public boolean closeClientConnection(ClientThread ct) {
        if (clients.contains(ct)) {
            clients.remove(ct);

            return true;
        }

        return false;
    }

    /**
     * 在向量中按登录用户ID寻找客户端
     */
    public boolean searchClientConnection(String curUser) {

        this.severRun.textArea.append("搜寻之前共有："+clients.size()+"个客户端");
        System.out.println("搜寻之前共有："+clients.size()+"个客户端");
        for (ClientThread ct: clients) {
            //this.severRun.textArea.append("服务器调用了searchClientConnection(String curUser)\n传入的curUser="+curUser+"\t遍历到客户端向量中的curUser="+ct.curUser);
            if (ct.curUser != null && ct.curUser.equals(curUser)) {
                return true;
            }
        }
        return false;
    }
}

