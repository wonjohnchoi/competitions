import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N, M;
        N = sc.nextInt();
        M = sc.nextInt();
        out.printf("%s\n", ans(N, M));
    }
    static int ans(int N, int M) {
        if (N >= M) return N - M;
        if (M % 2 == 0) return ans(N, M / 2) + 1;
        return ans(N, M + 1) + 1;
    }
}
