import javax.swing.*;
import java.awt.*;
public class FindPic 
{
	Animal animal;
	public Image FindAnimalPic(String name)
	{
		Image pic = null;
		name = animal.getName();
		if(name.equalsIgnoreCase("Aardvark"))
		{
			pic = new ImageIcon("img/Aardvark.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Antelope"))
		{
			pic = new ImageIcon("img/Antelope.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Baboon"))
		{
			pic = new ImageIcon("img/Baboon.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Bush baby"))
		{
			pic = new ImageIcon("img/Bush baby.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Cheetah"))
		{
			pic = new ImageIcon("img/Cheetah.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Crocodile") && animal.isPredator() == false )
		{
			pic = new ImageIcon("img/Crocodile_1.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Crocodile")&& animal.isPredator() == true)
		{
			pic = new ImageIcon("img/Crocodile_2.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Eagle"))
		{
			pic = new ImageIcon("img/Eagle.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Elefant"))
		{
			pic = new ImageIcon("img/elefant_3.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Giraffe"))
		{
			pic = new ImageIcon("img/Giraffe.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Gorilla"))
		{
			pic = new ImageIcon("img/Gorilla.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Hyena"))
		{
			pic = new ImageIcon("img/Hyena.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Lion") && animal.isPredator() == false)
		{
			pic = new ImageIcon("img/Lion.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Lion")&& animal.isPredator() == true)
		{
			pic = new ImageIcon("img/Lion_2.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Mad Mom"))
		{
			pic = new ImageIcon("img/Mad Mom.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Ostrich"))
		{
			pic = new ImageIcon("img/Ostrich.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Python"))
		{
			pic = new ImageIcon("img/Python.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Rhino"))
		{
			pic = new ImageIcon("img/Rhino.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Wart Hog"))
		{
			pic = new ImageIcon("img/Wart Hog.gif").getImage();
		}
		else if(name.equalsIgnoreCase("Zebra"))
		{
			pic = new ImageIcon("img/Zebra.gif").getImage();
		}
		return pic;
	}

}



