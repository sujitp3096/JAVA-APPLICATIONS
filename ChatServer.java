import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for client...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String clientMsg = in.readLine();
                System.out.println("Client: " + clientMsg);

                System.out.print("You: ");
                String reply = keyboard.readLine();
                out.println(reply);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
