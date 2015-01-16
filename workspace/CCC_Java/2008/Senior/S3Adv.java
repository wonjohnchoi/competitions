package Senior;

import java.io.*;
import java.util.*;

/*
 * By Wonjohn Choi
 */
public class S3Adv {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("DATA5.txt"));
		int numCase = sc.nextInt();
		int row, col, grid[][];
		char maze[][];

		String tempLine;
		for (int i = 0; i < numCase; i++) {
			row = sc.nextInt();
			col = sc.nextInt();
			maze = new char[row][col];
			grid = new int[row][col];
			for (int j = 0; j < grid.length; j++)
				Arrays.fill(grid[j], -1);
			// advMap=new boolean[row+2][col+2];

			for (int k = 0; k < row; k++) {
				tempLine = sc.next();
				maze[k] = tempLine.toCharArray();
			}

			ArrayList<Item> q = new ArrayList<Item>();

			// BFS search of the maze

			grid[0][0] = 1;
			q.add(new Item(0, 0));
			while (!q.isEmpty()) {
				Item x = q.remove(0);
				int r = x.x;
				int c = x.y;
				// go up if you can
				if ((maze[r][c] == '+' || maze[r][c] == '|') && r > 0
						&& maze[r - 1][c] != '*' && grid[r - 1][c] == -1) {
					grid[r - 1][c] = grid[r][c] + 1;
					q.add(new Item(r - 1, c));
				}
				// go down if you can
				if ((maze[r][c] == '+' || maze[r][c] == '|') && r < row - 1
						&& maze[r + 1][c] != '*' && grid[r + 1][c] == -1) {
					grid[r + 1][c] = grid[r][c] + 1;
					q.add(new Item(r + 1, c));
				}
				// go left if you can
				if ((maze[r][c] == '+' || maze[r][c] == '-') && c > 0
						&& maze[r][c - 1] != '*' && grid[r][c - 1] == -1) {
					grid[r][c - 1] = grid[r][c] + 1;
					q.add(new Item(r, c - 1));
				}
				// go right if you can
				if ((maze[r][c] == '+' || maze[r][c] == '-') && c < col - 1
						&& maze[r][c + 1] != '*' && grid[r][c + 1] == -1) {
					grid[r][c + 1] = grid[r][c] + 1;
					q.add(new Item(r, c + 1));
				}
			}
			System.out.println(grid[row - 1][col - 1]);
		}
		System.exit(0);
	}

}

class Item {
	int x, y;

	public Item(int xx, int yy) {
		x = xx;
		y = yy;
	}

}
