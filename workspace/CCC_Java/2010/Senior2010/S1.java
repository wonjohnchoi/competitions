package Senior2010;
/*
 * By Wonjon Choi
 */
import java.io.*;
import java.util.*;

public class S1 {

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(new FileReader("s1.in"));
		int numInput = sc.nextInt();
		if (numInput == 0)
			System.exit(0);
		if (numInput == 1) {
			System.out.print(sc.next());
			System.exit(0);
		}
		Item i[] = new Item[numInput];
		for (int j = 0; j < numInput; j++) {
			i[j] = new Item(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt());

		}
		Arrays.sort(i);
	
		print(i[numInput - 1].N);
		System.out.print(i[numInput - 2].N);

		System.exit(0);
	}

	public static void print(Object s) {

		System.out.println("" + s);
	}
}

class Item implements Comparable<Item> {
	int R, S, D, sum;
	String N;

	Item(String n, int r, int s, int d) {
		R = r;
		S = s;
		D = d;
		sum = 2 * R + 3 * S + D;
		N = n;
	}

	public int compareTo(Item o) {
		int n1 = sum - o.sum;
		int n2 = N.compareTo(o.N);
		if (n1 > 0)
			return 1;
		if (n1 < 0)
			return -1;
		if (n2 < 0)
			return 1;
		if (n2 > 0)
			return -1;
		return 0;

	}

}