package capcs.choi.yr20092010.round5;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/weak_passwords.html
 * Interestingly, brute force works...
 * @author Wonjohn Choi
 */
public class Problem5 {
    public static void main(String args[]) throws IOException {
        //I/O
        PrintWriter pw = new PrintWriter(new FileWriter("OUT5.txt"));
        Scanner sc = new Scanner(new FileReader("DATA5.txt"));

        int input[] = new int[5];
        String output[] = new String[5];

        //get input
        for (int i = 0; i < 5; i++) {
            input[i] = sc.nextInt();
        }

        //brute force
        for (int a = 65; a <= 90; a++) {
            for (int b = 65; b <= 90; b++) {
                for (int c = 65; c <= 90; c++) {
                    for (int d = 65; d <= 90; d++) {
                        int k = a * 1000000 + b * 10000 + c * 100 + d;
                        int m = a * 11 + b * 101 + c * 1009 + d * 10007;
                        int p = k % m;

                        for (int i = 0; i < 5; i++) {
                            if (input[i] == p) {
                                output[i] = "" + (char) a + (char) b + (char) c + (char) d;
                            }
                        }
                    }
                }
            }
        }

        //output
        for (int i = 0; i < 5; i++) {
            pw.println(output[i]);
        }

        //finish using I/O
        pw.close();
        sc.close();

    }

}