/**
 * Test program of Item class that demonstrates the methods
 * @author Wonjohn Choi
 * @date June 14th, 2010
 * @supervisor Mr. Reid
 * @course ICS4U
 */
public class TestItem 
{
	public static void main(String args[])
	{
		//create an item of a game (declare & instantiate)
		Item monkey = new Item(3, "Stupid Monkey");
		
		//use methods to show how it looks like
		System.out.println("Level: "+monkey.getLevel());
		System.out.println("Name: "+monkey.getName());
		System.out.println("General Description: "+monkey);
		
	} //main
}
