package Senior2005;

/*
 * By Wonjohn Choi
 */
import java.util.*; //Done but modification required
import java.io.*;

public class S4 {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s4.in"));
		Scanner sc2 = new Scanner(new FileReader("s4.in"));
		sc2.next();

		int N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			int dest = 0;
			int numRoute = sc.nextInt();
			ArrayList<String> al = new ArrayList<String>();

			for (int j = 0; j < numRoute; j++) {
				String s;
				s = sc.next();
				if (!al.contains(s)) {
					al.add(s);
				}
				if (j == numRoute - 1) {
					dest = al.indexOf(s);
				}
			}
			int numP = al.size();
			int list[][] = new int[numP][numP];
			for (int j = 0; j < list.length; j++)
				Arrays.fill(list[j], 9999);

			for (int j = 0; j < numP; j++) {
				list[j][j] = 0;
			}
			sc2.next();
			for (int j = 0; j < numRoute / 2; j++) {
				String s1, s2;
				s1 = sc2.next();
				s2 = sc2.next();
				int x, y;
				x = al.indexOf(s1);
				y = al.indexOf(s2);
				list[x][y] = 10;
				list[y][x] = 10;
			}

			for (int j = 0; j < numP; j++) {
				for (int k = 0; k < numP; k++) {
					for (int l = 0; l < numP; l++) {
						if (list[j][l] > list[j][k] + list[k][l]) {
							list[j][l] = list[j][k] + list[k][l];
							list[l][j] = list[j][l];
						}
					}
				}
			}

			int fast = 0;
			for (int j = 0; j < numP; j++) {
				if (fast < list[dest][j]) {
					fast = list[dest][j];
				}
			}
			System.out.println(numRoute * 10 - fast * 2);
		}
	}
}
