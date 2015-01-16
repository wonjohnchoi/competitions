package SeniorProblem;
/*
 * By Wonjohn Choi
 */
import java.util.*;
import java.io.*;
//right but need to modify;
public class S1 {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s1.in"));
		String M = sc.next();
		String F = sc.next();

		short MG[] = new short[5];
		short FG[] = new short[5];
		short result[] = new short[5];
		for (int i = 0; i < 5; i++) {
			if ((int) (M.charAt(2 * i) - 'a') == i
					&& (int) (M.charAt(2 * i + 1) - 'a') == i) {
				MG[i] = -1;
			} else if (((int) (M.charAt(2 * i) - 'A') == i)
					&& (int) (M.charAt(2 * i + 1) - 'A') == i) {
				MG[i] = 1;
			} else
				MG[i] = 0;

			if ((int) (F.charAt(2 * i) - 'a') == i
					&& (int) (F.charAt(2 * i + 1) - 'a') == i) {
				FG[i] = -1;
			} else if (((int) (F.charAt(2 * i) - 'A') == i && (int) (F
					.charAt(2 * i + 1) - 'A') == i)) {
				FG[i] = 1;
			} else
				FG[i] = 0;

			if (MG[i] == 1 && FG[i] == 1)
				result[i] = 1;
			else if (MG[i] == -1 && FG[i] == -1)
				result[i] = -1;
			else if ((MG[i] == 1 && FG[i] == -1) || MG[i] == -1 && FG[i] == 1)
				result[i] = 1;
			else if (MG[i] == 0 && FG[i] == 0)
				result[i] = 0;
			else if (MG[i] == -1 ||FG[i]==-1)
				result[i] = 0;
			else
				result[i] = 1;

		//	System.out.println(result[i] + "" + MG[i] + FG[i]);
		}

		int numChild = sc.nextInt();

		for (int i = 0; i < numChild; i++) {

			boolean baby = true;
			String child = sc.next();

			for (int j = 0; j < 5 && baby; j++) {
				// System.out.println((int) (child.charAt(j) - 'A') == j);
				if ((((int) (child.charAt(j) - 'A') == j) == false && result[j] == 1)
						|| (((int) (child.charAt(j) - 'A') == j) == true && result[j] == -1)) {
					baby = false;

				}

			}

			if (baby)
				System.out.println("Possible baby.");
			else
				System.out.println("Not their baby!");
		}

	}
}
