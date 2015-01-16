package March14;
/*
ID: yojo1002
LANG: JAVA
TASK: teststr
*/
import java.util.*;
import java.io.*;
import java.math.*;
public class teststr {
	public static void main(String args[]) throws IOException
	{
		String File = "teststr";
		Scanner in=new Scanner(new FileReader(File+".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(File+".out")));
		int N=in.nextInt();
		int K=in.nextInt();
		
		int k[]=new int[K];
		for(int i=0;i<K;i++)
		{
			k[i]=in.nextInt();
		}
		Arrays.sort(k);
		int max=0;
		for(int x=-N;x<0;x++)
		{
			int worst=Integer.MAX_VALUE;
							
			int temp=Math.abs(k[0]+x);
			if(worst>temp)
			{
				worst=temp;
			}
			temp=Math.abs(k[K-1]+x);
			if(worst>temp)
			{
				worst=temp;
			}
			
			if(max<worst)
			{
				max=worst;
			}
		}
		out.println(max);
		in.close();
		out.close();
		System.exit(0);
		
	}
}
