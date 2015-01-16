import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author wonjohnchoi
 *
 */
class P2 {
    static Scanner in = new Scanner(System.in);
    static boolean debug = false;
    public static void d(String str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(double str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(boolean str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(float str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(Object str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void o(String str) {
        System.out.println(str);
    }
    public static void o(int str) {
        System.out.println(str);
    }
    public static void o(boolean str) {
        System.out.println(str);
    }
    public static void o(float str) {
        System.out.println(str);
    }
    public static void o(long str) {
        System.out.println(str);
    }
    public static void o(Object str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        //debug = true;
        Fraction frac = new Fraction("1/1");
        while (in.hasNext()) {
        	String str = in.next();
        	d(frac);
        	d(new Fraction(str));
        	frac.multiply(new Fraction(str));
        }
        long numer = 1;
        long denom = 1;
        for (int i = 0; i < frac.primes.size(); i += 1) {
        	if (frac.powers.get(i) > 0) {
        		numer *= Math.pow(frac.primes.get(i), frac.powers.get(i));
        	} else if(frac.powers.get(i) < 0) {
        		denom *= Math.pow(frac.primes.get(i), -frac.powers.get(i));
        	}
        }
        o(numer + "/" + denom);
        in.close();
        System.exit(0);
    }
    
    static class Fraction {
    	ArrayList<Long> primes = new ArrayList<Long>();
    	ArrayList<Long> powers = new ArrayList<Long>();
    	Fraction(String str) {
    		String[] tokens = str.split("/");
    		process(Long.parseLong(tokens[0]), 2, 1);
    		process(Long.parseLong(tokens[1]), 2, -1);
    		
    	}
    	void process(long numer, long div, int factor) {
    		if (numer == 1) {
    			return;
    		}
    		long cnt = 0;
    		while (numer % div == 0) {
    			numer /= div;
    			cnt += 1;
    		}
    		if (cnt != 0) {
	
	    		int idx = primes.indexOf(div);
	
	    		if (idx == -1) {
	    			primes.add(div);
	    			powers.add(cnt * factor);
	    		} else {
	    			powers.set(idx, powers.get(idx) + cnt * factor);
	    		}
    		}
    		process(numer, div + 1, factor);
    	}
    	
    	void multiply(Fraction frac) {
    		for (int i = 0; i < frac.primes.size(); i += 1) {
    			int idx = primes.indexOf(frac.primes.get(i));
    			if (idx == -1) {
    				primes.add(frac.primes.get(i));
    				powers.add(frac.powers.get(i));
    			} else {
    				powers.set(idx, powers.get(idx) + frac.powers.get(i));
    			}
    		}
    	}
    	public String toString() {
    		return primes + " " + powers;
    	}
    	
    	
    	
    }
    
    static class Power {
    	
    }
}
