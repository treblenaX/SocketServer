import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SimpleUDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName("localhost");

        String sentence = "";
        byte[] sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 17);

        clientSocket.send(sendPacket);

        byte[] receiveData = new byte[512];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        System.out.println(new String(receivePacket.getData()));

        clientSocket.close();
    }
}