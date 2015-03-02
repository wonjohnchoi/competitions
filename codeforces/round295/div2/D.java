import java.util.*;
import java.io.*;
public class D {
    public static PrintStream out = System.out;
    static class Node {
        ArrayList<Node> above = new ArrayList<Node>();
        ArrayList<Node> below = new ArrayList<Node>();
        int x, y, i;
        public Node (int xx, int yy, int ii) {
            x = xx;
            y = yy;
            i = ii;
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String ans = "";
        int N = sc.nextInt();
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            nodes.add( new Node(x, y, i));
        }
        Node sky = new Node(-1, -1, -1);
        Collections.sort(nodes, new Comparator<Node>() {
                public int compare(Node n1, Node n2) {
                    return n1.y - n2.y;
                }
            });
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (nodes.get(j).y > nodes.get(i).y + 1) break;
                if (nodes.get(j).y == nodes.get(i).y) continue;
                if (nodes.get(j).x == nodes.get(i).x || nodes.get(j).x == nodes.get(i).x - 1 || nodes.get(j).x == nodes.get(i).x + 1) {
                    nodes.get(j).below.add(nodes.get(i));
                    nodes.get(i).above.add(nodes.get(j));
                }
            }
        }
        for (Node node : nodes) {
            if (node.above.size() == 0) sky.below.add(node);
        }
        int cnt = 0;
        int turn = 0;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        while (cnt < N) {
            Node best = null;
            for (Node node : sky.below) {
                if (best == null) best = node;
                else if (turn == 0 && node.i > best.i)
                    best = node;
                else if (turn == 1 && node.i < best.i)
                    best = node;
            }
            // System.out.println(best.i);
            sky.below.remove(best);
            for (Node node : best.below) {
                node.above.remove(best);
                sky.below.add(node);
            }
            cnt++;
            nums.add(best.i);
            turn = (turn + 1) % 2;
        }
        long MOD = (long) (Math.pow(10, 9) + 9);
        long ret = 0;
        long fac = 1;
        for (int i = 0; i < N; i++) {
            ret = (ret + (fac * nums.get(N - i - 1)) % MOD) % MOD;
            fac = (fac * N) % MOD;
        }
        ans = ret + "";
        out.printf("%s\n", ans);
    }
}
