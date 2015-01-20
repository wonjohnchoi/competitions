package ¸¶¹æÁø;
import java.util.Vector;


public class RowsNColumns {
	static int map[][];
	static int len;
	public static void main(String args[])
	{
		len=8;
		
		map=new int[len][len];
		
		fill(0, 0);
	}

	public static void fill(int y, int x)
	{
		/*
		for(int i=0;i<len;i++)
		{
			for(int j=0;j<len;j++)
			{
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();*/
		
		if(x==len)
		{
			x=0;
			y+=1;
			if(y==len)
			{
				for(int i=0;i<len;i++)
				{
					for(int j=0;j<len;j++)
					{
						System.out.print(map[i][j]);
					}
					System.out.println();
				}
				System.exit(0);
			}
		}
		
		if(map[y][x]==0)
		{
			Vector<Integer> n=find(y, x);
			while(!n.isEmpty())
			{
				map[y][x]=n.remove(0);
				fill(y, x+1);
				map[y][x]=0;
			}
		}
		else
		{
			fill(y, x+1);
		}
	}

	private static Vector<Integer> find(int y, int x) {
		Vector<Integer> v=new Vector<Integer>();
		for(int i=1;i<=len;i++)
		{
			boolean correct=true;

			for(int j=0;j<x;j++)
			{
				if(map[y][j]==i)
				{
					correct=false;
					break;
				}
			}

			if(correct)
			{
				for(int j=0;j<y;j++)
				{
					if(map[j][x]==i)
					{
						correct=false;
						break;
					}
				}
				
				if(correct)
				{
					if(y==x)
					{
						for(int j=0;j<x;j++)
						{
							if(map[j][j]==i)
							{
								correct=false;
								break;
							}
						}
					}
					else if(x+y==len-1)
					{
						for(int j=0;j<y;j++)
						{
							if(map[j][len-j-1]==i)
							{
								correct=false;
								break;
							}
						}
					}
					
					if(correct)
					{
						v.add(i);
					}
				}
			}
			
			
		}
		return v;
	}
}
