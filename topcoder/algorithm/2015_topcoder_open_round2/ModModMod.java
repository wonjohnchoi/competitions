import java.util.*;
import java.io.*;
public class ModModMod {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static long findSum(int[] m, int R) {
        HashMap<Long, Long> cnts = new HashMap<>();
        cnts.put((long) R, 1L);
        for (int i = 0; i < m.length; i++) {
            int mm = m[i];
            HashMap<Long, Long> ncnts = new HashMap<>();
            for (long key : cnts.keySet()) {
                long quo = key / mm;
                long rem = key % mm;
                if (quo > 0 && mm - 1 > 0) put(ncnts, mm - 1, quo * cnts.get(key));
                if (rem > 0) put(ncnts, rem, cnts.get(key));
            }
            cnts = ncnts;
        }
        long tot = 0;
        for (long key : cnts.keySet()) {
            tot += (1 + key) * key / 2 * cnts.get(key);
        }
        return tot;
    }
    static void put(HashMap<Long, Long> cnts, long val, long cnt) {
        if (!cnts.containsKey(val)) {
            cnts.put(val, 0L);
        }
        cnts.put(val, cnts.get(val) + cnt);
    }
    public static void main(String args[]) {

    }
}
