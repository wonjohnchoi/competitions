package Senior2004;

/*
 * By Wonjohn Choi
 */
import java.util.*;
import java.io.*;

public class S1 {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s1.in"));
		int N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			boolean fixFree = true;
			String s1, s2, s3;
			s1 = sc.next();
			s2 = sc.next();
			s3 = sc.next();
			if (!fixFree(s1, s2) || !fixFree(s2, s3) || !fixFree(s1, s3)) {
				fixFree = false;
			}
			if (fixFree) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}

	}

	public static boolean fixFree(String s1, String s2) {
		int length1, length2;
		length1 = s1.length();
		length2 = s2.length();
		if (length1 > length2) {
			String temp = s2;
			s2 = s1;
			s1 = temp;

			length1 = s1.length();
			length2 = s2.length();
		}

		if (s1.equals(s2.substring(0, length1))
				|| s1.equals(s2.substring(length2 - length1))) {
			return false;
		}
		return true;

	}
}
