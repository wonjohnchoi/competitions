package SRM144DIV1;

import java.util.*;

public class Lottery {
	public static String[] sortByOdds(String[] rules)
	{
		int size=rules.length;
		Arrays.sort(rules);
		String[] names=new String[size];
		java.util.Arrays.fill(names, "");
		int[] possibles=new int[size];
	//	LinkedList<String> l=new LinkedList<String>();
		for(int i=0;i<size;i++)
		{
			String temp[]=rules[i].split(" ");
			for(int j=0;j<temp.length-5;j++)
			{
				names[i]=names[i]+temp[0]+" ";
			}
			names[temp.length-5]+=temp[temp.length-5].substring(0,temp[temp.length-5].length()-1);
			
			boolean unique=temp[temp.length-1]=="F" ? false:true;
			boolean sort=temp[temp.length-2]=="F" ? false:true;
			int blanks=Integer.parseInt(temp[temp.length-3]);
			int choices=Integer.parseInt(temp[temp.length-4]);
			if(!unique&&!sort)
			{
				possibles[i]=1;
				for(int j=0;j<blanks;j++)
					possibles[i]*=choices;
			}
			if(unique&&sort)
			{
				possibles[i]=1;
				for(int j=0;j<blanks;j++)
				{
					possibles[i]*=choices-j;
				}
			}
			if(unique&&!sort)
			{
				possibles[i]=1;
				for(int j=0;j<blanks;j++)
					possibles[i]*=choices;
				if(blanks!=1)
				{
					
				}
			}
		}
		
		return names;
	}
	
	public static void UniqueAndNotSort(int possibles, int blanks, int choices)
	{
		if(choices==0)
		{
			
		}
		else if(blanks==1)
			possibles*=choices;
		else
		{
			for(int i=1;i<=choices;i++)
			{
				UniqueAndNotSort(possibles, blanks-1, i-1);
			}
		}
	}
	
	public static void main(String args[])
	{
		int possibles=1;
		UniqueAndNotSort(possibles, 10, 2);
		System.out.println(possibles);
	}
}
