package capcs.choi.yr20072008.round4;

import java.util.*;
import java.io.*;
public class Q5 {
	public static int result[];
	public static int ans;
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("DATA5.txt"));
		PrintWriter pw=new PrintWriter(new FileWriter("OUT5.txt"));

		String input[]=new String[25];
		result=new int[25];
		Arrays.fill(result,999999);
		for(int i=0;i<25;i++)
		{
			input[i]=br.readLine();
			if(input[i].charAt(0)!='=')
			{
				result[i]=Integer.parseInt(input[i]);
				input[i]="NULL";
			}
		}
		boolean done=false;
		while(!done)
		{
			done=true;
			for(int i=0;i<25 && done;i++)
			{
				if(input[i]!="NULL")
				{
					done=false;
				}
			}
			for(int i=0;i<25 && !done;i++)
			{
				if(input[i]!="NULL")
				{
					toAns(input[i].substring(1), 0, '+');
					
					if(ans!=999999)
					{
						result[i]=ans;
						input[i]="NULL";
					}
				}
			}
			
			
		}
		for(int i=0;i<25;i++)
		{
			pw.println(result[i]);
		}

		pw.close();
		System.exit(0);
		
	}
	
	public static void toAns(String input, int var, char operator )
	{
		int a=0, special = 0;
		
		if(input.charAt(0)=='A'||input.charAt(0)=='B'||input.charAt(0)=='C'||input.charAt(0)=='D'||input.charAt(0)=='E')
		{
			special=2;
			a=toInt(input.charAt(0), (int)(input.charAt(1)-'0'));
		}
		else
		{
			boolean done=false;
			for(int i=0;i<input.length()&&!done;i++)
			{
				if(input.charAt(i)==' ')
				{
					special=i;
					a=Integer.parseInt(input.substring(0,i));
					done=true;
				}
				
			}
			if(!done)
			{
				special=input.length();
				a=Integer.parseInt(input);
			}
			
		}
		if(operator=='+')
		{
			var+=a;
		}
		if(operator=='*')
		{
			var*=a;
		}
		
		if(operator=='-')
		{
			var-=a;
		}
		
		if(a==999999)
			ans=999999;
		
		else if(input.length()==special)
			ans=var;
		else
			toAns(input.substring(3+special), var, input.charAt(1+special));
	}
	
	public static int toInt(char c, int i)
	{
		char alpha[]={'A','B','C','D','E'};
		int ans=0;
		for(int j=0;j<5;j++)
		{
			if(alpha[j]==c)
			{
				ans=result[5*j+i-1];
			}
		}
		return ans;	
	
	}
}