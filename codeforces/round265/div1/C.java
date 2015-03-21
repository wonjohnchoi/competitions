import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static long MOD = (long) 1e9 + 7;
    static class Node {
        static Node EMPTY_LEAF = new Node(0);
        int i;
        List<Node> children = new ArrayList<Node>();
        Node(int i) {
            this.i = i;
        }
        long _ans = -3;
        long _size = -3;
        long ans() {
            if (_ans != -3) return _ans;
            if (children.size() == 0) {
                if (this == EMPTY_LEAF) return 0;
                return i;
            }
            long digits = 0;
            long ret = 0;
            for (int i = children.size() - 1; i >= 0; i--) {
                Node node = children.get(i);
                ret = (ret + (node.ans() * modPow(digits)) % MOD) % MOD;
                digits += node.size();
                digits %= MOD - 1;
            }
            _ans = ret;
            return ret;
        }
        long size() {
            if (_size != -3) return _size;
            if (children.size() == 0) {
                if (this == EMPTY_LEAF) return 0;
                return 1;
            }
            int ret = 0;
            for (int i = 0; i < children.size(); i++) {
                ret += children.get(i).size();
                ret %= MOD - 1;
            }
            _size = ret;
            return ret;
        }
    }
    static long modPow(long digits) {
        if (digits == 0) return 1;
        if (digits % 2 == 1) return 10L * modPow(digits - 1) % MOD;
        long c = modPow(digits / 2);
        return c * c % MOD;
    }
    static long[] tenPow = new long[200001];
    public static void main(String args[]) {
        long pow = 1;
        for (int i = 0; i < tenPow.length; i++) {
            tenPow[i] = pow;
            pow = (pow * 10) % MOD;
        }
        String S = in.next();
        int N = in.nextInt();
        nodes = new Node[10];
        Node root = new Node(-1);
        for (int i = 0; i < S.length(); i++) {
            root.children.add(get((int) S.charAt(i) - '0'));
        }
        for (int i = 0; i < N; i++) {
            String Q = in.next();
            int d = (int) Q.charAt(0) - '0';
            if (nodes[d] != null) {
                Node nd = nodes[d];
                nodes[d] = null;
                for (int j = 3; j < Q.length(); j++) {
                    nd.children.add(get((int) Q.charAt(j) - '0'));
                }
                if (Q.length() == 3) {
                    nd.children.add(Node.EMPTY_LEAF);
                }
            }
        }
        out.println(root.ans());
    }
    static Node[] nodes;
    static Node get(int i) {
        if (nodes[i] == null) {
            nodes[i] = new Node(i);
        }
        return nodes[i];
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
