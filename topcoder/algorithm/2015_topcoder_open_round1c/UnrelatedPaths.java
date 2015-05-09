import java.util.*;
import java.io.*;
public class UnrelatedPaths {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static int maxUnrelatedPaths(int[] parent){
        int N = parent.length + 1;
        boolean[] leaf = new boolean[N];
        Arrays.fill(leaf, true);
        for (int i = 1; i < N; i++) {
            // i-> parent[i - 1]
            leaf[parent[i - 1]] = false;
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (leaf[i]) cnt++;
        }
        return cnt;
    }
    public static void main(String args[]) {

    }
}
