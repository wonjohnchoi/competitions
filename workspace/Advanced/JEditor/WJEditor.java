import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;

public class WJEditor extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 298447795360098921L;

	JButton New = new JButton("New");
	JButton open = new JButton("Open");
	JButton save = new JButton("Save");
	JButton compile = new JButton("Compile");
	JButton run = new JButton("Run");
	JButton help = new JButton("Help");

	JTextArea code = new JTextArea("",28,200);
	JTextArea output = new JTextArea("",7,200);

	File file = null;

	Font font = new Font("monospaced",Font.PLAIN,12);

	public WJEditor()
	{
		super("Jave Editor copyright @Wonjohn Choi All Right Reserved");
		setSize(1280,770);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		Container contentArea = getContentPane();
		contentArea.setBackground(Color.lightGray);

		FlowLayout flowManager = new FlowLayout();
		contentArea.setLayout(flowManager);

		contentArea.add(New);
		New.addActionListener(this);
		contentArea.add(save);
		save.addActionListener(this);
		contentArea.add(open);
		open.addActionListener(this);
		contentArea.add(compile);
		compile.addActionListener(this);
		contentArea.add(run);
		run.addActionListener(this);
		contentArea.add(help);
		help.addActionListener(this);
		JScrollPane scrollPane = new JScrollPane(code,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentArea.add(scrollPane);
		code.setFont(font);
		JScrollPane scrollPane2 = new JScrollPane(output,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentArea.add(scrollPane2);
		output.setFont(font);


		setContentPane(contentArea);



		output.append("Welcome to WJEditor");
		try
		{
			file = new File(new Scanner(new FileReader("dir.txt")).nextLine());
			Scanner sc=new Scanner(new FileReader(file));
			while(sc.hasNextLine())
			{
				code.append(sc.nextLine()+"\n");
			}
			sc.close();			
		}
		catch (Exception e)
		{
			output.append("\nUnexpected Error: Failed to opent the recent file");
		}

	}

	@Override
	public void actionPerformed(ActionEvent event)
	{

		if(event.getSource() == New)
		{
			onNew();
		}
		else if(event.getSource() == save)
		{
			onSave();
		}
		else if(event.getSource() == open)
		{
			onOpen();
		}
		else if(event.getSource() == compile)
		{
			onCompile();
		}
		else if(event.getSource() == run)
		{
			onRun();	
		}
		else if(event.getSource() == help)
		{
			onHelp();
		}
	}



	private void onNew()
	{
		int result = JOptionPane.showConfirmDialog(null, "Would you like to save?", 
				"Save before making new program", JOptionPane.YES_NO_CANCEL_OPTION);

		//if yes,
		if(result==JOptionPane.YES_OPTION)
		{
			onSave();
		}
		
		if(result!=JOptionPane.CANCEL_OPTION)
		{
			code.setText("");
			file=null;
		}
	}

	private void onSave()
	{
		if(file==null)
		{
			JFileChooser saveChooser = new JFileChooser();
			saveChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int result = saveChooser.showSaveDialog(this);
			if(result == JFileChooser.CANCEL_OPTION)
				return;
			file = saveChooser.getSelectedFile();
		}

		if(file==null || file.getName().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Invalid File Name",
					"Invalid File Name", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			try
			{
				PrintWriter pw = new PrintWriter(new FileWriter(file.getPath()));
				pw.print(code.getText());
				pw.close();
				output.append("\nSaved to "+file.getPath());

				pw=new PrintWriter(new FileWriter("dir.txt"));
				pw.print(file.getPath());
				pw.close();
			}
			catch (Exception e)
			{
				output.append("\n Unable to save: Cannot open the file");
			}
		}
	}

	private void onOpen()
	{
		int result = JOptionPane.showConfirmDialog(null, "Would you like to save?", 
				"Save before opening new file", JOptionPane.YES_NO_CANCEL_OPTION);

		//if yes,
		if(result==JOptionPane.YES_OPTION)
		{
			onSave();
		}
		
		if(result!=JOptionPane.CANCEL_OPTION)
		{
		
			JFileChooser openChooser = new JFileChooser();
			openChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			
			result = openChooser.showOpenDialog(this);
			if(result == JFileChooser.CANCEL_OPTION)
				return;
			file = openChooser.getSelectedFile();
			if(file == null || file.getName().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Invalid File Name",
						"Invalid File Name", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				try
				{
					code.setText("");
					Scanner sc=new Scanner(new FileReader(file.getPath()));
					while(sc.hasNextLine())
					{
						code.append(sc.nextLine()+"\n");
					}
					sc.close();
					output.append("\nOpened from "+file.getPath());
	
				}
				catch (Exception e)
				{
					output.append("\nUnexpected Error: Failed to opent the file");
				}
			}
		}

	}
	private void onCompile()
	{

		int result = JOptionPane.showConfirmDialog(null, "Would you like to save?", 
				"Save before compiling", JOptionPane.YES_NO_CANCEL_OPTION);

		//if yes,
		if(result==JOptionPane.YES_OPTION)
		{
			onSave();
		}
		
		if(result!=JOptionPane.CANCEL_OPTION)
		{
		
			try
			{				
				Runtime.getRuntime().exec("javac " + file.getPath());
				output.append("\nCompiled at "+file.getPath());
			}
			catch(Exception e)
			{
				output.append("\nFail to compile");
			}
		}
		
		try
		{
			//wait for the compiling time
			Thread.sleep(1000);
		}
		catch (InterruptedException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	private void onRun()
	{	
		//compile
		onCompile();
		
		try {
			
			/*
			String path=file.getPath();
			path=path.substring(0, path.length()-5) +".bat";
			PrintWriter pw = new PrintWriter(new FileWriter(path));

			String name=file.getName();
			pw.println("@echo off");
			pw.println("@cd "+file.getParent());
			pw.println("@java "+name.substring(0, name.length()-5));
			pw.println("@pause");
			pw.print("@exit");
			pw.close();
			output.append("\nBatch file created at "+file.getParent());
			output.append("\nBatch file is running");
			Runtime.getRuntime().exec("cmd /C start "+path);
			System.out.println(path);*/
			
			Runtime runT = Runtime.getRuntime();
			//runT.exec("cmd /C set CLASSPATH="+file.getParent());
			//System.out.println("cmd /C java -cp \"" + file.getParent() + "\" " + file.getName().substring(0,file.getName().length()-5));
			
			Process p = runT.exec("cmd /C java -cp \"" + file.getParent() + "\" " + file.getName().substring(0,file.getName().length()-5));
		
			System.out.println ( "Done Running" );
			
			
			

			//Runtime.getRuntime().exec("cmd set CLASSPATH="+file.getParent()); //doesn't work
			/*
			output.append("\nRunning from "+file.getPath().substring(0,file.getPath().length()-5)+".class");
			Runtime.getRuntime().exec("cmd /C cd "+file.getParent());

			System.out.println("cmd /C cd "+file.getParent());
			Process result=Runtime.getRuntime().exec("java " +
					file.getName().substring(0,file.getName().length()-5));

			Scanner sc=new Scanner(new InputStreamReader(result.getInputStream()));


			while(sc.hasNextLine())
			{			System.out.println("HI6");

				output.append("\n"+sc.nextLine());
			}
			System.out.println("HI7");
			 */
		}
		catch (Exception e) {
			output.append("\nFailed to run");
			output.append("\n"+e);
		}

	}

	private void onHelp()
	{
		String msg="";
		msg+="New: create a new java file\n";
		msg+="Save: save the file to a location\n";
		msg+="Open: open a file from a location\n";
		msg+="Compile: ask if user wants to save and compile\n";
		msg+="Run: ask if user wants to save and compile and run";
		JOptionPane.showMessageDialog(null, msg,
				"Help", JOptionPane.INFORMATION_MESSAGE);

	}


	public static void main (String [] args)
	{
		new WJEditor();
	}

}
