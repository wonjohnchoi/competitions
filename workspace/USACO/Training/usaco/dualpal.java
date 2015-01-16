package usaco;
/*
ID: yojo1002
LANG: JAVA
TASK: dualpal
*/
import java.io.*;
import java.util.*;

public class dualpal {
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter pw=new PrintWriter(new FileWriter("dualpal.out"));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int S=Integer.parseInt(st.nextToken());
		int count;
		
		while(N!=0)
		{
			count=0;
			S++;
			if(checkPalindrome(Integer.toBinaryString(S)))
				count++;
			if(checkPalindrome(Integer.toOctalString(S)))
				count++;
			
			for(int i=3;i<11;i++)
			{
				if(i!=8)
					if(checkPalindrome(baseChanger(10, i, S)))
						count++;
			}
			if(count>=2)
			{
				pw.println(S);
				N--;
			}
		}
		
		pw.close();
		System.exit(0);
		
	}
	
	public static boolean checkPalindrome(String input)
	{
		boolean output=true;
		int length=input.length();
		for(int i=0;i<length/2;i++)
			if(input.charAt(i)!=input.charAt(length-i-1))
				output=false;
		
		return output;
		
	}
	
	public static String baseChanger(int from, int to, int input)
	{
		String inputS=input+"";
		int length=inputS.length();
		int inTenBase=0;
		String output = "";
		
		for(int i=length-1;i>=0;i--)
		{
			inTenBase+=(int)(inputS.charAt(i)-'0')*Math.pow(from, length-i-1);
		}
		           
		int remainder;
		while(inTenBase!=0)
		{
			remainder=inTenBase%to;
			output=remainder+output;
			inTenBase=(inTenBase-remainder)/to;
		}
		return output;
	}

}
