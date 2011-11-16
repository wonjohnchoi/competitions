import java.io.*;
import java.util.*;


class P3 {
	static PrintStream out = System.out;
	static Scanner in = new Scanner(System.in);
	public static void main(String args[]) {
		//int wordSize[] = new int[(int)('Z' - 'A' + 10)];
		//out.println(wordSize.length);
		
		/*
		for (int i = 0; i < 27; i+=3) {
			wordSize[i] = i/3 * 4 + 1;
			wordSize[i + 1] = wordSize[i] + 2;
			wordSize[i + 2] = wordSize[i] + 2;
		}*/
		/*
		Vector<Integer> sizes = new Vector<Integer>();
		fill(sizes, 1, 0);
		fill(sizes, 3, 0);
		Collections.sort(sizes);
		for (int i =0; i< 27;i +=1) {
			wordSize[i] = sizes.get(i);
		}*/
		int wordSize[] = new int[]{1,3,3,5,5,5,7,7,7,7,7,9,9,9,9,9,9,9,9,11,11,11,11,11,11,11,11,11,11,11,11,11};
		//for(int l: wordSize) {
		//	out.println(l);
		//}
		//out.println(wordSize['A' - 'A']);
		

		
		while(in.hasNextLine()) {
			Letter[] letters = new Letter['Z'-'A'+1];
			for(int i=0;i<letters.length;i++) {
				letters[i] = new Letter((char)(i+'A'));
			}
			//int counter = 0;
			String line = in.nextLine().trim();
			String words[] = line.split(" ");
			int result = 0;
			result += (words.length - 1) * 7;
			for (String word: words) {
				result += (word.length() - 1) * 3;
				for(char l: word.toCharArray()) {
					letters[l-'A'].counter += 1;
				}
			}
			
			Arrays.sort(letters);
			int letterVal[] = new int[(int)('Z' - 'A' + 1)];

			for(int i =0;i < letterVal.length; i+=1){
//				out.println(letters[i].c+ " "+ wordSize[i]+ " "+letters[i].counter);
				letterVal[letters[i].c - 'A'] = wordSize[i];
			}
 			
			
			for (String word: words) {
				for (char l: word.toCharArray()) {
					result += letterVal[(int)(l-'A')];
				}
			}
			
			out.println(result);
			
		}
	}
	/*
	static void fill(Vector<Integer> sizes, int seed, int count) {
		if (count >= 20) return;
		sizes.add(seed);
		fill(sizes, seed + 2, count + 1);
		fill(sizes, seed + 4, count + 1);
	}*/
	
	static class Letter implements Comparable<Letter>{
		int counter = 0;
		char c;
		public Letter(char newC) {
			c = newC;
		}
		@Override
		public int compareTo(Letter arg0) {
			return arg0.counter - counter;
		}
	}
}
