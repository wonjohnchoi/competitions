package Senior2005;
/*
 * By Wonjohn Choi
 */
import java.util.*;
import java.io.*;

public class S1 {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s1.in"));
		int n = sc.nextInt();

		char chart[] = new char[10];
		for (int i = 0; i < 10; i++) {
			chart[i] = (char) (i + '0');
		}
		for (int i = 0; i < n; i++) {

			char input[] = new char[10];

			String s = sc.next();
			int j = 0;
			for (char c : s.toCharArray()) {
				if (c != '-' && j < 10) {
					input[j] = c;
					if (Arrays.binarySearch(chart, input[j]) < 0) {
						if ((int) (input[j] - 'A') <= 17)
							input[j] = (char) ((((int) (input[j] - 'A')) / 3 + 2) + '0');
						else if ((int) (input[j] - 'A') == 18)
							input[j] = '7';
						else if ((int) (input[j] - 'A') <= 21)
							input[j] = '8';
						else
							input[j] = '9';
					}

					j++;
				}
			}
			String str = "";

			for (int i1 = 0; i1 < input.length; i1++) {
				if (i1 == 3 || i1 == 6)
					str += "-";
				str += input[i1];
			}
			System.out.println(str);

		}
	}

}
