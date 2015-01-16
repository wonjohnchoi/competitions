package capcs.choi.yr2010;

import java.io.*;
import java.util.*;
import java.math.*;
public class S3 {
	public static int numHouse;

	public static void main(String args[]) throws Exception
	{
		Scanner sc=new Scanner(new FileReader("s3.5.in"));
		numHouse=sc.nextInt();
		double house[]=new double[numHouse];
		for(int i=0;i<numHouse;i++)
		{
			house[i]=sc.nextDouble();
		}
		Arrays.sort(house);
		
		int water=sc.nextInt();

		double ans=100000000;
		for(int i=0;i<numHouse;i++)
		{
			ans=Math.min(process(house, water),ans);
			house[numHouse-1]-=1000000;
			Arrays.sort(house);
			
		}
		
				
	
		System.out.print(Math.round(ans));
		System.exit(0);
	}
	public static double process(double[] house, int water)
	{
		int num=house.length;
		if(water>=num)
			return 0;
		if(water==0 && num!=0)
			return Integer.MAX_VALUE;
		if(num==0)
			return 0;
		double temp[];
		double small=(house[num-1]-house[0])/2;
		for(int i=0;i<num-1;i++) // 0~ i  is not used for firing elmininating
		{	
			double ans=0;
			temp=new double[i+1];  //take first i number that is not used for water eliminating
				
		//get the value from house array
			for(int j=0;j<=i;j++)
			{
				//	System.out.println(j+" "+i);
				temp[j]=house[j];
			}
			
			ans=(house[num-1]-house[i+1])/2;
			
			ans=Math.max(process(temp, water-1),ans);
			
			if(small>ans)
			{
				small=ans;
			}
		}
		return small;
	}
}
