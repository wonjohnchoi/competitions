import java.util.*;
import java.io.*;
public class CombMOD {
    public static int[][] combMOD(int n, int MOD) { // O(N^2)
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
    public static void main(String args[]) {
	int[][] comb = combMOD(100, 100000000);
	System.out.println(comb[5][2]); // 10
	System.out.println(comb[6][2]); // 15
	System.out.println(comb[5][1]); // 5
	System.out.println(comb[5][0]); // 1

	comb = combMOD(100, 3);
	System.out.println(comb[5][2]); // 1
	System.out.println(comb[6][2]); // 0
	System.out.println(comb[5][1]); // 2
	System.out.println(comb[5][0]); // 1
    }
}
