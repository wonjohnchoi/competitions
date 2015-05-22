import java.util.*;
import java.io.*;
public class B {
    static class Node implements Comparable<Node> {
        int cost, val;
        Node (int c, int v) {
            cost = c;
            val = v;
        }
        @Override
            public int compareTo(Node n) {
            int r = n.val - val;
            if (r == 0) r = cost - n.cost;
            return r;
        }
    }
    static void solve() {
        char[] num = in.next().toCharArray();
        int k = in.nextInt();
        int i = 0;
        while (k > 0 && i < num.length) {
            List<Node> nodes = new ArrayList<>();
            for (int j = i; j < num.length; j++) {
                nodes.add(new Node(j - i, (int) num[j] - '0'));
            }
            Collections.sort(nodes);
            for (Node use : nodes) {
                if (k < use.cost) continue;
                //out.println(i + " " + use.cost + " " + use.val);
                k -= use.cost;
                char tmp = num[use.cost + i];
                for (int j = use.cost + i; j > i; j--) {
                    num[j] = num[j - 1];
                }
                num[i] = tmp;
                break;
            }
            i++;
        }
        out.println(new String(num));
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
