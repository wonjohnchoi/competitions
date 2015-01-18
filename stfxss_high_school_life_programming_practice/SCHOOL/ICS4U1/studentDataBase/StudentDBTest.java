package studentDataBase;


import java.util.*;
import java.io.*;
/**
 * Test program to test the StudentDataBase class
 * @author Wonjohn Choi
 * @supervisor Mr. Reid
 * @course ICS4U
 * @date 2010/05/03
 */
public class StudentDBTest {
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
			choice=option();
			
			//if choice==1, search option
			if(choice==1)
			{
				//ask for the value to search
				System.out.print("Value: ");
				
				//get input
				String value=sc.next();
				
				//output the search result
				System.out.printf("Searched by %s: \n",  value);
				System.out.println(db.search(value));

			}
			
			//if choice ==2, count # of students for some condition
			else if(choice==2)
			{
				//prompt whether it is by course or gender (female)
				System.out.println("1. by course 2. by gender (# of females)");
				
				//get input
				int tmp=sc.nextInt();
				
				//if it's by course
				if(tmp==1)
				{
					//get input and show result
					System.out.print("Course name: ");
					String course=sc.next();
					System.out.printf("There are %d students who take %s course\n", db.getNumStudentsByCourse(course), course);
				}
				else if(tmp==2)
				{
					//show # of female students
					System.out.printf("There are %d female student\n",db.getNumFemaleStudents());
				}
			}
			
			//save data
			else if(choice==3)
			{
				//use method to write data to a file
				db.saveStudentDataBase(fileOut);
				System.out.println("Saved");
			}
			
			//sort data
			else if(choice==4)
			{
				//ask which sorting method user wants
				System.out.println("1. Bubble sort 2. Select sort");
				int tmp=sc.nextInt();
				double ini_time=System.currentTimeMillis(); //initial time to calculate time consumed for sorting
				
				//if it's 1,
				if(tmp==1)
				{
					//do bubble sort
					db.bubbleSort();
				}
				
				//if it's 2
				else if(tmp==2)
				{
					//do select sort
					db.selectSort();
				}
				
				double tot_time=System.currentTimeMillis()-ini_time; //total time consumed
				//statiscally, select sort is way faster (6~7 times)
				if(tmp==1)
				{
					System.out.printf("Sorted by bubble sort (Time consumed: %.2f ms)\n", tot_time);
				}
				else if(tmp==2)
				{
					System.out.printf("Sorted by select sort (Time consumed: %.2f ms)\n", tot_time);
				}
			}
			
			//if choice is 5,
			else if(choice==5)
			{
				//show String representation of students
				System.out.println(db.displayStudents());
			}
			
			//if choice is 6, (reset)
			else if(choice==6)
			{
				//read a file (re-read to reset)
				db=new StudentDataBase();
				db.readStudentDataBase("StudentData.txt");
				System.out.println("Reset Complete");
			}
			
			//if choice is 6, done
		}while(choice!=7);



	}

	/**
	 * ask for an option
	 * @return the choice
	 */
	public static int option()
	{
		Scanner sc=new Scanner(System.in);
		
		//prompt
		System.out.println("Choose a number");
		System.out.println("1. search 2. get # of specific students 3. save 4. sort 5. display 6. reset 7. exit");
		
		//get input
		int choice=sc.nextInt();
		
		return choice;
	}
}
