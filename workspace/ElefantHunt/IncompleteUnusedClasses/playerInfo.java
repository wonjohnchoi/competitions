//import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout; //import java.awt.GridLayout;
//import java.awt.Image;
import javax.swing.*;
import java.awt.GridBagConstraints; //import java.awt.GridBagLayout;
import java.awt.Insets;

public class playerInfo extends JComponent
{
	/**
	 * 
	 */
	ImageIcon erik;
	ImageIcon aardvark;
	ImageIcon Erik;
	ImageIcon supplies;
	JPanel myPanel;

	public playerInfo()
	{
		// super();
		// for the number of supplies
		int num = 0;
		// num =

		// for the number of points
		int point = 0;
		// point =

		myPanel = new JPanel();
		// super();

		// getting the image files
		erik = new ImageIcon("img/Erik1.gif");
		aardvark = new ImageIcon("img/aardvark1.gif");
		Erik = new ImageIcon("img/Erik_111.gif");
		supplies = new ImageIcon("img/Sample Supply1.gif");

		JFrame frame = new JFrame("PLayer 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit program
																// when you
																// close it
		frame.setSize(500, 500); // set the size of the window to whatever width
									// and height you like
		frame.add(this); // put an object we can draw on in the centre of the
							// window
		frame.add(myPanel);
		frame.setVisible(true); // show the window

		// type of layout
		myPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH; // components grow in both dimensions
		c.insets = new Insets(5, 5, 5, 5); // 5-pixel margins on all sides

		// coordinates
		c.gridx = 0;
		c.gridy = 0;
		// c.gridwidth = 0;
		// c.gridheight = 1;
		// creating and displaying the player label
		JLabel player1 = new JLabel(erik);
		player1.setText("Erik");
		player1.setHorizontalTextPosition(JLabel.CENTER);
		player1.setVerticalTextPosition(JLabel.BOTTOM);
		myPanel.add((player1), c);

		// creating and displaying the supplies label
		c.gridx = 0;
		c.gridy = 1;
		JLabel supply = new JLabel(supplies);
		supply.setText("Supplies: " + num);
		supply.setHorizontalTextPosition(JLabel.CENTER);
		supply.setVerticalTextPosition(JLabel.BOTTOM);
		myPanel.add((supply), c);

		// creating and displaying the hunters label
		c.gridx = 0;
		c.gridy = 2;
		JLabel hunters = new JLabel(Erik);
		hunters.setText("Hunters:");
		hunters.setHorizontalTextPosition(JLabel.CENTER);
		hunters.setVerticalTextPosition(JLabel.BOTTOM);
		myPanel.add((hunters), c);

		// creating and displaying the animals label
		c.gridx = 0;
		c.gridy = 3;
		JLabel animals = new JLabel();
		animals.setText("Animals:");
		animals.setHorizontalTextPosition(JLabel.CENTER);
		animals.setVerticalTextPosition(JLabel.BOTTOM);
		myPanel.add((animals), c);

		// creating and displaying the points label
		c.gridx = 0;
		c.gridy = 4;
		JLabel points = new JLabel();
		points.setText("Points: " + point);
		myPanel.add((points), c);

		// this.add(myPanel);
	}

	public void paint(Graphics g)
	{
		// do all your drawing here
	}

	public static void main(String[] args)
	{
		new playerInfo();
	}
}
