import java.util.*;
import java.io.*;
public class TheKingsRoadsDiv1 {
    public static class Node {
        ArrayList<Node> near = new ArrayList<Node>();
        boolean visited = false;
    }
    public static String getAnswer(int h, int[] a, int[] b) {
        int maxLabel = (int) Math.pow(2, h) - 1;
        Node[] nodes = new Node[maxLabel + 1];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new Node();
        }
        int numExcuses = 3;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) {
                System.out.println(a[i] + " " + b[i]);
                numExcuses--;
                continue;
            }
            if (nodes[a[i]].near.contains(nodes[b[i]])) {
                System.out.println(a[i] + " " + b[i]);
                numExcuses--;
                continue;
            }
            nodes[a[i]].near.add(nodes[b[i]]);
            nodes[b[i]].near.add(nodes[a[i]]);
        }
        if (numExcuses >= 0) {
            for (int i = 1; i < nodes.length; i++) { // ROOT
                if (possible(nodes[i], null, h) <= numExcuses) {
                    return "Correct";
                }
            }
        }
        return "Incorrect";
    }
    static int size(Node root, Node parent) {
        System.out.println(root + " " + parent);
        ArrayList<Node> nodes = near(root, parent);
        int ret = 1;
        if (nodes.size() == 0) return ret;
        for (Node node : nodes) {
            if (!node.visited
            ret += size(node, root);
            if (ret >= 10) return ret;
        }
        return ret;
    }
    public static ArrayList<Node> near(Node root, Node parent) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (Node node : root.near) {
            if (node != parent && !nodes.contains(node)) {
                nodes.add(node);
            }
        }
        return nodes;
    }
    public static int possible(Node root, Node parent, int h) {
        ArrayList<Node> nodes = near(root, parent);
        int size = size(root, parent);
        if (h < 1) {
            return size;
        }
        if (h == 1) {
            return nodes.size();
        }
        if (nodes.size() < 2) return 10000;
        int minCost = 10000;
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                int keep = possible(nodes.get(i), root, h - 1)
                    + possible(nodes.get(j), root, h - 1)
                    + 1;
                minCost = Math.min(minCost, size - keep);
            }
        }
        return minCost;
    }
    public static void main(String args[]) {
        System.out.println(getAnswer(3, new int[] {1, 3, 2, 2, 3, 7, 1, 5, 4}, new int[] {6, 5, 4, 7, 4, 3, 3, 1, 7}));
        System.out.println(getAnswer(2, new int[] {1, 2, 1, 3, 3}, new int[] {2, 1, 2, 3, 3}));
	System.out.println(getAnswer(3, new int[] {1, 3, 2, 2, 6, 6, 4, 4, 7}, new int[] {2, 1, 4, 5, 4, 4, 7, 7, 6}));
    }
}
