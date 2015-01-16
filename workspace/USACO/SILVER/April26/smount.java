package April26;

/*
ID: yojo1002
LANG: JAVA
TASK: smount
*/

/*
 * Code By Wonjohn Choi
 */
import java.util.*;
import java.io.*;
public class smount {
	public static void main(String args[]) throws IOException
	{
		double time=System.currentTimeMillis();
		String File = "smount";
		Scanner sc=new Scanner(new FileReader(File+".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(File+".out")));
		int N=sc.nextInt();
		long cur=0;
		int countCur=0, countTot=0, maxCount=0;
		boolean up=true;
		
		for(int i=0;i<N;i++)
		{
			long tmp=sc.nextLong();
			if(up) //trend: moving up
			{
				if(tmp>cur) //increase
				{
					cur=tmp;
					countCur=1;
					countTot++;
					
				}
				else if(tmp<cur) //decrease
				{
					up=false;
					cur=tmp;
					countCur=1;
					countTot++;
				}
				else //same
				{
					countCur++;
					countTot++;
				}
			}
			
			else //trend: moving down
			{
				if(tmp>cur) //increase
				{
					//new mountain
					maxCount=Math.max(maxCount, countTot);
					countTot=countCur+1;
					cur=tmp;
					countCur=1;
					up=true;
					
				}
				else if(tmp<cur) //decrease
				{
					countTot++;
					cur=tmp;
					countCur=1;
				}
				else //same
				{
					countTot++;
					countCur++;
				}
			}
		}
		
		out.println(Math.max(countTot,maxCount));
		
		sc.close();
		out.close();
		System.exit(0);	
	}
	
	
}
