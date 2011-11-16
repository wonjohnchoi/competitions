/**
Wonjohn Choi
Solved with Java
2011 Annual Berkeley Programming Contest [Practice]: Problem 1
*/
import java.util.*;
import java.io.*;
class P1{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        Vector<Integer> vals = new Vector<Integer>();
        long sum = 0;
        while (in.hasNextInt()) {
            vals.add(in.nextInt());
            sum += vals.lastElement();
        }
        Collections.sort(vals);
        
        double avg = (double) sum / vals.size();
        
        Vector<Double> diffs = new Vector<Double>();
        for (Integer val: vals) {
            diffs.add(Math.abs(val - avg));
        }
        
        String ans = "";
        double bestDiff = Collections.min(diffs);
        for (int i = 0; i < vals.size(); i += 1) {
            if (diffs.get(i) == bestDiff) {
                ans = ans + vals.get(i) + " ";
            }
        }
        
        ans = ans.trim();
        System.out.println(ans);
        System.exit(0);
    }
  
  
}

