package SeniorProblem;

//done/but little modification needed?
import java.util.*;
import java.io.*;

/*
 * By Wonjohn Choi
 */
public class S5 {
	static int sMax, sMin, rMin;

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("DATA5.txt"));
		int height = sc.nextInt();
		int width = sc.nextInt();
		char input[][] = new char[height][width];
		sMin = sc.nextInt();
		sMax = sc.nextInt();
		rMin = sc.nextInt();
		for (int i = 0; i < height; i++) {
			String line = sc.next();
			for (int j = 0; j < width; j++) {
				input[i][j] = line.charAt(j);
			}
		}

		int size = (int) Math.pow(2, height * width);
		int aim = uniqueNum(input);
		boolean isEden[] = new boolean[size];
		Arrays.fill(isEden, true);
		for (int i = 0; i < size; i++) {
			if (isEden[i]) {
				char[][] eden = uniqueMap(i, height, width);

				eden = nextGeneration(eden);

				int temp = uniqueNum(eden);

				while (isEden[temp]) {
					isEden[temp] = false;

					eden = nextGeneration(eden);
					temp = uniqueNum(eden);

				}
			}

		}
		short dist[] = new short[size];
		Arrays.fill(dist, Short.MAX_VALUE);
		for (int i = 0; i < size; i++)
			if (isEden[i]) {
				dist[i] = 0;
				short count = 0;
				char nextEden[][] = nextGeneration(uniqueMap(i, height, width));
				count++;
				int temp = uniqueNum(nextEden);

				while (dist[temp] > count) {
					dist[temp] = count;
					nextEden = nextGeneration(nextEden);
					temp = uniqueNum(nextEden);
					count++;
				}
			}
		if (dist[aim] == Short.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(dist[aim]);

	}

	public static char[][] uniqueMap(int n, int height, int width) {
		char temp[][] = new char[height][width];
		int h = 0;
		int w = 0;
		for (int i = 0; i < height; i++)
			Arrays.fill(temp[i], '.');
		while (n != 0) {
			if (n % 2 != 0) {
				temp[h][w] = '*';
				n--;
			}

			n /= 2;
			w++;
			if (w == width) {
				w = 0;
				h++;
			}
		}
		return temp;
	}

	public static int uniqueNum(char[][] a) {
		int height = a.length;
		int width = a[0].length;
		int count = 0;
		int multiply = 1;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (a[i][j] == '*')
					count += multiply;
				multiply *= 2;
			}
		}
		return count;
	}

	/*
	 * public static boolean identical(char[][] a1, char[][] a2) { int length =
	 * a1.length; for (int i = 0; i < length; i++) { String s1 = new
	 * String(a1[i]); String s2 = new String(a2[i]); if (!s1.equals(s2)) {
	 * return false; } } return true; }
	 */
	/*
	 * public static void printArray(char[][] info) { for (int i = 0; i <
	 * info.length; i++) { for (int j = 0; j < info[0].length; j++) {
	 * System.out.print(info[i][j]); } System.out.println(); }
	 * System.out.println(); }
	 * 
	 * public static void print(String s) { System.out.println(s); }
	 */
	public static char[][] nextGeneration(char[][] before) {
		int height = before.length;
		int width = before[0].length;
		char temp[][] = new char[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				temp[i][j] = before[i][j];
			}
		}
		int top, bottom, left, right, living, dead;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				living = 0;
				dead = 0;
				top = Math.max(0, i - 1);
				bottom = Math.min(height - 1, i + 1);
				left = Math.max(0, j - 1);
				right = Math.min(width - 1, j + 1);
				for (int k = top; k <= bottom; k++) {
					for (int l = left; l <= right; l++) {
						if (before[k][l] == '*') {
							living++;
						} else
							dead++;
					}
				}
				if (before[i][j] == '*') {
					living--;
					if (living < sMin || living > sMax) {
						temp[i][j] = '.';
					}
				} else {
					dead--;
					if (living > rMin) {
						temp[i][j] = '*';
					}
				}
			}
		}
		return temp;
	}

}
