package capcs.choi.yr20092010.round1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 * @author Wonjohn Choi
 * 
 */
public class Q1 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("DATA1.txt"));
		PrintWriter pw = new PrintWriter(new FileWriter("OUT1.txt"));

		// loop
		for (int i = 0; i < 5; i++) {
			int price = Integer.parseInt(br.readLine());
			int ans = 0;

			while (price > 0) {
				ans += 1000;
				price -= 693;
			}
			pw.println(ans);

		}
		pw.close();
	}

}
