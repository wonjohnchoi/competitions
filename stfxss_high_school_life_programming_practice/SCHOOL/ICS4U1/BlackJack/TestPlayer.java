package BlackJack;

/**
 * @author Shazia 
 * @teacher Mr. Reid
 * @course ICS4U1
 * @project Blackjack
 * @description test program of player class
 * @Date April 22, 2010
 */
public class TestPlayer {
	public static void main(String args[])
	{
		//instantiate Player and a Card
		Player player=new Player("gamer", 10000);
		Card c=new Card(10, "Diamond", "A");
		
		//add a card to his hand
		player.getHand().addCard(c);
		
		//set the value of bet
		player.setBet(1000.0);
		System.out.println("Bet: "+player.getBet()); //result:1000.0

		//player double down
		player.doubleDown(); //indicates player doubled down once
		player.setBet(player.getBet()*2); //double the bet
		System.out.println("Bet: "+player.getBet()); //result: 2000.0
		System.out.println("Double downed before?: "+player.doubleDowned());//true
		
		//player finishes his turn? 
		System.out.println("Finished turn?: "+ player.finished()); //false
		
		//set player finishes his/her turn
		player.finish();
		
		//player finishes his turn?
		System.out.println("Finished turn?: "+player.finished()); //true
		
		System.out.println("Money owned: "+player.getMoney()); //10000
		System.out.println("Player name: "+player.getName()); //gamer
		System.out.println("Hand: "+player.getHand()); // |A Diamond|
		
		//win or lose
		System.out.println(player); //Wonjohn owns $10000.00 amount of money.
											//His/her battle report: 0W|0L
		
		//when winning a game with the bet (2000) - true indicates victory
		player.update(true);
		System.out.println(player); //Wonjohn owns $12000.00 amount of money.
		                                     //His/her battle report: 1W|0L
		
		//when lose a game with the bet (2000) - false indicates lose
		player.update(false);
		System.out.println(player);  //Wonjohn owns $10000.00 amount of money.
												//His/her battle report: 1W|1L
		
		
		//reset some settings
		player.reset(); 
		System.out.println("Double downed?: "+player.doubleDowned()); //false
		System.out.println("Finished his/her turn?: "+player.finished()); //false
		System.out.println("Hand: "+player.getHand()); // | <-nothing is there
		System.out.println("Bet: "+player.getBet()); //0
		
	
		
	}
}
