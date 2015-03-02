import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String ans = "";
        int N = sc.nextInt();
        String S = sc.next();
        boolean[] found = new boolean[26];
        for (int i = 0; i < N; i++) {
            char c = S.charAt(i);
            if ('a' <= c && c <= 'z') {
                found[(int) (c - 'a')] = true;
            } else {
                found[(int) (c - 'A')] = true;
            }
        }
        boolean good = true;
        for (int i = 0; i < 26; i++) {
            if (!found[i]) good = false;
        }
        ans = good ? "YES" : "NO";
        out.printf("%s\n", ans);
    }
}
