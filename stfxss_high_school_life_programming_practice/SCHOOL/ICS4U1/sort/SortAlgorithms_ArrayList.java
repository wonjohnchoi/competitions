package sort;
import java.io.*;
import java.util.*;
public class SortAlgorithms_ArrayList
{
	public static void main(String args[]) throws IOException 
	{
		double time=System.currentTimeMillis();
		/*
		String fileName="sortInput.txt";
		Scanner sc=new Scanner(new FileReader(fileName));
		ArrayList<String> al=new ArrayList<String>();

		while(sc.hasNext())
		{
			al.add(sc.next());
		}
		
		sc.close();
		*/
		
		ArrayList<String> al=new ArrayList<String>();
		for(int i=0;i<10000;i++)
		{
			al.add(""+Math.random());
		}
		
		//Collections.sort(al); //0.17: 10000 | 1.31: 100000
		//selectionSort(al);  //9 
		bubbleSort(al);  //27
		//insertionSort(al); //5
		//al=mergeSort(al); //0.48: 10000 | ???:100000
		
		time=(System.currentTimeMillis()-time)/1000;
		/*
		for(int i=0;i<al.size();i++)
		{
			System.out.println(al.get(i));
		}*/
		System.out.println("Time consumed is "+time);
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
	public static void insertionSort(ArrayList<String> str)
	{
		//for each index (except the first one since one element is assumed to be sorted),
		for(int i=1;i<str.size();i++)
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
				if(str.get(insertIdx).compareTo(str.get(j))>=0)
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
				str.add(insertIdx, str.remove(i));				
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
	public static void bubbleSort(ArrayList<String> str)
	{
		//variable to decide whether to finish the loop
		boolean finished;
		
		//do this step
		do
		{
			finished=true;
			
			//for every index except the last one (since one index will be compared to the next index)
			for(int i=0;i<str.size()-1;i++)
			{
				//if previous String has higher value, 
				if(str.get(i).compareTo(str.get(i+1))>0)
				{
					//swap
					Collections.swap(str, i, i+1);
					
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
	public static void selectionSort(ArrayList<String> str)
	{
		//for every index,
		for(int i=0;i<str.size();i++) 
		{
			//set initial index as ith index
			int idx=i;
			
			//for the other index (after i)
			for(int j=i+1;j<str.size();j++)
			{
				//compare if there is any index with less value
				if(str.get(idx).compareTo(str.get(j))>0)
				{
					//update index as the index with less value
					idx=j;
				}
			}
			
			if(idx!=i) //if there was a change in index (something less was found),
			{
				//swap with an extra variable
				Collections.swap(str, i, idx);
			}
			
		
			
			
		}
	}
}
