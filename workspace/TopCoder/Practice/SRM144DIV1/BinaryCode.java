package SRM144DIV1;

import java.util.*;
public class BinaryCode {
	public static String[] decode(String message)
	{
		String[] ans={"NONE","NONE"};
		LinkedList<Integer> temp=new LinkedList<Integer>();
		for(int i=0;i<2;i++)
		{
			boolean b=true;
			temp.add(0);
			temp.add(i);
			for(int j=2;j<=message.length();j++)
			{
				temp.add((int)(message.charAt(j-2)-'0')-temp.get(j-1)-temp.get(j-2));
				if(!(temp.get(j)==0 ||temp.get(j)==1))
					b=false;
			}
			if((int)(message.charAt(message.length()-1)-'0')-temp.get(message.length()-1)!=temp.get(message.length()))
				b=false;
			if(b==true)
			{
				ans[i]="";
			
				for(int j=1;j<=message.length();j++)
				{
					ans[i]+=temp.get(j);
	
				}
			}
			
			temp.clear();
		}
		return ans;
		
	}

}