import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;


public class Slitherlink
{
	static int data[][];
	static boolean connected[][][][];
	static int h,w;
	static boolean done=false;
	public static void main(String args[]) throws IOException
	{
		String fileIn, fileOut;
		fileIn="Slitherlink_DATA1.txt";
		fileOut="Slitherlink_OUT1.txt";
		Scanner sc=new Scanner(new FileReader(fileIn));
		
		h=sc.nextInt();
		w=sc.nextInt();
		
		data=new int[h][w];
		
		for(int i=0;i<h;i++)
		{
			for(int j=0;j<w;j++)
			{
				data[i][j]=sc.nextInt();
			}
		}
		
		connected=new boolean[h+1][w+1][h+1][w+1];
		recur(0, 0);
		
		PrintWriter pw=new PrintWriter(new FileWriter(fileOut));
		
		for(int i=0;i<h;i++)
		{
			for(int j=0;j<w;j++)
			{
				for(int k=0;k<h;k++)
				{
					for(int l=0;l<w;l++)
					{
						if(connected[i][j][k][l])
						{
							pw.printf("%d %d %d %d\n", i, j, k, l);
						}
					}
				}
			}
		}
	}
	
	public static void recur(int yPos, int xPos)
	{	
		if(!done)
		{
			int n=nFriend(yPos, xPos);
			int diff=data[yPos][xPos]-n;
			
			if(data[yPos][xPos]==9 && n<=4)
			{
				checkNext(yPos, xPos);
			}
			else if(diff==0)
			{
				checkNext(yPos, xPos);
			}
			else if(diff==1)
			{
				onOne(yPos, xPos);
			}
			else if(diff==2)
			{
				onTwo(yPos, xPos);
			}
		}
	}
	
	private static void onTwo(int yPos, int xPos)
	{
		
	}

	private static void onOne(int yPos, int xPos)
	{
		if(!done)
		{
			if(!connected[yPos][xPos][yPos][xPos+1])
			{
				setConnected(yPos, xPos, yPos, xPos+1, true);
				checkNext(yPos, xPos);
				setConnected(yPos, xPos, yPos, xPos+1, false);
	
			}
			if(connected[yPos][xPos][yPos+1][xPos])
			{
				setConnected(yPos, xPos, yPos+1, xPos, true);
				checkNext(yPos, xPos);
				setConnected(yPos, xPos, yPos+1, xPos, false);
			}
			if(connected[yPos+1][xPos+1][yPos][xPos+1])
			{
				setConnected(yPos+1, xPos+1, yPos, xPos+1, true);
				checkNext(yPos, xPos);
				setConnected(yPos+1, xPos+1, yPos, xPos+1, false);
			}
			if(connected[yPos+1][xPos+1][yPos+1][xPos])
			{
				setConnected(yPos+1, xPos+1, yPos+1, xPos, true);
				checkNext(yPos, xPos);
				setConnected(yPos+1, xPos+1, yPos+1, xPos, false);
	
			}
		}
	}
	
	

	private static void setConnected(int yPos, int xPos, int yPos2, int xPos2,
			boolean b)
	{
		if(!done)
		{
			connected[yPos][xPos][yPos2][xPos2]=b;
			connected[yPos2][xPos2][yPos][xPos]=b;
		}
	}

	private static void checkNext(int yPos, int xPos)
	{
		xPos++;
		if(xPos>=w)
		{
			xPos=0;
			yPos++;
		}
		
		if(yPos<h)
		{
			recur(yPos, xPos);
		}
		else
		{
			done=true;
		}
		
	}

	public static int nFriend(int yPos, int xPos)
	{
		int count=0;
		if(connected[yPos][xPos][yPos][xPos+1])
		{
			count++;
		}
		if(connected[yPos][xPos][yPos+1][xPos])
		{
			count++;
		}
		if(connected[yPos+1][xPos+1][yPos][xPos+1])
		{
			count++;
		}
		if(connected[yPos+1][xPos+1][yPos+1][xPos])
		{
			count++;
		}
		return count;
	}
}
