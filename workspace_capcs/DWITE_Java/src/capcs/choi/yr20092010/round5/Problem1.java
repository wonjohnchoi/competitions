package capcs.choi.yr20092010.round5;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/ASCII_bar_graphs.html
 * @author Wonjohn Choi
 */
public class Problem1 {
    public static void main(String args[]) throws IOException {
        //I/O
        PrintWriter pw = new PrintWriter(new FileWriter("OUT1.txt"));
        Scanner sc = new Scanner(new FileReader("DATA1.txt"));

        //for each input
        for (int i = 0; i < 5; i++) {
            int n = sc.nextInt(); //get input
            char[] dots = "-----|-----".toCharArray();
            
            //process logic
            if (n < 0) {
                Arrays.fill(dots, 5 + n, 5, '*');
            } else {
                Arrays.fill(dots, 6, 6 + n, '*');
            }

            //output
            pw.println(new String(dots));
        }

        //finish using I/O
        pw.close();
        sc.close();

    }
}