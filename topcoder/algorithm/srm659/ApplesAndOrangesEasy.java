import java.util.*;
import java.io.*;
public class ApplesAndOrangesEasy {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static int maximumApples(int N, int K, int[] info){
        boolean[] res = new boolean[N];
        for (int i = 0; i < info.length; i++) {
            res[info[i] - 1] = true;
        }
        int[] accum = new int[N];
        for (int i = 0; i < N; i++) {
            // i ~ i + K - 1
            for (int j = i; j < Math.min(i + K, N); j++) {
                if (res[j]) accum[i]++;
            }
        }
        int apples = K / 2;
        outer : for (int i = 0; i < N; i++) {
            if (!res[i]) {
                for (int j = Math.max(i - K + 1, 0); j <= i; j++) {
                    if (accum[j] >= apples) {
                        continue outer;
                    }
                }
                res[i] = true;
                for (int j = Math.max(i - K + 1, 0); j <= i; j++) {
                    accum[j]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (res[i]) ans++;
        }
        return ans;
    }
    public static void main(String args[]) {

    }
}
