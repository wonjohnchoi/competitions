import java.util.*;
import java.io.*;
public class PermutationCounts {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static int countPermutations(int N, int[] pos) {
        char[] s = new char[N - 1];
        Arrays.fill(s, '>');
        for (int p : pos) {
            s[p - 1] = '<';
        }
        boolean[] special = new boolean[N]; // i ~ i + 1 = > i in s
        int ns = N;
        Arrays.fill(special, true);
        List<Integer> specials = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            if (i != 0 && i != N - 1 &&
                s[i - 1] == s[i]) {
                special = false;
                ns--;
            } else {
                specials.add(i);
            }
        }
        HashMap<Integer, Long> dp = new HashMap<>(); // num larger => tot;
        int si = 0;
        dp.put(

    }
    public static void main(String args[]) {

    }
}
