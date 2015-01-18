package Senior2009;

import java.util.*;
import java.io.*;

public class P2009S4 {
	static short M= Short.MAX_VALUE;
	static int MAX;
	static short route[][];
	static short sellingCity[];
	static short shipping[];
	static int numCity;
	
	public static void main(String[]args) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("DATA5.txt"));
		numCity=Integer.parseInt(br.readLine());
		int numRoute=Integer.parseInt(br.readLine());
		
		route=new short[numCity][numCity];
		MAX=numCity;
		for(int i=0;i<numCity;i++)
			for(int j=0;j<numCity;j++)
				route[i][j]=Short.MAX_VALUE;
		
		for(int i=0;i<numRoute;i++)
		{			
			StringTokenizer st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			short fee=Short.parseShort(st.nextToken());
			//System.out.println(a+" "+b);
			route[a-1][b-1]=fee;
			route[b-1][a-1]=fee;		
		}
		int numSellingCity=Integer.parseInt(br.readLine());
	
		sellingCity=new short[numCity];
		Arrays.fill(sellingCity,M);
	
		for(int i=0;i<numSellingCity;i++)
		{
			StringTokenizer st=new StringTokenizer(br.readLine());
			int city=Integer.parseInt(st.nextToken());
			short fee=Short.parseShort(st.nextToken());
			sellingCity[city-1]=fee;
		}
		
		short destination=(short)(Short.parseShort(br.readLine())-1);
		/*
		short min=Short.MAX_VALUE;
		for(short i=0;i<numCity;i++)
		{
			int temp=di(i, destination, route)+sellingCity[i];
			if(sellingCity[i]!=M)
				if(min> temp)
						min=(short) temp;
		}
		System.out.println(min);
		*/
		Dijkstra (destination);
		 
		// Dijkstra has given the minimum shipping costs from every city
		// to the destination. So the cheapest pencil we can get at that
		// city is from the city with the lowest shipping cost + pencil cost.
		int min = Short.MAX_VALUE;
		int totalCost = 0;

		for (int i = 0 ; i < numCity ; i++)
		{
			if(sellingCity[i]!=Short.MAX_VALUE)
			{
				totalCost = sellingCity [i] + shipping [i];
				if (totalCost < min)
					min = totalCost;
			}
		}
		System.out.println (min);
		
	}
	
	// Use Dijkstra's algorithm to find the cheapest path from the destination
    // to all other points in O(n^2) time.
    // In other words it fills the shipping array with the minimum shipping
    // cost from that city to the destination city.
    public static void Dijkstra (int start)
    {
		boolean[] used = new boolean [numCity];
		int city, small, count;
	
		//step 1: fill all shipping values with a large value except the destination
		shipping= new short [numCity];
		for (int i = 0 ; i < numCity ; i++)
		{
		    used [i] = false;
		    shipping [i] = Short.MAX_VALUE;
		}
		shipping [start] = 0;
		count = 0;
		while (count < numCity)
		{
		    // Step 2: Choose the next city:
		    // the one with the smallest shipping that hasn't been used.
		    small = Short.MAX_VALUE;
		    city = 1;
		    for (int i = 0 ; i < numCity ; i++)
		    {
				if (!used [i] && shipping [i] < small)
				{
				    small = shipping [i];
				    city = i;
				}
		    }
		    
		    // Step 3: for all cities (i) connected to the city in question,
		    //         update their distance to city. It is the the distance
		    //         to the city plus the distance along the edge from
		    //         the ith city to the city, if that is smaller than
		    //         what is already stored in shipping for that city.
		    used [city] = true;
		    count++;
		    for (int i = 0 ; i < numCity ; i++)
		    	if (route [city] [i] >= 0 && !used [i])
		    		if (shipping [i] > shipping [city] + route [city] [i])
		    			shipping [i] = (short) (shipping [city] + route [city] [i]);
		}
    }



	public static int di(short start, short end, short path_info[][])
	{
		short di[]=new short[MAX];
		for(int i=0;i<MAX;i++)
			di[i]= M;
	    short chk[]=new short[MAX];
	
	    short idx = 0;
	    
	    di[start] = 0;
	    for(short i=0; i<MAX; i++)
	    {
	        short min = M;
	        for(short j=0; j<MAX; j++)
	        {
	            if( (chk[j]==0) && (di[j] < min))
	            {
	                idx= j;
	                min= di[j];
	            }
	        }
	        chk[idx]=1;
	       
	        for(short j=0; j<MAX; j++)
	        {
	            if( di [j] > di[idx]+path_info[idx][j])
	            {
	                di [j] = (short) (di[idx]+path_info[idx][j]);
	            }
	        }
	        if(idx==end)
	        {
	            break;
	        }
	    }
	    
	    return di[end];
	}
	
}
