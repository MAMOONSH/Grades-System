import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ServerThread implements Runnable {
    private String studentGrades ="";
    private boolean isLogin=false;
    private final GradingSystemDAO database;
    private Student student=null;
    DataInputStream inputFromClient;
    DataOutputStream outputToClient;
    ServerThread(Socket socket, GradingSystemDAO database) {
        this.database=database;
        try {
            inputFromClient = new DataInputStream(
                    socket.getInputStream());
            outputToClient = new DataOutputStream(
                    socket.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void run() {
        try {

            while (true) {
                while(!isLogin) {
                    logToTheSystem(inputFromClient, outputToClient);
                }
                System.out.println(student.getName());
                outputToClient.writeUTF(studentGrades);
                outputToClient.writeUTF("please enter course ID to see details or enter -1 to exit");
                String choice = inputFromClient.readUTF();
                if(choice.equals("-1"))
                    break;
                List<ClassGrade> classGrades=database.courseGrades(student,choice);
                if(classGrades.isEmpty()){
                    outputToClient.writeUTF("invalid course ID or access denied");
                    continue;
                }
                sendClassGradesToClient(outputToClient, classGrades);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void logToTheSystem(DataInputStream inputFromClient, DataOutputStream outputToClient) throws IOException {
        getStudentData(inputFromClient, outputToClient);
        if (database.login(student)) {
            isLogin = true;
            List<StudentCourse> studentCourses;
            studentCourses =database.displayGrades(student);
            studentGrades=getStudentGradesAsString(studentCourses);
            student.setName(database.getUserName(student));
        }
        outputToClient.writeBoolean(isLogin);
    }
    private void getStudentData(DataInputStream inputFromClient, DataOutputStream outputToClient) throws IOException {
        outputToClient.writeUTF("enter userID");
        String userID = inputFromClient.readUTF();
        outputToClient.writeUTF("enter password");
        String password = inputFromClient.readUTF();
        student=Student.creatStudent(userID,password);
    }
    private String getStudentGradesAsString( List<StudentCourse> studentCourses){
        StringBuilder result= new StringBuilder("CourseID\t Course Name\t Course Grade\t\n");
        for(int i=0;i<studentCourses.size();i++){
            result.append(studentCourses.get(i).getId()).append("\t\t\t\t");
            result.append(studentCourses.get(i).getCourseName()).append("\t\t\t");
            result.append(studentCourses.get(i).getGrade());
            result.append("\n");
        }
        return result.toString();
    }
    private void sendClassGradesToClient(DataOutputStream outputToClient, List<ClassGrade> classGrades) throws IOException {
        String classStatisticsResult=getClassGradesAsString(classGrades);
        ClassStatistics classStatistics=ClassStatistics.analyzeTheGrades(classGrades);
        classStatisticsResult+=getClassStatisticsAsString(classStatistics);
        outputToClient.writeUTF(classStatisticsResult);
    }
    private String getClassGradesAsString( List<ClassGrade> classGrades){
        StringBuilder result= new StringBuilder("Student Name\t Course Name\t Grade\t\n");
        for(int i=0;i<classGrades.size();i++){
            result.append(classGrades.get(i).getStudentName()).append("\t\t\t");
            result.append(classGrades.get(i).getCourseName()).append("\t\t\t");
            result.append(classGrades.get(i).getGrade());
            result.append("\n");
        }
        return result.toString();
    }
    private String getClassStatisticsAsString(ClassStatistics classStatistics){
        return   "Average is\t"+classStatistics.getAverage()+"\n"
                +"Median is \t"+classStatistics.getMedian()+"\n"
                +"Max is    \t"+classStatistics.getMax()+"\n"
                +"Min is    \t"+classStatistics.getMin()+"\n";
    }
}
