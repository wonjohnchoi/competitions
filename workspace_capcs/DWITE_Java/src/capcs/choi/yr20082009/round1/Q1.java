package capcs.choi.yr20082009.round1;

import java.util.*;
import java.io.*;
public class Q1 {
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("DATA1.txt"));
		PrintWriter pw=new PrintWriter(new FileWriter("OUT1.txt"));
		Scanner sc=new Scanner(new FileReader("DATA1.txt"));
		
		char[][] map=new char[200][200];
		for(int j=0;j<25;j++)
			for(int k=0;k<5;k++)
				map[j][k]='.';
		
		int pos=-1;
		
		for(int i=0;i<5;i++)
		{
			int num=sc.nextInt();
			pos+=num;
			if(num!=0)
			{
				
				map[pos][0]='x';
				if(num!=1)
				{
					map[pos-1][0]='x';
					map[pos+1][0]='x';
					map[pos][1]='x';
					if(num!=2)
					{
						map[pos-2][0]='x';
						map[pos+2][0]='x';
						map[pos][2]='x';
						map[pos-1][1]='x';
						map[pos+1][1]='x';		
					
						if(num!=3)
						{
							map[pos-3][0]='x';
							map[pos+3][0]='x';
							map[pos][3]='x';
							map[pos-2][1]='x';
							map[pos+2][1]='x';		
							map[pos-1][2]='x';
							map[pos+1][2]='x';
							if(num!=4)
							{
								map[pos-4][0]='x';
								map[pos+4][0]='x';
								map[pos][4]='x';
								map[pos-3][1]='x';
								map[pos+3][1]='x';		
								map[pos-2][2]='x';
								map[pos+2][2]='x';
								map[pos-1][3]='x';
								map[pos+1][3]='x';
								
							}
						}
					}
				}
				
			}
			
			else if(pos!=-1) 
				pos+=2;
			else
				pos+=1;
			
			pos+=num-1;
		}
		for(int j=4;j>=0;j--)
		{
			for(int i=0;i<=pos;i++)
				pw.print(map[i][j]);
			pw.println();
				
		}
		pw.close();
		System.exit(0);
		
	}

}