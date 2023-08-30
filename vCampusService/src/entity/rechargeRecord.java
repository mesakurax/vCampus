package entity;

import java.io.Serializable;
public class rechargeRecord implements Serializable{
    private int rId;
    private String uId;
    private String uName;
    private double amount;
    private int isDispose;   //0表示未处理，1表示已经处理
    private String status;
    private String time;

    public rechargeRecord(){
        this.rId = 0;
        this.uId = "";
        this.uName = "";
        this.time = "";
        this.amount =0;
        this.isDispose = 0;
        this.status = "";
    }

    public rechargeRecord(int rId, String uId, String uName, double amount, int isDispose, String status, String time) {
        this.rId = rId;
        this.uId = uId;
        this.uName = uName;
        this.time = time;
        this.amount = amount;
        this.isDispose = isDispose;
        this.status = status;
    }

    public int getIsDispose() {
        return isDispose;
    }

    public void setIsDispose(int isDispose) {
        this.isDispose = isDispose;
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "rechargeRecord{" +
                "rId=" + rId +
                ", uId='" + uId + '\'' +
                ", uName='" + uName + '\'' +
                ", amount=" + amount +
                ", isDispose=" + isDispose +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
