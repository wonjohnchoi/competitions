package Feb06;
/*
ID: yojo1002
LANG: JAVA
TASK: scavhunt
*/
import java.util.*;
import java.io.*;
public class scavhunt {
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("scavhunt.in"));
		PrintWriter pw=new PrintWriter(new FileWriter("scavhunt.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int a=Integer.parseInt(st.nextToken());
		int b=Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> al=new ArrayList<Integer>();
		ArrayList<Integer> count=new ArrayList<Integer>();
		int div=2;
	
		while(a!=1)
		{
			int countT=0;
			if(a%div==0)
			{
				al.add(div);
			}
			while(a%div==0)
			{
				countT++;
				a=a/div;
			}
			if(countT>0)
				count.add(countT);
			div++;
		}
		
		ArrayList<Integer> al2=new ArrayList<Integer>();
		ArrayList<Integer> count2=new ArrayList<Integer>();
		
		div=2;
		
		while(b!=1)
		{
			int countT=0;
			if(b%div==0)
			{
				al2.add(div);
			}
			while(b%div==0)
			{
				countT++;
				b=b/div;
			}
			if(countT>0)
				count2.add(countT);
			div++;
		}
		
		ArrayList<Integer> result=new ArrayList<Integer>();
		ArrayList<Integer> result2=new ArrayList<Integer>();
		result.add(1);
		result2.add(1);
		int temp;
		for(int i=0;i<al.size();i++)
		{
			temp=result.size();
			for(int j=0;j<=count.get(i);j++)
			{
				for(int k=0;k<temp;k++)
				{
					int temp2=(int) Math.pow(al.get(i), j) * result.get(k);
					if(!result.contains(temp2))
						result.add(temp2);
				}
			}
			
		}
		for(int i=0;i<al2.size();i++)
		{
			temp=result2.size();
			for(int j=0;j<=count2.get(i);j++)
			{
				for(int k=0;k<temp;k++)
				{
					int temp2=(int) Math.pow(al2.get(i), j) * result2.get(k);
					if(!result2.contains(temp2))
						result2.add(temp2);
				}
			}
		}
		Collections.sort(result);
		Collections.sort(result2);
		for(int i=0;i<result.size();i++)
		{
			for(int j=0;j<result2.size();j++)
			{
				pw.println(result.get(i)+" "+result2.get(j));
			}
		}
		
		br.close();
		pw.close();

		pw.close();
		System.exit(0);
		
	}
}