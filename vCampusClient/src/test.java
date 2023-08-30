import utils.*;
import module.*;
import entity.*;

public class test {

    public static void main(String[] args)
    {
        SocketHelper help=new SocketHelper();
        help.getConnection(help.ip,help.port);
        bankSystem bank=new bankSystem(help);
        rechargeRecord temp=new rechargeRecord(103,"555","1",50,3," ","u或");
        if(bank.user_addRecord(temp))
            System.out.println("uijuhbj");

    }

}
