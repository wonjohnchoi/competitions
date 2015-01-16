import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author wonjohnchoi
 *
 */
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
    	// floyd
    	int c = 1;
    	int n = in.nextInt();
    	while (n != 0) {
    		
    		long[][] dists = new long[100][100];
    		for (int i = 0; i < 100; i += 1) {
    			dists[i][i] = 0;
    			for (int j = 0; j < 100; j += 1) {
    				if (i != j) {
    					dists[i][j] = Integer.MAX_VALUE;
    				}
    			
    			}
    			
    		}

    		int cityCnt = 0;
    		HashMap<String, Integer> cityNum = new HashMap<String, Integer>();
    		for (int i = 0; i < n; i += 1) {
    			String c1, c2;
    			long d;
    			c1 = in.next();
    			c2 = in.next();
    			d = in.nextLong();
    			int n1, n2;
    			if (cityNum.containsKey(c1)) {
    				n1 = cityNum.get(c1);
    			} else {
    				cityNum.put(c1, cityCnt);
    				n1 = cityCnt;
    				cityCnt += 1;
    			}
    			if (cityNum.containsKey(c2)) {
    				n2 = cityNum.get(c2);
    			} else {
    				cityNum.put(c2, cityCnt);
    				n2 = cityCnt;
    				cityCnt += 1;
    			}
    			dists[n1][n2] = d;
    			dists[n2][n1] = d;
    			
    			
    		}
    		d(cityNum);
    		int max = Integer.MIN_VALUE;
    		for (int j = 0; j < cityCnt; j += 1) {
    			for (int i = 0; i < cityCnt; i += 1) {
    				for (int k = 0; k < cityCnt; k += 1) {
    					if (dists[i][j] + dists[j][k] < dists[i][k]) {
    						dists[i][k] = dists[i][j] + dists[j][k];
    					}
    				}
    			}
    		}
    		for (int i = 0; i < cityCnt; i += 1) {
    			String str = "";
    			for (int j = 0; j < cityCnt; j += 1) {
    				max = (int) Math.max(max, dists[i][j]);
    				str += dists[i][j] + " ";
    			}
    			d(str);
    		}
    		o(String.format("Set #%d: %d miles", c, max));
    		n = in.nextInt();
    		c += 1;
    	}
    	
    	
    	//long[][] dists = new long[]
        in.close();
        System.exit(0);
    }
}
