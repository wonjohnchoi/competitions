import java.util.*;
import java.io.*;
// TIME_USED: 56:39
public class A {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            long N;
            int PD, PG;
            N = sc.nextLong();
            PD = sc.nextInt();
            PG = sc.nextInt();
            // D<=G, D<=N
            // PD<=100, PG<=100, T<=2000, N<=1e15
            // Possible, Broken?
            // 1 100 50 => D=1. 1/1 today. 1/2 prev.
            // 9 80 56 => 4/5, 14/25
            // PD : 100 => a : b
            // PG : 100 => c : d
            // bk<=dj. ak<=cj. bk<=N<=1e15. can we find positive integers k and j satisfying the left side inequalities?
            // a, b, c, d, N are fixed integers. k, j are variable integers. a<=b,c<=d are given.
            // k<=N/b. k/j<=c/a. k/j<=d/b.
            // k<=N/b, k/j<=min(c/a, d/b) where 0.01 <= c/a, d/b <= 100  and 1e13 <= N/b <=1e15

            // example: 9 80 56 N PD PG
            // 80 : 100 => a : b = 4 : 5
            // 56 : 100 => c : d = 14 : 25
            // k<=9/5=1.8, k/j<=min(3.5, 5)=3.5
            // k = 1, j = 1 4/5 14/25, or k = 1 j = 2. 4/5 28/50
            // example: 10 10 100
            // a : b = 1 : 10, c : d = 1 : 1
            // k<=10/10=1, k/j<=min(1,0.1)=0.1
            // k = 1, j = 10. 1/10,10/10

            // forgot constraint: (b - a)k <= (d - c)j. should use instead of bk<=dj.
            // k/j<=(d-c)/(b-a)
            // k<=N/b, k/j<=min(c/a, (d-c)/(b-a)) where 0 <= a,c <= 100, 1<=b,d<=100, 0<=d-c,b-a<=100  and 1e13 <= N/b <=1e15, bk>=1
            // example: 10 10 100
            // a : b = 1 : 10, c : d = 1 : 1
            // k<=10/10=1, k/j<=min(1,0)=0
            // k = 1, j = 10. 1/10,10/10

            // golen rule.
            // k>=1, so N/b>=1.
            // k/j!=0, so min(c/a, (d-c)/(b-a))!=0, so c!=0 (assuming a!=0). if both a == c == 0, it is ok.
            // d!=c (assuming b!=a). if both d==c and a==b, d==c is ok. 
            
            // again
            // N>=b. c!=0 || (a==0 && c==0). d!=c || (d==c && a == b)
            
            int[] ab = reduce(PD, 100);
            int[] cd = reduce(PG, 100);
            int a, b, c, d;
            a = ab[0]; b = ab[1]; c = cd[0]; d = cd[1];
            String ans;
            if (N >= b
                && (c!=0 || (a==0 && c==0))
                && (d!=c || (d==c && a == b))) {
                ans = "Possible";
            } else {
                ans = "Broken";
            }
            System.out.printf("Case #%d: %s\n", tc, ans);
        }
        // for (int i = 0; i <= 100; i++)
        //    System.out.println(i + ":100=" + reduce(i, 100)[0] + ":" +reduce(i,100)[1]);

    }
    public static int[] reduce(int a, int b) {
        int div = 2;
        while (div <= a || div <= b) {
            while (a % div == 0 && b % div == 0) {
                a /= div;
                b /= div;
            }
            div++;
        }
        return new int[]{a, b};
    }
}
