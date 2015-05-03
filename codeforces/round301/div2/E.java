import java.util.*;
import java.io.*;
public class E {
    static void solve() {
        int N = in.nextInt();
        HashMap<Integer, Integer> posToNum = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = a;
            int d = b;
            if (posToNum.containsKey(a)) {
                c = posToNum.get(a);
            }
            if (posToNum.containsKey(b)) {
                d = posToNum.get(b);
            }
            posToNum.put(a, d);
            posToNum.put(b, c);
        }
        List<Integer> keys = new ArrayList<>();
        keys.addAll(posToNum.keySet());
        Collections.sort(keys);

        /*
        int[][][] cnt = new int[keys.size()][2][2];
        TreeSet<Integer> before = new TreeSet<>();
        for (int i = 0; i < keys.size(); i++) {
            int val = posToNum.get(keys.get(i));
            cnt[i][0][0] = before.lower(val);
            cnt[i][0][1] = before.higher(val);
            before.add(val);
        }
        before.clear();
        for (int i = keys.size() - 1; i >= 0; i--) {
            int val = posToNum.get(keys.get(i));
            cnt[i][1][0] = before.lower(val);
            cnt[i][1][1] = before.higher(val);
            before.add(val);
            }*/
        long tot = 0;
        for (int i = 0; i < keys.size(); i++) {
            int val = posToNum.get(keys.get(i));
            int pos = keys.get(i);
            // int val2 = posToNum.get(val);
            //int j = Collections.binarySearch(keys, val);
            tot += Math.abs(pos - val);

            /*
            if (pos == val) {
                tot += cnt[i][0][1] + cnt[i][1][0];
            } else if (pos > val) {
                int tmp = 0;
                if (j >= 1) tmp = cnt[j - 1][0][0];
                int tmp2 = 0;
                if (j >= 1) tmp2 = cnt[j - 1][0][1];
                tot += (pos - 1 - val + 1) - (cnt[i][0][0] - tmp) + tmp2;
                tot += cnt[i][1][0];
            } else {
                int tmp = 0;
                if (j + 1 < keys.size()) tmp = cnt[j + 1][1][1];
                int tmp2 = 0;
                if (j + 1 < keys.size()) tmp2 = cnt[j + 1][1][0];
                tot += (val - pos - 1 + 1) - (cnt[i][1][1] - tmp) + tmp2;
                tot += cnt[i][0][1];
                }*/
        }
        out.println(tot);
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
