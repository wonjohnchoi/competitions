package notOrganized;
import java.util.*;
import java.io.*;
public class LCS {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("LCS.in.txt"));
		PrintWriter pw=new PrintWriter(new FileWriter("LCS.out.txt"));
		
		LinkedList<Character> l1=new LinkedList<Character>();
		LinkedList<Character> l2=new LinkedList<Character>();
		LinkedList<Character> result=new LinkedList<Character>();
		for(int i=0;i<2;i++)
		{
			String temp=br.readLine();
			for(int j=0;j<temp.length();j++)
				if(i==0)
					l1.add(temp.charAt(j));
				else
					l2.add(temp.charAt(j));
		}
		int max=0;
		int count=0;

		while(count!=l1.size())
		{
			int temp = 0;
			for(int i=0;i<l1.size();i++)
				if(l1.get(i)==l2.get(i))
					temp++;
			
			if(temp>max)
				max=temp;
			count++;
			loop(l2);
		}
		for(int i=0;i<l1.size();i++)
		{
			int temp=0;
			for(int j=0;j<l1.size();j++)
				if(l1.get(j)==l2.get(j))
					temp++;
			if(temp==max)
			{
				for(int j=0;j<l1.size();j++)
					if(l1.get(j)==l2.get(j))
					{
						result.add(l1.get(j));
						
					}
				break;
			}
			loop(l2);
		}
		pw.println(max);
		for(int i=0;i<result.size();i++)
			pw.print(result.get(i));
		pw.println();
		
		pw.close();
		System.exit(0);
	
		
	}
	
	public static void loop(LinkedList<Character> l)
	{
		char c=l.remove();
		l.add(c);
	}
	
	static int LCS[][]=new int[1024][1024];
	
	
}
