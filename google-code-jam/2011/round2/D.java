import java.util.*;
import java.io.*;
public class D {
    public static PrintStream out = System.out;
    static class Node {
        ArrayList<Node> near = new ArrayList<Node>();
        int minConquer = Integer.MAX_VALUE;
        int minThreaten = Integer.MAX_VALUE;
        int idx;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            int P, W;
            P = sc.nextInt();
            W = sc.nextInt();
            Node[] nodes = new Node[P];
            for (int i = 0; i < P; i++) {
                nodes[i] = new Node();
                nodes[i].idx = i;
            }
            for (int i = 0; i < W; i++) {
                String[] ss = sc.next().split(",");
                int a = Integer.parseInt(ss[0]);
                int b = Integer.parseInt(ss[1]);
                nodes[a].near.add(nodes[b]);
                nodes[b].near.add(nodes[a]);
            }
            nodes[0].minConquer = 0;
            nodes[0].minThreaten = nodes[0].near.size();
            ArrayList<Node> queue = new ArrayList<Node>();
            queue.add(nodes[0]);
            while (!queue.isEmpty()) {
                Node node = queue.remove(0);
                System.out.println(node.idx + " " + node.minThreaten + " " + node.minConquer);
                out.println(node.near.size());
                for (Node next : node.near) {
                    int newConquer;
                    int newThreaten;
                    if (next.idx == 1) {
                        newConquer = node.minConquer;
                        newThreaten = node.minThreaten;
                    } else {
                        newConquer = node.minConquer + 1;
                        newThreaten = node.minThreaten - 1 + node.near.size() - 1;
                    }
                    if (next.minConquer == Integer.MAX_VALUE
                        || next.minConquer > newConquer
                        || (next.minConquer == newConquer && next.minThreaten < newThreaten)) {
                        next.minConquer = newConquer;
                        next.minThreaten = newThreaten;
                        queue.add(next);
                    }
                }
            }
            String ans = "" + nodes[1].minConquer + " " + nodes[1].minThreaten;
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
