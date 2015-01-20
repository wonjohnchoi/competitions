/**
 * @author Wonjohn Choi
 */
public class Hunter extends Item {
	private boolean isMain;

	/**
	 * constructor
	 */
	public Hunter(int lvl, String name, boolean main) {
		super(lvl, name);
		isMain = main;
	}

	/**
	 * check if it is a main hunter
	 * @return
	 */
	public boolean isMain() {
		return isMain;
	}

	/**
	 * string representation of this object
	 */
	public String toString() {
		
		String output = super.toString();
		
		if(isMain)
		{
			output = "Main Hunter " + output;
		}
		else
		{
			output = "Regular Hunter " + output;
		}
		return output;
	}
}
