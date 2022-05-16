import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int PORT = 2211;
    static WhiteList whiteList;

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        whiteList = new WhiteList();

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        while (true) {
            try {
                clientSocket = serverSocket.accept();

                String address = clientSocket.getInetAddress().toString();
                System.out.println(address);

                if(whiteList.allowedClients.contains(address)){
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println("Hi allowed client");
                    out.println("QUIT");
                    out.flush();
                }
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
        }
    }
}
