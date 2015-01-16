package SeniorProblem;

import java.util.*;
import java.io.*;

public class S2 {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s2.in"));
		String plain = sc.nextLine();
		String notPlain = sc.nextLine();

		char[] ans = new char[27];
		Arrays.fill(ans, (char) ('.'));

		for (int i = 0; i < plain.length(); i++) {
			/*
			 * if(plain.charAt(i)==' ') ans[26]=plain.charAt(i); else
			 */
			if (notPlain.charAt(i) == ' ')
				ans[26] = plain.charAt(i);
			else
				ans[(int) notPlain.charAt(i) - 'A'] = plain.charAt(i);
		}
		String input = sc.nextLine();
		char[] result = new char[input.length()];

		for (int i = 0; i < input.length(); i++) {
			result[i] = ans[input.charAt(i) - 'A'];
		}

		System.out.println(new String(result));
	}
}
