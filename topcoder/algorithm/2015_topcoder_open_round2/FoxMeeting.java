import java.util.*;
import java.io.*;
public class FoxMeeting {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    static List<List<toEdge>> adj;
    static class toEdge {
        int to;
        int dist;
        toEdge(int t, int d) {
            to = t;
            dist = d;
        }
    }
    static boolean[] fo;
    public static int maxDistance(int[] A, int[] B, int[] L, int[] foxes) {
        int N = A.length + 1;
        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<toEdge>());
        }
        for (int i = 0; i < N - 1; i++) {
            toEdge te = new toEdge(A[i] - 1, L[i]);
            toEdge te2 = new toEdge(B[i] - 1, L[i]);
            adj.get(A[i] - 1).add(te2);
            adj.get(B[i] - 1).add(te);
        }
        fo = new boolean[N];
        for (int fox : foxes) {
            fo[fox - 1] = true;
        }
        int min = Integer.MAX_VALUE;
        costs = new int[N];
        for (int root = 0; root < N; root++) {
            Arrays.fill(costs, -1);
            min = Math.min(dfs(root, -1, true), min);
        }
        return min;
    }
    static int[] costs;
    static int dfs(int cur, int par, boolean cache) {
        if (cache) if (costs[cur] != -1) return costs[cur];
        int cost = 0;
        // cur is ok
        if (fo[cur]) {
            for (int next : adj.get(cur)) {
                if (next != par)
                    cost += dfs(next, cur, cache);
            }
        } else {
            int min = Integer.MAX_VALUE;
            for (int next : adj.get(cur)) {
                if (next != par) {
                    // bring nearest and try use it without cache for this node

                    int ccost = 0;
                    for (int next2 : adj.get(cur)) {
                        if (next2 != par) {
                            ccost += dfs(next2, cur, cache && next2 != next);
                        }
                    }
                    min = Math.min(min, ccost);
                }
            }
            cost = min;
        }
        if (cache) costs[cur] = cost;
        return cost;
    }
    public static void main(String args[]) {

    }
}
