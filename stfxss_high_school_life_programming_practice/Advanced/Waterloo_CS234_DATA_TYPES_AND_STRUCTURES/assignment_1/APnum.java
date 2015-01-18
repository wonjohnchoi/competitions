package assignment_1;

public class APnum
{
	protected String value;
	protected boolean isNeg;
	protected int pos;
	
	public APnum(String val)
	{
		value=val;
		isNeg=false;
		pos=0;
	}
	public String toString()
	{
		return isNeg?'-'+new String(value):new String(value);
	}
}
