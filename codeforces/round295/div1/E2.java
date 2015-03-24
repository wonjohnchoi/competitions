import java.util.*;
import java.io.*;
public class E2 {
    // solution from http://codeforces.ru/contest/521/submission/10117526
    public static PrintWriter out = new PrintWriter(System.out);
    static boolean[] marked;
    static int[][] cycleV;
    static int[] dist;
    static int[] parent;
    static void printRes(int v, int u, int a, int b) {
        // out.println(v + " " + u + " " + a + " " + b);
        if (dist[u] > dist[b]) {
            int tmp = u; u = b; b = tmp;
            tmp = v; v = a; a = tmp;
        }
        // out.println(v + " " + u + " " + a + " " + b);
        ArrayList<Integer> ans1 = new ArrayList<Integer>();
        ArrayList<Integer> ans2 = new ArrayList<Integer>();
        ArrayList<Integer> ans3 = new ArrayList<Integer>();
        ans1.add(b);
        ans2.add(b);
        int w = b;
        while (w != u) {
            w = parent[w];
            ans1.add(w);
        }
        while (dist[v] > dist[a]) {
            ans1.add(v);
            v = parent[v];
        }
        while (dist[a] < dist[v]){
            ans2.add(a);
            a = parent[a];
        }
        while (v != a) {
            ans1.add(v);
            ans2.add(a);
            v = parent[v];
            a = parent[a];
        }
        ans1.add(v);
        ans2.add(a);
        while (v != b) {
            ans3.add(0, v);
            v = parent[v];
        }
        ans3.add(0, b);
        out.println("YES");
        printV(ans1);
        printV(ans2);
        printV(ans3);
        out.close();
        System.exit(0);
    }
    static void printV(List<Integer> ans) {
        out.print(ans.size());
        for (int i = 0; i < ans.size(); i++) {
            out.print(" ");
            out.print(ans.get(i) + 1);
        }
        out.println();
    }
    static void dfs(int v) {
        //out.println(v + 1 + " " + dist[v]);
        marked[v] = true;
        for (int u : G.get(v)) {
            if (!marked[u]) {
                dist[u] = dist[v] + 1;
                parent[u] = v;
                dfs(u);
            } else if (dist[u] + 1 < dist[v]) {
                // out.println("CYCLE FOUND: ");
                // out.println(dist[u] + " " + dist[v] + " " + (u + 1) + " " + (v + 1));
                int w = v;
                int[] cycle = new int[] {v, u};
                while(w != u) {
                    // out.println(w);
                    if (cycleV[w] != null) {
                        // out.println(w);
                        printRes(cycleV[w][0], cycleV[w][1], v, u);
                    }
                    cycleV[w] = cycle;
                    w = parent[w];
                }
                cycleV[u] = cycle;
            }
        }
    }
    static ArrayList<ArrayList<Integer>> G;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String ans = "";
        int N = sc.nextInt();
        int M = sc.nextInt();
        G = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < N; i++) {
            G.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < M; i++) {
            int a, b;
            a = sc.nextInt() - 1;
            b = sc.nextInt() - 1;
            G.get(a).add(b);
            G.get(b).add(a);
        }
        marked = new boolean[N];
        cycleV = new int[N][];
        dist = new int[N];
        parent = new int[N];
        dfs(0);
        out.println("NO");
        out.close();
    }
}
