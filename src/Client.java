import java.io.*;
import java.net.Socket;

public class Client {


    public static void main(String[] args) {
        BufferedReader bRead = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        int PORT = Settings.getInt("PORT");
        try (
            Socket socket = new Socket("localhost", PORT);
            DataOutputStream dOut =new DataOutputStream(socket.getOutputStream());
            DataInputStream dIn = new DataInputStream(socket.getInputStream())) {
                userInput = bRead.readLine();
                dOut.writeUTF(userInput);
                dOut.flush();
                String s = dIn.readUTF();
                System.out.println(s);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }

}
