import java.util.*;
import java.io.*;
public class D {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        long a1, a2, b1, b2;
        a1 = in.nextLong();
        a2 = in.nextLong();
        b1 = in.nextLong();
        b2 = in.nextLong();
        long a, b;
        a = a1 * a2;
        b = b1 * b2;
        long g = gcd(a, b);
        a /= g;
        b /= g;
        int three, two;
        two = three = 0;
        if (b != 1) {
            while (b % 2 == 0) {
                b /= 2;
                two--;
            }
            while (b % 3 == 0) {
                b /= 3;
                three--;
            }
            if (b != 1) NO();
        }
        if (a != 1) {
            while (a % 2 == 0) {
                a /= 2;
                two++;
            }
            while (a % 3 == 0) {
                a /= 3;
                three++;
            }
            if (a != 1) NO();
        }
        int x, y;
        x = three;
        y = two + x;
        if (x > 0) {
            for (int i = 0; i < x; i++) {
                if (a1 % 3 == 0) a1 = a1 * 2 / 3;
                else a2 = a2 * 2 / 3;
            }
        } else {
            for (int i = 0; i < -x; i++) {
                if (b1 % 3 == 0) b1 = b1 * 2 / 3;
                else b2 = b2 * 2 / 3;
            }
        }
        if (y > 0) {
            for (int i = 0; i < y; i++) {
                if (a1 % 2 == 0) a1 = a1 / 2;
                else a2 = a2 / 2;
            }
        } else {
            for (int i = 0; i < -y; i++) {
                if (b1 % 2 == 0) b1 = b1 / 2;
                else b2 = b2 / 2;
            }
        }
        out.println(Math.abs(x) + Math.abs(y));
        out.println(a1 + " " + a2);
        out.println(b1 + " " + b2);
    }
    static void NO() {
        out.println(-1);
        System.exit(0);
    }
    static long gcd(long a, long b) {
        if (a < b) return gcd(b, a);
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;
    public InputReader(InputStream stream) {
	reader = new BufferedReader(new InputStreamReader(stream), 32768);
	tokenizer = null;
    }
    public String next() {
	while (tokenizer == null || !tokenizer.hasMoreTokens()) {
	    try {
		tokenizer = new StringTokenizer(reader.readLine());
	    } catch (IOException e) {
		throw new RuntimeException(e);
	    }
	}
	return tokenizer.nextToken();
    }
    public double nextDouble() {
	return Double.parseDouble(next());
    }
    public long nextLong() {
	return Long.parseLong(next());
    }
    public int nextInt() {
	return Integer.parseInt(next());
    }
}
