package entityModel;

import entity.CrsPickRecord;
import sqlutil.DBHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CrsPickRecordModel {
    private String Query = "";
    private CrsPickRecord cpr = null;

    public CrsPickRecordModel() {

    }

//
public boolean addcrsPickRecord(Object obj) {
    this.cpr = (CrsPickRecord) obj;
    try {
        this.Query = "INSERT INTO CrsPickRecord (crsPickId, crsId, crsName, stuId, stuName, teacherId,teacherName,v1,v2,v3, crsPickScore) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ? , ? , ? ,?)";

        PreparedStatement statement = DBHelper.getConnection().prepareStatement(this.Query);
        statement.setNull(1, java.sql.Types.INTEGER); // …Ë÷√ crsPickId Œ™ null
        statement.setInt(2, cpr.getCrsId());
        statement.setString(3, cpr.getCrsName());
        statement.setString(4, cpr.getStuId());
        statement.setString(5, cpr.getStuName());
        statement.setString(6, cpr.getTeacherId());
        statement.setString(7, cpr.getTeacherName());
        statement.setDouble(8, cpr.getScore1());
        statement.setDouble(9, cpr.getScore2());
        statement.setDouble(10, cpr.getScore3());
        statement.setDouble(11, cpr.getCrsPickScore());

        int affectedRows = statement.executeUpdate();
        if (affectedRows > 0) {
            System.out.println(this.Query); // ¥Ú”° SQL ”Ôæ‰
            return true;
        }
    } catch (Exception err) {
        err.printStackTrace();
    }
    return false;
}

    public boolean modifyPickRecord(Object obj) {
        this.cpr = (CrsPickRecord) obj;
        try {
            this.Query = "UPDATE crspickrecord SET crsId = " + cpr.getCrsId() + ", crsName = '" + cpr.getCrsName()
                    + "', stuId = '" + cpr.getStuId() + "', stuName = '" + cpr.getStuName() + "', teacherId = '"
                    + cpr.getTeacherId() + "', teacherName = '" + cpr.getTeacherName() + "', v1 = " + cpr.getScore1()
                    + ", v2 = " + cpr.getScore2() + ", v3 = " + cpr.getScore3() + ", crsPickScore = " + cpr.getCrsPickScore()
                    + " WHERE crsPickId = " + cpr.getCrsPickId();
            System.out.println(this.Query);
            if (1 == DBHelper.executeNonQuery(this.Query)) return true;
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public boolean deletePickRecord(Object obj) {
        this.cpr = (CrsPickRecord) obj;
        try {
            this.Query = "DELETE FROM CrsPickRecord WHERE CrsPickId = " + cpr.getCrsPickId();;
            System.out.println(this.Query);
            if (1 == DBHelper.executeNonQuery(this.Query)) return true;
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public Object queryPickRecord(Object obj, int opt) {
        this.cpr = (CrsPickRecord) obj;
        if (1 == opt) this.Query = "select * from CrsPickRecord";
        if (2 == opt)
            this.Query = "select * from CrsPickRecord where crsName = " + ((CrsPickRecord) obj).getCrsName()
                    + " and stuId = '" + ((CrsPickRecord) obj).getStuId() + "'";
        if (3 == opt) this.Query = "SELECT * FROM CrsPickRecord WHERE crsId = " + ((CrsPickRecord) obj).getCrsId();
        if (4 == opt)
            this.Query = "SELECT * FROM CrsPickRecord WHERE crsName = '" + ((CrsPickRecord) obj).getCrsName() + "'";
        if (5 == opt) this.Query = "SELECT * FROM CrsPickRecord WHERE stuId = '" + ((CrsPickRecord) obj).getStuId() + "'";
        if (6 == opt)
            this.Query = "SELECT * FROM CrsPickRecord WHERE stuName = '" + ((CrsPickRecord) obj).getStuName() + "'";
        if (7 == opt) this.Query = "select * from CrsPickRecord where crsId = '" + ((CrsPickRecord) obj).getCrsId()
                + "'and stuId = '" + ((CrsPickRecord) obj).getStuId() + "'";
        if (8 == opt) this.Query = "SELECT * FROM CrsPickRecord WHERE stuId = '" + ((CrsPickRecord) obj).getStuId()
                + "' AND stuName = '" + ((CrsPickRecord) obj).getStuName() + "'";
        if (9 == opt) this.Query = "select * from CrsPickRecord where teacherId = '" + ((CrsPickRecord) obj).getTeacherId()
                + "'and stuId = '" + ((CrsPickRecord) obj).getStuId() + "'" + "and crsId = '" + ((CrsPickRecord) obj).getCrsId() + "'";
        if (10 == opt) this.Query = "select * from CrsPickRecord where teacherId = '" + ((CrsPickRecord) obj).getTeacherId()
                + "'and stuName = '" + ((CrsPickRecord) obj).getStuName() + "'" + " 'and crsId = '" + ((CrsPickRecord) obj).getCrsId() + "'";
        if (11 == opt) this.Query = "select * from CrsPickRecord where teacherId = '" + ((CrsPickRecord) obj).getTeacherId()
                + "'and crsId = '" + ((CrsPickRecord) obj).getCrsId() + "'";
        try {
            System.out.println(this.Query);
            ResultSet rs = DBHelper.executeQuery(this.Query);

            if (rs != null) {
                if (rs.next()) {
                    System.out.println("CoursePickRepository successfully returned records:");
                    CrsPickRecord temp = new CrsPickRecord(rs.getInt("crsPickId"), rs.getInt("crsId"), rs.getString("crsName"), rs.getString("stuId"),
                            rs.getString("stuName"), rs.getString("teacherId"),rs.getString("teacherName"),
                            rs.getDouble("v1"),rs.getDouble("v2"),rs.getDouble("v3"), rs.getDouble("crsPickScore"));
                    //System.out.println(temp);
                    return rs;
                } else {
                    System.out.println("No records were found that meet the specified criteria.");
                }
            }
            else {
            System.out.println("The result set returned by CoursePickRepository is null.");
        }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;

    }

}


