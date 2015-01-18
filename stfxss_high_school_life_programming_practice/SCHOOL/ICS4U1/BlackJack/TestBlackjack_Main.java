package BlackJack;
/**
 * @author Wonjohn Choi
 * @teacher Mr. Reid
 * @course ICS4U1
 * @project Blackjack
 * @description test program of blackjack class - this is the main program that runs blackjack
 * @Date April 22, 2010
 */

import java.util.Scanner;

public class TestBlackjack_Main {
	public static void main(String args[]) 
	{
		Scanner sc=new Scanner(System.in);
		
		//get number of players from user
		System.out.print("The number of players: ");
		int numPlayer=sc.nextInt();
		
		//instantiate blackjack object
		Blackjack bj=new Blackjack();		

		double money;
		do
		{
			//ask for initial money of each player
			System.out.print("Initial Money for each player (Minimum of $10): ");
			money=sc.nextDouble();
		}while(money<10); //require at least $10 or loop the process
		
		sc.nextLine();
		
		//get player names and add them to the game
		for(int i=1;i<=numPlayer;i++)
		{
			System.out.print("Name of Player "+i+": ");
			
			//get name from a player
			String name=sc.nextLine();
			
			//while name is nothing,
			while(name.trim().equals(""))
			{
				System.out.print("Name of Player "+i+": ");
				name=sc.nextLine(); // get input again
			}
			bj.add(new Player(name, money));
		}
		
		boolean done; //value to check if game finished
		do
		{
			done=false; //assume game is not finished
			bj.startARound(); //start game
			bj.reset(); //after a game, reset player settings..
			
			//player number is zero means game finished
			if(bj.getPlayerNum()==0)
			{
				System.out.println("There is no player left");
				done=true;
			}
			else 
			{
				//ask if user wants more game
				System.out.println("Want to play more games? (y=yes, n=no)");
				String isContinue=sc.next();
				
				//if not, stop game
				if(isContinue.equals("n"))
				{
					done=true;
				}
			}
			
		}
		while(!done);
		
		System.out.println("Game ends:");
		System.out.println(bj); //show result
	}
	
	
}
