package March14;

/*
 ID: yojo1002
 LANG: JAVA
 TASK: sboost
 */
import java.util.*;
import java.io.*;

public class sboost {
	public static void main(String args[]) throws IOException {
		String File = "sboost";
		Scanner in = new Scanner(new FileReader(File + ".in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(File + ".out")));

		Item ini = new Item(in.nextLong(), in.nextLong(), 0);
		int k = in.nextInt();
		Item[] al = new Item[k];
		for (int i = 0; i < k; i++) {
			al[i] = new Item(in.nextLong(), in.nextLong(), i + 1);
		}

		Arrays.sort(al);

		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < k; i++) {

			if (ini.accel < al[i].accel) {
				ini.force += al[i].force;
				ini.mass += al[i].mass;
				result.add(al[i].index);
				ini.update();
			}

			else {
				break;
			}

		}

		Collections.sort(result);
		if (result.size() == 0) {
			out.println("NONE");
		} else
			for (int i = 0; i < result.size(); i++) {
				out.println(result.get(i));
			}
		in.close();
		out.close();
		System.exit(0);

	}
}

class Item implements Comparable<Item> {
	long mass;
	long force;
	int index;
	double accel;

	public Item(long f, long m, int i) {
		mass = m;
		force = f;
		index = i;
		accel = (double) force / (double) mass;
	}

	public void update() {
		accel = (double) force / (double) mass;
	}

	public int compareTo(Item o) {
		if (accel < o.accel) {
			return 1;
		}
		if (accel > accel) {
			return -1;
		}
		return 0;

	}
}
