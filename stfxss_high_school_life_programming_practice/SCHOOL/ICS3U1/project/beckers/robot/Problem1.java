package project.beckers.robot;
/**
 * @author Wonjohn Choi
 * @date 2009/01/04
 * @course ICS3U
 */

import becker.robots.*;

public class Problem1
{
	
	/*
	 * This subroutine simply gives the setting of the city
	 * ex) the locations of each wall and the direction they face
	 */
	public static void createMap(City city)
	{
		//Create object walls
		Object Wall1=new Wall(city, 2,1, Direction.SOUTH);
		Object Wall2=new Wall(city, 2,1, Direction.WEST);
		Object Wall3=new Wall(city, 1,1, Direction.WEST);
		Object Wall4=new Wall(city, 1,1, Direction.NORTH);
		Object Wall5=new Wall(city, 1,2, Direction.NORTH);
		Object Wall6=new Wall(city, 1,2, Direction.EAST);
		Object Wall7=new Wall(city, 1,2, Direction.SOUTH);
	}
	
	/*
	 * This subroutine forces the robot to move until it reaches something to pick. 
	 */
	public static void moveUntilPickThing(City city, Robot robot)
	{
		//while robot cannot pick something, this loop repeats,
		while(!robot.canPickThing())
		{
			//while robot's front is not clear, this loop repeats,
			//(basically, this while loop forces the robot turns left until its front is clear)
			while(!robot.frontIsClear())
			{
				//robot turns left 
				robot.turnLeft();
			}	
			
			//since robot's front is clear due to the above while loop, the robot can move,
			robot.move();
		}
	}
	
	/*
	 * This subroutine forces the robot to return to its bed.
	 */
	public static void returnToBed(City city, Robot robot)
	{
		//Turing left twice makes the robot turn to backward.
		robot.turnLeft();
		robot.turnLeft();
		//Make the robot (move forward+turnRight) three times
		for(int i=0;i<3;i++)
		{
			//Move foward
			robot.move();
			for(int j=0;j<3;j++)
			{
				//for three times, turnLeft (this is equivalent with turnRight)
				robot.turnLeft();
			}
		}
	}
	
	//Create main method
	public static void main(String args[])
	{
		//Create the house of karel
		City karelHome = new City();
		
		//Create the robot named karel on Street 1, Avenue 2
		Robot karel = new Robot(karelHome, 1, 2, Direction.SOUTH);  
		
		//Create newsPaper that karel needs to pick
		Thing newspaper = new Thing(karelHome, 2,2); 
		
		//Use subroutine createMap(City city) to set the structure of Karel's home
		createMap(karelHome);
		
		//Use subroutine moveUntilPickThing(City city, Robot robot) to move karel to the location of the newspaper.
		moveUntilPickThing(karelHome, karel);
		
		//Make karel pick the newspaper
		karel.pickThing();
		
		//Use subroutine retunrToBed(City city, Robot robot) to move karel to his/her bed.
		returnToBed(karelHome, karel);
	} 
}