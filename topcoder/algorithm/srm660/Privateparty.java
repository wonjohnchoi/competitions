import java.util.*;
import java.io.*;
public class Privateparty {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    static class Node {
        List<Integer> vals;
        Node(List<Integer> vals) {
            this.vals = vals;
        }
        public boolean equals(Object o) {
            Node n = (Node) o;
            if (vals.size() != n.vals.size()) return false;
            for (int i = 0; i < vals.size(); i++) {
                if (vals.get(i) != n.vals.get(i)) return false;
            }
            return true;
        }
        public int hashCode() {
            long hash = 0;
            for (int i = 0; i < vals.size(); i++) {
                hash *= 31;
                hash += vals.get(i);
                hash %= Integer.MAX_VALUE;
            }
            return (int) hash;
        }
    }
    static HashMap<Node, Double> map = new HashMap<>();
    static double get(Node n) {
        if (n.vals.size() == 0) return 0;
        if (!map.containsKey(n)) {
            double ret = 0.0;
            int sumElem = 0;
            for (int i = 0; i < n.vals.size(); i++) {
                Integer cur = n.vals.get(i);
                sumElem += cur;
                ret += cur;
                Node nn = new Node(new ArrayList<Integer>(n.vals));
                nn.vals.remove(cur);
                ret += get(nn);
                if (cur > 1) {
                    nn.vals.add(cur - 1);
                    Collections.sort(nn.vals);
                    ret += get(nn) * (cur - 1);
                }
            }
            ret /= sumElem;
            map.put(n, ret);
        }
        out.println(n.vals + " " + map.get(n));
        return map.get(n);
    }
    public static double getexp(int[] a){
        ArrayList<Integer> aa = new ArrayList<>();
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[a[i]]++;
        }
        List<Integer> c = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (b[i] != 0) c.add(b[i]);
        }
        Collections.sort(c);
        return get(new Node(c));
    }
    public static void main(String args[]) {
        out.print(getexp(new int[] {0, 1}));
    }
}
