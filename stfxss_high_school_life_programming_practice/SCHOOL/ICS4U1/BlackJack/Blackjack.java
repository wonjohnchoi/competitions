package BlackJack;

/**
 * @author Wonjohn Choi
 * @teacher Mr. Reid
 * @course ICS4U1
 * @project Blackjack
 * @description Blackjack class that gathers other classes and help starting a game			
 * @Date April 22, 2010
 */

import java.util.ArrayList;
import java.util.Scanner;


public class Blackjack {
	protected ArrayList<Player> players;
	protected Dealer dealer;
	protected int numRound;

	/**
	 * Constructor
	 */
	public Blackjack() 
	{ 
		numRound=0;
		players=new ArrayList<Player>();
		dealer=new Dealer();
	}
	
	/**
	 * number of players
	 */
	public int getPlayerNum()
	{
		return players.size();
	}
	
	/**
	 * Reset the deck to start a new game
	 */
	public void reset()
	{
		//reset dealer for a new game
		dealer.reset();
		
		//for each player in the 'players' arrayList,
		for(Player player: players)
		{
			//reset each player for a new game
			player.reset();
		}
	}
	
	

	/**
	 * Add a player to the game
	 * @param newPlayer a player to be added
	 */
	public void add(Player newPlayer) 
	{
		players.add(newPlayer);
	}
	
	/**
	 * Kick a player out of the game
	 * @param oldPlayer a player to be kicked
	 */
	public void kick(Player oldPlayer)
	{
		players.remove(oldPlayer);
	}
	
	/**
	 * stop program till enter is inputed
	 */
	public void delay() 
	{		
		Scanner sc=new Scanner(System.in);
		sc.nextLine();

	}
	
	
	/**
	 * show rules of the casino
	 */
	public void viewRule()
	{		
		//String array contains rules
		String rules[]=
		{
			"--------------------------------------------------------------------------",
			"| Rules of this casino",				
			"| We do NOT offer insurance",
			"| We do NOT offer split option",
			"| Dealer stands on all 17s (means dealer gets cards till it has over 16)",
			"| Double down makes your bet double and you have maximum of one hit",
			"| When you get BlackJack, you 1.5 times of the money you bet",
			"| You can have maximum of 5 cards on your hand",
			"| You need at least $10 to stay at the game",
			"-------------------------------------------------------------------------"
		};
		
		//here we print out the rules
		for(int i=0;i<rules.length;i++)
		{
			System.out.println(rules[i]);
		}
		
		//make the interface clean
		System.out.println();
		
	}
	
	

	/**
	 * Set the bet of a player by getting input from the user
	 * @param player A player who will update his/her bet
	 */
	public void setBet(Player player)
	{
		Scanner sc=new Scanner(System.in);
		double bet=0.0D;
		
		//show the player how much money he/she has
		System.out.printf("%s has $%.2f \n", player.getName(), player.getMoney());
		
		//set the value of bet from the user 
		do
		{
			System.out.printf("Input an updated bet (less than $%.2f): ", player.getMoney()/1.5);
			bet=sc.nextDouble();
		}while(player.getMoney()<bet*1.5 ||bet<=0); //if 1.5 times amount of player's bet is more money than he/she has, give prompt again 
											//1.5 because Blackjack takes 1.5 times money than the bet
		
		//set the updated value of bet
		player.setBet(bet);
	}
	
	/**
	 * Output a line to make interface clear.
	 */
	private void divSection()
	{
		System.out.println("\n################################################################################");
	}
	
