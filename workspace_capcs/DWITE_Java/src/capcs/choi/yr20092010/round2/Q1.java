package capcs.choi.yr20092010.round2;

import java.util.*;
import java.io.*;

/**
 * 
 * @author Wonjohn Choi
 * http://dwite.ca/questions/angles.html
 */
public class Q1 {
	public static void main(String args[]) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter("OUT1.txt"));
		Scanner sc = new Scanner(new FileReader("DATA1.txt"));

		for (int i = 0; i < 5; i++) {
			double x1, x2, y1, y2;
			x2 = sc.nextDouble();
			y2 = sc.nextDouble();
			x1 = sc.nextDouble();
			y1 = sc.nextDouble();
			double ans = 0;
			ans = Math.toDegrees(Math.atan2(y2, x2) - Math.atan2(y1, x1));
			while (ans < 0)
				ans += 360;
			pw.printf("%.1f", ans);
			pw.println();

		}

		pw.close();
		System.exit(0);

	}
}