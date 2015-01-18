
public class TestFireHunterNow 
{

	/**
	 * @author: Sannie Loi
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		// TODO Auto-generated method stub
		Player ply = new Player("Sannie");
		ply.hireHunter(new Hunter(14, "Sannie", true));
		ply.hireHunter(new Hunter(2, "John", false));
		ply.hireHunter(new Hunter(6,"Danny", false));
		ply.hireHunter(new Hunter(1,"Jason", false));
		ply.hireHunter(new Hunter(2,"Daniel",false));
		ply.hireHunter(new Hunter(3,"Kevin", false));
		
		FireHunterNow fire = new FireHunterNow(ply);
		String name = fire.byeHunter();
		System.out.println(name);
		System.out.println(ply);
	}

}
