package Feb06;
/*
ID: yojo1002
LANG: JAVA
TASK: feedtime
*/
import java.util.*;
import java.io.*;
public class feedtime {
	static char map[][];
	static int W;
	static int H;
	static int ans;
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("feedtime.in"));
		PrintWriter pw=new PrintWriter(new FileWriter("feedtime.out"));
		StringTokenizer st=new StringTokenizer(br.readLine());
		W=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		map=new char[W][H];
		
		for(int i=0;i<H;i++)
		{
			String line=br.readLine();
			for(int j=0;j<W;j++)
			{
				map[j][i]=line.charAt(j);
			}
		}
		
		int result=0;
		for(int i=0;i<H;i++)
			for(int j=0;j<W;j++)
				if(map[j][i]=='.')
				{
					ans=0;
					countFood(j,i);
					if(ans>result)
						result=ans;
				}
		pw.println(result);

		pw.close();
		System.exit(0);
		
	}
	
	
	public static void countFood(int posX, int posY)
	{
		if(map[posX][posY]=='.' )
		{
			ans++;
			map[posX][posY]='*';
		
			for(int i=-1;i<=1 ;i++)
			{
				for(int j=-1;j<=1;j++)
				{
					if(posX+i>=0 && posX+i<W && posY+j>=0 && posY+j<H)
						if(map[posX+i][posY+j]=='.')
						{
							countFood(posX+i, posY+j);
							
						}
				}
			}
		}
	}
}