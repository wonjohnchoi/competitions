package Jan10;
/*
ID: yojo1002
LANG: JAVA
TASK: tseat

*/
import java.util.*;
import java.io.*;
public class tseat {
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("tseat.in"));
		PrintWriter pw=new PrintWriter(new FileWriter("tseat.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W=Integer.parseInt(st.nextToken());
		int R=Integer.parseInt(st.nextToken());
		int priority[][]=new int[W+1][R+1];
		priority[(W+1)/2][1]=1;
		double dis[][]=new double[W+1][R+1];
		
		for(int i=0;i<W+1;i++)
			for(int j=0;j<R+1;j++)
			{
				dis[i][j]=9999;
			}
		
		for(int i=1;i<W+1;i++)
			for(int j=1;j<R+1;j++)
			{
				dis[i][j]= Math.sqrt(Math.pow(((W+1)/2-i),2)+Math.pow((1-j),2));
			}
		
		dis[(W+1)/2][1]=9999;
		
		double cDist=1;
		boolean has=true;
		int label=2;
		while(label<=R*W)
		{
			if(has)
			{
				for(int j=1;j<R+1;j++)
					for(int i=1;i<W+1;i++)
					{
					
						if(dis[i][j]==cDist)
						{
							priority[i][j]=label;
							
								
						
							dis[i][j]=9999;
							label++;
							
						}
					}
				has=false;
			}
			
				
			else
			{
				double temp=Double.MAX_VALUE;
				for(int j=1;j<R+1;j++)
					for(int i=1;i<W+1;i++)
					{
						if(dis[i][j]<temp)
							temp=dis[i][j];
						
					}
				has=true;
				cDist=temp;
			}
		}
		for(int j=R;j>0;j--)
		{
			for(int i=1;i<W+1;i++)
			{
				pw.print(priority[i][j]);
				if(i!=W)
					pw.print(" ");
			}
			pw.println();
		}
		
		br.close();
		pw.close();
		System.exit(0);
		
	}
}