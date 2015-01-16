package Broadwide_Contest;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Problem4_Vortex_Cipher
{
	public static void main(String args[]) throws IOException
	{
		Scanner sc=new Scanner(new FileReader("Data41.txt"));
		for(int i=0;i<5;i++)
		{
			String words[]=sc.nextLine().split(" ");
			for(int j=0;j<words.length;j++)
			{
				char letter[]=words[j].toCharArray();
				int len=0;
				for(int k=0;k<letter.length;k++)
				{
					if(letter[k]<='z' && 'a'<=letter[k])
					{
						len++;
					}
				}
				if(len==1)
				{
					if(letter[0]=='z')
					{
						letter[0]='a';
					}
					else
					{
						letter[0]+=1;
					}
				}
				else if(len==2)
				{
					char tmp=letter[0];
					letter[0]=letter[1];
					letter[1]=tmp;
					for(int k=0;k<2;k++)
					{
						if(letter[k]=='a')
						{
							letter[k]='z';
						}
						else
						{
							letter[k]-=1;
						}
					}
				}
				else if(len%2==1 && len>2)
				{
					int iter[]={0,(len+1)/2,(len-1)/2};

					for(int k=0;k<3;k++)
					{
						if(letter[iter[k]]=='z')
						{
							letter[iter[k]]='a';
						}
						else
						{
							letter[iter[k]]++;
						}
					}
					char mid=letter[(len-1)/2];
					String word=new String(letter);
					String fir, sec;
					fir=word.substring(1, (len-1)/2)+letter[0];
					sec=letter[len-1]+word.substring((len-1)/2+1, len-1);
					word=mid+fir+sec;
					
					
					letter=word.toCharArray();
				}
				else if(len%2==0 && len>3)
				{
					//len=letter.length;
					int iter[]={len/2-1,len/2, 0, len-1};
					for(int k=0;k<4;k++)
					{
						if(letter[iter[k]]=='a')
						{
							letter[iter[k]]='z';
						}
						else
						{
							letter[iter[k]]--;
						}
					}
					char mid1=letter[len/2-1];
					char mid2=letter[len/2];
					letter[len/2-1]=letter[0];
					letter[len/2]=letter[len-1];
					letter[0]=mid1;
					letter[len-1]=mid2;
					
				}
				words[j]=new String(letter);
			}
			for(int j=0;j<words.length;j++)
			{
				System.out.print(words[j]+" ");
			}
			System.out.println();
				
			
		}
	}
}
