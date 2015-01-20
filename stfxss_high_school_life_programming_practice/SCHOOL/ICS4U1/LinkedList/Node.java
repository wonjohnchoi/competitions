package LinkedList;

public class Node<T>
{
	protected T val = null;
	protected Node<T> next = null;
	
	/**
	 * constructor to define the next node and the current value
	 * @param newVal
	 * @param newNext
	 */
	public Node(T newVal, Node<T> newNext)
	{
		val=newVal;
		next=newNext;
	}
	
	/**
	 * gives the value of the current node
	 * @return the current value
	 */
	public T getValue()
	{
		return val;
	}
	
	/**
	 * gives the next connected node
	 * @return the next node
	 */
	public Node<T> getNext()
	{
		return next;
	}

	/**
	 * replace the next node with the updated node
	 * @param newNext a new node to replace the current next
	 */
	public void setNext(Node<T> newNext)
	{
		next=newNext;
	}

	
}
