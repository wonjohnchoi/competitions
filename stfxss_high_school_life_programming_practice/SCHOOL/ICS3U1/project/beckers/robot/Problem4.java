package project.beckers.robot;
/**
 * @author Wonjohn Choi
 * @date 2009/01/04
 * @course ICS3U
 */
import becker.robots.*;

public class Problem4 {
	/*
	 * This subroutine simply gives the setting of the city
	 * ex) the locations of each wall and the direction they face
	 */
	public static void createMap(City city)
	{
		//Create object walls
		Object Wall1=new Wall(city, 3,1, Direction.NORTH);
		Object Wall2=new Wall(city, 3,2, Direction.NORTH);
		Object Wall3=new Wall(city, 1,3, Direction.WEST);
		Object Wall4=new Wall(city, 2,3, Direction.WEST);
		Object Wall6=new Wall(city, 2,3, Direction.SOUTH);
		Object Wall7=new Wall(city, 2,4, Direction.SOUTH);
		Object Wall8=new Wall(city, 3,2, Direction.EAST);
		Object Wall9=new Wall(city, 4,2, Direction.EAST);
	}
	public static void movePosition(Robot robot)
	{
		//Make the robot turn backward
		robot.turnLeft();
		robot.turnLeft();
		
		//Make the robot move forward twice
		robot.move();
		robot.move();
	}
	
	public static void turnRight(Robot robot)
	{
		for(int i=0;i<3;i++)
		{
			robot.turnLeft();
		}
	}
	
	public static boolean checkLeft(Robot robot)
	{
		boolean ans=false;
		
		robot.turnLeft();
		
		if(robot.frontIsClear())
		{
			ans=true;
		}
		
		turnRight(robot);
		
		return ans;
	}
	
	public static void plantQuater(Robot robot)
	{
		robot.move();
		robot.turnLeft();
		while(robot.frontIsClear())
		{
			robot.putThing();
			robot.move();
		}
		robot.putThing();
		turnRight(robot);
		robot.move();
		while(!checkLeft(robot))
		{
			robot.putThing();
			robot.move();
		}
		robot.putThing();
		robot.turnLeft();
		
		
	}
	
	@SuppressWarnings("restriction")
	public static void main(String[] args)
	{
		City garden=new City();
		Robot karel=new Robot(garden, 0,0, Direction.NORTH);
		createMap(garden);
		
		//Give karel enough things before start
		Thing thing;
		for(int i=0;i<10000;i++)
			thing=new Thing(karel);
		
		
		movePosition(karel);
		
		for(int i=0;i<4;i++)
		{
			plantQuater(karel);
		}
		
		movePosition(karel);
	}
}
