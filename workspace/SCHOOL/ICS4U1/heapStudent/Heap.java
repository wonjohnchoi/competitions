package heapStudent;

import java.util.Comparator;

/**
 * Heap class to store information of Students and manipulate data in a heap data structure 
 * containing add, remove, sort methods
 * @author Wonjohn Choi, Megan Lee
 * @supervisor Mr. Reid
 * @course ICS4U
 * @date 2010/05/12
 */
public class Heap
{
	protected Student root; //initial Student (head)
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
	 * add an element at the end with a new Student
	 * @param new Student data given by user
	 */
	public void add(Student newStudent)
	{
		//increase size of heap by one
		len++;

		//if root is null, heap does not have any element
		if(root==null)
		{
			//create new Student at root using new data
			root=newStudent;
		}
		else
		{
			//in a heap tree, parent's index is half of its children
			//get the Student at the new Student's parent's position
			Student theParent = getStudentAt(len/2);

			//create the new Student with the data given 
			Student newChild=newStudent;

			//if the position is even
			if(len%2==0)
			{
				//add to left
				theParent.setLeft(newChild);
			}
			//if the position is odd
			else if(len%2==1)
			{
				//add to right
				theParent.setRight(newChild);
			}
		}

	}
	
	/**
	 * remove a given Student
	 * @param s a Student given
	 * @return the removed Student object
	 */
	public Student remove(Student s)
	{
		Student endStudent=getStudentAt(len);
		
		//if the end student is not equal to the given student s,
		if(!endStudent.equals(s))
		{
			//exchange the given Student and the Student at end so that we only need to erase the end Student
			swap(s, getStudentAt(len));
		}
		
		//calling remove() - the end Student is removed as desired and the Student is returned.
		return remove();
	}

	/**
	 * remove a Student at the end
	 * @return the removed Student object
	 */
	public Student remove()
	{
		//value of the removed Student
		Student outStudent=null;

		if(len!=0)
		{
			//if there is only one element
			if(len==1)
			{
				//take out the root
				outStudent=root;
				root=null;
			}
			else
			{
				//get the parent of the Student 
				Student theParent=getStudentAt(len/2);

				//if there is even # of element,
				if(len%2==0)
				{
					//the student at left is stored to return
					outStudent=theParent.getLeft();

					//left child of parent loses connection
					theParent.setLeft(null);
				}
				//if there is odd # of element,
				else if(len%2==1)
				{
					//the student at right Student is stored to return
					outStudent=theParent.getRight();

					//right child of the Student loses connection
					theParent.setRight(null);
				}
			}

			//total # of element decreased
			len--;
		}
		return outStudent;
	}

	/**
	 * method to find the Student at a given index position
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
	 * It helps us to find where the Student is located by tracking the 1's and 0's of its binary form
	 */
	public Student getStudentAt(int idx)
	{
		//convert number to a binary String
		String bin=Integer.toBinaryString(idx);

		//initial position is root
		Student dest= root;

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

		//return the Student obtained from above algorithm
		return dest;
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
			Student tmp=getStudentAt(i);

			//take out the student's relations
			tmp.setLeft(null);
			tmp.setRight(null);

			newHeap.add(tmp);
		}

		//change the root of original heap to change it to a new heap
		root=newHeap.root;
	}

	/**
	 * begin heap sort algorithm using buildHeap(boolean isMaxHeap) and heapify(Student n, boolean isMaxHeap)
	 * @param cpt a Comparator that helps computer determine how to sort by what standard
	 */
	public void heapSort(Comparator<Student> cpt)
	{
		//heap to store sorted data
		Heap newHeap=new Heap();

		//change the random tree to a heap making the first student to get the highest value
		buildHeap(cpt);

		//for each index from the end to the second
		for(int i=len;i>=2;i--)
		{
			//swap the information of the first student, which has the highest value with the last student
			swap(getStudentAt(1), getStudentAt(i));

			//take out the last student who has the highest value
			Student tmp=remove();

			//take out the student's relations
			tmp.setLeft(null);
			tmp.setRight(null);

			//add the highest student to a new heap
			newHeap.add(tmp);

			//create a heap using the left element
			heapify(root, cpt);
		}

		//finally add root student to the new heap
		newHeap.add(root);

		//replace the original root with the new root (new heap is stored)
		root=newHeap.root;

		//set the length as the original length
		len=newHeap.size();
	}

	/**
	 * change a tree to a heap using several calls of heapify
	 * @param cpt Comparator that tells how to sort it
	 */
	private void buildHeap(Comparator<Student> cpt)
	{
		//from the index of last element's parent to the root index,
		for (int i = len / 2; i >= 1; i--)
		{
			heapify(getStudentAt(i), cpt);
		}
	}

	/**
	 * It changes order of a triangle portion of a node in a tree 
	 * to make it closer to a heap
	 * If change happens, recursively it calls its child
	 * @param cur current Student to check
	 * @param cpt Comparator that tells how to sort it
	 */
	private void heapify(Student cur, Comparator<Student> cpt)
	{
		//get left and right children
		Student left=cur.getLeft();
		Student right=cur.getRight();
		
		//set largest as current Student (parent)
		Student largest=cur;
		
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
			//swap largest (child) and the current Student
			swap(largest, cur);
			
			//call heapify to do same thing with the changed Student
			heapify(largest, cpt);
		}
	}



	/**
	 * method to swap two given Students' variables except its relation to other Student
	 * @param s1 first Student to swap
	 * @param s2 second Student to swap
	 */
	private void swap(Student s1, Student s2)
	{
		//variable to be used to store temporily
		String tmp;

		//swap lastname
		tmp=s1.lastName;
		s1.lastName=s2.lastName;
		s2.lastName=tmp;

		//swap firstname
		tmp=s1.firstName;
		s1.firstName=s2.firstName;
		s2.firstName=tmp;

		//swap courses
		tmp=s1.courses;
		s1.courses=s2.courses;
		s2.courses=tmp;

		//swap gender
		tmp=s1.gender;
		s1.gender=s2.gender;
		s2.gender=tmp;

		int tmp2;

		//swap stuident id
		tmp2=s1.studentId;
		s1.studentId=s2.studentId;
		s2.studentId=tmp2;

		//swap date of birth
		tmp2=s1.dob;
		s1.dob=s2.dob;
		s2.dob=tmp2;
	}

	/**
	 * check if it's empty 
	 * @return true if empty false if not empty
	 * @deprecated
	 */
	public boolean isEmpty()
	{
		return root==null;
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
