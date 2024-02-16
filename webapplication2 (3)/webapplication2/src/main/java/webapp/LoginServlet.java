package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	private GradingSystemDAO database = null;


	@Override
	public void init() throws ServletException {
		super.init();
		database=new GradingSystemDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
				request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		boolean isValidUser=false;
		Student student=null;
		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
		if(checkIfValidInput(userID, password)){
			isValidUser =false;
		}
		else {
			student =Student.creatStudent(userID, password);
			isValidUser= database.login(student);
		}

		if (isValidUser) {
			setStudentName(student);
			setSessionAttribute(request, student);
			setRequestAttribute(request, student);
			request.getRequestDispatcher("/WEB-INF/views/studentGrades.jsp").forward(
					request, response);
		} else {
			request.setAttribute("errorMessage", "Invalid Credentials!!");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
					request, response);
		}
	}

	private void setRequestAttribute(HttpServletRequest request, Student student) {
		request.setAttribute("student", student);
		List<StudentCourse> studentCourses=database.displayGrades(student);
		request.setAttribute("grades",studentCourses);
	}

	private void setSessionAttribute(HttpServletRequest request, Student student) {
		HttpSession session= request.getSession();
		session.setAttribute("student", student);
	}

	private void setStudentName(Student student) {
		String userName=database.getUserName(student);
		student.setName(userName);
	}

	private boolean checkIfValidInput(String userID, String password) {
		return userID == null || password == null || userID.isEmpty() || password.isEmpty();
	}
}