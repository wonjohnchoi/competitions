import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author wonjohnchoi
 *
 */
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
    	while (in.hasNextLine()) {
    		String s1 = in.nextLine();
    		String s2 = in.nextLine();
    		int[][] cache = new int[s1.length()][s2.length()];
    		for (int i = 0; i < cache.length; i += 1) {
    			Arrays.fill(cache[i], -1);
    		}
    		o(s1.length() + s2.length() - 2 * maxEqual(0, 0, s1, s2, cache));
    		
    	}
        in.close();
        System.exit(0);
    }
    
    static int maxEqual(int p1, int p2, String s1, String s2, int[][] cache) {
    	// d(p1 + " " + p2 + " ");
    	d(s1.substring(p1) + " " + s2.substring(p2));
    	if (p2 >= s2.length()) {
    		return 0;
    	}
    	if (p1 >= s1.length()) {
    		return 0;
    	}
    	if (cache[p1][p2] != -1) {
    		return cache[p1][p2];
    	}
    	int val = 0;
    	int q1 = s1.substring(p1).indexOf(s2.charAt(p2)) + p1;
    	d(q1);
    	if (q1 < p1) {
    		val = maxEqual(p1, p2 + 1, s1, s2, cache);
    	} else {
    		val = Math.max(1 + maxEqual(q1 + 1, p2 + 1, s1, s2, cache), maxEqual(p1, p2 + 1, s1, s2, cache));
    	}
    	cache[p1][p2] = val;
    	d("VAL: " + val);
    	return val;
    }
}
