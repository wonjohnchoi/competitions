package heapStudent;
/**
 * Student class that stores information of a Student
 * @author Wonjohn Choi, Megan Lee
 * @supervisor Mr. Reid
 * @course ICS4U
 * @date 2010/05/12
 */
public class Student 
{
	protected Student leftChild;
	protected Student rightChild;

	protected String lastName;
	protected String firstName;
	protected String gender;
	protected int studentId;
	protected int dob;
	protected String courses;

	/**
	 * constructor
	 * @param last lastname
	 * @param first firstname
	 * @param gen gender
	 * @param studentNum studentId
	 * @param newDob data of birth
	 * @param newCourses courses to take
	 */
	public Student(String last, String first, String gen, int studentNum, int newDob, String newCourses )
	{
		leftChild=null;
		rightChild=null;

		lastName=last;
		firstName=first;
		gender=gen;
		studentId=studentNum;
		dob=newDob;
		courses=newCourses;
	}

	/**
	 * setter method for leftChild 
	 * @param left left sided student
	 */
	public void setLeft(Student left)
	{
		leftChild=left;
	}

	/**
	 * getter method for leftChild
	 * @return left left sided student
	 */
	public Student getLeft()
	{
		return leftChild;
	}

	/**
	 * setter method for rightChild
	 * @param right right sided student
	 */
	public void setRight(Student right)
	{
		rightChild=right;
	}

	/**
	 * getter method for rightChild
	 * @return right sided student
	 */
	public Student getRight()
	{
		return rightChild;
	}

	/**
	 * String representation of a student
	 */
	public String toString()
	{
		return String.format("%s,%s,%s,%d,%d,%s", lastName, firstName, gender, studentId, dob, courses);
	}
}
