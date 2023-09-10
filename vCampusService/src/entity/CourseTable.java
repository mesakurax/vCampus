package entity;
import java.io.Serializable;
import java.util.Vector;
public class CourseTable implements Serializable{
    private Vector<Course> courseVector;
    private static final long serialVersionUID = 3565691700555410371L;

    public Vector<Course> getCourseVector() {
        return courseVector;
    }

    public void setCourseVector(Vector<Course> courseVector) {
        this.courseVector = courseVector;
    }


    public CourseTable(Vector<Course> cv) {
        this.courseVector = cv;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Course course : courseVector) {
            sb.append(course.toString()).append("\n");
        }
        return sb.toString();
    }
}
