import java.util.*;
import java.io.*;
public class Mutalisk {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static int minimalAttacks(int[] x){
        // 9 & 3
        int[][] used = new int[100][2];
        int usedI = 0;
        outer : while (true) {
            boolean more = false;
            for (int i = 0; i < x.length; i++) {
                if (x[i] >= 9) {
                    more = true;
                    break;
                }
            }
            if (!more) break;
            for (int j = 0; j < x.length; j++) {
                for (int i = 0; i < x.length; i++) {
                    if (x[i] % 9 >= 3 && i != j && x[j] >= 9) {
                        used[usedI++] = new int[] {i, j};
                        x[i] -= 3;
                        x[j] -= 9;
                        continue outer;
                    }
                }
            }
            int bestI = 0;
            for (int i = 1; i < x.length; i++) {
                if (x[bestI] > x[i]) bestI = i;
            }
            if (x[bestI] >= 9) {
                    for (int j = i + 1; j < x.length; j++) {
                        if (x[j] >= 9) {
                            used[usedI++] = new int[] {i, j};
                            x[i] -= 3;
                            x[j] -= 9;
                        }
                    }
                }
            }
        }
    }
    public static void main(String args[]) {

    }
}
