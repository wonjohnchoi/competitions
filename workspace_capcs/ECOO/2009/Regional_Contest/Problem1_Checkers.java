package Regional_Contest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Problem1_Checkers
{
	//public static char[][] board;
	public static int best=0;
	public static void main(String args[]) throws IOException
	{
		Scanner sc=new Scanner(new FileReader("Data11.txt"));
		char[][] board=new char[8][8];
		for(int i=0;i<8;i++)
		{
			board[i]=sc.next().toCharArray();

		}

		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(board[i][j]=='B')
				{
					recur(board, 0, i, j, 'W');

				}
				else if(board[i][j]=='W')
				{
					recur(board, 0, i, j, 'B');
				}
			}
		}


		System.out.println(best);
	}

	public static void recur(char newBoard[][], int count, int xPos, int yPos, char enemy)
	{
		best=Math.max(count, best);
		char board[][]=copy(newBoard);
		board[xPos][yPos]='*';

		if(inner(xPos, yPos))
		{
			print(newBoard);
			System.out.println();
			for(int i=-1;i<=1;i++)
			{
				int j=0;
				if(enemy=='B')
				{
					j=1;
				}
				else if(enemy=='W')
				{
					j=-1;
				}
				
				if(i!=0 && inner(xPos+i, yPos+j) && board[xPos+i][yPos+j]==enemy)
				{

					int tmpX, tmpY, tmpCount;
					tmpX=xPos;
					tmpY=yPos;
					tmpCount=count;
					tmpCount=count;
					char tmpBoard[][]=copy(board);

					tmpX+=i;
					tmpY+=j;

					while(inner(tmpX, tmpY) && tmpBoard[tmpX][tmpY]==enemy)
					{
						tmpBoard[tmpX][tmpY]='*';
						tmpX+=i;
						tmpY+=j;
						tmpCount++;
					}

					if(inner(tmpX, tmpY) && tmpBoard[tmpX][tmpY]=='*')
					{
						tmpBoard[tmpX][tmpY]=enemy=='W'? 'B': 'W';
						recur(tmpBoard, tmpCount, tmpX, tmpY, enemy);

					}
				}
			}
		}

	}




	public static boolean inner(int xPos, int yPos)
	{
		return 0<=xPos && xPos<8 && 0<=yPos && yPos<8;
	}

	public static char[][] copy(char from[][])
	{
		char[][] tmp=new char[8][8];
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				tmp[i][j]=from[i][j];
			}
		}
		return tmp;
	}

	public static void print(char[][] board)
	{
		for(int i=0;i<8;i++)
		{
			System.out.println(new String(board[i]));
		}
	}
}
