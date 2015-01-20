package BlackJack;


/**
 * @author Marek witczynski
 * @teacher Mr. Reid
 * @course ICS4U1
 * @project Blackjack
 * @description test program of the Card class
 * @Date April 22, 2010
 */
public class TestCard {
	public static void main(String args[])
	{
		//instantiate a card
		Card card=new Card(10, "Diamond", "K");
		
		//test methods 
		System.out.println("Value: "+card.getValue()); //value: 11
		
		System.out.println("Card: "+card); //Card: K Diamond
	}
}
