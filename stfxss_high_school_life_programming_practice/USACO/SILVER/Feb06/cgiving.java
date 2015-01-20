package Feb06;
/*
ID: yojo1002
LANG: JAVA
TASK: cgiving
*/
import java.util.*;
import java.io.*;
public class cgiving {
	static int M=65535;
	static int MAX;
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("cgiving.in"));
		PrintWriter pw=new PrintWriter(new FileWriter("cgiving.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int node=Integer.parseInt(st.nextToken());
		int street=Integer.parseInt(st.nextToken());
		int numBull=Integer.parseInt(st.nextToken());
		MAX=node;
		int fee[][]=new int[node][node];
		for(int i=0;i<node;i++)
			Arrays.fill(fee[i], M);
		
		for(int i=0;i<street;i++)
		{
			st=new StringTokenizer(br.readLine());
			int x,y,tempFee;
			x=Integer.parseInt(st.nextToken());
			y=Integer.parseInt(st.nextToken());
			tempFee=Integer.parseInt(st.nextToken());
			fee[x-1][y-1]=tempFee;
			fee[y-1][x-1]=tempFee;
		}
		
		int BullL[]=new int[numBull];
		int CowL[]=new int[numBull];
		
		for(int j=0;j<numBull;j++)
		{
			st=new StringTokenizer(br.readLine());
			BullL[j]=Integer.parseInt(st.nextToken())-1;
			CowL[j]=Integer.parseInt(st.nextToken())-1;
		}
		
		for(int i=0;i<numBull;i++)
			pw.println(di(BullL[i],0 , fee)+di(0,CowL[i],fee));
		
		

		pw.close();
		System.exit(0);
		
	}
	
	//Implement DijkstraAlgorithm
	public static int di(int start, int end, int path_info[][])
	{
		int di[]=new int[MAX];
		Arrays.fill(di, M);
	    int chk[]=new int[MAX];
	
	    int idx = 0;

	    di[start] = 0;
	    for(int i=0; i<MAX; i++)
	    {
	        int min = M;
	        for(int j=0; j<MAX; j++)
	        {
	            if(chk[j]==0 && di[j] < min)
	            {
	                idx= j;
	                min= di[j];
	            }
	        }
	        chk[idx]=1;
	        
	        for(int j=0; j<MAX; j++)
	        {
	            if( di [j] > di[idx]+path_info[idx][j])
	            {
	                di [j] = di[idx]+path_info[idx][j];
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
