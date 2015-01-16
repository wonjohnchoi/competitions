package capcs.choi.yr20092010.round1;

import java.util.*;
import java.io.*;

/**
 * 
 * @author Wonjohn Choi
 * 
 */
public class Q5 {
	public static void main(String args[]) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter("OUT5.txt"));
		Scanner sc = new Scanner(new FileReader("DATA5.txt"));

		for (int i = 0; i < 5; i++) {
			int numLines = sc.nextInt();
			int[][] ID = new int[100][100];

			for (int a = 0; a < 100; a++)
				for (int b = 0; b < 100; b++)
					ID[a][b] = 100000;

			for (int j = 0; j < numLines; j++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				ID[a - 1][b - 1] = 1;
			}

			// Floyd-Warshall algorithm
			for (int a = 0; a < 100; a++)
				for (int b = 0; b < 100; b++)
					for (int c = 0; c < 100; c++)
						if (ID[a][b] + ID[b][c] < ID[a][c])
							ID[a][c] = ID[a][b] + ID[b][c];

			for (int a = 0; a < 101; a++)
				if (ID[a][a] < 10000) {
					pw.println(ID[a][a]);
					break;
				}
		}
		pw.close();
		System.exit(0);
	}
}
