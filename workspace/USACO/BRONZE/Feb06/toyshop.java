package Feb06;

/*
ID: yojo1002
LANG: JAVA
TASK: toyshop
*/
import java.util.*;
import java.io.*;
public class toyshop {
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("toyshop.in"));
		PrintWriter pw=new PrintWriter(new FileWriter("toyshop.out"));
		int numToy=Integer.parseInt(br.readLine());
		int price[]=new int[numToy];
		int joy[]=new int[numToy];
		double HFM[]=new double[numToy];
		double Ori[]=new double[numToy];
		for(int i=0;i<numToy;i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			joy[i]=Integer.parseInt(st.nextToken());
			price[i]=Integer.parseInt(st.nextToken());
			HFM[i]=(double)joy[i]/(double)price[i];
			Ori[i]=HFM[i];
		}
		
		Arrays.sort(HFM);
		int ans=0;
		int result[]=new int[3];
		
		for(int i=numToy-3;i<numToy;i++)
		{
			for(int j=0;j<numToy;j++)
			{
				if(HFM[i]==Ori[j])
				{
					ans+=price[j];
					result[numToy-i-1]=j+1;
				}
			}
		}
		pw.println(ans);
		for(int i=0;i<3;i++)
			pw.println(result[i]);
		
		pw.close();
		System.exit(0);
		
	}
}