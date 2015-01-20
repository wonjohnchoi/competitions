import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 
 * @author Megan
 *
 */
public class Options extends JComponent implements ActionListener
{
	
	private static final long serialVersionUID = -1188823756788055263L;
	ImageIcon supplyPic;
	ImageIcon hunterPic;
	ImageIcon dicePic;
	JButton getSupplyButton;
	JButton hireHunterButton;
	JButton rollDiceButton;
	JPanel myPanel;
	// Container cp;
	// boolean buttonOn = false;
	// boolean buttonOn2 = false;
	// boolean buttonOn3 = false;
	int buttonClicked = 0;
	JFrame frame;

	public Options()
	{
		myPanel = new JPanel();

		// getting the image files
		supplyPic = new ImageIcon("img/Sample Supply2.gif");
		hunterPic = new ImageIcon("img/hunter-clipart.gif");
		dicePic = new ImageIcon("img/dice.gif");

		// Layout type (in grid format)
		myPanel.setLayout(new GridLayout(0, 3));

		// naming the buttons and adding the image
		getSupplyButton = new JButton("Get Supplies", supplyPic);
		hireHunterButton = new JButton("Hire A Hunter", hunterPic);
		rollDiceButton = new JButton("Roll Dice", dicePic);

		frame = new JFrame("What do you want to do?");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 200);
		frame.add(this);
		frame.add(myPanel);
		frame.setResizable(false);

		myPanel.add(getSupplyButton);
		getSupplyButton.addActionListener(this); // register that you're
													// interested in the
													// clicking of this button
		myPanel.add(hireHunterButton);
		hireHunterButton.addActionListener(this); // register that you're
													// interested in the
													// clicking of this button
		myPanel.add(rollDiceButton);
		rollDiceButton.addActionListener(this); // register that you're
												// interested in the clicking of
												// this button
		setSize(50, 50);
		frame.setVisible(true);
		// frame.setFocusableWindowState(true);
		frame.setAlwaysOnTop(true);
		repaint();
	}

	public void actionPerformed(ActionEvent e)
	{
		// if the getSupplyButton is clicked
		if (e.getSource() == getSupplyButton)
		{
			buttonClicked = 1;
			// System.out.println(buttonClicked);
			frame.setVisible(false); // make the frame invisible
		}
		// if the hireHunterButton is clicked
		else if (e.getSource() == hireHunterButton)
		{
			buttonClicked = 2;
			// System.out.println(buttonClicked);
			frame.setVisible(false); // make the frame invisible
		}
		// if the rollDiceButton is clicked
		else if (e.getSource() == rollDiceButton)
		{
			buttonClicked = 3;
			// System.out.println(buttonClicked);
			frame.setVisible(false); // make the frame invisible
		}
	}

	public int getButtonClicked()
	{
		while (buttonClicked == 0)
		{ // Wait until they have selected something
		}
		return buttonClicked;
	}

	public void reset()
	{
		frame.setVisible(true);
		buttonClicked = 0;
	}
}