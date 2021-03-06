import java.math.BigInteger;
import java.util.Scanner;


public class CoinTosses {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i += 1) {
            int n = in.nextInt();
            int m = in.nextInt();
            System.out.println(String.format("%s.00",
                BigInteger.valueOf(2).pow(n + 1)
                .subtract(BigInteger.valueOf(2).pow(m + 1))));
        }
    }
}
