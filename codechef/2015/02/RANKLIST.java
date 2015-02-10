import java.util.*;
import java.io.*;
public class RANKLIST {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            long n = sc.nextLong();
            long s = sc.nextLong();
            for (long a = n; a >= 1; a--) {
                if ((1 + a) * a / 2 + (n - a) <= s) {
                    System.out.println(n - a);
                    break;
                }
            }
        }
    }
}
