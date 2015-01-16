package capcs.choi.yr20092010.round6;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/time_for_change_2.html
 * Need to use dynamic solution
 * Using coins array, avoid to calculate same thing twice
 * @author Wonjohn Choi
 * 
 */
public class Problem4 {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(new FileReader("DATA4.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("OUT4.txt"));

        for (int i = 0; i < 5; i++) {
            int tot = sc.nextInt();
            int nCoin = sc.nextInt();

            int coins[] = new int[nCoin];
            for (int j = 0; j < nCoin; j++) {
                coins[j] = sc.nextInt();
            }

            int record[] = new int[tot + 1];
            Arrays.fill(record, 99999);
            record[0] = 0;

            for (int j = 1; j < record.length; j++) {
                for (int k = 0; k < nCoin; k++) {
                    if (j >= coins[k]) {
                        record[j] = Math.min(record[j],record[j - coins[k]] + 1);
                    }
                }
            }

            pw.println(record[tot]);
            pw.flush();
        }

        pw.close();
        sc.close();
        System.exit(0);
    }
}
