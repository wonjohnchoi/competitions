package capcs.choi.yr20092010.round1;

import java.util.*;
import java.io.*;

/**
 * 
 * @author Wonjohn Choi
 * 
 */
public class Q4 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("DATA4.txt"));
		PrintWriter pw = new PrintWriter(new FileWriter("OUT4.txt"));

		for (int i = 0; i < 5; i++) {
			ArrayList<Character> store = new ArrayList<Character>();
			String str = br.readLine();
			for (char c : str.toCharArray())
				store.add(c);
			for (int j = 0; j < store.size(); j++)
				if (Collections.frequency(store, store.get(j)) == 1) {
					pw.println(store.get(j));
					break;
				}
		}

		pw.close();
		System.exit(0);

	}
}
