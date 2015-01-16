import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
/**
 * ElefantHuntMap GUI class, used to create the map graphic while running the main engine.
 * @author Marek Witczynski
 * @date June , 2010
 * @supervisor Mr. Reid
 * @course ICS4U
 */

import javax.swing.*;
public class ElefantHuntMap extends JComponent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image img ;
	Image playerImg[];
	int width = 900;//create initial width of window size
	int height = 700;//create initial height of window size
	int spaceX[] = {800,640,560,490,410,320,320,350,430,540,610,620,580,500,400,340,260,260,220,190,100,85,85,85,170,260,400,500,610   };//creating an array of X coordinates used for space positions on map
	int spaceY[] = {80,80,150,150,150,210,270,340,400,400,460,520 ,600,610,610,610,595,510, 450,380,300,225,150,90,55,23,17,17,30    };//creating an array of Y coordinates used for space positions on the map
	
	
	protected ArrayList<Player> players;
	
	public ElefantHuntMap(ArrayList<Player> p)
	{
		players = p;
	   JFrame frame = new JFrame("Map");
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // exit program when you close it
	   frame.setSize(width,height);  // set the size of the window to whatever width and height you like
	   frame.add(this); // put an object we can draw on in the centre of the window
	   img = new ImageIcon("img/Simple_Table_Grey.gif").getImage();//create the map
	   
	   // Array of images
	   playerImg = new Image[players.size()];//create variable called playerimg, that stores the amount of players within an array
	   for (int i=0; i<players.size(); i++)
	   {
		   playerImg[i] = new ImageIcon("img/"+players.get(i).getName()+".gif").getImage();
	   }
	   	   
	   frame.setVisible(true); //show the window
	   frame.setAlwaysOnTop(true);
	   repaint();
	}

	public void paint(Graphics g) {
	     // do all your drawing here
		   
		   g.drawImage(img, 0, 0, width, height, null);//create map img and paste on screen
	   
		   // For each image in array + position based on Player position num
		   for (int i=0; i<players.size(); i++)//loop according to the amount of players within the game
		   {
			   int pos = players.get(i).getPosition();//get position of the player
			   g.drawImage(playerImg[i] , spaceX[pos], spaceY[pos], null, null);//change player coordinates according to the players new position and move player image to coordinates
		   }
		   
		   
	   }
	
	
	   
	 
	}







