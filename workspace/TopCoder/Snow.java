package project.beckers.robot;
/**
 * @author Wonjohn Choi
 * @date 2009/01/04
 * @course ICS3U
 */
import becker.robots.*;

public class Snow {
	public static void cleanARoad(Robot robot)
	{		
		while(robot.frontIsClear())
		{
			while(robot.canPickThing())
			{
				robot.pickThing();
			}
			robot.move();
		}
	}
	
	public static void turnRight(Robot robot)
	{
		robot.turnLeft();
		robot.turnLeft();
		robot.turnLeft();
	}
		
	public static void putEverythingOut(Robot robot)
	{
		while(robot.countThingsInBackpack()!=0)
		{
			robot.putThing();
		}
	}

	public static void CleanAllDriveWays(Robot robot)
	{
		while(robot.frontIsClear())
		{
			robot.turnLeft();
			if(robot.frontIsClear())
			{
				robot.move();
				cleanARoad(robot);
				while(robot.canPickThing())
				{
					robot.pickThing();
				}
				turnRight(robot);
				if(robot.frontIsClear())
				{
					robot.move();
				}
				turnRight(robot);
				cleanARoad(robot);
				putEverythingOut(robot);
				robot.turnLeft();
			}
			else
			{
				robot.turnLeft();
				robot.turnLeft();
				if(robot.frontIsClear())
				{
					robot.move();
					cleanARoad(robot);
					while(robot.canPickThing())
					{
						robot.pickThing();
					}
					robot.turnLeft();
					if(robot.frontIsClear())
					{
						robot.move();
					}
					robot.turnLeft();
					cleanARoad(robot);
					putEverythingOut(robot);
					turnRight(robot);
				}
				else
				{
					robot.turnLeft();
				}
				
			}
			robot.move();
		}	
		
	}
}

