package Broadwide_Contest;



import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Problem1_Power_Tiles
{
	public static void main(String args[]) throws IOException
	{
		Scanner sc=new Scanner(new FileReader("Data11.txt"));
		for(int i=0;i<5;i++)
		{
			int width, height;
			width=sc.nextInt();
			height=sc.nextInt();
			System.out.printf("%d tiles are needed for a %d by %d floor\n", count(width, height), width, height);
			
		}
	}
	public static int count(int width, int height)
	{
		int total=0;
		if(!(width==0 || height==0))
		{
			int minSize=Math.min(width, height);
			int num=1;
			while(num*2<=minSize)
			{
				num*=2;
			}
			total+=1;
			total+=count(width-num, height-num);
			total+=count(width-num, num);
			total+=count(num, height-num);
		}
		return total;
	}
}
