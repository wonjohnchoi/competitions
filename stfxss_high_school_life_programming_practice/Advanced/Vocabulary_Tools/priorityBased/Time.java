package priorityBased;

import java.util.Scanner;

public class Time
{
	public static void main(String arg[]) throws InterruptedException
	{
		Scanner sc=new Scanner(System.in);
		double duration=sc.nextDouble();
		Thread.sleep((long) (duration*1000));
		System.out.println("DONE");
		
	}

}
