import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerImproved {

    private ServerSocket listener;
    private Socket serverSocket;
    private Scanner incoming;
    private PrintWriter outgoing;
    private String[] thoughts = {"To be or not to be...", "For a man to conquer himself is the first and noblest of all victories...", "Everything we hear is an opinion, not a fact. Everything we see is a perspective, not the truth..."};

    /**
     * Constructor to create the server with a persistent connection to the client.
     * Takes multiple inputs from the client and responds accordingly with a philosophical thought.
     *
     * @param port            - port number to listen on
     */
    public ServerImproved(int port) {
        try {
            listener = new ServerSocket(port);
            System.out.println("Ready and waiting for incoming connections on port " + port + "...");

            while (true) {
                serverSocket = listener.accept();
                System.out.println("Client connected!");
                incoming = new Scanner(serverSocket.getInputStream());
                // Maintains connection whilst there is user input
                while (incoming.hasNext()) {
                    outgoing = new PrintWriter(serverSocket.getOutputStream(), true);
                    outgoing.println(thoughts[Integer.parseInt(incoming.nextLine())]); // Reads the integer input from the client and responds with the relevant quote
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ServerImproved server = new ServerImproved(54321);
    }
}
