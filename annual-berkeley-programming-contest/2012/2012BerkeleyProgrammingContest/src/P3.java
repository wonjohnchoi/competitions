import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author wonjohnchoi
 *
 */
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
    	debug = true;
    	while (in.hasNextInt()) {
    		int W, H;
    		W = in.nextInt();
    		H = in.nextInt();
    		if (W == 0 && H == 0) {
    			break;
    		}
    		
    		int[] top = new int[W];
    		int[] bot = new int[W];
    		int[] right = new int[H];
    		int[] left = new int[H];
    		for (int i = 0; i < W; i += 1) {    			
    			top[i] = in.nextInt();
    		}
    		for (int i = 0; i < W; i += 1) {
    			bot[i] = in.nextInt();
    		}
    		for (int i = 0; i < H; i += 1) {
    			left[i] = in.nextInt();
    		}
    		for (int i = 0; i < H; i += 1) {
    			right[i] = in.nextInt();
    		}
    		char[][] map = new char[H][W];
    		for (int h = 0; h < H; h += 1) {
    			String line = in.next();
    			map[h] = line.toCharArray();
    		}
    		
    		char[][] result = new char[H][W];
    		for (int h = 0; h < H; h += 1) {
    			Arrays.fill(result[h], '?');
    		}
			d("Searching started...");
    		char[][] found = find(map, top, bot, left, right, result, 0, new int[W], new int[W], new int[H], new int[H], new int[W], new int[H]);
			
    		for (int i = 0; i < H; i += 1) {
    			o(Arrays.toString(found[i]));
    		}
    	}
        in.close();
        System.exit(0);
    }
    
    
    static char[][] find(char[][] map, int[] top, int[] bot, int[] left, int[] right, char[][] result, int idx, int[] rTop, int[] rBot, int[] rLeft, int[] rRight, int[] colX, int[] rowX) {
    	int h = idx / top.length;
    	int w = idx % top.length;
    	d("h: " + h + ", w: " + w);
    	for (int i = 0; i < result.length; i += 1) {
    		d(Arrays.toString(result[i]));
    	}
    	if (idx == left.length * top.length) {

    		return result;
    	}
    	char found[][] = null;
    	
    	result[h][w] = '+';
    	rTop[w] += 1;
    	rLeft[h] += 1;
    	
    	if ((top[w] == -1 || rTop[w] <= top[w]) && (left[h] == -1 || rLeft[h] <= left[h]) && (bot[w] == -1 || (top.length - rTop[w] - colX[w]) >= bot[w]) && ((right.length - rLeft[h] - rowX[h]) >= right[h] || right[h] == -1)) {
        	d("Barrier1!");

    		found  = find(map, top, bot, left, right, result, idx + 1, rTop, rBot, rLeft, rRight, colX, rowX);
    	}
    	if (found != null) {
    		return found;
    	}

    	result[h][w] = '-';
       	rTop[w] -= 1;
    	rLeft[h] -= 1;
    	rBot[w] += 1;
    	rRight[h] += 1;
    	if ((bot[w] == -1 || rBot[w] <= bot[w]) && (rRight[h] <= right[h] || right[h] == -1) && ((bot.length - rBot[w] - colX[w]) >= top[w] || top[w] == -1) && ((left.length - rRight[h] - rowX[h]) >= left[h] || left[h] == -1)) {
        	d("Barrier2!");

    		found  = find(map, top, bot, left, right, result, idx + 1, rTop, rBot, rLeft, rRight, colX, rowX);
    	}
    	if (found != null) {
    		return found;
    	}
    	
    	result[h][w] = 'x';
    	rBot[w] -= 1;
    	rRight[h] -= 1;
    	colX[w] += 1;
    	rowX[h] += 1;
    	if (((top.length - rBot[w] - colX[w]) >= top[w] || top[w] == -1) && ((right.length - rRight[h] - rowX[h]) >= left[h] || left[h] == -1) && ((top.length - rTop[w] - colX[w]) >= bot[w] || bot[w] == -1) && ((right.length - rLeft[h] - rowX[h]) >= right[h] || right[h] == -1)) {
        	d("Barrier3!");

    		found  = find(map, top, bot, left, right, result, idx + 1, rTop, rBot, rLeft, rRight, colX, rowX);
    	}
    	if (found != null) {
    		return found;
    	}
    	colX[w] -= 1;
    	rowX[h] -= 1;
    	d("NULL BAERRIER?");
		return null;
    }
}
