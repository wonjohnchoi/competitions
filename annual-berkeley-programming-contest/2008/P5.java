import java.util.*;
import java.io.*;
/*
Wonjohn Choi
Solved with Java
2008 Annual Berkeley Programming Contest: Problem 5
*/
class P5 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        while (in.hasNextInt()) {
            int p = in.nextInt();
            Vector<Integer> parties = new Vector<Integer>(p);
            int totalForce = 0;
            for (int i = 0; i < p; i += 1) {
                parties.add(in.nextInt());
                totalForce += parties.lastElement();
            }
            
            for (int j = 0; j < p; j += 1) {
                int removed = parties.remove(j);
                int power = getPower(parties, removed, totalForce);
                parties.add(j, removed);
                System.out.printf("Party %d has power index %d\n", j + 1, power);
            }
            
            
        }
    }
    
    static int getPower(Vector<Integer> parties, int force, int totalForce) {
        int power = 0;
        for (int i = 0; i < Math.pow(2, parties.size()); i += 1) {
            String table = Integer.toBinaryString(i);
            while (table.length() < parties.size()) {
                table = "0" + table;
            }
            
            int subForce = 0;
            for (int j = 0; j <parties.size(); j += 1) {
                if (table.charAt(j) == '1') {
                    subForce += parties.get(j);
                }
            }
            
            if (subForce <= totalForce / 2 && (subForce + force) > totalForce / 2) {
                power += 1;
            }
        }
        
        return power;
    }
    
    
}