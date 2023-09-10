package module;

import entity.rechargeRecord;
import entityModel.UserDao_Imp;
import entityModel.rechargeRecordModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class bankSystem {
    private rechargeRecordModel model;

    public bankSystem() {
        this.model = new rechargeRecordModel();
    }

    //用户发送充值请求
    public Boolean addRecord(rechargeRecord info) {
        return model.insert(info);
    }

    //查询充值记录
    public rechargeRecord[] queryRecord(rechargeRecord info, int flag) {
        try {
            ResultSet rs = (ResultSet) model.query(info, flag);
            List<rechargeRecord> records = new ArrayList<>();

            while (rs.next()) {
                rechargeRecord temp = new rechargeRecord(
                        rs.getInt("rId"),
                        rs.getString("uId"),
                        rs.getString("uName"),
                        rs.getDouble("amount"),
                        rs.getInt("isDispose"),
                        rs.getString("status"),
                        rs.getString("time")
                );
                records.add(temp);
            }
                return records.toArray(new rechargeRecord[records.size()]);

        } catch (Exception e) {
            System.out.println("Database exception");
            e.printStackTrace();
            return null;
        }
    }

    //同意充值记录
    public Boolean accept(rechargeRecord info) {
        rechargeRecord temp = info;
        temp.setIsDispose(1);
        temp.setStatus("Success");

        UserDao_Imp temp1=new UserDao_Imp();

        try {
            Double b = temp1.select(info.getuId()).getBalance();
            temp1.updateC(info.getuId(),b+info.getAmount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return model.modify(temp);
    }

    public Boolean refuse(rechargeRecord info) {
        rechargeRecord temp = new rechargeRecord();
        temp.setrId(info.getrId());
        temp.setIsDispose(1);
        temp.setStatus("Failure");

        return model.modify(temp);
    }


    public static void main(String[] args) {
        rechargeRecord temp = new rechargeRecord(1, "1", "1", 60, 9, "55", "u或");
        bankSystem p = new bankSystem();
        if (p.accept(temp))
            System.out.println("yes");
        else
            System.out.println("null");
    }
}
 /*       rechargeRecord[] rs=(rechargeRecord[]) p.queryRecord(temp,5);
        if(rs==null)
            System.out.println("null");
        else
            for (rechargeRecord record : rs) {
                System.out.println(record.getrId() + ", " + record.getuId() + ", " + record.getuName() + ", " + record.getAmount()
                +", "+record.getIsDispose()+", "+record.getStatus()+", "+record.getTime());
            }
    }
}

*/