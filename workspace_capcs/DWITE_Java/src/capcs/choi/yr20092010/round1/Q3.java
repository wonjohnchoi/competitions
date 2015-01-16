package capcs.choi.yr20092010.round1;

import java.util.*;
import java.io.*;

/**
 * 
 * @author Wonjohn Choi
 * 
 */
public class Q3 {
	public static void main(String args[]) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter("OUT3.txt"));
		Scanner sc = new Scanner(new FileReader("DATA3.txt"));

		for (int i = 0; i < 5; i++) {
			int a = sc.nextInt();
			ArrayList<Integer> al = new ArrayList<Integer>();
			for (int b = 0; b < a; b++) {
				al.add(sc.nextInt());
			}
			for (int b = 1; b <= a + 1; b++)
				if (al.indexOf(b) == -1)
					pw.println(b);

		}

		pw.close();
		System.exit(0);

	}
}