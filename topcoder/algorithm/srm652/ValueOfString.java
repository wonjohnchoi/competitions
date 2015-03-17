import java.util.*;
import java.io.*;
public class ValueOfString {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static int findValue(String s) {
        int tot = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = (int) (s.charAt(i) - 'a') + 1;
            int cnt = 0;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(i) >= s.charAt(j)) cnt++;
            }
            tot += c * cnt;
        }
        out.println(tot);
        return tot;
    }
    public static void main(String args[]) {
        findValue("babca");
    }
}
