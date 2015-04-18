import java.util.*;
import java.io.*;
public class C {
    public static PrintWriter out = new PrintWriter(System.out);
    public static InputReader in = new InputReader(System.in);
    static int TC;
    static int N;
    static String solve(int tc) {
        N = in.nextInt();
        Point[] p = new Point[N];
        for (int i = 0; i < N; i++) {
            p[i] = new Point(in.nextInt(), in.nextInt());
            p[i].i = i;
        }
        res = new boolean[N + 1][N];
        all(0, p);
        String ans = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N + 1; j++) {
                if (res[j][i]) {
                    ans += "\n";
                    ans += j;
                    break;
                }
            }
        }
        return ans;
    }
    static boolean[][] res;
    static void all(int k, Point[] p) {
        if (p.length == 0) return;
        if (k == p.length) {
            List<Point> ch = convexHull(new ArrayList<Point>(Arrays.asList(p)), true);
            for (Point pp : ch) {
                res[N - p.length][pp.i] = true;
            }
            return;
        }
        Point[] p2 = new Point[p.length - 1];
        for (int i = 0; i < p2.length; i++) {
            if (i >= k) {
                p2[i] = p[i + 1];
            } else {
                p2[i] = p[i];
            }
        }
        all(k + 1, p);
        all(k, p2);
    }
    public static void main(String args[]) {
        TC = in.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            out.printf("Case #%d:%s\n", tc, solve(tc));
        }
        out.close();
    }
    static void d(Object o) {
        out.println(o);
        out.flush();
    }
    static class Point {
        final long x, y;
        int i;
        Point(long xx, long yy) {
            x = xx;
            y = yy;
        }
        long distanceSquared(Point p) {
            return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y);
        }
        Point subtract(Point p) {
            return new Point(x - p.x, y - p.y);
        }
        long cross(Point p) {
            return x * p.y - y * p.x;
        }
    }
    static List<Point> convexHull(List<Point> ps, boolean onEdge) {
        List<Point> ch = new ArrayList<>();
        int N = ps.size();
        if (N < 3) return new ArrayList<Point>(ps);
        int start = 0;
        for (int i = 1; i < N; i++) {
            if (ps.get(i).x < ps.get(start).x
                || (ps.get(i).x == ps.get(start).x && ps.get(i).y > ps.get(start).y))
                start = i;
        }
        List<Point> ret = new ArrayList<>();
        boolean[] used = new boolean[N];
        int cur = start;
        do {
            int next = -1;
            long dist = onEdge ? Long.MAX_VALUE : 0;
            for (int i = 0; i < N; i++) {
                if (i == cur) continue;
                if (used[i]) continue;
                if (next == -1) next = i;
                Point ic = ps.get(i).subtract(ps.get(cur));
                Point nc = ps.get(next).subtract(ps.get(cur));
                long cross = ic.cross(nc);
                long d = ps.get(cur).distanceSquared(ps.get(i));
                if (cross < 0) {
                    next = i;
                    dist = d;
                } else if (cross == 0) {
                    if (onEdge && d < dist) {
                        next = i;
                        dist = d;
                    } else if (!onEdge && d > dist) {
                        next = i;
                        dist = d;
                    }
                }
            }
            cur = next;
            used[cur] = true;
            ret.add(ps.get(cur));
        } while (start != cur);
        return ret;
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
