package capcs.choi.yr2010;
import java.io.*;
import java.util.*;
/**
 * @problemFrom http://cemc.math.uwaterloo.ca/contests/computing/2010/stage1/seniorEn.pdf
 * @lang Java (6)
 * @date 2010-12-22
 * @author Wonjohn Choi
 *
 */
public class Senior2 {
	public static void main(String args[]) throws Exception{
		Scanner sc=new Scanner(new FileReader("s2.1.in"));
		int numC=sc.nextInt();
		Item1 sss[]=new Item1[numC];
		for(int i=0;i<numC;i++){
			sss[i]=new Item1(sc.next().charAt(0), sc.next());
		}
		String ans="";
		String key=sc.next();
		while(key.length()!=0){
			for(int i=0;i<numC;i++){
				String cur=key.substring(0,  sss[i].length);
				if(sss[i].binary.equals(cur)){
					key=key.substring(sss[i].length);
					ans+=sss[i].character;
					break;
				}			
			}
		}
		System.out.print(ans);
		System.exit(0);
	}
	
	
}
class Item1{
	int length;
	char character;
	String binary;
	Item1(char c, String s){
		character=c;
		binary=s;
		length=binary.length();
	}
}