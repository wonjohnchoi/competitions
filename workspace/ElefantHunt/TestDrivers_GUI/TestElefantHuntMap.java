import java.util.ArrayList;


public class TestElefantHuntMap 
{
	public static void  main(String[] args) throws InterruptedException
	{
		//creates an option window 
		//composed of three buttons with three different options: get supplies, hire a hunter and roll dice.
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Erik"));
		players.add(new Player("Erik"));
		
		ElefantHuntMap ehm = new ElefantHuntMap(players);
		
	}
}
