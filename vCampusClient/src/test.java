import utils.*;
import entity.*;

public class test extends Thread{

   public  test(){}
    public static void main(String[] args) {
        new Thread(new client()).start();
    }
}

class client implements  Runnable{

    @Override
    public void run()
    {
        SocketHelper help = new SocketHelper();
        help.getConnection(help.ip, help.port);

        try {
            help.getOs().writeInt(0);
            help.getOs().flush();
            help.getOs().writeInt(-100);
            help.getOs().flush();
            help.getOs().writeObject("6");
            help.getOs().flush();

         /*   bankSystem bank = new bankSystem(help);
            rechargeRecord temp = new rechargeRecord(963, "555", "1", 50, 3, " ", "时代在召唤");
            if (bank.user_addRecord(temp))
                System.out.println("uijuhbj");*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        try{ help.getOs().writeInt(0);
            help.getOs().flush();
            User a=new User();
            a.setId("5");
            help.getOs().writeObject(a);
            help.getOs().flush();
            help.getOs().writeObject("你好");
            help.getOs().flush();
            System.out.println("kkk");
            while(true)
            {
                String p=(String)help.getIs().readObject();
                System.out.println(p);

            }}
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}


/*        try {
            help.getOs().writeInt(1);
            help.getOs().flush();
            bankSystem bank = new bankSystem(help);
            rechargeRecord temp = new rechargeRecord(199, "555", "1", 50, 3, " ", "时代在召唤");
       if (bank.user_addRecord(temp))
            System.out.println("uijuhbj");



            rechargeRecord[] m = bank.admin_querysolved(temp);
            for (rechargeRecord record : m) {
                System.out.println(record.getrId() + ", " + record.getuId() + ", " + record.getuName() + ", " + record.getAmount()
                        + ", " + record.getIsDispose() + ", " + record.getStatus() + ", " + record.getTime());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/