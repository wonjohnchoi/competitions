import java.util.*;
import java.io.*;
public class C2 {
    public static PrintStream out = System.out;
    public static String putStar(String word, int i) {
        String left, right;
        // System.out.println(word + " " + i);
        if (i == 0) left = "";
        else left = word.substring(0, i);
        if (i == word.length() - 1) right = "";
        else right = word.substring(i + 1, word.length());
        return left + "*" + right;
    }
    public static List<String> putStars(String word, int n) {
        List<String> words = new ArrayList<String>();
        if (n == 1) {
            for (int i = 0; i < word.length(); i++) {
                words.add(putStar(word, i));
            }
        } else if (n == 2) {
            for (int i = 0; i < word.length(); i++) {
                String oneStar = putStar(word, i);
                for (int j = i + 5; j < word.length(); j++) {
                    words.add(putStar(oneStar, j));
                }
            }
        }
        return words;
    }
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        Scanner dic = new Scanner(new FileReader("garbled_email_dictionary.txt"));
        HashMap<String, Integer>  map = new HashMap<String, Integer>();
        int MAX_WORD_LENGTH = 0;
        while (dic.hasNext()) {
            String word = dic.next();
            map.put(word, 0);
            // System.out.println(putStars(word, 2));
            for (String starWord : putStars(word, 1)) map.put(starWord, 1);
            for (String starWord : putStars(word, 2)) map.put(starWord, 2);
            MAX_WORD_LENGTH = Math.max(MAX_WORD_LENGTH, word.length());
        }
        // System.out.println(MAX_WORD_LENGTH); // 10
        // max two stars
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
                    for (int j = 1; j < Math.min(MAX_WORD_LENGTH + 1, S.length() - i + 1); j++) {
                        List<String> words = new ArrayList<String>();
                        String cur = S.substring(i, i + j);
                        words.add(cur);
                        words.addAll(putStars(cur, 1));
                        words.addAll(putStars(cur, 2));
                        for (String word : words) {
                            Integer newChanges = map.get(word);
                            if (newChanges == null) continue;
                            // out.println(word + " " + newChanges);
                            int newK = k;
                            boolean fail = false;
                            for (int l = 0; l < word.length() && !fail; l++) {
                                if (word.charAt(l) != '*') {
                                    newK--;
                                } else {
                                    if (newK > 0) {
                                        fail = true;
                                    } else {
                                        newK = 4;
                                    }
                                }
                            }
                            newK = Math.max(newK, 0);
                            if (!fail) {
                                numChanges[i + word.length()][newK]
                                    = Math.min(numChanges[i][k] + newChanges,
                                               numChanges[i + word.length()][newK]);
                                // out.println(i + word.length() + " " + newK + " " + numChanges[i + word.length()][newK]);
                            }
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
