import java.util.*;
public class KMP {
    static int[] preprocess(char[] ptrn) { // O(M)
	int i = 0, j = -1;
	int[] b = new int[ptrn.length + 1];
	b[i] = j;
	while (i < ptrn.length) {
	    while (j >= 0 && ptrn[i] != ptrn[j]) {
		j = b[j];
	    }
	    i++;
	    j++;
	    b[i] = j;
	}
	return b;
    }
    static List<Integer> search(char[] text, char[] ptrn) { // O(N)
        ArrayList<Integer> pos = new ArrayList<Integer>();
	int i = 0, j = 0;
	int[] b = preprocess(ptrn);
	while (i < text.length) {
	    while (j >= 0 && text[i] != ptrn[j]) {
		j = b[j];
	    }
	    i++;
	    j++;
	    if (j == ptrn.length) {
                pos.add(i - ptrn.length);
		j = b[j];
	    }
	}
        return pos;
    }
    public static void main(String args[]) {
        System.out.println(search("abcacdabab".toCharArray(), "ab".toCharArray())); // [0, 6, 8]
        System.out.println(search("a".toCharArray(), "ab".toCharArray())); // []
        System.out.println(search("aaaaa".toCharArray(), "a".toCharArray())); // [0, 1, 2, 3, 4]
    }
}
