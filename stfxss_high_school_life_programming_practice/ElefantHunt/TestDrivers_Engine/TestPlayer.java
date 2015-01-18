/**
 * @author Valery Perminov & Wayne Tsai
 * @date June 4th, 2010
 * @supervisor Mr. Reid
 * @course ICS4U1
 */

import java.util.*;
public class TestPlayer 
{
	/**
	 * @param args
	 * @throws InterruptedException
	 */

	public static void main(String[] args) throws InterruptedException 
	{
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String name1 = "";
		System.out.println("P l a y e r s");
		System.out.println();
		System.out.print("Player: ");
		
		//initialize the player
		Player player1 = new Player(name1 = sc.nextLine());
		
		//initialize the main hunter
		Hunter hunter1 = new Hunter(1, name1, true);
		
		//player hire himself as a hunter
		player1.hireHunter(hunter1);
		System.out.println("*************************************");
		
		//initialize the array for the animal
		ArrayList<Animal> animals = new ArrayList<Animal>();
		
		//initialize the animals
		Animal wayne = new Animal(10, "wayne", true);
		Animal wonjohn = new Animal(5, "wonjohn", false);
		Animal sannie = new Animal(12, "sannie", true);
		Animal megan = new Animal(15, "megan", true);
		Animal philip = new Animal(1, "philip", false);
		
		//add all the animals to the player
		animals.add(wayne);
		animals.add(wonjohn);
		animals.add(sannie);
		animals.add(megan);
		animals.add(philip);
		
		//initialize the hunter
		ArrayList<Hunter> hunters = new ArrayList<Hunter>();
		Hunter meric = new Hunter(5, "meric", false);
		Hunter qicheng = new Hunter(3, "qicheng", false);
		Hunter eric = new Hunter(5, "eric", false);
		
		//add the hunters to the player
		hunters.add(meric);
		hunters.add(qicheng);
		hunters.add(eric);
		
		//set variable
		int num = 0;
		
		//print out all the information of the animals that player owns
		System.out.println("Animals Available: \n" + wayne + ", \n" + wonjohn
				+ ", \n" + sannie + ", \n" + megan + ", \n" + philip + "\n");

		while (num != 10) 
		{
			System.out.println("*************************************");
			
			//print out the choices that player has
			System.out.println("1. Add Animal  2. Remove Animal  3. Hire A Hunter  "
							+ "4. Fire A Hunter 5. Hunter Information 6. Move 7. Pick supplies \n8. Position "
							+ "9. Supplies 10. Finish");
			num = sc.nextInt();

			if (num == 1) 
			{
				if (player1.numAnimals() < animals.size()) 
				{
					//randomly get an animal from the array
					double Ran = animals.size() * Math.random();
					int random =  (int)Ran;
					
					//add the random animal to the player
					player1.addAnimal(animals.get(random));   
					
					//get the level of the animal as the points
					player1.addPoints(animals.get(random).getLevel());
					System.out.println("You Have gained a random Animal");
				} 
				else 
				{
					System.out.println("There are no more Animals available.");
				}
			} 
			else if (num == 2) 
			{
				//remove the animal randomly from the player 
				player1.removeRandomAnimal();
				if (player1.numAnimals() > 0) 
				{
					System.out.println("You Have removed a random Animal");
				}
			} 
			
			/*
			 * Hire a hunter randomly from the array if there is hunter available
			 */
			
			else if (num == 3) 
			{				
				if (player1.numHunters() < hunters.size())
				{
					player1.hireHunter(hunters.get((int) (hunters.size() * Math
							.random())));
					System.out.println("You Have gained a random Hunter");
				} 
				else 
				{
					System.out.println("There are no more Hunters available.");
				}

			} 
			else if (num == 4) 
			{
				//remove the hunter from the player by input the name of the hunter
				System.out.println("Hunters available: "
						+ player1.getAllHunters());
				System.out.print("\nName: ");
				player1.fireHunter(sc.next());
				System.out.println("You Have removed the Hunter");
			} 
			else if (num==5)
			{
				//searching for the specific hunter
				System.out.println("Information on specific Hunter: \n");
				System.out.println("Name: "+player1.getHunter(sc.next()));
			}
			else if (num == 6) 
			{
				//shows the number on the die which make the player move
				System.out.print("Number on die : ");
				player1.move(sc.nextInt());
			} 
			else if (num == 7) 
			{
				//shows the number on the supplies' card that player get 
				System.out.print("Number on supply card : ");
				player1.setSupplies(sc.nextInt());
			}
			else if (num == 8) 
			{
				//shows the player position 
				System.out.println("Your position is : "+player1.getPosition());
			}
			else if (num == 9) 
			{
				//shows how many supplies does player has
				System.out.println("Your previous supply card was: "+player1.getSupplies()+" supplies.");
			}
			System.out.println();
			System.out.println("#Animals: " + player1.numAnimals());
			System.out.println("#Hunters: " + player1.numHunters());
			System.out.println();
		}
		
		//give the information to the player about the animals and hunters that player owns
		System.out.println("You contain these animals: \n" + player1.getAllAnimals()); 
		System.out.println("You contain these hunters: \n" + player1.getAllHunters()); 
		System.out.println("Your total score is: "+player1.getPoints());
	}
}
