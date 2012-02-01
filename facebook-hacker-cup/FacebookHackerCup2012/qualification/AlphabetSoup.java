import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class AlphabetSoup {
    public static void main(String args[]) {
        Scanner in = null;
        try {
            in = new Scanner(new FileReader("alphabetsoup.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("alphabetsoup.out"));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        int t = in.nextInt();
        in.nextLine();
        char[] letters = "HACKERCUP".toCharArray();

        HashMap<Character, Integer> freq = new HashMap<Character, Integer>();

        for (int i = 0; i < t; i += 1) {
            String line = in.nextLine();
            for (char chr : letters) {
                freq.put(chr, 0);
            }
            for (char chr : line.toCharArray()) {
                if (freq.containsKey(chr)) {
                    freq.put(chr, freq.get(chr) + 1);
                }
            }

            int minFreq = Integer.MAX_VALUE;
            for (char chr : letters) {
                minFreq = Math.min(minFreq, freq.get(chr));
            }

            out.printf("Case #%d: %d%n", i + 1, minFreq);
        }
        out.close();
    }
}
