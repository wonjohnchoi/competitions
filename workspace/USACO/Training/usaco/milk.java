package usaco;

import java.io.*;
import java.util.*;

/*
ID: yojo1002
LANG: JAVA
TASK: milk
*/
public class milk {
	public static void main(String args[]) throws IOException
	{
		
		BufferedReader br= new BufferedReader(new FileReader("milk.in"));
		PrintWriter pw=new PrintWriter(new FileWriter("milk.out"));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int amount=Integer.parseInt(st.nextToken());
		int numChoice=Integer.parseInt(st.nextToken());
		
		int price[]=new int[numChoice];
		int eachAmount[]=new int[numChoice];
		int sortedPrice[]=new int[numChoice];
		
		for(int i=0;i<numChoice;i++)
		{
			st=new StringTokenizer(br.readLine());
			price[i]=Integer.parseInt(st.nextToken());
			eachAmount[i]=Integer.parseInt(st.nextToken());
			sortedPrice[i]=price[i];
		}
		Arrays.sort(sortedPrice);
		boolean used[]=new boolean[numChoice];
		Arrays.fill(used, false);
		int result=0;
		boolean done=false;
		for(int i=0;i<numChoice && !done;i++)
		{
			for(int j=0;j<numChoice && !done;j++)
			{
				if(!used[j] && sortedPrice[i]==price[j])
				{
				//	System.out.println(amount);
					used[j]=true;
					if(eachAmount[j]>=amount)
					{
						result+=amount * price[j];
						done=true;
					}
					else
					{
						result+=eachAmount[j]*price[j];
						amount-=eachAmount[j];
					}
					break;
				}
			}
		}
		
		pw.println(result);
		pw.close();
	}
	
	
	
}
