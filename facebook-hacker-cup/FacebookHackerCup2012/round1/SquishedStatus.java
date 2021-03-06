import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class SquishedStatus {
    public static void main(String args[]) {
        Scanner in = null;
        try {
            in = new Scanner(new FileReader("squishedstatus.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("squishedstatus.out"));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        int t = in.nextInt();

        for (int i = 0; i < t; i += 1) {
            long max = in.nextLong();
            String msg = in.next();
            long[][] cache = new long[msg.length()][msg.length() + 1];
            for (long[] row : cache) {
                Arrays.fill(row, -1);
            }
            long ways = getWay(cache, msg, 0, msg.length(), max);
            out.printf("Case #%d: %d\n", i + 1, ways);
        }
        out.close();

    }
    
    private static long getWay(long[][] cache, String msg, int sIdx, int eIdx, long max) {
        //System.out.println(sIdx);
        //System.out.println(eIdx);
        if (cache[sIdx][eIdx] == -1) {
            long sum = 0;
            if (msg.charAt(sIdx) != '0') {
                for (int i = sIdx + 1; i < eIdx; i += 1) {
                    if (msg.substring(sIdx, i).length() > 3) {
                        continue;
                    }
                    int val = Integer.parseInt(msg.substring(sIdx, i));
                    
                    if (1 <= val && val <= max) {
                        sum = (sum + getWay(cache, msg, i, eIdx, max)) % 4207849484L;
                    }
                }
                if (msg.subSequence(sIdx, eIdx).length() <= 3) {
                    int val = Integer.parseInt(msg.substring(sIdx, eIdx));
                    if (1 <= val && val <= max) {
                        sum += 1;
                    }
                } 
            }
            
            cache[sIdx][eIdx] = sum % 4207849484L;
        }
        return cache[sIdx][eIdx];
    }
    
    
}
