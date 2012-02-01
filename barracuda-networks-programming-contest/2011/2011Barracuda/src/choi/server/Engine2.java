package choi.server;


public class Engine2 {
    boolean[] fixed;
    int[] rack;
    int nMoves = 0;
    int totalScore = 0;
    int totalGame = 0;
    int wins = 0;
    int ties = 0;
    int loses = 0;
    static int version = 2;

    public Engine2() {
        fixed = new boolean[20];
    }

    /**
     * 1 ~ 4 -> idx: 0 77 ~ 80 -> idx: 19
     * 
     * @param idx
     * @return card value of center (1~80)
     */
    public static double center(int idx) {
        return 2.5 + idx * 4;
    }

    public void reset() {
        fixed = new boolean[20];
        rack = null;
    }
    public void initRack(Object[] rackO) {
        if (rack == null) {
            rack = new int[20];
            for (int i = 0; i < rack.length; i += 1) {
                rack[i] = (Integer) rackO[i];
                double[] range = getRange(4, i);
                if (range[0] <= rack[i] && rack[i] <= range[1]) {
                    fixed[i] = true;
                }
            }
        }
    }
    
    public int getIdx(int val) {
        int off = 4;
        for (int i = 0; i < rack.length; i += 1) {
           // if (!fixed[i]) {
                double[] range = getRange(off, i);
                if (range[0] <= val && val <= range[1]) {
                    fixed[i] = true;
                    rack[i] = val;
                    return i;
                }
           // } else {
                
           // }
        }
        return -1;
    }

    public double[] getRange(double off, int idx) {
        double min, max;
        double center = center(idx);
        min = Math.max(1, center - off);
        max = Math.min(80, center + off);
        if (idx == 0) {
            if (fixed[idx + 1]) {
                max = rack[idx + 1];
            }
        } else if (idx == 19) {
            if (fixed[idx - 1]) {
                min = rack[idx - 1];
            }
        } else {
            if (fixed[idx + 1]) {
                max = rack[idx + 1];
            }
            if (fixed[idx - 1]) {
                min = rack[idx - 1];
            }
        }
        return new double[] { min, max };
    }
}
