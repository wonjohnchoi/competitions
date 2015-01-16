package capcs.choi.yr20082009.round1;

import java.io.*;
public class Q4 {
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("DATA4.txt"));
		PrintWriter pw=new PrintWriter(new FileWriter("OUT4.txt"));

		int val[]={1,5,10,50,100,500,1000};
		char rom[]={'I','V','X','L','C','D','M'};
		for(int i=0;i<5;i++)
		{
			int sum=0;
			String s=br.readLine();
			for(int i1=0;i1<s.length();i1++)
			{
				for(int v=0;v<rom.length;v++)
					if(s.charAt(i1)==rom[v])
						sum+=val[v];
				if(i1<s.length()-1)
					for(int a=0;a<rom.length;a++)
						for(int b=0;b<a;b++)
							if(s.charAt(i1)==rom[b])
								if(s.charAt(i1+1)==rom[a])
									sum-=2*val[b];
			}
			pw.println(sum);
					
		}
		pw.close();
		System.exit(0);
	}
}