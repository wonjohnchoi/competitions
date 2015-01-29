import java.util.*;
import java.io.*;

public class D {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(new FileReader("D2.in"));
        int T = sc.nextInt();
        while (T-- > 0) {
            double n, m, a, b, c, d, r;
            n = sc.nextDouble();
            m = sc.nextDouble();
            ZT.a = sc.nextDouble();
            ZT.b = sc.nextDouble();
            ZT.c = sc.nextDouble();
            ZT.d = sc.nextDouble();
            ZT.r = sc.nextDouble();
            

            ZT zt = new ZT(0, 1, n/m);
            // double originalT = t;
            ArrayList<ZT> zts = new ArrayList<ZT>();
            zts.add(zt);

            int idx = -1;
            while (true) {
                zt = zt.next();
                idx = zts.indexOf(zt);
                if (idx != -1) {
                    break;
                }
                zts.add(zt);
            }
            
            ZT found = zts.get(idx);

            // found.c2 * z[zt.t] + found.co1 = zt.c2 * z[zt.t] + zt.co1;
            double ztT = (zt.co1 - found.co1) / (found.co2 - zt.co2);
            // System.out.println(ztT);
            double ans = ztT * zt.co2 + zt.co1;
            System.out.println(ans);
        }

    }

    public static class ZT {
        static double a, b, r, c, d;
        double co1, co2, t;
        public ZT(double co1, double co2, double t) {
            this.co1 = co1;
            this.co2 = co2;
            this.t = t;
        }

        @Override
        public boolean equals(Object o) {
            ZT zt = (ZT) o;
            return Math.abs(t - zt.t) < 1e-10;
        }

        public ZT next() {
            double nco1, nco2, nt;
            nco1 = co1;
            nco2 = co2;
            nt = t;
            
            if (t <= 0) {
                nco1 += co2 * a / r;
                nco2 *= (b + r * t * t) / r;
                nt = -1 - 2 * t;
            } else {
                nco1 += co2 * c / r;
                nco2 *= (d + r * t * t) / r;
                nt = 1 - 2 * t;
            }
            // System.out.println(nco1 + " " + nco2 + " " + nt);
            return new ZT(nco1, nco2, nt);
        }
    }
}
