import java.util.*;
import java.io.*;
public class C {
    static void solve() {
        int N = in.nextInt();
        int[] a = new int[N];
        int minX, minY, maxX, maxY;
        minX = 0;
        maxX = 0;
        minY = 0;
        maxY = 0;
        int x, y;
        x = y = 0;
        int mult = 1;
        for (int i = 0; i < N; i++) {
            a[i] = in.nextInt();
            x += a[i];
            y += a[i] * mult;
            mult *= -1;
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }
        char[][] b = new char[maxY - minY][maxX]; // minX == 0
        for (int i = 0; i < b.length; i++) {
            Arrays.fill(b[i], ' ');
        }
        x = 0;
        y = -minY;
        mult = 1;
        // out.println(b.length + " " + b[0].length);
        for (int i = 0; i < N; i++) {
            if (mult == 1) {
                for (int j = 0; j < a[i]; j++) {
                    // d(x + " " + y + " /");
                    b[y++][x++] = '/';
                }
                x--; y--;
                x++;
            }
            else {
                // d(x + " " + y + " \\");
                for (int j = 0; j < a[i]; j++) {
                    b[y--][x++] = '\\';
                }
                x--; y++;
                x++;
            }
            mult *= -1;
        }
        for (int i = b.length - 1; i >= 0; i--) {
            out.println(new String(b[i]));
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
