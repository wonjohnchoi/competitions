import java.util.*;
import java.io.*;
// TIME_USED:
public class B {
    public static boolean DEBUG = true;
    public static void d(Object o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(int o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(long o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(double o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(float o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(boolean o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }

    public static class Node {
        ArrayList<Node> adj = new ArrayList<Node>();
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
            for (int i = 0; i < N - 1; i++) {
                int a, b;
                a = sc.nextInt() - 1;
                b = sc.nextInt() - 1;
                nodes[a].adj.add(nodes[b]);
                nodes[b].adj.add(nodes[a]);
            }
            int maxSize = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                Node root = nodes[i];
                int size = sizeOfFBT(root, null);
                maxSize = Math.max(maxSize, size);
            }
            System.out.printf("Case #%d: %d\n", tc, N - maxSize);
        }
    }
    public static int sizeOfFBT(Node root, Node parent) {
        int numAdj = root.adj.size() + (parent == null ? 0 : -1);
        if (numAdj < 2) return 1;
        ArrayList<Integer> sizes = new ArrayList<Integer>();
        for (Node node : root.adj) {
            if (node != parent) {
                sizes.add(sizeOfFBT(node, root));
            }
        }
        Collections.sort(sizes, Collections.reverseOrder());
        return sizes.get(0) + sizes.get(1) + 1;

    }
}
