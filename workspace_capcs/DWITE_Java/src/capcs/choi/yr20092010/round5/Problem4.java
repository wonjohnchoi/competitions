package capcs.choi.yr20092010.round5;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/kind_of_like_OCR.html
 * @author Wonjohn Choi
 */
public class Problem4 {

    static String data[][] = { { "x.", "xx" }, { "xx", "xx" },
            { "x.x", "xxx" }, { "xx", ".x" }, { "xxx", ".xx" } };

    static String out;

    public static void main(String args[]) throws IOException {
        //I/O
        PrintWriter pw = new PrintWriter(new FileWriter("OUT4.txt"));
        Scanner sc = new Scanner(new FileReader("DATA4.txt"));

        //for each input
        for (int i = 0; i < 5; i++) {
            //get input
            String given[] = new String[2];
            given[0] = sc.next();
            given[1] = sc.next();

            out = "";
            recur(given, "");
            pw.println(out);
        }

        pw.close();
        sc.close();

    }

    /**
     * recursively compute answer
     * @param given a given input
     * @param word the output
     */
    private static void recur(String[] given, String word) {
        //out is the output. If it has some value, it means that its value is determined so no more process is required
        if (out.isEmpty()) {
            if (given[0].isEmpty() && given[1].isEmpty()) {
                out = word;
            } else {
                for (int j = 0; j < 5; j++) {
                    if (given[0].startsWith(data[j][0]) && given[1].startsWith(data[j][1])) {
                       //recursive call
                        recur(new String[] {given[0].substring(data[j][0].length()), given[1].substring(data[j][1].length()) }, 
                                word + (char) (j + 'A'));
                    }
                }
            }
        }
    }
}