import java.util.*;
import java.io.*;
// http://codeforces.ru/contest/521/submission/10117526
public class E {
    public static PrintWriter out = new PrintWriter(System.out);
    static int N, M;
    static class Node {
        ArrayList<Node> near = new ArrayList<Node>();
        boolean visited = false;
        int idx;
        public Node(int idx) {
            this.idx = idx;
        }
        int numChildren;
        int lowP;
        int depth;
    }
    static void bfsCutNodes(Node node) {
        node.visited = true;
        int lowP = node.depth;
        boolean isCutNode = false;
        int numChildren = 0;
        for (Node child : Node.near) {
            if (!child.visited) {
                numChildren++;
                child.depth = node.depth + 1;
                bfsCutNodes(child);
                lowP = Math.min(lowP, child.lowP);
                if (child.lowP >= node.depth) {
                    isCutNode = true;
                }
            }
            lowP = Math.min(lowP, child.depth);
        }
        if (isCutNode) {
            cutNodes.add(node);
        }
        node.visited = false;
    }
    static Node[] nodes;
    static ArrayList<Node> biconRoots = new ArrayList<Node>();
    static ArrayList<Node> cutNodes = new ArrayList<Node>();
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String ans = "";
        N = sc.nextInt();
        M = sc.nextInt();
        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i + 1);
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            nodes[a].near.add(nodes[b]);
            nodes[b].near.add(nodes[a]);
        }
        Node root = nodes[0];
        root.depth = 0;
        bfsCutNodes(root);
        for (Node cutNode : cutNodes) {

            // disconnect and fill biconRoots
        }
        for (Node biconRoot : biconRoots) {
            ArrayList<Node> cycle = bfsCycle(biconRoot);
            for (int i = 0; i < cycle.length; i++) {
                Node cycleNode = cycle.get(i);
                ArrayList<Node> cycleNear = new ArrayList<Node>();
                cycleNear.add(cycle.get((i - 1 + cycle.length) % cycle.length));
                cycleNear.add(cycle.get((i + 1) % cycle.length));
                for (Node outsideNode : cycleNode.near) {
                    if (!cycleNear.contains(outsideNode)) {
                        ArrayList<Node> pathBackToCycle = bfsConnectToCyle(cycle, outsideNode, cycleNode); // cycle, node, parent
                        if (pathBackToCycle != null) {
                            Node cycleNode2 = pathBackToCycle.get(pathBackToCycle.size() - 1);
                            out.println("YES");
                            print(cycle, cycleNode, cycleNode2);
                            print(pathBackToCycle);
                        }
                    }
                }
            }
        }
        out.println("NO");
    }
}
