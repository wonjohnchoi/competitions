import java.util.*;
import java.io.*;
public class A {
    public static PrintStream out = System.out;
    static class Node {
        int[] cnt = new int[10];
        public int compareTo(Node n) {
            int sum1, sum2;
            sum1 = sum2 = 0;
            for (int i = 0; i < 10; i++) {
                sum1 += cnt[i];
                sum2 += n.cnt[i];
            }
            if (sum1 != sum2) return sum1 - sum2;
            for (int i = 9; i >= 0; i--) {
                if (cnt[i] > n.cnt[i]) return 1;
                else if (cnt[i] < n.cnt[i]) return -1;
            }
            return 0;
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String ans = "";
        int K = sc.nextInt();
        String S = sc.next();
        int[][] cnts; // 2, 3, 5, 7
        cnts = new int[][] {
            {0, 0, 0, 0}, // 0
            {0, 0, 0, 0}, // 1
            {1, 0, 0, 0}, // 2
            {1, 1, 0, 0}, // 3
            {3, 1, 0, 0}, // 4
            {3, 1, 1, 0}, // 5
            {4, 2, 1, 0}, // 6
            {4, 2, 1, 1}, // 7
            {7, 2,, 1, 1}, // 8
            {7, 4, 1, 1} // 9
        };
        int[] totCnt = new int[4];
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            int ci = (int) (c - '0');
            for (int j = 0; j < 4; j++) {
                totCnt[j] += cnts[ci][j];
            }
        }
        Node[][][][] cache = new Node[7 * K + 1][4 * K + 1][K + 1][K + 1];
        cache[0][0][0][0] = new Node();
        for (int i1 = 0; i1 < cache.length; i1++) {
            for (int i2 = 0; i2 < cache[0].length; i2++) {
                for (int i3 = 0; i3 < cache[0][0].length; i3++) {
                    for (int i4 = 0; i4 < cache[0][0][0].length; i4++) {
                        Node cur = cache[i1][i2][i3][i4];
                        if (cur == null) continue;
                        // out.println(i1 + " " + i2 + " " + i3 + " " + i4 + " " + Arrays.toString(cur.cnt));
                        expand : for (int j = 2; j < 10; j++) {
                            int[] curCnt = new int[] {i1, i2, i3, i4};
                            int[] newCnt =  new int[4];
                            for (int k = 0; k < 4; k++) {
                                newCnt[k] = curCnt[k] + cnts[j][k];
                            }
                            if (newCnt[0] >= cache.length
                                || newCnt[1] >= cache[0].length
                                || newCnt[2] >= cache[0][0].length
                                || newCnt[3] >= cache[0][0][0].length)
                                continue;
                            Node nextOrig = cache[newCnt[0]][newCnt[1]][newCnt[2]][newCnt[3]];
                            Node next = new Node();
                            for (int l = 0; l < 10; l++) {
                                next.cnt[l] = cur.cnt[l];
                            }
                            next.cnt[j]++;
                            if (nextOrig == null
                                || nextOrig.compareTo(next) < 0) {
                                cache[newCnt[0]][newCnt[1]][newCnt[2]][newCnt[3]] = next;
                                // out.println(Arrays.toString(newCnt) + " " + Arrays.toString(next.cnt));
                            }
                        }
                    }
                }
            }
        }
        int[] finalCnt = cache[totCnt[0]][totCnt[1]][totCnt[2]][totCnt[3]].cnt;
        for (int i = 9; i >= 0; i--) {
            for (int j = 0; j < finalCnt[i]; j++) {
                ans += i;
            }
        }
        ans = ans + "";
        out.printf("%s\n", ans);
    }
}
