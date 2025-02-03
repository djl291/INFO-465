package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import lib.*;

public class Guesser {
    private File file;
	private static Scanner scan;
    public static ArrayList<String> wordpool;
    private static int[] blacklist;

    public Guesser(int spaces, String wordFile) {
        wordpool = new ArrayList<String>();
        blacklist = new int[26];
		file = new File(wordFile);
		
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(scan.hasNextLine()) {
            String word = scan.nextLine();
            if (word.length()==spaces)
			    wordpool.add(word);
		}
		scan.close();
    }

    public static void updateWordpool(char[] guess) {
        ArrayList<String> wordpoolTemp = new ArrayList<>();

        next:
        for (int i=0; i<wordpool.size(); i++) {
            for (int j=0; j<guess.length; j++) {
                if (guess[j]!='.') {
                    if (wordpool.get(i).charAt(j)!=guess[j])
                        continue next;
                }
            }
            wordpoolTemp.add(wordpool.get(i));
        }
        wordpool = wordpoolTemp;


        StdOut.println("WORDPOOL SIZE: " + wordpool.size());
    }

    public static void updateWordpool() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        ArrayList<String> wordpoolTemp = new ArrayList<>();

        next:
        for (int i=0; i<wordpool.size(); i++) {
            for (int j=0; j<26; j++) {
                if (blacklist[j]==-1) {
                    if (wordpool.get(i).indexOf(alphabet.charAt(j))>=0) 
                        continue next;
                }
            }
            wordpoolTemp.add(wordpool.get(i));
        }
        wordpool = wordpoolTemp;

        StdOut.println("WORDPOOL SIZE: " + wordpool.size());
    }

    public static char makeAGuess(char[] guess) {
       int[] alphabetCount = new int[26];
       String alphabet = "abcdefghijklmnopqrstuvwxyz";
       char bestGuess = 0;
       int max = 0;
       for (int i=0; i<wordpool.size(); i++) {
           String currentWord = wordpool.get(i);
           for (int j=0; j<currentWord.length(); j++) {
               int k = alphabet.indexOf(currentWord.charAt(j));
               alphabetCount[k] = alphabetCount[k]+1;
           }
       }

       for (int r=0; r<blacklist.length; r++) {
           if (blacklist[r]==-1)
                alphabetCount[r]=-1;
       }

       for (int i=0; i<guess.length; i++) {
            if (guess[i]!='.') {
                int j = alphabet.indexOf(guess[i]);
                alphabetCount[j] = -1;
            }
        }

       for (int b : alphabetCount) {
           StdOut.print(b + " ");
       }

       StdOut.println();
       for (int l=0; l<alphabetCount.length; l++) {
           if (alphabetCount[l]>=max) {
                max = alphabetCount[l];
                bestGuess = alphabet.charAt(l);
           }
       }
       return bestGuess;
    }

    public static void blacklist(char letter) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int k = alphabet.indexOf(letter);
        blacklist[k]=-1;
    }


    public static ArrayList<String> getWordpool() {
        return wordpool;
    }

    public static int getWordPoolSize() {
        return wordpool.size();
    } 

    public static int[] getBlacklist() {
        return blacklist;
    }

}
