import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerImproved {

    private ServerSocket listener;
    private Socket serverSocket;
    private PrintWriter outgoing;

    public ServerImproved(int port, String messageToClient) {
        try {
            listener = new ServerSocket(port);
            System.out.println("Ready and waiting for incoming connections on port " + port + "...");

            while(true) {
                serverSocket = listener.accept();
                System.out.println("Client connected!");
                outgoing = new PrintWriter(serverSocket.getOutputStream(), true);
                outgoing.println(messageToClient);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

public static void main(String[] args) {
        ServerImproved server = new ServerImproved(54321, "Hi there!");
}
}
