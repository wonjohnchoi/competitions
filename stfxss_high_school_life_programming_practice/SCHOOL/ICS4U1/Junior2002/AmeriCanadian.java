package Junior2002;
import java.util.*;
public class AmeriCanadian 
{
	public static void main(String args[])
	{
		//to process Input, use Scanner
		Scanner sc= new Scanner(System.in);
		
		//get input from user
		String word=sc.next();
		
		//length of the word will be stored.
		int length;
		
		//if the word given was quit, finish program
		while(!word.equals("quit"))
		{
			//get the length of the word
			length=word.length();
			
			//if length is bigger or equal to 4,
			if(length>=4)
			{
				//get last two character part of word 
				String lastTwo=word.substring(length-2, length);
				
				//get the third last character of the word.
				char lastThird=word.charAt(length-3);
				
				//if last two String part is "or" and the third last character is not vowel,
				if(lastTwo.equals("or") && !(lastThird=='a' || lastThird=='e' || lastThird=='o' ||lastThird=='u' || lastThird=='i') )
				{
					//then cut last two digits and add "our" 
					word=word.substring(0,length-2)+"our";
				}
			}
			
			//output the word
			System.out.println(word);
			
			//get new word
			word=sc.next();
		}
	}

}
