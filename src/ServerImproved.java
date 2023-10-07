import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerImproved {

    private ServerSocket listener;
    private Socket serverSocket;
    private PrintWriter outgoing;

    public ServerImproved(int port, String[] messageToClient) {
        //String[] thoughts = {"To be or not to be...", "For a man to conquer himself is the first and noblest of all victories...",
                //"Everything we hear is an opinion, not a fact. Everything we see is a perspective, not the truth..."};
        try {
            listener = new ServerSocket(port);
            System.out.println("Ready and waiting for incoming connections on port " + port + "...");

            while(true) {
                serverSocket = listener.accept();
                System.out.println("Client connected!");
                Scanner incoming = new Scanner(serverSocket.getInputStream());
                while(incoming.hasNext()) {
                    outgoing = new PrintWriter(serverSocket.getOutputStream(), true);
                    outgoing.println(messageToClient[Integer.parseInt(incoming.nextLine())]);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

public static void main(String[] args) {
    String[] thoughts = {"To be or not to be...", "For a man to conquer himself is the first and noblest of all victories...",
            "Everything we hear is an opinion, not a fact. Everything we see is a perspective, not the truth..."};
    ServerImproved server = new ServerImproved(54321, thoughts);
}
}
