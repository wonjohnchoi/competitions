package Senior2005;

/*
 * By Wonjohn Choi
 */
import java.util.*;
import java.io.*;

public class S4Adv {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s4.in"));
		int t = sc.nextInt();

		for (int i = 0; i < t; i++) {
			int r = sc.nextInt();
			ArrayList<PMS> route = new ArrayList<PMS>();

			for (int j = 0; j < r / 2; j++) {
				String s1, s2;
				s1 = sc.next();
				s2 = sc.next();
				route.add(new PMS(s1, s2));
				route.add(new PMS(s2, s1));
			}

			String dest = route.get(r - 1).s1;
			ArrayList<PMS> moving = new ArrayList<PMS>();
			for (int j = 0; j < r; j++) {
				PMS item = route.get(j);

				if (item.s1.equals(dest)) {
					moving.add(item);
					item.dist = 1;

				}
			}
			ArrayList<String> used = new ArrayList<String>();
			int ans = 0;
			while (!moving.isEmpty()) {
				PMS tempI = moving.remove(0);
				if (used.contains(tempI.s2))
					continue;
				if (tempI.dist > ans)
					ans = tempI.dist;

				String nextS = tempI.s2;
				for (int j = 0; j < r; j++) {
					PMS temp = route.get(j);

					if (temp.s1.equals(nextS)) {
						moving.add(temp);
						temp.dist = tempI.dist + 1;
					}
				}
				used.add(tempI.s1);
			}
			System.out.println(r * 10 - ans * 2 * 10);

		}

	}
}

class PMS {
	String s1, s2;
	int dist;

	PMS(String str1, String str2) {
		s1 = str1;
		s2 = str2;
	}

}