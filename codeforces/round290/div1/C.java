import java.util.*;
import java.io.*;
public class C {
    static class V {
        int num, idx;
        boolean visited;
        public V(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
        LinkedList<V> connected = new LinkedList<V>();
        LinkedList<E> edges = new LinkedList<E>();
    }
    static class E {
        int capacity;
        int flow;
        V v, u;
        public E(V v, V u, int c, int f) {
            capacity = c;
            flow = f;
            this.v = v;
            this.u = u;
        }
        V other(V w) {
            if (v == w) return u;
            return v;
        }
    }
    public static PrintStream out = System.out;
    static boolean[] isPrime;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        boolean[] inCycle = new boolean[N];
        V[] Vs = new V[N];
        V source = new V(-1, -1);
        V sink = new V(-1, -1);
        isPrime = new boolean[20001];
        Arrays.fill(isPrime, true);
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int numEven = 0;
        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            Vs[i] = new V(num, i);
            if (num % 2 == 0) {
                E e = new E(source, Vs[i], 2, 0);
                // Vs[i].edges.add(e);
                source.edges.add(e);
                numEven++;
            } else {
                E e = new E(Vs[i], sink, 2, 0);
                Vs[i].edges.add(e);
                // sink.edges.add(e);;
            }
        }
        String impossible = "impossible";
        out.println(numEven * 2 + " " + N + " numEven, N");
        if (numEven * 2 != N) {
            out.println(impossible);
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // out.println(Vs[i].num + " " + Vs[j].num + " " + isPrime[Vs[i].num + Vs[j].num]);
                if (isPrime[Vs[i].num + Vs[j].num]) {
                    if (Vs[i].num % 2 == 0) {
                        E e = new E(Vs[i], Vs[j], 1, 0);
                        Vs[i].edges.add(e);                    } else {
                        E e = new E(Vs[j], Vs[i], 1, 0);
                        Vs[j].edges.add(e);
                    }
                }
            }
        }
        int tot = 0;
        while (true) {
            LinkedList<E> path = findPath(source, sink);
            if (path == null) break;
            int flow = 1;
            for (E e : path) {
                out.println(e.v.num + " " + e.u.num + " " + e.flow + " " + e.capacity);
                e.flow += flow;
                e.capacity -= flow;
                if (e.v != source && e.u != sink) {
                    e.v.connected.add(e.u);
                    e.u.connected.add(e.v);
                    out.println("CONNECTED: " + e.v.idx + " " + e.u.idx);
                    tot += 2;
                }
            }
        }
        out.println(tot + " " + N * 2 + " : tot, N * 2");
        if (tot != N * 2) {
            out.println(impossible);
            return;
        }
        ArrayList<ArrayList<V>> cycles = new ArrayList<ArrayList<V>>();
        for (int i = 0; i < N; i++) {
            ArrayList<V> cycle = new ArrayList<V>();
            if (!inCycle[i]) {
                cycle.add(Vs[i]);
                inCycle[Vs[i].idx] = true;
                out.println("CYCLE: " + Vs[i].idx);
                V last = Vs[i];
                V v = last.connected.get(0);
                while (v != Vs[i]) {
                    cycle.add(v);
                    out.println(v.idx);
                    inCycle[v.idx] = true;
                    if (v.connected.get(0) == last) {
                        last = v;
                        v = v.connected.get(1);
                    } else {
                        last = v;
                        v = v.connected.get(0);
                    }
                }
                cycles.add(cycle);
            }
        }
        out.println(cycles.size());
        for (ArrayList<V> cycle : cycles) {
            out.print(cycle.size());
            for (V u : cycle) {
                out.print(" " + (u.idx + 1));
            }
            out.println();
        }
    }
    static LinkedList<E> findPath(V source, V sink) {
        // out.println(source.idx + " " + sink.idx + " " + path.size() + " " + source.num);
        if (source == sink) return new LinkedList<E>();
        for (E e : source.edges) {
            if (e.capacity > 0) {
                LinkedList<E> path = findPath(e.other(source), sink);
                if (path != null) {
                    path.add(0, e);
                    return path;
                }
            }
        }
        return null;
    }
}
