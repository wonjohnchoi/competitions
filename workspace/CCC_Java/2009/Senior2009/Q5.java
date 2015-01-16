package Senior2009;
//description of an object
import java.util.*;
import java.io.*;

public class Q5 {
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("DATA5.txt"));
		PrintWriter pw=new PrintWriter(new FileWriter("OUT5.txt"));
		
		int row, col, numInternet;
		
		row=Integer.parseInt(br.readLine());
		col=Integer.parseInt(br.readLine());
		numInternet=Integer.parseInt(br.readLine());
	
		short internetPow[][]=new short[col+1][row+1];
				
		int x,y,R,B,top,bot;
		StringTokenizer st;
		
		for(int i=0;i<numInternet;i++)
		{
			st=new StringTokenizer(br.readLine());
			x=Short.parseShort(st.nextToken())-1;
			y=row-Short.parseShort(st.nextToken());
			R=Short.parseShort(st.nextToken());
			B=Short.parseShort(st.nextToken());
			
			for(int k=Math.max(0,x-R);k<=Math.min(col-1,x+R);k++)
			{
				int dev=(int) Math.sqrt (R * R - (x - k) * (x - k));
				top = Math.max (0, y-dev);
				bot= Math.min (row - 1, y + dev);
				internetPow [k] [top] += B;
				internetPow [k] [bot+1] -= B;

			}
		}		
							
		int best = 0;
		int count = 0;
		for (int i = 0 ; i < row ; i++)
		{
		    for (int j = 0 ; j < col ; j++)
		    {
				//calculate the actual bitrate at (j,i) by adding
				if (i > 0)
				    internetPow [j] [i] += internetPow [j] [i-1]; 
				if (internetPow [j] [i] == best)
				    count++;
				if (internetPow [j] [i] > best)
				{
				    best = internetPow [j] [i];
				    count = 1;
				}
		    }
		}
		pw.println (best + "\n" + count);

		
		
		pw.close();
		br.close();
		System.exit(0);
		
	}
}
