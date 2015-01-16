import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class GeneralGUI extends JFrame implements ActionListener
{
	Container content;
	JButton restart;//=new JButton("restart");
	JTextArea status;
	JTextArea log;
	
	public GeneralGUI()
	{
		super("ElefantHunt: the turn-based strategy game");
		initialize();
		this.setVisible(true);

	}
	
	public void initialize()
	{
		this.setSize(400,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		restart=new JButton("restart");
		status=new JTextArea("This is the status screen.\n",28,50);
		log=new JTextArea("This is the log screen\n",8, 50);
		content=this.getContentPane();
		content.setBackground(Color.LIGHT_GRAY);
		content.add(restart);
		content.add(status);
		content.add(log);
		restart.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		
	}
	public static void main(String args[])
	{
		new GeneralGUI();
	}
	
}
