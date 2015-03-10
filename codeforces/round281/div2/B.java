import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        long tot = 0;
        boolean last = false;
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int p = in.nextInt();
            tot += p;
            last = p > 0;
            if (p > 0) {
                pos.add(p);
            } else {
                neg.add(-p);
            }
        }
        boolean betterSeq = false;
        boolean worseSeq = false;
        int i;
        for (i = 0; i < Math.min(pos.size(), neg.size()); i++) {
            if (pos.get(i) == neg.get(i)) continue;
            if (pos.get(i) > neg.get(i)) {
                betterSeq = true;
                break;
            } else {
                worseSeq = true;
                break;
            }
        }
        if (!(betterSeq || worseSeq)) {
            if (pos.size() < neg.size()) {
                betterSeq = true;
            } else if (pos.size() > neg.size()) {
                worseSeq = true;
            }
        }
        if (tot > 0
            || (tot == 0 && betterSeq)
            || (tot == 0 && !(worseSeq || betterSeq) && last)) {
            out.println("first");
        } else {
            out.println("second");
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
