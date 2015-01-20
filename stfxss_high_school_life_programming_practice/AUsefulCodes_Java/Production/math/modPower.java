package math;

public class modPower {
	public static void main(String args[]) {
		System.out.println(modpow(2, 2000000000, 3)); // 1
	}

	public static long modpow(int base, int exponent, int modulus) {

		long result = 1;

		while (exponent > 0) {
			if ((exponent & 1) == 1)
				result = (result * base) % modulus;

			exponent >>= 1;
			base = (base * base) % modulus;
		}

		return result;
	}
}
