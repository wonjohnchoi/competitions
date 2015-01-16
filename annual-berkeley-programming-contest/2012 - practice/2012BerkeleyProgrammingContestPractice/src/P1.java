import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


class P1 {
    static Scanner in = new Scanner(System.in);
    static boolean debug = false;
    public static void d(String str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(double str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(boolean str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(float str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void d(Object str) {
        if (debug) {
            System.out.println("DEBUG: " + str);
        }
    }
    public static void o(String str) {
        System.out.println(str);
    }
    public static void o(int str) {
        System.out.println(str);
    }
    public static void o(boolean str) {
        System.out.println(str);
    }
    public static void o(float str) {
        System.out.println(str);
    }
    public static void o(long str) {
        System.out.println(str);
    }
    public static void o(Object str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        // debug = true;
        ArrayList<Long> nums = new ArrayList<Long>();
        long sum = 0;
        int cnt = 0;
        while (in.hasNextLong()) {
            long num = in.nextInt();
            nums.add(num);
            sum += num;
            cnt += 1;
            
        }
        
        double mean = (double) sum / cnt;   
        double minDiff = Long.MAX_VALUE;
        d("Mean: " + mean);
        for (Long num : nums) {
            minDiff = Math.min(minDiff, Math.abs(num - mean));
        }
        
        ArrayList<Long> goodNums = new ArrayList<Long>();
        for (Long num : nums) {
            if (minDiff == Math.abs(num - mean)) {
                goodNums.add(num);
            }
        }
        Collections.sort(goodNums);

        String output = "";

        for (Long num : goodNums) {
            output += num + " ";
        }
        d(nums);
        d(mean);
        d(minDiff);
        d(goodNums);
        o(output.trim());

        in.close();
        System.exit(0);
    }
}
