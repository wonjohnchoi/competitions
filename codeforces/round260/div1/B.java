import java.util.*;
import java.io.*;
public class B {
    static class ST {
        Character cur;
        HashMap<Character, ST> children = new HashMap<>();
        ST(Character cur) {
            this.cur = cur;
        }
        void insert(String s) {
            if (s.length() == 0) return;
            char c = s.charAt(0);
            String child = s.substring(1);
            if (!children.containsKey(c)) {
                children.put(c, new ST(c));
            }
            children.get(c).insert(child);
        }
        boolean res(boolean fw, boolean sw) {
            if (children.size() == 0) return false;
            for (Map.Entry<Character, ST> child : children.entrySet()) {
                ST st = child.getValue();
                boolean cr = st.res(sw, fw);
                if (fw && !cr) return true;
                if (!fw && cr) return false;
            }
            return !fw;
        }
    }
    static void solve() {
        int N, K;
        N = in.nextInt();
        K = in.nextInt();
        ST st = new ST(null);
        for (int i = 0; i < N; i++) {
            st.insert(in.next());
        }
        boolean first = false;
        boolean ww = st.res(true, true);
        boolean ll = st.res(false, false);
        // out.println(ww + " " + wl + " " + lw + " " + ll);
        if (!ll && ww) first = true;
        if (ww && ll && K % 2 == 1) first = true;
        if (first) out.println("First");
        else out.println("Second");
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
