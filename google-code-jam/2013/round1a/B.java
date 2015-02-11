import java.util.*;
import java.io.*;
public class B {
    public static class State {
        long gain = 0;
        long energy = 0;
        public State(long gain, long energy) {
            this.gain = gain;
            this.energy = energy;
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int E, R, N;
            E = sc.nextInt();
            R = sc.nextInt();
            N = sc.nextInt();
            long[] gains = null;
            for (int n = 0; n < N; n++) {
                int value = sc.nextInt();
                if (n == 0) {
                    gains = new long[E + 1];
                    for (int e = R; e <= E; e++) {
                        gains[(int) Math.min(E - e + R, E)] = value * e;
                    }
                } else {
                    long[] newGains = new long[E + 1];
                    long prevGain = 0;
                    for (int energy = E; energy >= 1; energy--) {
                        if (gains[energy] == 0) continue;
                        if (prevGain >= gains[energy]) continue;
                        prevGain = gains[energy];
                        int minUsed = Math.max(R + energy - E, 0);
                        for (int used = minUsed; used <= energy; used++) {
                            long newGain1 = newGains[energy - used + R];
                            long newGain2 = gains[energy] + value * used;
                            newGains[energy - used + R] = Math.max(newGain1, newGain2); 
                        }
                    }
                    gains = newGains;
                }
            }
            long maxGain = Long.MIN_VALUE;
            for (long gain : gains) {
                maxGain = Math.max(maxGain, gain);
            }
            System.out.printf("Case #%d: %d\n", tc, maxGain);
        }
    }
}
