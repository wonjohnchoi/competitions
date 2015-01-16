package priorityBased;

import java.io.IOException;
import java.util.Scanner;

public class Test
{
	public static IO io;
	public static Function function;
	public static void main(String args[]) throws IOException
	{

		System.out.println("C:\\Users\\user\\Desktop\\dictionary\\");
		io=new IO(IO.getFILE("C:\\Users\\user\\Desktop\\dictionary\\"));
		function=new Function(io.readFILE());
		//function.sortBy(0);
		//function.sortBy(3);
		
		random();
		
		//function.add(io.readScreen());
		//io.writeMeaning(function.dict);
		//io.writeSpelling(function.dict);
		//io.writeFILE(function.dict);
	}
	public static void random()
	{
		do
		{
			function.random();
			System.out.println("continue? (c)");
		}while(new Scanner(System.in).nextLine().equals("c"));
		function.sortBy(1);
		function.sortBy(0);
		function.sortBy(3);
	}
}
