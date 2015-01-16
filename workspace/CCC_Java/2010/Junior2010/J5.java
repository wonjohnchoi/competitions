package Junior2010;

import java.util.*;

public class J5 {
	public static void main(String args[])
	{
		//Scanner will process the Input
		Scanner sc=new Scanner(System.in);
		
		//Instantiate object Queue, which uses FIFO (First-in-First-Out) to deal with items
		//*Item is an object that stores xPosition, yPosition, and its steps to the destination
		Queue<Item> q=new LinkedList<Item>();
		
		//Declare four variables to indicate starting point and destination
		int xStart, yStart, xDest, yDest;
		
		//get input from user
		xStart=sc.nextInt();
		yStart=sc.nextInt();
		xDest=sc.nextInt();
		yDest=sc.nextInt();
		
		//start by adding an item that contains information of starting point and step of 0.
		q.add(new Item(xStart, yStart,0));
		
		//boolean variable done will be used to finish the following while loop when needed.
		boolean done=false;
		
		//if done is false, continue this loop
		while(!done)
		{
			//eliminate the head of the queue and store it to cur (current item)
			Item cur=q.poll();
			
			//declare x,y, step to show current item's x coordinate, y coordinate, steps.
			int x,y, step;
			
			//initialize
			x=cur.xPos;
			y=cur.yPos;
			step=cur.steps;
			
			//unless current item contains xPosition or yPosition that is not on the chess board (1~8),
			//go through this if loop
			if(1<=x && x<=8 && 1<=y && y<=8)
			{
				//if current item has position that is destination,  continue
				if(x==xDest && y==yDest)
				{
					//since the item reached its goal, done is true = finished!
					done=true;
					//output the answer, which is the step of the current item
					System.out.println(step);
				}
				
				//if current item did not reach the destination,
				else
				{				
					//update new positions and their step is one bigger than the current step 
					q.add(new Item(x-1, y+2, step+1));
					q.add(new Item(x-2, y+1, step+1));
					q.add(new Item(x-1, y-2, step+1));
					q.add(new Item(x-2, y-1, step+1));
					q.add(new Item(x+1, y+2, step+1));
					q.add(new Item(x+2, y+1, step+1));
					q.add(new Item(x+1, y-2, step+1));
					q.add(new Item(x+2, y-1, step+1));
				}
			}
			
		}
	}
	
	
}

/*
 * create an object Item to store the values of xPosition, yPostion, and its step.
 */
class Item
{
	//xPos, yPos, steps store the information of this object
	int xPos, yPos, steps;
	
	//Constructor
	Item(int x, int y, int c)
	{
		//get the xPos, yPos, steps.
		xPos=x;
		yPos=y;
		steps=c;
	}
}