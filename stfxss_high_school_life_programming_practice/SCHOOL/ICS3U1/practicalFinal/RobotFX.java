package practicalFinal;

import becker.robots.*;
import java.io.*;
import java.util.*;

/**
 * superclass for Final Exam question
 * 
 * January 22th, 2009
 * @author Wonjohn Choi
 * @course ICS3U
 */
public class RobotFX extends RobotSE 
{
  // Construct a new SuperRobot.
  public RobotFX(City theCity, int avenue, int street, Direction aDirection, int numThings)
  {  
    super(theCity, avenue, street, aDirection, numThings);
  }
  
  /*
   * get input from the user using the Scanner input and store the input to String commandString.
   */
  public static String getInput(boolean done, Scanner input, String commandString) throws IOException 
  {
    //get Input if done is not true,
    //if done is true, it means system should end soon so that don't get input.
    if(!done)
    {
      commandString=input.nextLine();
    }
    
    //returns new input String
    return commandString;
  }
  
  /*
   * use the input that it got from the user (command is the input and RobotFX karel is the robot).
   * int i tells program, which order of letter to use.
   * */
  public static void useInput(String command, RobotFX karel, int i)
  {
    //if command is 'L',
    if(command.charAt(i)=='L')
    {
      //turn Left
      karel.turnLeft();
    }
    
    //if command is 'R'
    if(command.charAt(i)=='R')
    {
      //turn Right
      karel.turnRight();
    }
    
    //if command is 'M',
    if(command.charAt(i)=='M')
    {
      //move
      karel.move();
    }
    
    //if command is 'U',
    if(command.charAt(i)=='U')
    {
      //pick thing
      karel.pickThing();
    }
    
    //if command is 'D',
    if(command.charAt(i)=='D')
    {
      //put thing
      karel.putThing();
    }
    
  }
}
  
