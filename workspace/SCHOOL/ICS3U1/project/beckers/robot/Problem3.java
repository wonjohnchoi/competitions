package project.beckers.robot;
/**
 * @author Wonjohn Choi
 * @date 2009/01/04
 * @course ICS3U
 */
import becker.robots.*;

public class Problem3 {
	/*
	 * Create a method moveMile(Robot robot) 
	 */
	public static void moveMile(Robot robot)
	{
		//This for loop allows this method make the robot to move one mile(10 intersections)
		for(int i=0;i<10;i++)
		{
			robot.move();
		}
	}
	
	/*
	 * Create a method move1000Miles(Robot robot)
	 */
	public static void move1000Miles(Robot robot)
	{
		//This for loop allows this method to make the robot move one mile 1000 times
		for(int i=0;i<1000;i++)
		{
			//The method moveMile(Robot robot) was used to make the robot one mile (10 intersection)
			moveMile(robot);
		}
	}
	
}
