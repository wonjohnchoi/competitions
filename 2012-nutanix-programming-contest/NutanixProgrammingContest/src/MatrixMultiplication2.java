import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * @author wonjohnchoi
 *
 */
public class MatrixMultiplication2 {
	static double time;

	public static void main(String args[]) {
		//recursive brute force. may run out of time.
		
		Scanner in = new Scanner(System.in);
		
		
		/*
		 * 49
3
-1
-1

1
10
81
1000
1000000
1000
1000000
-1
255025
909000
-1
-1
36
16
		 */
		int t = in.nextInt();
		for (int i = 0; i < t;i += 1) {
			
			//System.out.println(i);
			int n = in.nextInt();
			
			boolean[][] graph = new boolean[1001][1001];
			for (int j = 0; j < n; j += 1) {
				graph[in.nextInt()][in.nextInt()] = true;
			}
			
			for (int j = 0; j < graph.length; j += 1) {
				for (int k = 0; k < graph.length; k += 1) {
					for (int l = 0; l < graph.length; l += 1) {
						if (graph[j][k] && graph[k][l]) {
							graph[j][k] = false;
							graph[k][l] = false;
							graph[j][l] = true;
						}
					}
				}
			}
			
			boolean found = false;
			boolean bad = false;
			int ans = 0;
			for (int j = 0; j < graph.length; j += 1) {
				for (int k = 0; k < graph.length; k += 1) {
					if (graph[j][k]) {
						if (found) {
							bad = true;
						}
						found = true;
						ans = j * k;
					}
				}
			}
			
			if (bad) {
				System.out.println(-1);
				continue;
			}
			System.out.println(ans);
			
			
			
		}
	}
}
