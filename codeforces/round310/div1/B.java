import java.util.*;
import java.io.*;
public class B {
    static class Seg implements Comparable<Seg> {
        long a, b;
        int idx;
        int bidx;
        @Override
            public int compareTo(Seg s) {
            return Long.compare(b, s.b);
        }
    }
    static class Bridge implements Comparable<Bridge> {
        long len;
        int idx;
        @Override
            public int compareTo(Bridge b) {
            return Long.compare(len, b.len);
        }
    }
    static void solve() {
        int N = in.nextInt();
        int M = in.nextInt();
        Seg[] segs = new Seg[N - 1];
        long[] ll = new long[N];
        long[] rr = new long[N];
        for (int i = 0; i < N; i++) {
            long l = in.nextLong();
            long r = in.nextLong();
            ll[i] = l;
            rr[i] = r;
            if (i > 0) {
                long a, b;
                a = l - rr[i - 1];
                b = r - ll[i - 1];
                Seg seg = new Seg();
                seg.a = a;
                seg.b = b;
                seg.idx = i;
                segs[i - 1] = seg;
            }
        }
        Arrays.sort(segs);
        Bridge[] br = new Bridge[M];
        for (int i = 0; i < M; i++) {
            br[i] = new Bridge();
            br[i].len = in.nextLong();
            br[i].idx = i + 1;
        }
        Arrays.sort(br);
        int bi = 0;
        TreeSet<Bridge> ts = new TreeSet<>();
        for (int i = 0; i < N - 1; i++) {
            Seg seg = segs[i];
            while (bi < M && br[bi].len <= seg.b) {
                ts.add(br[bi]);
                bi++;
            }
            Bridge finder = new Bridge();
            finder.len = seg.a;
            Bridge bb = ts.ceiling(finder);
            if (bb == null) {
                out.println("No");
                return;
            } else {
                ts.remove(bb);
                seg.bidx = bb.idx;
            }
        }
        Arrays.sort(segs, new Comparator<Seg>() {
                public int compare(Seg s1, Seg s2) {
                    return Integer.compare(s1.idx, s2.idx);
                }
            });
        out.println("Yes");
        for (int i = 0; i < N - 1; i++) {
            if (i != 0) out.print(" ");
            out.print(segs[i].bidx);
        }
        out.println();
    }
    static PrintWriter out = new PrintWriter(System.out);
    static InputReader in = new InputReader(System.in);
    static void d(Object o) {
        out.println(o);
        out.flush();
    }
    public static void main(String args[]) {
        solve();
        out.close();
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
