package threads;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPSocket extends Thread {
    private static final Logger LOGGER = Logger.getLogger(TCPSocket.class.getName());
    private static final String[] QUOTES = new String[]{
        "Grandpa: Westley didn’t reach his destination. His ship was attacked by the Dread Pirate Roberts, who never left captives alive. When Buttercup got the news that Westley was murdered…\nThe Grandson: Murdered by pirates is good.",
        "Fezzik: I just want you to feel you’re doing well. I hate for people to die embarrassed.",
        "Buttercup: You mock my pain!\nMan in Black: Life is pain, Highness. Anyone who says differently is selling something.",
        "Inigo Montoya: You seem a decent fellow. I hate to kill you.\nThe Man in Black: You seem a decent fellow. I hate to die.",
        "Westley: To the pain means the first thing you will lose will be your feet below the ankles. Then your hands at the wrists. Next your nose.\nPrince Humperdinck: And then my tongue I suppose, I killed you too quickly the last time. A mistake I don’t mean to duplicate tonight.\nWestley: I wasn’t finished. The next thing you will lose will be your left eye followed by your right.\nPrince Humperdinck: And then my ears, I understand let’s get on with it.\nWestley: Wrong! Your ears you keep and  I’ll tell you why. So that every shriek of every child at seeing your hideousness will be yours to cherish. Every babe that weeps at your approach, every woman who cries out, “Dear God! What is that thing,” will echo in your perfect ears. That is what to the pain means. It means I leave you in anguish, wallowing in freakish misery forever.",
        "Miracle Max and Valerie: Have fun stormin’ da castle.",
        "Man in Black: I do not envy you the headache you will have when you awake. But for now, rest well and dream of large women.",
        "Vizzini: Finish him. Finish him, your way.\nFezzik: Oh good, my way. Thank you Vizzini… what’s my way?\nVizzini: Pick up one of those rocks, get behind a boulder, in a few minutes the man in black will come running around the bend, the minute his head is in view, hit it with the rock.\nFezzik: My way’s not very sportsman-like.",
        "Westley: There’s a shortage of perfect breasts in this world. It would be a pity to damage yours.",
        "Vizzini: He didn’t fall?! Inconceivable!\nInigo Montoya: You keep using that word. I do not think it means what you think it means.",
        "Vizzini: You fell victim to one of the classic blunders—the most famous of which is, “Never get involved in a land war in Asia”—but only slightly less well-known is this: “Never go against a Sicilian when death is on the line”! Ha ha ha! Ha ha ha! Ha ha ha…[thunk].",
        "Inigo Montoya: Hello! My name is Inigo Montoya! You killed my father! Prepare to die!",
        "Vizzini: No more rhymes now, I mean it.\nFezzik: Anybody want a peanut?",
        "Inigo Montoya: Who are you?\nMan in Black: No one of consequence.\nInigo Montoya: I must know.\nMan in Black: Get used to disappointment.",
        "Buttercup: We'll never survive.\nWestley: Nonsense. You're only saying that because no one ever has.",
        "Prince Humperdinck: Surrender.\nWestley: You mean you wish to surrender to me? Very well, I accept.",
        "Miracle Max: Go away or I'll call the Brute Squad.\nFezzik: I'm on the Brute Squad.",
        "Dread Pirate Roberts: Good night, Westley. Good work. Sleep well. I'll most likely kill you in the morning.",
        "Westley: We are men of action, lies do not become us.",
        "Buttercup: You're the Dread Pirate Roberts, admit it!\nMan in Black: With pride. What can I do for you?\nButtercup: You can die slowly, cut into a thousand pieces.\nMan in Black: Tsk, tsk. That's hardly complementary, Highness. Why loose your venom on me?\nButtercup: You killed my love.\nMan in Black: It's possible. I kill a lot of people.",
        "Prince Humperdinck: [sincerely] Tyrone, you know how much I love watching you work, but I've got my country's 500th anniversary to plan, my wedding to arrange, my wife to murder and Guilder to frame for it; I'm swamped.\nCount Rugen: Get some rest. If you haven't got your health, then you haven't got anything.",
        "Fezzik: Why do you wear a mask? Were you burned by acid, or something like that?\nMan in Black: Oh no, it's just that they're terribly comfortable. I think everyone will be wearing them in the future.",
        "Inigo Montoya: He's right on top of us. I wonder if he is using the same wind we are using." 
    };
    private static Random rand = new Random();
    Socket client;

    public TCPSocket(Socket client, Level level) {
        super();
        this.client = client;
        LOGGER.setLevel(level);
        LOGGER.info("Client connected from " + this.client.getInetAddress().getHostAddress());
    }

    public void run() {
        try {
            OutputStream os = this.client.getOutputStream();
            PrintWriter writer = new PrintWriter(os, true);

            String quote = selectQuote();
            LOGGER.info("Sending message: " + quote);
            writer.println(quote);
            LOGGER.info("Closing thread resources...");
            
            writer.close();
            os.close();
            this.client.close();
        } catch (IOException e) {
            LOGGER.severe(e.toString());
        }
    }

    private String selectQuote() {
        int randIndex = rand.nextInt(QUOTES.length);
        return QUOTES[randIndex];
    }
}