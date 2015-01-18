package heapStudent;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * StudentDataBase that manipulates Student database using the heap structure created at other class.
 * @author Wonjohn Choi, Megan Lee
 * @supervisor Mr. Reid
 * @course ICS4U
 * @date 2010/05/12
 */
public class StudentDataBase
{
	protected Heap studentData=new Heap();
	
	/**
	 * a method to read data from a file
	 * @param filename name of file to read data
	 * @throws IOException occurs when file does not exist
	 */
	public void readStudentDataBase(String filename) throws IOException
	{
		//Scanner object sc to read over file
		Scanner sc=new Scanner(new FileReader(filename));

		//if extra line exists,
		while(sc.hasNextLine())
		{
			//add the line as a student
			addAStudent(sc.nextLine());
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
		for(int i=1; i<=studentData.size(); i++)
		{
			//write to file with the proper format (the same format when reading file)
			pw.println(studentData.getStudentAt(i));
		}

		//indicate finished using PrintWriter object pw
		pw.close();
	}
	
	/**
	 * add a new student from user screen
	 * @param input a line that describes a Student (the format of database)
	 */
	public void addAStudent(String input)
	{
		//get input and split using ','
		StringTokenizer st= new StringTokenizer(input, ",");

		if(st.countTokens()==6)
		{
			//store the data
			studentData.add(new Student(st.nextToken(), st.nextToken(), st.nextToken(), 
					new Integer(st.nextToken()), new Integer(st.nextToken()), st.nextToken()));
		}
		else
		{
			System.err.println("Input form is not correct: "+st.countTokens()+" elements per line");
		}
	}
	
	/**
	 * eliminate a student from database
	 * @param student student to be removed
	 */
	public void removeAStudent(Student student)
	{
		studentData.remove(student);
	}
	
	/**
	 * sort by last name
	 */
	public void sortByLastName()
	{
		studentData.heapSort(new StudentComparator().byLastName);
	}
	
	/**
	 * sort by first name
	 */
	public void sortByFirstName()
	{
		studentData.heapSort(new StudentComparator().byFirstName);
	}
	
	/**
	 * sort by student id
	 */
	public void sortByStudentId()
	{
		studentData.heapSort(new StudentComparator().byStudentId);
	}
	
	/**
	 * sort by date of birth
	 */
	public void sortByDob()
	{
		studentData.heapSort(new StudentComparator().byDob);
	}
	
	/**
	 * sort in reverse order
	 */
	public void sortInReverse()
	{
		studentData.reverse();
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
		for(int i=1; i<=studentData.size();i++)
		{
			//add each line
			students+=studentData.getStudentAt(i)+"\n";
		}

		return students;
	}
	
	/**
	 * method to search a Student
	 * find only one entry 
	 * @param value that user requires to find
	 * @return the found Student. null if not exist
	 */
	public Student search(String value)
	{
		//store the matched student value
		Student matchedStudent = null;

		//check the other arrays with a linear search
		//for each index (use lastname array to check the size)
		for(int i=1;i<=studentData.size() && matchedStudent==null;i++)
		{
			Student tmp=studentData.getStudentAt(i);
			//check if each String from different arrays contains the value user wants to find
			if(tmp.lastName.equalsIgnoreCase(value) || tmp.firstName.equalsIgnoreCase(value) 
					|| (""+tmp.studentId).equalsIgnoreCase(value)|| (""+tmp.dob).equalsIgnoreCase(value))
			{
				//add the String representation 
				matchedStudent = tmp;
			}
			//check the course by spliting them
			else
			{
				String course[]=tmp.courses.split(" ");

				//for each idx 
				for(int j=0;j<course.length && matchedStudent==null;j++)
				{
					//check if the course code matches
					if(course[j].equalsIgnoreCase(value))
					{
						//add the String representation
						matchedStudent = tmp;
					}
				}
			}
		}

		return matchedStudent;

	}
	
	/**
	 * method to calculate the number of students who take the given course
	 * @param course that students take
	 * @return # of students who take the given course
	 */
	public int countStudentsByCourse(String course)
	{
		//variable to store the number of students who take the given course
		int numStudentsByCourse=0;
	
		//for each index,
		for(int i=1;i<=studentData.size();i++)
		{
	
			//done variable will check whether searching a student finishes
			boolean found=false;
			
			//get each courses a student has
			StringTokenizer st=new StringTokenizer(studentData.getStudentAt(i).courses, " ");
			
			int n=st.countTokens();
			
			//for each index,
			for(int j=0;j<n && !found;j++)
			{
				//check if the course is same to the given course
				if(st.nextToken().equalsIgnoreCase(course))
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
	 * method to calculate the number of the female students
	 * @return # of female students
	 */
	public int countFemaleStudents()
	{
		//variable to store the number of female students
		int numFemale=0;
	
		//for each index,
		for(int i=1;i<=studentData.size();i++)
		{
			//check if the gener is female
			if(studentData.getStudentAt(i).gender.equalsIgnoreCase("F"))
			{
				numFemale++;
			}
		}
	
		return numFemale;
	}

	/**
	 * method to calculate the number of the male students
	 * @return # of female students
	 */
	public int countMaleStudents()
	{
		//variable to store the number of male students
		int numMale=0;
	
		//for each index,
		for(int i=1;i<=studentData.size();i++)
		{
			//check if the gener is male
			if(studentData.getStudentAt(i).gender.equalsIgnoreCase("M"))
			{
				numMale++;
			}
		}
	
		return numMale;
	}

	public int countTotalStudents()
	{
		return studentData.size();
	}

}
