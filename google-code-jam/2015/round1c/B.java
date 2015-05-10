import java.util.*;
import java.io.*;
public class B {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    static int[] preprocess(char[] ptrn) { // O(M)
	int i = 0, j = -1;
	int[] b = new int[ptrn.length + 1];
	b[i] = j;
	while (i < ptrn.length) {
	    while (j >= 0 && ptrn[i] != ptrn[j]) {
		j = b[j];
	    }
	    i++;
	    j++;
	    b[i] = j;
	}
	return b;
    }

    static int TC;
    static int K, L, S;
    static char[] keys, word;
    static double[][] cache;
    static int[] kmp;
    static double f(int s, int i) {
        if (s == 0) return 0;
        if (cache[s][i] == -1) {
            double tot = 0.0;
            for (int j = 0; j < K; j++) {
                int n;
                if (keys[j] == word[i]) {
                    if (i + 1 == L) { n = kmp[i + 1]; tot++; }
                    else n = i + 1;
                    // out.println(i + "=>" + (i + 1) + " with " + keys[j]);
                } else {
                    // out.println(i + "=>" + next + " with " + keys[j]);
                    n = kmp[i];
                }
                tot += f(s - 1, n);
            }
            tot /= K;
            cache[s][i] = tot;
            // out.println(s + " " + i + " " + cache[s][i]);
        }
        return cache[s][i];
    }
    static int fSmall(int i, char[] res) {
        if (i == S) {
            int cnt = 0;
            outer : for (int j = 0; j <= S - L; j++) {
                for (int k = j; k < j + L; k++) {
                    if (res[k] == word[k - j]) continue;
                    else continue outer;
                }
                cnt++;
            }
            //if (cnt > 0)
            //out.println(new String(res) + " " + cnt);
            return cnt;
        }
        int cnt = 0;
        for (int j = 0; j < K; j++) {
            res[i] = keys[j];
            cnt += fSmall(i + 1, res);
        }
        return cnt;
    }
    static int min() {
        outer : for (int i = 0; i < L; i++) {
            for (int j = 0; j < K; j++) {
                if (word[i] == keys[j]) {
                    continue outer;
                }
            }
            return 0;
        }
        int cnt = 0;
        char[] res = new char[S];
        Arrays.fill(res, ' ');
        outer : for (int i = 0; i <= S - L; i++) {
            for (int j = i; j < i + L; j++) {
                if (res[j] == ' ') res[j] = word[j - i];
                else if (res[j] == word[j - i]) continue;
                else continue outer;
            }
            cnt++;
        }
        return cnt;
    }
    static String solve(int tc) {
        K = in.nextInt();
        L = in.nextInt();
        S = in.nextInt();
        keys = in.next().toCharArray(); // length K
        word = in.next().toCharArray(); // length L
        kmp = preprocess(word);
        kmp[0] = 0;
        cache = new double[101][101];
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }
        int min = min();
        // double used = f(S, 0); // B-small.out. need to figure out why this didn't work.
        int tot = fSmall(0, new char[S]);
        // out.println(tot);
        double used = (double) tot / Math.pow(K, S); // B-small.out.1
        // out.println(new String(keys) + " " + new String(word));
        // out.println(min + " " + used);
        return (min - used) + "";
    }
    public static void main(String args[]) {
        TC = in.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            out.printf("Case #%d: %s\n", tc, solve(tc));
        }
        out.close();
    }
    static void d(Object o) {
        out.println(o);
        out.flush();
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
