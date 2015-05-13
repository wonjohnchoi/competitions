import java.util.*;
import java.io.*;
public class CampLunch {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static int count(int N, int M, String[] a) {
        List<Integer> states = new ArrayList<Integer>();
        for (int i = 0; i < (1 << M); i++) {
            states.add(i);
        }
        Collections.sort(states, new Comparator<Integer> () {
                public int compare(Integer a, Integer b) {
                    int aa = Integer.bitCount(a);
                    int bb = Integer.bitCount(b);
                    return Integer.compare(aa, bb);
                }
            });
        for (int i = 0; i < 5; i++) {
            out.println(states.get(i));
        }
        int MOD = (int) 1e9 + 7;
        int[][] dp = new int[1 << M][N];
        dp[0][0] = 0;
        for (int i = 0; i < N; i++) {
            for (int state : states) {
                for (int j = 0; j < M; j++) {
                    boolean a = (state & (1 << j)) == 0;

                    boolean b = j + 1 < M && (state & (1 << (j + 1))) == 0;
                }
            }
        }
    }
    public static void main(String args[]) {

    }
}
