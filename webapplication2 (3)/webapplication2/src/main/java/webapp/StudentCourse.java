package webapp;

import java.util.Objects;

public class StudentCourse {
    private int id;
    private String courseName;
    private int grade;

    public static StudentCourse createStudentCourse(int id,String courseName,int grade){
        return new StudentCourse(id, courseName,grade);
    }

    private StudentCourse(int id,String courseName,int grade){
        this.id=id;
        this.courseName=courseName;
        this.grade=grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "ID=" + id +
                ", courseName='" + courseName + '\'' +
                ", grade=" + grade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourse that = (StudentCourse) o;
        return id == that.id && grade == that.grade && Objects.equals(courseName, that.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, grade);
    }
}
