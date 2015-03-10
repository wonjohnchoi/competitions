import java.util.*;
import java.io.*;
public class TaroFillingAStringDiv1 {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static class Node implements Comparable<Node> {
        char c;
        int pos;
        public Node(char cc, int ppos) {
            c = cc;
            pos = ppos;
        }
        @Override
            public int compareTo(Node n) {
            return pos - n.pos;
        }
    }
    public static int getNumber(int N, int[] position, String value) {
        Node[] nodes = new Node[position.length];
        for (int i = 0; i < position.length; i++) {
            nodes[i] = new Node(value.charAt(i), position[i] - 1);
        }
        Arrays.sort(nodes);
        long ret = 1;
        long MOD = (long) (1e9) + 7;
        for (int i = 0; i < nodes.length - 1; i++) {
            Node n2 = nodes[i + 1];
            Node n1 = nodes[i];
            int dist = n2.pos - n1.pos - 1;
            boolean same = n2.c == n1.c;
            if ((same && (dist % 2 == 0))
                || (!same && (dist % 2 == 1))) {
                ret = (ret * (dist + 1)) % MOD;
            }
        }
        return (int) ret;
    }
    public static void main(String args[]) {
        out.println(getNumber(3, new int[] {1, 3}, "AB"));
        out.println(getNumber(25, new int[] {23, 4, 8, 1, 24, 9, 16, 17, 6, 2, 25, 15, 14, 7, 13}, "ABBBBABABBAAABA"));
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
