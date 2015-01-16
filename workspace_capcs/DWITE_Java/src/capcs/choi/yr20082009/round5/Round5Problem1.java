package capcs.choi.yr20082009.round5;


import java.io.*;

public class Round5Problem1 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader in=new BufferedReader(new FileReader ("DATA1.txt"));
		PrintWriter out=new PrintWriter("OUT1.txt");
		
		for(int i=0;i<5;i++)
		{
			String s1,s2;
			int l1,l2;
			s1=in.readLine();
			s2=in.readLine();
			l1=s1.length();
			l2=s2.length();
			int count=0;
			for(int j=0;j<=Math.min(l1,l2)-1;j++)
			{
				if(s1.charAt(j)==s2.charAt(j))
				{
					count++;
				}
				else
				{
					j=Math.max(l1,l2);
				}
			}
			out.println(count);
			
		}

		out.close();
	}

}
