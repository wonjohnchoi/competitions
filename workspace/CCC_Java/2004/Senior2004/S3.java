package Senior2004;
/*
 * By Wonjohn Choi
 */
import java.util.*;
import java.io.*;

public class S3 {
	public static String result[];

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s3.in"));
		result = new String[90];

		for (int i = 0; i < 90; i++) {
			result[i] = sc.next();
		}

		for (int i = 0; i < 90; i++) {
			if (!isInteger(result[i])) {
				result[i] = "" + calcString(result[i], 0);
			}
		}

		for (int i = 0; i < 90; i++) {
			if (Integer.parseInt(result[i]) < 0) {
				System.out.print("*");
			} else {
				System.out.print(result[i]);
			}
			if (i % 9 != 8) {
				System.out.print(" ");
			}
			if (i % 9 == 8) {
				System.out.println();
			}
		}
	}

	public static boolean isInteger(String s) {
		int i = (int) (s.charAt(0) - '0');
		if (0 <= i && i < 10) {
			return true;
		}
		return false;
	}

	public static int toInt(String s) {
		int i = (int) (s.charAt(0) - 'A');
		int j = (int) (s.charAt(1) - '0');
		return i * 9 + j - 1;
	}

	public static int calcString(String s, int depth) {
		depth++;
		if (depth > 500) {
			return -1000000;
		}
		if (s.length() < 2) {
			return 0;
		}
		if (s.length() == 2) {
			int loc = toInt(s);

			if (!isInteger(result[loc])) {
				result[loc] = "" + calcString(result[loc], depth);
			}
			return Integer.parseInt(result[loc]);
		}
		int ans = 0;
		StringTokenizer st = new StringTokenizer(s, "+");

		while (st.hasMoreTokens()) {
			ans += calcString(st.nextToken(), depth);
			if (ans < 0)
				break;
		}
		return ans;
	}

}
