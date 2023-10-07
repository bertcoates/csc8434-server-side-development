import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientImproved {

    private Socket clientSocket;
    private Scanner incoming;

    public ClientImproved(String ipAddress, int port) {
        try {
            clientSocket = new Socket(ipAddress, port);
            System.out.println("Connected to " + ipAddress + " on port " + port);
            System.out.println("Press Ctrl+C or Ctrl+D to quit");
            Scanner userInput = new Scanner(System.in);
            incoming = new Scanner(clientSocket.getInputStream());
            PrintWriter outgoing = new PrintWriter(clientSocket.getOutputStream(), true);

            while(userInput.hasNext()) {
                outgoing.println(userInput.nextLine());
                System.out.println(incoming.nextLine());
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ClientImproved client = new ClientImproved("127.0.0.1", 54321);
    }


}
