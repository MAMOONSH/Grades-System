package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/studentGrades")
public class StudentGradesServlet extends HttpServlet {
    private GradingSystemDAO database = null;
    @Override
    public void init() throws ServletException {
        super.init();
        database=new GradingSystemDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        Student student = getStudent(request);
        setRequestAttribute(request, student);
        request.getRequestDispatcher("/WEB-INF/views/studentGrades.jsp").forward(
                request, response);
    }
    private Student getStudent(HttpServletRequest request) {
        HttpSession session= request.getSession();
        Student student=(Student) session.getAttribute("student");
        return student;
    }
    private void setRequestAttribute(HttpServletRequest request, Student student) {
        List<StudentCourse> studentCourses=database.displayGrades(student);
        request.setAttribute("grades",studentCourses);
    }
}