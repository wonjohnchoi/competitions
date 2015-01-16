package capcs.choi.yr20092010.round7;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/sum_of_primes.html
 * @author Wonjohn Choi
 *
 */
public class Problem2 {
    public static void main(String args[]) throws IOException {
        //I/O
        Scanner sc = new Scanner(new FileReader("DATA2.txt"));
        PrintWriter pw = new PrintWriter(new FileWriter("OUT2.txt"));
        
        //maximum possible number
        int len = 100001;
        boolean[] prime = new boolean[len];;
       
        //generate the list
        generatePrimes(prime, len);

        //for each input
        for (int it = 0; it < 5; it++) {
            pw.println(getSum(prime, sc.nextInt()));
        }

        //Finish I/O
        sc.close();
        pw.close();
    }

    /**
     * generate a list to indiate whether a number is prime
     * @param len
     */
    public static void generatePrimes(boolean[] prime, int len) {
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        for (int i = 2; i < Math.sqrt(len); i++) {
            if (prime[i])
                for (int j = i * i; j < len; j += i) {
                    prime[j] = false;
                }
        }
    }

    /**
     * get sum of prime numbers under 'len'
     */
    public static int getSum(boolean prime[], int len) {
        int sum = 0;
        for (int i = 2; i <= len; i++) {
            if (prime[i]) {
                sum += i;
            }
        }
        return sum;
    }

}
