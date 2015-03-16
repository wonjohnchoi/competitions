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
        List<Integer> idx = searchSubString(s, t);
        for (int i = N - 1; i >= 0; i--) {
            int cur = 0;
            int j  = Collections.binarySearch(idx, i);
            if (j < 0) j = -(j + 1);
            if (j == idx.size()) {
                ans[i] = 0;
            } else {
                int k = idx.get(j);
                ans[i] = (k - i + 1) * acc[k + t.length] % MOD;
            }
            acc[i] = acc[i + 1] + ans[i];
        }
        out.println("acc: " + Arrays.toString(acc));
        out.println("ans: " + Arrays.toString(ans));
        out.println(ans[0]);
    }
    static int[] preProcessPattern(char[] ptrn) {
	int i = 0, j = -1;
	int ptrnLen = ptrn.length;
	int[] b = new int[ptrnLen + 1];
	b[i] = j;
	while (i < ptrnLen) {
	    while (j >= 0 && ptrn[i] != ptrn[j]) {
		// if there is mismatch consider the next widest border
		// The borders to be examined are obtained in decreasing order from
		//  the values b[i], b[b[i]] etc.
		j = b[j];
	    }
	    i++;
	    j++;
	    b[i] = j;
	}
	return b;
    }
    /**
     * Based on the pre processed array, search for the pattern in the text
     *
     * @param text
     *            text over which search happens
     * @param ptrn
     *            pattern that is to be searched
     */
    static List<Integer> searchSubString(char[] text, char[] ptrn) {
	int i = 0, j = 0;
	// pattern and text lengths
	int ptrnLen = ptrn.length;
	int txtLen = text.length;
	// initialize new array and preprocess the pattern
	int[] b = preProcessPattern(ptrn);
        ArrayList<Integer> idx = new ArrayList<Integer>();
	while (i < txtLen) {
	    while (j >= 0 && text[i] != ptrn[j]) {
		j = b[j];
	    }
	    i++;
	    j++;
	    // a match is found
	    if (j == ptrnLen) {
                idx.add(i - ptrnLen);
		System.out.println("found substring at index:" + (i - ptrnLen));
		j = b[j];
	    }
	}
        return idx;
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
