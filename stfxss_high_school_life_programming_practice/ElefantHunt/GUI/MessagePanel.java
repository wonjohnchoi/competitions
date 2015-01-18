import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 
 * @author Megan
 *
 */
public class MessagePanel extends JComponent implements ActionListener 
{
	private static final long serialVersionUID = 833596035429008895L;
	
	JButton yesButton;
	JButton noButton;
	JPanel myPanel;
	JFrame frame;
	int buttonClicked;
	String message;

	public MessagePanel(String msg)
	{
		message = msg;
		myPanel = new JPanel();
		buttonClicked = 0;
		yesButton = new JButton("Yes");
		noButton= new JButton("No");

		
		frame = new JFrame("Message Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // exit program when you close it
		frame.setSize(900,100);  // set the size of the window to whatever width and height you like
		frame.add(this); // put an object we can draw on in the centre of the window
		frame.add(myPanel);
		//frame.setVisible(true); //show the window

		//type of layout
		myPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH; // components grow in both dimensions
		c.insets = new Insets(5, 5, 5, 5); // 5-pixel margins on all sides

		//coordinates 
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		c.gridheight = 5;
		//creating and displaying the player label
		JLabel messageBox = new JLabel();
		messageBox.setText(""+ message);
		messageBox.setHorizontalTextPosition(JLabel.CENTER);
		//messageBox.setVerticalTextPosition(JLabel.BOTTOM);
		myPanel.add((messageBox),c);

		myPanel.add(yesButton);
		yesButton.addActionListener(this); // register that you're interested in the clicking of this button
		myPanel.add(noButton);
		noButton.addActionListener(this); // register that you're interested in the clicking of this button
		frame.setVisible(true);
		//frame.setFocusableWindowState(true);
		frame.setAlwaysOnTop(true);
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{ 
		// if the getSupplyButton is clicked
		if (e.getSource() == yesButton)
		{ 
			buttonClicked = 1;
			// System.out.println(buttonClicked);
			frame.setVisible(false); //make the frame invisible 
		} 
		//roshan was here!
		//if the hireHunterButton is clicked 
		else if(e.getSource() == noButton)
		{
			buttonClicked = 2;
			// System.out.println(buttonClicked);
			frame.setVisible(false); //make the frame invisible 
		}

	}
	public boolean onYNButtonClicked()
	{
		boolean yn = false;
		while (buttonClicked == 0)
		{  // Wait until they have selected something
		}
		if (buttonClicked == 1)
		{
			yn = true;
		}
		return yn;
	}
	public void reset()
	{
		frame.setVisible(true);
		buttonClicked = 0;
	}
}
