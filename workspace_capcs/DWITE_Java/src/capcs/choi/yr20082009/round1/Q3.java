package capcs.choi.yr20082009.round1;

import java.util.*;
import java.io.*;
public class Q3 {
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("DATA3.txt"));
		PrintWriter pw=new PrintWriter(new FileWriter("OUT3.txt"));
		Scanner sc=new Scanner(new FileReader("DATA3.txt"));
		//StringTokenizer st = new StringTokenizer(f.readLine());
	
		char map[][]=new char[19][10];
		int dist[][][][]=new int[19][10][19][10];
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++)
				for(int k=0;k<19;k++)
					for(int l=0;l<19;l++)
						dist[k][i][l][j]=10000;
		for(int i=0;i<10;i++)
		{
			String temp=br.readLine();
			for(int j=0;j<19;j++)
			{
				map[j][i]=temp.charAt(j);

			}
		}
		
				
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<19;j++)
			{
				System.out.print(map[j][i]);
			}
			System.out.println();
		}
		
		
		
		for(int i=0;i<5;i++)
		{
	
			String s=sc.next();
			if(s.length()==1)
			{
				pw.println(0);
			}
			else
			{
				
			}
			
		}

		pw.close();
		System.exit(0);
		
	}
}