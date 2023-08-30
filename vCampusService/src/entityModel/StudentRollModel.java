package entityModel;

import entity.StudentRoll;
import sqlutil.DBHelper;

import java.sql.ResultSet;

public class StudentRollModel {
    private String Query = "";
    private StudentRoll info = null;
    public StudentRollModel() {

    }
    public boolean Insert(Object obj) {
        this.info = (StudentRoll) obj;
        try {
            this.Query = "Insert into tblStudent(stuId, stuName, stuSex, stuGrade, stuPlace, stuDepart, stuProf) values('" + info.getStuId() + "','"
                    + info.getStuName() + "','" + info.getsSex() + "','" + info.getsGrades() + "','" + info.getsPlace() + "','" + info.getsDepart() + "','" +
                    info.getsProfession() + "')";
            System.out.println(this.Query);
            if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
        } catch(Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public boolean Modify(Object obj) {
        this.info = (StudentRoll) obj;
        try {
            this.Query = "update tblStudent set stuName = '" + info.getStuName() + "',stuSex = '" + info.getsSex() + "',stuGrade = '" + info.getsGrades()
                    + "',stuPlace = '" + info.getsPlace() + "',stuDepart = '" + info.getsDepart() + "',stuProf = '"+info.getsProfession()
        + "' where stuId = '" + info.getStuId() + "'";
            System.out.println(this.Query);
            if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
        } catch(Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public boolean Delete(Object obj) {
        this.info = (StudentRoll) obj;
        try {
            this.Query = "delete from tblStudent where stuId = '" + info.getStuId() + "'";
            System.out.println(this.Query);
            if(1 ==  DBHelper.executeNonQuery(this.Query)) return true;
        } catch(Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public Object Search(Object obj, int opt) {
        this.info = (StudentRoll) obj;
        //this.Query = "select * from tblStudent";
        if(1 == opt) this.Query = "select * from tblStudent where stuId = '" + this.info.getStuId() + "'";
        if(2 == opt) this.Query = "select * from tblStudent where stuName = '" + this.info.getStuName() + "'";
        if(3 == opt) this.Query = "select * from tblStudent";
        try {
            System.out.println(this.Query);
            ResultSet rs =  DBHelper.executeQuery(this.Query);
            if(rs != null) {
                System.out.println("StudentRepository successfully return rs");
                return rs;
            } else {
                System.out.println("StudentRepository return rs = null");
            }
        } catch(Exception err) {
            err.printStackTrace();
        }
        return null;
    }
}
