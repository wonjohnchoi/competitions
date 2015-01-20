package volume_I;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created on 2010. 6. 27.
 * @Titel: Volume I-UVA 100
 * @URL: http://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=3&page=show_problem&problem=36
 * @Idea
	Approaching with dynamic solving
    Store results we get to an array
    When we need to check some value, if we already stored, just use it
 * @author Wonjohn Choi
 */
public class UVA100 {
	public static int[] cyclicLen=new int[1000000];
	public static void main(String args[]) throws IOException
	{
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			int max=Integer.MIN_VALUE;
			int i=sc.nextInt(),j=sc.nextInt();
			for(int x=i;x<=j;x++)
			{
				int len=getCyclicLen(x);
				if(len>max)
				{
					max=len;
				}
			}
			System.out.println(i+" "+j+" "+max);
		}
	}
	
	public static int getCyclicLen(int x)
	{
		if(x==1) return 1;
		int result=-1;
		if(x<1000000 && cyclicLen[x]!=0)
		{
			return cyclicLen[x];
		}
		
		if(x%2==0)
		{
			result=getCyclicLen(x>>1)+1;
		}
		else
		{
			result=getCyclicLen(((x<<1)+x+1)>>1)+2;
		}
		
		if(x<1000000 && cyclicLen[x]==0)
		{
			cyclicLen[x]=result;
		}
		return result;
	}
}
