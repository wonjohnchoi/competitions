package Snake;

public class Node 
{
	Node top, down;
	int yPos, xPos;
	boolean dead;
	char map[][];
	int direct[][]=
	{
			{-1, 0}, //N
			{0, -1}, //E
			{1, 0}, //S
			{0, 1} //W
	};
	int dir;
	public Node(int y, int x, int newDir, char m[][])
	{
		yPos=y;
		xPos=x;
		dir=newDir;
		map=m;
		
		dead=false;
		
		if(outOfRange())
		{
			dead=true;
		}

	}
	
	void turnRight()
	{
		dir++;
		dir%=4;
	}
	void turnLeft()
	{
		dir--;
		if(dir<0)
		{
			dir+=4;
		}
	}
	
	void turnNorth()
	{
		dir=0;
	}
	
	void turnEast()
	{
		dir=1;
	}
	
	void turnSouth()
	{
		dir=2;
	}
	
	void turnWest()
	{
		dir=3;
	}
	void updateDir()
	{
		if(top.xPos>xPos)
		{
			dir=1;
		}
		else if(top.xPos<xPos)
		{
			dir=3;
		}
		else if(top.yPos>yPos)
		{
			dir=2;
		}
		else if(top.yPos<yPos)
		{
			dir=0;
		}
	}
	void move()
	{
		yPos+=direct[dir][0];
		xPos+=direct[dir][1];
		
		if(outOfRange())
		{
			dead=true;
		}
	}
	
	boolean isDead()
	{
		return dead || outOfRange();
	}
	
	boolean outOfRange()
	{
		return 0<=xPos && 0<=yPos && xPos<map[0].length && yPos<map.length;
	}
}
