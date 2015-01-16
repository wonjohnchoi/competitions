package capcs.choi.yr20082009.round1;

import java.util.*;
import java.io.*;
public class Q2 {
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("DATA2.txt"));
		PrintWriter pw=new PrintWriter(new FileWriter("OUT2.txt"));
		Scanner sc=new Scanner(new FileReader("DATA2.txt"));
		//StringTokenizer st = new StringTokenizer(f.readLine());

		for(int i=0;i<5;i++)
		{
			int j=sc.nextInt();
			j=j%9;
			String s=sc.next();
			char alpha[]={'I','A','B','C','D','E','F','G','H'};	
			boolean b=false;
			for(int k=0;k<9;k++)
				if(j==k)
				{
					if((alpha[j]+"").equals(s))
					{
						pw.println("match");
						b=true;
					}
				}
			if(b==false)
				pw.println("error");
		}

		pw.close();
		System.exit(0);
		
	}
}