import javax.swing.*;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
public class BoardWithInternalFrames extends JComponent 
{
	JInternalFrame internalFrame;
	JInternalFrame internalFrame2;
	JPanel myPanel;
	playerInfo pi;
	
	public BoardWithInternalFrames()
	{
		
		myPanel = new JPanel();
		JFrame frame = new JFrame("PLayer 1");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // exit program when you close it
	    frame.setSize(1000,700);  // set the size of the window to whatever width and height you like
	    frame.add(this);
	    frame.add(myPanel);
	    frame.setVisible(true); //show the window	    
	    
	    //type of layout
	    myPanel.setLayout(new GridBagLayout());
	   // GridBagConstraints c = new GridBagConstraints();
	   // c.fill = GridBagConstraints.BOTH; // components grow in both dimensions
	   // c.insets = new Insets(5, 5, 5, 5); // 5-pixel margins on all sides
	    
	  //  pi = new playerInfo();
	   // c.gridx = 0;
	   // c.gridy = 0;
	    internalFrame = new JInternalFrame("Player 1", true, true, true, true);
	    internalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE );  // exit program when you close it
	    //internalFrame.setBounds(0,0,5,5);
	    //internalFrame.setLocation(1,1);
	    //internalFrame.setSize(2,3);
	    //internalFrame.add(pi);
	    myPanel.add(internalFrame);
	    internalFrame.setVisible(true);
	    
	    internalFrame2 = new JInternalFrame("Player 2", true, true, true, true);
	    myPanel.add(internalFrame2);
	    internalFrame2.setVisible(true);
	    
	    frame.pack();
	    
	}
	public static void main(String[] args)
	   {
	      new BoardWithInternalFrames();
	   }
}
