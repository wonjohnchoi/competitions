package project.beckers.robot;
import becker.robots.*;

/**
 * @author Wonjohn Choi
 * @date 2009/01/04
 * @course ICS3U
 */
public class Problem2
{
	
	/*
	 * This subroutine simply gives the setting of the city
	 * ex) the locations of each wall and the direction they face
	 */
	public static void createMap(City city)
	{
		//Create object walls
		Object Wall1=new Wall(city, 3,2, Direction.WEST);
		Object Wall2=new Wall(city, 3,2, Direction.NORTH);
		Object Wall3=new Wall(city, 2,3, Direction.WEST);
		Object Wall4=new Wall(city, 1,3, Direction.WEST);
		Object Wall5=new Wall(city, 1,3, Direction.NORTH);
		Object Wall6=new Wall(city, 1,3, Direction.EAST);
		Object Wall7=new Wall(city, 2,4, Direction.NORTH);
		Object Wall8=new Wall(city, 2,4, Direction.EAST);
		Object Wall9=new Wall(city, 3,4, Direction.EAST);
	}
	
	/*
	 * This subroutine forces the robot to move until it reaches something to pick. 
	 */
	public static void moveUntilPickThing(City city, Robot robot)
	{
		//while robot cannot pick something, this loop repeats,
		while(!robot.canPickThing())
		{
			//robot moves
			robot.move();
		}
	}
	
	public static void reachToTheTop(City city, Robot robot)
	{
		while(robot.getStreet()!=0)
		{
			while(robot.getDirection()==Direction.EAST)
			{
				if(robot.frontIsClear())
				{
					robot.move();
				}
				else
				{
					robot.turnLeft();
				}
			}
			while(robot.getDirection()==Direction.NORTH)
			{
				robot.move();
				robot.turnLeft();
				robot.turnLeft();
				robot.turnLeft();
				if(!robot.frontIsClear())
				{
					robot.turnLeft();
				}
			}
		}

		robot.move();
	}
	
	public static void reachToTheBottom(City city, Robot robot)
	{
		while(robot.getStreet()!=3)
		{
			while(robot.getDirection()==Direction.SOUTH&&robot.getStreet()!=3)
			{
				if(robot.frontIsClear())
				{
					robot.move();
				}
				else
				{
					robot.turnLeft();
				}
			}
			while(robot.getDirection()==Direction.EAST)
			{
				robot.move();
				robot.turnLeft();
				robot.turnLeft();
				robot.turnLeft();
				if(!robot.frontIsClear())
				{
					robot.turnLeft();
				}
			}
		}		
	}
	
	
	//Create main method
	public static void main(String args[])
	{
		//Create the mountain that the robot will go through
		City mountain = new City();
		
		//Create the robot named karel on Street 3, Avenue 0
		Robot karel = new Robot(mountain, 3, 0, Direction.EAST);
		
		//Create flag that karel needs to pick
		Thing flag = new Thing(mountain, 3,1);
		
		//Use subroutine createMap(City city) to set the structure of the mountain
		createMap(mountain);
		
		//Use subroutine moveUntilPickThing(City city, Robot robot) to move karel to the position of a flag
		moveUntilPickThing(mountain, karel);
		
		//Make karel pick the flag.
		karel.pickThing();
		
		//Use subroutine reachToTheTop(City city, Robot robot) to move karel to the top of the mountain
		reachToTheTop(mountain, karel);
		
		//Make karel drop the flag on the top
		karel.putThing();
		
		//Use subroutine reachToTheBottom(City city, Robot robot) to move karel to the bottom of the mountain
		reachToTheBottom(mountain, karel);
		
		//Make karel turn left 
		karel.turnLeft();
	} 
}