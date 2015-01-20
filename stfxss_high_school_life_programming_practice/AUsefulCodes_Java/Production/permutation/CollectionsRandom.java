package permutation;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionsRandom {
	public static void main(String args[]) {
		ArrayList<Integer> element = new ArrayList<Integer>();
		Collections.addAll(element, 1,2,3,4,5,6);
		ArrayList<String> result = Shuffling(element, factorial(6));
		Collections.sort(result);
		for (int i=0;i<result.size();i++){
			System.out.println(result.get(i));
		}
		System.out.println(result.size());

	}

	public static ArrayList<String> Shuffling(ArrayList<Integer> element, int totalNum) {
		int count = 0;
		ArrayList<String> result = new ArrayList<String>();
		while (count != totalNum) {
			String temp="";
			for(int i=0;i<element.size();i++)
				temp +=element.get(i);
			
			if(!result.contains(temp))
			{
				result.add(temp);

				count++;
			}
			
			Collections.shuffle(element);
			
		}
		return result;
	}

	public static int factorial(int to) {
		int ans = 1;
		for (int i = 2; i <= to; i++) {
			ans *= i;
		}
		return ans;
	}
}
