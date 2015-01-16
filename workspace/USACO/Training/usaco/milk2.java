package usaco;
/*
ID: yojo1002
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;

public class milk2 {
	
	public static void main (String [] args) throws IOException {
	// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
	                                                  // input file name goes above
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
	    int bTime[],eTime[];
	    int num=Integer.parseInt(f.readLine());
	    bTime=new int[num];
	    eTime=new int[num];
	    StringTokenizer st;
	    for(int i=0;i<num;i++)
	    {
	    	st=new StringTokenizer(f.readLine());
	    	bTime[i]=Integer.parseInt(st.nextToken());
	    	eTime[i]=Integer.parseInt(st.nextToken());
	    }
	    for(int i=0;i<num;i++)
	    {
	    	if(!(eTime[i]==0||bTime[i]==0))
	    	{
		    	for(int j=0;j<num;j++)
		    	{
		    		if((eTime[j]!=0||bTime[j]!=0)&&eTime[i]!=eTime[j] &&bTime[i]!=bTime[j])
		    		{
			    		if(bTime[j]>=bTime[i] && eTime[j]<=eTime[i])
			    		{
			    			eTime[j]=0;
			    			bTime[j]=0;
			    		}
			    		if(bTime[j]>=bTime[i]&&eTime[j]>=eTime[i] && bTime[j]<=eTime[i])
			    		{
			    			bTime[j]=bTime[i];
			    			eTime[i]=0;
			    			bTime[i]=0;
			    		}
			    		if(bTime[j]<=bTime[i]&&eTime[j]<=eTime[i] && bTime[i]<=eTime[j])
			    		{
			    			bTime[i]=bTime[j];
			    			eTime[j]=0;
			    			bTime[j]=0;
			    		}
			    		if(bTime[i]>=bTime[j] && eTime[i]<=eTime[j])
			    		{
			    			eTime[i]=0;
			    			bTime[i]=0;
			    		}
			    		
		    		}
		    	}
	    	}
	    }
	    int timeIn=0;
	    int timeOut=0;
	    Arrays.sort(eTime);
	    Arrays.sort(bTime);

	    for(int i=0;i<num;i++)
	    {
	    	if(eTime[i]!=0||bTime[i]!=0)
	    	{
		
		    	if(timeIn<=(eTime[i]-bTime[i]))
		    	{
		    		timeIn=eTime[i]-bTime[i];
		    	}
		 
			    if(i!=0)
			    {
				   	if(timeOut<=(bTime[i]-eTime[i-1])&&bTime[i]!=0&&eTime[i-1]!=0)
			    	{
					   		timeOut=bTime[i]-eTime[i-1];
				   	}
			    }
		    
	    	}
	    	
	    }
	   
	    out.println(timeIn+" "+timeOut);

	    out.close();
	    System.exit(0);
	  }
	    	
}
