import java.util.*;
import java.io.*;
public class OddEvenTree {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    static class Pair {
	int a, b;
	Pair(int aa, int bb) { a = aa; b = bb; }
    }
    public static int[] getTree(String[] x){
	int N = x.length;
	int[] ans = new int[2 * N - 2];
	int ansI = 0;
	int[] ans2 = new int[] { -1 };
	int[] cnt = new int[2];
	List<Integer> odds = new ArrayList<>();
	List<Integer> evens = new ArrayList<>();
	for (int i = 1; i < N; i++) {
	    if (x[0].charAt(i) == 'O') { cnt[0]++; odds.add(i); }
	    else { cnt[1]++; evens.add(i); }
	}
	if (cnt[0] == 0) return ans2;
	for (int i = 0; i < N; i++) {
	    for (int j = 0; j < N; j++) {
		for (int k = 0; k < N; k++) {
		    int a = x[i].charAt(j) == 'O' ? 1 : 0;
		    a += x[j].charAt(k) == 'O' ? 1 : 0;
		    int b = x[i].charAt(k) == 'O' ? 1 : 0;
		    if ((a % 2) != b) {
			return ans2;
		    }
		}
	    }
	}
	
	for (int odd : odds) {
	    ans[ansI++] = 0;
	    ans[ansI++] = odd;
	}
	for (int i = 0; i < evens.size(); i++) {
	    ans[ansI++] = odds.get(0);
	    ans[ansI++] = evens.get(i);
	}
	return ans;
    }
    public static void main(String args[]) {
	
    }
}
