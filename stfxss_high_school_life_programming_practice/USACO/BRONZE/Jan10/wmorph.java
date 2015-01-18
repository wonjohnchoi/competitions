package Jan10;
/*
ID: yojo1002
LANG: JAVA
TASK: wmorph
*/
import java.util.*;
import java.io.*;
public class wmorph {
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("wmorph.in"));
		BufferedReader dic=new BufferedReader(new FileReader("dict.txt"));
		Scanner sc=new Scanner(new FileReader("dict.txt"));
		PrintWriter pw=new PrintWriter(new FileWriter("wmorph.out"));
		
		String start=br.readLine();
		String end=br.readLine();
		int length=start.length();
		int sizeOfDic=0;
		while(sc.hasNext())
		{
			sizeOfDic++;
			sc.nextLine();
		}
		
		String words[]=new String[sizeOfDic];
		int realSize=0;
		String temp;
		int counter=0;
		while(counter!=sizeOfDic)
		{
			counter++;
			temp=dic.readLine();
			if(temp.length()==length && !temp.equals(start))
			{
				words[realSize]=temp;
				realSize++;
			}
		}
		
		int count=0;
		while(!start.equals(end))
		{
			boolean done=false;
			for(int i=0;i<length&&!done;i++)
			{
				if(start.charAt(i)!=end.charAt(i))
				{
					
					for(int k=0;k<realSize && !done;k++)
					{
						temp=start.substring(0,i)+end.charAt(i)+start.substring(i+1,length);
						if(temp.equals(words[k]))
						{
							done=true;
							start=temp;
							count++;
							words[k]="FDSJINKC";
						}
					}
				}
			}
			if(!done)
			{
				for(int i=0;i<length&&!done;i++)
				{
					for(int i1=0;i1<realSize && !done;i1++)
					{
						if(start.charAt(i)!=words[i1].charAt(i))
						{
							
							temp=start.substring(0,i)+words[i1].charAt(i)+start.substring(i+1,length);
							if(temp.equals(words[i1]))
							{
								start=temp;
								count++;
								done=true;
								words[i1]="FDSJINKC";
							}
							
						}
					}
				}
				
			}
		}
		pw.println(count);
		pw.close();
		System.exit(0);
		
	}
}