package capcs.choi.yr20092010.round5;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/round_to_second_prime.html
 * 
 * @author Wonjohn Choi
 */
public class Problem2 {
    public static void main(String args[]) throws IOException {
        //I/O
        PrintWriter pw = new PrintWriter(new FileWriter("OUT2.txt"));
        Scanner sc = new Scanner(new FileReader("DATA2.txt"));

        //for each input
        for (int i = 0; i < 5; i++) {
            int n = sc.nextInt();
            int hit, up, down;

            //logic to get bigger second prime
            hit = 0;
            up = n;
            while (hit != 2) {
                up++;
                if (isPrime(up)) {
                    hit++;
                }
            }

            
            //logic to get lower second prime
            hit = 0;
            down = n;
            while (hit != 2) {
                down--;
                if (isPrime(down)) {
                    hit++;
                }
            }

            //output logic
            if (up > 100) {
                pw.println(down);
            } else if (down < 2) {
                pw.println(up);
            } else if (up - n > n - down) {
                pw.println(down);
            } else {
                pw.println(up);
            }
        }

        sc.close();
        pw.close();

    }

    /**
     * function to determine if a number is prime
     * if a input is negative, output that it is prime
     */
    private static boolean isPrime(int n) {
        //for numbers smaller than given input
        for (int i = 2; i * i<=n ; i++) {
            //if the number strictly divides input 
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}