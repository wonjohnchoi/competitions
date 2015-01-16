package Broadwide_Contest;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Problem2_Anagrams
{
	public static void main(String args[]) throws IOException
	{
		Scanner sc=new Scanner(new FileReader("Data21.txt"));

		for(int i=0;i<5;i++)
		{
			String line=sc.nextLine();
			String words[]=line.split("=");
			String fir=words[0].trim().toLowerCase();
			String sec=words[1].trim().toLowerCase();
			if(!isSame(fir,sec))
			{
				System.out.printf("%s is not an anagram\n", line);
			}
			else
			{
				System.out.printf("%s is an anagram and its value is %d \n", line, sum(fir, sec));
			}
			
		}
	}
	
	public static int sum(String fir, String sec)
	{
		char[][] c=new char[2][];
		c[0]=fir.toCharArray();
		c[1]=sec.toCharArray();
		int sum=0;
		for(int i=0;i<c[0].length;i++)
		{
			if('a'<=c[0][i] && c[0][i]<='z')
			{
				for(int j=0;j<c[1].length;j++)
				{
					if('a'<=c[1][j] && c[1][j]<='z')
					{
						if(c[0][i]==c[1][j])
						{
							sum+=(i+1)*(j+1);
						}
					}
				}
			}
		}
		return sum;
	}
	
	public static boolean isSame(String fir, String sec)
	{
		boolean isSame=false;
		char[][] c=new char[2][];
		c[0]=fir.toCharArray();
		c[1]=sec.toCharArray();
		
		for(int j=0;j<2;j++)
		{
			String s="";
			for(int i=0;i<c[j].length;i++)
			{
				if('a'<=c[j][i] && c[j][i]<='z')
				{
					s+=c[j][i];
				}
			}
			c[j]=s.toCharArray();
			Arrays.sort(c[j]);
		}
		if(new String(c[0]).equals(new String(c[1])))
		{
			isSame=true;
		}
		return isSame;
	}
}
