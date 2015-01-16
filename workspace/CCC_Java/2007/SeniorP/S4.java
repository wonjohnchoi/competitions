package SeniorP;

import java.util.*;
import java.io.*;

/*
 * By Wonjohn Choi
 */
public class S4 {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s4.in"));

		int max = sc.nextInt();

		ArrayList<Item> street = new ArrayList<Item>();
		ArrayList<Item> moving = new ArrayList<Item>();
		int record[] = new int[max];
		record[0] = 1;

		int x, y;
		y = sc.nextInt() - 1;
		x = sc.nextInt() - 1;
		while (x != -1 && y != -1) {
			street.add(new Item(x, y));
			y = sc.nextInt() - 1;
			x = sc.nextInt() - 1;
		}
		for (int k = 1; k < max; k++) {
			for (int j = 0; j < street.size(); j++) {
				if (street.get(j).x == k)
					moving.add(street.get(j));
			}
			while (!moving.isEmpty()) {
				Item i = moving.remove(0);
				record[k] += record[i.y];
			}

		}
		System.out.println(record[max - 1]);

	}

}

class Item {
	int x, y;

	public Item(int from, int to) {
		x = from;
		y = to;

	}

}