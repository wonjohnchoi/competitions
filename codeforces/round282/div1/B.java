import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    public static void main(String args[]) {
        char[] s = in.next().toCharArray();
        char[] t = in.next().toCharArray();
        int N = s.length;
        long MOD = (long) 1e9 + 7;
        long[] ans = new long[N + 1];
        long[] acc = new long[N + 1];
        ans[N] = 1;
        acc[N] = 1;
        List<Integer> idx = search(s, t);
        if (idx.size() == 0) {
            out.println(0);
            System.exit(0);
        }
        int idxPtr = idx.size() - 1;
        for (int i = N - 1; i >= 0; i--) {
            while (idxPtr >= 1 && idx.get(idxPtr - 1) >= i) idxPtr--;
            if (idx.get(idxPtr) < i) {
                ans[i] = ans[i + 1];
            } else {
                int k = idx.get(idxPtr);
                ans[i] = (ans[i + 1] + acc[k + t.length]) % MOD;
            }
            acc[i] = (acc[i + 1] + ans[i]) % MOD;
        }
        // out.println("acc: " + Arrays.toString(acc));
        // out.println("ans: " + Arrays.toString(ans));
        out.println((ans[0] - 1 + MOD) % MOD);
    }
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
    static List<Integer> search(char[] text, char[] ptrn) { // O(N)
        ArrayList<Integer> pos = new ArrayList<Integer>();
	int i = 0, j = 0;
	int[] b = preprocess(ptrn);
	while (i < text.length) {
	    while (j >= 0 && text[i] != ptrn[j]) {
		j = b[j];
	    }
	    i++;
	    j++;
	    if (j == ptrn.length) {
                pos.add(i - ptrn.length);
		j = b[j];
	    }
	}
        return pos;
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
