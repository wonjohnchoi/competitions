package heapStudent;

import java.util.Comparator;

/**
 * A class that includes Comparator objects to help decide in which way to sort
 * @author Wonjohn Choi, Megan Lee
 * @supervisor Mr. Reid
 * @course ICS4U
 * @date 2010/05/12
 */
public class StudentComparator
{
	//by lastname
	protected Comparator<Student> byLastName = new Comparator<Student>() 
	{
		public int compare(Student s1, Student s2)
		{
			return s1.lastName.compareTo(s2.lastName);
		}
	};
	
	//by firstname
	protected Comparator<Student> byFirstName = new Comparator<Student>() 
	{
		public int compare(Student s1, Student s2)
		{
			return s1.firstName.compareTo(s2.firstName);
		}
	};

	//by studentId
	protected Comparator<Student> byStudentId = new Comparator<Student>() 
	{
		public int compare(Student s1, Student s2)
		{
			return s1.studentId-s2.studentId;
		}
	};
	
	//by date of birth
	protected Comparator<Student> byDob = new Comparator<Student>() 
	{
		public int compare(Student s1, Student s2)
		{
			return s1.dob-s2.dob;
		}
	};
}
