/**
 * @author Wonjohn Choi
 */
public class Animal extends Item 
{
	private boolean isPredator; 
	
	/**
	 * constructor
	 */
	public Animal (int lvl, String name, boolean predator)
	{	
		super(lvl, name);
		isPredator = predator;
	}
	
	/**
	 * check if it is predator
	 * @return
	 */
	public boolean isPredator()
	{	
		return isPredator;
	}	
	
	/**
	 * @return string representation of the animal
	 */
	public String toString()
	{
		String output = super.toString();
		
		if(isPredator)
		{
			output = "Predator Animal "+output;
		}	
		else
		{
			output = "Friendly Animal "+output;
		}
		
		return output;
	}	
}
