package src;
import lib.*;

public class App {

    public static void main(String[] args) throws InterruptedException {
        
        new Initializer();
        new Guesser(Initializer.getNumOfLetters(), "words.txt");

        int guesses = 1;
        while (guesses<9) {
            StdOut.println("Number of guesses left: " + (9-guesses));
            char guess = Guesser.makeAGuess(Commands.getGuessTemplate());
            StdOut.println("Does your word contain: " + guess + "? (Y/N)");
            if (Commands.getResponse()) {
                StdOut.println("Enter all positions where the letter appears. Then press X when complete");
                Commands.dispTemplate();

                while(true) {
                    String input = StdIn.readString().toLowerCase();
                    if (!input.equals("x")) {
                        int pos = Integer.parseInt(input);
                        Commands.fillTemplate(guess, pos);
                        Commands.dispTemplate();
                        if (Commands.checkCompletion()) {
                            StdOut.println("Your word is: ");
                            Commands.dispTemplate();
                            System.exit(0);
                        }
                    }
                    else break;
                }

                Guesser.updateWordpool(Commands.getGuessTemplate());
            }
            else {
                Guesser.blacklist(guess);
                int[] blacklist = Guesser.getBlacklist();
                for (int b : blacklist) {
                    StdOut.print(b+" ");
                }
                StdOut.println();
                Guesser.updateWordpool();
                guesses++;
            }
        }
    }
    
}
