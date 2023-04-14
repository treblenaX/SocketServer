import java.util.concurrent.*;
import java.io.*;
import java.net.*;
import java.util.logging.*;
import threads.TCPSocket;

public class SocketServer {
    private static final Logger LOGGER = Logger.getLogger(SocketServer.class.getName());
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        String flag = (args.length > 1) ? args[1].toLowerCase() : "";

        switch (flag) {
            case "-i":
                LOGGER.setLevel(Level.INFO);
                printLogLevel();
                break;
            case "-f":
                LOGGER.setLevel(Level.FINE);
                printLogLevel();
                break;
            default:
                LOGGER.setLevel(Level.SEVERE);
                break;
        }
        ExecutorService executor = Executors.newFixedThreadPool(5);

        try {
            ServerSocket server = new ServerSocket(port);
            LOGGER.info("Server started on port " + port);

            // Handle socket connection
            do {
                Socket client = server.accept();
                Thread TCPThread = new TCPSocket(client, LOGGER.getLevel());
                executor.execute(TCPThread);
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printLogLevel() {
        System.out.println("Log level: " + LOGGER.getLevel());
    }
}