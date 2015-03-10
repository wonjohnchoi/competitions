import java.util.*;
import java.io.*;
public class TheKingsRoadsDiv1 {
    public static class Node {
        ArrayList<Node> near = new ArrayList<Node>();

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
                excuses = numExcuses;
                System.out.println(i + " " + possible(nodes[i], null, h));
                System.out.println(excuses);
                if (possible(nodes[i], null, h) <= excuses) {
                    return "Correct";
                }
            }
        }
        return "Incorrect";
    }
    static int size(Node root, Node parent, int stopSize) {
        ArrayList<Node> nodes = near(root, parent);
        int ret = 1;
        for (Node node : nodes) {
            ret += size(node, root, stopSize - ret);
            if (ret > stopSize) {
                return ret;
            }
        }
        return ret;
    }
    static int excuses;
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
        if (h < 1) {
             return size(root, parent, excuses);
        }
        if (h == 1) {
            if (nodes.size() == 0) {
                return 0;
            } else {
                int ret = 0;
                for (Node node : nodes) {
                    ret += size(node, parent, excuses - ret);
                    if (ret > excuses) return 100;
                }
                return ret;
            }
        }
        if (nodes.size() < 2) return 100;
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                int ret = possible(nodes.get(i), root, h - 1);
                if (ret <= excuses) {
                    ret += possible(nodes.get(j), root, h - 1);
                }
                if (ret <= excuses) {
                    for (Node node : nodes) {
                        if (node != nodes.get(i) && node != nodes.get(j)) {
                            ret += size(node, root, excuses - ret);
                            if (ret > excuses) break;
                        }
                    }
                }
                if (ret <= excuses) {
                    return ret;
                }
            }
        }
        return 100;
    }
    public static void main(String args[]) {
        System.out.println(getAnswer(3, new int[] {1, 3, 2, 2, 3, 7, 1, 5, 4}, new int[] {6, 5, 4, 7, 4, 3, 3, 1, 7}));
        System.out.println(getAnswer(2, new int[] {1, 2, 1, 3, 3}, new int[] {2, 1, 2, 3, 3}));
	System.out.println(getAnswer(3, new int[] {1, 3, 2, 2, 6, 6, 4, 4, 7}, new int[] {2, 1, 4, 5, 4, 4, 7, 7, 6}));
    }
}
