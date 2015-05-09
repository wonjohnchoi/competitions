import java.util.*;
import java.io.*;
public class DevuAndBeautifulSubstrings {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    static long[][][] cache;
    static long f(int n, int cnt, int len) {
        if (n == 0) return cnt == 0 ? 1 : 0;
        if (cnt < 0) return 0;
        if (cache[n][cnt][len] == -1) {
            long ret = 0;
            if (len == 0) {
                ret = f(n - 1, cnt - 1, 1) * 2;
            } else {
                ret = f(n - 1, cnt - len - 1, len + 1);
                ret += f(n - 1, cnt - 1, 1);
            }
            cache[n][cnt][len] = ret;
        }
        return cache[n][cnt][len];
    }
    public static long countBeautifulSubstrings(int n, int cnt){
        int N = 50;
        cache = new long[N + 1][N * (N + 1) / 2 + 1][N + 1];
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[i].length; j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }
       return f(n, cnt, 0);
    }
    public static void main(String args[]) {
        out.println(countBeautifulSubstrings(50, 94));
    }
}
