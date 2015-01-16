package math;

public class baseChanger {
	public static void main(String args[])
	{
		System.out.println(baseChanger(10, 2, 33)); //100001
	}
	
	public static String baseChanger(int from, int to, int input)
	{
		if(from<=1|| to<=1)
			return "Error Occured";
		String inputS=input+"";
		int length=inputS.length();
		int inTenBase=0;
		String output = "";
		
		for(int i=length-1;i>=0;i--)
		{
			inTenBase+=(int)(inputS.charAt(i)-'0')*Math.pow(from, length-i-1);
		}
		           
		int remainder;
		while(inTenBase!=0)
		{
			remainder=inTenBase%to;
			if(remainder<10)
				output=remainder+output;
			else
			{
				remainder-=10;
				output=(char)(remainder+'A')+output;
			}
			inTenBase=(inTenBase-remainder)/to;
		}
		return output;
	}

}

