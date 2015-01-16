package capcs.choi.yr20092010.round5;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/summary_diff.html
 * 
 * @author Wonjohn Choi
 */
public class Problem3 {
    public static void main(String args[]) throws IOException {
        //I/O
        PrintWriter pw = new PrintWriter(new FileWriter("OUT3.txt"));
        Scanner sc = new Scanner(new FileReader("DATA3.txt"));
        
        int d, m;
        
        //for each input
        for (int i = 0; i < 5; i++) {
            //get input
            d = sc.nextInt();
            m = sc.nextInt();

            String data[] = new String[d];
            int valData[] = new int[d];
            String match[] = new String[m];
            int valMatch[] = new int[m];

            for (int j = 0; j < d; j++) {
                data[j] = sc.next();
                valData[j] = sc.nextInt();
            }

            for (int j = 0; j < m; j++) {
                match[j] = sc.next();
                valMatch[j] = sc.nextInt();
            }

            int miss = 0;
            int diff = 0;

            for (int j = 0; j < d; j++) {
                boolean found = false;
                for (int k = 0; k < m && !found; k++) {
                    if (data[j].equals(match[k])) {
                        found = true;
                    }
                }

                if (!found) {
                    miss++;
                }
            }

            for (int j = 0; j < m; j++) {
                boolean found = false;
                for (int k = 0; k < d && !found; k++) {
                    if (match[j].equals(data[k])) {
                        found = true;
                        diff += Math.abs(valMatch[j] - valData[k]);
                    }
                }

                if (!found) {
                    miss++;
                }
            }

            pw.printf("%d %d\n", miss, diff);
            sc.next();
        }

        pw.close();
        System.exit(0);

    }
}