package capcs.choi.yr20092010.round3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * simple problem that I used Java built-in 'toBinaryString' method
 * @author Wonjohn Choi
 * 
 */
public class Problem3 {
	public static void main(String[] args) throws IOException {
		// Input/Output
		Scanner sc = null;
		PrintWriter pw = null;

		try {
			sc = new Scanner(new FileReader("DATA3.txt"));
			pw = new PrintWriter(new FileWriter("OUT3.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//find out every binary number in the range 2^8
		String binaries[] = new String[256];
		for (int bin = 0; bin < 256; bin++) {
			binaries[bin] = Integer.toBinaryString(bin);

			//need to add '0's to the front to satify problem's condition
			while (binaries[bin].length() != 8) {
				binaries[bin] = "0" + binaries[bin];
			}
		}
		
		//for each input
		for (int it = 0; it < 5; it++) {
			int count = 0;
			String key = sc.next();
			
			//for each binaries
			for (int bin = 0; bin < 256; bin++) {
				//if key is not part of it
				if (binaries[bin].indexOf(key) == -1) {
					//increment count for # of '1's
					for (int j = 0; j < binaries[it].length(); j++) {
						if (binaries[bin].charAt(j) == '1') {
							count++;
						}
					}

				}
			}

			pw.println(count);
		}
		pw.close();
	}
}
