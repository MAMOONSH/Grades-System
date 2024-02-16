import java.io.IOException;

public interface Communication {
    boolean isLogin();
    void getStudentGrades();
    void getServerResponse();
    void sendChoiceToServer(String choice);
    void getClassStatistics();
}
