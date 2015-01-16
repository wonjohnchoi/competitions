import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author wonjohnchoi
 *
 */
class P7 {
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
    static boolean isOper(char chr) {
    	return chr == '+' || chr == '-' || chr == '.';
    }
    
    static int val(char chr) {
    	if (chr == '+') return 1;
    	else if (chr == '-') return -1;
    	return 0;
    }
    
    static boolean isNum(char chr) {
    	return '0' <= chr && chr <= '9';
    }

    public static void main(String[] args) {
    	debug = false;
    	while (in.hasNext()) {
    		String exp = in.next();
    		String newExp = "";
    		for (int i = 0; i < exp.length(); i += 1) {
    			if (isNum(exp.charAt(i))) {
    				continue;
    			}
    			if (!isOper(exp.charAt(i)) && exp.charAt(i) != ')') {
    				newExp += exp.charAt(i);
    				continue;
    			}
    			newExp += exp.charAt(i);
    			
    			if (i + 1 == exp.length()) {
    				newExp += "1";
    				break;
    			}
    			
    			if (!isNum(exp.charAt(i + 1))) {
    				newExp += "1";
    				continue;
    			}
    			String num = "";
    			int j = i + 1;
    			while (j != exp.length() && isNum(exp.charAt(j))) {
    				num += exp.charAt(j);
    				j += 1;
    			}
    			
    			newExp += num;
    			
    		}
    		d(newExp);
    		if (newExp.length() == 0) {
    			o(0);
    			continue;
    		}
    		long sum = 0;
    		int cnt = 1;
    		long num = 0;
    		String finalExp = genFullExp(newExp);
    		d(finalExp);
    		for (int i = 0; i < finalExp.length(); i += 1) {
    			// d("DDD");
    			if (!isOper(finalExp.charAt(i))) {
    				continue;
    			}
    			char c = finalExp.charAt(i);
    			String numStr = "";
    			int j = i + 1;
    			while (j != finalExp.length() && isNum(finalExp.charAt(j))) {
    				numStr += finalExp.charAt(j);
    				j += 1;
    			}
    			long tmp = Long.parseLong(numStr);
    			cnt += tmp;
    			long newNum = num + val(c) * tmp;
    			sum += (newNum + (num + val(c))) * tmp / 2;
    			num = newNum;
    		}
    		d(sum + " " + cnt);
    		o(String.format("Average value of %s is %.2f", exp, (double) sum / cnt));
    	}
        in.close();
        System.exit(0);
    }
    
    static String genFullExp(String exp) {
    	if (exp.length() == 0 || exp.length() == 1) {
    		return exp;
    	}
    	int lIdx = exp.indexOf('(');
    	if (lIdx == -1) {
    		return exp;
    	}
    	int deg = -1;
    	int oo = lIdx + 1;
    	while (deg != 0) {
    		if (exp.charAt(oo) == '(') {
    			deg -= 1;
    		} else if (exp.charAt(oo) == ')') {
    			deg += 1;
    		} 
    		oo += 1;
    	}
    	int rIdx = oo - 1; //exp.lastIndexOf(')');

    	String bracketed = "";
    	String num = "";
    	int j = rIdx + 1;
    	while (j != exp.length() && isNum(exp.charAt(j))) {
    		num += exp.charAt(j);
    		j += 1;
    	}
    	for (int i = 0; i < Long.parseLong(num); i += 1) {
    		bracketed += exp.substring(lIdx + 1, rIdx);
    		//d(exp.substring(lIdx + 1, rIdx - 1));
    	}
    	return exp.substring(0, lIdx) + genFullExp(bracketed) + genFullExp(exp.substring(j));
    	//return exp.substring(lIdx) + 
    }
}
