package math;
import java.math.BigInteger;
public class fibonacciNumber {
	public static void main(String args[]) {
		// 0 1 1 2 3 5 8 13 21 34 ...
		System.out.println(fib(BigInteger.ONE, BigInteger.ZERO, 10)); //34
		System.out.println(fib(1L, 0L, 10)); // 34
		System.out.println(fib(1D, 0D, 10D)); // 34.0
		System.out.println(fib(10)); // 34.0
		
	}

	public static BigInteger fib(BigInteger vals, BigInteger valf, int count) {

		return (count == 1) ? valf : (count == 2) ? vals : fib(valf.add(vals), vals, count - 1);
		// correct to more than 300th fibonacci numbers
		// Math.round((fib(1,0,71))==190392490709135
		// less than 1s for bigCases.
	}
	
	public static long fib(long vals, long valf, int count) {

		return (count == 1) ? valf : (count == 2) ? vals : fib(valf + vals, vals, count - 1);
		// correct to count=93th fib number
		// Math.round((fib(1,0,71))==190392490709135
		// Slightly faster :)
	}

	public static double fib(double vals, double valf, double count) {

		return (count == 1) ? valf : (count == 2) ? vals : fib(valf + vals, vals, count - 1);
		// correct to count=79th fib number
		// Math.round((fib(1,0,71))==190392490709135
		// Slightly faster :)
	}

	public static double fib(int k) {
		k--;
		return Math.round(1/ Math.sqrt(5)* (Math.pow((1 + Math.sqrt(5)) / 2, k) - Math.pow((1 - Math.sqrt(5)) / 2, k)));
		// correct to count=71th fib number
		// fib(71)==1.90392490709135E14
	}
}
