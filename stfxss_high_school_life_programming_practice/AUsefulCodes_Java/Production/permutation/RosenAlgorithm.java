package permutation;

import java.util.ArrayList;

public class RosenAlgorithm {

	public static void main(String[] args) {
		String input="123456789";
		char[] value =input.toCharArray();
		
		permute(value, 0, input.length());
	}
	
	// Rosen's algorithm
	public static ArrayList<String> container=new ArrayList<String>();
	
	public static void permute(char[] v, int a, int b) {
		//duplicate case
		String temp=new String(v);
		if(!container.contains(temp))
		{	
			container.add(temp);
			//System.out.println(temp);
		}
		
		for (int i = b - 2; i >= a; i--) {
			for (int j = i + 1; j < b; j++) {
				swap(v, i, j);
				permute(v, i + 1, b);
			}
			rotate(v, i, b);
		}
	}

	public static void swap(char[] v, int a, int b) {
		char t = v[a];
		v[a] = v[b];
		v[b] = t;
	}

	public static void rotate(char[] v, int a, int b) {
		char t = v[a];
		for (int i = a; i < b - 1; i++)
			v[i] = v[i + 1];
		v[b - 1] = t;
	}

}