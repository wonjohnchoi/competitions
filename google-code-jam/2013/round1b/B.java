import java.util.*;
import java.io.*;
public class B {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int MAX_N = 1000000;
        int[] level = new int[MAX_N];
        int[] levelStart = new int[MAX_N];
        int[] levelSize = new int[MAX_N];
        int curN = 0;
        int size = 1;
        int curLevel = 0;
        boolean done = false;
        while (true) {
            for (int i = 0; i < size && curN < MAX_N; i++, curN++) {
                levelStart[curN] = curN - i;
                levelSize[curN] = size;
                level[curN] = curLevel;
                // System.out.println(curN - i + " " + size + " " + curLevel);
            }
            if (curN == MAX_N) {
                break;
            }
            curLevel++;
            size = curLevel * 4 + 1;
        }
        
        for (int tc = 1; tc <= T; tc++) {
            int N, X, Y;
            N = sc.nextInt();
            X = sc.nextInt();
            Y = sc.nextInt();
            int last = N - 1;
            int l = level[last];
            int lstart = levelStart[last];
            int lsize = levelSize[last];
            int lend = lstart + lsize - 1;
            int idx = N - lstart;
            //System.out.printf("Case #%d: %d\n", tc, minUsed);
        }
    }
}
