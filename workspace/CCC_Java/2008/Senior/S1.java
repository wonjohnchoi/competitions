package Senior;

import java.io.*;
import java.util.*;

/*
 * By Wonjohn Choi
 */
public class S1 {
	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(new FileReader("s1.in"));

		String currentCity = "";
		String bestCity = "";
		short itsTemp = 200;
		short currentTemp = 0;
		while (sc.hasNext()) {
			currentCity = sc.next();
			currentTemp = sc.nextShort();
			if (currentTemp < itsTemp) {
				bestCity = currentCity;
				itsTemp = currentTemp;
			}
		}

		System.out.println(bestCity);
	}
}
