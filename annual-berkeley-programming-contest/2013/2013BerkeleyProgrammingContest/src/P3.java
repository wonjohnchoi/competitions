import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


class P3 {
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
        while (in.hasNextLine()) {
            int n = in.nextInt();
            ArrayList<Integer> ins = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                ins.add(in.nextInt());
            }
            int m = in.nextInt();
            
            if (m <= n) {
                o(String.format("Term %d of the sequence is %d", m, ins.get(m - 1)));
            } else {
                int[][] map = new int[m][m];
                for (int i = 0; i < n; i++) {
                    map[i][0] = ins.get(i);
                }
                for (int c = 1; c < n; c++) {
                    for (int r = 0; r < n - c; r++) {
                        map[r][c] = map[r + 1][c - 1] - map[r][c - 1];
                    }
                }
                for (int i = 0; i < m; i ++) {
                    d(Arrays.toString(map[i]));
                }
    
                for (int c = n; c < m; c++) {
                    map[1][c - 1] = map[0][c - 1];
                    for (int r = 2; r <= c; r++) {
                        d("r: " + r + " c: " + c);
                        map[r][c - r] = map[r - 1][c - r] + map[r - 1][c - r + 1];
                    }
                }
                for (int i = 0; i < m; i ++) {
                    d(Arrays.toString(map[i]));
                }
                o(String.format("Term %d of the sequence is %d", m, map[m - 1][0]));
                
            }
            in.nextLine();
        }
        in.close();
        System.exit(0);
    }
}

/*
4 3 6 10 15 5
4 3 6 10 15 2
4 3 6 10 15 6
3 2 4 6 23
6 3 9 12 5 18 -4 16
*/