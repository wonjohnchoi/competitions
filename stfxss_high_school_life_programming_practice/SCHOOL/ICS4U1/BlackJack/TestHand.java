package BlackJack;


/**
 * @author Valery
 * @teacher Mr. Reid
 * @course ICS4U1
 * @project Blackjack
 * @description test program of the hand class
 * @Date April 22, 2010
 */
public class TestHand {
	public static void main(String args[]){
		//instantiate a card
		Card card=new Card(11, "Diamond", "A");
		
		//instantiate a hand
		Hand myHand=new Hand();
		
		//check if empty
		System.out.println("Empty Hand?: "+myHand.isEmpty()); //result: true
		
		//add a card
		myHand.addCard(card);
		
		//check if empty
		System.out.println("Empty Hand?: "+myHand.isEmpty()); //result: false
		
		//count  # of cards
		System.out.println("You has " + myHand.countCards() + " cards"); //result: 1
		
		//if busted (sum>21)
		System.out.println("busted?: " +myHand.busted()); //result: false
		
		//add another card
		myHand.addCard(new Card(10, "Heart", "10"));
		
		//is blackjack? (since A & 10 give blackjack, it's true)
		System.out.println("BlackJack?: " +myHand.isBlackjack()); //result: true
		
		//add another card
		myHand.addCard(new Card(10, "Heard", "K"));
		
		//get sum (A & 10 & K give 21 - A is counted as 1)
		System.out.println("Sum?: " +myHand.getSum()); //result: 21
		
		//busted? nope
		System.out.println("busted?: " +myHand.busted()); //result: false 
		
		//add another card
		myHand.addCard(new Card(3, "Diamond", "3"));
		
		// sum (A & 10 & K & 3) give 24
		System.out.println("Sum?: " +myHand.getSum()); //result: 24
		
		//busted? true since sum>21
		System.out.println("busted?: " +myHand.busted()); //result: true
		
		//check a card at 0 position
		System.out.println("Card at first position?:"+myHand.seeCard(0)); //result: A Diamond
		
		//show the hand
		System.out.println("Hand: "+myHand); // |A Diamond|10 Heart|K Heard|3 Diamond|
		
		
	}
}
