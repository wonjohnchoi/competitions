package capcs.choi.yr20092010.round7;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/snapper_chain.html
 * This question requires a bit of logic
 * @author Wonjohn Choi
 *
 */
public class Problem5 {
    public static void main(String args[]) throws IOException {
        //I/O
        Scanner sc = new Scanner(new FileReader("DATA5.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("OUT5.txt"));
        
        //for each input
        for (int _it = 0; _it < 5; _it++) {
            //get input
            int N = sc.nextInt();
            int K = sc.nextInt();
            
            //get the value of 2^N
            int cir = 1 << N;
            
            K = K % cir;
            
            //output
            if (K == cir - 1) {
                pw.println("ON");
            } else {
                pw.println("OFF");
            }
        }

        //finish I/O
        sc.close();
        pw.close();
    }
}
