package capcs.choi.yr20092010.round6;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/round_to_power_of_two.html
 * @author Wonjohn Choi
 * 
 */
public class Problem2 {
    public static void main(String args[]) throws IOException {
        //I/O
        Scanner sc = new Scanner(new FileReader("DATA2.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("OUT2.txt"));

        //for each input
        for (int i = 0; i < 5; i++) {
            int num = sc.nextInt();
            
            //cover special case
            if (num == 0) {
                pw.println(1);
            } else {
                int pre = 1;
                int cur = 2;
                while (!(pre <= num && num <= cur)) {
                    pre *= 2;
                    cur *= 2;
                }

                if (cur - num > num - pre) {
                    pw.println(pre);
                } else {
                    pw.println(cur);
                }
            }

        }

        //finish I/O
        pw.close();
        sc.close();
    }
}
