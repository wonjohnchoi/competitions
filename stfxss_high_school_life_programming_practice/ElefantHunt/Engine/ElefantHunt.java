
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * ElefantHunt class that holds the main functions of the game, and the map
 * @author Wonjohn Choi
 */
public class ElefantHunt 
{
	protected int supplies;
	protected ArrayList<Animal> animalPool;
	protected ArrayList<Hunter> hunterPool;
	protected Player[] players;
	protected int [] map;
	protected GUI gui;

	/*
	 * Keep variables as 'final'
	 * They should not be changed during a game
	 */
	protected final int PORT = 0;
	protected final int SPACE = 1;
	protected final int SWAMP = 2;
	protected final int HUNT1 = 3;
	protected final int HUNT2 = 4;
	protected final int HUNT3 = 5;
	protected final int ENDPOINT = 100;
	
	/**
	 * Constructor for the game. Initializes all values.
	 * @throws InterruptedException 
	 */
	public ElefantHunt() throws InterruptedException 
	{	
		//GUI Message
		gui = new GUI();	
		
		initializeEngine();
	}

	/**
	 * Simplified method to call initializing methods.
	 * @throws InterruptedException 
	 */
	public void initializeEngine() throws InterruptedException 
	{
		initializePlayers();
		gui.initialize(players);
		intializeSupplies();
		initializeAnimals();
		initializeHunters();
		initializeMap();
		
	}

	/**
	 * initialize map filling it with data 
	 * @author Wonjohn Choi
	 */
	public void initializeMap()
	{
		//represent the map
		map = new int[] {
				PORT, SPACE, SPACE, HUNT2, SPACE, HUNT2, SPACE, SWAMP, 
				SPACE, SPACE, HUNT1, SPACE, SPACE, HUNT1, HUNT2, SWAMP, 
				SPACE, HUNT3, SWAMP, SPACE, SPACE, HUNT3, SPACE, SPACE, 
				SWAMP, SPACE, SPACE, HUNT1, SPACE
		};
		
		gui.writeLog("Map information is added.");
	}

	/**
	 * initialize an ArrayList with the ADT Hunter filling it with data 
	 * @author Wonjohn Choi
	 */
	public void initializeHunters()
	{
		//initialize
		hunterPool = new ArrayList<Hunter>();

		//add sub hunters
		hunterPool.add(new Hunter(2, "Sam Smyle", false));
		hunterPool.add(new Hunter(2, "Colonel", false));
		hunterPool.add(new Hunter(3, "Zartan", false));
		hunterPool.add(new Hunter(3, "Ed Oop", false));
		hunterPool.add(new Hunter(1, "Pistol Pete", false));
		hunterPool.add(new Hunter(2, "Chief", false));
		hunterPool.add(new Hunter(3, "Tom Trap", false));
		hunterPool.add(new Hunter(1, "Twoleft", false));
		hunterPool.add(new Hunter(5, "Aubrey", false));
		hunterPool.add(new Hunter(1, "Fritz", false));
		hunterPool.add(new Hunter(4, "Ned Net", false));
		hunterPool.add(new Hunter(2, "Bill Brute", false));
		hunterPool.add(new Hunter(2, "Rongwae", false));
		hunterPool.add(new Hunter(1, "Louie", false));

		//Hunter ArrayList is shuffled at the beginning to make sure that player gets random hunters
		Collections.shuffle(hunterPool);
		
		gui.writeLog("Hunter information is added");
	}

	/**
	 * Add players during the beginning of the game
	     * @author Wonjohn Choi
	 * @throws InterruptedException 
	 */
	public void initializePlayers() throws InterruptedException
	{

		//variable to store user's response
		int numPlayer = gui.promptNumPlayer();
		
		//initiate the ArrayList object to store the players
		players = new Player[numPlayer];
		
		CharacterChooser cc = new CharacterChooser(players);

		while(true){
			if(cc.counter == numPlayer){
				break;
			}
		}
		
	
		
		/*
		//for each iteration of # of players,
		for(int i = 0;i < numPlayer; i++)
		{
			//gui.writeLog("Player "+(i+1)+": choose your character!");

			while(true){
				if(cc.chose){
					break;
				}
			}
			cc.chose = false;
			
			//initialize and store player
			players[i] = new Player(cc.chosenCharacter);
			//gui.writeLog("Player "+(i+1)+" chose "+cc.chosenCharacter);
		}
		
		gui.writeLog("Player information is added.");
		*/
	}

