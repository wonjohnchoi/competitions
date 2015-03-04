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
        char[][] map = new char[N];
        int numDots = 0;
        for (int i = 0; i < N; i++) {
            map[i] = sc.next().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '.') numDots++;
            }
        }
        boolean fail = false;
        int[][] deltas = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        HashMap<Integer> no
        ArrayList<Node> degreeOne = new ArrayList<Node>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != '.') continue;
                Node node = new Node(map[i][j], i, j);
                for (int[] d : deltas) {
                    int i2 = d[0] + i;
                    int j2 = d[1] + j;
                    if (inRange(i2, j2) && map[i2][j2] == '.') {
                        map[i][j].near.add(map[i2][j2]);
                    }
                }
                // out.println("BEGINNING: " + i + " " + j + " " + map[i][j].near.size());
                if (map[i][j].near.size() == 0) fail = true;
                else if(map[i][j].near.size() == 1) degreeOne.add(map[i][j]);
            }
        }
        while (degreeOne.size() != 0) {
            Node n1 = degreeOne.remove(0);
            if (n1.near.size() != 1) continue; // removed by other pair.
            Node n2 = n1.near.remove(0);
            n1.setSym(n2);
            n2.near.remove(n1);
            numDots -= 2;
            if (!updateNode(n1, degreeOne) || !updateNode(n2, degreeOne))
                break;
            n2.near.clear();
        }
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
            node.near.remove(removed);
            // out.println(node.x + " " + node.y + " " + node.near.size());
            if (node.near.size() == 0) {
                return false;
            } else if (node.near.size() == 1) {
                degreeOne.add(node);
            }
        }
        return true;
    }
    static boolean inRange(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < M;
    }
}
