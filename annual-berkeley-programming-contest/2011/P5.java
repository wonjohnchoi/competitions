import java.io.*;
import java.util.*;


class P5 {
	static PrintStream out = System.out;
	static Scanner in = new Scanner(System.in);
	public static void main(String args[]) {
		while(in.hasNextLine()) {
			String line = in.nextLine();
			Vector<Frac> str = new Vector<Frac>();
			str.add(new Frac(0, 1));
			str.add(new Frac(1,1));
			str.add(new Frac(1,0));
			for(char c: line.toCharArray()) {
				if(c=='R') {
					str.remove(0);
					
					
				}else if(c=='L'){
					str.remove(2);
				}
				
				str.add(1, str.get(0).add(str.get(1)));
				
			}
			out.println(str.get(1));
		}
	}
	
	static class Frac {
		int top, bot;
		Frac(int t, int b) {
			top = t;
			bot = b;
		}
		
		Frac add(Frac f) {
			return new Frac(top+f.top, bot+f.bot);
		}
		public String toString() {
			return top+"/"+bot;
		}
	}
	

}
