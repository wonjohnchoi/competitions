import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int N = in.nextInt();
        int[] adj = new int[1000001];
        int[] back = new int[1000001];
        Arrays.fill(adj, -1);
        Arrays.fill(back, -1);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            adj[a] = b;
            back[b] = a;
        }
        int s = adj[0];
        ArrayList<Integer> line = new ArrayList<Integer>();
        while (s != -1 && s != 0) {
            line.add(s);
            s = adj[s];
        }
        int s2 = -1;
        ArrayList<Integer> line2 = new ArrayList<Integer>();
        for (int i = 0; i < adj.length; i++) {
            if (adj[i] != -1 && i != 0 && !line.contains(i)) {
                s2 = i;
                break;
            }
        }
        while (back[s2] != -1) s2 = back[s2];
        while (s2 != -1 && s2 != 0) {
            line2.add(s2);
            s2 = adj[s2];
        }
        int i = 0;
        int j = 0;
        while (true) {
            if (i != 0) {
                out.print(" ");
            }
            if (line2.size() == i) break;
            out.print(line2.get(i++));
            out.print(" ");
            if (line.size() == j) break;
            out.print(line.get(j++));
        }
        out.println();
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
