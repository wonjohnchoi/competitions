package usaco;

/*
ID: yojo1002
LANG: JAVA
TASK: palsquare
*/
import java.io.*;
import java.util.*;

import javax.xml.transform.Templates;

class palsquare {
	public static String changeBase(int baseInteger, int pal)
	{
		long tempB=(long)pal;
		int lengthB=0;
		int baseNum=1;
		
		while(tempB>=1)
		{
			tempB/=baseInteger;
			lengthB++;
		}
		
		int digits[]=new int[lengthB];
		char digitsC[]=new char[lengthB];
		for(int i=0;i<lengthB;i++)
		{
			digits[i]=0;
		}
		
		for(int i=1;i<lengthB;i++)
		{
			baseNum*=baseInteger;
		}
		
		for(int i=0;i<lengthB;i++)
		{
			while(pal>=baseNum)
			{
				pal-=baseNum;
				digits[i]++;
			}
			char conversionC[]={'A','B','C','D','E','F','G','H','I','J','K'};
			int conversionI[]={10,11,12,13,14,15,16,17,18,19,20};
			for(int j=0;j<11;j++)
			{
				if(conversionI[j]==digits[i])
				{
					digitsC[i]=conversionC[j];
				}
			}
			baseNum/=baseInteger;
		}
		
		String numInBaseB="";
		for(int i=0;i<lengthB;i++)
		{
			if(digits[i]<10)
			{
				numInBaseB+=digits[i];
			}
			else
			{
				numInBaseB+=digitsC[i];
			}
		}
		return numInBaseB;
	}
	
	public static boolean checkPal(String num)
	{
		boolean isPal=true;
		for(int i=0;i<Math.ceil(num.length()/2);i++)
		{
			if(num.charAt(i)!=num.charAt(num.length()-i-1))
			{
				isPal=false;
			}
		}
		return isPal;
	}
	
	public static void baseChanger(int baseI, char baseC, boolean size, char conversion[])
	{
		for(int i=10;i<=20;i++)
		{
			if(baseI==i)
			{
				baseC=conversion[i-10];
				size=false;
			}
		}
		
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		int baseInteger=Integer.parseInt(f.readLine()); // 2<=B<=20 
		
		for(int i=1;i<=300;i++)
		{
			String temp=changeBase(baseInteger, i*i);
			
			if(checkPal(temp))
			{
				out.println(changeBase(baseInteger, i)+" "+temp);
			}
			
		}
		
		out.close();                                  // close the output file
		System.exit(0);                               // don't omit this!
	}
}