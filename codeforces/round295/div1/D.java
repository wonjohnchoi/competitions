import java.util.*;
import java.io.*;
public class D {
    public static PrintWriter out = new PrintWriter(System.out);
    static class Item {
        long t, b;
        int idx, i;
        Fraction mult;
        public Item(long tt, int ii, long bb, int idx) {
            t = tt;
            i = ii;
            b = bb;
            this.idx = idx;
        }
    }
    static class Fraction {
        long up, down;
        public Fraction(long up, long down) {
            this.up = up;
            this.down = down;
        }
    }
    static int K, N, M;
    static long[] as;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String ans = "";
        K = sc.nextInt();
        N = sc.nextInt();
        M = sc.nextInt();
        as = new long[K];
        for (int i = 0; i < K; i++) {
            as[i] = sc.nextLong();
        }
        ArrayList<Item> items = new ArrayList<Item>();
        // ArrayList<Fraction> mults = new ArrayList<Fraction>();
        Item[] bestReplace = new Item[K];
        HashMap<Integer, ArrayList<Item>> addItemsFor = new HashMap<Integer, ArrayList<Item>>();
        for (int i = 0; i < N; i++) {
            Item item = new Item(sc.nextLong(), sc.nextInt() - 1, sc.nextLong(), i + 1);
            if (item.t == 3) item.mult = new Fraction(item.b - 1, 1);
            if (item.t == 1) {
                if (bestReplace[item.i] == null) bestReplace[item.i] = item;
                else if (item.b > bestReplace[item.i].b) bestReplace[item.i] = item;
            }
            if (item.t == 2) {
                ArrayList<Item> items2 = addItemsFor.get(item.i);
                if (items2 == null) {
                    items2 = new ArrayList<Item>();
                    addItemsFor.put(item.i, items2);
                }
                items2.add(item);
            }
            items.add(item);
        }
        ArrayList<Item> unused = new ArrayList<Item>();
        int i = 0;
        while (i < items.size()) {
            Item item = items.get(i);
            if (item.t == 1) {
                if (item != bestReplace[item.i]) {
                    items.remove(i);
                    unused.add(item);
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }
        for (i = 0; i < as.length; i++) {
            ArrayList<Item> adds = addItemsFor.get(i);
            if (adds == null) adds = new ArrayList<Item>();
            if (bestReplace[i] != null) {
                bestReplace[i].b -= as[i];
                adds.add(bestReplace[i]);
            }
            Collections.sort(adds, new Comparator<Item>() {
                    public int compare(Item a, Item b) {
                        return (int) (b.b - a.b);
                    }
                });
            long val = as[i];
            for (Item add : adds) {
                add.mult = new Fraction(add.b, val);
                val += add.b;
            }
        }
        Collections.sort(items, new Comparator<Item>() {
                public int compare(Item a, Item b) {
                    Fraction fracA = a.mult;
                    Fraction fracB = b.mult;
                    long res = fracB.up * fracA.down - fracA.up * fracB.down;
                    if (res > 0) return 1;
                    if (res == 0) return 0;
                    return -1;
                }
            });
        ArrayList<Item> finals = new ArrayList<Item>();
        for (i = 0; i < Math.min(items.size(), M); i++) {
            if (items.get(i).mult.up >= 0)
                finals.add(items.get(i));
        }
        Collections.sort(finals, new Comparator<Item>() {
                public int compare(Item a, Item b) {
                    return (int) (a.t - b.t);
                }
            });
        ans  = finals.size() + "\n";
        for (i = 0; i < finals.size(); i++) {
            if (i != 0) ans += " ";
            ans += finals.get(i).idx;
        }
        out.printf("%s\n", ans);
        out.close();
    }
}
