package capcs.choi.yr20092010.round7;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/binary_equipment.html
 * @author Wonjohn Choi
 *
 */
public class Problem1 {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(new FileReader("DATA1.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("OUT1.txt"));

        for (int it = 0; it < 5; it++) {
            int num = sc.nextInt();
            String str = Integer.toBinaryString(num);
            while (str.length() < 8) {
                str = "0" + str;
            }
            System.out.println(str);

            int pos = sc.nextInt();
            pw.println(str.charAt(7 - pos));
        }

        sc.close();
        pw.close();
    }

}
