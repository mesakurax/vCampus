package module;

import entity.*;
import entityModel.CourseModel;
import entityModel.CrsPickRecordModel;

import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

public class CourseSystem {
    private CourseModel courseModel;

    private CrsPickRecordModel crsPickRecordModel;

    public CourseSystem(){
        this.courseModel=new CourseModel();
        this.crsPickRecordModel=new CrsPickRecordModel();
    }

    //用户发出选课请求
    public boolean Scoring(CrsPickRecord crsPickRecord) {
        try {
            crsPickRecordModel.modifyPickRecord(crsPickRecord);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    //根据用户信息显示该用户的课程选择记录，并返回一个包含课程信息的表格对象
    public CourseTable ChooseDisplay(User userInfo) {
        try {
            CrsPickRecord crsPickRecord = new CrsPickRecord(-1, -1, " ", userInfo.getId(), " ", " ","", 0,0,0,-1);
            ResultSet rs = (ResultSet) crsPickRecordModel.queryPickRecord(crsPickRecord, 5);
            Vector<CrsPickRecord> cprVector = new Vector<CrsPickRecord>();
            Vector<Course> courseVector = new Vector<Course>();
            int count = 0;

            // 检查 ResultSet 是否为空
            if (rs != null) {
                // 在结果集 rs 上循环迭代，使用 next() 方法依次访问每一行的数据
                do {
                    CrsPickRecord temp = new CrsPickRecord(rs.getInt("crsPickId"), rs.getInt("crsId"), rs.getString("crsName"),
                            rs.getString("stuId"), rs.getString("stuName"), rs.getString("teacherId"),rs.getString("teacherName"),
                            rs.getDouble("v1"),rs.getDouble("v2"),rs.getDouble("v3"), rs.getDouble("crsPickScore"));
                    cprVector.add(temp);
                    count++;
                } while (rs.next());

                // 打印每个选课记录的每个课程
                for (int i = 0; i < count; i++) {
                    courseVector.add(CrsDisplay_P2(cprVector, i)); // 添加每个选课记录的课程向量
                }
            CourseTable courseTable = new CourseTable(courseVector);
            System.out.println(courseTable);
            return courseTable;
        }
            System.out.println("ResultSet is empty");
            return null; // 或者根据需要返回适当的值
        } catch (Exception e) {
            System.out.println("Database exception");
            e.printStackTrace();
            return null;
        }
    }

    //根据给定的课程选择记录向量和索引，获取对应课程的详细信息，并返回一个课程对象
    public Course CrsDisplay_P2(Vector<CrsPickRecord> cpr, int count){
        try {
            Course course=new Course(cpr.get(count).getCrsId(),"", "", "", "", "", "-1", " ", -1,-1);
            ResultSet rs1= (ResultSet)courseModel.queryCourse(course,2);
            Course temp = new Course(rs1.getInt("crsId"), rs1.getString("crsName"), rs1.getString("crsTime"), rs1.getString("crsRoom"),
                    rs1.getString("crsDate"),rs1.getString("crsMajor"),rs1.getString("teacherId"),rs1.getString("teacherName"),
                    rs1.getInt("crsSize"),rs1.getInt("crsCSize"));
            return temp;

        }catch (Exception e)
        {
            System.out.println("Database exception");
            e.printStackTrace();
            return null;
        }
    }

    //显示所有可选择的课程，并返回一个包含课程信息的表格对象
    public  CourseTable CourseDis(){
        try {
            Course course=new Course(-1,"", "", "", "", "", "-1", " ", -1,-1);
            ResultSet rs= (ResultSet)courseModel.queryCourse(course,1);
            Vector<Course> courseVector=new Vector<Course>();
            do{
                Course temp = new Course(rs.getInt("crsId"), rs.getString("crsName"), rs.getString("crsTime"), rs.getString("crsRoom"),
                        rs.getString("crsDate"),rs.getString("crsMajor"),rs.getString("teacherId"),rs.getString("teacherName"),
                        rs.getInt("crsSize"),rs.getInt("crsCSize"));
                courseVector.add(temp);
            }
            while (rs.next());
            CourseTable courseTable=new CourseTable(courseVector);
            System.out.println(courseTable);
            return courseTable;
        }catch (Exception e)
        {
            System.out.println("Database exception");
            e.printStackTrace();
            return null;
        }

    }

    //检查学生是否选择了特定课程，根据给定的学生和课程信息判断是否选择，并返回布尔值。
    public boolean IfChoose(User userinfo,Course course){

        try {
            CrsPickRecord crsPickRecord=new CrsPickRecord(-1,course.getCrsId(),course.getCrsName(),userinfo.getId()," ",course.getTeacherId(), course.getTeacherName(), 0,0,0,-1);
            ResultSet rs= (ResultSet)crsPickRecordModel.queryPickRecord(crsPickRecord,7);
            if(rs!=null) {
                System.out.println("The student has selected the course");
                return true;
            }
            else
            {
                System.out.println("The student does not select the course");
                return false;
            }


        }catch (Exception e)
        {
            System.out.println("Database exception");
            e.printStackTrace();
            return false;
        }

    }


//根据学生和课程信息从数据库中添加选课记录，并返回操作是否成功的布尔值
    public boolean CprAdd(User userinfo,Course course){
        try {
            CrsPickRecord crsPickRecord=new CrsPickRecord(-1,course.getCrsId(),course.getCrsName(),userinfo.getId(),userinfo.getName(),course.getTeacherId(), course.getTeacherName(), 0,0,0,-1);
            crsPickRecordModel.addcrsPickRecord(crsPickRecord);
            return true;


        }catch (Exception e)
        {
            System.out.println("Database exception");
            e.printStackTrace();
            return false;
        }
    }

//根据学生和课程信息从数据库中删除选课记录，并返回操作是否成功的布尔值。
    public boolean CprDelete(User userinfo,Course course){
        try {
            String date=new Date().toString();
            System.out.println(date);
            CrsPickRecord crsPickRecord=new CrsPickRecord(-1,course.getCrsId(),course.getCrsName(),userinfo.getId(),userinfo.getName(),course.getTeacherName(), course.getTeacherName(),0,0,0, -1);
            ResultSet rs= (ResultSet)crsPickRecordModel.queryPickRecord(crsPickRecord,7);
            if (rs!=null) {
                CrsPickRecord temp = new CrsPickRecord(rs.getInt("crsPickId"), rs.getInt("crsId"), rs.getString("crsName"), rs.getString("stuId"),
                        rs.getString("stuName"), rs.getString("teacherId"), rs.getString("teacherName"),
                        rs.getDouble("v1"),rs.getDouble("v2"),rs.getDouble("v3"), rs.getDouble("crsPickScore"));

                crsPickRecordModel.deletePickRecord(temp);
                return true;
            } else {
                // 处理结果集为空的情况
                System.out.println("free");
                return false;
            }


        }catch (Exception e)
        {
            System.out.println("Database exception");
            e.printStackTrace();
            return false;
        }

    }

    //根据课程选择记录从数据库中删除选课记录，并返回操作是否成功的布尔值。
    public boolean CprDelete_P2(CrsPickRecord crsPickRecord){
        try {
            crsPickRecordModel.deletePickRecord(crsPickRecord);
            return true;
        }catch (Exception e)
        {
            System.out.println("Database exception");
            e.printStackTrace();
            return false;
        }

    }

//根据特定条件搜索课程，并返回包含符合条件的课程信息的表格对象
public CourseTable CourseSearch(Course course, int c) {
    try {
        Vector<Course> courseVector = new Vector<>();
        ResultSet rs = (ResultSet) courseModel.queryCourse(course, c);
        if (rs != null) {
            do{
                Course temp = new Course(rs.getInt("crsId"), rs.getString("crsName"), rs.getString("crsTime"), rs.getString("crsRoom"),
                        rs.getString("crsDate"), rs.getString("crsMajor"), rs.getString("teacherId"), rs.getString("teacherName"),
                        rs.getInt("crsSize"), rs.getInt("crsCSize"));
                courseVector.add(temp);
            }
            while (rs.next());
            if (!courseVector.isEmpty()) {
                CourseTable courseTable = new CourseTable(courseVector);
                System.out.println(courseTable);
                return courseTable;
            }
        }
        return null;
    } catch (Exception e) {
        System.out.println("Database exception");
        e.printStackTrace();
        return null;
    }
}

//数据库中删除课程，并返回操作是否成功的布尔值
    public boolean CourseDelete(Course course){
        try {
            courseModel.deleteCourse(course);
            return true;

        }catch (Exception e)
        {
            System.out.println("Database exception");
            e.printStackTrace();
            return false;
        }

    }

//检索所有课程选择记录，并返回一个包含课程选择记录的向量
    public  RecordTable CprDis(){
        try {
            CrsPickRecord crsPickRecord=new CrsPickRecord(-1,-1," ","-1"," "," ", "",0,0,0,-1);
            ResultSet rs= (ResultSet)crsPickRecordModel.queryPickRecord(crsPickRecord,1);
            Vector<CrsPickRecord> cprVector=new Vector<CrsPickRecord>();
            do
             {
                CrsPickRecord temp = new CrsPickRecord(rs.getInt("crsPickId"),rs.getInt("crsId"),rs.getString("crsName"),rs.getString("stuId"),
                        rs.getString("stuName"), rs.getString("teacherId"), rs.getString("teacherName"),
                        rs.getDouble("v1"),rs.getDouble("v2"),rs.getDouble("v3"), rs.getDouble("crsPickScore"));
                cprVector.add(temp);
            }
            while (rs.next());
            RecordTable recordTable=new RecordTable(cprVector);
            System.out.println(recordTable);
            return recordTable;
        }catch (Exception e)
        {
            System.out.println("Database exception");
            e.printStackTrace();
            return null;
        }

    }

//向数据库中添加课程，并返回操作是否成功的布尔值
    public boolean CourseAdd(Course course){
        return courseModel.addCourse(course);
    }

    //修改数据库中的课程信息，并返回操作是否成功的布尔值
    public boolean CourseModify(Course course){
            return courseModel.modifyCourse(course);
    }

    //根据特定条件搜索课程选择记录，并返回符合条件的课程选择记录向量
    public RecordTable CprSearch(CrsPickRecord crsPickRecord, int c) {
        try {
            ResultSet rs = (ResultSet) crsPickRecordModel.queryPickRecord(crsPickRecord, c);
            Vector<CrsPickRecord> cprVector = new Vector<CrsPickRecord>();
            if (rs != null) {
                do
                {
                    CrsPickRecord temp = new CrsPickRecord(rs.getInt("crsPickId"), rs.getInt("crsId"), rs.getString("crsName"), rs.getString("stuId"),
                            rs.getString("stuName"), rs.getString("teacherId"),rs.getString("teacherName"),
                            rs.getDouble("v1"),rs.getDouble("v2"),rs.getDouble("v3"), rs.getDouble("crsPickScore"));
                    cprVector.add(temp);
                }
                while (rs.next());
                if (!cprVector.isEmpty()) {
                    RecordTable recordTable = new RecordTable(cprVector);
                    System.out.println(recordTable);
                    return recordTable;
                }
            }

            return null;
        } catch (Exception e) {
            System.out.println("Database exception");
            e.printStackTrace();
            return null;
        }
    }
}