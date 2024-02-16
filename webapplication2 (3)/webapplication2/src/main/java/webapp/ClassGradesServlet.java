package webapp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/courseGrades")
public class ClassGradesServlet extends HttpServlet {
    private GradingSystemDAO database = null;
    @Override
    public void init() throws ServletException {
        super.init();
        database=new GradingSystemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = getStudent(request);
        String courseID = request.getParameter("courseID");
        if (isValidCourseIDInput(courseID)) {
            handleNotValidCourseID(request, response);
        }
        List<ClassGrade> classGrades = database.courseGrades(student, courseID);
        if (classGrades.isEmpty()) {
            handleNotValidCourseID(request, response);
        } else {
            handleValidCourseID(request, response, classGrades);

        }
    }

    private Student getStudent(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");
        return student;
    }

    private boolean isValidCourseIDInput(String courseID) {
        return courseID == null || courseID.isEmpty();
    }

    private void handleNotValidCourseID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", "access denied or invalid class id");
        request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(
                request, response);
    }

    private void handleValidCourseID(HttpServletRequest request, HttpServletResponse response, List<ClassGrade> classGrades) throws ServletException, IOException {
        ClassStatistics classStatistics = ClassStatistics.analyzeTheGrades(classGrades);
        request.setAttribute("classGrades", classGrades);
        request.setAttribute("statistics", classStatistics);
        request.getRequestDispatcher("/WEB-INF/views/courseGrades.jsp").forward(
                request, response);
    }
}
