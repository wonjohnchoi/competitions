package capcs.choi.yr20092010.round2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author Wonjohn Choi
 * http://dwite.ca/questions/breadth_first_not_quite_tree.html
 */
public class Q4 {
	public static void main(String args[]) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter("OUT4.txt"));
		Scanner sc = new Scanner(new FileReader("DATA4.txt"));

		for (int i = 0; i < 5; i++) {
			int n = sc.nextInt();
			int x, y;
			int nodes[][] = new int[99][99];
			for (int j = 0; j < 99; j++)
				for (int k = 0; k < 99; k++)
					nodes[j][k] = 10000;

			for (int i1 = 0; i1 < n; i1++) {
				x = sc.nextInt();
				y = sc.nextInt();
				nodes[x - 1][y - 1] = 1;
				nodes[y - 1][x - 1] = 1;
			}

			for (int j = 0; j < 99; j++)
				for (int k = 0; k < 99; k++)
					for (int l = 0; l < 99; l++)
						nodes[j][l] = Math.min(nodes[j][l], nodes[j][k]
								+ nodes[k][l]);

			int ans = 0;
			for (int a = 1; a < 99; a++)
				for (int b = 1; b < a; b++)
					if (nodes[0][a] < 10000 && nodes[0][b] == nodes[0][a])
						if (nodes[a][b] == 1)
							ans++;
			pw.println(ans);

		}

		pw.close();
		System.exit(0);

	}
}
