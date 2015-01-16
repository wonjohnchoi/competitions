package Junior;

import java.util.*;

/*
 * By Wonjohn Choi
 */
public class J2 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		String word = sc.next();
		int length;
		while (!word.equals("quit")) {
			length = word.length();
			if (word.length() >= 4) {
				String lastTwo = word.substring(length - 2, length);
				char lastThird = word.charAt(length - 3);
				if (lastTwo.equals("or")
						&& !(lastThird == 'a' || lastThird == 'e'
								|| lastThird == 'o' || lastThird == 'u' || lastThird == 'i')) {
					word = word.substring(0, length - 2) + "our";
				}
			}
			System.out.println(word);
			word = sc.next();
		}
	}

}
