package permutation;
/*
 * By Wonjohn Choi
 */
import java.util.ArrayList;
import java.util.Collections;

public class Recursion {
	public static void main(String args[]) {

		ArrayList<String> result = new ArrayList<String>();
		ArrayList<Character> input = new ArrayList<Character>();
		Collections.addAll(input, '1', '2', '3', '4', '5', '6', '7', '8','9');
		permutation(result, input, "");
		for (int i = 0; i < result.size(); i++)
			System.out.println(result.get(i));

	}

	@SuppressWarnings("unchecked")
	public static void permutation(ArrayList<String> result,
			ArrayList<Character> components, String word) {
		if (components.size() == 0)
			result.add(word);
		else
			for (int i = 0; i < components.size(); i++) {
				String tempW = word;
				ArrayList<Character> tempC = (ArrayList<Character>) components.clone();
				tempW += components.get(i);
				tempC.remove(i);
				permutation(result, tempC, tempW);
			}

	}

}
