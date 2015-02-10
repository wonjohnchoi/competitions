import java.util.*;
import java.io.*;
public class CHEFEQ {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0) {
            int N = sc.nextInt();
            int[] cntA = new int[100001];
            int maxCnt = 0;
            for (int n = 0; n < N; n++) {
                int A = sc.nextInt();
                cntA[A]++;
                maxCnt = Math.max(maxCnt, cntA[A]);
            }
            System.out.println(N - maxCnt);
        }
    }
}
