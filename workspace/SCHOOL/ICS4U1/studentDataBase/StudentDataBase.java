package studentDataBase;

import java.util.*;
import java.io.*;

/**
 * Class to manipulate a large set of data (student data), mainly include sorting methods and IO
 * @author Wonjohn Choi
 * @supervisor Mr. Reid
 * @course ICS4U
 * @date 2010/05/03
 */
public class StudentDataBase 
{
	protected String lastname[];
	protected String firstname[];
	protected String gender[];
	protected String studentId[];
	protected String dob[];
	protected String courses[];
	protected boolean sorted;

	/**
	 * Constructor
	 * Arrays are not initialized at constructor since the size of file is unknown
	 * They will be initialized at the method 'readStudentDataBase'
	 */
	public StudentDataBase()
	{
		//set sorted variable to be false;
		sorted=false;

		//initially set them as null (since nothing is there!)
		lastname = null;
		firstname = null;
		gender= null;
		studentId= null;
		dob= null;
		courses= null;
	}

	/**
	 * a method to read data from a file
	 * @param filename name of file to read data
	 * @throws IOException occurs when file does not exist
	 */
	public void readStudentDataBase(String filename) throws IOException
	{
		//Scanner object sc to read over file
		Scanner sc=new Scanner(new FileReader(filename));

		//variable to get the number of inputs
		int countInput=0;

		//loop to check the number of inputs
		while(sc.hasNextLine())
		{
			//if the input is not an empty line
			if(!sc.nextLine().trim().equals(""))
			{
				countInput++; //increase the number of line
			}
		}

		//let computer knows that finishing using Scanner
		sc.close();

		//once got information about the length of input, we can initialize the arrays with appropriate size
		lastname = new String[countInput];
		firstname = new String[countInput];
		gender= new String[countInput];
		studentId= new String[countInput];
		dob= new String[countInput];
		courses= new String[countInput];

		//use a new Scanner to read the data
		sc=new Scanner(new FileReader(filename));

		//for each index,
		for(int i=0;i<countInput;i++)
		{
			//get a line and split into an array
			String parts[]=sc.nextLine().split(",");

			//if length is 6.. (it's the right size)
			if(parts.length==6)
			{
				//store the data
				lastname[i]=parts[0];
				firstname[i]=parts[1];
				gender[i]=parts[2];
				studentId[i]=parts[3];
				dob[i]=parts[4];
				courses[i]=parts[5];
			}
			else
			{
				//there must be problem for this line so send alert
				System.err.printf("An error occurs at %d th line: there are %d elements on the line\n", i+1, parts.length);
			}
		}

		//let computer knows finished using Scanner object sc
		sc.close();
	}

	/**
	 *  a method to save data into a file
	 * @param filename name of file where data will be saved
	 * @throws IOException occurs when file does not exist
	 */
	public void saveStudentDataBase(String filename) throws IOException
	{
		//PrintWriter Object pw to write to a file
		PrintWriter pw=new PrintWriter(new FileWriter(filename));

		//it is ok to use the length of lastname array  to check the length of the data
		//since there is equal amount of Lastname, Firstname, Gender, StudentId, DOB, Courses
		for(int i=0; i<lastname.length; i++)
		{
			//write to file with the proper format (the same format when reading file)
			pw.printf("%s,%s,%s,%s,%s,%s\n", lastname[i], firstname[i], gender[i], studentId[i], dob[i], courses[i]);
		}

		//indicate finished using PrintWriter object pw
		pw.close();
	}

	/**
	 * sort arrays by last name via bubble sort algorithm
	 * Bubble sort is a method of sorting that uses exchange of two elements
	 * ex)
	 * 2314 -> 3214 -> 3124 (first circulation)
	 * 3124 ->1324 -> 1234 (second circulation)
	 * 1234 (third circulation)
	 */
	public void bubbleSort()
	{
		//if sorted, there is no point to sort
		if(!sorted)
		{
			//variable to decide whether to finish the loop
			boolean finished;

			//do this step
			do
			{
				finished=true;

				//for every index except the last one (since one index will be compared to the next index)
				for(int i=0;i<lastname.length-1;i++)
				{
					//if previous String has higher value, 
					if(lastname[i].compareTo(lastname[i+1])>0)
					{
						swap(i, i+1);

						//indicate it as not finished
						finished=false;
					}
				}
			}while(!finished); //while not finished,

			//indicate it's sorted
			sorted=true;
		}

	}

	/**
	 * sort arrays by last names via selection sort algorithm
	 * Select Algorithm is a sorting method that moves the lowest value 
	 * (or the highest value depends on the priority) to each spot
	 * Select sort is more effective than bubble sort when new data will be added to a sorted array 
	 * ex)
	 * 2314 
	 * 1324
	 * 1234
	 * 1234
	 */
	public void selectSort()
	{
		//if sorted, there is no point to sort
		if(!sorted)
		{
			//for every index,
			for(int i=0;i<lastname.length;i++) 
			{
				//set initial index as ith index
				int idx=i;

				//for the other index (after i)
				for(int j=i+1;j<lastname.length;j++)
				{
					//compare if there is any index with less value
					if(lastname[idx].compareTo(lastname[j])>0)
					{
						//update index as the index with less value
						idx=j;
					}
				}

				if(idx!=i) //if there was a change in index (something less was found),
				{
					swap(idx, i);
				}

			}

			//indicate it's sorted
			sorted=true;
		}
	}

