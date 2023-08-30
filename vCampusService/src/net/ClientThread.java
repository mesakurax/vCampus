package net;

import entity.*;
import module.*;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;


/**
 * 客户端线程
 */
public class ClientThread extends Thread implements MessageTypes{
	/**
	 * 客户端当前连接的服务器线程
	 */
	private ServerThread currentServer;
	/**
	 * 客户端socket
	 */
	private Socket client;
	/**
	 * 对象输入流
	 */
	private ObjectInputStream ois;
	/**
	 * 对象输出流
	 */
	private ObjectOutputStream oos;
	/**
	 * 当前登录用户
	 */
	public String curUser;

	public ClientThread(Socket s, ServerThread st) {
		client = s;
		currentServer = st;
		curUser ="&&";
		try {
			//建立输入输出流（次序与客户端相反）
			ois = new ObjectInputStream(client.getInputStream());
			oos = new ObjectOutputStream(client.getOutputStream());

		} catch (IOException e) {
			System.out.println("Cannot get IO stream");
			e.printStackTrace();
		}
	}
	
	public void run() {
		int cmd = 0;//从客户端读到的消息

		while (true) {
			//读取消息
			try {
				cmd = ois.readInt();
				currentServer.severRun.textArea.append("服务器接受到的指令：" + cmd + " 来自：" + curUser + "\n");
				System.out.println("服务器接受到的指令：" + cmd + " 来自：" + curUser);
			} catch (IOException e) {
				//读不到指令，说明已登出
				return;
			}

			//判断消息属于哪一类型，调用对应模块函数完成相应功能
			switch (cmd / 100) {
				case -1:
					String uId = "";
					try {
						uId = (String) ois.readObject();
						curUser = curUser + uId;
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;


				//银行模块
				case 7:
					bank(cmd);
					break;

			}
		}
	}

	public void close() {
		if (client != null) {
			try {
				oos.close();
				ois.close();
				client.close();

				currentServer.closeClientConnection(this);//在服务器线程中关闭该客户端
				String msg="用户" + curUser + "已登出\n"+"客户端IP：" + client.getInetAddress().getHostAddress() +"  已断开,还剩下"+currentServer.getSize()+"个客户端";
				currentServer.severRun.textArea.append(msg);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



	/**
	 * 各模块功能函数
	 *
	 * 服务器端与客户端的数据交流遵从以下模式：
	 * 1. 服务器从客户端读取消息
	 * 2. 服务器从客户端读取所需参数（可选）
	 * 3. 服务器向客户端写回请求状态
	 * 4. 服务器向客户端写回请求结果集（可选）
	 */



	/**
	 * 登录模块
	 *
	 * @param cmd 接受的消息
	 */




	/**
	 * 课程管理模块
	 *
	 * @param cmd 接受的消息
	 */




	/**
	 * 商店模块
	 *
	 * @param cmd 接受的消息
	 */


	/**
	 * 银行模块
	 *
	 * @param cmd 接受的消息
	 */
	private void bank(int cmd){
		bankSystem model=new bankSystem();
		rechargeRecord info=new rechargeRecord();
		try{
			info=(rechargeRecord) ois.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		switch (cmd){
			case 701:
				try {
					int writeBack = model.addRecord(info)?7011:7012;
					System.out.println(writeBack);
					oos.writeInt(writeBack);
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 702:
				try {
					rechargeRecord[] result = model.queryRecord(info,1);

					if (result != null) {
						System.out.println(7021);
						oos.writeInt(7021);
						oos.flush();
						oos.writeObject(result);
						oos.flush();
					} else {
						System.out.println(7022);
						oos.writeInt(7022);
						oos.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case 703:
				try {
					rechargeRecord[] result = model.queryRecord(info,2);

					if (result != null) {
						System.out.println(7031);
						oos.writeInt(7031);
						oos.flush();
						oos.writeObject(result);
						oos.flush();
					} else {
						System.out.println(7032);
						oos.writeInt(7032);
						oos.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case 704:
				try {
					rechargeRecord[] result = model.queryRecord(info,3);

					if (result != null) {
						System.out.println(7041);
						oos.writeInt(7041);
						oos.flush();
						oos.writeObject(result);
						oos.flush();
					} else {
						System.out.println(7042);
						oos.writeInt(7042);
						oos.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case 705:
				try {
					int writeBack = model.accept(info)?7051:7052;
					System.out.println(writeBack);
					oos.writeInt(writeBack);
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 706:
				try {
					int writeBack = model.refuse(info)?7061:7062;
					System.out.println(writeBack);
					oos.writeInt(writeBack);
					oos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 707:
				try {
					rechargeRecord[] result = model.queryRecord(info,4);

					if (result != null) {
						System.out.println(7071);
						oos.writeInt(7071);
						oos.flush();
						oos.writeObject(result);
						oos.flush();
					} else {
						System.out.println(7072);
						oos.writeInt(7072);
						oos.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case 708:
				try {
					rechargeRecord[] result = model.queryRecord(info,5);

					if (result != null) {
						System.out.println(7081);
						oos.writeInt(7081);
						oos.flush();
						oos.writeObject(result);
						oos.flush();
					} else {
						System.out.println(7082);
						oos.writeInt(7082);
						oos.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;



		}


	}


}




