package threads;

import java.io.*;
import java.net.*;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(UDPThread.class.getName());
    private String name;
    private Executor executor;
    private DatagramSocket socket;
    private QuoteService quoteService;
    private byte[] buf = new byte[512];

    public UDPThread(Level level, int port, Executor executor, QuoteService quoteService) {
        this.name = "[" + UDPThread.class.getName() + "]";
        this.executor = executor;
        this.quoteService = quoteService;
        LOGGER.setLevel(level);

        try {
            this.socket = new DatagramSocket(port);
        } catch (IOException e) {
            LOGGER.severe(this.name + " " + e.toString());
        }
    }

    public void run() {
        try {
            int port = socket.getLocalPort();
            System.out.println("UDP Server is now listening on port " + port);
            LOGGER.info(this.name + " LISTENING - UDPThread - on port " + port);
            do {
                DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
                socket.receive(receivePacket);
                executor.execute(() -> {
                    try {
                        String sentence = new String(receivePacket.getData());

                        InetAddress address = receivePacket.getAddress();
                        int packetPort = receivePacket.getPort();
                        byte[] quoteBytes = quoteService.getQuote().getBytes();
                        LOGGER.info(this.name + " " + sentence + " " + address + " " + packetPort);

                        DatagramPacket sendPacket = new DatagramPacket(quoteBytes, quoteBytes.length, address, packetPort);
                        socket.send(sendPacket);

                        System.out.println("[UDP" + address + "] MESSAGE SENT: " + new String(quoteBytes));
                    } catch (IOException e) {
                        LOGGER.severe(this.name + " " + e.toString());
                    }
                });
            } while (true);
        } catch (IOException e) {
            LOGGER.severe(this.name + " " + e.toString());
        }
    }
    
}
