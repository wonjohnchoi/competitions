package March14;

/*
ID: yojo1002
LANG: JAVA
TASK: rocks
*/

/*
 * Code By Wonjohn Choi
 */
import java.util.*;
import java.io.*;
public class rocks {
	public static void main(String args[]) throws IOException
	{
		String File = "rocks";
		Scanner in=new Scanner(new FileReader(File+".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(File+".out")));
		
		int num=in.nextInt();
		String ans[]=recur(num);
		for(int i=0;i<ans.length;i++)
		{
			out.println(ans[i]);
		}
		in.close();
		out.close();
		System.exit(0);
		
	}
	
	public static String[] recur(int n)
	{
		if(n==1)
		{
			String temp[]= {"O","X","O"};
			return temp;
		}
		String pre[]=recur(n-1);
		String next[]=new String[(int)(Math.pow(2, n))+1];
		String rev[]=new String[(int)Math.pow(2, n-1)];
		for(int i=0;i<rev.length;i++)
		{
			rev[i]=pre[rev.length-i];
			//System.out.println(rev[i]);
		}
		for(int i=0;i<next.length-1;i++)
		{
			if(i<(next.length+1)/2-1)
			{
				next[i]="O"+rev[i];
			//	System.out.println(next[i]);
			}
			else
			{
				next[i]="X"+pre[i-(next.length+1)/2+2];
				//System.out.println(next[i]);
			}
		}
		next[next.length-1]="O"+pre[0];
		return next;
	}
}
