import java.util.*;
import java.io.*;
public class NumberTheory {
    public static int[][] comb(int n, int MOD) { // O(N^2)
	int[][] comb = new int[n + 1][n + 1];
	comb[0][0] = 1;
	for (int i = 1; i <= n; ++i) {
	    comb[i][0] = 1;
	    for (int j = 1; j <= n; ++j) {
		comb[i][j] = (int) ((comb[i - 1][j - 1] + comb[i - 1][j]) % MOD);
	    }
	}
        return comb;
    }
    public static int[] inverse(int N, int MOD) { // O(N)
        // returns 1/i % MOD (i < MOD, gcd(i, MOD) = 1)
        // MOD is prime
        int[] inverse = new int[N];
        inverse[0] = -10000;
        inverse[1] = 1;
        for (int i = 2; i < N; i++) {
            inverse[i] = -(MOD / i) * inverse[MOD % i];
        }
        return inverse;
    }
    static long inverse2(long a, long MOD) {
        return a == 1 ? 1 : (long) (MOD - MOD / a) * inverse2(MOD % a) % MOD;
    }
    public static void main(String args[]) {
	int[][] comb = comb(100, 100000000);
	System.out.println(comb[5][2]); // 10
	System.out.println(comb[6][2]); // 15
	System.out.println(comb[5][1]); // 5
	System.out.println(comb[5][0]); // 1
	comb = comb(100, 3);
	System.out.println(comb[5][2]); // 1
	System.out.println(comb[6][2]); // 0
	System.out.println(comb[5][1]); // 2
	System.out.println(comb[5][0]); // 1

        int[] inverse = inverse(100, 5);
        for (int i = 1; i < 5; i++) {
            System.out.println("1/" + i + " = " + inverse[i]);
            System.out.println("1 = " + (i * inverse[i] + 10) % 5);
        }
    }
}
