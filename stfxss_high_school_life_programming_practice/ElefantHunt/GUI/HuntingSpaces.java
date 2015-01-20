import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
	 * @author Sannie Loi
	 */
public class HuntingSpaces extends JComponent implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	protected JFrame frame;
	protected Image img[];
	protected JRadioButton hunter[];
	protected JButton okButton;
	protected ButtonGroup group[];
	protected JPanel radioPanel;
	protected int numAnimals;
	protected String hunterName[];
	protected Player player;
	protected int numHunters;
	protected ArrayList<ArrayList<Hunter>>huntingParties;
	
	public HuntingSpaces(Player p, ArrayList<Animal> animals)
	{
		player = p;
		numAnimals = animals.size();
		numHunters = player.numHunters();
		int num2 = numAnimals*numHunters;
		hunter = new JRadioButton[num2];

		//create the radio buttons
		int num = 0;
		hunterName = new String[numHunters];
		int hunterLevel[] = new int[numHunters];
		for(int i=0; i< numHunters; i++)
		{
			hunterName[i] = player.getAllHunters().get(i).getName();
			hunterLevel[i]= player.getAllHunters().get(i).getLevel();
		}
		for(int t=0; t < numHunters; t++)
		{
			for(int i = 0; i < numAnimals; i++)
			{
				hunter[num] = new JRadioButton(hunterName[t] + " lvl: " +hunterLevel[t]);
				num++;
			}
		}
		num = 0;

		//group the buttons together
		group = new ButtonGroup[numHunters];
		for(int s=0; s<numHunters;s++)
		{
			group[s]= new ButtonGroup();
			{
				for(int u=0;u<numAnimals;u++)
				{
					group[s].add(hunter[num]);
					num++;
				}
			}
		}

		//make the buttons acton listeners
		for(int i = 0; i < hunter.length;i++)
		{
			hunter[i].addActionListener(this);
		}

		//place the radiobuttons on a grid
		radioPanel = new JPanel(new GridLayout(numHunters,0));
		for(int i = 0; i< num2 ;i++)
		{
			radioPanel.add(hunter[i]);
		}

		//create a button that the user can click when they are finished
		okButton = new JButton("ok");
		okButton.addActionListener(this);

		//create the frame
		frame = new JFrame("Assign hunters to the animals you want to hunt");
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,200);
		frame.add(this);
		frame.add(radioPanel, BorderLayout.SOUTH);
		frame.add(okButton, BorderLayout.EAST);
	

		//get the images
		img = new Image[20];
		for(int i = 0; i < numAnimals; i++)
		{
			String animalName = animals.get(i).getName();
			if(animals.get(i).isPredator() == false)
			{
				if(animalName.equalsIgnoreCase("Crocodile"))
				{
					img[i] = new ImageIcon("img/Crocodile_1.gif").getImage();
				}
				else if(animalName.equalsIgnoreCase("Elefant"))
				{
					img[i] = new ImageIcon("img/elefant_3.gif").getImage();
				}
				else
				{
				img[i] = new ImageIcon("img/" +animalName+ ".gif").getImage();
				}
			}
			else if (animals.get(i).isPredator() == true)
			{
				if(animalName.equalsIgnoreCase("Crocodile"))
				{
					img[i] = new ImageIcon("img/Crocodile_2.gif").getImage();
				}
				else if(animalName.equalsIgnoreCase("Lion"))
				{
					img[i] = new ImageIcon("img/Lion_2.gif").getImage();
				}
				else if(animalName.equalsIgnoreCase("Mad Mom"))
				{
					img[i] = new ImageIcon("img/Mad Mom.gif").getImage();
				}
				else if (animalName.equalsIgnoreCase("Rhino"))
				{
					img[i] = new ImageIcon("img/Rhino.gif").getImage();
				}
				else if(animalName.equalsIgnoreCase("Wart Hog"))
				{
					img[i] = new ImageIcon("img/Wart Hog.gif").getImage();
				}
			}
		}
		frame.setVisible(true);
		repaint();
	}
	//format/place the images
	public void paint(Graphics g)
	{
		if(numAnimals == 1)
		{
			g.drawImage(img[0],0,20,100,80,null);
		}
		else if (numAnimals == 2)
		{
			g.drawImage(img[0],0,20,100,80,null);
			g.drawImage(img[1],160,20,100,80,null);
		}
		else if(numAnimals == 3)
		{
			g.drawImage(img[0],0,20,100,80,null);
			g.drawImage(img[1],120,20,100,80,null);
			g.drawImage(img[2],240,20,100,80,null);
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		//creates an arraylist in an arraylist
		ArrayList<ArrayList<Hunter>> hp = new ArrayList<ArrayList<Hunter>>();
		//happens when the player clicks on the ok button
		if(e.getSource() == okButton) 
		{
			int num = 0;
			for(int i = 0; i < numAnimals;i++)
			{
				hp.add(new ArrayList<Hunter>());
			}
			num = 0;
			for(int j = 0; j < numHunters; j++)
			{
				for (int i=0; i<numAnimals; i++)
				{
					if(hunter[num].isSelected() == true)
					{
						hp.get(i).add(player.getHunter(hunterName[j]));
					}
					num++;
				}
			}
			//makes the window invisible
			frame.setVisible(false);
			huntingParties = hp;
		}
	}
	//returns the arraylist
	public ArrayList<ArrayList<Hunter>> AssignHunters() throws InterruptedException
	{
		while (huntingParties == null)
		{
			// Wait for GUI
			Thread.sleep(10);
		}
		return huntingParties;
	}
}
