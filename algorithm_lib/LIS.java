import java.util.*;
import java.io.*;
public class LIS {
    public static int getLIS(double[] seq) {
        double[] table = new double[seq.length];
        Arrays.fill(table, Double.MAX_VALUE);
        int size = 0;
        for (int i = 0; i < seq.length; i++) {
            int pos = Arrays.binarySearch(table, seq[i]);
            if (pos < 0) pos = -(pos + 1);
            table[pos] = seq[i];
            if (size < pos + 1) {
                size = pos + 1;
            }
        }
        return size;
    }
    public static void main(String args[]) {
        System.out.println(getLIS(new double[] {1, 4, 2, 5, 2, 6, 7, 3, 9, 10, 1})); // 7
        System.out.println(getLIS(new double[] {5, 4, 3, 2, 1})); // 1
        System.out.println(getLIS(new double[] {5, 1, 2, 3, 4})); // 4
        System.out.println(getLIS(new double[] {5, 1, 3, 2, 3, 0})); // 3
    }
}
