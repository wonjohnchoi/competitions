package Senior;

/*
 * By Wonjohn Choi
 */
import java.io.*;
import java.util.*;

public class S4 {
	static int ans = 0;

	public static void main(String args[]) throws IOException {

		Scanner sc = new Scanner(new FileReader("s4.in"));
		int num = sc.nextInt();
		int set[] = new int[4];
		for (int i = 0; i < num; i++) {
			set[0] = sc.nextInt();
			set[1] = sc.nextInt();
			set[2] = sc.nextInt();
			set[3] = sc.nextInt();

			max(set);

			int temp[] = new int[4];
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					if (k != j)
						for (int l = 0; l < 4; l++) {
							if (l != k && l != j)
								for (int m = 0; m < 4; m++) {
									if (m != l && m != k && m != j) {
										temp[0] = set[j];
										temp[1] = set[k];
										temp[2] = set[l];
										temp[3] = set[m];

										max(temp);

									}
								}
						}
				}

			}

			System.out.println(ans);

			ans = 0;
		}

	}

	public static int max(int set[]) {
		int length = set.length;

		if (set.length == 1) {
			if (ans < set[0] && set[0] <= 24) {
				ans = set[0];
			}
			return 0;
		}
		int temp[];

		for (int i = 0; i < length - 1; i++) {
			temp = new int[set.length - 1];
			for (int j = 0; j < i; j++)
				temp[j] = set[j];
			for (int j = i + 1; j < length - 1; j++)
				temp[j] = set[j + 1];
			temp[i] = set[i] + set[i + 1];
			max(temp);

			temp[i] = set[i] * set[i + 1];
			// System.out.println(temp[i]);
			max(temp);

			temp[i] = set[i] - set[i + 1];
			max(temp);

			if (set[i + 1] != 0) {
				if (set[i] % set[i + 1] == 0) {
					temp[i] = set[i] / set[i + 1];
					max(temp);
				}
			}

		}
		return 0;

	}

}
