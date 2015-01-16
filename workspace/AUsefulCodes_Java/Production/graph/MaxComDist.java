package graph;

import java.io.*;
import java.util.*;
/*
 * With Prim's Algorithm, find a max distance that is 
 * bigger than every distances that "from" needs to go through to reach specific points.
 */
public class MaxComDist {
	public static int numCity, numRoute, cost[][], max[];
	public static int M = Integer.MIN_VALUE;

	public static void main(String args[]) throws IOException {
		int from=0;
		process(from);
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
