import java.util.*;
import java.io.*;
public class D {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static class Monster implements Comparable<Monster> {
        int n, i;
        String p = null;
        Monster(int nn, int ii) {
            n = nn;
            i = ii;
        }
        @Override
        public int compareTo(Monster m) {
            return n - m.n;
        }
    }
    public static void main(String args[]) {
        int N, X, Y;
        N = in.nextInt();
        X = in.nextInt();
        Y = in.nextInt();
        Monster[] n = new Monster[N];
        for (int i = 0; i < N; i++) {
            n[i] = new Monster(in.nextInt(), i);
        }
        Arrays.sort(n);
        int ni = 0;
        long x = 1;
        long y = 1;
        int hits = 0;
        while (ni < N) {
            int xHit = (int) ((double) (n[ni].n - hits) * X / (X + Y)) - 100;
            int yHit = (int) ((double) (n[ni].n - hits) * Y / (X + Y)) - 100;
            xHit = Math.max(xHit, 0);
            yHit = Math.max(yHit, 0);
            x += xHit;
            y += yHit;
            hits += xHit + yHit;
            if (x * Y == y * X) {
                while (ni < N && hits + 1 <= n[ni].n && n[ni].n <= hits + 2) {
                    n[ni].p = "Both";
                    ni++;
                }
                hits += 2;
                x++;
                y++;
            } else if (x * Y > y * X) {
                while (ni < N && hits + 1 == n[ni].n) {
                    n[ni].p = "Vova";
                    ni++;
                }
                hits++;
                y++;
            } else {
                while (ni < N && hits + 1 == n[ni].n) {
                    n[ni].p = "Vanya";
                    ni++;
                }
                hits++;
                x++;
            }
        }
        Arrays.sort(n, new Comparator<Monster>() {
                @Override
                public int compare(Monster m1, Monster m2) {
                    return m1.i - m2.i;
                }
            });
        for (int i = 0; i < N; i++) {
            out.println(n[i].p);
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
