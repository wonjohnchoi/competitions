import java.util.*;
import java.io.*;
public class B {
    public static class Group {
        double start, end, time;
        int P, V;
        public Group(int P, int V) {
            this.P = P;
            this.V = V;
            time = (V - 1) / 2.0 * D;
            start = P - time;
            end = P + time;
        }
        public void move(double a) {
            if (a == 0) return;
            double used = Math.min(Math.abs(a), time);
            time -= used;
            start += used * dir(a);
            end += used * dir(a);
        }
        public static double dir(double a) {
            return a / Math.abs(a);
        }
        public String toString() {
            return "(" + start + " " + end + ")";
        }
    }
    public static PrintStream out = System.out;
    public static double D;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int C;
            C = sc.nextInt();
            D = sc.nextDouble();
            Group[] gs = new Group[C];
            double totTime = 0;
            double maxTime = -1;
            for (int i = 0; i < C; i++) {
                int P, V;
                P = sc.nextInt();
                V = sc.nextInt();
                gs[i] = new Group(P, V);
                maxTime = Math.max(maxTime, gs[i].time);
            }
            totTime += maxTime;
            for (int i = 0; i < C; i++) {
                gs[i].time = maxTime - gs[i].time;
            }
            for (int i = 0; i < C; i++) {
                spendTime(gs, 0);
            }
            for (int i = 1; i < gs.length; i++) {
                double time = (gs[i - 1].end - gs[i].start + D) / 2;
                if (time < 0) time = 0;
                spendTime(gs, time);
                totTime += time;
            }
            out.printf("Case #%d: %s\n", tc, totTime + "");
        }
    }
    public static void spendTime(Group[] gs, double time) {
        for (int i = 0; i < gs.length; i++) {
            gs[i].time += time;
            if (i == 0) gs[i].move(-time);
            else gs[i].move(gs[i - 1].end - gs[i].start + D);
            gs[i].time = 0;
        }
    }
}
