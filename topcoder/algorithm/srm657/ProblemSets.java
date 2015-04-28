import java.util.*;
import java.io.*;
public class ProblemSets {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    static long[] vals;
    public static long maxSets(long E, long EM, long M, long MH, long H){
        // binary search
        vals = new long[] {E, EM, M, MH, H};
        long la = 0;
        long ra = EM;
        long lb = -1;
        while (la < ra) {
            long ma = (la + ra) / 2;
            lb = 0;
            long rb = MH;
            while (lb < rb) {
                long mb = (lb + rb) / 2;
                long[] res = res(ma, mb);
                long[] res2 = res(ma, mb + 1);
                if (Math.min(res[1], res[2]) < Math.min(res2[1], res2[2])) {
                    lb = mb + 1;
                } else {
                    rb = mb;
                }
            }
            long[] res = res(ma, lb);
            long[] res2 = res(ma + 1, lb);
            if (Math.min(res[1], res[0]) < Math.min(res2[1], res2[0])) {
                la = ma + 1;
            } else {
                ra = ma;
            }
        }
        long[] res = res(la, lb);
        return Math.min(res[0], Math.min(res[1], res[2]));
    }
    static long[] res(long a, long b) {
        return new long[] {vals[0] + a, vals[1] - a + vals[2] + b, vals[3] - b + vals[4]};
    }
    public static void main(String args[]) {

    }
}
