
public class TestMessagePanel 
{
	public static void  main(String[] args) throws InterruptedException
	{
		//message
		String msg1 = "blah?";
		
		MessagePanel mp = new MessagePanel(msg1);
		
		//call this to find out which button was clicked 
		//b = false if noButton is clicked
		//b = true if yesButton is clicked 
		boolean b = mp.onYNButtonClicked();
	    System.out.println(b);
	    
	    Thread.sleep(1000);//just makes it pause 
	    mp.reset(); //once an option window is already made, creating a new one will be redundant so call this function to reset the previous one created 
		b = mp.onYNButtonClicked();
	    System.out.println(b);
		
	}
}
