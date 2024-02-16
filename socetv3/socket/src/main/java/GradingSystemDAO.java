import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GradingSystemDAO {
    private DataSource dataSource;
    public GradingSystemDAO(){
        try{
            dataSource = getDataSource();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private DataSource getDataSource() throws SQLException {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName("localhost");
        mysqlDataSource.setDatabaseName("gradingDB");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("123456");
        mysqlDataSource.setUseSSL(false);
        mysqlDataSource.setAllowPublicKeyRetrieval(true);
        return mysqlDataSource;
    }
    public boolean login(Student student){
        return login(student.getId(),student.getPassword());
    }
    private boolean login(String userID,String password){
        boolean login =false;
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from students where id=\""+userID+"\" and password=\""+password+"\";");
            while (rs.next()) {
                login=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login;
    }
    public String getUserName(Student student){
        return getUserName(student.getId());
    }
    private String getUserName(String userID){
        String name="";
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select name from students where id="+userID+";");
            while (rs.next()) {
                name=rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }
    public List<StudentCourse> displayGrades(Student student){
        return displayGrades(student.getId());
    }
    private List<StudentCourse> displayGrades(String userID){
        List<StudentCourse> studentCourses =new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select courses.name as course,courses.id as course_id,enrollment.grade \n" +
                    "from students,courses,enrollment\n" +
                    "where enrollment.student_id=students.id and enrollment.course_id=courses.id and students.id="+userID+";");
            while (rs.next()) {
                StudentCourse studentCourse=StudentCourse.createStudentCourse(rs.getInt("course_id")
                        ,rs.getString("course"),rs.getInt("grade"));
                studentCourses.add(studentCourse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentCourses;
    }
    public List<ClassGrade> courseGrades(Student student, String courseID){
        return courseGrades(student.getId(),courseID);
    }
    private List<ClassGrade> courseGrades(String userID, String courseID){
        List<ClassGrade> classGrades=new ArrayList<>();
        if(!validCourseID(userID, courseID)){
            return classGrades;
        }
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select students.name as student_name,courses.name as course,enrollment.grade \n" +
                    "from students,courses,enrollment\n" +
                    "where enrollment.student_id=students.id " +
                    "and enrollment.course_id=courses.id and enrollment.course_id="+courseID+" order by grade;\n");
            while (rs.next()) {
                ClassGrade classGrade=ClassGrade.classGradeForStudent(
                        rs.getString("student_name"),rs.getString("course")
                        ,rs.getInt("grade"));
                classGrades.add(classGrade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classGrades;
    }
    private boolean validCourseID(String userID, String courseID){
        boolean isValid=false;
        try (Connection conn = dataSource.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select students.name as student_name,courses.name as course,enrollment.grade \n" +
                    "from students,courses,enrollment\n" +
                    "where enrollment.student_id=students.id and enrollment.course_id=courses.id " +
                    "and enrollment.course_id=\""+courseID+"\" and students.id="+userID+";\n");
            while (rs.next()) {
                isValid=true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }
}
