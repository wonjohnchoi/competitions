package extra;

import java.util.Arrays;

public class printing {
	public static void main(String args[])
	{
		print(2323+ " dfd"); //2323 dfd
		char a[][]=new char[3][6];
		for(int i=0;i<a.length;i++)
		{
			Arrays.fill(a[i], '.');
		}
		printArray(a);
	}
	
	public static void printArray(char[][] info) {
		for (int i = 0; i < info.length; i++) {
			for (int j = 0; j < info[0].length; j++) {
				System.out.print(info[i][j]);
			}
			System.out.println();
		}
	}

	public static void print(Object s) {
		
		System.out.println(""+s);
	}
	
	
}
