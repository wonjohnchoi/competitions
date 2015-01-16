package Fillomino;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Fillomino
{
	static int h, w;
	static int [][] map;
	
	public static void main(String args[]) throws IOException
	{
		Scanner sc=new Scanner(new FileReader("Fillomino_DATA1.txt"));
		
		h=sc.nextInt();
		w=sc.nextInt();
		
		map=new int[h][w];
		
		for(int i=0;i<h;i++)
		{
			for(int j=0;j<w;j++)
			{
				map[i][j]=sc.nextInt();
			}
		}
		
		fillSpace(0,0);
		
	}
	
	private static void fillSpace(int yPos, int xPos)
	{
		/*
		for(int i=0;i<h;i++)
		{
			for(int j=0;j<w;j++)
			{
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		*/
		
		if(xPos==w)
		{
			xPos=0;
			yPos++;
		}
		
		if(yPos==h && satisfied())
		{
			for(int i=0;i<h;i++)
			{
				for(int j=0;j<w;j++)
				{
					System.out.print(map[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
			System.exit(0);

		}
		
		
		
		if(inRange(yPos, xPos))
		{
			if(map[yPos][xPos]!=0)
			{
				fillSpace(yPos, xPos+1);
			}
			else
			{
				int nSpace=getArea(yPos, xPos, 0);
				int tmp=map[yPos][xPos];
				
				for(int i=1;i<=nSpace+1;i++)
				{
					map[yPos][xPos]=i;
					if(getArea(yPos, xPos, i)<=i)
					{
						fillSpace(yPos, xPos+1);
					}
					map[yPos][xPos]=tmp;
				}
				
				for(int i=-1;i<=1 ;i++)
				{
					for(int j=-1;j<=1 ;j++)
					{
						if((i+j)%2!=0 && inRange(i+yPos, j+xPos))
						{
							map[yPos][xPos]=map[yPos+i][xPos+j];
							if(getArea(yPos, xPos, map[yPos+i][xPos+j])<=map[yPos+i][xPos+j])
							{
								fillSpace(yPos, xPos+1);
							}
							map[yPos][xPos]=tmp;
						}
					}
				}
			}
		}
	}
	
	private static boolean satisfied()
	{
		boolean satisfied=true;
		for(int i=0;i<h && satisfied;i++)
		{
			for(int j=0;j<w && satisfied;j++)
			{
				if(getArea(i,j,map[i][j])!=map[i][j])
				{
					satisfied=false;
				}
			}
		}
		return satisfied;
	}

	private static int getArea(int yPos, int xPos, int val)
	{
		int count=0;
		
		if(inRange(yPos, xPos) && map[yPos][xPos]==val)
		{
			count=1;
			
			map[yPos][xPos]=-1;
			
			for(int i=-1;i<=1;i++)
			{
				for(int j=-1;j<=1;j++)
				{
					if((i+j)%2!=0)
					{
						count+=getArea(yPos+i, xPos+j, val);
					}
				}
			}
			
			map[yPos][xPos]=val;
		}
		return count;
	}

	private static boolean inRange(int yPos, int xPos)
	{
		return 0<=yPos && 0<=xPos && yPos<h && xPos<w;
	}
}
