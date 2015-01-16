package capcs.choi.yr20092010.round2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author Wonjohn Choi
 * http://dwite.ca/questions/binary_test_strings.html
 */
public class Q3 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("DATA3.txt"));
		PrintWriter pw = new PrintWriter(new FileWriter("OUT3.txt"));

		for (int i = 0; i < 5; i++) {
			String key = br.readLine();
			String binary[] = new String[16];
			for (int j = 0; j < 16; j++) {
				binary[j] = Integer.toBinaryString(j);
				while (binary[j].length() != 4) {
					binary[j] = "0" + binary[j];
				}
				if (binary[j].indexOf(key) == -1)
					pw.println(binary[j]);
			}

		}

		pw.close();
		System.exit(0);

	}
}