	/**
	 * Initializes number of supplies available in the game
	 */
	public void intializeSupplies() 
	{
		supplies = 54;
		gui.writeLog("Supply information is added.");
	}

	/**
	 * Initializes the animal pool for the game
	 */
	public void initializeAnimals() 
	{
		animalPool = new ArrayList<Animal>();
	
		for (int i = 0; i < 5; i++) 
		{
			animalPool.add(new Animal(10, "Elefant", false));
		}
		animalPool.add(new Animal(16, "Mad Mom", true));
		animalPool.add(new Animal(6, "Baboon", false));
		animalPool.add(new Animal(5, "Python", false));
		animalPool.add(new Animal(8, "Giraffe", false));
		animalPool.add(new Animal(12, "Eagle", false));
		animalPool.add(new Animal(9, "Gorilla", false));
		animalPool.add(new Animal(7, "Hyena", false));
		animalPool.add(new Animal(7, "Antelope", false));
		animalPool.add(new Animal(8, "Crocodile", false));
		animalPool.add(new Animal(10, "Cheetah", false));
		animalPool.add(new Animal(5, "Aardvark", false));
		animalPool.add(new Animal(5, "Ostrich", false));
		animalPool.add(new Animal(6, "Zebra", false));
		animalPool.add(new Animal(9, "Lion", false));
		animalPool.add(new Animal(3, "Bush Baby", false));
		animalPool.add(new Animal(9, "Rhino", true));
		animalPool.add(new Animal(9, "Lion", true));
		animalPool.add(new Animal(8, "Crocodile", true));
		animalPool.add(new Animal(7, "Wart Hog", true));
	
		Collections.shuffle(animalPool);
		gui.writeLog("Animal information is added.");
	}

	/**
	 * Method that starts the game and controls general user messages
	 * @throws InterruptedException 
	 */
	public void onStartGame() throws InterruptedException
	{
		//for each index,
		for(int i=0;i<players.length;i++)
		{   
			//get current player
			Player player = players[i];
	
			//hire the main hunter
			player.hireHunter(new Hunter(1, player.getName(), true));
	
			//console message
			
		}
		gui.writeLog("Main hunters are added to each and every player");
	
		
		//console message
		gui.writeLog("\nGame starts!\n");
	
		//execute the actions of players (0th player starts first) - recursively calls next player
		onPlayerTurns(0);
	
		//declare and initialize a variable that will store winner Player
		Player winner = null;
	
		//for each player,
		for(Player player: players)
		{
			//if one's player is over the ending point,
			if(player.getPoints() >= ENDPOINT)
			{
				//set that he won!
				winner = player;
			}
		}
	
		//console meesage to show the final winner
		System.out.println(winner.getName() +" won the game with "+winner.getPoints()+" points");
		
		//GUI
		gui.writeLog(winner.getName() +" wins the game with "+winner.getPoints()+" points");
	
	}

