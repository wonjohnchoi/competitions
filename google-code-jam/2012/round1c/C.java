import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static class Item {
        long amount;
        int type;
        public Item(long a, int t) {
            amount = a;
            type = t;
        }
        @Override
        public boolean equals(Object o) {
            Item i = (Item) o;
            return amount == i.amount && type == i.type;
        }
        public boolean isEmpty() {
            return amount == 0;
        }
        public Item clone() {
            return new Item(amount, type);
        }
    }
    public static class State {
        Item rem1, rem2;
        int i, j;
        public State(Item r1, Item r2, int i, int j) {
            rem1 = r1;
            rem2 = r2;
            this.i = i;
            this.j = j;
        }
        public State clone() {
            return new State(rem1.clone(), rem2.clone(), i, j);
        }
        @Override
        public boolean equals(Object o) {
            State s = (State) o;
            return rem1.equals(s.rem1) && rem2.equals(s.rem2) && i == s.i && j == s.j;
        }
        @Override
        public int hashCode() {
            int mult = 31;
            int MOD = 1322837333;
            int[] vals = new int[] {(int) (rem1.amount % MOD), rem1.type, (int) (rem2.amount % MOD), rem2.type, i, j};
            long res = 0;
            long fac = 1;
            for (int i = 0; i < vals.length; i++) {
                res += vals[i] * fac;
                res %= MOD;
                fac *= mult;
                fac %= MOD;
            }
            return (int) (res % MOD);
        }
    }
    public static Item[] items1, items2;
    public static int N, M;
    public static long[] hashcodes1;
    public static long[] hashcodes2;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            items1 = new Item[N];
            items2 = new Item[M];
            hashcodes1 = new long[N];
            hashcodes2 = new long[M];
            cache = new HashMap<State, Long>();
            for (int i = 0; i < N; i++) {
                items1[i] = new Item(sc.nextLong(), sc.nextInt());
            }
            for (int i = 0; i < M; i++) {
                items2[i] = new Item(sc.nextLong(), sc.nextInt());
            }
            long best = find(new State(new Item(0, 0), new Item(0, 0), 0, 0));
            String ans = best + "";
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
    public static HashMap<State, Long> cache;
    public static long find(State s) {
        if (s.rem1.isEmpty() && s.i == N) {
            return 0;
        } else if(s.rem2.isEmpty() && s.j == M) {
            return 0;
        }
        if (cache.get(s) == null) {
            long val = -1;
            State s2 = s.clone();
            if (s2.rem1.isEmpty()) {
                s2.rem1 = items1[s2.i];
                s2.i++;
            }
            if (s2.rem2.isEmpty()) {
                s2.rem2 = items1[s2.j];
                s2.j++;
            }
            if (s2.rem1.type == s2.rem2.type) {
                long used = Math.min(s2.rem1.amount, s2.rem2.amount);
                val = Math.max(val, find(new State(new Item(s2.rem1.amount - used, s2.rem1.type), new Item(s.rem2.amount - used, s.rem2.type), s.i, s.j)) + used);
            } else {
                val = Math.max(val, find(new State(new Item(0, 0), s.rem2, s2.i, s.j )));
                val = Math.max(val, find(new State(s.rem1, new Item(0, 0), s.i, s2.j )));
            }
            cache.put(s, val);
        }
        return cache.get(s);
    }
}
