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
        long coord(int dx, int dy) {
            return (x + dx) * 1000000L + (y + dy);
        }
        boolean check() {
            if (above.size() == 0) {
                return true;
            }
            for (Node node : above) {
                if (node.below.size() < 2)
                    return false;
            }
            return true;
        }
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
            coordToNode.put(node.coord(0, 0), node);
        }
        TreeSet<Integer> canRemove = new TreeSet<Integer>();
        for (int i = 0; i < N; i++) {
            Node node = nodes.get(i);
            for(int dx = -1; dx <= 1; dx++) {
                Node child = coordToNode.get(node.coord(dx, - 1));
                if (child != null) {
                    node.below.add(child);
                    child.above.add(node);
                }
            }
        }
        for (Node node : nodes) {
            if (node.check()) canRemove.add(node.i);
        }
        int cnt = 0;
        int turn = 0;
        ArrayList<Integer> nums = new ArrayList<Integer>();
        while (cnt < N) {
            Node best;
            if (turn == 0) best = nodes.get(canRemove.pollLast());
            else best = nodes.get(canRemove.pollFirst());
            // System.out.println("removed: " + best.i);
            for (Node node : best.below) {
                node.above.remove(best);
            }
            for (Node node : best.above) {
                node.below.remove(best);
            }
            for (Node node : best.below) {
                if (node.check())
                    canRemove.add(node.i);
            }
            for (Node node : best.above) {
                if (node.below.size() == 1)
                    canRemove.remove(node.below.get(0).i);
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
