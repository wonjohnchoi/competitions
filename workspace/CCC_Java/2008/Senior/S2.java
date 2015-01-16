package Senior;

import java.io.*;
import java.util.*;

/*
 * By Wonjohn Choi
 */
public class S2 {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("DATA5.txt"));
		int x = sc.nextInt();
		while (x != 0) {
			System.out.println(count(x));
			x = sc.nextInt();
		}

	}

	public static int count(int i) {
		int ans = 0;
		ans = 4 * i + 1;
		for (int j = -i; j <= -1; j++) {
			// x^2+y^2=i^2;
			int topMax = (int) Math.sqrt(Math.abs(i * i - j * j));
			ans += topMax * 4;
		}
		return ans;
	}
}
