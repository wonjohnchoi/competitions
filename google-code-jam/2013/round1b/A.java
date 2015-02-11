import java.util.*;
import java.io.*;
public class A {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int A = sc.nextInt();
            int N = sc.nextInt();
            int[] motes = new int[N];
            for (int n = 0; n < N; n++) motes[n] = sc.nextInt();
            Arrays.sort(motes);
            int maxMote = motes[motes.length - 1];
            int[][] used = new int[maxMote * 3 + 1][N + 1];
            for (int i = 0; i < used.length; i++) Arrays.fill(used[i], Integer.MAX_VALUE);
            int minUsed = Integer.MAX_VALUE;
            if (maxMote < A) {
                minUsed = 0;
            } else {
                used[A][0] = 0;
                for (int i = A; i <= maxMote; i++) {
                    for (int j = 0; j < N; j++) {
                        if (used[i][j] == Integer.MAX_VALUE) continue;
                        if (motes[j] < i) {
                            used[i + motes[j]][j + 1] = Math.min(used[i + motes[j]][j + 1], used[i][j]);
                            if (maxMote < i + motes[j])
                                minUsed = Math.min(minUsed, used[i][j]);
                            continue;
                        }
                        // make i - 1 sized mote and eat.
                        used[i + i - 1][j] = Math.min(used[i + i - 1][j], used[i][j] + 1);
                        if (maxMote < i + i - 1)
                            minUsed = Math.min(minUsed, used[i][j] + 1);
                        
                        // just delete jth
                        used[i][j + 1] = Math.min(used[i][j + 1], used[i][j] + 1);
                        if (j + 1 == N)
                            minUsed = Math.min(minUsed, used[i][j] + 1);
                    }
                }
            }
            System.out.printf("Case #%d: %d\n", tc, minUsed);
        }
    }
}