	/**
	 * Method to call when player lands on a hunting space
	 * @param player
	 * @author Philip Markowski
	 * @throws InterruptedException 
	 */
	public void onHunt(Player player) throws InterruptedException 
	{
		if (animalPool.size() > 0)
		{
			int numAnimals = 0;
			Scanner sc = new Scanner(System.in);

			//Get the number of animals that will appear depending on the space
			if (map[player.getPosition()] == HUNT1) 
			{
				numAnimals = 1;
			} 
			else if (map[player.getPosition()] == HUNT2) 
			{
				numAnimals = 2;
			} 
			else if (map[player.getPosition()] == HUNT3) 
			{
				numAnimals = 3;
			}
			System.out.println(numAnimals + " animals appeared:");

			//Add as many animals that will appear to the array list
			ArrayList<Animal> hunted = new ArrayList<Animal>();
			for (int i = 0; i < numAnimals; i++)
			{
				hunted.add(animalPool.remove(0));
				System.out.println(hunted.get(i));
			}

			//Create array list of hunter arraylists
			ArrayList<ArrayList<Hunter>> huntingParties = new ArrayList<ArrayList<Hunter>>();
			//Call GUI method to choose hunters
			HuntingSpaces hs = new HuntingSpaces(player, hunted);
			huntingParties = hs.AssignHunters();
			//Do the combat
			for (int i = 0; i < numAnimals; i++)
			{
				//If animals are captured successfully add them to the player's animals
				if (isCaptured(hunted.get(i), huntingParties.get(i)))
				{
					System.out.println(player.getName() + " captured a " + hunted.get(i));
					gui.writeLog(player.getName() + " captured a " + hunted.get(i));
					Thread.sleep(2000);
					player.addAnimal(hunted.get(i));
				}
				//If hunting fails and the animal is a predator, player must lose a hunter
				else if (hunted.get(i).isPredator() && player.numHunters() > 1)
				{
					System.out.println("Capture failed! Enter name of hunter to remove:");
					gui.writeLog(player.getName() + " captured a " + hunted.get(i));
					Thread.sleep(2000);
					String hunter = sc.nextLine();
					player.fireHunter(hunter);
					animalPool.add(hunted.get(i));
				}
				//If hunting fails but the animal is not a predator, nothing happens
				else
				{
					System.out.println("Capture failed!");
					gui.writeLog("Capture Failed!");
					Thread.sleep(2000);
					animalPool.add(hunted.get(i));
				}
				//Shuffle animal pool
				Collections.shuffle(animalPool);
			}
		}
		else
		{
			System.out.println("No more animals!");
			gui.writeLog("No more animals exist in hunting places!");
			Thread.sleep(2000);
		}
	}

	/**
	 * Action happens when player lands on an empty space
	 * @param player the player who will act
	 * @author Wonjohn Choi
	 * @throws InterruptedException 
	 */
	public void onSpace(Player player) throws InterruptedException
	{
		//declare and initialize variables to store required supplies and owned supplies
		int requiredSupplies = player.numHunters(); //# of hunters indicate required supplies
		int ownedSupplies =player.getSupplies(); //# of suppplies each people own

		//if there is enough supplies,
		if(requiredSupplies <= ownedSupplies)
		{
			//player's # of supplies are updated
			player.setSupplies(ownedSupplies - requiredSupplies);

			//port gets the paid supplies
			supplies +=requiredSupplies;

			//console message
			System.out.println(player.getName()+"'s supplies decrease from "+ownedSupplies+"  to "+player.getSupplies());
			
			//GUI message
			gui.writeLog(player.getName()+"'s supplies decrease from "+ownedSupplies+"  to "+player.getSupplies());
		}
		else 
		{
			//if there is not enough supplies, player's supplies become zero
			player.setSupplies(0);
			
			//GUI/console
			System.out.println("You do not have any supplies");
			gui.writeLog("You do not have any supplies");
			Thread.sleep(2000);
			
			//port gets the paid supplies
			supplies +=ownedSupplies;

			//if there are any hunters other than the main hunters, 
			if(player.numHunters()>1)
			{
				//update console/GUI message
				System.out.println("You do not have enough supplies to feed your hunters");
				gui.writeLog("You do not have enough supplies to feed your hunters");
				Thread.sleep(2000);
			
				//create message to tell user than he/she should fire some hunters
				String msg = "You should fire "+(player.numHunters()-ownedSupplies-1)+" hunters";
				
				//GUI/console message
				System.out.println(msg);
				gui.writeLog(msg);
				Thread.sleep(2000);
				
				//create variable to store # of players to be fired
				int numFired =player.numHunters()-ownedSupplies-1;
				
				//for the required # of hunters,
				for(int i=0;i<numFired;i++)
				{
					//create GUI object to fire
					FireHunterNow fire = new FireHunterNow(player);
					
					//get the name of hunter to fire
					String name = fire.byeHunter();
					
					//fire the hunter by name using player's method
					//remember the hunter object using firedHunter variable
					Hunter firedHunter = player.fireHunter(name);
					gui.writeLog("You fired "+firedHunter);
					
					//if the firedHunter exists,
					if(firedHunter!=null)
					{
						//add it to the hunterPool at port
						hunterPool.add(firedHunter);
					}
				}
				
				
				Thread.sleep(2000);

			}


		}

	}

