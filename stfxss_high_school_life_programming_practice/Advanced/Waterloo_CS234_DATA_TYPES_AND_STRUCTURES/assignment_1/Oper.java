package assignment_1;
/**
 * http://www.cs.uwaterloo.ca/~gweddell/cs234/a1.pdf
 * @author user
 *
 */
public final class Oper
{
	private Oper() {}
	
	
	public static APnum APzero()
	{
		return new APnum("0");
	}
	
	public static void APin(APnum n, char c)
	{
		if(n.value.equals("0"))
		{
			n.value="";
		}
		n.value+=c;
	}
	
	public static void APneg(APnum n)
	{
		n.isNeg=!n.isNeg;
	}
	
	public static void APadd(APnum n1, APnum n2)
	{
		String val="";
		String val1, val2;
		int len1, len2;
		
		val1=n1.value;
		val2=n2.value;
		
		len1=val1.length();
		len2=val2.length();
	
		if((n1.isNeg && n2.isNeg)||(!n1.isNeg && !n2.isNeg))
		{
			int over=0;
			for(int i=1;i<=Math.max(len1, len2);i++)
			{
				int sDigit=over;
				if(len1>=i)
				{
					sDigit+=val1.charAt(len1-i)-'0';
				}
				if(len2>=i)
				{
					sDigit+=val2.charAt(len2-i)-'0';
					
				}
				over=0;
				if(sDigit>=10)
				{
					sDigit-=10;
					over=1;
				}
				val=sDigit+val;
				
			}
			
			if(over==1)
			{
				val=1+val;
			}
			
			
		}
		else if(val1.equals(val2))
		{
			val="0";
			n1.isNeg=false;
		}
		else
		{
			//make val1>val2
			if((len1==len2 && val2.compareTo(val1)>0) || len2>len1)
			{			

				String tmp=val1;
				val1=val2;
				val2=tmp;
				
				int tmp1=len1;
				len1=len2;
				len2=tmp1;
				
				n1.isNeg=false;
				
			}

			int under=0;
			for(int i=1;i<=Math.max(len1, len2);i++)
			{
				int sDigit=under+val1.charAt(len1-i)-'0';
				
				if(len2>=i)
				{
					sDigit-=(val2.charAt(len2-i)-'0');
				}
				
				under=0;
				if(sDigit<0)
				{
					sDigit+=10;
					under=-1;
				}
				val=sDigit+val;
			}
			
		}
		
		n1.value=val;
		
	}
	
	public static boolean APeq(APnum n1, APnum n2)
	{
		return (n1.isNeg==n2.isNeg) && (n1.value.equals(n2.value));
	}
	
	public static boolean APless(APnum n1, APnum n2)
	{
		boolean isLess;
		if(n1.isNeg && !n2.isNeg)
		{
			isLess=true;
		}
		else if(!n1.isNeg && n2.isNeg)
		{
			isLess=false;
		}
		else if(n1.isNeg && n2.isNeg)
		{
			String val1=n1.value;
			String val2=n2.value;
			if(val1.length()==val2.length())
			{
				if(val1.compareTo(val2)>0)
				{
					isLess=true;
				}
				else
				{
					isLess=false;
				}
			}
			else if(val1.length()>val2.length())
			{
				isLess=true;
			}
			else
			{
				isLess=false;
			}
		}
		else
		{
			String val1=n1.value;
			String val2=n2.value;
			if(val1.length()==val2.length())
			{
				if(val1.compareTo(val2)<0)
				{
					isLess=true;
				}
				else
				{
					isLess=false;
				}
			}
			else if(val1.length()<val2.length())
			{
				isLess=true;
			}
			else
			{
				isLess=false;
			}
		}
		return isLess;

	}
	
	public static char APfirstdigit(APnum n)
	{
		n.pos=0;
		return n.value.charAt(0);
	}
	
	public static char APnextdigit(APnum n)
	{
		n.pos++;
		return n.value.charAt(n.pos);
	}
	
	
	
	
	
	
	
}
