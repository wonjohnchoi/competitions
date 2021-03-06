import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


public class PickingCards {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        //int MAXN = 50000;
        long MOD = 1000000007;
        
        int t = in.nextInt();
        for (int i = 0; i < t ; i += 1) {
            int n = in.nextInt();
            LinkedList<Integer> ins = new LinkedList<Integer>();
            
            for (int j = 0; j < n; j += 1) {
                ins.add(in.nextInt());
            }
            
            Collections.sort(ins);
            Collections.reverse(ins);
            //int[] weight = new int[MAXN];
            //int[] freq = new int[MAXN];
            LinkedList<Integer> weight = new LinkedList<Integer>();
            LinkedList<Integer> freq = new LinkedList<Integer>();
            //int idx = 0;
            ListIterator<Integer> iter= ins.listIterator();

            while (iter.hasNext()) {
                int w = iter.next();
                iter.remove();
                weight.add(w);
                int f = 1;
                while (iter.hasNext()) {
                    if (iter.next() == w) {
                        iter.remove();
                        f += 1;
                    } else {
                        iter.previous();
                        break;
                    }
                }
                freq.add(f);
                //idx += 1;
            }
            
            int choices = n;
            long result = 1;
            Iterator<Integer> freqIter = freq.iterator();
            Iterator<Integer> weightIter = weight.iterator();
            while (freqIter.hasNext()) {
                int f = freqIter.next();
                int w = weightIter.next();
                //System.out.printf("(f: %d w: %d) ", f, w);
                result *= perm(choices - w, f, MOD) % MOD;
                choices -= f;
               
            }
            
            System.out.println(result);
        }
        
    }
    
    static void dump(int[] data) {
        for (Integer d : data) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    private static long perm(int n, int r, long MOD) {
        if (n < r) {
            return 0;
        }
        long result = 1;
        for (int i = n; i >= n - r + 1; i -= 1) {
            result = result * i % MOD;
        }
        return result;
        
    }
    
    
}
