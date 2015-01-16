package capcs.choi.yr20092010.round2;

import java.util.*;
import java.io.*;

/**
 * 
 * @author Wonjohn Choi
 * http://dwite.ca/questions/mini_dwite.html
 */
public class Q2 {
	public static void main(String args[]) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter("OUT2.txt"));
		Scanner sc = new Scanner(new FileReader("DATA2.txt"));

		for (int i = 0; i < 5; i++) {
			int scoreL[] = new int[5];
			int scoreR[] = new int[5];
			int sum[] = new int[5];
			int count = 0;
			for (int j = 0; j < 5; j++) {
				scoreL[j] = sc.nextInt();
				scoreR[j] = sc.nextInt();
			}

			for (int j = 0; j < 5; j++) {
				sum[j] = sc.nextInt();
				if (scoreL[j] + scoreR[j] == sum[j])
					count++;
			}
			pw.println(count);
		}

		pw.close();
		System.exit(0);

	}
}