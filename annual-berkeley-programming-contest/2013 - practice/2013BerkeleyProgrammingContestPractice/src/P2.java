import java.util.ArrayList;
import java.util.Scanner;


class P2 {
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

    static class Point {
        double x, y;
        double distTo(Point p) {
            double dx, dy;
            dx = x - p.x;
            dy = y - p.y;
            return Math.sqrt(dx * dx + dy * dy);
        }
        public String toString() {
            return String.format("(%f, %f)", x, y);
        }
    }
    public static void main(String[] args) {
        // debug = true;
        int c = 1;
        while (in.hasNextLine()) {
            String[] tokens = in.nextLine().split(" ");
            ArrayList<Point> points = new ArrayList<Point>();
            for (int i = 0; i < tokens.length / 2; i += 1) {
                Point point = new Point();
                point.x = Double.parseDouble(tokens[i * 2]);
                point.y = Double.parseDouble(tokens[i * 2 + 1]);
                points.add(point);
            }
            d(points);
            
            double max = Integer.MIN_VALUE;
            for (int i = 0; i < points.size(); i += 1) {
                for (int j = 0; j < i; j += 1) {
                    max= Math.max(max, points.get(i).distTo(points.get(j)));
                }
            }
            if (max == Integer.MIN_VALUE) {
                max = 0;
            }
            o(String.format("Set #%d: Diameter is %.2f", c, max));
            c += 1;
        }
        
        in.close();
        System.exit(0);
    }
}
