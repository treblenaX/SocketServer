package threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuoteService {
    private final Logger LOGGER = Logger.getLogger(QuoteService.class.getName());
    private List<String> quotesList;
    private Random rand;

    public QuoteService(Level level) {
        super();
        this.rand = new Random();
        this.quotesList = new ArrayList<String>();
        this.LOGGER.setLevel(level);
    }

    public String getQuote() {
        if (this.rand == null) {
            this.rand = new Random();
        }
        
        int randIndex = rand.nextInt(quotesList.size());
        return this.quotesList.get(randIndex);
    }

    public void loadQuoteFromFile(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();
            while (line != null) {
                this.quotesList.add(line.replace("\\n", "\n"));
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
    }
}