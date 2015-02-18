import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static class Node {
        Node parent = null;
        Node child = null;
        boolean visited = false;
        ArrayList<Node> near = new ArrayList<Node>();
        String code = null;
        int idx;
    }
    public static int N, M;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            Node[] nodes = new Node[N];
            Node root = null;            
            for (int i = 0; i < N; i++) {
                nodes[i] = new Node();
                nodes[i].code = sc.next();
                nodes[i].idx = i;
                if (root == null) {
                    root = nodes[i];
                } else if (nodes[i].code.compareTo(root.code) < 0) {
                    root = nodes[i];
                }
            }
            for (int i = 0; i < M; i++) {
                int a, b;
                a = sc.nextInt() - 1;
                b = sc.nextInt() - 1;
                nodes[a].near.add(nodes[b]);
                nodes[b].near.add(nodes[a]);
            }
            String ans = find(root);
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    public static Node findBest(Node node) {
        Node bestNode = null;
        for (Node next : node.near) {
            if (!next.visited) {
                // candidates.add(next);
                if (bestNode == null) {
                    bestNode = next;
                } else if (bestNode.code.compareTo(next.code) > 0) {
                    bestNode = next;
                }
            }
        }
        return bestNode;
    }
    public static boolean isConnected(Node node1, Node node2, Node node3) {
        while (node2 != node3) {
            for (Node node : node2.near) {
                if (!node.visited) {
                    if (!isConnected(node1, node, new boolean[N])) {
                        return false;
                    }
                }
            }
            node2 = node2.parent;
        }
        return true;
    }
    public static boolean isConnected(Node node1, Node node2, boolean[] hist) {
        hist[node1.idx] = true;
        if (node1 == node2) {
            return true;
        }
        for (Node node : node1.near) {
            if (!hist[node.idx] && !node.visited) {
                if (isConnected(node, node2, hist)) {
                    return true;
                }
            }
        }
        Node parent = node1.parent;
        while (parent != null) {
            if (isConnected(parent, node2, hist)) {
                return true;
            }
        }
        return false;
    }
    public static String find(Node node) {
        node.visited = true;
        // ArrayList<Node> candidates = new ArrayList<Node>();
        Node bestNode = findBest(node);
        //        if (bestNode == null) {
        //  return node.code;
        //}
        Node parent = node.parent;
        boolean distant = false;
        Node bestParent = node;
        while (parent != null) {
            Node bestNodeP = findBest(parent);
            if (bestNodeP != null) {
                if (bestNode == null) {
                    bestNode = bestNodeP;
                    bestParent = parent;
                    distant = true;
                } else if (bestNodeP.code.compareTo(bestNode.code) < 0) {
                    if (isConnected(bestNodeP, node, parent)) {
                        bestNode = bestNodeP;
                        bestParent = parent;
                        distant = true;
                    }
                }
            }
            parent = parent.parent;
        }
        if (bestNode == null) {
            return node.code;
        }
        bestNode.parent = bestParent;
        return node.code + find(bestNode);
    }
}
