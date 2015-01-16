package Senior2004;

/*
 * By Wonjohn Choi
 */
import java.util.*;
import java.io.*;

public class S2 {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s2.in"));
		int n = sc.nextInt();
		int k = sc.nextInt();

		int resultS[][] = new int[n][k];

		for (int i = 0; i < k; i++) {
			for (int j = 0; j < n; j++) {
				resultS[j][i] += sc.nextInt();
				if (i != k - 1) {
					resultS[j][i + 1] = resultS[j][i];
				}
			}

		}

		ArrayList<Integer> winner = new ArrayList<Integer>();
		int topScore = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			if (topScore < resultS[i][k - 1]) {
				winner = new ArrayList<Integer>();
				winner.add(i);
				topScore = resultS[i][k - 1];
			} else if (topScore == resultS[i][k - 1]) {
				winner.add(i);
			}
		}
		for (int w = 0; w < winner.size(); w++) {
			int worstRank = 0;
			int curWinner = winner.get(w);
			for (int i = 0; i < k; i++) {
				int curRank = 0;
				for (int j = 0; j < n; j++) {
					if (resultS[j][i] > resultS[curWinner][i]) {
						curRank++;
					}
				}
				if (curRank > worstRank) {
					worstRank = curRank;
				}
			}
			System.out.printf("Yodeller %d is the TopYodeller: score %d, worst rank %d\n",
							curWinner + 1, topScore, worstRank + 1);
		}
	}

}
