import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static class Node implements Comparable<Node> {
        int i, h;
        Node(int ii, int hh) {
            i = ii; h = hh;
        }
        public int compareTo(Node n) {
            return Integer.compare(h, n.h);
        }
    }
    public static void main(String args[]) {
        int N = in.nextInt();
        int[] a = new int[N];
        Node[] nodes = new Node[N];
        int[] c = new int[2001];
        boolean[] u = new boolean[N];
        int two = 0;
        int three = 0;
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
            nodes[i] = new Node(i + 1, a[i]);
            c[a[i]]++;
            if (c[a[i]] == 2) two++;
            else if (c[a[i]] == 3) three++;
        }
        Arrays.sort(nodes);
        int[][] ans = new int[3][N];
        int p = 0;
        if (two >= 2 || three >= 1) {
            for (int i = 0; i < N; ) {
                int s = nodes[i].h;
                int j = i;
                while (j < N && nodes[j].h == s) {
                    j++;
                }
                if (j - i >= 1) {
                    for (int k = i; k < j; k++) {
                        for (int l = 0; l < 3; l++) {
                            int m;
                            int l2 = l;
                            if (p == 1) {
                                if (l == 2) l2 = 1;
                            }
                            m = l2 + k;
                            while (m >= j) {
                                m = m - j + i;
                            }
                            ans[l][k] = nodes[m].i;
                        }
                    }
                    if (j - i >= 2)
                        p++;
                }
                i = j;
            }
            out.println("YES");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < N; j++) {
                    if (j != 0) out.print(" ");
                    out.print(ans[i][j]);
                }
                out.println();
            }
        } else {
            out.println("NO");
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
