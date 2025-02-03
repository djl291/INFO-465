package lib;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;


public class Commands {

    private static char[] guessTemplate;
    private static int[] guessSpaces;

    public static boolean getResponse() {
        try {
            String token = StdIn.readString().toLowerCase();
            if ("y".equalsIgnoreCase(token))  return true;
            if ("n".equalsIgnoreCase(token)) return false;

            throw new InputMismatchException("attempts to read a 'boolean' value from standard input, "
                                           + "but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'boolean' value from standard input, "
                                           + "but no more tokens are available");
        }

    }

    public static void createTemplate(int spaces) {
        guessTemplate = new char[spaces];
        guessSpaces = new int[spaces];
        for (int i=0; i<spaces; i++) {
            guessTemplate[i] = '.';
            guessSpaces[i]=(i+1);
        }
    }

    public static void fillTemplate(char letter, int pos) {
        guessTemplate[pos-1] = letter;
    }

    public static void dispTemplate() {
        for (int i=0; i<guessTemplate.length; i++) {
            StdOut.print(guessTemplate[i]+"\t");
        }
        StdOut.println();
        for (int i=0; i<guessTemplate.length; i++) {
            StdOut.print(guessSpaces[i]+"\t");
        }
        StdOut.println();
    }

    public static char[] getGuessTemplate() {
        return guessTemplate;
    }

    public static boolean checkCompletion() {
        for (int i=0; i<guessTemplate.length; i++) {
            if (guessTemplate[i]=='.') return false;
        }
        return true;
    }



}
