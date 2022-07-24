import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int PORT = Settings.getInt("PORT");

        try(
                ServerSocket ss = new ServerSocket(PORT);
                Socket socket = ss.accept();
                DataInputStream dIn=new DataInputStream(socket.getInputStream());
                DataOutputStream dOut = new DataOutputStream(socket.getOutputStream())) {
            String str = dIn.readUTF();
            dOut.writeUTF("Server: "+str);
            dOut.flush();
            System.out.println("Done");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
