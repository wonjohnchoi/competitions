import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static InputReader in = new InputReader(System.in);
    static Node source, sink;
    static Node[] actors, songs;
    public static void main(String args[]) {
        int N = in.nextInt();
        songs = new Node[N];
        for (int i = 0; i < N; i++) {
            songs[i] = new Node(in.nextInt(), in.nextInt());
        }
        int M = in.nextInt();
        actors = new Node[M];
        for (int i = 0; i < M; i++) {
            actors[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt(), i + 1);
        }
        source = new Node();
        sink = new Node();
        for (Node song : songs) {
            Flow flow = new Flow(0, 0);
            sink.add(song);
            sink.flows.add(flow);
            Flow flow2 = new Flow(1, 0);
            song.add(sink);
            song.flows.add(flow2);
            flow.rev = flow2;
            flow2.rev = flow;
        }
        for (Node actor : actors) {
            Flow flow = new Flow(actor.k, 0);
            source.add(actor);
            source.flows.add(flow);
            Flow flow2 = new Flow(0, 0);
            actor.add(source);
            actor.flows.add(flow2);
            flow.rev = flow2;
            flow2.rev = flow;
        }
        for (Node actor : actors) {
            for (Node song : songs) {
                if (actor.a <= song.a && song.b <= actor.b) {
                    Flow flow = new Flow(1, 0);
                    actor.add(song);
                    actor.flows.add(flow);
                    Flow flow2 = new Flow(0, 0);
                    song.add(actor);
                    song.flows.add(flow2);
                    flow.rev = flow2;
                    flow2.rev = flow;
                }
            }
        }
        if (flows() != songs.length) out.println("NO");
        else {
            out.println("YES");
            for (int j = 0; j < songs.length; j++) {
                Node song = songs[j];
                if (j != 0) out.print(" ");
                for (int i = 0; i < song.adj.size(); i++) {
                    if (song.adj.get(i) != sink && song.flows.get(i).f == -1) {
                        out.print(song.adj.get(i).i);
                        break;
                    }
                }
            }
            out.println();
        }
    }
    static int flows() {
        int cnt = 0;
        while (dfs(source, new HashSet<Node>())) {
            cnt++;
        }
        return cnt;
        // out.println("tot : " + cnt);
    }
    static boolean dfs(Node node, HashSet<Node> marked) {
        if (marked.contains(node)) return false;
        if (node == sink) return true;
        marked.add(node);
        for (int i = 0; i < node.adj.size(); i++) {
            Node next = node.adj.get(i);
            Flow flow = node.flows.get(i);
            if (flow.c > flow.f && dfs(next, marked)) {
                // out.println("connecting: " + node.a + "." + node.b + " and " + next.a + "." + next.b);
                flow.f++;
                flow.rev.f--;
                return true;
            }
        }
        return false;
    }
    static class Flow {
        int c, f;
        Flow(int cc, int ff) {
            c = cc;
            f = ff;
        }
        Flow rev;
    }
    static class Node {
        int a, b, k, i;
        Node() {}
        Node(int aa, int bb) {
            a = aa;
            b = bb;
        }
        Node (int aa, int bb, int kk, int ii) {
            a = aa; b = bb; k = kk; i = ii;
        }
        void add(Node node) {
            adj.add(node);
        }
        List<Node> adj = new ArrayList<Node>();
        List<Flow> flows = new ArrayList<>();
    }
}
class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;
    public InputReader(InputStream stream) {
	reader = new BufferedReader(new InputStreamReader(stream), 32768);
	tokenizer = null;
    }
    public String next() {
	while (tokenizer == null || !tokenizer.hasMoreTokens()) {
	    try {
		tokenizer = new StringTokenizer(reader.readLine());
	    } catch (IOException e) {
		throw new RuntimeException(e);
	    }
	}
	return tokenizer.nextToken();
    }
    public double nextDouble() {
	return Double.parseDouble(next());
    }
    public long nextLong() {
	return Long.parseLong(next());
    }
    public int nextInt() {
	return Integer.parseInt(next());
    }
}
