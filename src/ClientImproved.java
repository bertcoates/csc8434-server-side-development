import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientImproved {

    private Socket clientSocket;
    private Scanner incoming;

    /**
     * Constructor for creating client sockets with a persistent connection to the server
     * taking multiple inputs from the user and printing the response to the console
     *
     * @param ipAddress - IP Address of the server to connect to
     * @param port      - Port of the server to connect to
     */
    public ClientImproved(String ipAddress, int port) {
        try {
            clientSocket = new Socket(ipAddress, port);
            System.out.println("Connected to " + ipAddress + " on port " + port);
            System.out.println("Press Ctrl+C or Ctrl+D to quit");
            Scanner userInput = new Scanner(System.in);
            incoming = new Scanner(clientSocket.getInputStream()); // Connection to the servers output stream
            PrintWriter outgoing = new PrintWriter(clientSocket.getOutputStream(), true); // Writes to the server

            // Maintains connection until user cancels input stream
            while (userInput.hasNext()) {
                outgoing.println(userInput.nextLine());
                System.out.println(incoming.nextLine());
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to create an instance of the client, specifying the IP address and port number
    public static void main(String[] args) {
        ClientImproved client = new ClientImproved("127.0.0.1", 54321);
    }


}
