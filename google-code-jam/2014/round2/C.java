import java.util.*;
import java.io.*;
public class C {
    public static class Node {
        int x0, y0, x1, y1;
        public Node(int x0, int y0, int x1, int y1) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
        }
        public int dist(Node o) {
            int a0 = dist(x0, x1, o.x0);
            int a1 = dist(x0, x1, o.x1);
            int a2 = dist(y0, y1, o.y0);
            int a3 = dist(y0, y1, o.y1);
            return Math.max(Math.min(a0, a1), Math.min(a2, a3));
        }
        public static int dist(int x0, int x1, int x) {
            if (x0 <= x && x <= x1) return 0;
            if (x < x0) return x0 - x - 1;
            return x - x1 - 1;
        }
    }
    public static int W, H, B;
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            W = sc.nextInt();
            H = sc.nextInt();
            B = sc.nextInt();
            Node[] nodes = new Node[B];
            for (int i = 0; i < B; i++) {
                nodes[i] = new Node(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            }
            int[][] dists = new int[B + 2][B + 2]; // 0 is left wall. 1 is right wall.
            dists[0][1] = W;
            dists[1][0] = W;
            for (int j = 2; j < B + 2; j++) {
                dists[0][j] = nodes[j - 2].x0;
                dists[j][0] = dists[0][j];
                dists[1][j] = W - 1 - nodes[j - 2].x1;
                dists[j][1] = dists[1][j];
            }
            for (int i = 2; i < B + 2; i++) {
                for (int j = i + 1; j < B + 2; j++) {
                    dists[i][j] = nodes[i - 2].dist(nodes[j - 2]);
                    // System.out.println(i + " " + j + " " + dists[i][j]);
                    dists[j][i] = dists[i][j];
                }
            }
            // NTF dists[0][1]. B ~ 1000. N^3 is slow (floyd). 
            // N^2 slow dijkstra works
            int[] minDists = new int[B + 2];
            Arrays.fill(minDists, Integer.MAX_VALUE);
            boolean[] visited = new boolean[B + 2];
            minDists[0] = 0;
            int numVisited = 0;
            while (numVisited < B + 2) {
                int next = -1;
                for (int i = 0; i < B + 2; i++) {
                    if (!visited[i] && minDists[i] != Integer.MAX_VALUE) {
                        if (next == -1) next = i;
                        else if (minDists[next] > minDists[i]) {
                            next = i;
                        }
                    }
                }
                visited[next] = true;
                numVisited++;
                for (int i = 0; i < B + 2; i++) {
                    if (!visited[i])
                        minDists[i] = Math.min(minDists[i], minDists[next] + dists[next][i]);
                }
            }
            // System.out.println("minDists from left wlal");
            // for (int i = 0; i < minDists.length; i++) System.out.println(minDists[i]);
            String ans = minDists[1] + "";
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
