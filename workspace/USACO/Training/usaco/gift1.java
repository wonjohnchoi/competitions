package usaco;
/*
ID: yojo1002
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

class gift1 {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("gift1.in"));                                          
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
    StringTokenizer st=new StringTokenizer(f.readLine());
    int numLines=Integer.parseInt(st.nextToken());
    String names[]=new String[numLines+20];
    for(int i=1;i<=numLines;i++)
    	names[i]=f.readLine();
    
    String giver;
    int money,numReceiver;
    int answer[]=new int[numLines+10];
    for(int s=1;s<=numLines+10;s++)
    	answer[s-1]=0;
    for(int i=1;i<=numLines;i++)
    {
    	giver=f.readLine();
    	st= new StringTokenizer(f.readLine());
    	money=Integer.parseInt(st.nextToken());
    	numReceiver=Integer.parseInt(st.nextToken());
    	String receivers[]=new String[numReceiver+1];
    	for(int j = 1;j<=numReceiver;j++)
    	{
    		receivers[j]=f.readLine();
    	}
    	for(int k=1;k<=numLines ;k++)
    	{
    		if(numReceiver!=0 && money!=0)
    		{
	    		if(names[k].equals(giver))
	    		{
	    			answer[k]-=(Math.floor(money/numReceiver)*numReceiver);
	    		}
	    		for(int l=1;l<=numReceiver;l++)
	    		{
	    			if(receivers[l].equals(names[k]))
	    			{
	    				answer[k]+=(Math.floor(money/numReceiver));
	    			}
	    		}
    		}
    	}
    }
    for(int v=1;v<=numLines;v++)
	{
		out.println(names[v]+" "+answer[v]);
	}
    
    
    out.close();                               
    System.exit(0);                            
  }
}
