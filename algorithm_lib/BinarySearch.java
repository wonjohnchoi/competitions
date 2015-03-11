import java.util.*;
import java.io.*;

public class BinarySearch {
    static int search(double[] vals, double val) {
        return search(vals, 0, vals.length - 1, val);
    }
    static int search(double[] vals, int low, int high, double val) {
        while (low != high) {
            int mid = (low + high) / 2;
            if (val < vals[mid]) {
                high = mid;
            } else if (vals[mid] < val) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return low;
    }
    public static void main(String args[]) {
        double[] vals = new double[] {1, 10, 11, 15, 16, 16, 16, 16, 18};
        System.out.println(search(vals, 1)); // 0
        System.out.println(search(vals, 2)); // 1
        System.out.println(search(vals, 0)); // 0
        System.out.println(search(vals, 1.5)); // 1
        System.out.println(search(vals, 10.5)); // 2
        System.out.println(search(vals, 16)); // 4
        System.out.println(search(vals, 15.9)); // 4
    }
}
