import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * @author wonjohnchoi
 *
 */
public class Laundry {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int i = 0;i < t; i += 1) {
			
			long nCoins = in.nextLong();
			int nMachines = in.nextInt();
			long required[] = new long[nMachines];
			long returned[] = new long[nMachines];
			
			for (int j = 0; j < nMachines; j += 1) {
				required[j] = in.nextLong();
			}
			

			for (int j = 0; j < nMachines; j += 1) {
				returned[j] = in.nextLong();
			}
			
			LinkedList<Machine> machines = new LinkedList<Machine>();
			for (int j = 0; j < nMachines; j += 1) {
				machines.add(new Machine(required[j], returned[j]));
			}
			
			Collections.sort(machines);
			int nWashes = 0;
			
			while (!machines.isEmpty()) {
				Machine cur = machines.remove();
				//System.out.println("Using machine: " + cur.required + " " +cur.returned);
				if (nCoins >= cur.required) {
					long usable = nCoins - cur.required;
					//System.out.println(cur.required + " " + cur.returned + " " + cur.diff);
					long washes = usable / cur.diff + 1;
					nWashes += washes;
					nCoins -= nWashes * cur.diff;
				}
			}
			
			System.out.println(nWashes);
		}
	}
	
	static class Machine implements Comparable<Machine>{
		long required;
		long returned;
		long diff;
		Machine(long required, long returned) {
			this.required = required;
			this.returned  =returned;
			diff = required - returned;
		}
		@Override
		public int compareTo(Machine arg0) {
			return (int)(diff - arg0.diff);
		}
	}
}
