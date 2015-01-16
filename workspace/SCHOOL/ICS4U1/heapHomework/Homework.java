package heapHomework;
/**
 * Homework class that stores information of a homework entry
 * @author Wonjohn Choi, Megan Lee
 * @supervisor Mr. Reid
 * @course ICS4U
 * @date 2010/05/12
 */
public class Homework 
{
	protected Homework leftChild;
	protected Homework rightChild;

	protected String name;
	protected int startingTime;
	protected int dueTime;
	protected int weight;

	/**
	 * constructor
	 * @param newName name
	 * @param sDate startingDate
	 * @param eDate dueDate
	 * @param newWeight weight
	 */
	public Homework(String newName, int start, int due, int newWeight )
	{
		leftChild=null;
		rightChild=null;

		name=newName;
		startingTime=start;
		dueTime=due;
		weight=newWeight;
	}

	/**
	 * setter method for leftChild 
	 * @param left left sided student
	 */
	public void setLeft(Homework left)
	{
		leftChild=left;
	}

	/**
	 * getter method for leftChild
	 * @return left left sided student
	 */
	public Homework getLeft()
	{
		return leftChild;
	}

	/**
	 * setter method for rightChild
	 * @param right right sided student
	 */
	public void setRight(Homework right)
	{
		rightChild=right;
	}

	/**
	 * getter method for rightChild
	 * @return right sided student
	 */
	public Homework getRight()
	{
		return rightChild;
	}

	/**
	 * String representation of a student
	 */
	public String toString()
	{
		return String.format("%s//%d//%d//%d", name, startingTime, dueTime, weight);
	}
}
