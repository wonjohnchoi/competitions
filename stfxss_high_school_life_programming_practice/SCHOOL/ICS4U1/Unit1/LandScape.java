package Unit1;

import java.util.*;
import java.io.*;

public class LandScape 
{
	//2D array where I will store the created LandScape.
	public static char background[][];

	public static void main(String args[]) throws IOException {
		// Scanner to get Input
		Scanner sc = new Scanner(new FileReader("DATA1.txt"));

		// int type array to store the five heights given by user
		int hillHeight[] = new int[5];

		// store the width of the background 
		int width = 0;
		
		for (int i = 0; i < 5; i++) {
			// get input
			hillHeight[i] = sc.nextInt();

			// calculate the width of background 
			
			//if height is zero, the hill takes one blank space.
			if (hillHeight[i] == 0)
			{
				width += 1;
			}
			//if height is not zero, the hill takes height*2-1 spaces.
			else 
			{
				width += hillHeight[i] * 2 - 1;
			}
		}

		// create background with 2D array
		background = new char[5][width];

		// fill each row with '.'
		for (int i = 0; i < 5; i++) 
		{
			Arrays.fill(background[i], '.');
		}

		// pos will indicate the location of origin of each mountain (origin is
		// the middle-bottom of the mountain)
		int pos = -1;

		// for each input,
		for (int i = 0; i < 5; i++) 
		{
			//declare an integer variable height and initialize as hillHeight[i]
			int height=hillHeight[i];
			
			//if height is zero, do nothing except moving position forward.
			if (height == 0) 
			{
				pos++;
			} 
			else 
			{
				// move position
				pos += height;

				// use the method createHill: pos gives postion 
				// hillHeight[i] gives the height of each hill
				createHill(height, pos);

				// move position
				pos += height - 1;
			}
		}

		// print the picture of background
		printArray(background);

	}

	/*
	 * subroutine to create each mountain
	 */
	public static void createHill(int hillHeight, int hillPos)
	{
		//for each j, position-j gives column
		for(int j=0;j<hillHeight;j++)
		{
			//for each i, i gives the row
			for(int i=4;i>=5-hillHeight+j;i--)
			{
				//fill the position as x
				background[i][hillPos-j]='x';
				background[i][hillPos+j]='x';
			}
		}
		
	}

	/*
	 * print the 2D array
	 */
	public static void printArray(char array[][]) throws IOException {
		//PrintWriter and FileWriter to output to the file.
		PrintWriter pw = new PrintWriter(new FileWriter("OUT1.txt"));
		
		//i represents height,
		for (int i = 0; i < array.length; i++) {
			// j represents width,
			for (int j = 0; j < array[i].length; j++) {
				// print the char value at the array.
				pw.print(array[i][j]);
			}
			// print a line after priting the row.
			pw.println();
		}
		pw.close();
	}
}

