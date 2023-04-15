import java.util.concurrent.*;
import java.io.*;
import java.net.*;
import java.util.logging.*;
import threads.*;

public class SocketServer {
    private static final Logger LOGGER = Logger.getLogger(SocketServer.class.getName());
    private static int PORT = 17;
    public static void main(String[] args) {
        String flag = (args.length > 0) ? args[0].toLowerCase() : "";

        switch (flag) {
            case "-i":
                LOGGER.setLevel(Level.INFO);
                printLogLevel();
                break;
            default:
                LOGGER.setLevel(Level.SEVERE);
                break;
        }

        LOGGER.info("CREATED - ExecutorService");
        ExecutorService executor = Executors.newFixedThreadPool(5);
        QuoteService quoteService = new QuoteService(LOGGER.getLevel());
        quoteService.loadQuoteFromFile("./threads/MontyPythonQuotes.txt");
        quoteService.loadQuoteFromFile("./threads/PrincessBrideQuotes.txt");

        LOGGER.info("EXECUTE - TCPThread");
        executor.execute(new TCPThread(LOGGER.getLevel(), PORT, executor, quoteService));
        executor.execute(new UDPThread(LOGGER.getLevel(), PORT, executor, quoteService));
    }

    private static void printLogLevel() {
        System.out.println("Log level: " + LOGGER.getLevel());
    }
}