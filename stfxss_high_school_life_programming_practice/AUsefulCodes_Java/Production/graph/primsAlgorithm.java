package graph;

import java.io.*;
import java.util.*;

public class primsAlgorithm {
	public static int INFTY = Integer.MAX_VALUE;

	public static void main(String args[]) throws IOException {
		Scanner fin = new Scanner(new FileReader("graph.txt"));
		int V = fin.nextInt();

		int dist[] = new int[V];
		int adj[][] = new int[V][V];
		boolean intree[] = new boolean[V];

		for (int i = 0; i < V; i++)
			for (int j = 0; j < V; j++)
				adj[i][j] = fin.nextInt();

		for (int i = 0; i < V; i++) {
			dist[i] = INFTY;
			intree[i] = false;
		}

		int mstcost = 0, curnode = 0;

		for (int j = 0; j < V - 1; j++) {
			intree[curnode] = true;

			for (int i = 0; i < V; i++)
				if (dist[i] > adj[curnode][i])
					dist[i] = adj[curnode][i];

			int min = INFTY;

			for (int i = 0; i < V; i++) {
				if (dist[i] < min && !intree[i]) {
					min = dist[i];
					curnode = i;
				}
			}
			mstcost += dist[curnode];
		}

		System.out.println(mstcost);
		System.exit(0);
	}

}
