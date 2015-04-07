import java.util.*;
import java.io.*;
public class D {
    static class Node {
        long xor = -1;
        List<Long> nums = new ArrayList<>();
        void add(long n) {
            if (xor == -1) xor = n;
            else xor ^= n;
            nums.add(n);
        }
        Node better(Node n) {
            if (xor == -1) return n;
            if (n.xor == -1) return this;
            if (xor < n.xor) return this;
            return n;
        }
        Node copy() {
            Node n = new Node();
            n.nums = new ArrayList<>(nums);
            return n;
        }
    }
    static void solve() {
        long L, R, K;
        L = in.nextLong();
        R = in.nextLong();
        K = in.nextLong();
        Node ans = null;
        if (K == 1) {
            Node one = new Node();
            one.add(L);
            ans = one;
        } else if (K == 2) {
            Node one = new Node();
            one.add(L);
            Node two = two(L, R).better(one);
            ans = two;
        } else if (K == 3) {

        } else if (R - L <= 24) {
            List<Node> all = new ArrayList<>();
            all.add(new Node());
            find(L, R, K, all);
            Node best = new Node();
            for (Node n : all) {
                // out.println("xor: " + n.xor);
                best = best.better(n);
            }
            ans = best;
        } else {
            Node n = new Node();
            long cur = L;
            while (true) {
                if (cur % 16 == 3) {
                    n.add(cur);
                    n.add(cur + 3);
                    n.add(cur + 6);
                    n.add(cur + 9);
                    break;
                }
                cur++;
            }
            ans = n;
        }
        out.println(ans.xor);
        out.println(ans.nums.size());
        if (ans.nums.size() == 0) return;
        for (int i = 0; i < ans.nums.size(); i++) {
            if (i != 0) out.print(" ");
            out.print(ans.nums.get(i));
        }
        out.println();
    }
    static void find(long L, long R, long K, List<Node> all) {
        if (L > R) return;
        List<Node> all2 = new ArrayList<>();
        for (Node n : all) {
            if (n.nums.size() < K) {
                all2.add(n.copy());
                n.add(L);
            }
        }
        all.addAll(all2);
        find(L + 1, R, K, all);
    }
    static Node two(long L, long R) {
        Node ret = new Node();
        if (R == L + 1) {
            ret.add(L);
            ret.add(L + 1);
            return ret;
        }
        Node ret2 = new Node();
        ret2.add(L + 1);
        ret2.add(L + 2);
        return ret.better(ret2);
    }
    static Node one(long L, long R) {
        Node ret = new Node();
        long cur = L;
        while (Long.bitCount(cur) >= 2) {
            long next = next(cur);
            if (next > R) {
                break;
            }
            cur = next;
        }
        ret.add(cur);
        return ret;
    }
    static long next(long v) {
        int cnt = 0;
        long i = 0;
        while (true) {
            while ((v & (1L << i)) == 1) {
                cnt++;
                i++;
            }
            if (cnt >= 2) break;
            while ((v & (1L << i)) == 0) {
                i++;
            }
        }
        v += 1L << i;
        return v;
    }
    static PrintWriter out = new PrintWriter(System.out);
    static InputReader in = new InputReader(System.in);
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
    /*
    public int DONTUSEnextInt() {
	return Integer.parseInt(next());
        }*/
}
