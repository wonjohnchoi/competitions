import java.util.*;
import java.io.*;
public class Template {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {

            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
