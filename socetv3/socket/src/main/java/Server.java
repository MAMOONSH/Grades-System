import java.io.IOException;
import java.net.*;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) throws IOException {
        GradingSystemDAO database=new GradingSystemDAO();
        ServerSocket serverSocket = new ServerSocket(8000);
        ExecutorService executor = Executors.newCachedThreadPool();
        while (true) {
            Socket socket = serverSocket.accept();
            ServerThread serverThread=new ServerThread(socket,database);
            executor.execute(serverThread);
        }
    }
}
