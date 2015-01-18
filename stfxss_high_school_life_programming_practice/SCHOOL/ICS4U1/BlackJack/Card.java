package BlackJack;

/**
 * @author Marek witczynski
 * @teacher Mr. Reid
 * @course ICS4U1
 * @project Blackjack
 * @description Card class that contains information related to a card
 * @Date April 22, 2010
 */
public class Card {
	protected int value;
	protected String suit;
	protected String view;
	
	/**
	 * constructor
	 * @param newValue value of card //2 3 4 5 6 7 8 9 10 10 10 10 11(1)
	 * @param newSuit suit of card //Heart: 0 Spade: 1 Diamond: 2 Club: 3 
	 * @param newView view of card //2 3 4 5 6 7 8 9 10 J Q K A
	 */
	
	public Card(int newValue, String newSuit, String newView)
	{
		value=newValue;
		suit=newSuit;
		view=newView;
	}
	
	/**
	 * gives the value of a card
	 * @return value of the card
	 */
	public int getValue()
	{
		return value;
	}
	
	/**
	 * String representation of the card
	 */
	public String toString()
	{
		return view+" "+suit;
	}






}
