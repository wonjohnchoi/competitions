package capcs.choi.yr20092010.round6;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * http://dwite.ca/questions/air_travel_planning.html
 * @author Wonjohn Choi
 *
 */
public class Problem5 {
    /**
     * class to save a set of information
     * @author Wonjohn Choi
     *
     */
    static class Airport {
        String cur;
        String next;
        int price;

        Airport(String newCur, String dest, int newPrice) {
            cur = newCur;
            next = dest;
            price = newPrice;
        }
    }
    
    
    static Airport[] group;
    static int min;

    public static void main(String args[]) throws IOException {
        //I/O
        PrintWriter pw = new PrintWriter(new FileWriter("OUT5.txt"));
        Scanner sc = new Scanner(new FileReader("DATA5.txt"));

        //for each input
        for (int i = 0; i < 5; i++) {
            int nAir = sc.nextInt(); //get # of airports

            group = new Airport[nAir]; //make an array to store the airports

            //for each airport
            for (int j = 0; j < nAir; j++) {
                group[j] = new Airport(sc.next(), sc.next(), sc.nextInt()); //save information
            }

            boolean used[] = new boolean[nAir]; //array to remember whether an airport is used
            min = Integer.MAX_VALUE;

            //for
            for (int j = 0; j < group.length; j++) {
                if (group[j].cur.equals("YYZ")) {
                    find(j, 0, used);
                }
            }

            pw.println(min);
        }

        //finish I/O
        pw.close();
        sc.close();

    }

    /**
     * recursively find the mininum price
     */
    public static void find(int loc, int count, boolean newUsed[]) {
        boolean used[] = Arrays.copyOf(newUsed, newUsed.length);
        used[loc] = true;

        if (group[loc].next.equals("SEA")) {
            min = Math.min(count + group[loc].price, min);
        } else {
            for (int i = 0; i < group.length; i++) {
                if (!used[i] && group[loc].next.equals(group[i].cur)) {
                    find(i, count + group[loc].price, used);
                }
            }
        }
    }
}

