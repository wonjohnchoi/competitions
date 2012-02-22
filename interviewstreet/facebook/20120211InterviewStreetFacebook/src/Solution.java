import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Solution {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String[] words = in.nextLine().split(" ");
		String line = in.nextLine();
		/*
		Trie trie = new Trie();
		for (String word : words) {
			trie.insert(word);
		}
		
		for (int i = 0; i < line.length(); i += 1) {
			
		}*/
		
		Arrays.sort(words);
		
		int len = words[0].length();
		
		for (int i = 0; i < len ;i += 1) {
			//System.out.println("i: " + i);
			int cur = i;
			List<String> required = new LinkedList<String>();
			for (String word : words) {
				required.add(word);
			}
			
			while (cur + len < line.length()) {
				String nextWord = line.substring(cur, cur + len);
				//System.out.println(nextWord);
				int idx = Collections.binarySearch(required, nextWord);
				if (idx < 0) {
					/*
					required = new LinkedList<String>();
					for (String word : words) {
						required.add(word); //possible error
					}*/
					if (words.length == required.size()) {
						cur += len;
						continue;
					}
					int pre = cur - (words.length - required.size()) * len;
					//System.out.println("Requiring: " + pre);
					//System.out.println("cur: " + cur);
					required.add(line.substring(pre, pre + len));
					Collections.sort(required);
				} else {
					//System.out.println(idx);
					required.remove(idx);
					if (required.isEmpty()) {
						System.out.println(cur + len - words.length * len);
						System.exit(0);
					}
					cur += len;

				}
				
			}
		}
		
	}
	
	
	static class Trie {
		HashMap<Character, Trie> letters = new HashMap<Character, Trie>();
		//public Tree(String[] words) {
			
		//}
		boolean end = false;
		
		void insert(String word) {
			char first = word.charAt(0);
			Trie trie = letters.get(first);
			
			if (trie == null) {
				trie = new Trie();
				letters.put(first, trie);
			}
			
			if (word.length() > 1) {
				trie.insert(word.substring(1));
			
			} else {
				end = true;
			}
		}
		
	    public boolean has(String word) {
	    	char first = word.charAt(0);
	    	Trie trie = letters.get(first);
	    	if (trie != null && word.length() > 0) {
	    		return trie.has(word.substring(1));
	    	} else if (trie.end && word.length() == 1) {
	    		return true;
	    	}
	    	return false;
	    }

		static List<String> getStartingWords(Trie trie, String line, int from) {
			LinkedList<String> words = new LinkedList<String>();
			
			int idx = from;
			while (trie != null) { 	
				if (trie.end) {
					words.add(line.substring(0, idx));
				}
				char first = line.charAt(0);
				trie = trie.letters.get(first);
				idx += 1;
			}
			return words;
			
		}
		
	}
}
