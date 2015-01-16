import java.io.*;
import java.util.*;


class P4 {
	static PrintStream out = System.out;
	static Scanner in = new Scanner(System.in);
	public static void main(String args[]) {
		while(in.hasNextLine()) {
			String line = in.nextLine().trim();
			String[] tokens = line.split(" ");
			Vector<Expr> stack = new Vector<Expr>();
			for(String t: tokens) {
				char token = t.charAt(0);
				if ('0'<= token && token<='9') {
					stack.add(new Expr(token + "", getPower(token)));
				} else {
					switch(token) {
					case '_':
						Expr exp = stack.lastElement();
						if(getPower('_') >= exp.power) {
							exp.str = "(" + exp.str + ")";
						}
						
						exp.str = "-"+exp.str;
						break;
					default:
						Expr exp2 = stack.remove(stack.size() - 1);
						Expr exp1 = stack.remove(stack.size() - 1);
						if(getPower(token) >= exp2.power) {
							exp2.str = "(" + exp2.str + ")";
						} 
						
						if(getPower(token) > exp1.power) {
							exp1.str = "(" + exp1.str + ")";
						}
						stack.add(new Expr(exp1.str + token + exp2.str, getPower(token)));
						
					}
				}
				
				//num 100
				//_ 10
				//* / 1
				//+ - 0
			}
			out.println(stack.get(0).str);
		}
		
		
		
	}
	static int getPower(char c){
		if (c=='+' || c=='-') return 0;
		if( c=='*' || c=='/') return 1;
		if (c== '_') return 10;
		return 100;
	}
	
	static class Expr {
		String str;
		int power;
		Expr(String s, int p) {
			str = s;
			power = p;
		}
	}
}