	/**
	 * Action happens on port
	 * @param player player on action
	 * @author Wonjohn Choi
	 * @throws InterruptedException 
	 */
	public void onPort(Player player) throws InterruptedException
	{ 
		/* covered console message by GUI
		//console message
		System.out.println("Do you want to ");
		System.out.println("                        1. Get 4 extra supplies");
		System.out.println("                        2. Get an extra hunter");
		System.out.println("                        3. Roll a die to move");

		//receive the choice of user (1, 2, or 3)
		Scanner sc=new Scanner(System.in);
		int choice = sc.nextInt();*/

		//declare and initialize GUI object Options
		Options op = new Options();
		
		//Using op Object, you can get user's choice of what they want to do
		int choice = op.getButtonClicked();
		
		//if player asks for extra supplies,
		if(choice == 1)
		{
			//if supplies stored in port is enough,
			if(supplies >= 4)
			{
				//player's supplies increase by 4
				player.setSupplies(player.getSupplies() + 4);
				supplies-=4;

				//console message
				System.out.println("Total supplies: "+player.getSupplies()+" = "+(player.getSupplies()-4)+" + 4");
				
				//update GUI message
				gui.writeLog("Total supplies: "+player.getSupplies()+" = "+(player.getSupplies()-4)+" + 4");
				Thread.sleep(2000);
			}
			//if supplies are not enough to give players,
			else
			{
				//console message
				System.out.println("There is not enough supplies stored on port: "+supplies);
				
				//update GUI message
				gui.writeLog("There is not enough supplies stored on port: "+supplies);
				Thread.sleep(2000);

				//repeat question since there is not enough supplies
				onPort(player);
			}


		}
		//if player asks for extra hunter
		else if(choice == 2)
		{
			if(!hunterPool.isEmpty())
			{
				//take an hunter from port
				Hunter newHunter = hunterPool.remove(0);

				//player hires the hunter
				player.hireHunter(newHunter);

				//console message
				System.out.println(newHunter + " has been hired");
				
				//GUI update
				gui.writeLog(newHunter + " has been hired");
				Thread.sleep(2000);
			}
			else
			{
				//console message
				System.out.println("There is not enough hunters left!");
				
				//GUI update
				gui.writeLog("There is not enough hunters left!");
				Thread.sleep(2000);

				onPort(player);
			}
		}
		//if player wants to go for adventure,
		else if(choice == 3)
		{
			//roll a die and move
			rollAndMove(player);

			int pos = player.getPosition();
			
			//update gui
			//TODO guimap.repaint();
			

			//if landing location is an empty space,
			if(map[pos] == SPACE)
			{
				//Use method for action on an empty space
				onSpace(player);
			}
			//if landing location is a swamp,
			else if(map[pos] == SWAMP)
			{
				//Use method for action on a swamp.
				onSwamp(player);
			}

			//if landing location is a hunting place (other cases are covered above)
			else
			{
				//Do hunting
				onHunt(player);
			}
		}

	}

