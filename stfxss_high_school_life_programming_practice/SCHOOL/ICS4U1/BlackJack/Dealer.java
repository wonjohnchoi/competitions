package BlackJack;

/**
 * @author Wayne
 * @teacher Mr. Reid
 * @course ICS4U1
 * @project Blackjack
 * @description Dealer class that contains information related to a dealer
 * @Date April 22, 2010
 */

import java.util.ArrayList;
import java.util.Collections;


public class Dealer extends Player {
	private ArrayList<Card> deck = new ArrayList<Card>();
	private int nextCardPos=0;
	private double profit=0.0D;

	/**
	 * constructor
	 */
	public Dealer() 
	{
		// Fill the deck
		// arrays to represent a card's property
		String suit[] = { "Heart", "Spade", "Diamond", "Club" };
		int value[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };
		String view[] = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };

		// i indicates whether the card has
		// "Heart, "Spade", "Diamond", or "Club"
		for (int i = 0; i < 4; i++) 
		{
			// 13 different values and views of cards
			for (int j = 0; j < 13; j++) 
			{
				// add a card
				deck.add(new Card(value[j], suit[i], view[j]));
			}
		}
	}

	/**
	 * a method to return the profit of dealer
	 * @return profit dealer made
	 */
	public double getProfit()
	{
		return profit;
	}
	
	/**
	 * reset the information of deck to restart player's hand
	 * nextCard position is reset to use deck from the beginning
	 */
	public void reset()
	{
		nextCardPos=0;
		hand =new Hand();

	}

	/**
	 * update the Battle report and money owned
	 * if victory is true, money will increase as much as bet and win record will increase by one.
	 * But, if victory is false, money will decrease as much as bet and lose record will increase by one.
	 * @param victory tell whether player won or lose
	 * @param bet money to be added or subtracted
	 */
	public void update(boolean victory, double bet)
	{
		//victory!
		if(victory)
		{
			//money and wins increase!
			profit+=bet;
			wins++;
		}
		//lost..
		else
		{
			//profit increases and losses increase
			profit-=bet;
			losses++;
		}
	}

	/**
	 * shuffle the deck
	 */
	public void shuffleDeck() 
	{
		Collections.shuffle(deck);
	}

	/**
	 * deal a card
	 */
	public Card dealCard() 
	{
		//More than 52 cards were used
		if(nextCardPos>=52)
		{
			//alert message
			System.out.println("More than 52 cards were used. An additional deck was created");
			//shuffle deck to create a new deck
			Collections.shuffle(deck);
			//reset position to zero
			nextCardPos-=52;
		}

		//get a new card from deck
		Card curCard=deck.get(nextCardPos);

		//update the position of the next card
		nextCardPos++;
		return curCard;
	}

	/**
	 * represent the hand of dealer
	 * @return String representation of dealer's hand (hidden cards shown as [hidden])
	 */
	public String getHiddenHand()
	{
		String handStr="";

		//if dealer has at least one card, show the first card
		if(getHand().countCards()!=0)
		{
			handStr+="|"+hand.seeCard(0);

			//for the rest of cards, show them as hidden
			for(int i=1;i<hand.countCards();i++)
			{
				handStr+=("|[hidden]");
			}

			handStr+="|";
		}
		return handStr;


	}

	/**
	 * a special method that represents Dealer object as String
	 */
	public String toString() {
		String str;
		if(profit<0)
		{
			str= String.format("Dealer made a profit of -$%.2f against players." +
					"\nDealer's battle report: %dW|%dL", profit*(-1), wins, losses);
		}
		
		else if(profit>0)
		{
			str= String.format("Dealer made a profit of $%.2f against players." +
					"\nDealer's battle report: %dW|%dL", profit, wins, losses);
		}
		else
		{
			str= String.format("Dealer ties with players." +
					"\nDealer's battle report: %dW|%dL", wins, losses);
		}
		return str;
	}

}
