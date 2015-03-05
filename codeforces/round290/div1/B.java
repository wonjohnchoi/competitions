import java.util.*;
import java.io.*;
public class B {
    public static PrintStream out = System.out;
    static int N;
    static int[] nums, costs;
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        nums = new int[N];
        costs = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            costs[i] = sc.nextInt();
        }
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int cost = getCost(nums[i]);
            if (cost != Integer.MAX_VALUE)
                minCost = Math.min(minCost, costs[i] + cost);
        }
        if (minCost == Integer.MAX_VALUE) minCost = -1;
        out.println(minCost);
    }
    static HashMap<Integer, Integer> costMap = new HashMap<Integer, Integer>();
    static int getCost(int a) {
        if (a == 1) return 0;
        Integer minCost = costMap.get(a);
        if (minCost == null) {
            minCost = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                int gcd = gcd(a, nums[i]);
                if (gcd < a) {
                    int cost = getCost(gcd);
                    if (cost != Integer.MAX_VALUE) {
                        minCost = Math.min(minCost, cost + costs[i]);
                    }
                }
            }
            costMap.put(a, minCost);
        }
        // out.println(minCost + " " + a);
        return minCost;
    }
    static int gcd(int a, int b) {
        if (a < b) return gcd(b, a);
        if (b == 0) return a;
        return gcd(a % b, b);
    }
}
