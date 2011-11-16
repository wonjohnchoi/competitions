import java.util.*;
import java.io.*;
/*
Wonjohn Choi
Solved with Java
2008 Annual Berkeley Programming Contest: Problem 7
*/
class P7 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int nCases = 0;
        while (in.hasNextInt()) {
            nCases += 1;
            int n = in.nextInt();
            int idx = in.nextInt();
            int[] primes = new int[n];
            for (int i = 0; i < n ;i += 1) {
                primes[i] = in.nextInt();
            }
            Vector<Integer> list = new Vector<Integer>(10000);
            list.add(1);
            generateAll(list, primes, 0, 1);
            Collections.sort(list);
            System.out.printf("Case %d: %d\n", nCases, list.get(idx - 1));
        }
    }

    static void generateAll(Vector<Integer> list, int[] primes, int idxPrime, long elem) {
            if (primes.length <= idxPrime) {
                return;
            }
            
            while (true) {
                generateAll(list, primes, idxPrime + 1, elem);
                elem *= primes[idxPrime];
                
                if (elem > Integer.MAX_VALUE) break;
                list.add((int) elem);
            }
    }
    
}
