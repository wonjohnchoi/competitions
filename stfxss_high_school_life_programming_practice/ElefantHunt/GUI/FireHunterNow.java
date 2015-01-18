import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
	 * @author: Sannie Loi
	 */
public class FireHunterNow extends JComponent implements ActionListener 
{
	
	private static final long serialVersionUID = 1L;
	protected JFrame frame;
	protected JButton okButton;
	protected JRadioButton hunters[];
	protected int numHunters;
	protected ButtonGroup group;
	protected JPanel radioPanel;
	protected String firedHunter;
	protected Player player;
	protected String hunterName[];

	public FireHunterNow(Player p)
	{
		numHunters = p.getAllHunters().size();
		hunters = new JRadioButton[numHunters];
		hunterName = new String[numHunters];
		//create the radioButtons 
		for(int i = 1; i < numHunters; i ++ )
		{
			hunterName[i]= p.getAllHunters().get(i).getName();
			hunters[i] = new JRadioButton(hunterName[i] + " Level: " + p.getAllHunters().get(i).getLevel());
		}
		group = new ButtonGroup();
		for(int i = 1 ; i < numHunters;i++)
		{
			//add them to a group
			group.add(hunters[i]);
		}
		//create a grid panel to display buttons
		radioPanel = new JPanel(new GridLayout(0,1));
		for(int i = 1; i < numHunters;i++)
		{	
			radioPanel.add(hunters[i]);
			//create actionListener
			hunters[i].addActionListener(this);
		}
		//create the ok button
		okButton = new JButton("ok");
		okButton.addActionListener(this);

		//create the frame
		frame = new JFrame("Select the hunter you want to fire:");
		frame.setSize(300,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(radioPanel, BorderLayout.CENTER);
		frame.add(okButton, BorderLayout.EAST);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		String var = null;
		// TODO Auto-generated method stub
		if(e.getSource() == okButton)
		{
			for(int i = 1 ; i < numHunters; i++)
			{
				if(hunters[i].isSelected() == true)
				{
					var = hunterName[i];
				}
			}
			firedHunter = var;
			//makes frame disappear
			frame.setVisible(false);
		}
	}
	//returns the name of the hunter that was fired
	public String byeHunter() throws InterruptedException
	{
		while(firedHunter == null)
		{
			//waits until it gets GUI
			Thread.sleep(10);
		}
		return firedHunter;
	}

}
