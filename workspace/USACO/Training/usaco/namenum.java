package usaco;

/*
ID: yojo1002
LANG: JAVA
TASK: namenum
*/
import java.io.*;
import java.util.*;

public class namenum {
	public static void main (String [] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("namenum.in"));
		Scanner dic=new Scanner(new FileReader("dict.txt"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		String key=br.readLine();
		String word;
		String newWord;
		
		boolean done=false;
		while(dic.hasNext())
		{
			newWord="";
			word=dic.next();
			
			for(int i=0;i<word.length();i++)
			{
				if(word.charAt(i)<'Q')
					newWord=newWord+(char)('0'+((word.charAt(i)-'A')/3+2));
				else 
					newWord=newWord+(char)('0'+((word.charAt(i)-'A'-1)/3+2));
			}
			if(key.equals(newWord))
			{
				out.println(word);
				done=true;
			}
		}
		
		if(!done)
			out.println("NONE");
		
		out.close();                                 
		System.exit(0);                             
	}
}