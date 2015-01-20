package assignment_1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Test
{
	public static void main(String args[]) throws IOException
	{
		APnum n=Oper.APzero();
		Scanner sc=new Scanner(new FileReader("tmp"));
		while(sc.hasNext())
		{
			String line=sc.nextLine();
			APnum tmp=Oper.APzero();
			for(int i=0;i<line.length();i++)
			{
				Oper.APin(tmp, line.charAt(i));
			}
			Oper.APadd(n, tmp);
		}
		System.out.print(Oper.APfirstdigit(n));
		for(int i=0;i<9;i++)
		{
			System.out.print(Oper.APnextdigit(n));
		}
	}
}
