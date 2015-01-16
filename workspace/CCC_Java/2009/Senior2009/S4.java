package Senior2009;

/*
 * @author Wonjohn Choi
 */
import java.util.*;
import java.io.*;

public class S4 {
	public static short M = Short.MAX_VALUE;
	public static int numCity, numRoute, numShop;
	public static long result[];
	public static short cost[][], price[];

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s4.41.in"));

		numCity = sc.nextInt(); // <=5000
		numRoute = sc.nextInt(); // <=25000000
		cost = new short[numCity][numCity]; // 0<= <=10000
		for (int i = 0; i < numCity; i++) {
			Arrays.fill(cost[i], M);
		}
		for (int i = 0; i < numRoute; i++) {
			int x, y;
			short fee;
			x = Math.min(numCity - 1, sc.nextInt() - 1);
			y = Math.min(numCity - 1, sc.nextInt() - 1);
			fee = sc.nextShort();

			cost[x][y] = fee;
			cost[y][x] = fee;
		}

		numShop = sc.nextInt(); // 1<= <=numCity
		price = new short[numCity]; // 0<= <=10000
		Arrays.fill(price, M);
		for (int i = 0; i < numShop; i++) {
			price[sc.nextInt() - 1] = sc.nextShort();
		}

		int destination = sc.nextInt() - 1;
		dijskra(destination);
		long min = Long.MAX_VALUE;
		for (int i = 0; i < numCity; i++) {

			if (price[i] != M) {
				if (min > price[i] + result[i])
					min = price[i] + result[i];
			}
		}
		System.out.println(min);

	}

	public static void dijskra(int start) {
		boolean used[] = new boolean[numCity];
		result = new long[numCity];
		Arrays.fill(result, M);

		result[start] = 0;
		int cur = start;

		for (int j = 0; j < numCity; j++) {
			used[cur] = true;
			long min = Long.MAX_VALUE;

			for (int i = 0; i < numCity; i++) {
				if (!used[i]) {
					if (result[i] > cost[cur][i] + result[cur])
						result[i] = cost[cur][i] + result[cur];

				}
			}

			for (int i = 0; i < numCity; i++) {
				if (!used[i]) {
					if (min > result[i]) {
						min = result[i];
						cur = i;
					}
				}
			}
		}

	}

}
