package Practice;


public class Part {
	Part back;
	Part next;
	int xPos, yPos;
	char dir;
	char map[][];

	public Part(int y, int x, char newMap[][])
	{
		back=null;
		next=null;
		xPos=x;
		yPos=y;
		map=newMap;
		map[y][x]='*';
	}

	public void move()
	{
		map[yPos][xPos]=' ';
		if(dir=='E')
		{
			xPos++;
		}
		else if(dir=='W')
		{
			xPos--;
		}
		else if(dir=='N')
		{
			yPos--;
		}
		else if(dir=='S')
		{
			yPos++;
		}
		else
		{
			System.err.println("Unexpected");
		}

		if(inRange(yPos, xPos))
		{
			map[yPos][xPos]='*';
		}
		else
		{
			GunGame.fillArray(map, ' ');
			GunGame.head=null;
		}

	}

	public void lengthify()
	{
		Part parent=this;
		Part child=back;
		if(parent!=null)
		{
			while(child!=null)
			{
				parent=parent.back;
				child=child.back;
			}
		}

		if(dir=='E')
		{
			child=new Part( yPos,xPos-1, map);
		}
		else if(dir=='W')
		{
			child=new Part( yPos,xPos+1,map);
		}
		else if(dir=='N')
		{
			child=new Part(yPos-1, xPos, map);
		}
		else if(dir=='S')
		{
			child=new Part(yPos+1, xPos, map);
		}
		else
		{
			System.err.println("Unexpected Error 2");
		}
		child.dir=parent.dir;


	}

	private boolean inRange(int y, int x) {
		return 0<=y && 0<=x && y<map.length && x<map[0].length;
	}
}
