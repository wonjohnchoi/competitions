package capcs.choi.yr20022003.october;

import java.util.*;
import java.io.*;
public class Q5 {
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("DATA5.txt"));
		PrintWriter pw=new PrintWriter(new FileWriter("OUT5.txt"));
		Scanner sc=new Scanner(new FileReader("DATA5.txt"));
		//StringTokenizer st = new StringTokenizer(f.readLine());

		int num=Integer.parseInt(br.readLine());
		int m=Integer.parseInt(br.readLine());
		
		int current=0;
		Set<Integer> store=new HashSet<Integer>();
		while(store.size()!=15)
		{
			int count=0;
			while(count==m)
			{
				current++;
				current=current%m;
				if(!store.contains(current))
				{
					store.add(current);
					count++;
				}
			}
		}
		
		
		pw.close();
		System.exit(0);
		
	}
}