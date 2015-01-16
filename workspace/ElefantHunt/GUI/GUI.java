import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;


public class GUI extends JFrame{
	protected final static int YES_OPTION = 1000;
	protected final static int NO_OPTION = 1001;
	
	protected JTextArea log; //log screen 
	protected JTextArea playerInfo[]; //player information screen
	protected Player players[]; //objects of players
		
	public GUI() {
		//title
		super("Elefant Hunt designed by Tom Wham and computerized by Wonjohn Choi");
	}
	
	public void initialize(Player[] newPlayers){
		//initializes, instantiates, ...
		players = newPlayers;
		playerInfo = new JTextArea[players.length];
		
		//layout
		setLayout(new BorderLayout());
		
		//North part controlled by a panel
		JPanel panel = new JPanel(new BorderLayout());
		//North: West: map
		panel.add(new JLabel(new ImageIcon("img/Simple_Table_Grey.jpg")), BorderLayout.WEST);
		//North: East: log
		log = new JTextArea("*************************LOG SCREEN*************************");
		log.setBorder(new LineBorder(Color.getHSBColor(0, 0, 0.5f)));
		panel.add(log, BorderLayout.CENTER);
		//add the panel to the frame
		add(panel, BorderLayout.NORTH);
		
		//South Part controlled by a panel
		JPanel infoScreen = new JPanel(new GridLayout(1,3));
		//basic settings of text areas
		for(int i=0;i<playerInfo.length;i++){
			playerInfo[i] = new JTextArea(players[i].toString());
			
			playerInfo[i].setEditable(false);
			playerInfo[i].setBorder(new LineBorder(Color.getHSBColor(0, 0, 0.5f)));
			//infoScreen.add(new JScrollPane(playerInfo[i], JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
			infoScreen.add(playerInfo[i]);
		}
		//add the panel to the frame
		add(infoScreen, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // exit program when you close it
		setResizable(false);
		pack(); //set proper size
		setVisible(true);
	}
	
	/**
	 * prompt screen to ask # of players playing
	 * It covers every case
	 * @return
	 */
	public int promptNumPlayer(){
		int n = 0;
		boolean hasProblem = false;
		
		//cover the wrong-type input
		try{
			//writeLog("How many players are playing? (*Should be 2 or 3)");
			String option = JOptionPane.showInputDialog(this, "How many players are playing? (*Should be 2 or 3)", "Question", JOptionPane.QUESTION_MESSAGE);			
			
			//for cancel option
			if(option == null){
				//writeLog("Closing Program...");
				System.exit(0);
			}
			n = Integer.parseInt(option.trim());
		}catch(Exception e){
			hasProblem = true;
		}
		
		//cover wrong range input
		if(n !=2 && n!=3) {
			hasProblem = true;
		}
		
		if(!hasProblem){
			return n;
		}
		else{
			//writeLog("Wrong input: Input must be 2 or 3.");
			JOptionPane.showMessageDialog(this, "Wrong input: Input must be 2 or 3.");
			
			return promptNumPlayer();
		} 
	}
	

	/**
	 * add new logs
	 * @param message
	 */
	public void writeLog(String message){
		log.append("\n"+message);
	}
	
	/**
	 * update player's information screen
	 */
	public void updatePlayerInfo(){
		//setVisible(false);
		for(int i=0;i<playerInfo.length;i++){
			playerInfo[i].setText(players[i].toString());
		}
		//setVisible(true);
	}
	
	
}
