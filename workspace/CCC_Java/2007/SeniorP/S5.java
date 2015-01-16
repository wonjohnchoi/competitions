package SeniorP;
//not yet

import java.util.*;
import java.io.*;

public class S5 {
	static int sum[];
	static int size;
	static int result[];
	static int store[][];

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("DATA5.txt"));

		int numTestCase = sc.nextInt();

		for (int i = 0; i < numTestCase; i++) {
			int numPin = sc.nextInt();
			int numBall = sc.nextInt();
			size = sc.nextInt();
			int pin[] = new int[numPin];
			int numSum = numPin - size + 1;
			sum = new int[numSum];
			for (int j = 0; j < numPin; j++)
				pin[j] = sc.nextInt();
			for (int j = 0; j < numSum; j++)
				for (int k = 0; k < size; k++)
					sum[j] += pin[j + k];

			System.out.println("FINISHED Get Input");
			// for (int j = 0; j < numSum; j++)
			// System.out.println(sum[j]);
			System.out.println(count(sum, numBall, 0));
		}

	}

	public static int count(int sum[], int numBall, int score) {

		int ans = 0;
		int numSum = sum.length;
		// System.out.println(numSum+" "+numBall+" "+score);
		if (numBall == 0 || numBall * size > numSum + size - 1) {
			return score;
		}

		else {

			for (int i = 0; i < numSum; i++) {
				int tempC = score + sum[i];
				int newSize = numSum - i - 3;
				if (newSize > 0) {
					int temp[] = new int[newSize];

					for (int j = 0; j < newSize; j++) {
						temp[j] = sum[j + i + 3];
					}

					/*
					 * if(i<numPin - size ) { int
					 * tempC2=pin[i]+pin[i+1]+pin[i+2]; if(tempC2>tempC)
					 * tempC=tempC2; System.out.println(tempC); } else
					 */
					// System.out.println("HIH "+sum[i]+" "+i+" "+numSum);
					tempC = count(temp, numBall - 1, tempC);
				}
				if (tempC > ans)
					ans = tempC;

			}
			return ans;
		}
	}
}
