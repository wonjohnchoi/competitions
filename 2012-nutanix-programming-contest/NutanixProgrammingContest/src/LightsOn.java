import java.util.Scanner;

/**
 * 
 * @author wonjohnchoi
 *
 */
public class LightsOn {
	public static void main(String args[]) {
		Scanner in = new Scanner (System.in);
		
		int t = in.nextInt();
		in.nextLine();
		for (int i = 0; i < t; i += 1) {
			String line = in.nextLine();
			String[] data = line.split(",");
			boolean[][] table = new boolean[data.length][data[0].length()];
			
			for (int j = 0;j < data.length; j += 1) {
				for (int k = 0;k < data[j].length(); k += 1) {
					table[j][k] = data[j].charAt(k) == '1' ? true : false;
				}
			}
			
			//goal is make everything true;
			int nSwitch = 0;
			for (int j = table.length - 1; j >= 0; j -= 1) {
				for (int k = table[j].length - 1; k >= 0; k -= 1) {
					if (!table[j][k]) {
						nSwitch += 1;
						for (int m = 0; m <= j; m += 1) {
							for (int n = 0; n <= k; n += 1) {
								table[m][n] = !table[m][n];
							}
						}
					}
				}
			}
			
			System.out.println(nSwitch);
			
			
			
		}
	}
}
