import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        int P = in.nextInt() - 1;
        String S = in.next();
        int b = N / 2 - 1;
        int rb, lb;
        if (P <= b) {
            lb = 0; rb = b;
        } else {
            lb = b + 1; rb = N - 1;
        }
        int lc = lb;
        int rc = rb;
        while (lc <= rb) {
            if (S.charAt(lc) != S.charAt(N - 1 - lc)) {
                break;
            }
            lc++;
        }
        while (rc >= lb) {
            if (S.charAt(rc) != S.charAt(N - 1 - rc)) {
                break;
            }
            rc--;
        }
        // out.println(lc + " " + rc);
        // out.println("P: " + P);
        if (lc <= rc) {
            int changes = 0;
            for (int i = lc; i <= rc; i++) {
                char c1 = S.charAt(i);
                char c2 = S.charAt(N - 1 - i);
                if (c1 != c2) {
                    int diff = Math.abs((int) c1 - c2);
                    changes += Math.min(diff, 26 - diff);
                    // out.println(c1 + " " + c2 + " " + (int) (c1 - c2));
                }
            }
            int extraMoves = 0;
            if (P < lc) {
                extraMoves += lc - P;
                P = lc;
            }
            if (P > rc) {
                extraMoves += P - rc;
                P = rc;
            }
            int aa = rc - P;
            int bb = P - lc;
            if (aa > bb) {
                int tmp = aa;
                aa = bb;
                bb = tmp;
            }
            out.println(2 * aa + bb + changes + extraMoves);
        } else {
            out.println(0);
        }

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
