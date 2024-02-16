import java.util.Objects;

public class ClassGrade {
    private String studentName;
    private String courseName;
    private int grade;

    public static ClassGrade classGradeForStudent(String studentName, String courseName, int grade){
        return  new ClassGrade(studentName, courseName, grade);
    }

    private ClassGrade(String studentName, String courseName, int grade) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.grade = grade;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
        return "ClassGrades{" +
                "studentName='" + studentName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", Grade=" + grade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassGrade that = (ClassGrade) o;
        return grade == that.grade && Objects.equals(studentName, that.studentName) && Objects.equals(courseName, that.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, courseName, grade);
    }
}
