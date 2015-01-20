package SeniorP;

import java.util.*;
import java.io.*;

/*
 * By Wonjohn Choi
 */
public class S2 {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s2.in"));
		int numCase = sc.nextInt();
		list cases[] = new list[numCase];
		int oneCase[] = new int[3];
		for (int i = 0; i < numCase; i++) {

			oneCase[0] = sc.nextInt();
			oneCase[1] = sc.nextInt();
			oneCase[2] = sc.nextInt();
			Arrays.sort(oneCase);

			cases[i] = new list(oneCase[0], oneCase[1], oneCase[2]);

		}
		Arrays.sort(cases);
		numCase = sc.nextInt();

		for (int j = 0; j < numCase; j++) {
			oneCase[0] = sc.nextInt();
			oneCase[1] = sc.nextInt();
			oneCase[2] = sc.nextInt();

			Arrays.sort(oneCase);
			boolean done = false;
			for (int i = 0; i < numCase && !done; i++) {
				if (oneCase[0] <= cases[i].h && oneCase[1] <= cases[i].w
						&& oneCase[2] <= cases[i].l) {
					System.out.println(cases[i].sum);
					done = true;

				}

			}
			if (!done)
				System.out.println("Item does not fit.");

		}
	}
}

class list implements Comparable {
	int sum, h, w, l;

	public list(int a, int b, int c) {
		h = a;
		w = b;
		l = c;
		sum = a * b * c;
	}

	@Override
	public int compareTo(Object o) {
		int n = sum - ((list) o).sum;
		if (n > 0)
			return 1;
		else if (n < 0)
			return -1;
		else
			return 0;
	}
}
