package Unit1;

import java.util.*;
import java.io.*;

public class LandScapePre {
	static char background[][];

	public static void main(String args[]) throws IOException {
		// Scanner to get Input
		Scanner sc = new Scanner(new FileReader("DATA1.txt"));

		// int type array to store the five heights
		int hillHeight[] = new int[5];

		// store the width of the background (sum of heights *2 - 1).
		int width = 0;

		for (int i = 0; i < 5; i++) {
			// get input
			hillHeight[i] = sc.nextInt();

			// calculate the width of background
			if (hillHeight[i] == 0) {
				width += 1;
			} else {
				width += hillHeight[i] * 2 - 1;
			}
		}

		// create background with 2D array
		background = new char[5][width];

		// fill each row with '.'
		for (int i = 0; i < 5; i++) {
			Arrays.fill(background[i], '.');
		}

		// ArrayList<fillHill> al=new ArrayList<fillHill>();

		// pos will indicate the location of origin of each mountain (origin is
		// the middle-bottom of the mountain
		int pos = -1;

		// for each input,
		for (int i = 0; i < 5; i++) {
			if (hillHeight[i] == 0) {
				pos++;
			} else {
				// move position
				pos += hillHeight[i];

				// use the method createHill: pos gives postion and
				// hillHeight[i] gives the height of each hill
				createHill(hillHeight[i], pos);

				// move position
				pos += hillHeight[i] - 1;
			}
		}

		// print the picture of background
		printArray(background);

	}

	/*
	 * subroutine to create each mountain
	 */
	public static void createHill(int hillHeight, int hillPos) {
		// create an item to use
		ArrayList<fillHill> item = new ArrayList<fillHill>();

		// add an item: this item will be the starting point of each mountain.
		// (hillPos, 4) is the origin of mountain
		item.add(new fillHill(hillPos, 4, hillHeight));

		// continue till item is empty
		while (!item.isEmpty()) {
			// get the first item.
			fillHill cur = item.remove(0);

			// replace the position of cur as 'x'
			background[cur.yPos][cur.xPos] = 'x';

			// if count is smaller or equal to 1, it blocks this item from going
			// forever.
			if (cur.count > 1) {
				// System.out.println(cur.count-1);
				item.add(new fillHill(cur.xPos + 1, cur.yPos, cur.count - 1));
				item.add(new fillHill(cur.xPos - 1, cur.yPos, cur.count - 1));
				item.add(new fillHill(cur.xPos, cur.yPos - 1, cur.count - 1));
			}

		}
	}

	/*
	 * print the 2D array
	 */
	public static void printArray(char array[][]) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter("OUT1.txt"));
		for (int i = 0; i < array.length; i++) {
			// print a row
			for (int j = 0; j < array[i].length; j++) {
				// print a char
				pw.print(array[i][j]);
			}
			// print a line
			pw.println();
		}
		pw.close();
	}
}

class fillHill {
	// xPos and yPos give current Position
	// count shows how many times char value will be changed
	int xPos, yPos, count;

	// contructor: gets value of x, y coordinates and the value of count (count
	// indicates how many time it goes).
	public fillHill(int x, int y, int c) {
		xPos = x;
		yPos = y;
		count = c;
	}
}
