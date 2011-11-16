import java.io.*;
import java.util.*;


class P8 {
	static PrintStream out = System.out;
	static Scanner in = new Scanner(System.in);
	public static void main(String args[]) {
		long data[] = new long[81];
		
	     int com[] = new int[]{6, 2, 5, 5, 4, 5, 6, 3, 7, 5};
				//0 6
				//1 2
				//2 5
				//3 5
				//4 4
				//5 5
				//6 6
				//7 3
				//8 7
				//9 5
		
		while(in.hasNextInt()) {
			int n = in.nextInt();
			long sum = 0;
			if (n >= com[0]) sum+=1;
			for(int i=1;i<com.length; i+=1){
				if(n>=com[i]){
					sum+= getAns(data, com, n-com[i]);
				}
			}
			out.println(sum);
		}
	}
	static long getAns(long data[], int com[], int n) {
		//if (n <=1) return 1;
		if (data[n] != 0) return data[n];
		long sum = 1;
		for(int i = 0;i<com.length;i+=1) {
			//if (count >=1 && i == 0) continue;
			if (n >= com[i]) {
				sum += getAns(data, com, n - com[i]);
			}
			
		}
		data[n] = sum;
		return sum;
	}
	
}
