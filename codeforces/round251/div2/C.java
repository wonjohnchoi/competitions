import java.util.*;
import java.io.*;
public class C {
    static class Node {
        int num;
        int cnt;
        List<Node> nodes = new LinkedList<>();
        Node(int num) {
            this.num = num;
            cnt = 1;
        }
        Node add(Node n) {
            nodes.add(n);
            cnt += n.cnt;
            return this;
        }
        void print(boolean root) {
            if (root) out.print(cnt);
            out.print(" " + num);
            for (Node n : nodes) {
                n.print(false);
            }
        }
        void print() { print(true); }
    }
    static void solve() {
        int N, K, P;
        N = in.nextInt();
        K = in.nextInt();
        P = in.nextInt();
        List<Node> evens = new LinkedList<>();
        List<Node> odds = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int a = in.nextInt();
            int j = a % 2;
            if (j == 0) evens.add(new Node(a));
            else odds.add(new Node(a));
        }
        int aa = (K + P - 2 * evens.size() - odds.size());
        if (aa % 2 != 0) {
            out.println("NO");
            return;
        }
        int a = -aa / 2;
        int b = P - evens.size() + a;
        // out.println(a + " " + b);
        if (a < 0 || b < 0) {
            out.println("NO");
            return;
        }
        if (odds.size() == 0 && P == 0 && evens.size() >= 1) {
            out.println("NO");
            return;
        }
        for (int i = 0; i < b; i++) {
            evens.add((odds.remove(0)).add(odds.remove(0)));
        }
        for (int i = 0; i < a; i++) {
            if (evens.size() >= 2) {
                evens.add(evens.remove(0).add(evens.remove(0)));
            } else if (evens.size() >= 1 && odds.size() >= 1) {
                odds.add(evens.remove(0).add(odds.remove(0)));
            } else {
                out.println("NO");
                return;
            }
        }
        out.println("YES");
        for (Node even : evens) {
            even.print();
            out.println();
        }
        for (Node odd : odds) {
            odd.print();
            out.println();
        }
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
