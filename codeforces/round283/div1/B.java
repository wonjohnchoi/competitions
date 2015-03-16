import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static Set<Integer> poss = new HashSet<Integer>();
    public static void main(String args[]) {
        int N = in.nextInt();
        int[] b = new int[N];
        for (int i = 0; i < N; i++) {
            b[i] = in.nextInt();
        }
        int last = b[N - 1];
        int cnt = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (b[i] == last) cnt++;
            else {
                add(cnt);
            }
        }
        add(cnt);
        List<Res> res = new ArrayList<>();
        for (int t : poss) {
            int[] points = new int[2];
            int[] wins = new int[2];
            for (int i = 0; i < N; i++) {
                int j = b[i] % 2;
                points[j]++;
                if (points[j] == t) {
                    wins[j]++;
                    points = new int[2];
                }
            }
            if (points[last % 2] != 0 || wins[(last + 1) % 2] >= wins[last % 2]) continue;
            res.add(new Res(t, wins[last % 2]));
        }
        Collections.sort(res);
        out.println(res.size());
        for (Res res2 : res) {
            out.println(res2.s + " " + res2.t);
        }
    }
    static class Res implements Comparable<Res> {
        int t, s;
        Res(int tt, int ss) {
            t = tt;
            s = ss;
        }
        public int compareTo(Res r) {
            if (s == r.s) return t - r.t;
            return s - r.s;
        }
    }
    static void add(int num) {
        List<Integer> divs = new ArrayList<Integer>();
        List<Integer> cnts = new ArrayList<Integer>();
        int div = 2;
        // out.println("num :" + num);
        while (div * div <= num) {
            if (num % div == 0) {
                divs.add(div);
                int cnt = 0;
                while (num % div == 0) {
                    cnt++; num /= div;
                }
                cnts.add(cnt);
                // out.println("div: " + div + " cnt: " + cnt);
            }
            div++;
        }
        if (num != 1) {
            divs.add(num);
            cnts.add(1);
        }
        add(divs, cnts, 0, 1);
    }
    static void add(List<Integer> divs, List<Integer> cnts, int i, int num) {
        if (divs.size() == i) {
            poss.add(num);
            return;
        }
        for (int j = 0; j < cnts.get(i) + 1; j++) {
            add(divs, cnts, i + 1, num);
            num *= divs.get(i);
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
