package capcs.choi.yr20072008.round2;
/*
 * code by Wonjohn Choi
 */
import java.util.*;
import java.io.*;
public class Q5 {
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("DATA5.txt"));
		PrintWriter pw=new PrintWriter(new FileWriter("OUT5.txt"));
		
		for(int l=0;l<5;l++)
		{
			int numPoint=Integer.parseInt(br.readLine());
			int numEdge=Integer.parseInt(br.readLine());
			int line[][]=new int[numPoint][numPoint];
			
			for(int i=0;i<numPoint;i++)
				Arrays.fill(line[i],99999);
			
			for(int i=0;i<numEdge;i++)
			{
				StringTokenizer st=new StringTokenizer(br.readLine());
				int x,y;
				x=Integer.parseInt(st.nextToken());
				y=Integer.parseInt(st.nextToken());
				line[x-1][y-1]=1;
				line[y-1][x-1]=1;
			}
			
			int ans=0;
			
			for(int i=0;i<numPoint;i++)
				for(int j=0;j<numPoint;j++)
					if(line[i][j]!=99999 && i<j && bridge(line, i, j))
					{
						ans++;
					}
			pw.println(ans);
		}
 
		pw.close();
		System.exit(0);
	 
 
	}
 
	public static boolean bridge(int line[][], int x, int y)
	{
		int temp[][]=new int[line.length][line.length];
		
		for(int i=0;i<line.length;i++)
			for(int j=0;j<line.length;j++)
				temp[i][j]=line[i][j];
		
		boolean bridge=true;
		
		temp[x][y]=99999;
		temp[y][x]=99999;
 
		if(connected(temp))
		{
			bridge=false;
		}
 
		return bridge;
	}
 
	public static boolean connected(int line[][])
	{
		for(int i=0;i<line.length;i++)
		{
			for(int j=0;j<line.length;j++)
			{
				for(int k=0;k<line.length;k++)
				{
					line[i][k]=Math.min(line[i][j]+line[j][k],line[i][k]);
					line[k][i]=line[i][k];			
				}
			}
		}
		
 
		boolean connected=true;
 
		for(int i=0;i<line.length && connected;i++)
		{
			for(int j=0;j<i;j++)
			{
				if(line[i][j]==99999)
				{
					connected=false;
					break;
				}
			}
		}
		return connected;
	}
} 