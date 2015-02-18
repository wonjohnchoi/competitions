import java.util.*;
import java.io.*;
public class TaroJiroDividing {
    public static int getNumber(int a, int b) {
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        } // a >= b
        while (a % 2 != 1 && a != b) {
            a /= 2;
        }
        if (a != b) {
            return 0;
        }
        int cnt = 1;
        while (b % 2 != 1) {
            cnt++;
            b /= 2;
        }
        return cnt;
    }
    public static void main(String args[]) {
        System.out.println(getNumber(12, 12));
        System.out.println(getNumber(12, 96));
        System.out.println(getNumber(1, 1000000000));
        System.out.println(getNumber(3, 1000000000));
        System.out.println(getNumber(1000000000, 1000000000));
        System.out.println(getNumber((int)Math.pow(5, 9), 1000000000));
        System.out.println(getNumber(1, 1));
    }
}
