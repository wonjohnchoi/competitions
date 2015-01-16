package Snake;

import java.util.Arrays;

public class TestSnake {
	public static void main(String args[])
	{
		char map[][]=new char[10][10];
		for(int i=0;i<map.length;i++)
		{
			Arrays.fill(map[i], ' ');
		}
		
		Node root=new Node(5, 5,1, map);
		
		Snake sn=new Snake(root, map);
		
		for(int i=0;i<10;i++)
		{
			sn.updateMap();
			System.out.println(sn);
			sn.move();
			System.out.println("-------------------------------------------------");
		}
		
	}

	private static void print(char[][] map) {
		// TODO Auto-generated method stub
		for(int i=0;i<map.length;i++)
		{
			System.out.println(new String(map[i]));
		}
	}
}
