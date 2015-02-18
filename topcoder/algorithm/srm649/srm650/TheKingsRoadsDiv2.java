import java.util.*;
import java.io.*;
public class TheKingsRoadsDiv2 {
    public static class Node {
        ArrayList<Node> near = new ArrayList<Node>();
        
    }
    public static String getAnswer(int h, int[] a, int[] b) {
        int maxLabel = (int) Math.pow(2, h) - 1;
        Node[] nodes = new Node[maxLabel + 1];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new Node();
        }

        for (int i = 0; i < a.length; i++) {
            nodes[a[i]].near.add(nodes[b[i]]);
            nodes[b[i]].near.add(nodes[a[i]]);
        }
        for (int i = 1; i < nodes.length; i++) { // ROOT
            for (int j = 0; j < a.length; j++) { // REMOVE THIS EDGE
                nodes[a[j]].near.remove(nodes[b[j]]);
                nodes[b[j]].near.remove(nodes[a[j]]);
                if (possible(nodes[i], null, h)) {
                    return "Correct";
                }
                nodes[a[j]].near.add(nodes[b[j]]);
                nodes[b[j]].near.add(nodes[a[j]]);

            }
        }
        return "Incorrect";
    }
    public static boolean possible(Node root, Node parent, int h) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (Node node : root.near) {
            if (node != parent && !nodes.contains(node)) {
                nodes.add(node);
            }
        }
        if (h < 1) return false;
        if (nodes.size() == 0) {
            if (h == 1) {
                return true;
            }
        }
        if (nodes.size() != 2) {
            return false;
        }
        boolean good = true;
        for (Node node : nodes) {
            if (!possible(node, root, h - 1)) {
                good = false;
            }
        }
        return good;
    }
    public static void main(String args[]) {
        System.out.println(getAnswer(3, new int[] {1, 2, 3, 7, 1, 5, 4}, new int[] {6, 7, 4, 3, 3, 1, 7}));
        System.out.println(getAnswer(2, new int[] {1, 2, 3}, new int[] {2, 1, 3}));
    }
}
