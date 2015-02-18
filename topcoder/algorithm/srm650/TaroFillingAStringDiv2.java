import java.util.*;
import java.io.*;
public class TaroFillingAStringDiv2 {
    public static int getNumber(String s) {
        int minCnt = Integer.MAX_VALUE;
        for (char start : new char[] {'A', 'B'}) {
            int cnt = 0;
            char prev = start;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '?') {
                    if (prev == 'A') {
                        prev = 'B';
                    } else {
                        prev = 'A';
                    }
                } else if (c == 'A') {
                    if (prev == 'A') {
                        cnt++;
                    }
                    prev = c;
                } else {
                    if (prev == 'B') {
                        cnt++;
                    }
                    prev = c;
                }
            }
            minCnt = Math.min(minCnt, cnt);
        }
        return minCnt;
    }
    public static void main(String args[]) {
        System.out.println(getNumber("A??B???AAB?A???A"));
        System.out.println(getNumber("?BB?BAAB???BAB?B?AAAA?ABBA????A?AAB?BBA?A?"));
        System.out.println(getNumber("A"));
        System.out.println(getNumber("?"));
        System.out.println(getNumber("B"));
        System.out.println(getNumber("AB"));
        System.out.println(getNumber("BA"));
        System.out.println(getNumber("AA"));
        System.out.println(getNumber("BB"));
        System.out.println(getNumber("BB?"));
        System.out.println(getNumber("B?B?"));
    }
}
