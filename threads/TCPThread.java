package threads;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import threads.QuoteService;

public class TCPThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(TCPThread.class.getName());
    private String name;
    private Executor executor;
    private ServerSocket serverSocket;
    private Random random;
    private QuoteService quoteService;

    public TCPThread(Level level, int port, Executor executor, QuoteService quoteService) {
        this.name = "[" + TCPThread.class.getName() + "]";
        this.random =  new Random();
        this.executor = executor;
        this.quoteService = quoteService;
        LOGGER.setLevel(level);

        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            LOGGER.severe(this.name + " " + e.toString());
        }
    }

    public void run() {
        try {
            int port = serverSocket.getLocalPort();
            System.out.println("TCP Server is now listening on port " + port);
            LOGGER.info(this.name + " LISTENING - TCPThread - on port " + port);
            do {
                Socket client = serverSocket.accept();
                executor.execute(() -> {
                    try {
                        LOGGER.info("[" + client.getInetAddress() + "] CONNECTED - TCPThread");

                        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                        String quote = quoteService.getQuote();
                        out.println(quote);
                        LOGGER.info("[" + client.getInetAddress() + "] SENT - TCPThread - " + quote);

                        out.close();
                        client.close();
                        LOGGER.info("[" + client.getInetAddress() + "] DISCONNECTED - TCPThread");
                        
                        System.out.println("[TCP" + client.getInetAddress() + "] MESSAGE SENT: " + quote);
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
