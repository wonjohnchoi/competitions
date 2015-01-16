package heapHomework;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * HW scheduling class that uses the heap data structure to store information of homework entries
 * @author Wonjohn Choi, Megan Lee
 * @supervisor Mr. Reid
 * @course ICS4U
 * @date 2010/05/12
 */
public class HWSchedule {
	protected Heap homeworkData=new Heap();

	/**
	 * a method to read data from a file
	 * @param filename name of file to read data
	 * @throws IOException occurs when file does not exist
	 */
	public void readHomeworkFile(String filename) throws IOException
	{
		//Scanner object sc to read over file
		Scanner sc=new Scanner(new FileReader(filename));

		//if extra line exists,
		while(sc.hasNextLine())
		{
			//add the line as a homework
			addAnEntry(sc.nextLine());
		}

		sc.close();
	}

	/**
	 *  a method to save data into a file
	 * @param filename name of file where data will be saved
	 * @throws IOException occurs when file does not exist
	 */
	public void saveHomeworkData(String filename) throws IOException
	{
		//PrintWriter Object pw to write to a file
		PrintWriter pw=new PrintWriter(new FileWriter(filename));

		//for each index,
		for(int i=1; i<=homeworkData.size(); i++)
		{
			//write to file the entry at each index
			pw.println(homeworkData.getElementAt(i));
		}

		pw.close();
	}

	/**
	 * add a new Homework from user screen
	 * @param input a line that describes a Homework (the format of database)
	 */
	public void addAnEntry(String input)
	{
		//get input and split using '//'
		StringTokenizer st= new StringTokenizer(input, "//");

		if(st.countTokens()==4)
		{
			//store the data
			homeworkData.add(new Homework(st.nextToken(), new Integer(st.nextToken()), new Integer(st.nextToken()), new Integer(st.nextToken())));
		}
		else
		{
			System.err.println("Input form is not correct: "+st.countTokens()+" elements.");
		}
	}

	/**
	 * eliminate a Homework from database
	 * @param Homework Homework to be removed
	 */
	public void removeAnEntry(Homework hw)
	{
		homeworkData.remove(hw);
	}

	/**
	 * sort by name
	 */
	public void sortByName()
	{
		homeworkData.heapSort(new HWComparator().byName);
	}

	/**
	 * sort by starting time
	 */
	public void sortByStartingTime()
	{
		homeworkData.heapSort(new HWComparator().byStartingTime);
	}

	/**
	 * sort by due time
	 */
	public void sortByDueTime()
	{
		homeworkData.heapSort(new HWComparator().byDueTime);
	}

	/**
	 * sort by weight
	 */
	public void sortByWeight()
	{
		homeworkData.heapSort(new HWComparator().byWeight);
	}

	/**
	 * sort in reverse order
	 */
	public void sortReverse()
	{
		homeworkData.reverse();
	}

	/**
	 * method to get the whole String of homework
	 * @return display of homework entries (String representation)
	 */
	public String displayEntries()
	{
		//variable to store
		String entries="";

		//for each index,
		for(int i=1; i<=homeworkData.size();i++)
		{
			//add each line
			entries+=homeworkData.getElementAt(i)+"\n";
		}

		return entries;
	}

	/**
	 * method to search an entry by name
	 * find only one entry 
	 * @param value that user requires to find
	 * @return the found homework.. null if not exist
	 */
	public Homework search(String value)
	{
		//store the matched homework value
		Homework matchedHW = null;

		//check the other arrays with a linear search
		//for each index (use lastname array to check the size)
		for(int i=1;i<=homeworkData.size() && matchedHW==null;i++)
		{
			Homework tmp=homeworkData.getElementAt(i);
			
			//check if each String from different arrays contains the value user wants to find
			if(tmp.name.equalsIgnoreCase(value))
			{
				//add the String representation 
				matchedHW = tmp;
			}
		}

		return matchedHW;

	}
	
	public int countTotalEntries()
	{
		return homeworkData.size();
	}
}
