import java.util.*;
import java.io.*;
public class E {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        int K, N;
        N = in.nextInt();
        K = in.nextInt();
        String[] a = new String[N + K];
        for (int i = 0; i < N; i++) {
            a[i] = in.next();
        }
        for (int i = N; i < N + K; i++) {
            a[i] = "" + Integer.MAX_VALUE;
        }
        // out.println(N + " " + K);
        N += K;
        for (int i = 0; i < K; i++) {
            long prev = Integer.MIN_VALUE;
            int q = 0;
            for (int j = i; j < N; j += K) {
                if (a[j].equals("?")) {
                    q++;
                } else {
                    long cur = Long.parseLong(a[j]);
                    long room = (cur - 1) - (prev + 1) + 1;
                    if (cur > prev && room >= q) {
                        // out.println(prev + " " + cur + " " + q);
                        if (q == 0) {
                            // NOTHING
                        } else if (prev >= 0) {
                            int l2 = j;
                            for (int l = 0; l < q; l++) {
                                l2 -= K;
                                a[l2] = "" + (prev + q - l);
                            }
                            q = 0;
                        } else if (cur <= 0) {
                            int l2 = j;
                            for (int l = 0; l < q; l++) {
                                l2 -= K;
                                a[l2] = "" + (cur - l - 1);
                            }
                            q = 0;
                        } else {
                            ArrayList<Long> vals = new ArrayList<Long>();
                            long curr = 1;
                            vals.add(0L);
                            while (vals.size() < q) {
                                if (prev < curr && curr < cur) {
                                    vals.add(curr);
                                }
                                if (curr > 0) {
                                    curr = -curr;
                                } else {
                                    curr = -curr;
                                    curr++;
                                }
                            }
                            Collections.sort(vals);
                            int l2 = j;
                            for (int l3 = 0; l3 < q; l3++) {
                                l2 -= K;
                                a[l2] = "" + vals.get(q - l3 - 1);
                            }
                            q = 0;
                        }
                    } else {
                        NO();
                    }
                    prev = cur;
                }
            }
        }
        // out.println(N + " " + K);
        for (int i = 0; i < N - K; i++) {
            if (i != 0) out.print(" ");
            out.print(a[i]);
        }
        out.println();
        out.close();
    }
    static void NO() {
        out.println("Incorrect sequence");
        out.close();
        System.exit(0);
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
