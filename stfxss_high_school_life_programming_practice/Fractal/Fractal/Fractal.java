import hsa.Console;
import java.awt.Color;
import java.util.Random;

/**
 * A simple class that draws and paints different structures
 * using recursions. It tries to imitate the property of fractals
 * @author Wonjohn Choi
 * @date April 2010
 */
public class Fractal
{
	//------------------------declare and initialize global variables---------------------------------
	//Console object 'csl' with 70 rows, 405 columns,5 font sizes, and a title of "Beautiful Fractals" 
	protected static Console csl=new Console(70, 405, 5, "Beautiful Fractals");
	
	//Constants to easily use direction without any confusion
	protected static int NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
	
	//Constant to control time between steps
	protected static long timeInterval = 10;
	
	//Random type object 'rand' to generate random numbers for random colours
	protected static Random rand = new Random();
	//----------------------------------------------------------------------------------------------------
	
	/**
	 * main method
	 */
	public static void main(String args[]) throws InterruptedException
	{
		drawDiamondUsingCircles(600, 320, 100);
		//drawSquaresInSquares(600, 320, 500, EAST);
		//drawSquareSpiral(350, 30, 600, EAST);
	}
	
	/**
	 * draw a square shape using continuation of lines that curves clockwise like a spiral
	 * @param x x coordinate of center of the current line's starting point
	 * @param y y coordinate of center of the current line's starting point
	 * @param length length of the current line
	 * @param direction direction of the current line (NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3)
	 * @throws InterruptedException
	 */
	public static void drawSquareSpiral(int x, int y, int length, int direction) throws InterruptedException
	{
		//declare and initialize width
		//width indicates the interval that the length of each line decrease
		//it is changeable by user
		int width = 5;
		
		//Direction should be either 0, or, 1, or, 2, or 3
		//NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3
		direction%=4;

		//newX & newY will remember the updated location of starting point of a new line
		int newX = x;
		int newY = y;

		//adjust newX or newY depends on the direction of the current line's direction
		if(direction==NORTH)
		{
			newY-=length;
		}
		else if(direction==SOUTH)
		{
			newY+=length;
		}
		else if(direction==EAST)
		{
			newX+=length;
		}
		else if(direction==WEST)
		{
			newX-=length;
		}

		//base case: if length >1, keep going
		if(length>1)
		{
			//stop for a while (timeInterval: global constant)
			Thread.sleep(timeInterval);

			//change the color of Console 'csl' to a randomly selective Color using Random variable 'rand'
			csl.setColor(Color.getHSBColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
			
			//draw line from (x,y) to (newX, newY)
			csl.drawLine(x, y, newX, newY);
			
			//fill a rectangular portion from (x,y) to (x+ max(newX-x, width), y+ max(newY-y, width))
			csl.fillRect(x, y, Math.max(newX-x, width), Math.max(newY-y, width));

			//recursively call for the next line (next line's length decreases by width and direction increases by one)
			//In this system, direction is determined by (0, 1, 2, 3) so increment direction by one causes the line
			//to turn clockwise.
			drawSquareSpiral(newX, newY, length - width, direction+1);
		}
		
		
			
	}
	
	
	/**
	 * draw a diamond shape using circles recursively
	 * @param x x coordinate of center of the current circle
	 * @param y y coordinate of center of the current circle
	 * @param radius radius of the current circle
	 * @throws InterruptedException 
	 */
	public static void drawDiamondUsingCircles(int x, int y, int radius) throws InterruptedException
	{
		//stop for a while (timeInterval: global constant)
		Thread.sleep(timeInterval);
		
		//change the color of Console 'csl' to a randomly selective Color using Random variable 'rand'
		csl.setColor(Color.getHSBColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
		
		//fill a circular portion using Console 'csl' (at the square portion from (x-radius, y-radius) to (x+radius, y+radius))
		csl.fillOval(x-radius, y-radius, 2*radius, 2*radius);
		
		//base case: if radius of the current circle is bigger than 1 
		if(radius>1)
		{	
			drawDiamondUsingCircles(x, y + radius * 3/2, radius/2); //recursively, for South direction half-size circle
			drawDiamondUsingCircles(x + radius * 3/2, y, radius/2); //recursively, for East direction half-size circle
			drawDiamondUsingCircles(x, y - radius * 3/2, radius/2); //recursively, for North direction half-size circle
			drawDiamondUsingCircles(x - radius * 3/2, y, radius/2); //recursively, for West direction half-size circle
		}
	}
	
	/**
	 * 
	  * draw squares inside squares recursively
	 * @param x x coordinate of center of the current square
	 * @param y y coordinate of center of the current square
	 * @param length length of a side of the current square
	 * @param direction direction of the next square
	 * @throws InterruptedException
	 */
	public static void drawSquaresInSquares(int x, int y, int length, int direction) throws InterruptedException
	{
		//stop for a while (timeInterval: global constant)
		Thread.sleep(timeInterval);
		
		//change the color of Console 'csl' to a randomly selective Color using Random variable 'rand'
		csl.setColor(Color.getHSBColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
		
		//fill a rectangular portion using console 'csl' (at the portion from (x-length/2, y-length/2) to (x+length/2, y+length/2)
		csl.fillRect(x-length/2, y-length/2, length, length);
		
		//base case: if length of the current square if bigger than 0.1
		if(length>0.1)
		{
			//direction is determined by constans (0, 1, 2, 3) so if direction goes over 3, it should go back to 0
			direction%=4;
			
			if(direction == NORTH)
			{
				drawSquaresInSquares(x, y-length*3/4, length/2, direction+1); //recursively call for outside square
				drawSquaresInSquares(x, y+length*1/4, length/2, direction+1); //recursively call for inside square
			}
			else if(direction == SOUTH)
			{
				drawSquaresInSquares(x, y+length*3/4, length/2, direction+1); //recursively call for outside square
				drawSquaresInSquares(x, y-length*1/4, length/2, direction+1); //recursively call for inside square

			}
			else if(direction == EAST)
			{
				drawSquaresInSquares(x+length*3/4, y, length/2, direction+1); //recursively call for outside square
				drawSquaresInSquares(x-length*1/4, y, length/2, direction+1); //recursively call for inside square

			}
			else if(direction == WEST)
			{
				drawSquaresInSquares(x-length*3/4, y, length/2, direction+1); //recursively call for outside square
				drawSquaresInSquares(x+length*1/4, y, length/2, direction+1); //recursively call for inside square

			}
			else
			{
				//Should not reach here
				System.err.println("Fatal ERROR: illegal direction");
			}
		}
	}
	
}//Fractal class
