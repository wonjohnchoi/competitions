import java.util.*;
public class TestHuntingSpaces {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		Player player =  new Player("Sannie");
		ArrayList<Animal> random = new ArrayList<Animal>();
		random.add(new Animal(16, "Mad Mom", true));
		random.add(new Animal(6, "Baboon", false));
		random.add(new Animal(5, "Python", false));
		player.hireHunter(new Hunter(5, "Wonjohn", true));
		player.hireHunter(new Hunter(10,"Sannie",false));
		player.hireHunter(new Hunter(4,"Hello", false));
		//roshan was here!!!!
		// TODO Auto-generated method stub
		HuntingSpaces var = new HuntingSpaces(player, random);
		
		ArrayList<ArrayList<Hunter>> determines = var.AssignHunters();
		int num = determines.size();
		for(int i = 0; i < num;i++)
		{
			System.out.println(determines.get(i));
		}

	}

}
