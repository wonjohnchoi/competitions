import java.util.ArrayList;
import java.util.Scanner;


class P8 {
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
        while (in.hasNextDouble()) {
            double w, t00, t01, t10, t11;
            w = in.nextDouble();
            t00 = in.nextDouble();
            t01 = in.nextDouble();
            t10 = in.nextDouble();
            t11 = in.nextDouble();
            double i1, i2;
            i1 = w / (Math.tan(Math.toRadians(t00)) + Math.tan(Math.toRadians(-t01)));
            i2 = w / (Math.tan(Math.toRadians(t10)) + Math.tan(Math.toRadians(-t11)));
            double time;
            time = i1/(i1 - i2);
            d(i1 + " " + i2);
            
            double x0 = i1 * Math.tan(Math.toRadians(t00));
            double x1 = i2 * Math.tan(Math.toRadians(t10));
            double x = x0 + (x1 - x0) * time;
            //double x = i1 * Math.tan(Math.toRadians(t00));

            d(x);
            if (i1 <= i2) {
                o("Not approaching");
            } else {
                o(String.format("Intersects at x=%.2f, t=%.2f", x, time));
            }
        }
        in.close();
        System.exit(0);
    }
}
