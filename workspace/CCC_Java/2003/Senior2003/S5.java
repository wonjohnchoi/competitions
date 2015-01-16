package Senior2003;

import java.io.*;
import java.util.*;

public class S5 {
	public static int numCity, numRoute, numDest, cost[][], dest[], max[];
	public static int M = Integer.MIN_VALUE;

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s5.in"));
		numCity = sc.nextInt();
		numRoute = sc.nextInt();
		numDest = sc.nextInt();

		cost = new int[numCity][numCity];
		for (int i = 0; i < numCity; i++)
			Arrays.fill(cost[i], M);

		for (int i = 0; i < numRoute; i++) {
			int x, y, fee;
			x = sc.nextInt() - 1;
			y = sc.nextInt() - 1;
			fee = sc.nextInt();

			if (cost[x][y] != M) {
				if (cost[x][y] < fee) {
					cost[x][y] = fee;
					cost[y][x] = fee;
				}
			} else {
				cost[x][y] = fee;
				cost[y][x] = fee;
			}
		}

		dest = new int[numDest];

		for (int i = 0; i < numDest; i++) {
			dest[i] = sc.nextInt() - 1;

		}
		process(0);

		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < numDest; i++) {
			if (max[dest[i]] < ans) {
				ans = max[dest[i]];
			}
		}
		System.out.println(ans);
	}

	public static void process(int start) {
		boolean used[] = new boolean[numCity];
		max = new int[numCity];
		Arrays.fill(max, M);

		max[start] = Integer.MAX_VALUE;
		int cur = start;
		int min;
		do {
			min = M;

			for (int i = 0; i < numCity; i++) {
				if (!used[i] && max[i] < Math.min(max[cur], cost[cur][i])) {
					max[i] = Math.min(max[cur], cost[cur][i]);
				}
			}

			for (int i = 0; i < numCity; i++) {
				if (!used[i] && min < max[i]) {
					min = max[i];
					cur = i;
				}
			}

			used[cur] = true;
		} while (min != M);

	}
}
