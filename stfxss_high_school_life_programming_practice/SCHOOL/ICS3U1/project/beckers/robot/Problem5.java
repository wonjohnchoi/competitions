package project.beckers.robot;
/**
 * @author Wonjohn Choi
 * @date 2009/01/04
 * @course ICS3U
 */

import becker.robots.*;
/*
 * Create a superclass to use it for inheritance
 */
class Shovelbot {
	/*
	 * Create a method that makes a robot clean (pick things) a straight road
	 */
	public static void cleanARoad(Robot robot)
	{		
		//Make the robot do this until there is something in front of the robot.
		while(robot.frontIsClear())
		{
			//If there is something to pick, robot picks everything that exists
			while(robot.canPickThing())
			{
				robot.pickThing();
			}
			//Make the robot move forward
			robot.move();
		}
	}
	
	/*
	 * Create a method that makes the robot turn right
	 */
	public static void turnRight(Robot robot)
	{
		//Turning Left three times is same to turning right one time.
		robot.turnLeft();
		robot.turnLeft();
		robot.turnLeft();
	}
	
	/*
	 * Create a method that makes the robot to put out all of the things in its backpack
	 */
	public static void putEverythingOut(Robot robot)
	{
		//While there is more than zero things in backpack,
		while(robot.countThingsInBackpack()!=0)
		{
			//Robot puts out one thing
			robot.putThing();
		}
	}

	/*
	 * Create a method that enable the robot to clean all of the drive ways
	 */
	public static void CleanAllDriveWays(Robot robot)
	{
		//In the sidewalk, while front is clear,
		while(robot.frontIsClear())
		{
			//robot turns left 
			robot.turnLeft();
			//And checks if there is a drive way (if front is clear, there is a drive way)
			if(robot.frontIsClear())
			{
				//Since there is a drive way, robot moves forward
				robot.move();
				//Now use the method created above to clean the drive way (cleanARoad(Robot robot))
				cleanARoad(robot);
				//The robot reaches the end of the drive way and check if there is something to pick,
				while(robot.canPickThing())
				{
					//If there are something to pick, it picks
					robot.pickThing();
				}
				//Make the robot turn right
				turnRight(robot);
				//This if loop checks whether there is another drive way (since the question says
				//that there will be one or two drive ways.
				if(robot.frontIsClear())
				{
					//If there is another drive way, robot moves to the position (so the end of another 
					//drive way.
					robot.move();
				}
				//Turn right so that robot sees the direction of the sidewalk
				turnRight(robot);
				//Again, use the method cleanARoad(Robot robot) to clean another driveway
				cleanARoad(robot);
				//After the method cleanARoad, the robot arrives at the sidewalk
				//With the method, putEverythingOut(Robot robot), the robot puts out everything it has
				//on the side walk
				putEverythingOut(robot);
				//robot turns left 
				robot.turnLeft();
			}
			//else means that there was no driveway when the robot turned left before the above if loop
			else
			{
				//So the robot turns left twice more so that the robot checks its right side to see
				//if there is a drive way (so checking the opposite side to see if there is a drive way)
				robot.turnLeft();
				robot.turnLeft();
				//if there is a driveway, 
				if(robot.frontIsClear())
				{
					//Robot moves forward
					robot.move();
					//With the method created (CleanARoad(Robot robot)), it cleans the road
					cleanARoad(robot);
					//Robot arrives at the end of the drive way after the method cleanARoad
					//If there is something to pick, 
					while(robot.canPickThing())
					{
						//robot picks them
						robot.pickThing();
					}
					//robot turns left to see if there is another drive way since question says
					//there will be one or two drive ways
					robot.turnLeft();
					//If there is one more drive way (so front is clear)
					if(robot.frontIsClear())
					{
						//robot moves to the position
						robot.move();
					}
					//robot turns left to see the direction of the side walk
					robot.turnLeft();
					//By using the method "cleanARoad(Robot robot)", robot cleans the road and reach 
					//to the sidewalk.
					cleanARoad(robot);
					//Now, the robot reached the sidewalk and puts all of the things it has on the sidewalk
					//With this method, putEverythingOut(Robot robot)
					putEverythingOut(robot);
					//Robot turns right with this created method turn right, so become ready to go to the
					//next position of the side walk.
					turnRight(robot);
				}
				//else, so if front was not clear, so there was no driveway, the robot can go
				else
				{
					//Robot turns left so that robot sees the direction of the sidewalk being ready to move
					//forward
					robot.turnLeft();
				}
				
			}
			//Robot moves forward 
			robot.move();
		}	
		
	}
}

/*
 * Create a subclass to solve problem 5
 */
public class Problem5 
{		
	//Create main method
	public static void main(String args[])
	{
		//Create the place based on the text file "Canada.txt" where karel and tina will work
		City Canada = new City("Canada.txt");
		
		//Create the robots named karel and tina
		Robot karel = new Robot(Canada, 0,2, Direction.SOUTH);    
		Robot tina = new Robot(Canada, 0,2, Direction.SOUTH);    System.out.println(karel.getAvenue());
		//Use the methods from the superclass Shovelbot to clean all the drive ways using karel
		Shovelbot.CleanAllDriveWays(karel);
		
		
		
		//clean the side way using tina
		Shovelbot.cleanARoad(tina);
		//Then, put out all the things tina has at the end of the sidewalk with the method
		//from superclass "putEverythingOut(Robot robot)
		Shovelbot.putEverythingOut(tina);
		
	} 
}