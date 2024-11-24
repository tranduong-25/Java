import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socketClient = new DatagramSocket();
            InetAddress địaChỉServer = InetAddress.getByName("localhost");
            byte[] gửiDữLiệu;
            byte[] nhậnDữLiệu = new byte[1024];
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Nhập n: ");
                String n = scanner.nextLine();
                gửiDữLiệu = n.getBytes();

                DatagramPacket góiGửi = new DatagramPacket(gửiDữLiệu, gửiDữLiệu.length, địaChỉServer, 8080);
                socketClient.send(góiGửi);

                DatagramPacket góiNhận = new DatagramPacket(nhậnDữLiệu, nhậnDữLiệu.length);
                socketClient.receive(góiNhận);
                String phảnHồi = new String(góiNhận.getData(), 0, góiNhận.getLength());

                System.out.println("Phản hồi từ server: " + phảnHồi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
