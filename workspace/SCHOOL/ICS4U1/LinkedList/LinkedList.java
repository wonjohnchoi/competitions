package LinkedList;


public class LinkedList<T>
{
	protected Node<T> front;
	protected int len;

	/**
	 * constructor to define the 'front' (top of the list) and 'len' (size of the list) 
	 */
	public LinkedList()
	{
		front = null;
		len = 0;
	}

	/**
	 * it checks whether the list is empty by checking its front
	 * @return true if empty or false if not empty
	 */
	public boolean isEmpty()
	{
		return front == null;
	}

	/**
	 * gives the number of elements in the list
	 * @return the number of elements
	 */
	public int size()
	{
		return len;
	}

	/**
	 * insert new data at the top of the list
	 * @param newData data to be inserted 
	 */
	public void add_Front(T newData)
	{
		//front is a node that contains the new data AND the connected node
		//newData is the next value AND front is the connected node
		front = new Node<T>(newData, front);

		//increment the number of elements by one
		len++;
	}

	/**
	 * remove a node from the top
	 * @return the value of removed Node. If no elements exist, return null.
	 */
	public T remove_Front()
	{
		//data to return
		T val;

		//if it's empty,
		if(isEmpty())
		{
			//the value is null 
			val=null;
		}
		//if it's not empty,
		else
		{
			//the value is front's value
			val=front.getValue();

			//replace the front with its next connected node so that the current node does not exist anymore.
			front = front.getNext();

			//decrease the number of elements by one
			len--;
		}

		return val;
	}

	/**
	 * 
	 * @param newData
	 */
	public void add_End(T newData)
	{
		if(isEmpty())
		{
			add_Front(newData);
		}
		else
		{
			Node<T> end = front;
			while(end.getNext() != null)
			{
				end=end.getNext();
			}

			end.setNext(new Node<T>(newData, null));
			len++;
		}
	}


	public T remove_End()
	{
		T last;

		if(isEmpty())
		{
			last = null;
		}
		else
		{
			if(front.getNext() == null)
			{
				last = remove_Front();
			}
			else
			{
				Node<T> secLast = front;
				while(secLast.getNext().getNext() != null)
				{
					secLast = secLast.getNext();
				}

				last = secLast.getNext().getValue();
				secLast.setNext(null);
				len--;
			}
		}

		return last;
	}

	public void add(T newData, int index)
	{
		if(index == 0)
		{
			add_Front(newData);
		}
		else if(index == len)
		{
			add_End(newData);
		}
		else if(inRange(index))
		{

			Node<T> beforeIdx = front;
			for(int i = 0;i<index-1;i++)
			{
				beforeIdx = beforeIdx.getNext();
			}

			Node<T> tmp=new Node<T>(newData, beforeIdx.getNext());

			beforeIdx.setNext(tmp);
			len++;	

		}
	}

	public T remove(int index)
	{
		T val = null;

		if(index == 0)
		{
			val = remove_Front();
		}
		else if(index == len-1 && len>=1)
		{
			val = remove_End();
		}
		else if(inRange(index))
		{
			if(isEmpty())
			{
				val = null;
			}
			else
			{
				Node<T> beforeIdx = front;
				for(int i = 0;i<index-1;i++)
				{
					beforeIdx = beforeIdx.getNext();
				}

				val=beforeIdx.getNext().getValue();


				beforeIdx.setNext(beforeIdx.getNext().getNext());
				len--;
			}
		}


		return val;
	}

	/**
	 * Get the 'index'th element's value
	 * @param index
	 * @return
	 */
	public T peek(int index){
		Node<T> cur = null;
		if(inRange(index))
		{
			cur=front;
			for(int i = 0;i<index;i++)
			{
				cur = cur.getNext();
			}
		}
		return cur.getValue();
	}

	private boolean inRange(int idx)
	{
		boolean inRange=true;

		if(idx<0 || idx>=len)
		{
			System.err.println("Out of Bound Error at "+idx);
			inRange=false;
		}

		return inRange;
	}
}
