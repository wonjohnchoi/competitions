package Practice;

import java.util.Arrays;
import java.util.Scanner;


public class GunGame {
	static Part head;
	public static void main(String args[])
	{
		char [][] map=new char[10][10];
		fillArray(map, ' ');
		head=new Part(5,1, map);
		head.dir='N';
		for(int i=0;i<10;i++)
		{
			printArray(map);
			nextMove(map);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static void fillArray(char[][] map, char fill) {
		for(int i=0;i<map.length;i++)
		{
			Arrays.fill(map[i], fill);
		}
	}

	private static void nextMove(char[][] map) {
		if(head!=null)
		{
			head.move();
			if(head!=null)
			{
				Part part=head.back;
				while(part!=null)
				{
					part.move();
					part=part.back;
				}
				
			//	head.lengthify();
			}
		}
	}

	private static void printArray(char[][] map) {
		for(int i=0;i<map.length;i++)
		{
			System.out.println(new String(map[i]));
		}
	}
}
