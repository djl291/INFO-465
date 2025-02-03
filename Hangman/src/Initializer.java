package src;
import lib.*;

public class Initializer {
    private static int numOfLetters;

    public Initializer() throws InterruptedException {
        StdOut.println("Let's play a game of Hangman!");
        Thread.sleep(1500);
        StdOut.println("Think of any word, and I'll try to guess what it is");
        Thread.sleep(1500);
        StdOut.println("Ready? (Y/N)");
        if (!Commands.getResponse()) {
            StdOut.println("See you later!");
            System.exit(0);
        }
        StdOut.println("Great! Let's Play!");

        while(true) {
            StdOut.println("How many letters are in your word?");
            numOfLetters = StdIn.readInt();
            Commands.createTemplate(numOfLetters);
            Commands.dispTemplate();
            StdOut.println("Like so? (Y/N)");
            boolean foward = Commands.getResponse();
            
            if (foward) {
                StdOut.println("Great!");
                break;
            }
        }
        
    }

    public static int getNumOfLetters() {
        return numOfLetters;
    }

}