	/**
	 * method to get the whole String of students
	 * @return display of students (String representation)
	 */
	public String displayStudents()
	{
		//variable to store
		String students="";

		//for each index,
		for(int i=0;i<lastname.length;i++)
		{
			//add each line
			students+=String.format("%s, %s, %s, %s, %s, %s\n", lastname[i], firstname[i], gender[i], studentId[i], dob[i], courses[i]);
		}

		return students;
	}

	/**
	 * method to calculate the number of the female students
	 * @return # of female students
	 */
	public int getNumFemaleStudents()
	{
		//variable to store the number of female students
		int numFemale=0;

		//for each index,
		for(int i=0;i<gender.length;i++)
		{
			//check if the gener is female
			if(gender[i].equalsIgnoreCase("F"))
			{
				numFemale++;
			}
		}

		return numFemale;
	}

	/**
	 * method to calculate the number of students who take the given course
	 * @param course that students take
	 * @return # of students who take the given course
	 */
	public int getNumStudentsByCourse(String course)
	{
		//variable to store the number of students who take the given course
		int numStudentsByCourse=0;

		//for each index,
		for(int i=0;i<gender.length;i++)
		{

			//done variable will check whether searching a student finishes
			boolean found=false;

			//get each courses a student has
			String eachCourse[]=courses[i].split(" ");

			//for each index,
			for(int j=0;j<eachCourse.length && !found;j++)
			{
				//check if the course is same to the given course
				if(eachCourse[j].equalsIgnoreCase(course))
				{
					//if so, num, something is found
					found=true;
				}
			}

			//if found
			if(found)
			{
				//increase # of student by one
				numStudentsByCourse++;
			}

		}

		return numStudentsByCourse;
	}

	/**
	 * method to search a String 'value'
	 * find only one entry (Check last name first using binarysearch and then check all entries by linear search)
	 * @param value that user requires to find
	 * @return the String representation of the found Student entry
	 */
	public String search(String value)
	{
		//store the matched student value
		String matchedStudents;

		//if not sorted
		if(!sorted)
		{
			//required to sort before doing binary search
			selectSort();
		}

		//Assume given value indicates the last name (binary search)
		matchedStudents=binarySearch(value);

		//check the other arrays with a linear search
		//for each index (use lastname array to check the size)
		for(int i=0;i<lastname.length && matchedStudents.length()==0;i++)
		{
			//check if each String from different arrays contains the value user wants to find
			if(lastname[i].equalsIgnoreCase(value) || firstname[i].equalsIgnoreCase(value) || gender[i].equalsIgnoreCase(value)
					|| studentId[i].equalsIgnoreCase(value)|| dob[i].equalsIgnoreCase(value))
			{
				//add the String representation 
				matchedStudents = String.format("%s, %s, %s, %s, %s, %s\n", lastname[i], firstname[i], gender[i], studentId[i], dob[i], courses[i]);
			}
			//check the course by spliting them
			else
			{
				String course[]=courses[i].split(" ");
				boolean done=false;

				//for each idx while not done,
				for(int j=0;j<course.length && !done;j++)
				{
					//check if the course code matches
					if(course[j].equalsIgnoreCase(value))
					{
						//add the String representation
						matchedStudents = String.format("%s, %s, %s, %s, %s, %s\n", lastname[i], firstname[i], gender[i], studentId[i], dob[i], courses[i]);
						done=true;
					}
				}
			}
		}

		//nothing is found?
		if(matchedStudents.length()==0)
		{
			//set the variable to Not found
			matchedStudents="Not found";
		}

		return matchedStudents;

	}

	/**
	 * method to swap two elements in arrays
	 * @param a first index
	 * @param b second index
	 */
	private void swap(int a, int b)
	{
		//swap with an extra variable
		//temporily variable to swap data
		String tmp;

		//swap lastname
		tmp=lastname[b];
		lastname[b]=lastname[a];
		lastname[a]=tmp;

		//swap firstname
		tmp=firstname[b];
		firstname[b]=firstname[a];
		firstname[a]=tmp;

		//swap gender
		tmp=gender[b];
		gender[b]=gender[a];
		gender[a]=tmp;

		//swap studentId
		tmp=studentId[b];
		studentId[b]=studentId[a];
		studentId[a]=tmp;

		//swap dob
		tmp=dob[b];
		dob[b]=dob[a];
		dob[a]=tmp;

		//swap courses
		tmp=courses[b];
		courses[b]=courses[a];
		courses[a]=tmp;

	}

	/**
	 * check the most matching last name
	 * if there are multiple, return only one
	 * @param name the name to be searched
	 * @return the matching last name's student entry
	 */
	private String binarySearch(String name)
	{
		//student entry to be stored
		String result="";

		//if already sorted, (unless function returns "")
		if(sorted)
		{
			int first=0;
			int last=lastname.length-1;

			//if first is equal to last, the index of name is equal to first or last 
			while(first != last)
			{
				int middle= (first+last)/2;

				//if name is lexicographically bigger
				if(name.compareTo(lastname[middle])>0)
				{
					//check upper part (range: middle+1 ~ last)
					first=middle+1;
				}
				// if name is lexicographically lesser or equal,
				else
				{
					//check bottom part (range: first ~ middle)
					last=middle;
				}
			}

			//it does not matter whether to use 'first' or 'last' to get the index since they are the same

			//check if the last name of the index contains the value given
			if(lastname[first].equalsIgnoreCase(name))
			{
				result= String.format("%s, %s, %s, %s, %s, %s\n", lastname[first], firstname[first],
						gender[first], studentId[first], dob[first], courses[first]);
			}
		}

		return result;
	}

}
