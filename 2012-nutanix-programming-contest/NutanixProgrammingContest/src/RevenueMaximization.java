import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author wonjohnchoi
 *
 */
public class RevenueMaximization {
	public static  void main(String args[]) {
		Scanner in = new Scanner(System.in);
		
		int t = in.nextInt();
		
		for (int i = 0; i < t; i += 1) {
			int n = in.nextInt();
			
			int[] ps = new int[n];
			for (int j = 0; j < n; j += 1) {
				ps[j] = in.nextInt();
			}
			Arrays.sort(ps);
			
			//n<=4
			int ans = 0;
			if (n <= 4) {
				for (int j = 0;j < n;j += 1) {
					ans += ps[j];
				}
				System.out.println(ans);
				continue;
			}
			
			//System.out.println(Arrays.toString(ps));
			//brute force
			ans = Integer.MIN_VALUE;
			for (int j = 0;j < n ;j += 1) {
				for (int k = j + 1; k < n ;k += 1) {
					for (int m = k + 1; m < n; m += 1) {
						for (int v = m + 1; v < n; v += 1) {
							ans = Math.max(ans, ps[j] * (k - j) + ps[k] * (m - k) + ps[m] * (v - m) + ps[v] * (n - v));
							//System.out.println("ans:"+ans+" "+j+" "+k+" "+m+" "+v);
						}
					}
				}
			}
			System.out.println(ans);
		}
	}
}
