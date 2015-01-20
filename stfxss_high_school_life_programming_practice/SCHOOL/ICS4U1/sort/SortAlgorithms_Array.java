package sort;
import java.io.*;
import java.util.*;
public class SortAlgorithms_Array
{
	public static void main(String args[]) throws IOException 
	{
		String fileName="sortInput.txt";
		Scanner sc;
		int countStr=0;
		           
		sc=new Scanner(new FileReader(fileName));
		ArrayList<String> list=new ArrayList<String>();

		while(sc.hasNext())
		{
			list.add(sc.next());
			countStr++;
		}
		
		sc.close();
		
		String str[]=new String[countStr];
		sc=new Scanner(new FileReader(fileName));
		
		for(int i=0;i<str.length;i++)
		{
			str[i]=sc.next();
		}
		
		selectionSort(str);
		//bubbleSort(str);
		//insertionSort(str);
		
		//merge
		list=mergeSort(list);
		
		for(int i=0;i<str.length;i++)
		{
			System.out.println(str[i]+" "+list.get(i));
		}
		
	}
	
	public static void quickSort(String [] str)
	{
		
	}
	
	public static ArrayList<String> mergeSort(ArrayList<String> str)
	{
		if(str.size()<=1)
		{
			return str;
		}
		
		int mid=str.size()/2;
		
		
		ArrayList<String> left = new ArrayList<String>();
		ArrayList<String> right = new ArrayList<String>();
		ArrayList<String> result;
		
		
		
		for(int i=0;i<mid;i++)
		{
			left.add(str.remove(i));
		}
		
		right.addAll(str);
		
		left=mergeSort(left);
		right=mergeSort(right);
		//str.clear();
		
		if(left.get(mid-1).compareTo(right.get(0))>0)
		{
			result=merge(left, right);
		}
		else
		{
			left.addAll(right);
			result=left;
		}
		return result;
		
	}
	
	private static ArrayList<String> merge(ArrayList<String> left, ArrayList<String> right)
	{
		ArrayList<String> result=new ArrayList<String>();
		while(left.size()>0 && right.size()>0)
		{
			String leftStr=left.get(0);
			String rightStr=right.get(0);
			if(leftStr.compareTo(rightStr)<0)
			{
				result.add(leftStr);
				left.remove(0);
			}
			else
			{
				result.add(rightStr);
				right.remove(0);
			}
		}
		
		if(left.size()>0)
		{
			result.addAll(left);
		}
		else
		{
			result.addAll(right);
		}
		
		return result;
	}

	/**
	 * sort via insertion sort algorithm
	 * 2314
	 * 2314
	 * 1234
	 * 1234
	 * @param str String array to be sorted
	 */
	public static void insertionSort(String [] str)
	{
		//for each index (except the first one since one element is assumed to be sorted),
		for(int i=1;i<str.length;i++)
		{
			//variable to check if insertion finished
			boolean detected=false;
			
			//insertion point to be found
			int insertIdx=i;
			
			//check index before i in order. If insertion point is found, stop
			for(int j=i-1;j>=0 && !detected;j--)
			{
				//1234564
				//3<4
				//
				//if stored String is bigger than the current index's String,
				if(str[insertIdx].compareTo(str[j])>=0)
				{
					insertIdx=j+1;
					detected=true;
				}
			}
			
			//if nothing is detected, it means insertion should be made at the first index
			if(!detected)
			{
				insertIdx=0;
			}
			
			//if insert index is not i itself (no change),
			if(insertIdx!=i)
			{
				//store ith String
				String tmp=str[i]; 
				
				//copy part of array from inserIdx to insertIdx+1 to get the empty spot to put tmp
				System.arraycopy(str, insertIdx, str, insertIdx+1, i-insertIdx);
				
				//insert the stored tmp String
				str[insertIdx]=tmp;
			}
			
		}
	}
	
	/**
	 * sort via bubble sort algorithm
	 * 2314
	 * 3124
	 * 1234
	 * @param str String array to be sorted
	 */
	public static void bubbleSort(String [] str)
	{
		//variable to decide whether to finish the loop
		boolean finished;
		
		//do this step
		do
		{
			finished=true;
			
			//for every index except the last one (since one index will be compared to the next index)
			for(int i=0;i<str.length-1;i++)
			{
				//if previous String has higher value, 
				if(str[i].compareTo(str[i+1])>0)
				{
					//swap
					String tmp=str[i];
					str[i]=str[i+1];
					str[i+1]=tmp;
					
					//indicate it as not finished
					finished=false;
				}
			}
		}while(!finished); //while not finished,
	}
	
	/**
	 * sort via selection sort algorithm
	 * 2314
	 * 1324
	 * 1234
	 * 1234
	 * @param str String array to be sorted
	 */
	public static void selectionSort(String [] str)
	{
		//for every index,
		for(int i=0;i<str.length;i++) 
		{
			//set initial index as ith index
			int idx=i;
			
			//for the other index (after i)
			for(int j=i+1;j<str.length;j++)
			{
				//compare if there is any index with less value
				if(str[idx].compareTo(str[j])>0)
				{
					//update index as the index with less value
					idx=j;
				}
			}
			
			if(idx!=i) //if there was a change in index (something less was found),
			{
				//swap with an extra variable
				String tmp=str[i];
				str[i]=str[idx];
				str[idx]=tmp;
			}
			
		
			
			
		}
	}
}