	/**
	 * Action happens on a player's turn
	 * @param player the player of subject
	 * @author Wonjohn Choi
	 * @throws InterruptedException 
	 */
	public void onPlayerTurns(int idx) throws InterruptedException
	{
		Player player = players[idx];
		System.out.println("************************************************");
		System.out.println(player.getName()+"'s turn starts!");
		
		//GUI stuff
		gui.writeLog(player.getName()+"'s turn starts!");
		Thread.sleep(1000);
		
		//if player has hunters other than main,
		if(player.numHunters() >1)
		{
			//call a method to fire hunters that player want to fire
			onFireHunters(player);
		}


		//if player is on a port,
		if(map[player.getPosition()] == PORT)
		{
			//Use method for action on port
			onPort(player);
		}
		else //if it's not on port, 
		{
			//player is forced to roll and move.
			rollAndMove(player);
			
			//update gui
			//TODO guimap.repaint();
			
			//variable to store position of player
			int pos = player.getPosition();
			
			

			//if landing location is an empty space,
			if(map[pos] == SPACE)
			{
				//Use method for action on an empty space
				onSpace(player);
			}
			
			//if landing location is a swamp,
			else if(map[pos] == SWAMP)
			{
				//Use method for action on a swamp.
				onSwamp(player);
			}
			
			//if landing location is a port (it means that player finished a cycle)
			else if(map[pos] == PORT)
			{
				//if player has any animal,
				if(player.numAnimals() != 0)
				{
					//get captured animals of the player
					ArrayList<Animal> capturedAnimals = player.getAllAnimals();

					//if there is any animal left,
					while(capturedAnimals.size()!=0)
					{
						//remove the animal and convert them to points
						player.addPoints(capturedAnimals.remove(0).getLevel());
					}
				}

				//console & GUI messages
				System.out.println("Points updated: "+player.getPoints());
				gui.writeLog("Points updated: "+player.getPoints());
				Thread.sleep(2000);
			}

			//if landing location is a hunting place (other cases are covered above)
			else
			{
				//Do hunting
				onHunt(player);
			}

		}
		
		gui.updatePlayerInfo();

		//if the current player's score is under 100, player is not finished
		if(player.getPoints()<ENDPOINT)
		{
			//continue the turns using the next player
			onPlayerTurns((idx+1)%players.length);
		}
		
	}

	/**
	 * When a player lands on a swamp, if they have any animals, they lose one
	 * randomly, and if there are no animals they lose one supply, and if they
	 * have no supplies, nothing happens
	 * @param player
	 * @author Philip Markowski
	 * @throws InterruptedException 
	 */
	public void onSwamp(Player player) throws InterruptedException 
	{
		if (player.numAnimals() > 0) 
		{
			Animal removed =player.removeRandomAnimal();
			System.out.println("Player lost " + removed);
			gui.writeLog("Player lost " + removed);
			Thread.sleep(2000);
		} 
		else if (player.getSupplies() > 0) 
		{
			player.setSupplies(player.getSupplies() - 1);
			System.out.println(player.getName() + "'s supplies decrease from "+(player.getSupplies()+1) + " to "+player.getSupplies());
			gui.writeLog(player.getName() + "'s supplies decrease from "+(player.getSupplies()+1) + " to "+player.getSupplies());
			Thread.sleep(2000);
		}
		else
		{
			gui.writeLog("Nothing happened!");
			Thread.sleep(1000);
		}
	}

	/**
	 * Ask player if he/she wants to fire hunters and fire them
	 * @param player who will fire hunters
	 * @throws InterruptedException 
	 */
	public void onFireHunters(Player player) throws InterruptedException
	{
	
		/* covered by gui
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to fire any hunters prior to the start of your turn? (Y/N)");
		
		String choice = sc.next();*/

		//declare and initialize MessagePanel to ask question
		MessagePanel msg = new MessagePanel("Do you want to fire any hunters(Y/N)");
		
		//yes will be true/false if user click yes/no
		//create a variable called yes
		boolean yes = msg.onYNButtonClicked();
		
		//if user clicks yes,
		if(yes)
		{
			//console/GUI messages
			System.out.println("You chose to fire your hunters!");
			gui.writeLog("You chose to fire your hunters!");
			
			//use FireHunterNow object to fire a hunter!
			FireHunterNow fire = new FireHunterNow(player);
			String name = fire.byeHunter();
			
			//use the name returned by byeHunter method to fire a hunter
			Hunter firedHunter = player.fireHunter(name);
			
			//if fired hunter exists,
			if(firedHunter!=null)
			{
				//add the hunter to the hunter pool on port
				hunterPool.add(firedHunter);
			}
			
			//GUI/console message
			System.out.println(name+" is fired by you!");
			gui.writeLog(name+" is fired by you!");
			
			//if there are any hunters left other than main,
			if(player.numHunters()>1)
			{
				//ask again recursively 
				//Do not need to worry about StackOverFlow Exception as # of players are limited
				onFireHunters(player);	
			}
			
		}
		//if user clicks no,
		else 
		{
			//console message
			System.out.println("You chose not to fire any hunter.");
		}

	}

