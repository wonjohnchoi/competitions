import java.util.*;
import java.io.*;
public class DevuAndPlantingTrees {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static int maximumTreesDevuCanGrow(String[] garden){
        int i = 0;
        int cnt = 0;
        while (i < garden[0].length()) {
            if (has(garden, i)) {
                i += 2;
                cnt++;
            } else if (i + 1 == garden[0].length()) {
                i++;
                cnt++;
            } else if (has(garden, i + 1)) {
                i++;
            } else {
                cnt++;
                i += 2;
            }
        }
        return cnt;
    }
    static boolean has(String[] garden, int i) {
        return garden[0].charAt(i) == '*' || garden[1].charAt(i) == '*';
    }
    public static void main(String args[]) {
        out.println(maximumTreesDevuCanGrow(new String[] {".", "."}));
    }
}
