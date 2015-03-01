import java.util.*;
import java.io.*;
public class C {
    public static PrintStream out = System.out;
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        Scanner dic = new Scanner(new FileReader("garbled_email_dictionary.txt"));
        List<String> words = new ArrayList<String>();
        while (dic.hasNext()) words.add(dic.next());
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            String S = sc.next();
            int[][] numChanges = new int[S.length() + 1][5];
            for (int i = 0; i < numChanges.length; i++) {
                Arrays.fill(numChanges[i], 4001);
            }
            numChanges[0][0] = 0;
            for (int i = 0; i < S.length(); i++) {
                for (int k = 0; k < 5; k++) {
                    if (numChanges[i][k] == -1) continue;
                    for (String word : words) {
                        if (word.length() > S.length() - i) continue;
                        int newK = k;
                        int newChanges = 0;
                        boolean fail = false;
                        for (int j = 0; j < word.length(); j++) {
                            if (S.charAt(i + j) == word.charAt(j)) {
                                newK--;
                            } else {
                                if (newK > 0) {
                                    fail = true;
                                } else {
                                    newK = 4;
                                    newChanges++;
                                }
                            }
                        }
                        newK = Math.max(newK, 0);
                        if (!fail) {
                            numChanges[i + word.length()][newK]
                                = Math.min(numChanges[i][k] + newChanges,
                                           numChanges[i + word.length()][newK]);
                        }
                    }
                }
            }
            int minChanges = Integer.MAX_VALUE;
            for (int i = 0; i < 5; i++) {
                minChanges = Math.min(minChanges, numChanges[S.length()][i]);
            }
            String ans = "" + minChanges;
            out.printf("Case #%d: %s\n", tc, ans);
        }
    }
}
