package Senior2005;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class S5 {
	static ArrayList<Integer> score = new ArrayList<Integer>();
	static Scanner sc;
	static Map<Integer, Integer> m ;
	public static void main(String args[]) throws IOException {
		
		sc = new Scanner(new FileReader("DATA5.txt"));
		int num=sc.nextInt();

		int rankSum = 0;
		m  =new TreeMap<Integer, Integer>();
		for(int i=0;i<num;i++) {
			rankSum+=sumRank(sc.nextInt());
		//	rankSum += search(sc.nextInt());
		}
		
		System.out.printf("%.2f \n",(double)rankSum/(double)num);

	}
	public static int sumRank(int newScore)
	{
	//	int i=m.get(newScore);
		if(m.get(newScore)==null)
		{
			m.put(newScore, 1);
		}
		else
		{
			m.put(newScore, m.get(newScore)+1);
		}
		
		Set<Integer> s=m.keySet();
		Object a[]=s.toArray();
		int rank=0;
		int index=Arrays.binarySearch(a, newScore);
		for(int i=a.length-1;i>=index+1;i--)
		{
			Object temp=a[i];

			rank+=m.get(temp);
			
		}
		return rank+1;
	}
/*
	public static double search(int newScore) {
		int length = score.size();
		if(length==0)
			return 1;
		if(length==1)
		{
			if(score.get(0)>newScore)
			{
				return 2;
			}
			else
			{
				return 1;
			}
		}
		
		int cur = length / 2;
		while (!(score.get(cur) <= newScore && score.get(cur + 1) <= newScore)) {
			if(score.get(cur) > newScore)
			{
				cur/=2;
			}
			else
			{
				cur=cur+(length-cur)/2;
			}
		}
		score.add(newScore);
		return cur+1;

	}*/
}