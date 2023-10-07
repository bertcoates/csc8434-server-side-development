import java.io.IOException;
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
            incoming = new Scanner(clientSocket.getInputStream());
            System.out.println(incoming.nextLine());
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
