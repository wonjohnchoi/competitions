package notOrganized;

import java.io.*;
import java.util.*;

public class DFS {
	
	static int dist[][];
	static char map[][];
	static int row, col;
	static double start=System.currentTimeMillis();
	
	public static void main(String[] args) throws IOException {

		Scanner sc=new Scanner(new FileReader("DATA5.txt"));
		int numCase=sc.nextInt();


		String tempLine;
		for(int i=0;i<numCase;i++)
		{
			row=sc.nextInt();
			col=sc.nextInt();
			map=new char[row][col];
			dist=new int[row][col];
			for(int j=0;j<dist.length;j++)
				Arrays.fill(dist[j], Integer.MAX_VALUE);
			//advMap=new boolean[row+2][col+2];
	
			for(int k=0;k<row;k++)
			{
				tempLine=sc.next();
				map[k]=tempLine.toCharArray();
			}
			count(0,0,1);
			if(dist[row-1][col-1]==Integer.MAX_VALUE)
				System.out.println(-1);
			else
				System.out.println(dist[row-1][col-1]);
		}
		System.out.println(System.currentTimeMillis()-start);
		System.exit(0);
		
		
		
	}
	
	public static void count(int x, int y, int count)
	{
		//System.out.println(x+" "+y);
		if(x>=0 && x<col && y>=0 && y<row)
		{
			
			if(count<dist[y][x] && map[y][x]!='*')
			{
				dist[y][x]=count;
				if(map[y][x]=='+')
				{
					count(x+1, y, count+1);
					count(x, y+1, count+1);
					count(x, y-1, count+1);
					count(x-1, y, count+1);
				}
				else if(map[y][x]=='-')
				{
					count(x+1, y, count+1);
					count(x-1, y, count+1);
				}
				else if(map[y][x]=='|')
				{
					count(x, y+1, count+1);
					count(x, y-1, count+1);
				}
			}
					
		}
	}

}

