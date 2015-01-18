import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * choose a character
 * @author Wonjohn CHoi
 *
 */
public class CharacterChooser extends JFrame implements ActionListener{
	
	//make buttons
	protected JButton[] buttons = {
				new JButton("Osgood"),
				new JButton("Erik"),
				new JButton("Paula")
	};

	protected Player players[];
	protected int counter = 0;
	
	public CharacterChooser(Player[] newPlayers){
		super("Choose your characters");
		players = newPlayers;
		
		//initialize
		JPanel panel = new JPanel();

		//add buttons to the panel
		for(int i=0;i<buttons.length;i++){
			buttons[i].addActionListener(this);
			panel.add(buttons[i]);
		}
		
		add(panel);
		
		pack();
		setResizable(false);
		setVisible(true);
	}
	
	/**
	 * when button is clicked
	 */
	public void actionPerformed(ActionEvent e) {
		JButton chosen = (JButton) e.getSource();
		
		players[counter] = new Player(chosen.getText());
		counter++;
		chosen.setEnabled(false);
		
		if(counter == players.length){
			setVisible(false);
		}
	}
}
