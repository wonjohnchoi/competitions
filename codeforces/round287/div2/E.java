import java.util.*;
import java.io.*;
public class E {
    public static PrintStream out = System.out;
    static long idx(int i, int j) {
        if (i > j) return idx(j, i);
        return i * N + j;
    }
    static class Node implements Comparable<Node> {
        ArrayList<Node> near = new ArrayList<>();
        ArrayList<Integer> nearBad = new ArrayList<>();
        Node prev = null;
        int dist = -1;
        int bad = -1;
        int i;
        boolean visited = false;
        Node(int i) {
            this.i = i;
        }
        @Override
        public int compareTo(Node n) {
            if (dist != n.dist) return dist - n.dist;
            return bad - n.bad;
        }
    }
    static int N, M;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        HashMap<Long, Boolean> connected = new HashMap<Long, Boolean>();
        ArrayList<Long> edges = new ArrayList<Long>();
        Node[] nodes = new Node[N];
        int totGood = 0;
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < M; i++) {
            int x, y, b;
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            b = sc.nextInt();
            if (b == 1) totGood++;
            long edgeIdx = idx(x, y);
            connected.put(edgeIdx, b == 1 ? true : false);
            edges.add(edgeIdx);
            nodes[x].near.add(nodes[y]);
            nodes[x].nearBad.add(b);
            nodes[y].near.add(nodes[x]);
            nodes[y].nearBad.add(b);
        }
        PriorityQueue<Node> frontiers = new PriorityQueue<Node>();
        nodes[0].dist = 0;
        nodes[0].bad = 0;
        frontiers.add(nodes[0]);
        HashSet<Long> used = new HashSet<>();
        while (!frontiers.isEmpty()) {
            Node cur = frontiers.poll();
            if (cur.visited) continue;
            cur.visited = true;
            for (int i = 0; i < cur.near.size(); i++) {
                Node next = cur.near.get(i);
                if (!next.visited) {
                    // out.println("next: " + next.i);
                    int nextDist = cur.dist + 1;
                    int nextBad = cur.nearBad.get(i) == 1 ? 0 : 1;
                    nextBad += cur.bad;
                    if (next.dist == -1
                        || (nextDist < next.dist)
                        || (nextDist == next.dist
                            && nextBad < next.bad)) {
                        next.dist = nextDist;
                        next.bad = nextBad;
                        next.prev = cur;
                        frontiers.add(next);
                        next.visited = false;
                    }
                }
            }
        }
        Node node = nodes[N - 1];
        while (node.prev != null) {
            used.add(idx(node.i, node.prev.i));
            node = node.prev;
        }
        int tot = totGood - (nodes[N - 1].dist - nodes[N - 1].bad) + nodes[N - 1].bad;
        out.println(tot);
        for (long edge : edges) {
            boolean c = connected.get(edge);
            boolean d = used.contains(edge);
            long a = edge / N + 1;
            long b = edge % N + 1;
            if (!c && d) {
                out.println(a + " " + b + " " + 1);
            } else if (c && !d) {
                out.println(a + " " + b + " " + 0);
            }
        }
        // out.println(nodes[N - 1].dist + " " + nodes[N - 1].bad + " " + nodes[N - 1].i);

    }
}
