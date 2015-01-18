package Senior2010;
/*
 * By Wonjohn Choi
 */
import java.io.*;
import java.util.*;

public class S2 {
	
	public static void main(String args[]) throws Exception
	{
		Scanner sc=new Scanner(new FileReader("s2.in"));
		int numC=sc.nextInt();
		Item1 sss[]=new Item1[numC];
		for(int i=0;i<numC;i++)
		{
			sss[i]=new Item1(sc.next().charAt(0), sc.next());
		}
		String ans="";
		String key=sc.next();
		while(key.length()!=0)
		{
			
			for(int i=0;i<numC;i++)
			{
				String cur=key.substring(0,  sss[i].length);
				if(sss[i].binary.equals(cur))
				{
					key=key.substring(sss[i].length);
					ans+=sss[i].character;
					break;
				}			
			}
		}
		System.out.print(ans);
		System.exit(0);
	}
	
	
}
class Item1
{
	int length;
	char character;
	String binary;
	Item1(char c, String s)
	{
		character=c;
		binary=s;
		length=binary.length();
	}
}