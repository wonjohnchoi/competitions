package capcs.choi.yr20072008.round1;

import java.io.*;

public class Q5 {
	public static int vX;
	public static int vY;
	public static int hX;
	public static int hY;
	public static int eX;
	public static int eY;
	public static char map[][];
	public static int row, col;
	
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("DATA5.txt"));
		PrintWriter pw=new PrintWriter(new FileWriter("OUT5.txt"));
		
		row=Integer.parseInt(br.readLine());
		col=Integer.parseInt(br.readLine());
		
		map=new char[col][row];
		
		for(int i=0;i<row;i++)
		{
			String line=br.readLine();
			for(int j=0;j<col;j++)
			{
				map[j][i]=line.charAt(j);
				if(map[j][i]=='V')
				{
					vX=j;
					vY=i;
					map[j][i]='.';
				}
				if(map[j][i]=='H')
				{
					hX=j;
					hY=i;
					map[j][i]='.';
				}
				if(map[j][i]=='E')
				{
					eX=j;
					eY=i;
					map[j][i]='.';
				}					
			}	
		}
		double hT, vT;
		hT=(double)Math.min(distance(hX,hY,0, 'e'), Math.min(distance(hX,hY,0, 'w'),Math.min(distance(hX,hY,0, 'n'),distance(hX,hY,0, 's'))));
		vT=(double)Math.min(distance(vX,vY,0, 'e'), Math.min(distance(vX,vY,0, 'w'),Math.min(distance(vX,vY,0, 'n'),distance(vX,vY,0, 's'))))/2;

		if(hT>=vT)
			pw.println(Math.round(hT));
		else
			pw.println("escape");
		
		pw.close();
	}
	public static int distance(int sX, int sY, int distance, int direction)
	{
		int ans=Integer.MAX_VALUE;
		if(sX==eX && sY==eY)
		{
			ans=distance;
		}
		else if(sX>=col ||sX<0 || sY>=row || sY<0)
		{
			ans=Integer.MAX_VALUE;
		}
		else if(map[sX][sY]=='#')
		{
			ans=Integer.MAX_VALUE;
		}
		else if(distance>30)
		{
			ans=distance;
		}
		else
		{
			if(direction!='n')
			{
				ans=Math.min(ans,distance(sX, sY+1, distance+1, 's'));
			}
			if(direction!='s')
			{
				ans=Math.min(ans,distance(sX, sY-1, distance+1, 'n'));
			}
			if(direction!='e')
			{
				ans=Math.min(ans,distance(sX-1, sY, distance+1, 'w'));
			}
			if(direction!='w')
			{
				ans=Math.min(ans,distance(sX+1, sY, distance+1, 'e'));
			}
			
		}
		return ans;
			
	}
}
