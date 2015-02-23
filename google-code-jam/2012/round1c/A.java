import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static int N;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            List[] graph = new List[N];
            for (int i = 0; i < N; i++) {
                int M = sc.nextInt();
                graph[i] = new ArrayList();
                for (int j = 0; j < M; j++) {
                    graph[i].add(sc.nextInt() - 1);
                }
            }
            boolean yes = false;
            for (int i = 0; i < N && !yes; i++) {
                int[] tot = new int[N];
                boolean[] visited = new boolean[N];
                visited[i] = true;
                tot[i] = 1;
                for (int j = 0; j < N; j++) {
                    if (tot[j] + (visited[j] ? 1 : 0) >= 2 && i != j) {
                        yes = true;
                        break;
                    } else if (tot[j] >= 1) {
                        for (Object o : graph[j]) {
                            int next = (Integer) o;
                            tot[next] += tot[j];
                        }
                        tot[j] = 0;
                        visited[j] = true;
                    }
                }
            }
            /* works for small
            boolean[] visited = new boolean[N];
            for (int i = 0; i < N && !yes; i++) {
                for (int j = 0; j < N && !yes; j++) {
                    tot = 0;
                    visited[i] = true;
                    yes(graph, i, j, visited);
                    if (tot >= 2) {
                        yes = true;
                    }
                    visited[i] = false;
                }
                }*/
            String ans = yes ? "Yes" : "No";
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    public static int yes1(List[] graph, int i, int j) {
        // boolean[] visited = new boolean[N];
        List<Integer> frontiers = new ArrayList<Integer>();
        frontiers.add(i);
        // visited[i] = true;
        int tot = 0;
        while (!frontiers.isEmpty()) {
            int cur = frontiers.remove(0);
            for (Object o : graph[cur]) {
                int next = (Integer) o;
                if (next == j) {
                    tot++;
                    if (tot >= 2) return 2;
                } else {
                    frontiers.add(next);
                }
            }
        }
        return tot;
    }
    static int tot;
    public static void yes(List[] graph, int i, int j, boolean[] visited) {
        if (i == j) {
            tot++;
            return;
        }
        if (tot >= 2) return;
        for (Object o : graph[i]) {
            int next = (Integer) o;
            if (!visited[next]) {
                visited[next] = true;
                yes(graph, next, j, visited);
                visited[next] = false;
            }
        }
    }
}
