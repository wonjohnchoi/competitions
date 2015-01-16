import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


class P1 {
    static Scanner in = new Scanner(System.in);
    static boolean debug = false;
    public static void d(String str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(double str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(boolean str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(float str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(Object str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void o(String str) {
        System.out.println(str);
    }
    public static void o(int str) {
        System.out.println(str);
    }
    public static void o(boolean str) {
        System.out.println(str);
    }
    public static void o(float str) {
        System.out.println(str);
    }
    public static void o(long str) {
        System.out.println(str);
    }
    public static void o(Object str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        // debug = true;
        while (in.hasNextInt()) {
            int x1, x2, y1, y2, z1, z2;
            x1 = y1 = z1 = Integer.MIN_VALUE;
            x2 = y2 = z2 = Integer.MAX_VALUE;

            int n = in.nextInt();
            for (int i = 0; i < n; ++i) {
                int x, y, z, r;
                x = in.nextInt();
                y = in.nextInt();
                z = in.nextInt();
                r = in.nextInt();
                x1 = Math.max(x1, x);
                y1 = Math.max(y1, y);
                z1 = Math.max(z1, z);
                x2 = Math.min(x2, x + r);
                y2 = Math.min(y2, y + r);
                z2 = Math.min(z2, z + r);

            }
            
            if (x1 >= x2 || y1 >= y2 || z1 >= z2) {
                o(0);
            } else {
                o((long) ((long) x2 - x1) * (y2 - y1) * (z2 - z1));
            }
        }
        in.close();
        System.exit(0);
    }
}

/*
2
0 0 0 10
9 1 1 5
3
0 0 0 10
9 1 1 5
8 2 2 3 
 */
