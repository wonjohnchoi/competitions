import java.applet.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class DrawSlitherlink extends Applet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init()
	{ 
		
		
	}

	public void stop() 
	{ 
		
	}

	public void paint(Graphics g) 
	{
		g.drawString(".", 0, 5);
		g.drawString(".", 0, 10);

		g.drawString(".", 0, 15);

		g.drawString(".", 0, 20);

		g.drawString(".", 0, 25);

		g.drawString(".", 0, 30);

	}
}