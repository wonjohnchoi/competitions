import java.util.*;
import java.io.*;
// TIME_USED:
public class C {
    public static boolean DEBUG = true;
    public static void d(Object o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(int o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(long o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(double o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(float o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static void d(boolean o) {
        if (DEBUG) {
            System.out.println(o);
        }
    }
    public static class Car implements Comparable<Car> {
        int start, end, speed;
        boolean left;
        public Car(char c, int s, int p) {
            start = s;
            end = s + 5;
            speed = p;
            left = c == 'L';
        }
        @Override
            public int compareTo(Car c) {
            return start - c.start;
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            Car[] cars = new Car[N];
            ArrayList<Integer> points = new ArrayList<Integer>();
            for (int i = 0; i < N; i++) {
                char C;
                int S, P;
                C = sc.next().charAt(0);
                S = sc.nextInt();
                P = sc.nextInt();
                cars[i] = new Car(C, S, P);
                points.add(cars[i].start);
                points.add(cars[i].end);
            }
            points = new ArrayList<Integer>(new HashSet(points));
            Collections.sort(points);
            while (true) {
                boolean found = false;
                double 
                for (Car i = 0; i < cars.length; i++) {
                    for (Car j = 0; j < cars.length; j++) {
                        if (i == j) continue;
                        int dist = cars.get(j).end - cars.get(i).start;
                        int speed = cars.get(i).speed - cars.get(j).speed;
                        if (speed == 0) continue;
                        double time = ((double) dist) / speed;
                        
                    }
                }
            }
            /*
            for (int i = 0; i < points.length; i++) {
                for (int j = 0; j < cars.length; j++) {
                    int point = points.get(i);
                    Car car = cars.get(j);
                    if (point >= )
                }
                }*/
            // System.out.printf("Case #%d: %?\n", tc, ?);
        }
    }
}
