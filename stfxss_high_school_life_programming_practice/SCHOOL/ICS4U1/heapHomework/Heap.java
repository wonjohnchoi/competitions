package heapHomework;

import java.util.Comparator;

/**
 * Heap class to store information of homework and manipulate data in a heap data structure 
 * containing add, remove, sort methods
 * @author Wonjohn Choi, Megan Lee
 * @supervisor Mr. Reid
 * @course ICS4U
 * @date 2010/05/12
 */
public class Heap
{
	protected Homework root; //initial Homework (head)
	protected int len; //# of elements

	/**
	 * constructor
	 */
	public Heap()
	{
		root=null;
		len=0;
	}

	/**
	 * @return # of elements
	 */
	public int size()
	{
		return len;
	}

	/**
	 * check if it's empty 
	 * @return true if empty false if not empty
	 */
	public boolean isEmpty()
	{
		return root==null;
	}

	/**
	 * method to find the Homework at a given index position
	 * Index is given in the way:
	 * 			1
	 * 	  2 		  3
	 * 	4 	 5 	   6 	 7
	 * 8 9 10 11 12 13 14 15
	 *... 
	 *
	 *method first changes index given to binary form
	 *			1
	 * 	  10 		 11
	 * 100 	 101 110 	 111
	 * Then you might notice that there is some nice property of heap
	 * left child has extra zero and right child has extra one at the end compared to its parent
	 * It helps us to find where the Homework is located by tracking the 1's and 0's of its binary form
	 */
	public Homework getElementAt(int idx)
	{
		//convert number to a binary String
		String bin=Integer.toBinaryString(idx);
	
		//initial position is root
		Homework dest= root;
	
		//for each index (except first one, which is already counted by adding root),
		for(int i=1; i<bin.length();i++)
		{
			//if the character is '0',
			if(bin.charAt(i)=='0')
			{
				//move dest's reference to its left 
				dest=dest.leftChild;
			}
			//if the character is '1',
			else if(bin.charAt(i)=='1')
			{
				//move dest's reference to its right 
				dest=dest.rightChild;
	
			}
		}
	
		//return the Homework obtained from above algorithm
		return dest;
	}

	/**
	 * add an element at the end with a new Homework
	 * @param new Homework data given by user
	 */
	public void add(Homework newHomework)
	{
		//increase size of heap by one
		len++;

		//if root is null, heap does not have any element
		if(root==null)
		{
			//create new Homework at root using new data
			root=newHomework;
		}
		else
		{
			//in a heap tree, parent's index is half of its children
			//get the Homework at the new Homework's parent's position
			Homework theParent = getElementAt(len/2);

			//if the position is even
			if(len%2==0)
			{
				//add to left
				theParent.setLeft(newHomework);
			}
			//if the position is odd
			else if(len%2==1)
			{
				//add to right
				theParent.setRight(newHomework);
			}
		}

	}
	
	/**
	 * remove a Homework at the end
	 * @return the removed Homework object
	 */
	public Homework remove()
	{
		//remember 'len' variable indicates the index of the last node
		
		//value of the removed Homework
		Homework out=null;

		if(len!=0)
		{
			//if there is only one element
			if(len==1)
			{
				//take out the root and store it to the 'out' variable
				out=root;
				root=null;
			}
			else
			{
				//get the parent of the Homework 
				Homework theParent=getElementAt(len/2);

				//if there is even # of element,
				if(len%2==0)
				{
					//the Homework at left is stored to return
					out=theParent.getLeft();

					//left child of parent loses connection
					theParent.setLeft(null);
				}
				//if there is odd # of element,
				else if(len%2==1)
				{
					//the Homework at right Homework is stored to return
					out=theParent.getRight();

					//right child of the Homework loses connection
					theParent.setRight(null);
				}
			}

			//total # of element decreased
			len--;
		}
		return out;
	}

	/**
	 * remove a given Homework
	 * @param oldHomework a Homework given to remove
	 * @return the removed Homework object
	 */
	public Homework remove(Homework oldHomework)
	{
		Homework endHomework=getElementAt(len);
		
		//if the end Homework is not equal to the given Homework s,
		if(!endHomework.equals(oldHomework))
		{
			//exchange the given Homework and the Homework at end so that we only need to erase the end Homework
			swap(oldHomework, endHomework);
		}
		
		//calling remove() - the end Homework is removed as desired and it is returned at the same time
		return remove();
	}

	/**
	 * reverse the order of data
	 */
	public void reverse()
	{
		//create new heap to store data in reverse order
		Heap newHeap=new Heap();

		//move data
		for(int i=len;i>=1;i--)
		{
			Homework tmp=getElementAt(i);

			//take out the Homework's relations
			tmp.setLeft(null);
			tmp.setRight(null);

			newHeap.add(tmp);
		}

		//change the root of original heap to change it to a new heap
		root=newHeap.root;
	}

