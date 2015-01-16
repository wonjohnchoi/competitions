package usaco;
/*
ID: yojo1002
LANG: JAVA
TASK: transform
*/
import java.io.*;
import java.util.*;

class transform {

	
	public static void turn90(char maze1[][], int n)
	{
		
		char temp[][] = new char[n][n];
		int tempx, tempy=0;
		for(int wid=0;wid<n;wid++)
		{
			tempx=n-1;
			for(int col=0;col<n;col++)
			{
				temp[wid][col]=maze1[tempx][tempy];
				tempx--;
			}
			tempy++;
		}
		
		for(int wid=0;wid<n;wid++)
			for(int col=0;col<n;col++)
			{
				maze1[wid][col]=temp[wid][col];
			}
	
	}
	
	public static void turn902(char maze2[][], int n)
	{
		
		char temp[][] = new char[n][n];
		int tempx, tempy=0;
		for(int wid=0;wid<n;wid++)
		{
			tempx=n-1;
			for(int col=0;col<n;col++)
			{
				temp[wid][col]=maze2[tempx][tempy];
				tempx--;
			}
			tempy++;
		}
		
		for(int wid=0;wid<n;wid++)
			for(int col=0;col<n;col++)
			{
				maze2[wid][col]=temp[wid][col];
			}
	
	}
	
	public static void mirror(char maze2[][], int n)
	{
		char temp[][]=new char[n][n];
		for(int wid=0;wid<n;wid++)
			for(int col=0;col<n;col++)
			{
				temp[wid][col]=maze2[wid][n-1-col];
			}
		for(int wid=0;wid<n;wid++)
			for(int col=0;col<n;col++)
			{
				maze2[wid][col]=temp[wid][col];
			}
	}
	
	public static void main (String [] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("transform.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		int n=Integer.parseInt(f.readLine());

		boolean decide[]=new boolean[9];
		for(int i=0;i<9;i++){decide[i]=true;}
		char maze[][]=new char[n][n];
		char maze1[][]=new char[n][n];
		char maze2[][]=new char[n][n];
		char maze4[][]=new char[n][n];
			
		for(int i=0;i<n;i++)
		{
			String temp=f.readLine();
			for(int j=0;j<n;j++)
			{
				maze1[i][j]=temp.charAt(j);
				maze2[i][j]=maze1[i][j];
				maze4[i][j]=maze1[i][j];
			}
		}

		for(int i=0;i<n;i++)
		{
			String temp=f.readLine();
			for(int j=0;j<n;j++)
			{
				maze[i][j]=temp.charAt(j);
			}
		}
		
		
		turn90(maze1,n);
		
		
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(maze1[i][j]!=maze[i][j])
				{
					decide[1]=false;
				}
				
			}
		}
		if(decide[1]==true) {out.println(1);out.close();                                 
		System.exit(0); }
		
		turn90(maze1,n);
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(maze1[i][j]!=maze[i][j])
				{
					decide[2]=false;
				}
				
			}
		}
		
		if(decide[2]==true) {out.println(2);out.close();                                 
		System.exit(0); }

		turn90(maze1,n);
		
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(maze1[i][j]!=maze[i][j])
				{
					decide[3]=false;
				}
				
			}
		}
		
		if(decide[3]==true) {out.println(3);out.close();                                 
		System.exit(0); }

		mirror(maze2,n);
		
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(maze2[i][j]!=maze[i][j])
				{
					decide[4]=false;
				}
				
			}
		}
		
		if(decide[4]==true) {out.println(4);out.close();                                 
		System.exit(0); }

		
		turn902(maze2,n);
		
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(maze2[i][j]!=maze[i][j])
				{
					decide[5]=false;
				}
				
			}
		}
		
		if(decide[5]==true) {out.println(5);out.close();                                 
		System.exit(0); }

		

		turn902(maze2,n);
		
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(maze2[i][j]!=maze[i][j])
				{
					decide[6]=false;
				}
				
			}
		}
		
		if(decide[6]==true) {out.println(5);out.close();                                 
		System.exit(0); }
		
		turn902(maze2,n);
		
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(maze2[i][j]!=maze[i][j])
				{
					decide[7]=false;
				}
				
			}
		}
		
		if(decide[7]==true) {out.println(5);out.close();                                 
		System.exit(0); }
		
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(maze1[i][j]!=maze[i][j])
				{
					decide[0]=false;
				}
				
			}
		}
		
		if(decide[0]==true) {out.println(6);out.close();                                 
		System.exit(0); }
		
		for(int i=0;i<8;i++)
			if(decide[i]==true)
				decide[8]=false;
		if(decide[8]==true){out.println(7);out.close();                                 
		System.exit(0); }
		
			
	                         
	}
}