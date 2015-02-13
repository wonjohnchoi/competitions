import java.util.*;
import java.io.*;
// TIME_USED:
public class B {
    public static class State {
        int x, y, njs;
        State parent;
        char move;
        public State(int x, int y, int njs, State parent, char move) {
            this.x = x;
            this.y = y;
            this.njs = njs;
            this.parent = parent;
            this.move = move;
        }
        static int[][] DELTAS = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        static char[] MOVES = new char[] {'N', 'S', 'E', 'W'};
        public List<State> getNext() {
            ArrayList<State> states = new ArrayList<State>();
            for (int i = 0; i < 4; i++) {
                int xd = DELTAS[i][0];
                int yd = DELTAS[i][1];
                states.add(new State(xd * njs + x, yd * njs + y, njs + 1, this, MOVES[i]));
            }
            return states;
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int X, Y;
            X = sc.nextInt();
            Y = sc.nextInt();
            /* Just too slow
            ArrayList<State> states = new ArrayList<State>();
            states.add(new State(0, 0, 1, null, ' '));
            State best = null;
            while (true) {
                State next = states.remove(0);
                if (next.x == X && next.y == Y) {
                    best = next;
                    break;
                }
                states.addAll(next.getNext());
            }
            String ans = "";
            while (best.parent != null) {
                ans = best.move + ans;
                best = best.parent;
                }*/

            /* worked for small */
            String ans = "";
            String hMove = X > 0 ? "WE" : "EW";
            String vMove = Y > 0 ? "SN" : "NS";
            for (int x = 0; x < Math.abs(X); x++) {
                ans += hMove;
            }
            for (int y = 0; y < Math.abs(Y); y++) {
                ans += vMove;
            }
            System.out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
