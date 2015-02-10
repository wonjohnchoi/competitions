import java.util.*;
import java.io.*;

public class CHEFCH {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            String S = sc.next();
            char[] syms = new char[] {'-', '+'};
            int ans = Integer.MAX_VALUE;
            for (int j = 0; j < 2; j++) {
                int tot = 0;
                int cur = j;
                for (int i = 0; i < S.length(); i++) {
                    cur = (cur + 1) % 2;
                    if (S.charAt(i) != syms[cur]) {
                        tot++;
                    }
                }
                ans = Math.min(ans, tot);
            }
            System.out.println(ans);
        }
    }
}
