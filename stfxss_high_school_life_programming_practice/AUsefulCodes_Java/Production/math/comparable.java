package math;

import java.util.Arrays;

public class comparable {
	public static void main(String args[])
	{
		Item[] i=new Item[5];
		i[0]=new Item("1","2");
		i[1]=new Item("2","3");
		i[2]=new Item("1", "3");
		i[3]=new Item("2", "2");
		i[4]=new Item("1","1");
		
		Arrays.sort(i);
		
		for(int j=0;j<5;j++)
		{
			System.out.println(i[j].s1+" "+i[j].s2);
			// 1 1
			// 1 2
			// 1 3
			// 2 2
			// 2 3
		}
	}
}

class Item implements Comparable<Item> {
	public String s1;
	public String s2;

	public Item(String str1, String str2) {
		s1 = str1;
		s2 = str2;
	}

	public int compareTo(Item o) {
		int n1 = s1.compareTo(o.s1);
		int n2 = s2.compareTo(o.s2);
		if (n1 > 0)
			return 1;
		if (n1 < 0)
			return -1;
		if (n2 > 0)
			return 1;
		if (n2 < 0)
			return -1;
		return 0;
	}
}
