package capcs.choi.yr20092010.round1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Wonjohn Choi
 * 
 */
public class Q2 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("DATA2.txt"));
		PrintWriter pw = new PrintWriter(new FileWriter("OUT2.txt"));

		for (int i = 0; i < 5; i++) {
			ArrayList<String> result = new ArrayList<String>();
			ArrayList<Character> components = new ArrayList<Character>();

			for (char keys : br.readLine().toCharArray())
				components.add(keys);

			permutation(result, components.size(), components, "");

			for (int l = 0; l < result.size(); l++)
				pw.println(result.get(l));

		}

		br.close();
		pw.close();
		System.exit(0);

	}

	/**
	 * recursive approach
	 */
	public static void permutation(ArrayList<String> result,
			final int lenResult, ArrayList<Character> components, String word) {
		if (word.length() == lenResult)
			result.add(word);
		else
			for (int i = 0; i < components.size(); i++) {
				char removed = components.remove(i);
				permutation(result, lenResult, components, word + removed);
				components.add(0, removed);
			}

	}

}