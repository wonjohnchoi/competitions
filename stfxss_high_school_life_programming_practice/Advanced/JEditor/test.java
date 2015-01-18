import java.io.IOException;


public class test
{
	public static void main(String args[])
	{
		try
		{
			Runtime.getRuntime().exec("C:\\Users\\user\\Desktop\\Test.bat");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
