package Senior2005;
/*
 * By Wonjohn Choi
 */
import java.util.*;
import java.io.*;

public class S2 {
	static int col;
	static int row;

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s2.in"));
		col = sc.nextInt();
		row = sc.nextInt();
		String s1, s2;
		int x, y = 0;
		s1 = sc.next();
		s2 = sc.next();

		x = toInteger(s1);
		y = toInteger(s2);

		int curX = 0, curY = 0;
		while (!(x == 0 && y == 0)) {

			curX = limit(curX, x, true);
			curY = limit(curY, y, false);
			System.out.println(curX + " " + curY);

			s1 = sc.next();
			s2 = sc.next();

			x = toInteger(s1);
			y = toInteger(s2);
		}
	}

	public static int limit(int current, int move, boolean xMovement) {
		if (move > 0) {
			if (xMovement)
				return Math.min(current + move, col);
			if (!xMovement)
				return Math.min(current + move, row);
		}
		if (move < 0)
			return Math.max(current + move, 0);
		return current;
	}

	public static int toInteger(String s) {
		if (s.charAt(0) == '-')
			return -Integer.parseInt(s.substring(1));
		return Integer.parseInt(s);
	}
}
