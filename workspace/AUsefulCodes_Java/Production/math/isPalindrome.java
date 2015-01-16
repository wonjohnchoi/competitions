package math;

public class isPalindrome {
	public static void main(String args[])
	{
		System.out.println(checkPalindrome("!"));//false
	}
	
	public static boolean checkPalindrome(String input)
	{
		int len = input.length();
		return len==0 || len==1 || 
		(input.charAt(0) == input.charAt(len-1) &&checkPalindrome(input.substring(1, len-1))); 
	}
}
