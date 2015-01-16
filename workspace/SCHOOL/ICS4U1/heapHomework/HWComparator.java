package heapHomework;

import java.util.Comparator;

/**
 * A class that includes Comparator objects to help decide in which way to sort
 * @author Wonjohn Choi, Megan Lee
 * @supervisor Mr. Reid
 * @course ICS4U
 * @date 2010/05/12
 */
public class HWComparator
{
	//by name
	protected Comparator<Homework> byName = new Comparator<Homework>() 
	{
		public int compare(Homework h1, Homework h2)
		{
			return h1.name.compareTo(h2.name);
		}
	};
	
	//by due time
	protected Comparator<Homework> byDueTime = new Comparator<Homework>() 
	{
		public int compare(Homework h1, Homework h2)
		{
			return h1.dueTime-h2.dueTime;
		}
	};
	
	//by starting time
	protected Comparator<Homework> byStartingTime = new Comparator<Homework>() 
	{
		public int compare(Homework h1, Homework h2)
		{
			return h1.startingTime-h2.startingTime;
		}
	};

	
	//by weight
	protected Comparator<Homework> byWeight = new Comparator<Homework>() 
	{
		public int compare(Homework h1, Homework h2)
		{
			return h1.weight-h2.weight;
		}
	};
}
