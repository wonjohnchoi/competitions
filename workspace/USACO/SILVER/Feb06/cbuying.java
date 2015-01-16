package Feb06;
/*
ID: yojo1002
LANG: JAVA
TASK: cbuying
*/
import java.util.*;
import java.io.*;
public class cbuying {
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("cbuying.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbuying.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int numLine=Integer.parseInt(st.nextToken());
		long B=Long.parseLong(st.nextToken());


		Item[] list = new Item[numLine];
		
		for(int i=0;i<numLine;i++)
		{
			st=new StringTokenizer(br.readLine());
			list[i]=new Item(Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()));
		}
		Arrays.sort(list);
		long ans=0L;
		long maxCow=1;
		
		for(int i = 0; maxCow>0 && i<numLine; i++) {
			maxCow = Math.min(list[i].numCows, B/list[i].price);
			ans += maxCow;
			B -= maxCow*list[i].price;
		}
		pw.println(ans);
		

		pw.close();
		System.exit(0);
		
	}
}
class Item implements Comparable {
	public long price;
	public long numCows;
	
	public Item(long p, long c) {
		price = p;
		numCows = c;
	}

	@Override
	public int compareTo(Object o) {
		long n = price-((Item)o).price;
		if(n>0)
			return 1;
		else if(n<0)
			return -1;
		else
			return 0;
	}
}