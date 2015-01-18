package heapHomework;

import java.io.IOException;
import java.util.Scanner;

/**
 * HWScheduleTest is test class to test HWSchedule class
 * @author Wonjohn Choi, Megan Lee
 * @supervisor Mr. Reid
 * @course ICS4U
 * @date 2010/05/12
 */
public class HWScheduleTest
{
	public static void main(String args[]) throws IOException
	{
		String fileIn="HomeworkData.txt";
		String fileOut="HomeworkDataSaved.txt";

		Scanner sc=new Scanner(System.in);

		int choice;

		//instantiate
		HWSchedule hw=new HWSchedule();

		//read a file
		hw.readHomeworkFile(fileIn);

		do
		{
			//get choice
			choice=showOption();

			//add
			if(choice==1)
			{
				sc=new Scanner(System.in);

				//prompt
				System.out.println("Put input with a format \"name//start date//end date//weight\"");

				//get input
				String input=sc.nextLine();

				//add
				hw.addAnEntry(input);
			}

			// search option
			else if(choice==2)
			{
				sc=new Scanner(System.in);
				
				//ask for the value to search
				System.out.print("Name: ");

				//get input
				String name=sc.nextLine();
				Homework result=hw.search(name);

				//output the search result
				System.out.println("Searched by "+ name);				

				if(result==null)
				{
					System.out.println("Not Found");
				}
				else
				{
					System.out.println(result);
					System.out.println("If you want to remove this Student, press Y. If not, press N");
					String tmp=sc.next();
					if(tmp.equalsIgnoreCase("Y"))
					{
						hw.removeAnEntry(result);
					}
				}


			}

			//count # of students 
			else if(choice==3)
			{
				//show total # of students
				System.out.printf("There are %d students\n", hw.countTotalEntries());
			}

			//sort data
			else if(choice==4)
			{
				//ask which sorting method user wants
				System.out.println("1. byName 2. byStartingTime 3. byDueTime 4. byWeight 5. inReverseOrder");
				int tmp=sc.nextInt();
				double ini_time=System.currentTimeMillis(); //initial time to calculate time consumed for sorting

				if(tmp==1)
				{
					hw.sortByName();
				}
				else if(tmp==2)
				{
					hw.sortByStartingTime();
				}
				else if(tmp==3)
				{
					hw.sortByDueTime();
				}
				else if(tmp==4)
				{
					hw.sortByWeight();
				}
				else if(tmp==5)
				{
					hw.sortReverse();
				}
				else
				{
					System.out.println("Wrong Input");
				}

				double tot_time=System.currentTimeMillis()-ini_time; 

				System.out.printf("Time consumed: %.2f ms\n", tot_time);
			}

			//save data
			else if(choice==5)
			{
				//use method to write data to a file
				hw.saveHomeworkData(fileOut);
				System.out.println("Saved");
			}

			//display
			else if(choice==6)
			{
				//show String representation of students
				System.out.println(hw.displayEntries());
			}

			//reset
			else if(choice==7)
			{
				//read a file (re-read to reset)
				hw=new HWSchedule();
				hw.readHomeworkFile(fileIn);
				System.out.println("Reset Complete");
			}


			//if choice is 8, done
		}while(choice!=8);



	}
	/**
	 * ask for an option
	 * @return the choice
	 */
	public static int showOption()
	{
		Scanner sc=new Scanner(System.in);

		//prompt
		System.out.println("Choose a number");
		System.out.println("1. add 2. search & remove 3. get # of students 4. sort 5. save 6. display 7. reset 8. exit");

		//get input
		int choice=sc.nextInt();

		return choice;
	}
}
