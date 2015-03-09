import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    static int ghosts, duration, candles;
    static int[] arrivals;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        // W1 ~ WM (1<= <=300)
        ghosts = sc.nextInt();
        duration = sc.nextInt();
        candles = sc.nextInt();
        arrivals = new int[ghosts];
        for (int i = 0; i < ghosts; i++) {
            arrivals[i] = sc.nextInt() + 400;
        }
        boolean[] pos = new boolean[300 + 400 + 1];
        int cnt = 0;
        while (!done(pos)) {
            boolean success = placeOne(pos);
            if (!success) {
                out.println(-1);
                System.exit(0);
            }
            cnt++;
        }
        out.println(cnt);
    }
    static boolean placeOne(boolean[] pos) {
        for (int a : arrivals) {
            int cnt = 0;
            for (int i = a + 1 - duration; i <= a; i++) {
                if (pos[i]) cnt++;
            }
            if (cnt < candles) {
                for (int i = a; i >= a + 1 - duration; i--) {
                    if (!pos[i]) {
                        pos[i] = true;
                        return true;
                    }
                }
                return false;
            }
        }
        throw new RuntimeException("Shouldn't reach here");
    }
    static boolean done(boolean[] pos) {
        for (int a : arrivals) {
            int cnt = 0;
            for (int i = a + 1 - duration; i <= a; i++) {
                if (pos[i]) cnt++;
            }
            if (cnt < candles) return false;
        }
        return true;
    }
}
