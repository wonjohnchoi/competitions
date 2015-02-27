import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            int N = sc.nextInt();
            int[] As = new int[N];
            int[] Bs = new int[N];
            for (int i = 0; i < N; i++) {
                As[i] = sc.nextInt();
                Bs[i] = sc.nextInt();
            }
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    int Ad = As[i] - As[j];
                    int Bd = Bs[i] - Bs[j];
                    if ((Ad < 0 && Bd > 0) || (Ad > 0 && Bd < 0)) {
                        cnt++;
                    }
                }
            }
            String ans = "" + cnt;
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
