package SeniorP;

import java.util.*;
import java.io.*;

public class S1 {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s1.in"));

		int num = sc.nextInt();
		for (int i = 0; i < num; i++) {

			int year = sc.nextInt();
			int month = sc.nextInt();
			int day = sc.nextInt();

			if (2007 - year > 18)
				System.out.println("Yes");
			else if (2007 - year == 18)
				if (month < 2)
					System.out.println("Yes");
				else if (month == 2)
					if (day <= 27)
						System.out.println("Yes");
					else
						System.out.println("No");
				else
					System.out.println("No");
			else
				System.out.println("No");

		}
	}
}
