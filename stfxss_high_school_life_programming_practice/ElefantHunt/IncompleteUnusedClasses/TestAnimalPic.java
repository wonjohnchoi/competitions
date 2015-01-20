/*
import javax.swing.*;
 The whole class does not work and is unused
 

import java.awt.*;
import java.util.*;
public class TestAnimalPic extends JComponent 
{
	FindPic pic;
	Image img;
	public TestAnimalPic()
	{
		Scanner sc = new Scanner(System.in);
		String var = sc.next();
		JFrame frame = new JFrame("TESTING");
		frame.setSize(1000,750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		for(int i = 0; i < numHunters; i++)
		{
			String animalName = animal[i].getName();
			if(animal[i].isPredator() == false)
			{
				img[i] = new ImageIcon(animalName).getImage();
			}
			else if (animal[i].isPredator() == true)
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
	
	public void paint(Graphics g)
	{
		//drawImage(variable, width, height, widthofpic, heightofpic)
		g.drawImage(img, 0, 20, 100, 80, null);
		
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new TestAnimalPic();
	}

}
*/
