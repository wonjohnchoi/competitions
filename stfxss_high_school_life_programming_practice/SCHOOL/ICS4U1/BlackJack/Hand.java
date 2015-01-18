package BlackJack;

/**
 * @author Valery
 * @teacher Mr. Reid
 * @course ICS4U1
 * @project Blackjack
 * @description Hand class that contains information related to a hand of a player
 * @Date April 22, 2010
 */

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card > hand=new ArrayList<Card>();
	
	
	//default constructor
	
	/**
	 * add a card to hand
	 * @param c card to add
	 */
	public void addCard(Card c)
	{
		hand.add(c);
	}
	
	/**
	 * count number of cards in the hand
	 * @return hand size
	 */
	public int countCards()
	{
		return hand.size();
	}
	
	/**
	 * count number of aces in a card (will be used to calculate sum)
	 * @return number of aces
	 */
	private int countAce()
	{
		int countAce=0;
	
		//for each hand
		for(Card card: hand)
		{
			//is this ace? (has a value of 11?)
			if(card.getValue()==11)
			{
				countAce++;
			}
		}
		return countAce;
	}
	
	/**
	 * get sum of the value of cards in a hand
	 * @return sum of card values
	 */
	public int getSum()
	{
		int sum=0;
		
		for(Card card: hand)
		{
			sum+=card.getValue();
		}
		
		/*
		 * If there are some aces and the hand was over 21 (busted),
		 * use the value of Ace as 1 instead of 11 (subtract 10)
		 */
		for(int i=0;i<countAce() && sum>21;i++)
		{
			sum-=10;
		}
		return sum;
	}
	
	/**
	 * check if the player has blackjack!
	 * @return true if blackjack. false if not blackjack
	 */
	public boolean isBlackjack()
	{
		return (hand.get(0).getValue()+hand.get(1).getValue())==21;
	}
	
	/**
	 * see card at a position
	 * @param pos the position of a card
	 * @return
	 */
	public Card seeCard(int pos)
	{
		return hand.get(pos);
	}
	
	/**
	 * check if player gets over 21,
	 * @return true if sum>21. false if sum<=21
	 */
	public boolean busted()
	{
		return getSum()>21;
	}
	
	/**
	 * check whether the hand is empty
	 * @return true if empty. false if not empty.
	 */
	public boolean isEmpty()
	{
		return hand.size()==0;
	}
	
	/**
	 * show String value of hand
	 */
	public String toString()
	{
		String handStr="";
		for(int i=0;i<hand.size();i++)
		{
			handStr+=("|"+hand.get(i));
		}
		handStr+="|";
		return handStr;
	}
	
	

}
