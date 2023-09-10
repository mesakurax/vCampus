package entityModel;

import entity.Course;
import sqlutil.DBHelper;

import java.sql.ResultSet;

public class CourseModel {
    private String query = "";
    private Course crs = null;

    public CourseModel() {

    }

    public boolean addCourse(Object obj) {
        this.crs  = (Course) obj;
        try {
            this.query = "INSERT INTO course (crsId, crsName, crsTime, crsRoom, crsDate, crsMajor, teacherId, teacherName, crsSize, crsCSize) " +
                    "VALUES (" + crs .getCrsId() + ",'" + crs .getCrsName() + "','" + crs .getCrsTime() + "','" + crs .getCrsRoom()
                    + "','" + crs .getCrsDate() + "','" + crs .getMajor() + "','" + crs .getTeacherId() + "','" + crs  .getTeacherName()
                    + "'," + crs .getCrsSize() + "," + crs .getCrsCSize() + ")";

            System.out.println(this.query);
            if (1 == DBHelper.executeNonQuery(this.query)) {
                return true;
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public boolean modifyCourse(Object obj) {
        this.crs  = (Course) obj;
        try {
            this.query = "UPDATE course SET crsName = '" + crs .getCrsName() + "', crsRoom = '" + crs .getCrsRoom() + "', crsSize = " + crs .getCrsSize()
                    + ", crsMajor = '" + crs .getMajor() + "', teacherId = '" + crs .getTeacherId() + "', teacherName = '" + crs .getTeacherName()
                    + "', crsTime = '" + crs .getCrsTime() + "', crsDate = '" + crs .getCrsDate() + "', crsCSize = " + crs .getCrsCSize() + " WHERE crsId = " +crs .getCrsId();
            System.out.println(this.query);
            if (1 == DBHelper.executeNonQuery(this.query)) {
                return true;
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public boolean deleteCourse(Object obj) {
        this.crs  = (Course) obj;
        try {
            this.query = "DELETE FROM course WHERE crsId = " + crs .getCrsId();
            System.out.println(this.query);
            if (1 == DBHelper.executeNonQuery(this.query)) {
                return true;
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public Object queryCourse(Object obj, int opt) {
        this.crs  = (Course) obj;
        if (1 == opt) {
            this.query = "SELECT * FROM course";//返回所有课程数据
        } else if (2 == opt) {
            this.query = "SELECT * FROM course WHERE crsId = " + ((Course) obj).getCrsId();
        } else if (3 == opt) {
            this.query = "SELECT * FROM course WHERE crsName = '" + ((Course) obj).getCrsName() + "'";
        } else if (4 == opt) {
            this.query = "SELECT * FROM course WHERE teacherId = '" + ((Course) obj).getTeacherId() + "'";
        } else if (5 == opt) {
            this.query = "SELECT * FROM course WHERE teacherId = '" + ((Course) obj).getTeacherId()
                    + "' AND crsId = '" + ((Course) obj).getCrsId() + "'";
        }
        else if (6 == opt) {
            this.query = "SELECT * FROM course WHERE crsId = '" + ((Course) obj).getCrsId()
                    + "' AND crsName = '" + ((Course) obj).getCrsName() + "'";
        }
        else if (7 == opt) {
            this.query = "SELECT * FROM course WHERE teacherId = '" + ((Course) obj).getTeacherId()
                    + "' AND crsName = '" + ((Course) obj).getCrsName() + "'";
        }
        else if (8 == opt) {
            this.query = "SELECT * FROM course WHERE teacherName = '" + ((Course) obj).getTeacherName() +"'";
        }

        try {
            System.out.println(this.query);
            ResultSet rs = DBHelper.executeQuery(this.query);
            if (rs != null) {
                if (rs.next()) {
                    Course temp = new Course(rs.getInt("crsId"), rs.getString("crsName"), rs.getString("crsTime"), rs.getString("crsRoom"),
                            rs.getString("crsDate"),rs.getString("crsMajor"),rs.getString("teacherId"),rs.getString("teacherName"),
                            rs.getInt("crsSize"),rs.getInt("crsCSize"));
                    System.out.println("CoursePickRepository successfully returned records:");
                   // System.out.println(temp);
                    return rs;
                } else {
                    System.out.println("No records were found that meet the specified criteria.");
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
    }

}

