package entity;
import java.io.Serializable;


public class CrsPickRecord implements Serializable {

        private Integer crsPickId;

        public Integer getCrsPickId() {
            return crsPickId;
        }

        public void setCrsPickId(Integer crsPickId) {
            this.crsPickId = crsPickId;
        }

        private Integer crsId;

        private String crsName;

        private String teacherId;

        private String teacherName;

        private String stuId;

        private String stuName;

        private double crsPickScore;

        private double score1;
        private double score2;
        private double score3;

        public String getCrsName() {
        return crsName;
    }

        public void setCrsName(String crsName) {
        this.crsName = crsName;
    }

        public Integer getCrsId() {
        return crsId;
    }

        public void setCrsId(Integer crsId) {
        this.crsId = crsId;
    }

        public String getTeacherId() {
        return teacherId;
    }

        public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;}

        public String getTeacherName() {
        return teacherName;
    }

        public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;}

        public String getStuId() {
        return stuId;
    }

        public void setStuId(String stuId) {
        this.stuId = stuId;
    }

        public String getStuName() {
            return stuName;
        }

        public void setStuName(String stuName) {
            this.stuName = stuName;
        }

        public double getCrsPickScore() {
            return crsPickScore;
        }

        public void setCrsPickScore(double crsPickScore) {
            this.crsPickScore = crsPickScore;
        }

        public double getScore1() {
        return score1;
    }

         public void setScore1(double value) {
        this.score1 = value;
    }
    public double getScore2() {
        return score2;
    }

    public void setScore2(double value) {
        this.score2 = value;
    }
    public double getScore3() {
        return score3;
    }

    public void setScore3(double value) {
        this.score3 = value;
    }


        public CrsPickRecord(Integer crsPickId, Integer crsId, String crsName, String stuId, String stuName, String teacherId, String teacherName, double v1, double v2, double v3, double crsPickScore) {
            setCrsPickId(crsPickId);
            setCrsName(crsName);
            setCrsId(crsId);
            setTeacherId(teacherId);
            setTeacherName(teacherName);
            setStuId(stuId);
            setStuName(stuName);
            setScore1(v1);
            setScore2(v2);
            setScore3(v3);
            setCrsPickScore(crsPickScore);
        }

    @Override
    public String toString() {
        return "CoursePickId:" + crsPickId +
                " CourseId:" + crsId +
                " CourseName:" + crsName +
                " TeacherId:" + teacherId +
                " TeacherName:" + teacherName +
                " StudentId:" + stuId +
                " StudentName:" + stuName +
                " CoursePickScore:" + crsPickScore+"\n";
    }
}
