package capcs.choi.yr2010;

import java.io.*;
import java.util.*;
import java.math.*;

public class S4 {
	protected static int len=1001;
	protected static int route[][]=new int[len][len];
	protected static int tot=0;
	protected static int max=0;
	protected static boolean used[]=new boolean[len];
	protected static int dist[]=new int[len];
	
	public static void main(String args[]) throws Exception
	{
		Arrays.fill(used, true);
		Scanner sc=new Scanner(new FileReader("s4.txt"));
		int numPan=sc.nextInt();
		
		
		for(int i=0;i<numPan;i++)
		{
			
			int numEdge=sc.nextInt();
			
			int nodes[]=new int[numEdge];
	
			for(int j=0;j<numEdge;j++)
			{
				nodes[j]=sc.nextInt();
			}
			
			for(int j=0;j<numEdge-1;j++)
			{
				int value=sc.nextInt();
				route[nodes[j]][nodes[(j+1)]]=value;
				route[nodes[j+1]][nodes[j]]=value;
			}
			int value=sc.nextInt();
			route[nodes[numEdge-1]][nodes[0]]=value;
			route[nodes[0]][nodes[numEdge-1]]=value;
			
			
		}
		
		for(int i=0;i<len;i++)
		{
			for(int j=0;j<=i;j++)
			{
				if(route[i][j]!=0)
				{
					tot+=route[i][j];
					used[i]=false;
					used[j]=false;
				}
			}
		}
		
		findMax();
		
		System.out.println(tot-max);
		
		
		System.exit(0);
	}
	
	
	public static void findMax()
	{
		//detect an initial node (any node exists)
		int curNode=-1;
		
		for(int i=0;i<len;i++)
		{
			if(!used[i])
			{
				curNode=i;
			}
		}
		
				
		while(curNode!=-1) //if curNode is -1, it's not detected so stop
		{		
			//System.out.println(curNode);
			//set current node as used
			used[curNode]=true;
			
			
			//update the best length (cost) to a node
			for(int i=0;i<len;i++)
			{
				if(!used[i])
				{
					if(dist[i]< route[curNode][i])
					{	
						dist[i]=route[curNode][i];
					}
					
				}
			}
			
			int maxLen=0;
			curNode=-1;
			
			//get the maximum length (cost)
			for(int i=0;i<len;i++)
			{
				if(!used[i])
				{
					if(maxLen<dist[i])
					{
						maxLen=dist[i];
						curNode=i;
					}
				}
			}
			
			//add the total length (cost)
			max+=maxLen;
		}
	}
}
