package notOrganized;

import java.util.*;
import java.io.*;
public class BFS {
	public static void main(String args[]) throws IOException
	{
		PrintWriter pw=new PrintWriter(new FileWriter("OUT4.txt"));
		Scanner sc=new Scanner(new FileReader("DATA4.txt"));
		
		char map[][];
		int dist[][];
		boolean found;
		
		ArrayList<Item> q;
		
		for(int i=0;i<5;i++)
		{
			
			map=new char[10][10];
			dist=new int[10][10];
			q=new ArrayList<Item>();
			found=false;
			
			for(int j=0;j<10;j++)
				Arrays.fill(dist[j], -1);
			for(int j=0;j<10;j++)
				map[j]=sc.next().toCharArray();
			
			for(int j=0;j<10;j++)
			{
				for(int k=0;k<10;k++)
				{
					if(map[j][k]=='X')
						if(!found)
						{
							q.add(new Item(j,k));
							dist[j][k]=0;
							map[j][k]='#';
							found=true;
						}
				}
			}
			
		
			
			while(!q.isEmpty())
			{
				Item it=q.remove(0);
				int x=it.x;
				int y=it.y;
				//System.out.println(x+" "+y+" "+i);
				if(map[x][y]=='X') {
					pw.println(dist[x][y]);
					break;
				}
				
				if(x>0 && dist[x-1][y]==-1 && map[x-1][y]!='#')
				{
					q.add(new Item(x-1,y));
					dist[x-1][y]=dist[x][y]+1;
				}
				
				if(x<9 && dist[x+1][y]==-1 && map[x+1][y]!='#')
				{
					q.add(new Item(x+1,y));
					dist[x+1][y]=dist[x][y]+1;
				}
				if(y>0 && dist[x][y-1]==-1 && map[x][y-1]!='#')
				{
					q.add(new Item(x,y-1));
					dist[x][y-1]=dist[x][y]+1;
				}
				
				if(y<9 && dist[x][y+1]==-1 && map[x][y+1]!='#')
				{
					q.add(new Item(x,y+1));
					dist[x][y+1]=dist[x][y]+1;
				}
				if(x>0 && y>0 && dist[x-1][y-1]==-1 && map[x-1][y-1]!='#')
				{
					q.add(new Item(x-1,y-1));
					dist[x-1][y-1]=dist[x][y]+1;
				}
				
				if(x<9&& y<9&& dist[x+1][y+1]==-1 && map[x+1][y+1]!='#')
				{
					q.add(new Item(x+1,y+1));
					dist[x+1][y+1]=dist[x][y]+1;
				}
				if(y>0 && x<9 && dist[x+1][y-1]==-1 && map[x+1][y-1]!='#')
				{
					q.add(new Item(x+1,y-1));
					dist[x+1][y-1]=dist[x][y]+1;
				}
				
				if(y<9 && x>0&& dist[x-1][y+1]==-1 && map[x-1][y+1]!='#')
				{
					q.add(new Item(x-1,y+1));
					dist[x-1][y+1]=dist[x][y]+1;
				}
			}
			
			sc.next();
		}

		pw.close();
		System.exit(0);
		
	}
}
class Item
{
	int x,y;
	Item(int xx, int yy)
	{
		x=xx;
		y=yy;
	}
	
	
}
