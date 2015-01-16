import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author wonjohnchoi
 *
 */
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
    public static void d(long str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(int str) {
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
    static long fact(int n) {
    	if (n == 0) return 1;
    	return n * fact(n - 1);
    }
    static long comb(int n, int r) {
    	if (n - r > r) {
    		r = n - r;
    	}
     	long out = 1;
    	for (int i = n; i > r; i -= 1) {
    		out *= i;
    	}
    	return out / fact(n - r);
    }

    public static void main(String[] args) {
        // debug = true;
        while (in.hasNextLong()) {
        	long n = in.nextLong();
        	d(n);
        	String str = Long.toBinaryString(n);
        	int oones;
        	int ones = 0, zeros = 0;
        	for (int i = 0; i < str.length(); i += 1) {
        		if (str.charAt(i) == '1') {
        			ones += 1;
        		} else {
        			zeros += 1;
        		}
        	}
        	oones = ones;
        	long result = comb(ones + zeros, ones);
        	d(result);
        	for (int i = 0; i < str.length(); i += 1) {
        		if (str.charAt(i) == '0' && ones >= 1) {
        			result -= comb(ones + zeros - 1, zeros);
        			d(result);
        			zeros -= 1;
        		} else {
        			ones -= 1;
        		}
        	}
        	o(String.format("%d is number %d in the sequence of numbers with %d 1-bits.", n, result - 1, oones));
        }
        in.close();
        System.exit(0);
    }
}
