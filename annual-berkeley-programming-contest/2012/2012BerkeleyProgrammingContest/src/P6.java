import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author wonjohnchoi
 *
 */
class P6 {
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
        //debug = true;
    	int[] dx = new int[]{-1, 0, 1, 1, 0, -1};
    	int[] dy = new int[]{0, 1, 1, 0, -1, -1};
    	int N = 10001;
    	int[] cx = new int[N * 2];
    	int[] cy = new int[N * 2];
    	cx[1] = 0;
    	cy[1] = 0;
    	int level = 1;
    	int x = 0;
    	int y = -1;
    	int idx = 2;
    	
    	while (idx < N) {
    		for (int dir = 0; dir < 6; dir += 1) {
    			if (dir == 4) {
    				cx[idx] = x;
    				cy[idx] = y;
    				x += dx[dir];
    				y += dy[dir];
    				idx += 1;
    			}
    			for (int i = 0; i < level; i += 1) {
    				cx[idx] = x;
    				cy[idx] = y;
    				x += dx[dir];
    				y += dy[dir];
    				idx += 1;
    			}
    		}
    		level += 1;
    	}
    	for (int i = 0; i < 60; i += 1) {
    		d(i + ":" + "(" + cx[i] + "," + cy[i]+")");
    	}
    	while (in.hasNextInt()) {
    		int a = in.nextInt();
    		int b = in.nextInt();
    		if (a == 0 && b == 0) {
    			break;
    		}
    		
    		int result = 0;
    		int ddx = cx[b] - cx[a];
    		int ddy = cy[b] - cy[a];
    		if ((ddx > 0 && ddy > 0) || (ddx < 0 && ddy < 0)) {
    			result = Math.max(Math.abs(ddx), Math.abs(ddy));
    		} else {
    			result = Math.abs(ddx) + Math.abs(ddy);
    		}
    		o(String.format("The distance between cells %d and %d is %d.", a, b, result));
    	}
 
        in.close();
        System.exit(0);
    }
}
