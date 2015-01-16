package BlackJack;


/**
 * @author Shazia 
 * @teacher Mr. Reid
 * @course ICS4U1
 * @project Blackjack
 * @description Player class that includes information about a player
 * @Date April 22, 2010
 */
public class Player {
	protected Hand hand=new Hand();
	protected int wins = 0;
	protected int losses = 0;
	
	private double money=0.0D;
	private String name="";
	private double bet=0.0D;
	private boolean doubleDowned=false;
	private boolean finished=false;
	
	/**
	 * constructor
	 */
	public Player()
	{
		this("anonymous", 0.0D);
	}
	
	/**
	 * constructor
	 * @param playerName name of player
	 * @param playerMoney money that player has
	 */
	public Player(String playerName, double playerMoney)
	{
		name=playerName;
		money=playerMoney;
	}
	
	/**
	 * reset player's states for the next game
	 */
	public void reset()
	{
		//at the beginning of game, doubleDowned, finished and hand should have initial value
		//since player didn't split, double down, or got hand yet at the beginning
		doubleDowned=false;
		finished=false;
		hand=new Hand();
		bet=0.0D;
	}
	
	/**
	 * indicates double down happened by setting the value of doubleDowned true
	 */
	public void doubleDown()
	{
		doubleDowned=true;
	}
	
	/**
	 * if double down happened, return true
	 * if double down didn't happen, return false
	 * @return doubleDowned (contains info of whether double down happened or not)
	 */
	public boolean doubleDowned()
	{
		return doubleDowned;
	}
	
	/**
	 * check if the player's turn finished
	 * @return If finished, ture. If not finished, false
	 */
	public boolean finished()
	{
		return finished;
	}
	
	/**
	 * set the player's turn finished
	 */
	public void finish()
	{
		finished=true;
	}
	
	/**
	 * get the hand of player
	 * @return hand (groups of cards)
	 */
	public Hand getHand()
	{
		return hand;
	}
	
	/**
	 * get player's money value
	 * @return money
	 */
	public double getMoney()
	{
		return money;
	}
	
	/**
	 * show the player's name
	 * @return return name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * show the value of the bet value
	 * @return the value of the current bet value
	 */
	public double getBet()
	{
		return bet;
	}
	
	/**
	 * set new value of bet
	 * @param newBet the value of new bet
	 */
	public void setBet(double newBet)
	{
		bet=newBet;
	}
	
	/**
	 * update the Battle report and money owned
	 * if victory is true, money will increase as much as bet and win record will increase by one.
	 * But, if victory is false, money will decrease as much as bet and lose record will increase by one.
	 * @param victory tell whether player won or lose
	 */
	public void update(boolean victory)
	{
		//victory!
		if(victory)
		{
			//money and wins increase!
			money+=bet;
			wins++;
		}
		//lost..
		else
		{
			//money increases and losses increase
			money-=bet;
			losses++;
		}
	}
	
	/**
	 * It returns "name: Money owned: money, Battle report: win|loss"					
	 * For example, "James: Money owned: $10000, Battle report: 3W|4L"
	 */
	public String toString()
	{
		return String.format("%s owns $%.2f amount of money.\nHis/her battle report: %dW|%dL",name,money,wins,losses);
	}


}
