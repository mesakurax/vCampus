package module;
import utils.*;
import entity.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class bankSystem {
    ObjectInputStream is;
    ObjectOutputStream os;

    public bankSystem(SocketHelper socketHelper){
        this.is=socketHelper.getIs();
        this.os=socketHelper.getOs();
    }

    public boolean user_addRecord(rechargeRecord info)
    {

        try {
            this.os.writeInt(701);
            this.os.flush();
            this.os.writeObject(info);
            this.os.flush();
            System.out.println("user_addRecord");

            try {
                if(this.is.readInt()==7011)
                    return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
