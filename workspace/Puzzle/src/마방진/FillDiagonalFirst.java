package ¸¶¹æÁø;

import java.util.Vector;

public class FillDiagonalFirst {
	static int map[][];
	static int len;

	public static void main(String args[]) {
		len = 10;

		map = new int[len][len];

		for (int i = 0; i < len; i++) {
			map[i][i] = i + 1;
		}

		fill(0, 0);
	}

	public static void fill(int y, int x) {
		/*
		 * for(int i=0;i<len;i++) { for(int j=0;j<len;j++) {
		 * System.out.print(map[i][j]); } System.out.println(); }
		 * System.out.println();
		 */

		if (x == len) {
			x = 0;
			y += 1;
			if (y == len) {
				if (isPerfect()) {
					for (int i = 0; i < len; i++) {
						for (int j = 0; j < len; j++) {
							String out = map[i][j] + "";
							while (out.length() != 2) {
								out += " ";
							}
							System.out.print(out + "|");
						}
						System.out.println();

					}
				} else {
					System.err.println("Unexpected Error");
				}
				System.exit(0);
			}
		}

		if (map[y][x] == 0) {
			Vector<Integer> n = find(y, x);
			while (!n.isEmpty()) {
				map[y][x] = n.remove(0);
				fill(y, x + 1);
				map[y][x] = 0;
			}
		} else {
			fill(y, x + 1);
		}
	}

	private static boolean isPerfect() {
		int ans = (1 + len) * len / 2;

		for (int i = 0; i < len; i++) {
			int sum = 0;
			for (int j = 0; j < len; j++) {
				sum += map[i][j];
			}
			if (sum != ans) {
				return false;
			}

			sum = 0;
			for (int j = 0; j < len; j++) {
				sum += map[j][i];
			}

			if (sum != ans) {
				return false;
			}
		}
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += map[i][i];
		}

		if (sum != ans) {
			return false;
		}
		sum = 0;

		for (int i = 0; i < len; i++) {
			sum += map[i][len - i - 1];
		}

		if (sum != ans) {
			return false;
		}

		return true;
	}

	private static Vector<Integer> find(int y, int x) {
		Vector<Integer> v = new Vector<Integer>();
		for (int i = 1; i <= len; i++) {
			boolean correct = true;

			for (int j = 0; j < x; j++) {
				// horizontal
				if (map[y][j] == i) {
					correct = false;
					break;
				}
			}

			// diagonal
			if (correct && (map[y][y] == i || map[x][x] == i)) {
				correct = false;
			}

			if (correct) {
				// vertical
				for (int j = 0; j < y; j++) {
					if (map[j][x] == i) {
						correct = false;
						break;
					}
				}

				if (correct) {
					/*
					 * if(y==x) { for(int j=0;j<x;j++) { if(map[j][j]==i) {
					 * correct=false; break; } } }
					 */
					if (x + y == len - 1) {
						for (int j = 0; j < y; j++) {
							if (map[j][len - j - 1] == i) {
								correct = false;
								break;
							}
						}

						if (len % 2 == 1 && map[len / 2][len / 2] == i) {
							correct = false;
						}
					}

					if (correct) {
						v.add(i);
					}
				}
			}

		}
		return v;
	}
}
