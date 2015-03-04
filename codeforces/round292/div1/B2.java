import java.util.*;
import java.io.*;
public class B2 {
    public static PrintStream out = System.out;
    static class Node {
        ArrayList<Node> near = new ArrayList<Node>();
        char sym;
        int x, y;
        public Node(char sym, int xx, int yy) {
            this.sym = sym;
            x = xx;
            y = yy;
        }
        void setSym(Node n) {
            if (x == n.x) {
                if (y < n.y) {
                    sym = '<';
                    n.sym = '>';
                } else {
                    sym = '>';
                    n.sym = '<';
                }
            } else {
                if (x < n.x) {
                    sym = '^';
                    n.sym = 'v';
                } else {
                    sym = 'v';
                    n.sym = '^';
                }
            }
        }
    }
    static int N, M;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        Node[][] map = new Node[N][M];
        int numDots = 0;
        if (N == 2000 && M == 2000)
            out.println("LINE41");
        for (int i = 0; i < N; i++) {
            String S = sc.next();
            for (int j = 0; j < M; j++) {
                char sym = S.charAt(j);
                if (S.charAt(j) == '.')
                    map[i][j] = new Node(sym, i, j);
                if (sym == '.') numDots++;
            }
        }
        boolean fail = false;
        int[][] deltas = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        ArrayList<Node> degreeOne = new ArrayList<Node>();
        if (N == 2000 && M == 2000)        out.println("LINE53");
        loop : for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == null) continue;
                for (int[] d : deltas) {
                    int i2 = d[0] + i;
                    int j2 = d[1] + j;
                    if (inRange(i2, j2) && map[i2][j2] != null) {
                        map[i][j].near.add(map[i2][j2]);
                    }
                }
                // out.println("BEGINNING: " + i + " " + j + " " + map[i][j].near.size());
                if (map[i][j].near.size() == 0) {
                    fail = true;
                    break loop;
                }
                else if(map[i][j].near.size() == 1) degreeOne.add(map[i][j]);
            }
        }
        if (N == 2000 && M == 2000) {
            out.println("LINE71");
            out.println(degreeOne.size() + " SIZE OF DEGREEONE");
        }
        int cnt = 0;
        if (!fail)
            while (degreeOne.size() != 0) {
                Node n1 = degreeOne.remove(0);
                if (n1.near.size() != 1) continue; // removed by other pair.
                cnt++;
                if (cnt % 100000 == 0 && N == 2000 && M == 2000)
                    out.println(degreeOne.size() + " SIZE OF DEGREEONE");

                Node n2 = n1.near.remove(0);
                n1.setSym(n2);
                n2.near.remove(n1);
                numDots -= 2;
                if (!updateNode(n2, degreeOne))
                    break;
            }
        if (N == 2000 && M == 2000)
            out.println("LINE83");
        if (numDots > 0) fail = true;
        if (fail) out.println("Not unique");
        else
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == null) out.print("*");
                    else out.print(map[i][j].sym);
                }
                out.println();
            }
    }
    static boolean updateNode(Node removed, ArrayList<Node> degreeOne) {
        for (Node node : removed.near) {
            // out.println(node.x + " " + node.y + " " + node.near.size());
            if (!node.near.remove(removed)) out.println("WTF");
            int size = node.near.size();
            // out.println(node.x + " " + node.y + " " + node.near.size());
            if (size == 0) {
                return false;
            } else if (size == 1) {
                degreeOne.add(node);
            }
        }
        return true;
    }
    static boolean inRange(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < M;
    }
}
