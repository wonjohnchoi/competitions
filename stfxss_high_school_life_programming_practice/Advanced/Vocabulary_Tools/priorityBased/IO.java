package priorityBased;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class IO
{
	String FILE="";
	public IO(String FILE)
	{
		this.FILE=FILE;
	}
	public IO()
	{
		this(getFILE());
	}
	public static String getFILE()
	{
		return getFILE("");
	}
	public static String getFILE(String prefix)
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("FILE NAME: ");
		String name=sc.next();
		try
		{
			new Scanner(new FileReader(prefix+name));
			return prefix+name;
		}
		catch (FileNotFoundException e)
		{
			return getFILE(prefix);
		}
	}
	
	/**
	 * set new file name 
	 * @param newFILE new file name
	 */
	public void setFILE(String newFILE)
	{
		this.FILE=newFILE;
	}
	
	/**
	 * read regular file format from a .txt file 
	 * Regular Format: spelling//word//difficulty
	 * @return array of Words (objects)
	 * @throws IOException
	 */
	public ArrayList<Word> readFILE() throws IOException
	{
		ArrayList<Word> dict=new ArrayList<Word>();
		Scanner sc=new Scanner(new FileReader(FILE));
		while(sc.hasNext())
		{
			String line=sc.nextLine();
			String parts[]=line.split("//");
			if(parts.length!=3)
			{
				System.err.printf("While Reading FILE %s, %dth line has more or less than 3 parts\n", FILE, dict.size()+1);
				System.err.printf("INPUT: %s\n", line);
			}
			else
			{
				dict.add(new Word(parts[0], parts[1], new Integer(parts[2])));
			}
		}
		sc.close();
		return dict;
	}
	
	/**
	 * read regular file format from screen 
	 * Regular Format: spelling//word//difficulty
	 * @return array of Words (objects)
	 * @throws IOException
	 */
	public ArrayList<Word> readScreen() throws IOException
	{
		ArrayList<Word> dict=new ArrayList<Word>();
		Scanner sc=new Scanner(System.in);
		System.out.println("spelling//meaning format + // means finished");
		boolean done=false;
		do
		{
			String line=sc.nextLine();
			String parts[]=line.split("//");
		
			if(line.equals("//"))
			{
				System.out.println("Reading stops properly");
				done=true;
			}
			else if(parts.length!=2)
			{
				System.err.printf("While Reading screen, %dth line has less or more than 2 parts \n",  new Integer(dict.size()+1));
			}
		
			else
			{
				dict.add(new Word(parts[0], parts[1]));
			}
		}while(!done);
		sc.close();
		return dict;
		
	}
	
	/**
	 * write regular file format to a txt file 
	 * Regular Format: spelling//word//difficulty
	 * @param dict array of Words (objects)
	 * @throws IOException
	 */
	public void writeFILE(ArrayList<Word> dict) throws IOException
	{
		PrintWriter pw=new PrintWriter(new FileWriter(FILE));
		for(Word word: dict)
		{
			pw.println(word);
		}
		pw.close();
	}
	
	/**
	 * write regular file format to screen
	 * Regular Format: spelling//word//difficulty
	 * @param dict array of Words (objects)
	 */
	public void writeScreen(ArrayList<Word> dict)
	{
		for(Word word: dict)
		{
			System.out.println(word);
		}
	}
	
	public void writeMeaning(ArrayList<Word> dict)
	{
		for(Word word:dict)
		{
			System.out.println(word.meaning);
		}
	}
	
	public void writeSpelling(ArrayList<Word> dict)
	{
		for(Word word:dict)
		{
			System.out.println(word.spelling);
		}
	}
	
	/**
	 * read words in common type to
	 * @return array of words in regular type (Word class)
	 * @throws IOException 
	 */
	public ArrayList<Word> readCommon() throws IOException
	{
		ArrayList<Word> dict=new ArrayList<Word>();
		Scanner sc=new Scanner(new FileReader(FILE));
		while(sc.hasNext())
		{
			String line[]=sc.nextLine().split(" ");
			String meaning="";
			for(int i=1;i<line.length;i++)
			{
				meaning+=line[i]+" ";
			}
			meaning=meaning.trim();
			dict.add(new Word(line[0], meaning, 0));
		}
		sc.close();
		return dict;
	}
	public String toString()
	{
		return FILE;
	}
}
