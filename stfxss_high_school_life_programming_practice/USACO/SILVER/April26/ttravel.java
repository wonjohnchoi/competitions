package April26;

/*
ID: yojo1002
LANG: JAVA
TASK: ttravel
 */

/*
 * Code By Wonjohn Choi
 */
import java.util.*;
import java.io.*;
public class ttravel {
	static Query[] q;
	public static void main(String args[]) throws IOException
	{
		String File = "ttravel";
		Scanner sc=new Scanner(new FileReader(File+".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(File+".out")));

		int N=sc.nextInt();

		q=new Query[N+1];
		Stack<Integer> checkPoints=new Stack<Integer>();
		q[0]=new Query('a', 0);
		q[0].result=-1;
		for(int i=1;i<=N;i++)
		{
			char command=sc.next().charAt(0);
			
			if(command=='t')
			{
				q[i]=q[sc.nextInt()-1];
			}
			
			else
			{

				if(command=='a')
				{
					q[i]=new Query(command, i );
					q[i].result=sc.nextInt();
				}
				else if(command=='s')
				{
					q[i]=new Query(command, i);
					checkPoints.add(i);
				}
				q[i].pre=q[i-1];
			}
		}
		
		for(int i=N;i>0;i--)
		{
			if(q[i].result==-1)
			{
				sub(q[i]);
			}
		}
		

		for(int i=1;i<=N;i++)
		{
			
			out.println(q[i].result);
		
		}

		sc.close();
		out.close();
		System.exit(0);	
	}
	
	public static Query sub(Query q)
	{
		Query result=null;
		Query prev=q.pre;
		if(prev.command=='a')
		{
			Query dest=prev.pre;
			q.result=dest.result;
			result=dest;
		}
		
		else 
		{
			Query dest;
			do{
				dest=sub(prev);
			}while(dest.command=='s');
			q.result=dest.pre.result;
			result=dest.pre;
			
		}
		return result;
	}

	

}
class Query
{
	char command;
	int result=-1;
	int loc;
	Query pre;
	
	public Query (char o, int loc)
	{
		pre=null;
		command=o;
		this.loc=loc;
	}
	
}