package Broadwide_Contest;



import java.io.*;
import java.util.*;

public class Problem3_Chemical_Brew {
	public static char map[][];
	public static ArrayList<String> group;
	public static int width, height;
	public static void main(String args[]) throws IOException
	{
		Scanner sc=new Scanner(new FileReader("Data31.txt"));
		for(int i=0;i<5;i++)
		{
			width=sc.nextInt();
			height=sc.nextInt();
			String key=sc.next();
			char keys[]=key.toCharArray();
			Arrays.sort(keys);
			group=new ArrayList<String>();
			map=new char[height][width];
			for(int j=0;j<height;j++)
			{
				map[j]=sc.next().toCharArray();
			}
			
			getPos(0,0); 
			int count=0;
			for(String str: group)
			{
				char[] chemical=str.toCharArray();
				Arrays.sort(chemical);
				if(new String(keys).equals(new String(chemical)))
				{
					
					count++;
				}
			}
			System.out.printf("there are %d of %s \n",count, key );
			
		}
	}
	
	public static void getPos(int x, int y)
	{
		if(x>=width)
		{
			x-=width;
			y++;
		}
		if(y<height)
		{
			if(map[y][x]!='*')
			{
				group.add(getArea(x,y,""));
			}
			getPos(x+1,y);
		}
	}
	
	public static String getArea(int x,int y, String str)
	{
		
		if(0<=x && x<width && 0<=y && y<height)
		{
			if(map[y][x]!='*')
			{
				str+=map[y][x];
				map[y][x]='*';
				str=getArea(x+1, y, str);
				str=getArea(x-1,y,str);
				str=getArea(x, y+1, str);
				str=getArea(x,y-1, str);
			}
		}
		return str;
		
	}
}
