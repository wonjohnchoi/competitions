package Senior;

import java.util.*;
import java.io.*;
/*
 * By Wonjohn Choi
 */
public class S5 {
	static char used[];

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s5.in"));
		int numCase = sc.nextInt();

		used = new char[calc(31, 31, 31, 31) + 1];
		for (int i = 1; i <= 30; i++)
			for (int j = 1; j <= 30; j++)
				for (int k = 1; k <= 30; k++)
					for (int l = 1; l <= 30; l++)
						winning(i, j, k, l);
		for (int i = 0; i < numCase; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int C = sc.nextInt();
			int D = sc.nextInt();

			if (winning(A, B, C, D))
				System.out.println("Patrick");
			else
				System.out.println("Roland");
		}
	}

	public static boolean winning(int A, int B, int C, int D) {

		if (A < 0 || B < 0 || C < 0 || D < 0)
			return true;
		int temp = calc(A, B, C, D);
		if (used[temp] == 'w')
			return true;
		else if (used[temp] == 'l')
			return false;

		used[temp] = 'w';

		if (!winning(A - 2, B - 1, C, D - 2))
			return true;
		if (!winning(A - 1, B - 1, C - 1, D - 1))
			return true;
		if (!winning(A, B, C - 2, D - 1))
			return true;
		if (!winning(A, B - 3, C, D))
			return true;
		if (!winning(A - 1, B, C, D - 1))
			return true;
		used[temp] = 'l';
		return false;

	}

	public static int calc(int a, int b, int c, int d) {
		return a * 31 * 31 * 31 + b * 31 * 31 + c * 31 + d;
	}
}
