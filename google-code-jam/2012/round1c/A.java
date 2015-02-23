import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static class Node {
        List<Node> from;
        int cnt;
        int idx;
        public Node() {
            cnt = 0;
            from = new ArrayList<Node>();
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            Node[] nodes = new Node[N];
            for (int i = 0; i < N; i++) {
                nodes[i] = new Node();
            }
            for (int i = 0; i < N; i++) {
                int M = sc.nextInt();
                for (int j = 0; j < M; j++) {
                    nodes[i].from.add(nodes[sc.nextInt() - 1]);
                }
                nodes[i].idx = i;
            }
            boolean yes = false;
            List<Node> sorted = tSort(nodes);
            for (int i = 0; i < sorted.size(); i++) {
                for (int j = 0; j < sorted.size(); j++) {
                    sorted.get(j).cnt = 0;
                }
                sorted.get(i).cnt = 1;
                for (int j = i + 1; j < sorted.size(); j++) {
                    for (Node node : sorted.get(j).from) {
                        sorted.get(j).cnt += node.cnt;
                    }
                    if (sorted.get(j).cnt >= 2) {
                        yes = true;
                    }
                }
            }
            String ans = yes ? "Yes" : "No";
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    public static List<Node> tSort(Node[] nodes2) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (Node node : nodes2) {
            nodes.add(node);
        }
        List<Node> res = new ArrayList<Node>();
        while (!nodes.isEmpty()) {
            int i = 0;
            while (i < nodes.size()) {
                int cnt = 0;
                for (Node node : nodes.get(i).from) {
                    if (!res.contains(node)) {
                        cnt++;
                    }
                }
                if (cnt == 0) {
                    res.add(nodes.remove(i));
                } else {
                    i++;
                }
            }
        }
        return res;
    }
}
