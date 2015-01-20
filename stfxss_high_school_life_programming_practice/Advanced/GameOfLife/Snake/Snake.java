package Snake;

import java.util.Arrays;

public class Snake {
	Node root;
	char map[][];
	boolean dead;
	int direct[][]=
	{
			{-1, 0}, //N
			{0, -1}, //E
			{1, 0}, //S
			{0, 1} //W
	};
	public Snake(Node r, char m[][])
	{
		root=r;
		map=m;
		dead=false;
	}

	public void lengthify()
	{
		Node par=root;
		while(par.down!=null)
		{
			par=par.down;
		}

		par.down=new Node(par.yPos-direct[par.dir][0], par.xPos-direct[par.dir][1], par.dir, map);
		if(par.down.dead)
		{
			dead=true;
			System.out.println("Dead while lengthifying");
		}
		else
		{
			par.down.top=par;
		}
	}

	void turnLeft()
	{
		root.turnLeft();
	}

	void turnRight()
	{
		root.turnRight();
	}

	void move()
	{
		Node end=root;
		while(end.down!=null)
		{
			end=end.down;
		}

		while(end.down!=root && !end.isDead())
		{
			end.updateDir();
			end.move();
		}
		
		if(end.isDead())
		{
			dead=true;
			System.out.println("Dead while moving");
		}
	}

	boolean isDead()
	{
		Node tmp=root;
		boolean dead=false;

		while(tmp!=null && !dead)
		{
			if(tmp.dead)
			{
				dead=true;
			}
			else
			{
				tmp=tmp.down;
			}
		}

		return dead;
	}
	void updateMap()
	{
		for(int i=0;i<map.length;i++)
		{
			Arrays.fill(map[i], ' ');
		}
		Node n=root;
		while(n!=null)
		{
			map[n.yPos][n.xPos]='*';
			n=n.down;
		}
	}
	
	public String toString()
	{
		String out="";
		for(int i=0;i<map.length;i++)
		{
			out+=new String(map[i])+"\n";
		}
		return out;
	}
}


