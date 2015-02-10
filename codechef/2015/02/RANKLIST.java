import java.util.*;
import java.io.*;
public class RANKLIST {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            long n = sc.nextLong();
            long s = sc.nextLong();
            int val = 1;
            int numE = 1;
            s -= val;
            while (s > 0) {
                if ((val + 1) * (n - numE) <= s) {
                    val++;
                }
                numE++;
                s -= val;
            }
            System.out.println(n - val);
        }
    }
}
