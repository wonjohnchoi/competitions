import java.util.*;
import java.io.*;
public class A {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        char[] vowels = "aeiou".toCharArray();
        // too many consecutive consonants => more hard_to_prounce => less common => more socially privileged
        // n-value: the number of substrings with at least n consecutive consonants in the name
        // quartz 3-value: quartz, uartz, artz, and rtz 4
        // begin, end => one substring
        // v0 ? c1 v1 c2 v2 c3 v3 ... ck vk?
        // any ci whose length < n, consider them like v.
        // if ci >= n, ci0 cin-1
        for (int tc = 1; tc <= T; tc++) {
            String S = sc.next();
            int L = sc.nextInt();
            int consecutive = 0;
            int lastIdx = -1;
            long tot = 0;
            for (int i = 0; i < S.length(); i++) {
                boolean isVowel = false;
                for (char vowel : vowels) {
                    if (S.charAt(i) == vowel) {
                        isVowel = true;
                        break;
                    }
                }
                if (isVowel) {
                    consecutive = 0;
                } else {
                    consecutive += 1;
                }
                if (consecutive >= L) {
                    lastIdx = i;
                }
                if (lastIdx != -1) {
                    tot += lastIdx - (L - 1) + 1;
                }
            }
            System.out.printf("Case #%d: %d\n", tc, tot);
        }
    }
}
