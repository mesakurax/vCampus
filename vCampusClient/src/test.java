import utils.*;
import module.*;
import entity.*;

public class test {

    public static void main(String[] args) {
        SocketHelper help = new SocketHelper();
        help.getConnection(help.ip, help.port);
        bankSystem bank = new bankSystem(help);
        rechargeRecord temp = new rechargeRecord(199, "555", "1", 50, 3, " ", "Ê±´úÔÚÕÙ»½");
 /*       if (bank.user_addRecord(temp))
            System.out.println("uijuhbj");
    }
}*/
        rechargeRecord[] m = bank.admin_querysolved(temp);
        for (rechargeRecord record : m) {
            System.out.println(record.getrId() + ", " + record.getuId() + ", " + record.getuName() + ", " + record.getAmount()
                    + ", " + record.getIsDispose() + ", " + record.getStatus() + ", " + record.getTime());
        }

    }
}