//WRong needs to be fixed
package Junior2009; 
import java.util.*;
import java.io.*;
public class p5 {
	
	static int rel[][]=new int[50][50];

	public static void initiate()
	{
		for(int i=1;i<50;i++)
			for(int j=1;j<50;j++)
				rel[i][j]=0;
		rel[2][6]=1;
		rel[6][2]=1;
		rel[1][6]=1;
		rel[6][1]=1;
		rel[3][6]=1;
		rel[6][3]=1;
		rel[7][6]=1;
		rel[6][7]=1;
		rel[4][6]=1;
		rel[6][4]=1;
		rel[5][6]=1;
		rel[6][5]=1;
		rel[4][5]=1;
		rel[5][4]=1;
		rel[3][4]=1;
		rel[4][3]=1;
		rel[3][5]=1;
		rel[5][3]=1;
		rel[6][7]=1;
		rel[7][6]=1;
		rel[7][8]=1;
		rel[8][7]=1;
		rel[8][9]=1;
		rel[9][8]=1;
		rel[9][10]=1;
		rel[10][9]=1;
		rel[9][12]=1;
		rel[12][9]=1;
		rel[9][10]=1;
		rel[10][9]=1;
		rel[10][11]=1;
		rel[11][10]=1;
		rel[11][12]=1;
		rel[12][11]=1;
		rel[12][13]=1;
		rel[13][12]=1;
		rel[3][15]=1;
		rel[15][3]=1;
		rel[15][13]=1;
		rel[13][15]=1;
		rel[13][14]=1;
		rel[14][13]=1;
		rel[16][18]=1;
		rel[18][16]=1;
		rel[16][17]=1;
		rel[17][16]=1;
		rel[17][18]=1;
		rel[18][17]=1;
	}
	
	public static void i(int x, int y)
	{
		rel[x][y]=1;
		rel[y][x]=1;
	}
	
	public static void d(int x, int y)
	{
		rel[x][y]=0;
		rel[y][x]=0;
	}
	
	public static void n(int x)
	{
		int temp=0;
		for(int i=1;i<50;i++)
		{
			if(rel[x][i]==1&&i!=x)
			{
				temp++;
			}
		}
		System.out.println(temp);
	}
	
	public static void f(int x)
	{
		int temp=0;
		for(int i=1;i<50;i++)
		{
			if(rel[x][i]==1&&i!=x)
			{
				for(int j=1;j<50;j++)
				{
					if(rel[i][j]==1&&j!=i&&j!=x&&rel[x][j]==0)
					{
						temp++;
					}
				}
			}
		}
		System.out.println(temp);
	}
	
	public static void s(int x, int y)
	{
		int fake[][]=new int[50][50];
		for(int i=1;i<50;i++)
			for(int j=1;j<50;j++)
			{
				fake[i][j]=rel[i][j];
				if(fake[i][j]==0)
					fake[i][j]=999;
			}
		
		for(int i=1;i<50;i++)
			for(int j=1;j<50;j++)
				if(fake[i][j]<999)
					for(int k=1;k<50;k++)
						if(fake[j][k]<999)
							fake[i][k]=Math.min(fake[i][j]+fake[j][k], fake[i][k]);
		if(fake[x][y]==999)
		{
			System.out.println("Not connected");	
		}
		else
		{
			System.out.println(fake[x][y]);
		}
	}
	
	public static void main(String[] args) {
		initiate();
	
		Scanner sc=new Scanner(System.in);
		char cr='p';
		int i1;
		int i2;
		while(cr!='q')
		{
			cr=sc.next().charAt(0);
			if(cr=='i')
			{
				i1=sc.nextInt();
				i2=sc.nextInt();
				i(i1,i2);
			}
			if(cr=='d')
			{
				i1=sc.nextInt();
				i2=sc.nextInt();
				d(i1,i2);
			}
			if(cr=='n')
			{
				i1=sc.nextInt();
				n(i1);
			}
			if(cr=='f')
			{
				i1=sc.nextInt();
				f(i1);
			}
			if(cr=='s')
			{
				i1=sc.nextInt();
				i2=sc.nextInt();
				s(i1,i2);
			}

		}
	
	}

}
