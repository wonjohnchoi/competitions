import java.util.*;
import java.io.*;
/*
Wonjohn Choi
Solved with Java
2008 Annual Berkeley Programming Contest: Problem 1
*/
class P1 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        int nCases = 0;
        while(in.hasNextLine()) {
            nCases += 1;
            int counter = 0;
            int best = Integer.MIN_VALUE;
            int sum = 0;
            String line = in.nextLine().trim();
            
            while (!line.isEmpty()) {
                counter += 1;
                int len = 0;
                for (char c: line.toCharArray()) {
                    if (c ==  'X') {
                        len += 1;
                    }
                }
                sum += len;
                if (len > best) {
                    best = len;
                }
                
                line = in.nextLine().trim();
            }
            
            System.out.printf("Image %d: %d\n", nCases, best * counter -  sum);
        }
    }
    
    
}