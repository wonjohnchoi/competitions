package BlackJack;


/**
 * @author Wayne
 * @teacher Mr. Reid
 * @course ICS4U1
 * @project Blackjack
 * @description Test program of the Dealer class
 * @Date April 22, 2010
 */
public class TestDealer {
	public static void main(String args[])
	{
		//instantiate
		Dealer theDealer=new Dealer();
		Player thePlayer=new Player("Wayne", 10000.0D);
		
		theDealer.shuffleDeck(); //shuffle the deck!
		
		System.out.println("Dealer's hand: "+theDealer.dealCard()); //a random card that is dealt		
		
		//deal cards while theDealer's hand's sum is under 17 (see rule)
		while(theDealer.getHand().getSum()<17)
		{
			theDealer.getHand().addCard(theDealer.dealCard());
		}
		
		System.out.println("Hand: " +theDealer.getHand()); //show full hand 
		System.out.println("Hand with hidden cards: "+theDealer.getHiddenHand()); //only dealer's first card is shown
		
		//battle report
		System.out.println(theDealer);//Dealer made a profit of $0.00 against players.
												    //Dealer's battle report: 0W|0L
		//set player's bet
		thePlayer.setBet(1000);
		
		//on victory (true==victory | false==lose)
		theDealer.update(true, thePlayer.getBet());
		System.out.println(theDealer);//Dealer made a profit of $1000.00 against players.
													//Dealer's battle report: 1W|0L
		
		//on lose (true==victory | false==lose)
		theDealer.update(false, thePlayer.getBet());
		System.out.println(theDealer);//Dealer made a profit of $0.00 against players.
													//Dealer's battle report: 1W|1L
		
		System.out.println("Dealer's profit: "+theDealer.getProfit()); //0.0
	}
}
