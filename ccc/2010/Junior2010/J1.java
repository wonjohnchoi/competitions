package Junior2010;

import java.util.Scanner;

public class J1 {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		
		//declare two integer values 
		//theNum is the number you wants to represent using fingers
		//count will count the number of ways you can represent theNum using fingers
		int theNum, count=0;
		
		//get input
		theNum=sc.nextInt();
		
		//one hand representation (if theNum is less than 6, you can represent using one hand)
		if(theNum<6)
		{
			count++;
		}
			
		//two hands representation
		// handOne+handTwo = 5+ (theNum-5) =theNum
		int handOne=5, handTwo=theNum-5;
		
		//question states first hand is bigger than second hand.
		while(handOne>=handTwo)
		{
			//if the number baby counting using the second hand is bigger or equal to 1,
			if(handTwo>=1)
			{
				count++;
			}
			//decrease first hand by one
			//increase second hand by one
			handOne--;
			handTwo++;
		}
		
		//output the answer
		System.out.print(count);
	}
}
