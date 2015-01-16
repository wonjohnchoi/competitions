package p2008WorldFinals;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

public class A {
    public static void main(String args[]) throws IOException {
        Scanner in = new Scanner(new FileReader("A-large.in"));
        // Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new FileWriter("A-large.out"));

        int T = in.nextInt();
        int c = 1;
        while (T-- > 0) {
            int N = in.nextInt();
            Vector<Person> ppl = new Vector<Person>(N);
            Set<Integer> Cs = new TreeSet<Integer>();
            
            while (N-- > 0) {
                Person p = new Person();
                p.A = in.nextInt();
                p.B = in.nextInt();
                p.C = in.nextInt();
                Cs.add(p.C);
                ppl.add(p);
            }
            int best = Integer.MIN_VALUE;
            for (Integer C : Cs) {
                int AB = 10000 - C;
                int[] cntByA = new int[AB + 2];
                int[] cntByB = new int[AB + 2];
                for (Person p : ppl) {
                    if (p.C <= C && p.A + p.B <= AB) {
                        cntByA[p.A] += 1;
                        cntByB[p.B] += 1;
                    }
                }
               
                int cur = 0;
                for (int A = 0, B = AB; A <= AB; ++A, --B) {
                    cur = cur + cntByA[A] - cntByB[B + 1];
                    if (cur > best) {
                        best = cur;
                    }
                }
            }
            out.printf("Case #%d: %d\n", c, best);
            ++c;

        }
        out.close();
    }
    static class Person {
        int A, B, C;
    }
}