	/**
	 * Start a single round 
	 */
	public void startARound() {
		//Player who does not have money will be kicked (money < $10)
		///for(Player player: players)
		for(int i=0;i<players.size();i++)
		{
			Player player=players.get(i);
			if(player.getMoney()<10)
			{
				System.out.printf("%s is kicked out of the game due to lack of money\n", player.getName());
				kick(player);
				i--; //need to check the previous player since kicking one player made i automatically
					//move to next position
			}
		}
		
		//if no player plays, finish game (it happens by kicking option above)
		if(players.size()!=0)
		{
			numRound++;
			System.out.printf("Round %d start! \n", numRound);
			
			//make screen clearer
			delay();
			divSection(); 
			
			//Set a value of bet for each player
			for(Player player: players)
			{
				setBet(player);
				System.out.println();
			}
	
			delay();
			divSection();
			
			//shuffle deck
			dealer.shuffleDeck();
			
			//deal two initial cards for each player 
			for(Player player: players)
			{
				player.getHand().addCard(dealer.dealCard());
				player.getHand().addCard(dealer.dealCard());
				System.out.printf("%s's hand: %s \n",player.getName(),player.hand); //show player's hand
			}
			
			//deal two initial cards for the dealer
			dealer.getHand().addCard(dealer.dealCard());
			dealer.getHand().addCard(dealer.dealCard());
			System.out.println("Dealer's hand: "+dealer.getHiddenHand()); //show dealer's hand
			
			//make screen clearer
			
			delay();
			divSection();
			
			//detect players and dealer if they have blackjack and process it.
			for(Player player: players)
			{
				onBlackJack(player);
			}
			
			//if game not finished (because of blackjack count),
			if(!isFinished())
			{
				
				//Players get option to hit, stand, see rules, etc.
				for(Player player: players)
				{
					
					if(!player.finished()) //if player didn't finish his/her turn (because of blackjack)
					{
						System.out.printf("%s's turn-----------------------------------------------------------------\n", player.getName());
						while(!player.finished())
						{
							System.out.printf("%s's hand: %s\n", player.getName(),player.getHand());
							
							//check if this is player's first turn using # of cards, info whether splited or doubleDowned before
							boolean isInitialTurn=player.getHand().countCards()==2;
							
							//show option to user and perform the choice
							performOption(viewOption(isInitialTurn), player);
						}
						System.out.printf("%s's hand: %s\n", player.getName(),player.getHand());
						delay();
						divSection();
					}
				}
		
				//deal the hand of the dealer until his hand has sum over 16 (it's a rule)
				//also, his cards should be less or equal to 5
				while(dealer.getHand().getSum()<17 && dealer.getHand().countCards()<5)
				{
					dealer.getHand().addCard(dealer.dealCard());
				}

				//show players and dealer what they have
				for(Player player: players)
				{
					System.out.println(player.getName()+"'s hand: "+player.getHand());
				}
				System.out.println("Dealer's hand: "+dealer.getHand());
				
				//make screen clearer
				delay();
				divSection();
				
				//declare winner
				for(Player player: players)
				{
					checkWinner(player);
				}
			
				//make screen clearer
				delay();
				divSection();
			}
				
			//show dealer and players' battle reports
			for(Player player: players)
			{
				System.out.println(player);
			}
			System.out.println(dealer);
				
			delay();
			divSection();
		}
	
	}
	
	/**
	 * check whether the game is finished
	 * It checks whether every player finished their turn..
	 */
	public boolean isFinished()
	{
		boolean finished=true;
		for(Player player:players)
		{
			//if any of the player is not finished,
			if(!player.finished())
			{
				//game is not finished
				finished=false;
			}
		}
		return finished;
	}
	
	/**
	 * Step to detect blackjacks after the player gets two initial cards
	 * @param player a player to be evaluated
	 */
	public void onBlackJack(Player player)
	{
		// BlackJack wins immediately, unless dealer also has BlackJack
		if(player.getHand().isBlackjack())
		{
			//multiply the bet by 1.5 since it is blackjack
			player.setBet(player.getBet()*1.5);
			
			//if dealer has blackJack, it is push (no money change)
			if(dealer.getHand().isBlackjack())
			{
				System.out.printf("%s pushed with dealer since both has blackjack!\n",player.getName());
			}
			
			//if dealer does not have blackjack, you win.
			else
			{
				System.out.printf("%s won with blackJack!\n", player.getName());
				
				//update money and battle record
				player.update(true);
				dealer.update(false, player.getBet());
		
			}
			
			//finish turn
			player.finish();
			
			//make screen clearer
			delay();
			divSection();
		}
		else if(dealer.getHand().isBlackjack())
		{
			//multiply the bet by 1.5 since it is blackjack
			player.setBet(player.getBet()*1.5);
			
			System.out.printf("%s lost due to dealer's BlackJack!\n", player.getName());

			//update money and battle record
			player.update(false);
			dealer.update(true, player.getBet());
			
			//finish turn
			player.finish();
			
			//make screen clearer
			delay();
			divSection();
		}
		
	}
	
