import java.util.*;
import java.io.*;
public class B {
    public static class Room {
        double ceiling, floor, time;
        boolean visited;
        List<Room> near;
        public Room() {
            visited = false;
            near = new ArrayList<Room>();
        }
        public double h() {
            return Math.max(H - time * 10, 0);
        }
        public double timeNeeded(Room r) {
            boolean canMove = true;
            canMove &= ceiling >= r.floor + HS;
            canMove &= r.ceiling >= r.floor + HS;
            canMove &= r.ceiling >= floor + HS;
            if (!canMove) return INF + 1;
            double wf1 = Math.max(floor, h()) + HS;
            double wf2 = Math.max(r.floor, h()) + HS;
            double tot = Math.max(Math.max(wf2 - ceiling, wf2 - r.ceiling), wf1 - r.ceiling) / 10.0;
            if (tot <= 0 && time == 0) {
                return 0;
            } else if (tot <= 0) {
                tot = 0;
            }
            double wh = h() - floor - tot * 10;
            if (wh >= 20) {
                tot++;
            } else {
                tot += 10;
            }
            return tot;
        }
     }
    public static int HS = 50; // human size
    public static int INF = 10000;
    public static int H, N, M;
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            H = sc.nextInt();
            N = sc.nextInt();
            M = sc.nextInt();
            Room[][] rooms = new Room[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    rooms[i][j] = new Room();
                    rooms[i][j].ceiling = sc.nextInt();
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (i > 0) rooms[i][j].near.add(rooms[i - 1][j]);
                    if (j > 0) rooms[i][j].near.add(rooms[i][j - 1]);
                    if (i + 1 < N) rooms[i][j].near.add(rooms[i + 1][j]);
                    if (j + 1 < M) rooms[i][j].near.add(rooms[i][j + 1]);
                    rooms[i][j].floor = sc.nextInt();
                }
            }
            List<Room> frontiers = new ArrayList<Room>();
            frontiers.add(rooms[0][0]);
            rooms[0][0].time = 0;
            rooms[0][0].visited = true;
            while (!frontiers.isEmpty()) {
                Room room = frontiers.remove(0);
                for (Room next : room.near) {
                    double timeNeeded = room.timeNeeded(next);
                    if (timeNeeded < INF) {
                        if (!next.visited || next.time > timeNeeded + room.time) {
                            next.time = timeNeeded + room.time;
                            next.visited = true;
                            frontiers.add(next);
                        }
                    }
                }
            }
            out.printf("Case #%d: %s\n", tc, rooms[N - 1][M - 1].time);
        }
    }
}
