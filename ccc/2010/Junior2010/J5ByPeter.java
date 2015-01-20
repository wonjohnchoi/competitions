package Junior2010;
import java.util.*;

public class J5ByPeter {
	//integer array to count how many steps it was needed to reach a position
	public static int[][] counter;
	
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		
		//the size of the map is 8 * 8, so assign the size of 8 for both dimensions.
		counter = new int[8][8];
		
		//fill counter array with the largest possible integer value.
		for (int i=0; i<8; i++)
		{
			Arrays.fill(counter[i], Integer.MAX_VALUE);
		}
		
		//declare variables to store starting point and ending point
		int startX, startY, endX, endY;
		
		//get input from user (subtract one so that it fits in the array)
		startX = sc.nextInt()-1;
		startY = sc.nextInt()-1;
		endX = sc.nextInt()-1;
		endY = sc.nextInt()-1;
		
		//use countDist method (recursion) to calculate the distances and store them into counter array
		// 0 indicates the initial step to be zero.
		countDist(startX, startY, 0);
		
		//output the distance at the end point
		System.out.println(counter[endX][endY]);
	}
	
	/*
	 * countDist method is a recursive method 
	 */
	public static void countDist(int x, int y, int steps)
	{
		//if x or y are less than 0 or bigger than 7, it will give out of bound for array, so 
		if (x>=0 && x<8 && y>=0 && y<8 && steps<counter[x][y])
		{
			counter[x][y] = steps;
			
			countDist(x-1, y+2, steps+1);
			countDist(x-1, y-2, steps+1);
			countDist(x+1, y+2, steps+1);
			countDist(x+1, y-2, steps+1);
			countDist(x-2, y+1, steps+1);
			countDist(x-2, y-1, steps+1);
			countDist(x+2, y+1, steps+1);
			countDist(x+2, y-1, steps+1);
		}
	}
}
