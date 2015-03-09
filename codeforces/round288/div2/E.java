import java.util.*;
import java.io.*;
public class E {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        char[] b = new char[2 * N];
        Arrays.fill(b, ' ');
        boolean impossible = false;
        for (int i = 0; i < N; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            int lb = idx(b, 0, ' ');
            int rb =
            int rb = lb + l;
            if (b[rb] == ')') {
                impossible = true;
                break;
            }
            if ((rb - lb) % 2 != 1) {
                rb++;
                if (b[rb] == ')') {
                    impossible = true;
                    break;
                }
            }
            if (rb >= nextOccu || rb > lb + r) {
                impossible = true;
                break;
            }
            b[lb] = '(';
            b[rb] = ')';
        }
        if (impossible) {
            out.println("IMPOSSIBLE");
        } else {
            out.println(new String(b));
        }
    }
    static int idx(char[] b, int s, char c) {
        for (int i = s; i < b.length; i++) {
            if (b[i] == c) return i;
        }
        return b.length;
    }
}
