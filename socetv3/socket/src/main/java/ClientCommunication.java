import java.io.*;
import java.util.Scanner;
public class ClientCommunication implements Communication{
    private boolean isLogin = false;
    private final DataOutputStream toServer;
    private final DataInputStream fromServer;
    private Scanner sc=null;

    public static ClientCommunication startCommunicationWithServer(DataInputStream fromServer, DataOutputStream toServer) throws IOException {
        return new ClientCommunication(fromServer, toServer);
    }

    private ClientCommunication(DataInputStream fromServer, DataOutputStream toServer){
        this.fromServer=fromServer;
        this.toServer=toServer;
        sc=new Scanner(System.in);
    }

    public boolean isLogin() {
        insertUserData();
        insertUserData();
        try {
            isLogin = fromServer.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!isLogin)
            System.out.println("wrong username or password");
        return isLogin;
    }

    private void insertUserData(){
        try {
            String serverQuestion = fromServer.readUTF();
            System.out.println(serverQuestion);
            String userData = sc.nextLine();
            toServer.writeUTF(userData);
            toServer.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getStudentGrades() {
        try {
            String studentGrade = fromServer.readUTF();
            System.out.println(studentGrade);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getServerResponse(){
        try {

            String serverResponse = fromServer.readUTF();
            System.out.println(serverResponse);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendChoiceToServer(String choice){
        try {
            toServer.writeUTF(choice);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void getClassStatistics() {
        try{
        String classStatisticsResult = fromServer.readUTF();
        System.out.println(classStatisticsResult);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
