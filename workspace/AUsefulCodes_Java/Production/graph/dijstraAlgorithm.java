package graph;

import java.util.Arrays;

public class dijstraAlgorithm {
	public static short M= Short.MAX_VALUE;
	public static short path_info[][]=
	{     
	     { 0, 1, 4, 2, 9, M, M, M, M, 3},     //a    0
	     { 1, 0, M, M, 7, M, M, 5, M, M},     //b    1
	     { 4, M, 0,11, M, M, 4, M, M, M},     //c    2
	     { 2, M,11, 0, M, 1, M, M, M, M},     //d    3
	     { 9, 7, M, M, 0, 2, M, 1, M, 3},     //e    4
	     { M, M, M, 1, 2, 0, 2, M, 5, 4},     //f    5
	     { M, M, 4, M, M, 2, 0, M, 7, M},     //g    6
	     { M, 5, M, M, 1, M, M, 0, M, 2},     //h    7
	     { M, M, M, M, M, 5, 7, M, 0, 3},     //i    8
	     { 3, M, M, M, 3, 4, M, 2, 3, 0}      //j    9
	};
	public static short result[];
	public static int sizeOfPath=10;
	public static void main(String args[])
	{
	
		int destination =6;		
		Dijkstra (destination);
		for(int i=0;i<sizeOfPath;i++)	
			System.out.println (result[i]);
	}
	
	public static void Dijkstra (int start)
	{
		boolean[] used = new boolean [sizeOfPath];
		int city=0, small;
	
		result= new short [sizeOfPath];
		Arrays.fill(result, M);
		
		result [start] = 0;
		
		for(int j=0;j<sizeOfPath;j++) {
		    small = M;
		   
		    for (int i = 0 ; i < sizeOfPath ; i++)
		    {
				if (!used [i] && result [i] < small)
				{
				    small = result [i];
				    city = i;
				}
		    }
		    
		    used [city] = true;
		    
		    for (int i = 0 ; i < sizeOfPath ; i++){
		    	if (path_info [city] [i]>=0 && !used [i]) {
		    		if (result [i] > result [city] + path_info[city] [i]) {
		    			result [i] = (short) (result [city] + path_info [city] [i]);
		    		}
		    	}
		    }
		}
	}

}
