package Junior2010;

//get library.
import java.util.Scanner;

public class J3 
{
	static Scanner sc = new Scanner(System.in);
	
	/*
	 * getInt() subroutine gets the alphabet A or B from user 
	 * Then, it changes A to 0 and B to 1 so that it is easier to manipulate the input.
	 */
	public static int getInt() 
	{
		char alphabet=sc.next().charAt(0) ;
		
		//subtracting 'A' changes A to 0 and B to 1.
		int theInt= (int) (alphabet - 'A');
		
		return theInt;
	}
	
	public static void main(String args[]) 
	{
		/*
		 *	cur= 1,2,3,4,5, 6, or 7 (current number given)
		 * var[0] is the value of A
		 *	var[1] is the value of B
		 */
		int cur = sc.nextInt();
		int var[] = new int[2];

		/*
		 * if current number is 7, problem exits <=> cur is not 7.
		 */
		while (cur != 7) 
		{
			 // set the value of var[0] or var[1]
			if (cur == 1) 
			{
				var[getInt()] = sc.nextInt();
			}

			// output the value of var[0] or var[1]
			else if (cur == 2) 
			{
				System.out.println(var[getInt()]);
			}

			//increment first value by second value
			else if (cur == 3) 
			{
				var[getInt()]  += var[getInt()];
			}

			//muliply first value by second value
			else if (cur == 4) 
			{
				var[getInt()] *= var[getInt()];
			}

			//subtract first value by second value
			else if (cur == 5) 
			{
				var[getInt()] -= var[getInt()];
			}
			
			//divide first value by second value and drop the remainder
			else if (cur == 6) 
			{
				var[getInt()] /= var[getInt()];
			}
			
			//update current number
			cur = sc.nextInt();
		}

	}


}
