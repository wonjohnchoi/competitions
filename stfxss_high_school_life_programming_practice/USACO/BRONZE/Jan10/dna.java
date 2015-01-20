package Jan10;

/*
ID: yojo1002
LANG: JAVA
TASK: dna
*/

import java.util.*;
import java.io.*;
public class dna {
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("dna.in"));
		PrintWriter pw=new PrintWriter(new FileWriter("dna.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int bull=Integer.parseInt(st.nextToken());
		int cow=Integer.parseInt(st.nextToken());
		String bDNA[]=new String[bull];
		String cDNA[]=new String[cow];
		int ans[][]=new int[bull][cow];
		for(int i=0;i<bull;i++)
		{
			bDNA[i]=br.readLine();
		}
		for(int j=0;j<cow;j++)
		{
			cDNA[j]=br.readLine();
		}
		
		
		//choose one bull and one cow
		for(int i=0;i<bull;i++)
			for(int j=0;j<cow;j++)
			{
				int count=0;
				
				//compare to other bulls
				for(int b=0;b<bull;b++)
				{
					//it's not its parents
					if(b!=i)
					{
						boolean yes=true;
						for(int l=0;l<25;l++)
						{
							if(!(bDNA[b].charAt(l)==bDNA[i].charAt(l) || bDNA[b].charAt(l)==cDNA[j].charAt(l)))
								yes=false;
						}
						if(yes)
						{
							count++;
						}
					}
				}
				
				//compare to other cows
				for(int c=0;c<cow;c++)
				{
					//it's not its parents
					if(c!=j)
					{
						boolean yes=true;
						for(int l=0;l<25;l++)
						{
							
							if(!(cDNA[c].charAt(l)==bDNA[i].charAt(l) || cDNA[c].charAt(l)==cDNA[j].charAt(l)))
								yes=false;
						}
						if(yes)
						{
							count++;
						}
					}
				}
				ans[i][j]=count;
			}
		
		for(int i=0;i<bull;i++)
		{
			for(int j=0;j<cow;j++)
			{
				pw.print(ans[i][j]);
				if(j!=cow-1)
					pw.print(" ");
			}
			pw.println();
		}
		br.close();
		pw.close();
		System.exit(0);
		
	}
}