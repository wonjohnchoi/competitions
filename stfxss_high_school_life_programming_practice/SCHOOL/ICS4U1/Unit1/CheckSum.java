package Unit1;
import java.util.*;
import java.io.*;
public class CheckSum 
{
	public static void main(String args[]) throws Exception
	{
		//Scanner, PrintWriter, FileReader, FileWriter for processing I/O
		Scanner sc=new Scanner(new FileReader("DATA2.txt"));
		PrintWriter pw=new PrintWriter(new FileWriter("OUT2.txt"));

		//do 5 times
		for(int i=0;i<5;i++)
		{
			//get input to inNum (for integer), inChar(for alphabet)
			int inNum=sc.nextInt();
			char inChar=sc.next().charAt(0);
			
			
			//I am using the property that getting the sum of each digits till the number becomes one digit-number 
			//is same to getting the remainder.
			//subtract one from inNum and get the remainder
			inNum=(inNum-1)%9;
			
			//get the matched character value from theNum
			char tempChar=(char)(inNum+'A');
			
			//String variable ans to store answer.
			String ans="";
			
			//compare the tempChar to in Char
			if(tempChar==inChar)
			{
				ans="match";
			}
			else
			{
				ans="error";
			}
			
			//output answer.
			pw.println(ans);
		}
		
		pw.close();
	}
}
