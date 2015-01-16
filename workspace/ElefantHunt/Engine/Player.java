/**
 * @author Valery Perminov & Wayne Tsai
 * @date June 4th, 2010
 * @supervisor Mr. Reid
 * @course ICS4U1
 */
import java.util.*;

public class Player 
{

	protected int points;
	protected int position;
	protected int supplies;
	protected ArrayList<Animal> capturedAnimals;
	protected ArrayList<Hunter> hiredHunters;
	protected String name;
	protected Player next;
	protected final int MAPSIZE = 29;


	/**
	 * creates an Object of the class that initialize all the instance variables
	   and create a place in memory to hold the Object.
	 * @param newName of the player
	 * @author Valery & Wayne
	 */
	public Player(String newName) 
	{
		name = newName;
		capturedAnimals = new ArrayList<Animal>();
		hiredHunters = new ArrayList<Hunter>();
	}

	/**
	 * creates a method to make the player move forward 
	 * @param space that player goes forward
	 * @author Valery & Wayne
	 */
	public void move(int space) 
	{
		position += space;
		if (position >= MAPSIZE) 
		{
			position = 0;
		}
	}

	/**
	 * set the supplies of the player by getting input from the items
	 * @param items that player uses to pay for hunter
	 * @author Valery & Wayne
	 */
	public void setSupplies(int items) 
	{
		supplies = items;
	}

	/**
	 * @return number of the supplies that player has
	 * @author Valery & Wayne
	 */
	public int getSupplies() 
	{
		return supplies;
	}

	/**
	 * @return the position where player at
	 * @author Valery & Wayne
	 */
	public int getPosition() 
	{
		return position;
	}

	/**
	 * a method that use the levels of the animals and add as points to the
	   player
	 * @author Valery & Wayne
	 */
	public void addPoints(int score) 
	{
		points += score;
	}

	/**
	 * @return the points that player got already
	 * @author Valery & Wayne
	 */
	public int getPoints() 
	{
		return points;
	}

	/**
	 * add animals to the player when the player capture an animal
	 * @param animal that player has captured
	 * @author Valery & Wayne
	 */
	public void addAnimal(Animal animal) 
	{
		capturedAnimals.add(animal);
	}

	/**
	 * @return number of animals
	 * @author Valery & Wayne
	 */
	public int numAnimals() 
	{
		return capturedAnimals.size();
	}

	/**
	 * @return number of hunters
	 * @author Valery & Wayne
	 */
	public int numHunters() 
	{
		return hiredHunters.size();
	}

	/**
	 * Removes a random animal from LinkedList If there is no animals apparent
	   within the LinkedList Print > "There is no Animals"
	 * @author Valery & Wayne
	 */
	public Animal removeRandomAnimal() 
	{
		if (capturedAnimals.size() > 0) 
		{
			return capturedAnimals.remove((int) (capturedAnimals.size() * Math.random()));
		} else 
		{
			System.out.println("There is no animals");
		}
		return null;
	}

	/**
	 * Search through Hunter LinkedList for a specific hunter
	 * @param name of the hunters
	 * @return Hunter information
	 * @author Valery & Wayne
	 */
	public Hunter getHunter(String name) 
	{
		Hunter hunter = null;
		for (int i = 0; i < hiredHunters.size(); i++) 
		{
			if (hiredHunters.get(i).getName().equalsIgnoreCase(name)) 
			{
				hunter = hiredHunters.get(i);
			}
		}
		return hunter;
	}

	/**
	 * Add new Hunter
	 * @param hunter that are hired by player
	 * @author Valery & Wayne
	 */
	public void hireHunter(Hunter hunter) 
	{
		hiredHunters.add(hunter);
	}

	/**
	 * Remove specified Hunter 
	 * @param name of the hunters who are fired by player
	 * @author Valery & Wayne
	 */
	public Hunter fireHunter(String name) 
	{
		for (int i = 0; i < hiredHunters.size(); i++) 
		{
			if (hiredHunters.get(i).getName().equalsIgnoreCase(name)) 
			{
				return hiredHunters.remove(i);
			}
		}

		return null;
	}

	/**
	 * @return all hunters
	 * @author Valery & Wayne
	 */
	public ArrayList<Hunter> getAllHunters() 
	{
		return hiredHunters;
	}

	/**
	 * @return all animals
	 * @author Valery & Wayne
	 */
	public ArrayList<Animal> getAllAnimals()
	{
		return capturedAnimals;
	}

	public String getName() 
	{
		return name;
	}

	/**
	 * Return player constructor in String format
	 * @author Valery & Wayne
	 */
	public String toString() 
	{
		String format = "";
		return format  + "Player: " + name
				+ "\n #Supplies: " + getSupplies() + "\n #Points: "+ points +"\n #Animals: "
				+ numAnimals() + "\n #Hunters: " + numHunters();

	}
	
	public static void main(String args[]){
		System.out.println(new Player("WJ"));
	}
}
