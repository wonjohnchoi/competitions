package notOrganized;

import java.util.*;
import java.io.*;

public class longestIncreasingSequence2 {
	

	static int LIS[]=new int[1024];
	
	public static int lis( ArrayList< Integer > S ) {
		// insert negative infinity at the front
		S.set(0, Integer.MIN_VALUE);
		int n = S.size();
		// run iterative DP
		for( int i = n-1; i >= 0; i--)
		{
			LIS[i] = 1; // pick nothing but the first element
			// try picking a larger second element
			for( int j = i + 1; j < n; j++ )
			if( S.get(j) > S.get(i)&& LIS[j] + 1 > LIS[i] )
				LIS[i] = LIS[j] + 1;
			// return the length of the LIS, minus the first number we added
		}
		return LIS[0]-1;	
	}
	public static void main(String[] args) throws IOException
	{
		PrintWriter pw=new PrintWriter(new FileWriter("OUT1.txt"));
		Scanner sc=new Scanner(new FileReader("DATA1.txt"));
		ArrayList<Integer> al=new ArrayList<Integer>();
		while(sc.hasNext())
			al.add(sc.nextInt());
		System.out.println(lis(al));
	}
}
