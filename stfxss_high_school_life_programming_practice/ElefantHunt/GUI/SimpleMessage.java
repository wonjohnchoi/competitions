import javax.swing.*;
import java.awt.*;

/**
 * 
 * @author Megan
 * @modifiedBy Wonjohn
 *
 */
public class SimpleMessage extends JComponent
{
	private static final long serialVersionUID = 1L;
	JFrame frame;
	JPanel myPanel;
	String message;
	JLabel messageBox;
	
	public SimpleMessage(String msg)
	{
		message = msg;
		myPanel = new JPanel();
		
		frame = new JFrame("Message Panel (Do not close this panel!)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // exit program when you close it
		frame.setSize(500,100);  // set the size of the window to whatever width and height you like
		frame.setLocation(400, 600);
		frame.add(this); // put an object we can draw on in the centre of the window
		frame.add(myPanel);
		
		//Layout type (in grid format)  
		myPanel.setLayout(new GridLayout(1,1));
		
		messageBox = new JLabel();
		messageBox.setText(""+ message);
		messageBox.setHorizontalTextPosition(JLabel.CENTER);		

		myPanel.add(messageBox);
		frame.setVisible(true);
		//frame.setFocusableWindowState(true);
		frame.setAlwaysOnTop(true);
	}
	public void hide()
	{
		frame.setVisible(false);
	}

	public void update(String msg)
	{
		messageBox.setText(msg);
	}
}
