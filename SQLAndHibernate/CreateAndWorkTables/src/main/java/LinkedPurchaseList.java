import javax.persistence.*;
import java.io.Serializable;

@Entity
public class LinkedPurchaseList implements Serializable {
    @EmbeddedId
    private LinkedPurchaseListKey id;

    @Column(name = "student_id", insertable = false, updatable = false, nullable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false, nullable = false)
    private int courseId;

    public int getStudentId() {
        return studentId;
    }

    public LinkedPurchaseListKey getId() {
        return id;
    }

    public void setId(LinkedPurchaseListKey id) {
        this.id = id;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
