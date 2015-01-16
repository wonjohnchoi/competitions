package capcs.choi.yr20092010.round4;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/social_media_overload.html
 * so... Ok this problem is such a joke that I even do not know why I had to waste time to read the problem...
 * @author Wonjohn Choi
 *
 */
public class Problem1 {

    public static void main(String[] args) throws IOException {

        // Input/Output
        Scanner sc = null;
        PrintWriter pw = null;

        try {
            sc = new Scanner(new FileReader("DATA1.txt"));
            pw = new PrintWriter(new FileWriter("OUT1.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int it = 0; it < 5; it++) {
            pw.println(Math.round(9000/sc.nextInt())); //output
        }


        sc.close();
        pw.close();
    }
}