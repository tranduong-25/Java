import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socketServer = new DatagramSocket(8080);
            byte[] nhậnDữLiệu = new byte[1024];
            byte[] gửiDữLiệu;

            System.out.println("Server đang chạy...");

            while (true) {
                // Nhận dữ liệu từ client
                DatagramPacket góiNhận = new DatagramPacket(nhậnDữLiệu, nhậnDữLiệu.length);
                socketServer.receive(góiNhận);
                String dữLiệu = new String(góiNhận.getData(), 0, góiNhận.getLength());

                // Parse n và kiểm tra
                int n;
                try {
                    n = Integer.parseInt(dữLiệu.trim());
                } catch (NumberFormatException e) {
                    String lỗi = "Sai! Vui lòng nhập số nguyên dương.";
                    gửiDữLiệu = lỗi.getBytes();
                    InetAddress địaChỉClient = góiNhận.getAddress();
                    int cổngClient = góiNhận.getPort();
                    DatagramPacket góiGửi = new DatagramPacket(gửiDữLiệu, gửiDữLiệu.length, địaChỉClient, cổngClient);
                    socketServer.send(góiGửi);
                    continue;
                }

                StringBuilder kếtQuả = new StringBuilder();
                if (n > 0) {
                    for (int i = 2; i < n; i++) {
                        if (làNguyênTố(i) && i % 5 == 0) {
                            kếtQuả.append(i).append(" ");
                        }
                    }
                    if (kếtQuả.length() == 0) {
                        kếtQuả.append("Không có số nào thỏa mãn.");
                    }
                } else {
                    kếtQuả.append("Sai! Vui lòng nhập số nguyên dương.");
                }

                gửiDữLiệu = kếtQuả.toString().getBytes();
                InetAddress địaChỉClient = góiNhận.getAddress();
                int cổngClient = góiNhận.getPort();
                DatagramPacket góiGửi = new DatagramPacket(gửiDữLiệu, gửiDữLiệu.length, địaChỉClient, cổngClient);
                socketServer.send(góiGửi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm kiểm tra số nguyên tố
    private static boolean làNguyênTố(int số) {
        if (số < 2) return false;
        for (int i = 2; i * i <= số; i++) {
            if (số % i == 0) return false;
        }
        return true;
    }
}
