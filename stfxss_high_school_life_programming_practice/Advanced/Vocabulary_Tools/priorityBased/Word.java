package priorityBased;


public class Word 
{
	String spelling="";
	String meaning="";
	int difficulty=0;
	
	public Word(String spelling, String meaning, int difficulty)
	{
		this.spelling=spelling;
		this.meaning=meaning;
		this.difficulty=difficulty;
	}
	public Word(String spelling, String meaning)
	{
		this.spelling=spelling;
		this.meaning=meaning;
	}
	
	public Word()
	{
		
	}
	public void setSpelling(String spelling)
	{
		this.spelling=spelling;
	}
	public void setMeaning(String meaning)
	{
		this.meaning=meaning;
	}
	
	public void setDifficulty(int difficulty)
	{
		this.difficulty=difficulty;
	}
	public String toString()
	{
		return spelling+"//"+meaning+"//"+difficulty;
	}
}
