import java.util.*;
import java.io.*;
// TIME_USED:
public class A {
    public static boolean DEBUG = false;
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

    public static class Flow implements Comparable<Flow> {
        long from, to, amount;
        public Flow(long from, long to, long amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }
        @Override
            public int compareTo(Flow f) {
            if (from - f.from > 0) return 1;
            if (from == f.from) return 0;
            return -1;
        }
        public String toString() {
            return String.format("from: %d, to: %d, amount: %d", from, to, amount);
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            long N, M;
            N = sc.nextLong();
            M = sc.nextLong();
            ArrayList<Flow> flows = new ArrayList<Flow>();
            for (int m = 0; m < M; m++) { // <= 1000
                flows.add(new Flow(sc.nextLong(), sc.nextLong(), sc.nextLong()));
            }
            Collections.sort(flows);
            long ans = 0;
            long MOD = 1000002013;
            while (!flows.isEmpty()) {
                Flow flow = flows.remove(0);
                d("size: " + flows.size());
                d("removed: " + flow);
                int j = 0;
                while (j < flows.size() && flow.amount != 0) {
                    Flow flow2 = flows.get(j);
                    d("compare to: " + flow2);
                    if (flow.from == flow2.from) {
                        j++;
                        continue;
                    }
                    if (flow.to >= flow2.from && flow.to < flow2.to) {
                        d("Merging");
                        if (flow2.amount <= flow.amount) {
                            ans += (flow2.from - flow.from) * flow2.amount * (flow2.to - flow.to);
                            ans %= MOD;
                            flow.amount -= flow2.amount;
                            flow2.from = flow.from;
                            flows.add(0, flows.remove(j));
                            j++;
                        } else {
                            ans += (flow2.from - flow.from) * flow.amount * (flow2.to - flow.to);
                            ans %= MOD;
                            flow2.amount -= flow.amount;
                            flow.to = flow2.to;
                            j = 0;
                        }
                    } else {
                        j++;
                    }
                }
            }
            System.out.printf("Case #%d: %d\n", tc, ans);
        }
    }
}
