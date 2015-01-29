import java.util.*;
import java.io.*;

// Brute force but does not work on difficult input.
public class H {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(new FileReader("H.in"));
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            Integer[] S = new Integer[M];
            for (int i = 0; i < M; i++) {
                S[i] = sc.nextInt();
            }
            Arrays.sort(S, Collections.reverseOrder());
            ArrayList<Integer> digits = new ArrayList<Integer>();
            int rem = N;
            int removedIdx = -1;
            while (rem != 0) {
                int maxDigit = (int) (rem / (digits.size() + 1));
                int idx = Arrays.binarySearch(S, maxDigit, Collections.reverseOrder());
                if (idx < 0) {
                    idx = -(idx + 1); // if not exact, choose one that is < maxDigit and is the largest.
                }
                if (removedIdx >= idx) {
                    idx = removedIdx + 1;
                }
                // in this case, no value in S is smaller than maxDigit. 
                if (idx == S.length) {
                    if (digits.size() == 0) {
                        break;
                    }
                    removedIdx = digits.remove(digits.size() - 1);
                    rem += S[removedIdx] * (digits.size() + 1);
                    continue;
                }
                
                removedIdx = -1;
                digits.add(idx);
                rem -= S[idx] * digits.size();
            }

            int ans = 0;
            if (rem != 0) {
                ans = -1;
            } else {
                for (Integer digit : digits) {
                    ans += S[digit];
                }
            }
            System.out.println(ans);
        }
    } 
}
