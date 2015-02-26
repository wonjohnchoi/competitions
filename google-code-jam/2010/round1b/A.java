import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static class Dir {
        String cur;
        public Dir(String cur) {
            this.cur = cur;
        }
        ArrayList<Dir> dirs = new ArrayList<Dir>();
        public int mkdir(String s) {
            ArrayList<String> names = new ArrayList<String>();
            for (String ss : s.split("/")) {
                if (!ss.isEmpty()) {
                    names.add(ss);
                }
            }
            return mkdir(names);
        }
        public int mkdir(ArrayList<String> s) {
            if (s.size() == 0) {
                return 0;
            }
            for (Dir d : dirs) {
                if (d.cur.equals(s.get(0))) {
                    s.remove(0);
                    return d.mkdir(s);
                }
            }
            dirs.add(new Dir(s.remove(0)));
            return dirs.get(dirs.size() - 1).mkdir(s) + 1;
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N, M;
            N = sc.nextInt();
            M = sc.nextInt();
            Dir root = new Dir(null);
            for (int i = 0; i < N; i++) {
                root.mkdir(sc.next());
            }
            int cnt = 0;
            for (int j = 0; j < M; j++) {
                cnt += root.mkdir(sc.next());
            }
            String ans = "" + cnt;
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
