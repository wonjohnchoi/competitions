package capcs.choi.yr20082009.round5;

import java.io.*;
import java.util.*;

public class Round5Problem2 {
	public static void main(String[] args)throws Exception
	{
		BufferedReader in=new BufferedReader(new FileReader("DATA2.txt"));
		PrintWriter out=new PrintWriter("OUT2.txt");
		Scanner choi=new Scanner(new FileReader("DATA2.txt"));
		
		for(int i=0;i<5;i++)
		{
			String kill;
			int killNum;
			kill=choi.next();
			killNum=choi.nextInt();
			String [] ss=new String[8];
			int [] ii=new int[8];
			for(int j=1;j<=5;j++)
			{
				ss[j]=choi.next();
				ii[j]=choi.nextInt();
				if(ii[j]>(-killNum))
					out.println(ss[j]);
			}
			
			
		}
		in.close();
		out.close();
	}

}

