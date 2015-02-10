import java.util.*;
import java.io.*;
public class RANKLIST {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            long n = sc.nextLong();
            long s = sc.nextLong();
            int maxA = (int) ((2 * n + 1 - Math.sqrt(Math.pow(2 * n + 1, 2) - 8 * s)) / 2);
            System.out.println(n - maxA);
        }
    }
}
