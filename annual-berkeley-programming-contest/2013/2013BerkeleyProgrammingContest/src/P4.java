import java.util.ArrayList;
import java.util.Scanner;


class P4 {
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
            int a = in.nextInt();
            int b = in.nextInt();
            String x, y;
            x = Integer.toBinaryString(a);
            y = Integer.toBinaryString(b);
            d(x);
            d(y);
            int ans = -1;
            if (x.length() < y.length()) {
                ans = a;
            } else {
                while (x.length() >= y.length()) {
                    String newX = "";
                    for (int i = 0; i < y.length(); i++) {
                        if (y.charAt(i) == '1') {
                            newX += flip(x.charAt(i));
                        } else {
                            newX += x.charAt(i);
                        }
                    }
                    newX += x.substring(y.length());

                    d(newX);
                    
                    x = trim(newX);
                }
                ans = toInt(x);
            }
            o(String.format("As polynomials, %d mod %d = %d", a, b, ans));
        }
        
        in.close();
        System.exit(0);
    }
    static int toInt(String bin) {
        int x = 0;
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) == '1')
                x += Math.pow(2, bin.length() - i  - 1);
        }
        return x;
    }
    static char flip(char a) {
        if (a == '0') return '1';
        return '0';
    }
    static String trim(String bin) {
        while (bin.startsWith("0")) {
            bin = bin.substring(1);
        }
        return bin;
    }
}
