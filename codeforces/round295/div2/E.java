import java.util.*;
import java.io.*;
public class E {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String ans = "";
        int[][] a = new int[100001][100001];
        for (int i = 0; i < a.length; i++) {
            Arrays.fill(a[i], -1);
        }
        out.printf("%s\n", ans);
    }
}
