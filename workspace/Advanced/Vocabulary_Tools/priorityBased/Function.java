package priorityBased;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Function
{
	ArrayList<Word> dict;
	public Function(ArrayList<Word> dict)
	{
		this.dict=dict;
	}
	
	public void random()
	{
		
		Scanner sc=new Scanner(System.in);
		boolean done=false;
		System.out.println("To exit(e), To change difficulty (;(-1), '(1), To pass (x)");
		shuffle();
		sortBy(3);
		int pos=0;
		while(!done && dict.size()!=0)
		{
			if(pos==dict.size())
			{
				pos-=dict.size();
				
			}
			System.out.print(dict.get(pos).spelling);
			sc.nextLine();

			System.out.println(dict.get(pos).meaning);
			
			String choice=sc.nextLine();
			if(choice.equals("e"))
			{
				done=true;
			}
			else if(choice.equals(";"))
			{
				dict.get(pos).difficulty-=1;
			}
			else if(choice.equals("'"))
			{
				dict.get(pos).difficulty+=1;
			}
			pos++;
		}
	}
	
	public void add(ArrayList<Word> newDict)
	{
		for(Word word: newDict)
		{
			dict.add(word);
		}
	}
	
	/**
	 * shuffle
	 */
	public void shuffle()
	{
		Collections.shuffle(dict);
	}
	
	/**
	 * spelling(0), meaning(1), ascendingDifficulty(2), descendingDifficulty(3)
	 * @param i standard
	 */
	public void sortBy(int i)
	{
		Comparator<Word> o=null;
		if(i==0)
			o=new bySpelling();
		else if(i==1)
			o=new byMeaning();
		else if(i==2)
			o=new byAscendingDifficulty();
		else if(i==3)
			o=new byDescendingDifficulty();
		else
			System.err.println("Choose another sorting option");

		Collections.sort(dict, o);
		
	}
}

class byMeaning implements Comparator<Word>
{
	public int compare(Word w1, Word w2)
	{ 
		return w1.meaning.compareTo(w2.meaning);
	}
}

class bySpelling implements Comparator<Word>
{
	public int compare(Word w1, Word w2)
	{ 
		return w1.spelling.compareTo(w2.spelling);
	}
}

class byAscendingDifficulty implements Comparator<Word>
{
	public int compare(Word w1, Word w2)
	{ 
		int diff=w1.difficulty-w2.difficulty;
		return diff>0? 1:diff==0? 0:-1 ;
	}
}
class byDescendingDifficulty implements Comparator<Word>
{
	public int compare(Word w1, Word w2)
	{ 
		int diff=w1.difficulty-w2.difficulty;
		return diff>0? -1:diff==0? 0:1 ;
	}
}
