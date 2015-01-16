package capcs.choi.yr20092010.round3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Problem from http://dwite.ca/questions/quiz_time.html
 * A simple data management can solve it
 * @author Wonjohn Choi
 *
 */
public class Problem1 {
	/**
	 * main logic
	 * @param args
	 */
	public static void main(String args[]){
		Scanner sc = null; //make variable of Scanner
		PrintWriter pw = null;
		
		try {
			//create Scanner to read given input file
			sc = new Scanner(new FileReader("DATA1.txt"));
			//create PrintWriter to write to the output file
			pw = new PrintWriter(new FileWriter("OUT1.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String data[] = new String[5]; //array to store data
		
		//for five inputs,
		for(int it = 0; it<5; it++){
			String curData = sc.nextLine(); //get the data
			data[Integer.parseInt(sc.nextLine().trim())-1] = curData; //store the data to a appropriate position
			
		}
		
		//for five outputs,
		for(int it=0;it<5;it++){
			pw.println(data[it]);
		}
		
		sc.close();
		pw.close();
		
	}
}
