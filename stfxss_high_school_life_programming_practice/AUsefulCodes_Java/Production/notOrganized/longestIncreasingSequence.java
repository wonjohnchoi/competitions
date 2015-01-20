package notOrganized;

import java.io.*;
import java.util.*;

public class longestIncreasingSequence {
	public static void main(String args[]) throws IOException
	{
		PrintWriter pw=new PrintWriter(new FileWriter("OUT1.txt"));
		Scanner sc=new Scanner(new FileReader("DATA1.txt"));
		
		ArrayList<Double> al=new ArrayList<Double>();
	
		while(sc.hasNext())
			al.add(sc.nextDouble());
		ArrayList<Double> al2=(ArrayList<Double>) al.clone();
		
		
		int ans=Integer.MIN_VALUE;
		double previous=Double.MIN_VALUE;
		int temp=0;
		while(al.size()!=0)
		{	
			if(al.get(0)<=previous)
				temp=0;
			temp++;
			if(temp>ans)
				ans=temp;
			previous=al.remove(0);
		}
		if(ans==Integer.MIN_VALUE)
		{
			System.out.println(0);
			System.out.println("There is no elements");
		}
		else
			System.out.println(ans);
		temp=0;
		previous=Double.MIN_VALUE;
		int count=0;
		while(al2.size()!=count&&ans!=Integer.MIN_VALUE)
		{	
			if(al2.get(count)<=previous)
				temp=0;
			temp++;
			if(temp==ans)
			{
				for(int i=ans-1;i>=0;i--)
					System.out.printf("%.0f ",al2.get(count-i));
				System.out.println();
			}
			previous=al2.get(count);
			count++;
		}
		pw.close();
		System.exit(0);
		
		
		
	}
}
