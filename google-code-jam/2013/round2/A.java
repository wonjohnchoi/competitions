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
    public static class Event implements Comparable<Event> {
        long stationIdx, amount;
        boolean isEnter;
        public Event(long stationIdx, long amount, boolean isEnter) {
            this.stationIdx = stationIdx;
            this.amount = amount;
            this.isEnter = isEnter;
        }
        @Override
            public int compareTo(Event f) {
            long diff = stationIdx - f.stationIdx;
            if (diff > 0) return 1;
            if (diff < 0) return -1;
            if (isEnter == f.isEnter) return 0;
            if (isEnter && !f.isEnter) return -1;
            return 1;
        }
        public String toString() {
            return String.format("stationIdx: %d, amount: %d, isEnter: %b", stationIdx, amount, isEnter);
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            long N, M;
            N = sc.nextLong();
            M = sc.nextLong();
            ArrayList<Event> es = new ArrayList<Event>();
            long cost = 0;
            long MOD = 1000002013;
            for (int m = 0; m < M; m++) { // <= 1000
                long from = sc.nextLong();
                long to = sc.nextLong();
                long amount = sc.nextLong();
                es.add(new Event(from, amount, true));
                es.add(new Event(to, amount, false));
                long i = to - from;
                long costP = ((2 * N + 1 - i) * i / 2) % MOD;
                costP = (costP * amount) % MOD;
                cost = (cost + costP) % MOD;
            }
            Collections.sort(es);
            d("events: " + es);
            d("cost: " + cost);
            long reducedCost = 0;
            ArrayList<Event> sim = new ArrayList<Event>();
            while (!es.isEmpty()) {
                Event e = es.remove(0);
                if (e.isEnter) {
                    sim.add(e);
                } else {
                    while (e.amount != 0) {
                        Event last = sim.get(sim.size() - 1);
                        long used = Math.min(e.amount, last.amount);
                        e.amount -= used;
                        last.amount -= used;
                        long i = e.stationIdx - last.stationIdx;
                        if (last.amount == 0) {
                            sim.remove(sim.size() - 1);
                        }
                        long costP = ((2 * N + 1 - i) * i / 2) % MOD;
                        costP = (costP * used) % MOD;
                        reducedCost = (reducedCost + costP) % MOD;
                    }
                }
            }
            d("reduced: " + reducedCost);
            System.out.printf("Case #%d: %d\n", tc, (cost - reducedCost + MOD) % MOD);
        }
    }
}
