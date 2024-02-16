import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static DataOutputStream toServer = null;
    private static DataInputStream fromServer = null;
    private static final Scanner sc=new Scanner(System.in);
    private static Communication communicateWithServer=null;
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 8000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
            communicateWithServer=ClientCommunication.startCommunicationWithServer(fromServer,toServer);
        }
        catch (IOException ex) {
            System.out.println("socket wasn't created correctly");
            ex.printStackTrace();
        }
        boolean isLogin=false;
        while(true) {
            while (!isLogin){
                isLogin=communicateWithServer.isLogin();
            }
            communicateWithServer.getStudentGrades();
            communicateWithServer.getServerResponse();
            String choice = sc.nextLine();
            if (choice.equals("-1")) {
                communicateWithServer.sendChoiceToServer(choice);
                break;
            }
            communicateWithServer.sendChoiceToServer(choice);
            communicateWithServer.getClassStatistics();
        }
    }
}
