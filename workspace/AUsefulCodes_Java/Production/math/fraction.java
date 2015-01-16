package math;


/*
 *Fraction
 *
 *It has some functions you might need, if you can think of some I haven't 
 *included feel free to email me.
 *
 *add(Fraction b) - Adds b to this fraction and returns it
 *subtract(Fraction b) - Subtracts b to this fraction and returns it
 *multiply(Fraction b) - Multiplies b to this fraction and returns it
 *divide(Fraction b) - Divides b to this fraction and returns it
 *abs() - returns a fraction that is the absolute value of this
 *inverse() - return a fractice that is the inverse of this
 *pow(int x) - returns a fraction that is this^x
 *gcd(int x, int y) - It's used by the constructor to guarantee simplest form
 *compareTo(Fraction x) - compares their value
 *toString() - returns a string in the form of "a / b"
 *
 */

public class fraction implements Comparable<fraction>
{
	int a, b;
	
	public static void main(String[] args) //TESTING CODE (can be removed)
	{
		fraction f = new fraction(-18, 9);
		f = f.abs();
		f = f.pow(2);
		f = f.add(new fraction(3,15));	
		System.out.println(f);
	}
	
	public fraction(int x, int y)
	{ 
		int f = gcd(x, y);
		a = x/f; 
		b = y/f; 
	}
	
	public fraction add(fraction x)
	{
		return new fraction(a*x.b+b*x.a, b*x.b);
	}
	
	public fraction subtract(fraction x)
	{
		return new fraction(a*x.b-b*x.a, b*x.b);
	}
	
	public fraction multiply(fraction x)
	{
		return new fraction(a*x.a, b*x.b);
	}
	
	public fraction divide(fraction x)
	{
		return new fraction(a*x.b, b*x.a);
	}
	
	public fraction abs()
	{
		return new fraction(Math.abs(a), Math.abs(b));
	}
	
	public fraction inverse()
	{
		return new fraction(b, a);
	}
	
	public fraction pow(int x) //carefull with overflow
	{
		return new fraction((int)Math.pow(a, x), (int)Math.pow(b, x));
	}
	
	public int gcd(int x, int y)
	{
		if (y==0) return x;
		else return gcd(y, x%y);
	}
	
	public int compareTo(fraction x) //nobody likes overflow
	{
		long ret = (long)a*x.b-(long)x.a*b;
		if (ret>0) return 1;
		else if (ret<0) return -1;
		else return 0;
	}
	
	public String toString()
	{
		return a+" / "+b;
	}
}