	/**
	 * Check whether a player won against the dealer
	 * @param player a player to be checked
	 */
	public void checkWinner(Player player)
	{
		boolean victory=false;
		boolean push=false;
		//both does not have blackjack since blackjack is already computed
		if(!(player.getHand().isBlackjack() || dealer.getHand().isBlackjack()))
		{
			//if player busts,
			if(player.getHand().busted())
			{
				System.out.print(player.getName()+" busted! ");
				victory=false;
			}
			
			//if dealer busts
			else if(dealer.getHand().busted())
			{
				System.out.print("Dealer busted! ");
				victory=true;
			}
			
			//if player has bigger hand
			else if(player.getHand().getSum()>dealer.getHand().getSum())
			{
				victory=true;
			}
			
			//if dealer has bigger hand
			else if(player.getHand().getSum()<dealer.getHand().getSum())
			{
				victory=false;
			}
			else
			{
				push=true;
			}
			
			if(push)
			{
				System.out.println(player.getName()+" push with dealer due to the same sum");
			}
			else 
			{
				//update money and battle record
				player.update(victory);
				dealer.update(!victory, player.getBet());
				
				//declare who won
				if(victory)
				{
					System.out.println(player.getName()+" won!");
				}
				else
				{
					System.out.println(player.getName()+" lost!");

				}
			}
		}
	}
	
	/**
	 * Show options to users
	 * @param initialTurn Is this user's first turn?
	 * @return choice that user chose to do
	 */
	public int viewOption(boolean initialTurn)
	{
		Scanner sc=new Scanner(System.in);
		int choice=0;
		boolean properOption=false;
		
		//if it is player's first turn
		if(initialTurn)
		{
			do
			{
				//prompt
				System.out.println("--------------------------------------------");
				System.out.println("| 1.Show rules 2.Hit 3.Stand 4.Double down |");
				System.out.println("--------------------------------------------");
				choice=sc.nextInt();
				
				//decide whether user put one of options
				properOption=choice==1 || choice==2 || choice==3 || choice==4;
				
				if(!properOption)
				{
					System.out.println("Input one of the options");
				}
			}while(!properOption);
		}
		
		//if it is not player's first turn
		else
		{
			do
			{
				//prompt
				System.out.println("------------------------------");
				System.out.println("| 1.Show rules 2.Hit 3.Stand |");
				System.out.println("------------------------------");
				choice=sc.nextInt();
				
				//decide whether user put one of options
				properOption=choice==1 || choice==2 || choice==3;
				
				if(!properOption)
				{
					System.out.println("Input one of the options");
				}
			}while(!properOption);
		}
		
		return choice;
	}
	
	/**
	 * Perform the action who chose to do
	 * @param choice The choice that user chose to perform
	 * @param player A player who will perform the action
	 */
	public void performOption(int choice, Player player)
	{
		//instruction (help)
		if(choice==1)
		{
			viewRule();
		}
		
		//when hit
		else if(choice==2)
		{
			onHitEvent(player);
		}
		
		//when stand
		else if(choice==3)
		{
			//player finished his/her turn
			player.finish();
		}
	
		//on double down
		else if(choice==4)
		{
			onDoubleDownEvent(player);
		}
		
		//if player get busted, finish player's turn
		if(player.getHand().busted())
		{
			player.finish();
			System.out.printf("END TURN: %s busted \n", player.getName());
		}
	}
	
	/**
	 * when a player hits
	 * @param player one who hits
	 */
	public void onHitEvent(Player player)
	{	
		//add a card to player's hand 
		player.getHand().addCard(dealer.dealCard());
		
		//if player's hand has 5 cards, no more hit
		if(player.getHand().countCards()==5)
		{
			player.finish(); //indicates player finishes his turn
		}
		
	}
	
	/**
	 * when player doubles down
	 * @param player one who doubles downs
	 */
	public void onDoubleDownEvent(Player player)
	{
		//if money is not enough for double amount of bet 
		//Double down: *2
	
		if(player.getMoney()<player.getBet()*2)
		{
			//alert
			System.out.println("You need double amount of money than bet to double down");
		}
		
		else
		{
			//double the bet
			player.setBet(player.getBet()*2);
			
			//indicate player performed doubled down
			player.doubleDown();
			
			//add a card when double down (rule)
			player.getHand().addCard(dealer.dealCard());
			
			//end turn 
			player.finish();
		}
	}
	
	/**
	 * a special method to represent Blackjack object as String
	 */
	public String toString()
	{
		String str;
		
		//when profit of dealer is positive,
		if(dealer.getProfit()>0)
		{
			str=String.format("This game ends in %dth round and dealer made a profit of $%.2f", 
					numRound, dealer.getProfit());
		}
		//when profit of dealer is negative,
		else if(dealer.getProfit()<0)
		{
			str=String.format("This game ends in %dth round and dealer has a loss of $%.2f", 
					numRound, -dealer.getProfit());
		}
		//when profit is zero,
		else 
		{
			str=String.format("This game ends in %dth round and dealer ties with players overall", 
					numRound);
		}
		
		return str;
	}

}
