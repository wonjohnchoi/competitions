package capcs.choi.yr20092010.round3;

import java.io.*;
import java.util.*;

/**
 * 
 * @author Wonjohn Choi
 *
 */
public class Problem2 {
	public static void main(String[] args) throws IOException {
		//Input/Output
		Scanner sc = null;
		PrintWriter pw = null;
		
		try{
			sc = new Scanner(new FileReader("DATA2.txt"));
			pw = new PrintWriter(new FileWriter("OUT2.txt"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//for 5 inputs
		for(int it=0;it<5;it++){
			int n = sc.nextInt();
			
			int pre=0, cur=1, tmp;
			
			//wait till pre<=n<=cur where pre and cur are continuous Fibonacci numbers
			while(!(pre<=n && n<=cur)){
				tmp = pre+cur;
				pre = cur;
				cur = tmp;
			}
			
			if((cur-n)<=(n-pre)){
				pw.println(cur);
			}else{
				pw.println(pre);
			}
		}
		
		sc.close();
		pw.close();
	}
}