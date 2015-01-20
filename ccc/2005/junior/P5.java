package capcs.choi.yr2005;

import java.util.Scanner;

/**
 * 
 * @author Wonjohn Choi
 * @lang Java
 * @CCC 2005 Junior 5
 */
public class Junior5 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while (!input.equals("X")) {
            if (isMonkeyWord(input)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            input = sc.next();
        }
    }

    /**
     * Method to check if a word is monkey word, by the definition from the problem
     * Use functional approach
     */
    public static boolean isMonkeyWord(String word) {
        if (word.equals("A")) {
            return true;
        }

        else if (word.startsWith("B") && word.endsWith("S")) {
            if (isMonkeyWord(word.substring(1, word.length() - 1))) {
                return true;
            } else {
                return false;
            }
        } else if (word.startsWith("AN")) {
            if (isMonkeyWord(word.substring(2, word.length()))) {
                return true;
            } else {
                return false;
            }
        } else if (word.startsWith("B")) {
            String[] twoParts = word.substring(1, word.length()).split("SN");
            if (twoParts.length != 2) {
                return false;
            } else if (isMonkeyWord(twoParts[0]) && isMonkeyWord(twoParts[1])) {
                return true;
            } else {
                return false;
            }
        }

        return false;

    }
}