	/**
	 * begin heap sort algorithm using buildHeap(Comparator<Homework> cpt) and 
	 * heapify(Homework cur, Comparator<Homework> cpt)
	 * @param cpt a Comparator that helps computer determine how to sort by what standard
	 */
	public void heapSort(Comparator<Homework> cpt)
	{
		//heap to store sorted data
		Heap newHeap=new Heap();

		//change the random tree to a heap making the first Homework to have the highest value 
		//according to the Comparator
		buildHeap(cpt);

		//for each index from the end to the second
		for(int i=len;i>=1;i--)
		{
			//take out the root element who has the highest value
			Homework tmp=remove(root);

			//take out the Homework's relations
			tmp.setLeft(null);
			tmp.setRight(null);

			//add the highest value Homework to a new heap
			newHeap.add(tmp);

			//arrange order again
			heapify(root, cpt);
		}

		//replace the original root with the new root (new heap is stored)
		root=newHeap.root;

		//set the length as the original length
		len=newHeap.size();
	}

	/**
	 * change a tree to a heap using several calls of heapify(Comparator<Homework> cpt)
	 * @param cpt Comparator that tells how to sort it
	 */
	private void buildHeap(Comparator<Homework> cpt)
	{
		//from the index of last element's parent to the root index,
		for (int i = len / 2; i >= 1; i--)
		{
			heapify(getElementAt(i), cpt);
		}
	}

	/**
	 * It changes order of a triangle portion of a node in a tree 
	 * to make it closer to a heap
	 * If change happens, recursively it calls its child
	 * @param cur current Homework to check
	 * @param cpt Comparator that tells how to sort it
	 */
	private void heapify(Homework cur, Comparator<Homework> cpt)
	{
		if(!isEmpty())
		{
			//get left and right children
			Homework left=cur.getLeft();
			Homework right=cur.getRight();
			
			//set largest as current Homework (parent)
			Homework largest=cur;
			
			//check if change occurs
			boolean change=false;
	
			//if left child exists and left child is smaller, (we know order is wrong!- it should be ascending in default)
			if(left!=null && cpt.compare(left, largest)<0)
			{
				//change largest and check change occurs
				largest=left;
				change=true;
			}
			
			//if right child exists and right child is smaller, (we know order is wrong!)
			if(right!=null && cpt.compare(right, largest)<0)
			{
				//change largest and check change occurs
				largest=right;
				change=true;
			}
	
			//if change occurs,
			if(change)
			{
				//swap largest (child) and the current Homework
				swap(largest, cur);
				
				//call heapify to do same thing with the changed Homework
				heapify(largest, cpt);
			}
		}
	}



	/**
	 * method to swap two given Homeworks' variables except its relation to other Homework
	 * @param h1 first Homework to swap
	 * @param h2 second Homework to swap
	 */
	private void swap(Homework h1, Homework h2)
	{
		//variable to be used to store temporily
		String tmp;

		//swap name
		tmp=h1.name;
		h1.name=h2.name;
		h2.name=tmp;
		
		int tmp2;
		
		//swap starting time
		tmp2=h1.startingTime;
		h1.startingTime=h2.startingTime;
		h2.startingTime=tmp2;

		//swap due time
		tmp2=h1.dueTime;
		h1.dueTime=h2.dueTime;
		h2.dueTime=tmp2;

		//swap weight
		tmp2=h1.weight;
		h1.weight=h2.weight;
		h2.weight=tmp2;
	}

	/**
	 * reset every variable
	 * empty the heap
	 * @deprecated
	 */
	public void reset()
	{
		root=null;
		len=0;
	}

	/**
	 * method to find the height of heap 
	 * it simply takes log base 2 on the length and add one
	 * @return height (depth) of heap from the root
	 * @deprecated
	 */
	public int getHeight()
	{
		int height;
	
		//if there is no element,
		if(len==0)
		{
			//height is zero
			height=0;
		}
		else
		{
			//since it's binary tree, take log base 2 on the total length and add one to get the height
			//(int)(log_2 (len)) + 1
			//=(int)(log(len)/log2)+1
			height= (int)(Math.log10(len)/Math.log10(2))+1;
		}
	
		return height;
	}

	/**
	 * @deprecated
	 * @param idx
	 * @return
	 */
	public int getHeight(int idx)
	{
		int height=-1;
		//if index given is in 1~len
		if(1<=idx && idx<len+1)
		{
			height=getHeight()- (int)(Math.log10(idx)/Math.log10(2));			
		}
		return height;
	}



}
