package Senior2004;

/*
 * By Wonjohn Choi
 */
import java.util.*;
import java.io.*;

public class S5 {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s5.in"));
		int r, c;
		r = sc.nextInt();
		c = sc.nextInt();
		while (r >= 2 && c >= 2) {
			char map[][] = new char[r][c];
			// int money[][] = new int[r][c];
			// for (int i = 0; i < r; i++) {
			// Arrays.fill(money[i], -1);
			// }
			for (int i = 0; i < r; i++) {
				map[i] = sc.next().toCharArray();
			}
			ArrayList<p> al = new ArrayList<p>();
			int curMoney = 0;
			if (!(map[r - 1][0] == '.' || map[r - 1][0] == '*')) {

				curMoney = (int) (map[r - 1][0] - '0');
			}
			al.add(new p(1, r - 1, 'e', curMoney));
			al.add(new p(0, r - 2, 'n', curMoney));
			int maxMoney = 0;
			ArrayList<u> used = new ArrayList<u>();

			while (!al.isEmpty()) {

				p item = al.remove(0);
				int curX = item.xPos;
				int curY = item.yPos;
				boolean done = false;
				boolean exist=false;
				for (int l = 0; l < used.size(); l++) {
					if (used.get(l).xPos == curX && used.get(l).yPos == curY) {
						used.get(l).freCount++;
						if(used.get(l).freCount>500)
						{
							done=true;
						}
						break;
					}
					
					
				}
				if(!exist)
				{
					used.add(new u(curX,curY,1));
				}
				used.add(new u(curX, curY, 0));
				if (!done) {
					if (curX == c - 1 && curY == r - 1) {
						if (map[curY][curX] != '.') {
							curMoney = (int) (map[curY][curX] - '0');
						} else {
							curMoney = 0;
						}
						if (item.curMoney > maxMoney) {
							maxMoney = item.curMoney;
						}
					} else if (curX < c && curY < r && 0 <= curY) {
						if (map[curY][curX] != '*') {

					//		System.out.println(curX + " " + curY + " "
					//				+ item.preMove + " " + item.curMoney);
							// int curMoney;
							if (map[curY][curX] != '.') {
								curMoney = (int) (map[curY][curX] - '0');
							} else {
								curMoney = 0;
							}

							// System.out.println(curMoney);
							if (item.preMove == 'n') {
								al.add(new p(curX, curY - 1, 'n', item.curMoney
										+ curMoney));
								al.add(new p(curX + 1, curY, 'e', item.curMoney
										+ curMoney));
							}

							else if (item.preMove == 's') {
								al.add(new p(curX, curY + 1, 's', item.curMoney
										+ curMoney));
								al.add(new p(curX + 1, curY, 'e', item.curMoney
										+ curMoney));

							} else if (item.preMove == 'e') {

								al.add(new p(curX, curY + 1, 's', item.curMoney
										+ curMoney));
								al.add(new p(curX, curY - 1, 'n', item.curMoney
										+ curMoney));
								al.add(new p(curX + 1, curY, 'e', item.curMoney
										+ curMoney));
							}
						}
					}
					/*
					 * else { if (item.preMove == 'n') { al.add(new p( curX,curY
					 * - 1, 'n')); al.add(new p(curX + 1,curY, 'e'));
					 * money[curY][curX] = money[curY + 1][curX]; }
					 * 
					 * else if (item.preMove == 's') { al.add(new p( curX, curY
					 * + 1,'s')); al.add(new p( curX + 1,curY, 'e')); }
					 * 
					 * else if (item.preMove == 'e') { al.add(new p( curX,curY +
					 * 1, 's')); al.add(new p(curX, curY - 1, 'n')); al.add(new
					 * p(curX + 1,curY, 'e')); }
					 * 
					 * }
					 */
					// }
				}
			}
			/*
			 * for (int i = 0; i < r; i++) { for (int j = 0; j < c; j++) {
			 * System.out.print(curMoney + " "); } System.out.println(); }
			 */

			System.out.println(maxMoney);
			r = sc.nextInt();
			c = sc.nextInt();
		}
	}

}

class u {
	int xPos, yPos, freCount;

	u(int x, int y, int count) {
		xPos = x;
		yPos = y;
		freCount = count;
	}
}

class p {
	int xPos, yPos, curMoney;
	char preMove;

	p(int x, int y, char move, int cM) {
		xPos = x;
		yPos = y;
		preMove = move;
		curMoney = cM;
	}
}