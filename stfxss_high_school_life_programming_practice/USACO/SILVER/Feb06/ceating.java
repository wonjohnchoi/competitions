package Feb06;
/*
ID: yojo1002
LANG: JAVA
TASK: ceating
*/
import java.util.*;
import java.io.*;
public class ceating {
	
	
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("ceating.in"));
		PrintWriter pw=new PrintWriter(new FileWriter("ceating.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, D;
		N=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		int H[]=new int[N];
		int preResult[]=new int[D];
		int preMin=Integer.MAX_VALUE;
		int currentResult[][]=new int[D][63355];
		for(int i=0;i<N;i++)
		{
			H[i]=Integer.parseInt(br.readLine());
		}
		
		
		int tempN=N;
		while(tempN!=0)
		{
			for(int i=0;i<tempN;i++)
			{
				for(int j=0;j<i;j++)
				{
					//currentResult[i][count]+=H[j][count];
				}
				tempN-=i;
			}
		}
		
		int min=Integer.MAX_VALUE;
		for(int i=0;i<N;i++)
		{
	//		if(min>currentResult[i])
	//			min=currentResult[i];
		}
		
		if(preMin>min)
			preMin=min;
		else
		{
		//	for(int i=0;i<N;i++)
				//preResult[i]=currentResult[i];
		}
		
		
		pw.close();
		System.exit(0);
		
	}
	
	public static void recursion(int N, int D, int H[], int min)
	{
		
	}
}