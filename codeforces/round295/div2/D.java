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
    static long coord(int x, int y) {
        return x * 1000000 + y;
    }
    static boolean check(Node node) {
        if (node.above.size() == 0) {
            return true;
        }
        boolean good  = true;
        for (Node node2 : node.above) {
            if (node2.below.size() < 2) good = false;
        }
        return good;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String ans = "";
        int N = sc.nextInt();
        ArrayList<Node> nodes = new ArrayList<Node>();
        HashMap<Long, Node> coordToNode = new HashMap<Long, Node>();
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            Node node = new Node(x, y, i);
            nodes.add(node);
            coordToNode.put(coord(x, y), node);
        }
        Node sky = new Node(-1, -1, -1);
        for (int i = 0; i < N; i++) {
            Node node = nodes.get(i);
            for(int dx = -1; dx <= 1; dx++) {
                Node child = coordToNode.get(coord(node.x + dx, node.y - 1));
                if (child != null) {
                    node.below.add(child);
                    child.above.add(node);
                }
            }
        }
        for (Node node : nodes) {
            if (check(node)) sky.below.add(node);
        }
        int cnt = 0;
        int turn = 0;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        while (cnt < N) {
            Node best = null;
            for (Node node : sky.below) {
                // out.println(node.i + "in sky");
                if (best == null) best = node;
                else if (turn == 0 && node.i > best.i)
                    best = node;
                else if (turn == 1 && node.i < best.i)
                    best = node;
            }
            // System.out.println("removed: " + best.i);
            sky.below.remove(best);
            for (Node node : best.below) {
                node.above.remove(best);
            }
            for (Node node : best.above) {
                node.below.remove(best);
            }
            for (Node node : best.below) {
                if (check(node))
                    if (!sky.below.contains(node))
                        sky.below.add(node);
            }
            int i = 0;
            while (i < sky.below.size()) {
                if (!check(sky.below.get(i))) sky.below.remove(i);
                else i++;
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
