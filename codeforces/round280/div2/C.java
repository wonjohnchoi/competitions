import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static int N, R, AVG;
    static class Exam implements Comparable<Exam> {
        int a, b;
        Exam(int aa, int bb) {
            a = aa;
            b = bb;
        }
        @Override
            public int compareTo(Exam e) {
            return b - e.b;
        }
    }
    public static void main(String args[]) {
        N = in.nextInt();
        R = in.nextInt();
        AVG = in.nextInt();
        Exam[] exams = new Exam[N];
        long tot = (long) AVG * N;
        // out.println(tot);
        for (int i = 0; i < N; i++) {
            exams[i] = new Exam(in.nextInt(), in.nextInt());
            tot -= exams[i].a;
        }
        Arrays.sort(exams);
        // out.println(tot);
        long price = 0;
        for (int i = 0; i < N && tot > 0; i++) {
            if (exams[i].a < R) {
                long used = Math.min(tot, R - exams[i].a);
                tot -= used;
                exams[i].a += used;
                price += used * exams[i].b;
                // out.println(used + " " + exams[i].a + " " + exams[i].b);
            }
        }
        out.println(price);
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
