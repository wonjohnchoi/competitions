/**
 * Item class that holds the information of level of item and its name
 * It will be used as superclass of Hunter and Animal classes
 * @author Wonjohn Choi
 */
public class Item 
{
	protected int level;
	protected String name;

	/**
	 * constructor
	 * @param lvl the level of the item
	 * @param newName the name of the item
	 * @author Wonjohn Choi
	 */
	public Item(int lvl, String newName)
	{
		level=lvl;
		name=newName;
	}
	
	/**
	 * @return the level of the item
	 * @author Wonjohn Choi
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * @return the name of the item
	 * @author Wonjohn Choi
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @return the String represetation of the item
	 * @author Wonjohn Choi
	 */
	public String toString()
	{
		String output = name + ": level "+level;
		return output;
	}
}
