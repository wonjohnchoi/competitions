package dec09;

/*
ID: yojo1002
LANG: JAVA
TASK: bigdance
*/
import java.io.*;
import java.util.*;

class bigdance {
	
	public static double findGlobalSum(int numCow, double label[])
	{
		double ans=0;
		double tempLabel[]=new double[(int)(Math.ceil((numCow-0.01)/2)+1)];
		int tempNumCow;
		if(numCow==2)
		{
			ans=label[1]*label[2];
		}
		else if(numCow>=3)
		{
			for(int j=1;j<=Math.ceil((numCow-0.01)/2);j++)
			{
				
				tempLabel[j]=label[j];
			}
			tempNumCow=(int) Math.ceil((numCow-0.01)/2);
			ans+=findGlobalSum(tempNumCow, tempLabel);
			for(int j=(int)(Math.ceil((numCow-0.01)/2)+1);j<=numCow;j++)
			{
				
				tempLabel[j-(int)(Math.ceil((numCow-0.01)/2))]=label[j];
				
			}
			tempNumCow=numCow-(int)(Math.ceil((numCow-0.01)/2));
			ans+=findGlobalSum(tempNumCow, tempLabel);
			
		}
		return ans;
	}
	public static void main (String [] args) throws IOException {
		// Use BufferedReader rather than RandomAccessFile; it's much faster
		BufferedReader f = new BufferedReader(new FileReader("bigdance.in"));
		// input file name goes above
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bigdance.out")));
		int numCow=Integer.parseInt(f.readLine());
		double[] label=new double[numCow+1];
		for(int i=1;i<=numCow;i++)
		{
			label[i]=(double)i;
		}
		double ans=(double)findGlobalSum(numCow, label);
		System.out.println(ans);
		out.println((int)ans);
		out.close();                                  // close the output file
		System.exit(0);                               // don't omit this!
	}
}