	/**
	 * String representation of this class: contains information about # of players, hunters, and animals
	 * @author Wonjohn Choi
	 */
	public String toString()
	{
		//String manipulation to include the required information
		String output = "";
		output+="# players = "+players.length;
		output+="/n# hunters = "+hunterPool.size();
		output+="/n# animals = "+animalPool.size();
	
		return output;
	}

	/**
	 * method to get a random number from 1 to 6
	 * @return the random number obtained
	 * @author Wonjohn Choi
	 */
	private int rollDie()
	{
		return (int)(Math.random()*6)+1;
	}

	/**
	 * get the name of location where player is located currently 
	 * solely for console message purpose
	 * @param player the player on subject
	 * @return the String representation of the location
	 * @author Wonjohn Choi
	 */
	private String getSiteName(Player player)
	{
		//variable to store the destination name
		String dest="";
	
		//variable to store position of player
		int pos = player.getPosition();
	
		//if player is on swamp,
		if(map[pos] == SWAMP)
		{
			dest = "swamp";
		}
		//if player is on an empty space,
		else if(map[pos] == SPACE)
		{
			dest = "empty space";
		}
		//if player is on a port,
		else if(map[pos] == PORT)
		{
			dest = "port";
		}
		//else - there are hunting places left
		else 
		{
			dest = "hunting place";
		}
	
		//return the destination
		return dest;
	}

	/**
	 * A method that contains messages for user when rolling a die and actually moves player to other place
	 * @param player the player who will roll a die and move
	 * @author Wonjohn Choi
	 * @throws InterruptedException 
	 */
	private void rollAndMove(Player player) throws InterruptedException
	{
		//create a variable to store the message that will be used for GUI
		String msg="";
		
		//console message
		System.out.println("Roll a die!");
		msg+="Rolled a die!";

		//get a random number between 1~6 using rollDie() method
		int dist = rollDie();

		//console message
		System.out.println("and got "+dist);
		msg+=" Got "+dist;
		
		//move player with the distance from the die
		player.move(dist);

		//console message to show where the player lands
		System.out.println("Arrived at "+getSiteName(player));
		msg+=(" Arrived at "+getSiteName(player));
		
		//update GUI message
		gui.writeLog(msg);
		Thread.sleep(2000);
	}

	/**
	 * Checks to see if the animal is captured by the hunters given
	 * @param prey
	 * @param hunters
	 * @return Whether the animal is successfully captured or not
	 * @author Philip Markowski
	 */
	private boolean isCaptured(Animal prey, ArrayList<Hunter> hunters)
	{
		boolean isCaptured = false;

		//Roll die to see if animal gets away
		int roll = rollDie();
		System.out.println("Rolled a " + roll);

		if (roll > 1)
		{	
			//Add up total skill level of the hunters going after the animal
			int totalLvl = 0;
			for (int i = 0; i < hunters.size(); i++)
			{
				totalLvl = totalLvl + hunters.get(i).getLevel();
			}
			//If the total level plus the die roll is greater than the animal's level, it is captured, otherwise, the animal gets away
			if (prey.getLevel() <= totalLvl + roll)
			{
				System.out.println("Captured a " + prey.getName());
				isCaptured = true;
			}
			else if (prey.getLevel() > totalLvl + roll)
			{
				System.out.println(prey.getName() + " got away; skill too low");
				isCaptured = false;
			}
			//If a 1 is rolled, the animal automatically gets away 
		}
		else if (roll == 1)
		{
			System.out.println(prey.getName() + " got away");
			isCaptured = false;
		}

		return isCaptured;
	}


}
