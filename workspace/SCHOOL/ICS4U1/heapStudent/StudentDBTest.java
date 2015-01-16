package heapStudent;

import java.io.IOException;
import java.util.Scanner;

/**
 * StudentDataBase is test at this StudentDBTest class
 * @author Wonjohn Choi, Megan Lee
 * @supervisor Mr. Reid
 * @course ICS4U
 * @date 2010/05/12
 */
public class StudentDBTest
{
	public static void main(String args[]) throws IOException
	{
		String fileIn="StudentData.txt";
		String fileOut="StudentDataSaved.txt";
		
		Scanner sc=new Scanner(System.in);

		int choice;

		//instantiate
		StudentDataBase db=new StudentDataBase();

		//read a file
		db.readStudentDataBase(fileIn);

		do
		{
			//get choice
			choice=showOption();
			
			//add
			if(choice==1)
			{
				sc=new Scanner(System.in);
				
				//prompt
				System.out.println("Put input with a form \"lastname,firstname,gender(F/M)," +
						"studentId,data of birth,courses(separate by space)\"");
				
				//get input
				String input=sc.nextLine();
				
				//add
				db.addAStudent(input);
			}
			
			// search option
			else if(choice==2)
			{
				//ask for the value to search
				System.out.print("Value: ");

				//get input
				String value=sc.next();
				Student result=db.search(value);
				
				//output the search result
				System.out.println("Searched by "+ value);				
				
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
						db.removeAStudent(result);
					}
				}
				

			}

			//count # of students 
			else if(choice==3)
			{
				//prompt whether it is by course or gender (female)
				System.out.println("1. by course 2. by # of male 3. by # of female 4. total student");

				//get input
				int tmp=sc.nextInt();

				//if it's by course
				if(tmp==1)
				{
					//get input and show result
					System.out.print("Course name: ");
					String course=sc.next();
					System.out.printf("There are %d students who take %s course\n", db.countStudentsByCourse(course), course);
				}
				else if(tmp==2)
				{
					//show # of male students
					System.out.printf("There are %d male students\n",db.countMaleStudents());
				}
				else if(tmp==3)
				{
					//show # of female students
					System.out.printf("There are %d female students\n", db.countFemaleStudents());
				}
				else if(tmp==4)
				{
					//show total # of students
					System.out.printf("There are %d students\n", db.countTotalStudents());
				}
			}

			//sort data
			else if(choice==4)
			{
				//ask which sorting method user wants
				System.out.println("1. byLastName 2. byFirstName 3. byDateOfBirth 4. byStudentId 5. inReverseOrder");
				int tmp=sc.nextInt();
				double ini_time=System.currentTimeMillis(); //initial time to calculate time consumed for sorting

				if(tmp==1)
				{
					db.sortByLastName();
				}
				else if(tmp==2)
				{
					db.sortByFirstName();
				}
				else if(tmp==3)
				{
					db.sortByDob();
				}
				else if(tmp==4)
				{
					db.sortByStudentId();
				}
				else if(tmp==5)
				{
					db.sortInReverse();
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
				db.saveStudentDataBase(fileOut);
				System.out.println("Saved");
			}
			
			//display
			else if(choice==6)
			{
				//show String representation of students
				System.out.println(db.displayStudents());
			}

			//reset
			else if(choice==7)
			{
				//read a file (re-read to reset)
				db=new StudentDataBase();
				db.readStudentDataBase(fileIn);
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
