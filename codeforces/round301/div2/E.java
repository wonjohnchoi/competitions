import java.util.*;
import java.io.*;
public class E {
    static class BinaryIndexedTree {
        int length;
        int logLength;
        int[] c;
        BinaryIndexedTree(int n) {
            logLength = Integer.SIZE - Integer.numberOfLeadingZeros(n);
            length = 1 << logLength;
            c = new int[length + 1];
        }
        void add(int x, int num) {
            if (!(x >= 1 && x <= length)) throw new AssertionError();
            for (; x <= length; x += Integer.lowestOneBit(x)) {
                c[x] += num;
            }
        }
        void insert(int x) {
            add(x, 1);
        }
        void remove(int x) {
            add(x, -1);
        }
        int sum(int x) {
            if (!(x >= 0 && x <= length)) throw new AssertionError();
            int sum = 0;
            for (; x > 0; x -= Integer.lowestOneBit(x)) {
                sum += c[x];
            }
            return sum;
        }
        int sum(int l, int r) {
            return sum(r) - sum(l - 1);
        }
        int rank(int x) {
            return sum(x - 1) + 1;
        }
        int select(int k) {
            int ans = 0;
            for (int i = logLength; i >= 0; i--) {
                ans += (1 << i);
                if (c[ans] < k) {
                    k -= c[ans];
                } else {
                    ans -= (1 << i);
                }
            }
            return ans + 1;
        }
    }
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
        HashMap<Integer, Integer> numToPos = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            int pos = keys.get(i);
            int val = posToNum.get(pos);
            numToPos.put(val, pos);
        }
        HashMap<Integer, Integer> keyToIdx = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            keyToIdx.put(keys.get(i), i);
        }
        BinaryIndexedTree bit = new BinaryIndexedTree(keys.size());
        int[][] cnt = new int[keys.size()][2];
        for (int i = 0; i < keys.size(); i++) {
            int pos = keys.get(i);
            int val = posToNum.get(pos);
            int posI = i + 1;
            int valI = keyToIdx.get(val) + 1;
            cnt[i][0] = bit.sum(posI);
            cnt[i][1] = bit.sum(valI);
            out.println(cnt[i][0] + " " + cnt[i][1]);
            bit.insert(valI);
        }
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
            int pos = keys.get(i);
            int val = posToNum.get(pos);
            int posI = i + 1;
            int valI = keyToIdx.get(val) + 1;
            int a = Math.max(pos - val, 0);
            int b = Math.max(i - 1, 0) - cnt[i][0];
            int c = cnt[i][1] - cnt[valI - 1][0];
            tot += a + b + c;
            out.println(pos + " " + val);
            out.println(a + " " + b + " " + c);
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
