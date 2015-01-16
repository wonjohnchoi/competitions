package SeniorP;

import java.util.*;
import java.io.*;

/*
 * By Wonjohn Choi
 */
public class S3 {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s3.in"));
		int numRel = sc.nextInt();

		int rel[] = new int[10000];
		Arrays.fill(rel, 99999);
		for (int i = 0; i < numRel; i++) {
			rel[sc.nextInt()] = sc.nextInt();
		}

		int x, y;
		x = sc.nextInt();
		y = sc.nextInt();

		while (!(x == 0 && y == 0)) {
			ArrayList<Integer> al = new ArrayList<Integer>();

			int count = 0;
			boolean done = false;
			while (x != y && !done) {

				if (rel[x] == 99999 || al.contains(x)) {
					count = 0;
					done = true;
				}

				else {
					al.add(x);
					count++;
					x = rel[x];

				}

			}

			if (count == 0)
				System.out.println("No");
			else
				System.out.println("Yes " + (count - 1));
			x = sc.nextInt();
			y = sc.nextInt();
		}
	}
}
