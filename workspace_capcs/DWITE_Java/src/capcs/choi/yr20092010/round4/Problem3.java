package capcs.choi.yr20092010.round4;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/moving_at_the_same_time.html
 * 
 * @author Wonjohn Choi
 */
public class Problem3 {

    public static void main(String[] args) {

        // Input/Output
        Scanner sc = null;
        PrintWriter pw = null;

        try {
            sc = new Scanner(new FileReader("DATA3.txt"));
            pw = new PrintWriter(new FileWriter("OUT3.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // for each input
        for (int it = 0; it < 5; it++) {
            char[] map = sc.next().toCharArray(); // get input

            // move indicates the power of movement
            for (int move = 0; move < 5; move++) {
                char[] newMap = new char[map.length];

                Arrays.fill(newMap, '.'); // fill with empty space

                // for each position of map
                for (int pos = 0; pos < map.length; pos++) {
                    // if the position is not dot,
                    if (map[pos] != '.') {
                        // get updated position
                        int newPos = pos + (int) (map[pos] - '0') * move;

                        // process to check whether there is a number already in
                        // the new position
                        if (newPos < newMap.length) {
                            if (newMap[newPos] == '.') {
                                newMap[newPos] = map[pos];
                            } else {
                                newMap[newPos] += (int) (map[pos] - '0');
                            }
                        }
                    }
                }

                pw.println(new String(newMap));
            }
        }

        pw.close();
        sc.close();
    }
}
