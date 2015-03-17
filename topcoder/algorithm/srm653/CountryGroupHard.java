import java.util.*;
import java.io.*;
public class CountryGroupHard {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static String solve(int[] a) {
        long[] b = new long[a.length + 1];
        b[a.length] = 1;
        for (int i = a.length - 1; i >= 0; i--) {
            int next = -1;
            for (int j = i; j < a.length; j++) {
                if (a[j] != 0) {
                    next = j;
                    break;
                }
            }
            if (next == -1) {
                if (a.length - 1 - i + 1 >= 1) {
                    b[i] = 2;
                } else {
                    b[i] = 1;
                }
            } else {
                int cnt = a[next];
                outer : for (int j = next - cnt + 1; j <= next; j++) {
                    for (int k = 0; k < cnt; k++) {
                        if (i <= j + k && j + k < a.length
                            && (a[j + k] == 0 || a[j + k] == cnt)) {
                        } else {
                            continue outer;
                        }
                    }
                    b[i] += b[j + cnt];
                }
            }
        }
        out.println(b[0]);
        return b[0] == 1 ? "Sufficient" : "Insufficient";
    }
    public static void main(String args[]) {
        solve(new int[] {0,2,3,0,0});
        solve(new int[] {0,2,0});
        solve(new int[] {0,3,0,0,3,0});
        solve(new int[] {0,0,3,3,0,0});
        solve(new int[] {2,2,0,2,2});
        solve(new int[] {0});
        solve(new int[] {});
    }
}
