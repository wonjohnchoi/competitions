package capcs.choi.yr20092010.round4;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/autocomplete.html
 * @author Wonjohn Choi
 * 
 */
public class Problem4 {

    public static void main(String[] args) {

        // Input/Output
        Scanner sc = null;
        PrintWriter pw = null;

        try {
            sc = new Scanner(new FileReader("DATA4.txt"));
            pw = new PrintWriter(new FileWriter("OUT4.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        int digitSum;

        String dic[] = new String[50000];

        //for each key
        for (int it = 0; it < dic.length; it++) {
            //find out sum of digit
            digitSum = 0;
            String num = it + "";
            for (int j = 0; j < num.length(); j++) {
                digitSum += (int) (num.charAt(j) - '0');
            }

            //put the required value for each key
            dic[it] = it * digitSum % 99999 + "";
        }

        //for each input
        for (int it = 0; it < 5; it++) {
            int count = 0;
            String key = sc.next();
            for (int j = 0; j < dic.length; j++) {
                if (dic[j].startsWith(key)) {
                    count++;
                }
            }

            pw.println(count);
        }

        sc.close();
        pw.close();
        System.exit(0);
    }
}